package com.diamondfsd.jooq.learn.service;

import com.diamondfsd.jooq.learn.BaseTest;
import com.diamondfsd.jooq.learn.Section6Main;
import com.diamondfsd.jooq.learn.codegen.tables.daos.S1UserDao;
import com.diamondfsd.jooq.learn.codegen.tables.pojos.S1UserPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends BaseTest{

    @Autowired
    private UserService userService;

    @Autowired
    S1UserDao userDao;

    @Test
    void save() {
        S1UserPojo userPojo = userService.save();
        Assertions.assertNotNull(userPojo.getId());
    }
}
