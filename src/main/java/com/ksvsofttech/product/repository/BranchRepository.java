package com.ksvsofttech.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.BranchMst;

@Repository
public interface BranchRepository extends JpaRepository<BranchMst, Long> {

	@Query("SELECT u FROM BranchMst u WHERE u.isActive=1")
	public List<BranchMst> getIsActive();

	@Query("SELECT u FROM BranchMst u WHERE u.isActive=0")
	public List<BranchMst> getInActive();

	/* For Duplicate Branch Code Validation */
	@Query("SELECT e FROM BranchMst e WHERE e.branchCode = :branchCode")
	public Optional<BranchMst> findBranchByBranchCode(String branchCode);

}