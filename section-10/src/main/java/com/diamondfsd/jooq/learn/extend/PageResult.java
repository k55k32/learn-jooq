package com.diamondfsd.jooq.learn.extend;

import java.util.List;

/**
 * 分页结果封装类
 * @param <T> 数据类型
 */
public class PageResult<T> {
    /**
     * 分页数据
     */
    private List<T> data;

    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 当期页码 从1开始
     */
    private Integer currentPage;
    /**
     * 数据总数
     */
    private Long total;

    public PageResult() {}

    public PageResult(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public PageResult(List<T> data, int currentPage, int pageSize, long total) {
        this.data = data;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void from(PageResult<T> pageResult) {
        setPageSize(pageResult.pageSize);
        setCurrentPage(pageResult.currentPage);
        setTotal(pageResult.total);
        setData(pageResult.data);
    }

    public PageResult<T> into(PageResult<T> into) {
        into.from(this);
        return into;
    }
}
