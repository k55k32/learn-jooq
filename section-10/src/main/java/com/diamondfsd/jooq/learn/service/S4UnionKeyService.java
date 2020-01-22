package com.diamondfsd.jooq.learn.service;


import com.diamondfsd.jooq.learn.extend.AbstractGenericService;
import com.diamondfsd.jooq.learn.jooq.tables.daos.S4UnionKeyDao;
import com.diamondfsd.jooq.learn.pojos.S4UnionKey;

import org.springframework.stereotype.Service;


@Service
public class S4UnionKeyService extends AbstractGenericService<S4UnionKey, org.jooq.Record2<Integer, Integer>> {

    public S4UnionKeyService(S4UnionKeyDao dao) {
        super(dao);
    }
}