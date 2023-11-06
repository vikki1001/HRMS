package com.ksvsofttech.product.service;

import java.util.List;

import com.ksvsofttech.product.entities.RoleMenuActionAccess;

public interface RoleMenuActionAccessService {
	
	public List<RoleMenuActionAccess> getMenuListByModuleRoleMap(String TenantId, String roleCode, String isActive) throws Exception;

	public List<RoleMenuActionAccess> getAllMenusByRoleCode(String roleCode) throws Exception;
	
	public List<RoleMenuActionAccess> getRoleMenuActionAccessListByRoleCode(String roleCode) throws Exception;

	public void saveMenus(List<RoleMenuActionAccess> roleMenuActionAccess) throws Exception;
}
