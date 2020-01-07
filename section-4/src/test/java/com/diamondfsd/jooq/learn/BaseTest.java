package com.diamondfsd.jooq.learn;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    DSLContext dslContext;
    Connection connection;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @BeforeAll
    public void initDSLContext() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/learn-jooq?serverTimezone=GMT%2B8";
        String jdbcUsername = "root";
        String jdbcPassword = "root";
        connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        dslContext = DSL.using(connection, SQLDialect.MYSQL);

        Integer fetchOneResult = dslContext.select().fetchOne().into(Integer.class);
        Assertions.assertNotNull(fetchOneResult);
        Assertions.assertEquals(1, fetchOneResult.intValue());
    }

    @BeforeEach
    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    @AfterEach
    public void rollbackTransaction() throws SQLException {
        connection.rollback();
    }
}
