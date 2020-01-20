package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.tables.daos.S1UserDao;
import com.diamondfsd.jooq.learn.codegen.tables.pojos.S1UserPojo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

/**
 * @author Diamond
 */
@SpringBootApplication
public class Section8Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Section8Main.class);
    }
}
