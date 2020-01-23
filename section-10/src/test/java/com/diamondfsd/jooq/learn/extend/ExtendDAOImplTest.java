package com.diamondfsd.jooq.learn.extend;

import com.diamondfsd.jooq.learn.jooq.tables.daos.S1UserDao;
import com.diamondfsd.jooq.learn.jooq.tables.daos.S4UnionKeyDao;
import com.diamondfsd.jooq.learn.jooq.tables.daos.S9NewsDao;
import com.diamondfsd.jooq.learn.entity.S1User;
import com.diamondfsd.jooq.learn.entity.S4UnionKey;
import com.diamondfsd.jooq.learn.entity.S9News;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.diamondfsd.jooq.learn.jooq.tables.TS1User.S1_USER;
import static com.diamondfsd.jooq.learn.jooq.tables.TS9News.S9_NEWS;

@SpringBootTest
@Transactional
@Rollback
class ExtendDAOImplTest {

    @Autowired
    S1UserDao userDao;

    @Autowired
    S9NewsDao newsDao;

    @Autowired
    S4UnionKeyDao unionKeyDao;

    @Test
    void create() {
        DSLContext dslContext = userDao.create();
        Assertions.assertNotNull(dslContext);
        Integer result = dslContext.selectOne().fetchOneInto(Integer.class);
        Assertions.assertEquals(1, result);
    }

    @Test
    void fetchOne() {
        S1User s1User = userDao.fetchOne(S1_USER.USERNAME.eq("demo1"));
        Assertions.assertNotNull(s1User);
    }

    @Test
    void fetchOneOptional() {
        Optional<S1User> s1User =
                userDao.fetchOneOptional(S1_USER.ID.eq(0));
        Assertions.assertFalse(s1User.isPresent());
    }

    @Test
    void fetch() {
        List<S1User> fetch = userDao.fetch(DSL.noCondition(), S1_USER.ID.desc());
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

    @Test
    void update() {
        S1User user = userDao.findById(1);
        Assertions.assertNotNull(user.getEmail());
        S1User user1 = new S1User();
        user1.setId(1);
        user1.setUsername("username-test");
        userDao.update(user1);
        S1User afterUpdate = userDao.findById(1);
        Assertions.assertNull(afterUpdate.getEmail());
    }

    @Test
    void updateBatch() {
        S1User user1 = new S1User();
        user1.setId(1);
        user1.setUsername("username-test");

        S1User user2 = new S1User();
        user2.setId(2);
        user2.setUsername("hello 2");
        userDao.update(user1, user2);

        List<S1User> s1Users = userDao.fetchById(1, 2);
        Assertions.assertNull(s1Users.get(0).getEmail());
        Assertions.assertNull(s1Users.get(1).getEmail());
    }

    @Test
    void updateSelective() {
        S1User user = userDao.findById(1);
        Assertions.assertNotNull(user.getEmail());
        S1User user1 = new S1User();
        user1.setId(1);
        user1.setUsername("username-test");
        userDao.updateSelective(user1);
        S1User afterUpdate = userDao.findById(1);
        Assertions.assertNotNull(afterUpdate.getEmail());

        user1.setUsername("user1-test");

        S1User user2 = new S1User();
        user2.setId(2);
        user2.setUsername("user2-test");
        userDao.updateSelective(user1, user2);

        List<S1User> users = userDao.fetchById(1, 2);
        Assertions.assertEquals(2, users.size());
        Assertions.assertEquals("user1-test", users.get(0).getUsername());
        Assertions.assertEquals("user2-test", users.get(1).getUsername());
        Assertions.assertNotNull(users.get(0).getEmail());
        Assertions.assertNotNull(users.get(1).getEmail());
    }

    @Test
    void insert() {
        S1User s1User = new S1User();
        s1User.setUsername("hello");
        s1User.setAddress("222");
        userDao.insert(s1User);
        Assertions.assertNotNull(s1User.getId());
        S1User storeUser = userDao.findById(s1User.getId());
        Assertions.assertNull(storeUser.getCreateTime());

        S1User batchUser1 = new S1User();
        batchUser1.setUsername("batch-user-1");
        S1User batchUser2 = new S1User();
        batchUser2.setUsername("batch-user-2");
        userDao.insert(batchUser1, batchUser2);
    }

    @Test
    void insertSelective() {
        S1User user = new S1User();
        user.setUsername("username-test");
        userDao.insertSelective(user);
        Assertions.assertNotNull(user.getId());
    }

    @Test
    void insertSelectiveBatch() {
        S1User user = new S1User();
        user.setUsername("username-test");
        userDao.insertSelective(user);
        Assertions.assertNotNull(user.getId());

        S1User user1 = new S1User();
        user1.setUsername("hello user1");
        S1User user2 = new S1User();
        user2.setUsername("hello user2");
        user2.setEmail("email2");
        userDao.insertSelective(user1, user2);
        List<S1User> createUser = userDao.fetch(S1_USER.ID.gt(user.getId()));
        Assertions.assertEquals(2, createUser.size());
        Assertions.assertEquals("hello user1", createUser.get(0).getUsername());
        Assertions.assertEquals("hello user2", createUser.get(1).getUsername());
        Assertions.assertEquals("email2", createUser.get(1).getEmail());
    }

    @Test
    void fetchById() {
        List<S1User> s1Users = userDao.fetchById(Arrays.asList(1, 2));
        Assertions.assertEquals(2, s1Users.size());
        S4UnionKey uk1 = new S4UnionKey();
        uk1.setUk_1(1);
        uk1.setUk_2(1);
        S4UnionKey uk2 = new S4UnionKey();
        uk2.setUk_1(1);
        uk2.setUk_2(2);
        /**
         * 联合主键查询
         */
        List<S4UnionKey> s4UnionKeys = unionKeyDao.fetchById(Arrays.asList(unionKeyDao.getId(uk1), unionKeyDao.getId(uk2)));
        Assertions.assertEquals(2, s4UnionKeys.size());

        s4UnionKeys = unionKeyDao.fetchById(Collections.singletonList(unionKeyDao.getId(uk1)));
        Assertions.assertEquals(1, s4UnionKeys.size());
    }
}
