package com.pmi.tutor.dao;

import com.pmi.tutor.domain.Role;
import com.pmi.tutor.domain.Role.RoleEnum;

public interface RoleDAO extends GenericDAO<Role>{

	Role fetchOrCreateRoleByName(RoleEnum name);

}
