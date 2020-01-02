package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.Keys;
import com.diamondfsd.jooq.learn.codegen.tables.records.S1UserRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.diamondfsd.jooq.learn.codegen.Tables.S1_USER;
import static com.diamondfsd.jooq.learn.codegen.Tables.S2_USER_MESSAGE;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDeleteTest extends BaseTest {
    /**
     * 删除操作
     */
    @Test
    public void deleteTest() throws SQLException {
        // 删除操作
        int deleteRows = dslContext.delete(S1_USER)
                .where(S1_USER.USERNAME.eq("demo1")).execute();
        Assertions.assertEquals(1, deleteRows);

        deleteRows = dslContext.delete(S1_USER)
                .where(S1_USER.ID.in(2))
                .execute();
        Assertions.assertEquals(1, deleteRows);
    }

    /**
     * 通过 Record API 进行删除
     */
    @Test
    public void deleteRecord() {
        S1UserRecord record = dslContext.newRecord(S1_USER);
        record.setId(2);
        int deleteRows = record.delete();
        Assertions.assertEquals(1, deleteRows);

        S1UserRecord record2 = dslContext.newRecord(S1_USER);
        record2.setUsername("demo1");
        deleteRows = record2.delete();
        Assertions.assertEquals(0, deleteRows);
    }

    @Test
    public void batchDelete() {
        // 批量删除
        S1UserRecord record1 = new S1UserRecord();
        record1.setId(1);
        S1UserRecord record2 = new S1UserRecord();
        record2.setId(2);
        int[] deleteRowResult = dslContext.batchDelete(record1, record2).execute();
        Assertions.assertArrayEquals(new int[]{1, 1}, deleteRowResult);
    }
}
