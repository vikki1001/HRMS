package com.ksvsofttech.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.RoleMenuActionAccess;

@Repository
public interface RoleMenuActionAccessRepository extends JpaRepository<RoleMenuActionAccess, Integer>{
	
	@Query("SELECT u FROM RoleMenuActionAccess u WHERE u.tenantId = :tenantId AND u.roleCode=:roleCode AND u.isActive = :isActive ORDER BY u.menuDisplayRank ASC")
	public List<RoleMenuActionAccess> getMenuListByRoleMap(String tenantId, String roleCode, String isActive);

	@Query("SELECT u FROM RoleMenuActionAccess u WHERE u.roleCode = :roleCode")
	public List<RoleMenuActionAccess> getMenuByRoleCode(String roleCode);
	
//	@Query("SELECT u FROM RoleMenuActionAccess u WHERE u.isActive=1") 
//	public List<RoleMenuActionAccess> getIsActieList();
	 
	@Query("SELECT u FROM RoleMenuActionAccess u WHERE u.roleCode=:roleCode")
	public List<RoleMenuActionAccess> getRoleMenuActionAccessListByRoleCode(String roleCode);
	
}
