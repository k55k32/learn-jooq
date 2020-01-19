package com.diamondfsd.jooq.learn.codegen.learn_jooq_2.tables.daos;

import com.diamondfsd.jooq.learn.BaseTest;
import com.diamondfsd.jooq.learn.codegen.learn_jooq_2.tables.pojos.S7UserPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static com.diamondfsd.jooq.learn.config.DataSourceConfig.TX_LEARN_JOOQ_2;

@Transactional(TX_LEARN_JOOQ_2)
class S7UserDaoTest extends BaseTest {
    @Autowired
    S7UserDao s7UserDao;

    @Test
    @Transactional(TX_LEARN_JOOQ_2)
    public void insert() {
        S7UserPojo s7UserPojo = new S7UserPojo();
        s7UserPojo.setUsername("s7username");
        s7UserDao.insert(s7UserPojo);
        Assertions.assertNotNull(s7UserPojo.getId());
    }

}
