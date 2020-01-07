package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.tables.pojos.S1UserPojo;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.InvalidResultException;
import org.jooq.exception.NoDataFoundException;
import org.jooq.exception.TooManyRowsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.diamondfsd.jooq.learn.codegen.Tables.S2_USER_MESSAGE;
import static com.diamondfsd.jooq.learn.codegen.tables.TS1User.S1_USER;

public class fetchTest extends BaseTest {

    @Test
    public void fetchTest() {
        Record record = dslContext.select().from(S1_USER)
                .where(S1_USER.ID.eq(1)).fetchOne();
        Result<Record> records = dslContext.select().from(S1_USER)
                .where(S1_USER.ID.eq(1)).fetch();

        Assertions.assertEquals(1, record.get(S1_USER.ID));
        Assertions.assertEquals(1, records.size());

        S1UserPojo userPojo = dslContext.select()
                .from(S1_USER)
                .where(S1_USER.ID.eq(1))
                .fetchOne(r -> r.into(S1UserPojo.class));
        List<S1UserPojo> userPojoList = dslContext.select()
                .from(S1_USER)
                .where(S1_USER.ID.eq(1))
                .fetch(r -> r.into(S1UserPojo.class));

        Assertions.assertEquals(1, userPojo.getId());
        Assertions.assertEquals(1, userPojoList.size());

        // 多表关联查询，查询s2_user_message.id = 2的数据，直接into的结果getId()却是1
        // 这是因为同时关联查询了s1_user表，该表的id字段值为1
        S2UserMessage userMessage = dslContext.select().from(S2_USER_MESSAGE)
                .leftJoin(S1_USER).on(S1_USER.ID.eq(S2_USER_MESSAGE.USER_ID))
                .where(S2_USER_MESSAGE.ID.eq(2))
                .fetchOne(r -> r.into(S2UserMessage.class));
        Assertions.assertEquals(1, userMessage.getId());

        // 处理字段名称重复
        S2UserMessage userMessage2 = dslContext.select().from(S2_USER_MESSAGE)
                .leftJoin(S1_USER).on(S1_USER.ID.eq(S2_USER_MESSAGE.USER_ID))
                .where(S2_USER_MESSAGE.ID.eq(2))
                .fetchOne(r -> {
                    S2UserMessage fetchUserMessage = r.into(S2_USER_MESSAGE).into(S2UserMessage.class);
                    fetchUserMessage.setUsername(r.get(S1_USER.USERNAME));
                    return fetchUserMessage;
                });
        Assertions.assertEquals(2, userMessage2.getId());


        Integer id = dslContext.select().from(S1_USER).where(S1_USER.ID.eq(1))
                .fetchOne(S1_USER.ID);

        Assertions.assertEquals(1, id);
        List<Integer> idList = dslContext.select().from(S1_USER).where(S1_USER.ID.eq(1))
                .fetch(S1_USER.ID);
        Assertions.assertEquals(1, idList.size());
        Assertions.assertEquals(1, idList.get(0));

    }

    @Test
    public void fetchObject() {
        Integer id = dslContext.select().from(S1_USER).where(S1_USER.ID.eq(1))
                .fetchOne("id", Integer.class);
        Assertions.assertEquals(1, id);
        List<Integer> idList = dslContext.select().from(S1_USER).where(S1_USER.ID.eq(1))
                .fetch("id", Integer.class);
        Assertions.assertEquals(1, idList.size());
        Assertions.assertEquals(1, idList.get(0));
    }

    @Test
    public void fetchObjectIndex() {
        Object id = dslContext.select(S1_USER.ID, S1_USER.USERNAME).from(S1_USER)
                .where(S1_USER.ID.eq(1)).fetchOne(0, Integer.class);
        Assertions.assertEquals(1, id);
        List<Object> idList = dslContext.select(S1_USER.ID, S1_USER.USERNAME)
                .from(S1_USER).where(S1_USER.ID.eq(1)).fetch(0, Integer.class);
        Assertions.assertEquals(1, idList.size());
        Assertions.assertEquals(1, idList.get(0));
    }

    @Test
    public void fetchGroupsTest() {
        Map<Integer, List<S2UserMessage>> userIdUserMessageMap = dslContext.select().from(S2_USER_MESSAGE)
                .fetchGroups(S2_USER_MESSAGE.USER_ID, S2UserMessage.class);
        Assertions.assertTrue(userIdUserMessageMap.containsKey(1));
        Assertions.assertTrue(userIdUserMessageMap.containsKey(2));

        Map<Integer, List<Integer>> userIdUserMessageIdMap = dslContext.select().from(S2_USER_MESSAGE)
                .fetchGroups(S2_USER_MESSAGE.USER_ID, S2_USER_MESSAGE.ID);
        Assertions.assertTrue(userIdUserMessageIdMap.containsKey(1));
        Assertions.assertTrue(userIdUserMessageIdMap.containsKey(2));
    }

    @Test
    public void fetchMapTest() {
        Map<Integer, S1UserPojo> idUserPojoMap = dslContext.select().from(S1_USER)
                .fetchMap(S1_USER.ID, S1UserPojo.class);

        Assertions.assertTrue(idUserPojoMap.containsKey(1));
        Assertions.assertTrue(idUserPojoMap.containsKey(2));

        Map<Integer, String> idUserNameMap = dslContext.select().from(S1_USER)
                .fetchMap(S1_USER.ID, S1_USER.USERNAME);
        Assertions.assertTrue(idUserNameMap.containsKey(1));
        Assertions.assertTrue(idUserNameMap.containsKey(2));

        try {
            Map<Integer, S2UserMessage> integerS2UserMessageMap = dslContext.select().from(S2_USER_MESSAGE)
                    .fetchMap(S2_USER_MESSAGE.USER_ID, S2UserMessage.class);
            Assertions.fail("fetch map key must be unique");
        } catch (InvalidResultException e) {
            log.info("fetch map key is not unique in result", e);
        }
    }

    @Test
    public void fetchSingleTest() {
        Record singleRecord = dslContext.select().from(S1_USER).where(S1_USER.ID.eq(1))
                .fetchSingle();
        Assertions.assertEquals(1, singleRecord.get(S1_USER.ID));
        try {
            dslContext.select().from(S1_USER).where(S1_USER.ID.eq(0))
                    .fetchSingle();
            Assertions.fail("无数据时，fetchSingle会报错");
        } catch (NoDataFoundException e) {
            log.info("no data fund exception", e);
        }

        try {
            dslContext.select().from(S1_USER)
                    .fetchSingle();
            Assertions.fail("数据超过一条时，fetchSingle会报错");
        } catch (TooManyRowsException e) {
            log.info("too many rows exception", e);
        }
    }

    @Test
    public void fetchAnyTest() {
        Record record = dslContext.select().from(S1_USER)
                .fetchAny();
        Assertions.assertNotNull(record);

        record = dslContext.select().from(S1_USER).where(S1_USER.ID.eq(0))
                .fetchAny();
        Assertions.assertNull(record);
    }
}
