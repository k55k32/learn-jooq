package com.diamondfsd.jooq.learn.extend;

import org.jooq.*;

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
     * 读取分页数据
     *
     * @param pageResult 分页参数
     * @param condition  约束条件
     * @param sortFields 排序字段
     * @return 分页结果集
     */
    PageResult<P> fetchPage(PageResult<P> pageResult, Condition condition, SortField<?>... sortFields);

    /**
     * 读取分页数据
     *
     * @param pageResult      分页参数
     * @param selectLimitStep 查询语句
     * @return 分页结果集
     */
    PageResult<P> fetchPage(PageResult<P> pageResult, SelectLimitStep<?> selectLimitStep);

    /**
     * 任意类型读取分页数据
     *
     * @param pageResult      分页参数
     * @param selectLimitStep 查询语句
     * @param mapper          结果映射方式
     * @param <O>             返回类型的泛型
     * @return 分页结果集
     */
    <O> PageResult<O> fetchPage(PageResult<O> pageResult, SelectLimitStep<?> selectLimitStep,
                                RecordMapper<? super Record, O> mapper);

    /**
     * 任意类型读取分页数据
     *
     * @param pageResult      分页参数
     * @param selectLimitStep 查询语句
     * @param pojoType        POJO类型
     * @param <O>             返回类型的泛型
     * @return 分页结果集
     */
    <O> PageResult<O> fetchPage(PageResult<O> pageResult, SelectLimitStep<?> selectLimitStep,
                                Class<O> pojoType);

}
