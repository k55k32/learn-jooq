package com.diamondfsd.jooq.learn;

import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.diamondfsd.jooq.learn.codegen.Tables.S1_USER;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserSelectTest extends BaseTest {
    /**
     * 查询操作
     */
    @Test
    public void selectTest() {
        // 基础查询
        Result<Record> fetchAll = dslContext.select().from(S1_USER)
                .where(S1_USER.ID.in(1, 2)).fetch();
        Assertions.assertEquals(2, fetchAll.size());

        fetchAll.forEach(record -> {
            Assertions.assertNotNull(record.getValue(S1_USER.ID));
            Assertions.assertNotNull(record.getValue(S1_USER.USERNAME));
            Assertions.assertNotNull(record.getValue(S1_USER.ADDRESS));
            Assertions.assertNotNull(record.getValue(S1_USER.CREATE_TIME));
            Assertions.assertNotNull(record.getValue(S1_USER.UPDATE_TIME));
        });


        // 查询部分字段
        Result<Record2<Integer, String>> fetchTwoRecord = dslContext.select(S1_USER.ID, S1_USER.USERNAME)
                .from(S1_USER).fetch();
        Assertions.assertFalse(fetchAll.isEmpty());

        fetchTwoRecord.forEach(record -> {
            Assertions.assertNotNull(record.getValue(S1_USER.ID));
            Assertions.assertNotNull(record.getValue(S1_USER.USERNAME));
            Assertions.assertEquals(record.getValue(S1_USER.ID), record.value1());
            Assertions.assertEquals(record.getValue(S1_USER.USERNAME), record.value2());
            try {
                record.getValue(S1_USER.ADDRESS);
                Assertions.fail("此查询没有查询ADDRESS字段，所以在获取此字段的时候，会报错");
            } catch (IllegalArgumentException e) {
            }
        });

        // 所有的SQL语法都有可以用对应的API进行链式调用
        Result<Record> recordResult = dslContext.select().from(S1_USER)
                .where(S1_USER.ID.in(1, 2))
                .orderBy(S1_USER.ID.desc())
                .limit(1).fetch();
        Assertions.assertEquals(1, recordResult.size());

        Record record = recordResult.get(0);
        Assertions.assertEquals(2, record.get(S1_USER.ID));

    }
}
