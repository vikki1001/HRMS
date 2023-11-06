package com.ksvsofttech.product.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.EmpBasicDetails;

@Repository
public interface EmpBasicRepository extends JpaRepository<EmpBasicDetails, String> {

	@Query("SELECT e FROM EmpBasicDetails e WHERE e.coEmailId = :coEmailId")
	Optional<EmpBasicDetails> findEmpByEmail(String coEmailId);

	@Query("SELECT u FROM EmpBasicDetails u WHERE u.isActive=1")
	public List<EmpBasicDetails> getIsActive();

	@Query("SELECT u FROM EmpBasicDetails u WHERE u.isActive=0")
	public List<EmpBasicDetails> getInActive();

	@Query("SELECT u FROM EmpBasicDetails u WHERE u.empId = :empId and u.isActive=1")
	public EmpBasicDetails getCurrentUser(String empId);

	/* Employee Summary Report */
	@Query("SELECT e FROM EmpBasicDetails e WHERE e.empId LIKE %:empId% AND e.fullName LIKE %:fullName% AND e.departName LIKE %:departName% AND e.grade LIKE %:grade%")
	public List<EmpBasicDetails> getBySearch(@Param("empId") String empId, @Param("fullName") String fullName,
			@Param("departName") String departName, @Param("grade") String grade);

	@Query("SELECT u FROM EmpBasicDetails u WHERE u.empId = :empId and u.isActive=1")
	public List<EmpBasicDetails> listOfCurrentUser(String empId);

	/* Search Box in DashBoard */
	@Query("SELECT e FROM EmpBasicDetails e WHERE e.fullName LIKE %:fullName% AND isActive='1'")
	public List<EmpBasicDetails> getSearchEmployeeByFullName(String fullName);

	@Query(value = "SELECT * FROM EmpBasicDetails WHERE tenantId = :tenantId order by createdDate DESC limit 1", nativeQuery = true)
	public EmpBasicDetails getEmployeeId(String tenantId);

	/* Escalation Manager */
	@Query("SELECT e FROM EmpBasicDetails e WHERE e.empId LIKE %:value% OR e.fullName LIKE %:value%")
	public List<EmpBasicDetails> getSearchEmpByIdAndFullName(String value);
	
	@Query(value = "SELECT DISTINCT tenantId FROM EmpBasicDetails WHERE isActive='1' ORDER BY tenantId ASC", nativeQuery = true)
	public List<String> getUniqueTenantId();

	@Query(value = "SELECT employeeid FROM EmpBasicDetails WHERE isActive='1'",nativeQuery = true)
	public List<String> getEmpIdWithIsActive();	
	
	/* DashBoard Count for HR */
	@Query(value = "SELECT count(*) FROM EmpBasicDetails WHERE isActive = 1",nativeQuery = true)
	public long getAllEmployees();
	
	@Transactional
	@Modifying
	@Query("UPDATE EmpBasicDetails u SET u.file = ?1 WHERE u.empId = ?2")
	public void uploadImage(byte[] imageData, String empId);
	
	
	@Query(value = "SELECT * FROM EmpBasicDetails WHERE departmentname = :depId AND employeeid = :empId AND isActive = '1' ORDER BY createdDate DESC", nativeQuery = true)
	public EmpBasicDetails getKRAWithDepIdAndEmpId(String depId, String empId);

	@Query(value = "SELECT DISTINCT departmentname FROM EmpBasicDetails WHERE isActive='1' ORDER BY departmentname ASC", nativeQuery = true)
	List<String> getUniqueDepId();
	
}