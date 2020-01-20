package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.tables.daos.S1UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Diamond
 */
@SpringBootApplication
public class Section9Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Section9Main.class);
        S1UserDao bean = run.getBean(S1UserDao.class);
        bean.update();
    }
}
