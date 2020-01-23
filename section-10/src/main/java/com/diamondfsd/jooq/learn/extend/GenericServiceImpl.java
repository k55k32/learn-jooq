package com.diamondfsd.jooq.learn.extend;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 抽象通用Service
 *
 * @author Diamond
 * @param <P>
 * @param <T>
 */
public abstract class GenericServiceImpl<P, T> implements GenericService<P, T> {
    private ExtendDAO<?, P, T> dao;

    public GenericServiceImpl(ExtendDAO<?, P, T> dao) {
        this.dao = dao;
    }

    @Override
    public P get(T id) {
        return dao.findById(id);
    }

    @Override
    public Optional<P> getOptional(T id) {
        return Optional.ofNullable(get(id));
    }

    @Override
    public T save(P entity) {
        dao.insertSelective(entity);
        return dao.getId(entity);
    }

    @Override
    public void save(Collection<P> entities) {
        dao.insertSelective(entities);
    }

    @Override
    public void update(P entity) {
        dao.updateSelective(entity);
    }

    @Override
    public void update(Collection<P> entities) {
        dao.updateSelective(entities);
    }

    @Override
    public PageResult<P> page(PageResult<P> page) {
        return dao.fetchPage(page);
    }

    @Override
    public void delete(T id) {
        dao.deleteById(id);
    }

    @Override
    public void delete(Collection<T> ids) {
        dao.deleteById(ids);
    }

    @Override
    public List<P> listAll() {
        return dao.findAll();
    }

    @Override
    public List<P> listByIds(Collection<T> ids) {
        return dao.fetchById(ids);
    }
}
