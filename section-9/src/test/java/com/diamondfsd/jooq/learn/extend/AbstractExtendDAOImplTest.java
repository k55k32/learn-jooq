package com.diamondfsd.jooq.learn.extend;

import com.diamondfsd.jooq.learn.codegen.tables.daos.S1UserDao;
import com.diamondfsd.jooq.learn.codegen.tables.daos.S9NewsDao;
import com.diamondfsd.jooq.learn.codegen.tables.pojos.S1UserPojo;
import com.diamondfsd.jooq.learn.codegen.tables.pojos.S9NewsPojo;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.diamondfsd.jooq.learn.codegen.tables.TS1User.S1_USER;
import static com.diamondfsd.jooq.learn.codegen.tables.TS9News.S9_NEWS;

@SpringBootTest
@Transactional
@Rollback
class AbstractExtendDAOImplTest {

    @Autowired
    S1UserDao s1UserDao;

    @Autowired
    S9NewsDao newsDao;

    @Test
    void create() {
        DSLContext dslContext = s1UserDao.create();
        Assertions.assertNotNull(dslContext);
        Integer result = dslContext.selectOne().fetchOneInto(Integer.class);
        Assertions.assertEquals(1, result);
    }

    @Test
    void fetchOne() {
        S1UserPojo s1UserPojo = s1UserDao.fetchOne(S1_USER.USERNAME.eq("demo1"));
        Assertions.assertNotNull(s1UserPojo);
    }

    @Test
    void fetchOneOptional() {
        Optional<S1UserPojo> s1UserPojo =
                s1UserDao.fetchOneOptional(S1_USER.ID.eq(0));
        Assertions.assertFalse(s1UserPojo.isPresent());
    }

    @Test
    void fetch() {
        List<S1UserPojo> fetch = s1UserDao.fetch(DSL.noCondition(), S1_USER.ID.desc());
        Assertions.assertTrue(fetch.size() >= 2);
        Assertions.assertTrue(fetch.get(0).getId() > fetch.get(1).getId());
    }

    @Test
    void fetchPage() {
        PageResult<S9NewsPojo> pageQuery = new PageResult<>(1, 10);
        PageResult<S9NewsPojo> pageResult = newsDao.fetchPage(pageQuery, DSL.noCondition(), S9_NEWS.ID.desc());
        Assertions.assertTrue(pageResult.getTotal() > 10);
        List<S9NewsPojo> pageData = pageResult.getData();
        Assertions.assertNotNull(pageData);
        Assertions.assertEquals(10, pageData.size());
        Assertions.assertEquals(1, pageResult.getCurrentPage());
        for (int i = 0; i < pageData.size() - 1; i++) {
            S9NewsPojo curr = pageData.get(i);
            S9NewsPojo next = pageData.get(i + 1);
            Assertions.assertTrue(curr.getId() > next.getId());
        }

        PageResult<S9NewsPojo> s9NewsPojoPageResult = newsDao.fetchPage(pageQuery,
                newsDao.create().select(S9_NEWS.TITLE, S9_NEWS.CONTENT)
                        .from(S9_NEWS)
                        .where(S9_NEWS.ID.le(5))
                        .orderBy(S9_NEWS.ID.desc()), S9NewsPojo.class);

        Assertions.assertEquals(5, s9NewsPojoPageResult.getTotal());
        Assertions.assertEquals(5, s9NewsPojoPageResult.getData().size());
        Assertions.assertEquals(10, s9NewsPojoPageResult.getPageSize());
        Assertions.assertEquals(1, s9NewsPojoPageResult.getCurrentPage());

        PageResult<S9NewsPojo> sizeZeroResult = newsDao.fetchPage(new PageResult<>(1, 0), DSL.noCondition());
        Assertions.assertNotNull(sizeZeroResult.getData());
        Assertions.assertTrue(sizeZeroResult.getData().isEmpty());
        Assertions.assertEquals(0, sizeZeroResult.getTotal());
        Assertions.assertEquals(0, sizeZeroResult.getPageSize());
        Assertions.assertEquals(0, sizeZeroResult.getCurrentPage());

    }
}
