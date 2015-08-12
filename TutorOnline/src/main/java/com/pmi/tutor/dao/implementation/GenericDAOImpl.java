package com.pmi.tutor.dao.implementation;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmi.tutor.dao.GenericDAO;

@Repository
public class GenericDAOImpl<T> implements GenericDAO<T> {

	static Logger LOGGER = Logger.getLogger(GenericDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(final T item) {
		sessionFactory.getCurrentSession().save(item);
	}

	@Override
	@Transactional
	public void update(final T item) {
		sessionFactory.getCurrentSession().merge(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public T fetchById(final Class<T> objectClass, final Long id) {
		return (T) sessionFactory.getCurrentSession().get(objectClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<T> fetchAll(final Class<T> objectClass) {
		return sessionFactory.getCurrentSession().createQuery("from " + objectClass.getName()).list();
	}

	@Override
	@Transactional
	public void delete(final T item) {
		sessionFactory.getCurrentSession().delete(item);

	}

	@Transactional
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional
	public void saveOrUpdate(final T item) {
		sessionFactory.getCurrentSession().saveOrUpdate(item);
	}

	@Override
	@Transactional
	public void deleteById(final Class<T> clazz, final Long id) {
		sessionFactory.getCurrentSession().createQuery("delete from " + clazz.getSimpleName() + " where id= :id").setParameter("id", id)
				.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public T findEntity(final String singleQuery, final Object... params) {
		final Query query = sessionFactory.getCurrentSession().getNamedQuery(singleQuery);

		for (int i = 0; i < params.length; i++) {
			final String p = String.valueOf(i + 1);
			query.setParameter(p, params[i]);
		}

		return (T) query.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<T> findEntityList(final String singleQuery, final Object... params) {
		final Session currentSession = sessionFactory.getCurrentSession();
		final Query query = currentSession.getNamedQuery(singleQuery);

		for (int i = 0; i < params.length; i++) {
			final String p = String.valueOf(i + 1);
			query.setParameter(p, params[i]);
		}

		return query.list();
	}


	public static List<String> resolveCollectionProperties(final Class<?> type) {
		final List<String> ret = new ArrayList<String>();
		try {
			final BeanInfo beanInfo = Introspector.getBeanInfo(type);
			for (final PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {

				if (Collection.class.isAssignableFrom(pd.getPropertyType())) {
					ret.add(pd.getName());
				}
			}
		} catch (final IntrospectionException e) {
			LOGGER.error("Error collection properties resolving");
		}
		return ret;
	}
}
