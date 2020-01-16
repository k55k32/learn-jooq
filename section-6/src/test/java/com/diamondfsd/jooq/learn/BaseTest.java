package com.diamondfsd.jooq.learn;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitConfig(Section6Main.class)
@Transactional
public class BaseTest {
    @Autowired
    DSLContext dslContext;
}
