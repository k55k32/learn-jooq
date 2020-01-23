package com.diamondfsd.jooq.learn.service;


import com.diamondfsd.jooq.learn.extend.GenericServiceImpl;
import com.diamondfsd.jooq.learn.jooq.tables.daos.S2UserMessageDao;
import com.diamondfsd.jooq.learn.entity.S2UserMessage;

import org.springframework.stereotype.Service;


@Service
public class S2UserMessageService extends GenericServiceImpl<S2UserMessage, Integer> {

    public S2UserMessageService(S2UserMessageDao dao) {
        super(dao);
    }
}
