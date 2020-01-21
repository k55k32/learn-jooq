package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.jooq.tables.daos.S1UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Diamond
 */
@SpringBootApplication
public class Section10Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Section10Main.class);
        S1UserDao bean = run.getBean(S1UserDao.class);
        bean.update();
    }
}
