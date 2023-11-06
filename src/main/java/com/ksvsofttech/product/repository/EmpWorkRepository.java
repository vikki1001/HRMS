package com.ksvsofttech.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.EmpWorkDetails;

@Repository
public interface EmpWorkRepository extends JpaRepository<EmpWorkDetails, String> {

	@Query(value = "SELECT * FROM EmpWorkDetails WHERE reportingManager = :empId", nativeQuery = true)
	public List<EmpWorkDetails> getEmpWithManger(String empId);

	@Query(value = "SELECT * FROM EmpWorkDetails WHERE reportingManager = :empId AND employeeid IN :empId2", nativeQuery = true)
	public EmpWorkDetails getEmpWithManger1(String empId, List<String> empId2);

	@Query(value = "SELECT employeeid FROM EmpWorkDetails WHERE reportingManager = :empId", nativeQuery = true)
	public List<String> getEmpWithManger3(String empId);

	@Query(value = "SELECT employeeid FROM EmpWorkDetails WHERE reportingManager = :empId", nativeQuery = true)
	public List<String> getAllEmpId(String empId);
	
	@Query(value = "SELECT * FROM EmpWorkDetails WHERE employeeid IN :empIds", nativeQuery = true)
	public List<EmpWorkDetails> getAllEmpJoiningDate(List<String> empIds);


//	@Query(value = "SELECT * FROM EmpWorkDetails WHERE reportingManager = :empId OR reportingManager = :reportingManager AND ( SELECT * FROM EmpBasicDetails WHERE departName = :depId)", nativeQuery = true)
//	public List<EmpWorkDetails> getEmpWithMangerIdOrReportingManager(String empId, String depId, String reportingManager);
}