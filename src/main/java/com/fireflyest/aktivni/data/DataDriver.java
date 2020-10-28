package com.fireflyest.aktivni.data;

import java.util.List;

public interface DataDriver {

    void update(Object obj);

    int insert(Object obj);

    List<?> queryList(Class<?> clazz, Object target, String key);

    Object query(Class<?> clazz, Object target, String key);

    Object query(Class<?> clazz, Object target);

    Object query(Object obj);

    void delete(Class<?> clazz, Object target, String key);

    void delete(Object obj);

    boolean contain(Class<?> clazz, Object target, String key);

    boolean contain(Object obj);

    void initTable(Class<?> clazz);

}
