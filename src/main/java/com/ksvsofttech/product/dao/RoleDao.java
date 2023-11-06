package com.ksvsofttech.product.dao;

import java.util.List;
import java.util.Optional;

import com.ksvsofttech.product.entities.RoleMst;

public interface RoleDao {

	public List<RoleMst> getIsActive() throws Exception;

	public List<RoleMst> getInActive() throws Exception;

	public RoleMst save(RoleMst role) throws Exception;

	public RoleMst getRoleById(long roleId) throws Exception;

	public RoleMst deactiveRole(RoleMst roleMst) throws Exception;

	public RoleMst activateRole(RoleMst roleMst) throws Exception;
	
	public Optional<RoleMst> findRoleByRoleCode(String roleCode) throws Exception;

	/* For Duplicate Role Code Validation */
	public boolean roleExists(String roleCode) throws Exception;

}
