package com.ksvsofttech.product.service;

import java.util.List;
import java.util.Optional;

import com.ksvsofttech.product.entities.RoleMst;

public interface RoleService {

	public List<RoleMst> getIsActive() throws Exception;

	public List<RoleMst> getInActive() throws Exception;

	public void save(RoleMst role) throws Exception;

	public RoleMst getRoleById(long roleId) throws Exception;
	
	public RoleMst deactiveRole(RoleMst roleMst) throws Exception;

	public RoleMst activateRole(RoleMst roleMst) throws Exception;
	
	/* For Duplicate Role Code Validation */
	public Optional<RoleMst> findRoleByRoleCode(String roleCode) throws Exception;

	public boolean roleExists(String roleCode) throws Exception;

}
