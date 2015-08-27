package com.pmi.tutor.dao.implementation;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.pmi.tutor.dao.TemporaryLinkDAO;
import com.pmi.tutor.domain.TemporaryLink;
@Repository
public class TemporaryLinkDAOImpl extends GenericDAOImpl<TemporaryLink> implements TemporaryLinkDAO{

	@Override
	public TemporaryLink fetchByLink(String link){
		try{
		return (TemporaryLink) getSession().createQuery("from TemporaryLink tl where tl.link=:link").setParameter("link", link).uniqueResult();
		} catch(NoResultException e){
			return null;
		} 
		}
}
