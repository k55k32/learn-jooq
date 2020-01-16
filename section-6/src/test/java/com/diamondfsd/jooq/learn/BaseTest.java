package com.diamondfsd.jooq.learn;

import org.jooq.DSLContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitConfig(Section6Main.class)
@Transactional
@Rollback
public class BaseTest {
    @Autowired
    private DSLContext dslContext;

    @Test
    public void  dslContextNotNull() {
        Assertions.assertNotNull(dslContext);
    }
}
