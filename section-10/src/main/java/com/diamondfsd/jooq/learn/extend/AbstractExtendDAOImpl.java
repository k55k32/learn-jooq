package com.diamondfsd.jooq.learn.extend;

import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.impl.DAOImpl;
import org.jooq.impl.DSL;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Diamond
 */
public abstract class AbstractExtendDAOImpl<R extends UpdatableRecord<R>, P, T> extends DAOImpl<R, P, T>
        implements ExtendDAO<R, P, T> {

    protected AbstractExtendDAOImpl(Table<R> table, Class<P> type) {
        super(table, type);
    }

    protected AbstractExtendDAOImpl(Table<R> table, Class<P> type, Configuration configuration) {
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
    public PageResult<P> fetchPage(PageResult<P> pageResult, Condition condition, SortField<?>... sortFields) {
        return fetchPage(pageResult, create().selectFrom(getTable())
                .where(condition)
                .orderBy(sortFields));
    }

    @Override
    public PageResult<P> fetchPage(PageResult<P> pageResult, SelectLimitStep<?> selectLimitStep) {
        return fetchPage(pageResult, selectLimitStep, r -> r.into(getType()));
    }

    @Override
    public <O> PageResult<O> fetchPage(PageResult<O> pageResult, SelectLimitStep<?> selectLimitStep,
                                       RecordMapper<? super Record, O> mapper) {
        int size = pageResult.getPageSize();
        int start = (pageResult.getCurrentPage() - 1) * size;
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
        PageResult<O> result = pageResult.into(new PageResult<>());
        result.setData(resultList);
        result.setTotal(total);
        return result;
    }

    @Override
    public <O> PageResult<O> fetchPage(PageResult<O> pageResult, SelectLimitStep<?> selectLimitStep, Class<O> pojoType) {
        return fetchPage(pageResult, selectLimitStep, r -> r.into(pojoType));
    }
}
