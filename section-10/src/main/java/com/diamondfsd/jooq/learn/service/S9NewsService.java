package com.diamondfsd.jooq.learn.service;


import com.diamondfsd.jooq.learn.extend.AbstractGenericService;
import com.diamondfsd.jooq.learn.jooq.tables.daos.S9NewsDao;
import com.diamondfsd.jooq.learn.pojos.S9News;

import org.springframework.stereotype.Service;


@Service
public class S9NewsService extends AbstractGenericService<S9News, java.lang.Integer> {

    public S9NewsService(S9NewsDao dao) {
        super(dao);
    }
}