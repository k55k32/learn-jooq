package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.tables.daos.S1UserDao;
import com.diamondfsd.jooq.learn.codegen.tables.pojos.S1UserPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class S1UserDaoTest extends BaseTest {
    @Autowired
    S1UserDao s1UserDao;

    @Test
    public void userDao() {
        List<S1UserPojo> userAll =
                s1UserDao.findAll();
        Assertions.assertTrue(userAll.size() > 0);
        S1UserPojo s1UserPojo = new S1UserPojo();
        s1UserPojo.setUsername("hell username");
        s1UserDao.insert(s1UserPojo);
    }
}
