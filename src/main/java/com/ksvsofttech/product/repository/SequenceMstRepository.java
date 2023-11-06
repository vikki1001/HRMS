package com.ksvsofttech.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.SequenceMst;

@Repository
public interface SequenceMstRepository extends JpaRepository<SequenceMst, Long> {

	/* User/Edit Registration */
	@Query(value = "SELECT *  FROM SequenceMst WHERE isActive = '1'", nativeQuery = true)
	public List<SequenceMst> getTenantId();
	
	@Query(value = "SELECT *  FROM SequenceMst WHERE tenantId = :tenantId", nativeQuery = true)
	public Optional<SequenceMst> getSequenceKey(String tenantId);
	
}
