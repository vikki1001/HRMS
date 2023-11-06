package com.ksvsofttech.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.DepartmentMst;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentMst, Long> {
	
	@Query("SELECT e FROM DepartmentMst e WHERE e.departmentId = :departmentId")
	public Optional<DepartmentMst> findByDepartmentId(Long departmentId);

	@Query("SELECT u FROM DepartmentMst u WHERE u.isActive=1")
	public List<DepartmentMst> getIsActive();

	@Query("SELECT u FROM DepartmentMst u WHERE u.isActive=0")
	public List<DepartmentMst> getInActive();
	
	@Query("SELECT e FROM DepartmentMst e WHERE e.departmentName = :departmentName")
	public Optional<DepartmentMst> findDeptByDeptName(String departmentName);
}
