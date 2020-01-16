package com.diamondfsd.jooq.learn.codegen.tables.daos;

import com.diamondfsd.jooq.learn.BaseTest;
import com.diamondfsd.jooq.learn.codegen.tables.pojos.S1UserPojo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class S1UserDaoTest extends BaseTest {
    @Autowired
    S1UserDao s1UserDao;

    Integer insertUserId = null;

    @Test
    public void findAll() {
        List<S1UserPojo> userAll = s1UserDao.findAll();
        Assertions.assertTrue(userAll.size() > 0);
    }

    @Test
    @Order(1)
    public void insert() {
        S1UserPojo s1UserPojo = new S1UserPojo();
        s1UserPojo.setUsername("hell username");
        s1UserDao.insert(s1UserPojo);
        Assertions.assertNotNull(s1UserPojo.getId());
        insertUserId = s1UserPojo.getId();
    }

    @Test
    @Order(2)
    public void findById() {
        S1UserPojo findById = s1UserDao.findById(1);
        Assertions.assertNotNull(findById);

        if (insertUserId != null) {
            S1UserPojo userPojo = s1UserDao.findById(insertUserId);
            Assertions.assertNull(userPojo);
        }
    }
}
