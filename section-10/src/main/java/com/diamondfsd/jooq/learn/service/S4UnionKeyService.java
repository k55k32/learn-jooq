package com.diamondfsd.jooq.learn.service;


import com.diamondfsd.jooq.learn.extend.GenericServiceImpl;
import com.diamondfsd.jooq.learn.jooq.tables.daos.S4UnionKeyDao;
import com.diamondfsd.jooq.learn.entity.S4UnionKey;

import org.jooq.Record2;
import org.springframework.stereotype.Service;


@Service
public class S4UnionKeyService extends GenericServiceImpl<S4UnionKey, Record2<Integer, Integer>> {

    public S4UnionKeyService(S4UnionKeyDao dao) {
        super(dao);
    }
}
