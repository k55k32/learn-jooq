package com.diamondfsd.jooq.learn.extend;

import org.jooq.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 扩展DAO，扩展之前DAO的各类方法
 *
 * @param <R> 表对象
 * @param <P> POJO类型
 * @param <T> 主键类型
 * @author Diamond
 */
public interface ExtendDAO<R extends UpdatableRecord<R>, P, T> extends DAO<R, P, T> {

    /**
     * 获取 DSLContext
     *
     * @return DSLContext
     */
    DSLContext create();

    /**
     * 条件查询单条记录
     *
     * @param condition 约束条件
     * @return <p>
     */
    P fetchOne(Condition condition);

    /**
     * 条件查询单条记录
     *
     * @param condition 约束条件
     * @return Optional<P>
     */
    Optional<P> fetchOneOptional(Condition condition);

    /**
     * 条件查询多条，并排序
     *
     * @param condition  约束条件
     * @param sortFields 排序字段
     * @return POJO 集合
     */
    List<P> fetch(Condition condition, SortField<?>... sortFields);

    /**
     * 通过指定的 主键集合 查找数据
     *
     * @param ids
     * @return POJO 集合
     */
    List<P> fetchById(Collection<T> ids);

    /**
     * 读取分页数据
     *
     * @param page 分页参数
     * @return 分页结果集
     */
    PageResult<P> fetchPage(PageResult<P> page);

    /**
     * 读取分页数据
     *
     * @param page       分页参数
     * @param condition  约束条件
     * @param sortFields 排序字段
     * @return 分页结果集
     */
    PageResult<P> fetchPage(PageResult<P> page, Condition condition, SortField<?>... sortFields);

    /**
     * 读取分页数据
     *
     * @param page            分页参数
     * @param selectLimitStep 查询语句
     * @return 分页结果集
     */
    PageResult<P> fetchPage(PageResult<P> page, SelectLimitStep<?> selectLimitStep);

    /**
     * 任意类型读取分页数据
     *
     * @param page            分页参数
     * @param selectLimitStep 查询语句
     * @param mapper          结果映射方式
     * @param <O>             返回类型的泛型
     * @return 分页结果集
     */
    <O> PageResult<O> fetchPage(PageResult<O> page, SelectLimitStep<?> selectLimitStep,
                                RecordMapper<? super Record, O> mapper);

    /**
     * 任意类型读取分页数据
     *
     * @param page            分页参数
     * @param selectLimitStep 查询语句
     * @param pojoType        POJO类型
     * @param <O>             返回类型的泛型
     * @return 分页结果集
     */
    <O> PageResult<O> fetchPage(PageResult<O> page, SelectLimitStep<?> selectLimitStep,
                                Class<O> pojoType);


    /**
     * 对指定的 POJO 执行 UPDATE 操作， 只更新非空的部分
     *
     * @param object 需要更新的 POJO
     */
    void updateSelective(P object);

    /**
     * 对指定的 POJOs 执行 UPDATE 操作， 只更新非空的部分
     *
     * @param objects 需要更新的 POJOs
     */
    void updateSelective(P... objects);

    /**
     * 对指定的 POJOs 执行 UPDATE 操作， 只更新非空的部分
     *
     * @param objects 需要更新的 POJOs
     */
    void updateSelective(Collection<P> objects);

    /**
     * 对传入的 POJO 执行 INSERT 操作，只会插入非空的值
     * 如果有自增主键,传入的 POJO 主键会被赋值
     *
     * @param object
     * @return
     */
    void insertSelective(P object);

    /**
     * 对传入的 POJO 执行 INSERT 操作， 只会插入非空的值
     *
     * @param objects
     */
    void insertSelective(P... objects);

    /**
     * 对传入的 POJO 执行 INSERT 操作， 只会插入非空的值
     *
     * @param objects
     */
    void insertSelective(Collection<P> objects);
}
