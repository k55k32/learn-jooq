package com.diamondfsd.jooq.learn.service;

import com.diamondfsd.jooq.learn.BaseTest;
import com.diamondfsd.jooq.learn.codegen.tables.daos.S1UserDao;
import com.diamondfsd.jooq.learn.codegen.tables.pojos.S1UserPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Autowired
    S1UserDao userDao;

    Integer saveUserId;

    @Test
    @Order(1)
    public void save() {
        List<S1UserPojo> allUser = userDao.findAll();
        Integer id = allUser.get(allUser.size() - 1).getId();
        S1UserPojo saveUser = new S1UserPojo();
        saveUserId = id + 1;
        saveUser.setId(saveUserId);
        saveUser.setUsername("saveAndThrow");
        try {
            S1UserPojo userPojo = userService.saveAndThrowError(saveUser);
            Assertions.fail("need throw exception");
        } catch (Exception ignore) {
            Assertions.assertEquals(RuntimeException.class, ignore.getClass());
        }
    }

    @Test
    @Order(2)
    public void findById() {
        S1UserPojo userPojo = userDao.findById(saveUserId);
        Assertions.assertNull(userPojo);
    }
}
