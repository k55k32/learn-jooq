package com.diamondfsd.jooq.learn;

import com.alibaba.fastjson.JSONObject;
import com.diamondfsd.jooq.learn.codegen.tables.records.S1UserRecord;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.diamondfsd.jooq.learn.codegen.Tables.S1_USER;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserInsertTest extends BaseTest {
    /**
     * 插入操作
     */
    @Test
    public void insertTest() {
        // 使用类似SQL的语法方式，该方法批量插入很方便
        int insertRows = dslContext.insertInto(S1_USER,
                S1_USER.USERNAME, S1_USER.ADDRESS, S1_USER.EMAIL)
                .values("username1", "demo-address1", "diamondfsd@gmail.com")
                .values("username2", "demo-address2", "diamondfsd@gmail.com")
                .execute();
        Assertions.assertEquals(2, insertRows);

        // 使用set方法进行插入
        int setInsertRows = dslContext.insertInto(S1_USER)
                .set(S1_USER.USERNAME, "usernameSet1")
                .set(S1_USER.EMAIL, "diamondfsd@gmail.com")
                .newRecord()
                .set(S1_USER.USERNAME, "usernameSet2")
                .set(S1_USER.EMAIL, "diamondfsd@gmail.com")
                .execute();
        Assertions.assertEquals(2, setInsertRows);

        // 以上两种方式都支持返回自增主键通过returning方法返回主键ID，
        // 如果需要其他的自动生成或者有默认值的字段，可以在returning内添加多个参数进行返回
        Integer userId = dslContext.insertInto(S1_USER,
                S1_USER.USERNAME, S1_USER.ADDRESS, S1_USER.EMAIL)
                .values("username1", "demo-address1", "diamondfsd@gmail.com")
                .returning(S1_USER.ID)
                .fetchOne().getId();
        Assertions.assertNotNull(userId);

        // 使用Record接口进行插入
        S1UserRecord record = dslContext.newRecord(S1_USER);
        record.setUsername("usernameRecord1");
        record.setEmail("diamondfsd@gmail.com");
        record.setAddress("address hello");
        int recordInsertRows = record.insert();
        Assertions.assertNotNull(record.getId());
        Assertions.assertNull(record.getCreateTime());
        Assertions.assertEquals(1, recordInsertRows);
    }

    @Test
    public void batchInsert() {
        // 使用Record接口进行批量插入
        List<S1UserRecord> recordList = IntStream.range(0, 10).mapToObj(i -> {
            S1UserRecord s1UserRecord = new S1UserRecord();
            s1UserRecord.setUsername("usernameBatchInsert" + i);
            s1UserRecord.setEmail("diamondfsd@gmail.com");
            return s1UserRecord;
        }).collect(Collectors.toList());
        int[] executeRows = dslContext.batchInsert(recordList).execute();
        Assertions.assertEquals(recordList.size(), executeRows.length);
        for (int i = 0; i < recordList.size(); i++) {
            S1UserRecord item = recordList.get(i);
            Assertions.assertEquals("usernameBatchInsert" + i, item.getUsername());
            Assertions.assertNull(item.getId());
        }
    }

    @Test
    public void onDuplicateKeyIgnore() {
        int execute = dslContext.insertInto(S1_USER,
                S1_USER.ID, S1_USER.USERNAME)
                .values(1, "username-1")
                .onDuplicateKeyIgnore()
                .execute();
        Assertions.assertEquals(0, execute);
    }

    @Test
    public void onDuplicateKeyUpdate() {
        int row = dslContext.insertInto(S1_USER)
                .set(S1_USER.ID, 1)
                .set(S1_USER.USERNAME, "duplicateKey-update")
                .set(S1_USER.ADDRESS, "hello world")
                .onDuplicateKeyUpdate()
                .set(S1_USER.USERNAME, "duplicateKey-update")
                .set(S1_USER.ADDRESS, "update")
                .execute();
    }
}
