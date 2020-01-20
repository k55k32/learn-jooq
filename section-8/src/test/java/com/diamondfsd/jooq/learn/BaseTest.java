package com.diamondfsd.jooq.learn;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Section8Main.class)
@Transactional
@Rollback
public class BaseTest {
    @Autowired
    DSLContext dslContext;

    @Autowired
    Configuration configuration;

    @Autowired
    TransactionManager transactionManager;

    @Test
    public void empty() {
        Assertions.assertNotNull(dslContext);
        Assertions.assertNotNull(configuration);
        Assertions.assertNotNull(transactionManager);
    }
}
