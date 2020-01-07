package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.tables.TS4NoPrimary;
import com.diamondfsd.jooq.learn.codegen.tables.records.S1UserRecord;
import com.diamondfsd.jooq.learn.codegen.tables.records.S4NoPrimaryRecord;
import org.jooq.Record;
import org.jooq.Record3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static com.diamondfsd.jooq.learn.codegen.Tables.S4_NO_PRIMARY;
import static com.diamondfsd.jooq.learn.codegen.tables.TS1User.S1_USER;

public class FetchRecordTest extends BaseTest {

    @Test
    public void newRecordTest() {
        S1UserRecord s1UserRecord = dslContext.newRecord(S1_USER);
        s1UserRecord.setUsername("newRecord1");
        s1UserRecord.setEmail("diamondfsd@gmail.com");
        s1UserRecord.insert();
        // insert过后会自动返回自增主键
        Assertions.assertNotNull(s1UserRecord.getId());
    }

    @Test
    public void newTest() {
        S1UserRecord s1UserRecord = new S1UserRecord();
        s1UserRecord.setUsername("new1");
        s1UserRecord.setEmail("diamondfsd@gmail.com");
        try {
            s1UserRecord.insert();
            Assertions.fail("没有Configuration，无法直接insert");
        } catch (Exception e) {
            log.info("no configuration ", e);
        }
        int row = dslContext.insertInto(S1_USER).set(s1UserRecord)
                .execute();
        Assertions.assertEquals(1, row);

        Integer id = dslContext.insertInto(S1_USER).set(s1UserRecord)
                .returning(S1_USER.ID)
                .fetchOne().getId();
        Assertions.assertNotNull(id);
    }

    @Test
    public void fetchRecordTest() {
        S1UserRecord s1UserRecord = dslContext.selectFrom(S1_USER).where(S1_USER.ID.eq(1))
                .fetchOne();
        s1UserRecord.setEmail("hello email");
        int row = s1UserRecord.update();
        Assertions.assertEquals(1, row);

        S1UserRecord fetchIntoUserRecord = dslContext.select().from(S1_USER)
                .where(S1_USER.ID.eq(1))
                .fetchOneInto(S1UserRecord.class);
        fetchIntoUserRecord.setEmail("hello email2");
        int row2 = fetchIntoUserRecord.update();
        Assertions.assertEquals(1, row2);

        S1UserRecord s1UserRecord1 = new S1UserRecord();
        s1UserRecord1.from(s1UserRecord);
        Assertions.assertFalse(s1UserRecord1 == s1UserRecord);
    }
}
