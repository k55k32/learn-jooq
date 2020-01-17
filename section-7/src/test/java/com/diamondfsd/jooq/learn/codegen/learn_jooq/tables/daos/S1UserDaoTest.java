package com.diamondfsd.jooq.learn.codegen.learn_jooq.tables.daos;

import com.diamondfsd.jooq.learn.BaseTest;
import com.diamondfsd.jooq.learn.codegen.learn_jooq.tables.pojos.S1UserPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

class S1UserDaoTest extends BaseTest {
    @Autowired
    S1UserDao userDao;

    @Test
    @Transactional
    public void insert() {
        S1UserPojo s1UserPojo = new S1UserPojo();
        s1UserPojo.setUsername("username");
        userDao.insert(s1UserPojo);
        Assertions.assertNotNull(s1UserPojo.getId());
    }

}
