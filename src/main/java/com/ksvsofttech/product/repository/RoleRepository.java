package com.ksvsofttech.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.RoleMst;

@Repository
public interface RoleRepository extends JpaRepository<RoleMst, Long> {
	
	/* For Duplicate Role Code Validation */
	@Query("SELECT e FROM RoleMst e WHERE e.roleCode = :roleCode")
	public Optional<RoleMst> findRoleByRoleCode(String roleCode);

	@Query("SELECT u FROM RoleMst u WHERE u.isActive=1")
	public List<RoleMst> getIsActive();

	@Query("SELECT u FROM RoleMst u WHERE u.isActive=0")
	public List<RoleMst> getInActive();

	@Query("UPDATE RoleMst s SET s.isActive='0' WHERE s.roleId = :roleId")
	public List<RoleMst> findByroleId(long roleId);
}
