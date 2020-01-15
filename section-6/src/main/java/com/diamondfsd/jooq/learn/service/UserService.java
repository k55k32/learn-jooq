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

    @Transactional
    public S1UserPojo save() {
        S1UserPojo userPojo = new S1UserPojo();
        userPojo.setUsername("service insert");
        s1UserDao.insert(userPojo);
        return userPojo;
    }
}
