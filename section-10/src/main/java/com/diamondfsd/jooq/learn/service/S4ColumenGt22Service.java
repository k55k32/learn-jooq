package com.diamondfsd.jooq.learn.service;


import com.diamondfsd.jooq.learn.extend.GenericServiceImpl;
import com.diamondfsd.jooq.learn.jooq.tables.daos.S4ColumenGt22Dao;
import com.diamondfsd.jooq.learn.entity.S4ColumenGt22;

import org.springframework.stereotype.Service;


@Service
public class S4ColumenGt22Service extends GenericServiceImpl<S4ColumenGt22, Integer> {

    public S4ColumenGt22Service(S4ColumenGt22Dao dao) {
        super(dao);
    }
}
