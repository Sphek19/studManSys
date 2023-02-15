package com.sms.studmansys.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> list();
    Optional<T> get(int id);
    T create(T t);
    void update(T t, int id);
    void delete(int id);
}
