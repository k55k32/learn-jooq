package com.diamondfsd.jooq.learn.codegen.learn_jooq.tables.daos;

import com.diamondfsd.jooq.learn.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class S1UserDaoTest extends BaseTest {
    @Autowired
    S1UserDao userDao;

    @Test
    public void userDaoExists() {
        Assertions.assertNotNull(userDao);
    }

}
