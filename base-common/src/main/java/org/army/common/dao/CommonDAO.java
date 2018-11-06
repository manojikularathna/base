package org.army.common.dao;

import java.util.List;

public interface CommonDAO {

    <T> T add(T entity);

    <T> void update(T entity);

    <T> void add(List<T> entities);

    <T> void update(List<T> entities);

    <T> List<T> get(Class<T> type);

    <T, I> T get(Class<T> type, I id);
}
