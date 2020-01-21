package com.diamondfsd.jooq.learn.extend;

import com.diamondfsd.jooq.learn.jooq.tables.daos.S1UserDao;
import com.diamondfsd.jooq.learn.jooq.tables.daos.S9NewsDao;
import com.diamondfsd.jooq.learn.pojos.S1User;
import com.diamondfsd.jooq.learn.pojos.S9News;
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

import static com.diamondfsd.jooq.learn.jooq.tables.TS1User.S1_USER;
import static com.diamondfsd.jooq.learn.jooq.tables.TS9News.S9_NEWS;

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
        S1User s1User = s1UserDao.fetchOne(S1_USER.USERNAME.eq("demo1"));
        Assertions.assertNotNull(s1User);
    }

    @Test
    void fetchOneOptional() {
        Optional<S1User> s1User =
                s1UserDao.fetchOneOptional(S1_USER.ID.eq(0));
        Assertions.assertFalse(s1User.isPresent());
    }

    @Test
    void fetch() {
        List<S1User> fetch = s1UserDao.fetch(DSL.noCondition(), S1_USER.ID.desc());
        Assertions.assertTrue(fetch.size() >= 2);
        Assertions.assertTrue(fetch.get(0).getId() > fetch.get(1).getId());
    }

    @Test
    void fetchPage() {
        PageResult<S9News> pageQuery = new PageResult<>(1, 10);
        PageResult<S9News> pageResult = newsDao.fetchPage(pageQuery, DSL.noCondition(), S9_NEWS.ID.desc());
        Assertions.assertTrue(pageResult.getTotal() > 10);
        List<S9News> pageData = pageResult.getData();
        Assertions.assertNotNull(pageData);
        Assertions.assertEquals(10, pageData.size());
        Assertions.assertEquals(1, pageResult.getCurrentPage());
        for (int i = 0; i < pageData.size() - 1; i++) {
            S9News curr = pageData.get(i);
            S9News next = pageData.get(i + 1);
            Assertions.assertTrue(curr.getId() > next.getId());
        }

        PageResult<S9News> s9NewsPageResult = newsDao.fetchPage(pageQuery,
                newsDao.create().select(S9_NEWS.TITLE, S9_NEWS.CONTENT)
                        .from(S9_NEWS)
                        .where(S9_NEWS.ID.le(5))
                        .orderBy(S9_NEWS.ID.desc()), S9News.class);

        Assertions.assertEquals(5, s9NewsPageResult.getTotal());
        Assertions.assertEquals(5, s9NewsPageResult.getData().size());
        Assertions.assertEquals(10, s9NewsPageResult.getPageSize());
        Assertions.assertEquals(1, s9NewsPageResult.getCurrentPage());

        PageResult<S9News> sizeZeroResult = newsDao.fetchPage(new PageResult<>(1, 0), DSL.noCondition());
        Assertions.assertNotNull(sizeZeroResult.getData());
        Assertions.assertTrue(sizeZeroResult.getData().isEmpty());
        Assertions.assertEquals(0, sizeZeroResult.getTotal());
        Assertions.assertEquals(0, sizeZeroResult.getPageSize());
        Assertions.assertEquals(0, sizeZeroResult.getCurrentPage());

    }
}
