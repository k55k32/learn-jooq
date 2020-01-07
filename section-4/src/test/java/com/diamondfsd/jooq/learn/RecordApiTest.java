package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.tables.pojos.S1UserPojo;
import com.diamondfsd.jooq.learn.codegen.tables.records.S1UserRecord;
import com.diamondfsd.jooq.learn.codegen.tables.records.S4NoPrimaryRecord;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Record3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.diamondfsd.jooq.learn.codegen.Tables.S4_NO_PRIMARY;
import static com.diamondfsd.jooq.learn.codegen.tables.TS1User.S1_USER;

public class RecordApiTest extends BaseTest{
    @Test
    public void insertApiTest() {
        S1UserRecord userRecord = dslContext.newRecord(S1_USER);
        userRecord.setUsername("insertUsername");
        userRecord.setEmail("email");
        userRecord.insert();
        Assertions.assertNotNull(userRecord.getId());

        userRecord = getUserRecordById(userRecord.getId());
        Assertions.assertNotNull(userRecord.getEmail());
        Assertions.assertNotNull(userRecord.getUsername());
        Assertions.assertNotNull(userRecord.getId());

        userRecord = dslContext.newRecord(S1_USER);
        userRecord.setUsername("insertUsername");
        userRecord.setEmail("email");
        userRecord.insert(S1_USER.USERNAME, S1_USER.ADDRESS);
        Assertions.assertNotNull(userRecord.getId());

        S1UserRecord userRecord2 = getUserRecordById(userRecord.getId());
        Assertions.assertNull(userRecord2.getEmail());

        S4NoPrimaryRecord s4NoPrimaryRecord = dslContext.newRecord(S4_NO_PRIMARY);
        s4NoPrimaryRecord.setColumn1("1");
        s4NoPrimaryRecord.setColumn2("2");
        int row = s4NoPrimaryRecord.insert();
        Assertions.assertEquals(1, row);

    }

    private S1UserRecord getUserRecordById(Integer id) {
        return dslContext.selectFrom(S1_USER)
                .where(S1_USER.ID.eq(id))
                .fetchOne();
    }

    @Test
    public void updateApiTest() {
        S1UserRecord userRecord = getUserRecordById(1);
        Assertions.assertNotNull(userRecord.getEmail());
        userRecord.setEmail(null);
        userRecord.setUsername("hello");
        userRecord.update();
        userRecord = getUserRecordById(1);
        Assertions.assertNull(userRecord.getEmail());

        userRecord = getUserRecordById(2);
        Assertions.assertNotNull(userRecord.getEmail());
        userRecord.setEmail(null);
        userRecord.setUsername("hello");
        userRecord.update(S1_USER.USERNAME, S1_USER.ADDRESS);
        userRecord = getUserRecordById(2);
        Assertions.assertNotNull(userRecord.getEmail());

    }

    @Test
    public void deleteApiTest() {
        S1UserRecord userRecord = dslContext.newRecord(S1_USER);
        userRecord.delete();

        userRecord = dslContext.newRecord(S1_USER);
        userRecord.setId(1);
        userRecord.delete();

        S1UserRecord userRecordById = getUserRecordById(1);
        Assertions.assertNull(userRecordById);
    }

    @Test
    public void getApiTest() {
        Record record = dslContext.select().from(S1_USER).limit(1).fetchOne();
        Integer id = record.get(S1_USER.ID);
        Timestamp createTime = record.get(S1_USER.CREATE_TIME);
        Long createTimeLong = record.get(S1_USER.CREATE_TIME, Long.class);
        Assertions.assertTrue(id > 0);
        Assertions.assertTrue(createTimeLong > 0);
        Assertions.assertEquals(createTimeLong, createTime.getTime());
    }

    @Test
    public void getFieldNameApiTest() {
        S1UserRecord userRecord = dslContext.select().from(S1_USER)
                .fetchAny().into(S1_USER);
        Integer id = userRecord.getId();
        Timestamp createTime = userRecord.getCreateTime();
        Assertions.assertNotNull(id);
        Assertions.assertNotNull(createTime);
    }

    @Test
    public void valueTest() {
        Record3<Integer, String, Timestamp> record3 = dslContext
                .select(S1_USER.ID,
                        S1_USER.USERNAME,
                        S1_USER.CREATE_TIME)
                .from(S1_USER)
                .fetchAny();

        Integer id = record3.value1();
        String username = record3.value2();
        Timestamp createTime = record3.get(S1_USER.CREATE_TIME);

        Assertions.assertNotNull(id);
        Assertions.assertNotNull(username);
        Assertions.assertNotNull(createTime);
    }

    @Test
    public void setTest() {
        S1UserRecord userRecord = dslContext.newRecord(S1_USER);
        userRecord.set(S1_USER.ID, 1);
        userRecord.set(S1_USER.USERNAME, "username");
        userRecord.update();
        S1UserRecord userRecordById = getUserRecordById(1);
        Assertions.assertEquals("username", userRecordById.getUsername());
    }

    @Test
    public void setFieldTest() {
        S1UserRecord userRecord = dslContext.newRecord(S1_USER);
        userRecord.setId(1);
        userRecord.setUsername("username");
        userRecord.update();
        S1UserRecord userRecordById = getUserRecordById(1);
        Assertions.assertEquals("username", userRecordById.getUsername());
    }

    @Test
    public void changedTest() {
        S1UserRecord userRecord = dslContext.newRecord(S1_USER);
        userRecord.setId(1);
        userRecord.setUsername("username");
        userRecord.setEmail(null);
        userRecord.changed(S1_USER.EMAIL, false);
        userRecord.update();

        S1UserRecord userRecordById = getUserRecordById(1);
        Assertions.assertNotNull(userRecordById.getEmail());
    }

    @Test
    public void resetTest() {
        S1UserRecord userRecord = dslContext.newRecord(S1_USER);
        userRecord.setId(1);
        userRecord.setUsername("username");
        userRecord.setEmail(null);
        userRecord.reset(S1_USER.EMAIL);
        userRecord.update();

        S1UserRecord userRecordById = getUserRecordById(1);
        Assertions.assertNotNull(userRecordById.getEmail());
    }

    @Test
    public void fromTest() {
        S1UserPojo userPojo = new S1UserPojo();
        userPojo.setUsername("username");
        userPojo.setAddress("address");

        S1UserRecord userRecord = dslContext.newRecord(S1_USER);
        userRecord.from(userPojo);
        Assertions.assertEquals("username", userRecord.getUsername());
        Assertions.assertEquals("address", userRecord.getAddress());

        Map<String, Object> userDataMap = new HashMap<>();
        userDataMap.put("username", "username1");
        userDataMap.put("address", "user-address-1");
        S1UserRecord userRecord1 = dslContext.newRecord(S1_USER);
        userRecord1.fromMap(userDataMap);
        Assertions.assertEquals("username1", userRecord1.getUsername());
        Assertions.assertEquals("user-address-1", userRecord1.getAddress());

        Object[] userDataArray = new Object[]{"username2", "user-address-2"};
        S1UserRecord userRecord2 = dslContext.newRecord(S1_USER);
        userRecord2.fromArray(userDataArray, S1_USER.USERNAME, S1_USER.ADDRESS);
        Assertions.assertEquals("username2", userRecord2.getUsername());
        Assertions.assertEquals("user-address-2", userRecord2.getAddress());
    }

    @Test
    public void intoTest() {
        S1UserRecord userRecord = getUserRecordById(1);
        S1UserPojo userPojo = userRecord.into(S1UserPojo.class);
        Assertions.assertEquals(1, userPojo.getId());

        Record2<Integer, String> record2 = userRecord.into(S1_USER.ID, S1_USER.USERNAME);
        Integer id = record2.value1();
        Assertions.assertEquals(1, id);

        Object[] userArray = userRecord.intoArray();
        Integer fromArrayId = (Integer) userArray[0];
        Assertions.assertEquals(1, fromArrayId);

        Map<String, Object> userMap = userRecord.intoMap();
        Integer mapId = (Integer) userMap.get("id");
        Assertions.assertEquals(1, mapId);

        ResultSet resultSet = userRecord.intoResultSet();
    }
}
