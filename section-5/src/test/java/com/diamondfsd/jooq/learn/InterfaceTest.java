package com.diamondfsd.jooq.learn;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.diamondfsd.jooq.learn.codegen.tables.pojos.S1UserPojo;
import com.diamondfsd.jooq.learn.codegen.tables.records.S1UserRecord;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;

import static com.diamondfsd.jooq.learn.codegen.tables.TS1User.S1_USER;

public class InterfaceTest extends BaseTest {

    @Test
    public void fromTest() {
        Arrays.asList(1, 1, 10, 100, 1000, 10000, 100000, 1000000)
                .forEach(times -> {
                    log.info("begin test");
                    interfaceTest(times);
                    testNoInterfaceTime(times);
                });
    }

    @Test
    public void intoTest() {
        Arrays.asList(1, 1, 10, 100, 1000, 10000, 100000, 1000000)
                .forEach(times -> {
                    log.info("begin test");
                    selectRecordTest(times);
                    selectS1UserRecordTest(times);
                });
    }

    public void interfaceTest(int times) {
        S1UserPojo s1UserPojo = new S1UserPojo();
        s1UserPojo.setId(1);
        s1UserPojo.setUsername("username");
        s1UserPojo.setAddress("address");
        s1UserPojo.setEmail("email");
        s1UserPojo.setCreateTime(Timestamp.from(Instant.now()));
        s1UserPojo.setUpdateTime(Timestamp.from(Instant.now()));
        S1UserRecord s1UserRecord = dslContext.newRecord(S1_USER);
        start();
        for (int i = 0; i < times; i++) {
            s1UserRecord.from(s1UserPojo);
        }
        endAndPrintTime();
    }

    public void testNoInterfaceTime(int times) {
        S1UserPojoNoInterface s1UserPojoNoInterface = new S1UserPojoNoInterface();
        s1UserPojoNoInterface.setId(1);
        s1UserPojoNoInterface.setUsername("username");
        s1UserPojoNoInterface.setAddress("address");
        s1UserPojoNoInterface.setEmail("email");
        s1UserPojoNoInterface.setCreateTime(Timestamp.from(Instant.now()));
        s1UserPojoNoInterface.setUpdateTime(Timestamp.from(Instant.now()));
        S1UserRecord s1UserRecord = dslContext.newRecord(S1_USER);
        start();
        for (int i = 0; i < times; i++) {
            s1UserRecord.from(s1UserPojoNoInterface);
        }
        endAndPrintTime();
    }

    public void selectRecordTest(int times) {
        S1UserRecord s1UserRecord = dslContext.newRecord(S1_USER);
        s1UserRecord.setId(1);
        s1UserRecord.setUsername("username");
        s1UserRecord.setAddress("address");
        s1UserRecord.setEmail("email");
        s1UserRecord.setCreateTime(Timestamp.from(Instant.now()));
        s1UserRecord.setUpdateTime(Timestamp.from(Instant.now()));
        S1UserPojo s1UserPojo = new S1UserPojo();

        start();
        for (int i = 0; i < times; i++) {
            s1UserRecord.into(s1UserPojo);
        }
        endAndPrintTime();
    }

    public void selectS1UserRecordTest(int times) {
        S1UserRecord s1UserRecord = dslContext.newRecord(S1_USER);
        s1UserRecord.setId(1);
        s1UserRecord.setUsername("username");
        s1UserRecord.setAddress("address");
        s1UserRecord.setEmail("email");
        s1UserRecord.setCreateTime(Timestamp.from(Instant.now()));
        s1UserRecord.setUpdateTime(Timestamp.from(Instant.now()));
        S1UserPojoNoInterface s1UserPojo = new S1UserPojoNoInterface();

        start();
        for (int i = 0; i < times; i++) {
            s1UserRecord.into(s1UserPojo);
        }
        endAndPrintTime();
    }
}
