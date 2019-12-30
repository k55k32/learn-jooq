package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.tables.records.S1UserRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.diamondfsd.jooq.learn.codegen.Tables.S1_USER;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDeleteTest extends BaseTest {
    /**
     * 删除操作
     */
    @Test
    public void deleteTest() throws SQLException {
        // 删除操作
        int deleteRow2 = dslContext.delete(S1_USER)
                .where(S1_USER.ID.eq(1)).execute();
        Assertions.assertEquals(1, deleteRow2);

        deleteRow2 = dslContext.delete(S1_USER)
                .where(S1_USER.ID.in(2))
                .execute();
        Assertions.assertEquals(1, deleteRow2);
        rollbackTransaction();

        // 批量删除
        S1UserRecord record1 = new S1UserRecord();
        record1.setId(1);
        S1UserRecord record2 = new S1UserRecord();
        record2.setId(2);
        int[] deleteRowResult = dslContext.batchDelete(record1, record2).execute();
        Assertions.assertArrayEquals(new int[]{1, 1}, deleteRowResult);
    }
}
