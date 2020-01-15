package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.tables.daos.S1UserDao;
import com.diamondfsd.jooq.learn.codegen.tables.pojos.S1UserPojo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

/**
 * @author Diamond
 */
@ComponentScan
public class Section6Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Section6Main.class);

        S1UserDao bean = context.getBean(S1UserDao.class);

        List<S1UserPojo> s1UserPojo = bean.findAll();

    }
}
