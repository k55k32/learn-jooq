package com.diamondfsd.jooq.learn.entity;

import com.diamondfsd.jooq.learn.jooq.tables.daos.S2UserMessageDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.diamondfsd.jooq.learn.jooq.Tables.S1_USER;
import static com.diamondfsd.jooq.learn.jooq.Tables.S2_USER_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@Rollback
class S2UserMessageTest {

    @Autowired
    S2UserMessageDao userMessageDao;

    @Test
    void leftJoinUsernameTest() {
        List<S2UserMessage> userMessageList = userMessageDao.create()
                .select(S2_USER_MESSAGE.ID, S2_USER_MESSAGE.MESSAGE_TITLE, S1_USER.USERNAME)
                .from(S2_USER_MESSAGE)
                .leftJoin(S1_USER).on(S1_USER.ID.eq(S2_USER_MESSAGE.USER_ID))
                .fetchInto(S2UserMessage.class);

        assertTrue(userMessageList.size() > 0);
        List<String> usernameList = userMessageList.stream()
                .map(S2UserMessage::getUsername)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        assertEquals(userMessageList.size(), usernameList.size());
    }

}
