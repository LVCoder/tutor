package com.pmi.tutor.dao.implementation;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmi.tutor.dao.RoleDAO;
import com.pmi.tutor.domain.Role;
import com.pmi.tutor.domain.Role.RoleEnum;

@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role> implements RoleDAO {
	
	@Override
	@Transactional
	public Role fetchOrCreateRoleByName(RoleEnum name) {
		Role role = fetchRoleByName(name);
		if (role==null){
			role = new Role();
			role.setName(name);
			save(role);
		}
		return fetchRoleByName(name);
	}

	@Transactional
	private Role fetchRoleByName(RoleEnum name) {
		try {
			return (Role) getSession()
					.createQuery("from Role r where r.name = :name")
					.setParameter("name", name).uniqueResult();

		} catch (NoResultException e) {
			return null;
		}
	}

}
