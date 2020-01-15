package com.diamondfsd.jooq.learn;

import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitConfig(Section6Main.class)
@Transactional
public class BaseTest {
    @Autowired
    DSLContext dslContext;
}
