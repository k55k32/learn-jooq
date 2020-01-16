package com.diamondfsd.jooq.learn.codegen.learn_jooq_2.tables.daos;

import com.diamondfsd.jooq.learn.BaseTest;
import org.jooq.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class S7UserDaoTest extends BaseTest {
    @Autowired
    S7UserDao userDao;

    @Test
    public void s7UserDaoExists() {
        Assertions.assertNotNull(userDao);
        Configuration configuration = userDao.configuration();

        userDao.findAll();
    }

}
