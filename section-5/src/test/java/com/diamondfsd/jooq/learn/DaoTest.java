package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.tables.daos.S1UserDao;
import com.diamondfsd.jooq.learn.codegen.tables.pojos.S1UserPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DaoTest extends BaseTest {

    @Test
    public void daoTest() {
        S1UserDao s1UserDao = new S1UserDao(dslContext.configuration());
        s1UserDao.setConfiguration(dslContext.configuration());

        S1UserPojo s1UserPojo = s1UserDao.fetchOneById(1);
        Assertions.assertEquals(1, s1UserPojo.getId());

        s1UserDao.fetchById(1);
    }
}
