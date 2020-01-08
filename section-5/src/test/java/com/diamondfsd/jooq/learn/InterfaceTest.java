package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.tables.pojos.S1UserPojo;
import com.diamondfsd.jooq.learn.codegen.tables.records.S1UserRecord;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;

public class InterfaceTest extends BaseTest {

    @Test
    public void timeTest() {
        Arrays.asList(100, 1000, 10000, 100000, 1000000)
                .forEach(times -> {
                    log.info("begin test");
                    interfaceTest(times);
                    testNoInterfaceTime(times);
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
        S1UserRecord s1UserRecord = new S1UserRecord();
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
        S1UserRecord s1UserRecord = new S1UserRecord();
        start();
        for (int i = 0; i < times; i++) {
            s1UserRecord.from(s1UserPojoNoInterface);
        }
        endAndPrintTime();
    }
}
