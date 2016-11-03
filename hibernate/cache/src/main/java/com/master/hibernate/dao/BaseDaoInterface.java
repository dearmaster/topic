package com.master.hibernate.dao;

import java.io.Serializable;
import java.util.Set;

public interface BaseDaoInterface<T> {

    void save(T t);

    void del(T t);

    void upd(T t);
    
    T load(Class<T> c, Serializable id);

    Set<T> loadAll();

}
