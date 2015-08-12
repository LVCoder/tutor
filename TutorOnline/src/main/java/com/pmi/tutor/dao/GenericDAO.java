package com.pmi.tutor.dao;

import java.util.List;

public interface GenericDAO<T> {

	void save(T item);

	void update(T item);

	T fetchById(Class<T> objectClass, Long id);

	List<T> fetchAll(Class<T> objectClass);

	void delete(T item);

	void saveOrUpdate(T item);

	void deleteById(Class<T> clazz, Long id);

	T findEntity(String singleQuery, Object... params);

	List<T> findEntityList(String singleQuery, Object... params);

}
