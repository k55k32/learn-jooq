package com.diamondfsd.jooq.learn.codegen.learn_jooq.tables.daos;

import com.diamondfsd.jooq.learn.BaseTest;
import com.diamondfsd.jooq.learn.codegen.learn_jooq.tables.pojos.S1UserPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static com.diamondfsd.jooq.learn.config.DataSourceConfig.TX_LEARN_JOOQ;

class S1UserDaoTest extends BaseTest {
    @Autowired
    S1UserDao s1UserDao;

    @Test
    @Transactional(TX_LEARN_JOOQ)
    public void insert() {
        S1UserPojo s1UserPojo = new S1UserPojo();
        s1UserPojo.setUsername("username");
        s1UserDao.insert(s1UserPojo);
        Assertions.assertNotNull(s1UserPojo.getId());
    }

}
