package com.diamondfsd.jooq.learn.service;

import com.diamondfsd.jooq.learn.extend.AbstractGenericService;
import com.diamondfsd.jooq.learn.jooq.tables.daos.S1UserDao;
import com.diamondfsd.jooq.learn.pojos.S1User;
import org.springframework.stereotype.Service;

/**
 * @author Diamond
 */
@Service
public class S1UserService extends AbstractGenericService<S1User, Integer> {

    public S1UserService(S1UserDao dao) {
        super(dao);
    }
}
