package com.diamondfsd.jooq.learn.extend;

import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.conf.Settings;
import org.jooq.impl.DAOImpl;
import org.jooq.impl.DSL;

import java.util.*;

import static org.jooq.impl.DSL.row;

/**
 * @author Diamond
 */
public abstract class ExtendDAOImpl<R extends UpdatableRecord<R>, P, T> extends DAOImpl<R, P, T>
        implements ExtendDAO<R, P, T> {

    protected ExtendDAOImpl(Table<R> table, Class<P> type) {
        super(table, type);
    }

    protected ExtendDAOImpl(Table<R> table, Class<P> type, Configuration configuration) {
        super(table, type, configuration);
    }

    @Override
    public DSLContext create() {
        return DSL.using(configuration());
    }

    @Override
    public P fetchOne(Condition condition) {
        return create().selectFrom(getTable())
                .where(condition)
                .orderBy(getTable().getPrimaryKey().getFields())
                .fetchOne(mapper());
    }

    @Override
    public Optional<P> fetchOneOptional(Condition condition) {
        return Optional.ofNullable(fetchOne(condition));
    }

    @Override
    public List<P> fetch(Condition condition, SortField<?>... sortFields) {
        return create().selectFrom(getTable())
                .where(condition)
                .orderBy(sortFields)
                .fetch(mapper());
    }

    @Override
    public List<P> fetchById(Collection<T> ids) {
        return fetch(equalPrimary(ids));
    }

    private Condition equalPrimary(Collection<T> ids) {
        Condition condition;
        TableField<R, ?>[] pk = getTable().getPrimaryKey().getFieldsArray();
        if (pk.length == 1) {
            if (ids.size() == 1) {
                condition = ((Field<Object>) pk[0]).equal(ids.iterator().next());
            } else {
                condition = pk[0].in(ids);
            }
        } else {
            condition = row(pk).in(ids.toArray(new Record[0]));
        }
        return condition;
    }

    @Override
    public PageResult<P> fetchPage(PageResult<P> page) {
        return fetchPage(page, DSL.noCondition());
    }

    @Override
    public PageResult<P> fetchPage(PageResult<P> page, Condition condition, SortField<?>... sortFields) {
        return fetchPage(page, create().selectFrom(getTable())
                .where(condition)
                .orderBy(sortFields));
    }

    @Override
    public PageResult<P> fetchPage(PageResult<P> page, SelectLimitStep<?> selectLimitStep) {
        return fetchPage(page, selectLimitStep, r -> r.into(getType()));
    }

    @Override
    public <O> PageResult<O> fetchPage(PageResult<O> page, SelectLimitStep<?> selectLimitStep,
                                       RecordMapper<? super Record, O> mapper) {
        int size = page.getPageSize();
        int start = (page.getCurrentPage() - 1) * size;
        // 在页数为零的情况下小优化，不查询数据库直接返回数据为空集合的分页包装类
        if (size == 0) {
            return new PageResult<>(Collections.emptyList(), start, 0, 0);
        }
        String pageSql = selectLimitStep.getSQL(ParamType.INLINED);
        String SELECT = "select";

        pageSql = SELECT + " SQL_CALC_FOUND_ROWS " +
                pageSql.substring(pageSql.indexOf(SELECT) + SELECT.length())
                + " limit ?, ? ";

        List<O> resultList = create().fetch(pageSql, start, size).map(mapper);
        Long total = create().fetchOne("SELECT FOUND_ROWS()").into(Long.class);
        PageResult<O> result = page.into(new PageResult<>());
        result.setData(resultList);
        result.setTotal(total);
        return result;
    }

    @Override
    public <O> PageResult<O> fetchPage(PageResult<O> page, SelectLimitStep<?> selectLimitStep, Class<O> pojoType) {
        return fetchPage(page, selectLimitStep, r -> r.into(pojoType));
    }

    @Override
    public void updateSelective(P object) {
        this.updateSelective(Collections.singletonList(object));
    }

    @Override
    public void updateSelective(P... objects) {
        this.updateSelective(Arrays.asList(objects));
    }

    @Override
    public void updateSelective(Collection<P> objects) {
        if (objects.size() > 1) {
            create().batchUpdate(recordsSelective(objects, true)).execute();
        } else if (objects.size() == 1) {
            recordsSelective(objects, true).get(0).update();
        }
    }

    @Override
    public void insertSelective(P object) {
        R record = recordsSelective(Collections.singletonList(object), false).get(0);
        record.insert();
        record.into(object);
    }

    /**
     * 重写 DAOImpl.insert 方法的原因是因为
     * 在默认配置下父级的方法不会进行批量插入操作，而是便利每个对象
     * 进行Insert操作，会产生N条SQL语句，影响性能
     *
     * @param objects
     */
    @Override
    public void insert(Collection<P> objects) {
        Settings settings = configuration().settings();
        Boolean oldConfigIsReturnRecord = settings.isReturnRecordToPojo();
        if (objects.size() > 1 && !settings.isReturnAllOnUpdatableRecord()) {
            settings.setReturnRecordToPojo(false);
        }
        try {
            super.insert(objects);
        } finally {
            settings.setReturnRecordToPojo(oldConfigIsReturnRecord);
        }
    }

    @Override
    public void insertSelective(P... objects) {
        insertSelective(Arrays.asList(objects));
    }

    @Override
    public void insertSelective(Collection<P> objects) {
        if (objects.size() > 0) {
            create().batchInsert(recordsSelective(objects, false)).execute();
        }
    }

    private List<R> recordsSelective(Collection<P> objects, boolean forUpdate) {
        List<R> result = new ArrayList<>(objects.size());
        Field<?>[] pk = getTable().getPrimaryKey().getFieldsArray();
        DSLContext ctx = create();

        for (P object : objects) {
            R record = ctx.newRecord(getTable(), object);

            if (forUpdate && pk != null) {
                for (Field<?> field : pk) {
                    record.changed(field, false);
                }
            }

            for (int i = 0; i < record.size(); i++) {
                Object data = record.get(i);
                record.changed(i, data != null);
            }
            result.add(record);
        }

        return result;
    }
}
