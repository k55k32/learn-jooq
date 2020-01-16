package com.diamondfsd.jooq.learn.service;

import com.diamondfsd.jooq.learn.codegen.tables.daos.S1UserDao;
import com.diamondfsd.jooq.learn.codegen.tables.pojos.S1UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private S1UserDao s1UserDao;

    @Transactional(rollbackFor = Throwable.class)
    public S1UserPojo saveAndThrowError(S1UserPojo userPojo) {
        s1UserDao.insert(userPojo);
        throw new RuntimeException("test throw roll back");
    }
}
