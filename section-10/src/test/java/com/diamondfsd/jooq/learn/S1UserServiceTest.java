package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.entity.S1User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Rollback
class S1UserServiceTest {
    @Autowired
    S1UserService userService;

    @Test
    public void findById() {
        List<S1User> s1Users =
                userService.listByIds(Arrays.asList(1, 2));
        assertEquals(2, s1Users.size());
    }
}
