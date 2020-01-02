package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.tables.records.S1UserRecord;
import org.jooq.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.diamondfsd.jooq.learn.codegen.Tables.S1_USER;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserUpdateTest extends BaseTest {
    /**
     * 插入操作
     */
    @Test
    public void updateTest() {
        // 使用DSLContext的API进行更新操作
        int updateRows = dslContext.update(S1_USER)
                .set(S1_USER.USERNAME, "apiUsername-1")
                .where(S1_USER.ID.eq(1))
                .execute();
        Assertions.assertEquals(1, updateRows);

        // 使用Record对象进行更新操作，默认是根据主键作为条件
        S1UserRecord record = dslContext.newRecord(S1_USER);
        record.setId(1);
        record.setUsername("usernameUpdate-2");
        record.setAddress("record-address-2");
        updateRows = record.update();
        Assertions.assertEquals(1, updateRows);
    }

    @Test
    public void batchUpdate() {
        // 批量更新
        S1UserRecord record1 = new S1UserRecord();
        record1.setId(1);
        record1.setUsername("batchUsername-1");
        S1UserRecord record2 = new S1UserRecord();
        record2.setId(2);
        record2.setUsername("batchUsername-2");

        List<S1UserRecord> userRecordList = new ArrayList<>();
        userRecordList.add(record1);
        userRecordList.add(record2);
        int[] execute = dslContext.batchUpdate(userRecordList).execute();
        Assertions.assertArrayEquals(new int[]{1, 1}, execute);
    }

    /**
     * 主键为空时使用Record进行更新操作
     */
    @Test
    public void updateNoId() {
        S1UserRecord record2 = dslContext.newRecord(S1_USER);
        record2.setUsername("usernameUpdate-noID");
        int affectedRows = record2.update();
        Assertions.assertEquals(0, affectedRows);
        String username = dslContext.select().from(S1_USER).where(S1_USER.ID.eq(1))
                .fetchOneInto(S1UserRecord.class)
                .getUsername();
        Assertions.assertNotEquals("usernameUpdate-noID", username);
    }
}
