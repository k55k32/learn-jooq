package com.diamondfsd.jooq.learn.service;


import com.diamondfsd.jooq.learn.extend.AbstractGenericService;
import com.diamondfsd.jooq.learn.jooq.tables.daos.S2UserMessageDao;
import com.diamondfsd.jooq.learn.pojos.S2UserMessage;

import org.springframework.stereotype.Service;


@Service
public class S2UserMessageService extends AbstractGenericService<S2UserMessage, java.lang.Integer> {

    public S2UserMessageService(S2UserMessageDao dao) {
        super(dao);
    }
}