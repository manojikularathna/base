package org.army.common.dao.impl;

import org.army.common.dao.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CommonDAOImpl implements CommonDAO {

    @Autowired
    private EntityManager entityManager;

    public <T> T add(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public <T> void update(T entity) {
        entityManager.persist(entity);
    }

    public <T> void add(List<T> entities) {
        entities.forEach(entityManager::persist);
    }

    public <T> void update(List<T> entities) {
        entities.forEach(entityManager::persist);
    }

    public <T> List<T> get(Class<T> type) {
        return entityManager.createQuery(entityManager.getCriteriaBuilder().createQuery(type)).getResultList();
    }

    public <T, I> T get(Class<T> type, I id) {
        return entityManager.find(type, id);
    }
}
