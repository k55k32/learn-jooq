/*
 * This file is generated by jOOQ.
 */
package com.diamondfsd.jooq.learn.codegen.tables.daos;


import com.diamondfsd.jooq.learn.codegen.tables.TS4ColumenGt22;
import com.diamondfsd.jooq.learn.codegen.tables.pojos.S4ColumenGt22Pojo;
import com.diamondfsd.jooq.learn.codegen.tables.records.S4ColumenGt22Record;
import com.diamondfsd.jooq.learn.extend.AbstractDAOExtendImpl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 表字段超22
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Repository
public class S4ColumenGt22Dao extends AbstractDAOExtendImpl<S4ColumenGt22Record, S4ColumenGt22Pojo, Integer> {

    /**
     * Create a new S4ColumenGt22Dao without any configuration
     */
    public S4ColumenGt22Dao() {
        super(TS4ColumenGt22.S4_COLUMEN_GT22, S4ColumenGt22Pojo.class);
    }

    /**
     * Create a new S4ColumenGt22Dao with an attached configuration
     */
    @Autowired
    public S4ColumenGt22Dao(Configuration configuration) {
        super(TS4ColumenGt22.S4_COLUMEN_GT22, S4ColumenGt22Pojo.class, configuration);
    }

    @Override
    public Integer getId(S4ColumenGt22Pojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchById(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public S4ColumenGt22Pojo fetchOneById(Integer value) {
        return fetchOne(TS4ColumenGt22.S4_COLUMEN_GT22.ID, value);
    }

    /**
     * Fetch records that have <code>column_1 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_1(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_1, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_1 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_1(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_1, values);
    }

    /**
     * Fetch records that have <code>column_2 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_2(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_2, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_2 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_2(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_2, values);
    }

    /**
     * Fetch records that have <code>column_3 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_3(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_3, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_3 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_3(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_3, values);
    }

    /**
     * Fetch records that have <code>column_4 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_4(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_4, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_4 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_4(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_4, values);
    }

    /**
     * Fetch records that have <code>column_5 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_5(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_5, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_5 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_5(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_5, values);
    }

    /**
     * Fetch records that have <code>column_6 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_6(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_6, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_6 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_6(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_6, values);
    }

    /**
     * Fetch records that have <code>column_7 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_7(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_7, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_7 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_7(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_7, values);
    }

    /**
     * Fetch records that have <code>column_8 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_8(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_8, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_8 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_8(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_8, values);
    }

    /**
     * Fetch records that have <code>column_9 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_9(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_9, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_9 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_9(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_9, values);
    }

    /**
     * Fetch records that have <code>column_10 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_10(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_10, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_10 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_10(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_10, values);
    }

    /**
     * Fetch records that have <code>column_11 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_11(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_11, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_11 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_11(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_11, values);
    }

    /**
     * Fetch records that have <code>column_12 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_12(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_12, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_12 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_12(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_12, values);
    }

    /**
     * Fetch records that have <code>column_13 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_13(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_13, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_13 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_13(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_13, values);
    }

    /**
     * Fetch records that have <code>column_14 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_14(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_14, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_14 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_14(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_14, values);
    }

    /**
     * Fetch records that have <code>column_15 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_15(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_15, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_15 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_15(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_15, values);
    }

    /**
     * Fetch records that have <code>column_16 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_16(String lowerInclusive, String upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_16, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_16 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_16(String... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_16, values);
    }

    /**
     * Fetch records that have <code>column_17 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_17(String lowerInclusive, String upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_17, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_17 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_17(String... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_17, values);
    }

    /**
     * Fetch records that have <code>column_18 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_18(String lowerInclusive, String upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_18, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_18 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_18(String... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_18, values);
    }

    /**
     * Fetch records that have <code>column_19 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_19(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_19, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_19 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_19(Timestamp... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_19, values);
    }

    /**
     * Fetch records that have <code>column_20 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_20(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_20, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_20 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_20(Boolean... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_20, values);
    }

    /**
     * Fetch records that have <code>column_21 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_21(Byte lowerInclusive, Byte upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_21, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_21 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_21(Byte... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_21, values);
    }

    /**
     * Fetch records that have <code>column_22 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_22(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_22, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_22 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_22(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_22, values);
    }

    /**
     * Fetch records that have <code>column_23 BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfColumn_23(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_23, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>column_23 IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByColumn_23(Integer... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.COLUMN_23, values);
    }

    /**
     * Fetch records that have <code>create_time BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfCreateTime(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.CREATE_TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>create_time IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByCreateTime(Timestamp... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.CREATE_TIME, values);
    }

    /**
     * Fetch records that have <code>update_time BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<S4ColumenGt22Pojo> fetchRangeOfUpdateTime(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(TS4ColumenGt22.S4_COLUMEN_GT22.UPDATE_TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>update_time IN (values)</code>
     */
    public List<S4ColumenGt22Pojo> fetchByUpdateTime(Timestamp... values) {
        return fetch(TS4ColumenGt22.S4_COLUMEN_GT22.UPDATE_TIME, values);
    }
}
