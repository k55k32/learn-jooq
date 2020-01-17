package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.learn_jooq.tables.daos.S1UserDao;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitConfig(Section7Main.class)
@Rollback
public class BaseTest {
}
