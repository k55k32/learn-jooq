package com.diamondfsd.jooq.learn.s1;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/learn-jooq?serverTimezone=GMT%2B8";
        String username = "root";
        String password = "root";

        // get jdbc connection
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
