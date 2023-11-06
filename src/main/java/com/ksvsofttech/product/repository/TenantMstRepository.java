package com.ksvsofttech.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.TenantMst;

@Repository
public interface TenantMstRepository extends JpaRepository<TenantMst, Long> {

	@Query("SELECT u FROM TenantMst u WHERE u.isActive='1' AND u.tenantId = :tenantId")
	public TenantMst getTenantDetails(String tenantId);

	
}
