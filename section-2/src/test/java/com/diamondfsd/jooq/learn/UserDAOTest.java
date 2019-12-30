package com.diamondfsd.jooq.learn;

import com.alibaba.fastjson.JSONObject;
import com.diamondfsd.jooq.learn.codegen.tables.S1User;
import com.diamondfsd.jooq.learn.codegen.tables.records.S1UserRecord;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.JooqLogger;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.diamondfsd.jooq.learn.codegen.Tables.S1_USER;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDAOTest {

    DSLContext dslContext;
    Connection connection;

    @BeforeAll
    public void initDSLContext() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/learn-jooq?serverTimezone=GMT%2B8";
        String jdbcUsername = "root";
        String jdbcPassword = "root";
        connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        dslContext = DSL.using(connection, SQLDialect.MYSQL);
    }

    @BeforeEach
    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    @AfterEach
    public void rollbackTransaction() throws SQLException {
        connection.rollback();
    }

    @Test
    public void testConnection() {
        Integer fetchOneResult = dslContext.select().fetchOne().into(Integer.class);
        Assertions.assertNotNull(fetchOneResult);
        Assertions.assertEquals(1, fetchOneResult.intValue());
    }


    /**
     * 批量插入
     */
    @Test
    public void insertBatch() {
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
        S1UserRecord userRecord = dslContext.insertInto(S1_USER,
                S1_USER.USERNAME, S1_USER.ADDRESS, S1_USER.EMAIL)
                .values("username1", "demo-address1", "diamondfsd@gmail.com")
                .returning(S1_USER.ID, S1_USER.CREATE_TIME)
                .fetchOne();
        Assertions.assertNotNull(userRecord.getId());
        Assertions.assertNotNull(userRecord.getCreateTime());

        // 使用Record接口进行插入
        S1UserRecord record = dslContext.newRecord(S1_USER);
        record.setUsername("usernameRecord1");
        record.setEmail("diamondfsd@gmail.com");
        record.setAddress("address hello");
        int recordInsertRows = record.store();
        Assertions.assertEquals(1, recordInsertRows);

        // 使用Record接口进行批量插入
        List<S1UserRecord> recordList = IntStream.range(0, 10).mapToObj(i -> {
            S1UserRecord s1UserRecord = new S1UserRecord();
            s1UserRecord.setUsername("usernameBatchInsert" + i);
            s1UserRecord.setEmail("diamondfsd@gmail.com");
            return s1UserRecord;
        }).collect(Collectors.toList());
        String jsonString = JSONObject.toJSONString(recordList);
        List<S1UserRecord> s1UserRecordsFromJson = JSONObject.parseArray(jsonString, S1UserRecord.class);

        int[] executeRows = dslContext.batchInsert(recordList).execute();

        Assertions.assertEquals(recordList.size(), executeRows.length);
    }
}
