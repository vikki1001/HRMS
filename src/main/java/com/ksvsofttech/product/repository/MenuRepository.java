package com.ksvsofttech.product.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ksvsofttech.product.entities.MenuMst;
import com.ksvsofttech.product.entities.RoleMst;

public interface MenuRepository extends JpaRepository<MenuMst, Integer> {

	@Query("SELECT u FROM MenuMst u WHERE u.tenantId=:tenantId AND u.menuCode IN :menuCode AND u.isActive='1' ")
	public List<MenuMst> getMenuMstIn(@Param("tenantId") String tenantId, @Param("menuCode") Set<String> menuCode);

	@Query("SELECT u FROM MenuMst u WHERE u.tenantId=:tenantId AND u.menuCode=:menuCode AND u.isActive='1'")
	public List<MenuMst> getMenuMst(String tenantId, String menuCode);

	@Query("SELECT u FROM MenuMst u WHERE u.tenantId=:tenantId AND u.sectionDisplayName=:sectionCode AND u.isVisible=1 AND u.isActive='1'")
	public List<MenuMst> getMenuMstBySubSection(String tenantId, String sectionCode);

	@Query("SELECT u FROM MenuMst u WHERE u.isActive=1")
	public List<MenuMst> getIsActive();
	
	@Query("SELECT u FROM RoleMst u WHERE u.roleCode = :roleCode")
	public RoleMst getByroleCode(@Param("roleCode") String roleCode);

	@Query("SELECT u FROM MenuMst u WHERE u.sectionCode = :roleCode AND u.isActive=1")
	public List<MenuMst> getMenu(@Param("roleCode") String roleCode);
}