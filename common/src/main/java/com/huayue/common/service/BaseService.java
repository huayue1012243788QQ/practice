package com.huayue.common.service;

import com.huayue.common.exception.NotFoundException;
import com.huayue.common.repository.BaseRepository;

import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/13.
 */
public abstract class BaseService<T> {
    public abstract BaseRepository<T> getRepository();

    public T save(T t) {
        return getRepository().save(t);
    }

    public List<T> queryForAll() {
        return getRepository().findAll();
    }

    public T findById(String id) {
        if (!getRepository().existsById(id)) {
            throw new NotFoundException(id);
        }
        return getRepository().findById(id).get();
    }
    public T update(T t) {
        return getRepository().saveAndFlush(t);
    }
    public void delete(String id) {
        getRepository().deleteById(id);
    }
}
