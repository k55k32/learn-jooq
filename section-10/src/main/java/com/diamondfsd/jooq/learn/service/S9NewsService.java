package com.diamondfsd.jooq.learn.service;


import com.diamondfsd.jooq.learn.extend.GenericServiceImpl;
import com.diamondfsd.jooq.learn.jooq.tables.daos.S9NewsDao;
import com.diamondfsd.jooq.learn.entity.S9News;

import org.springframework.stereotype.Service;


@Service
public class S9NewsService extends GenericServiceImpl<S9News, Integer> {

    public S9NewsService(S9NewsDao dao) {
        super(dao);
    }
}
