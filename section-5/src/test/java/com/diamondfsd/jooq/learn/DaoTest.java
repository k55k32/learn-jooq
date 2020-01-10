package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.tables.daos.S1UserDao;
import com.diamondfsd.jooq.learn.codegen.tables.pojos.S1UserPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DaoTest extends BaseTest {

    @Test
    public void daoTest() {
        S1UserDao s1UserDao = new S1UserDao(dslContext.configuration());

        S1UserPojo s1UserPojo = s1UserDao.fetchOneById(1);
        Assertions.assertEquals(1, s1UserPojo.getId());
        S1UserPojo userPojo = s1UserDao.findById(1);
        Assertions.assertEquals(s1UserPojo.toString(), userPojo.toString());

        List<S1UserPojo> s1UserPojos =
                s1UserDao.fetchByUsername(s1UserPojo.getUsername());
        Assertions.assertTrue(s1UserPojos.size() > 0);

        List<S1UserPojo> allUser = s1UserDao.findAll();
        Assertions.assertTrue(allUser.size() > 0);
    }
}
