package com.ksvsofttech.product.appraisal;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EmpKRARepository extends JpaRepository<EmpKRA, Long> {

	@Query(value = "SELECT * FROM EmpKRA WHERE userId = :empId AND isActive = '1'", nativeQuery = true)
	public List<EmpKRA> getCurrentEmpAppraisal(String empId);

	@Query(value = "SELECT * FROM EmpKRA WHERE levelIIAppStatus = 'Completed' AND isActive = '1' ORDER BY date DESC", nativeQuery = true)
	public List<EmpKRA> getAllEmpAppraisal();

	@Query(value = "SELECT * FROM EmpKRA WHERE userId = :empId AND selfAppStatus = 'Completed' ORDER BY date DESC", nativeQuery = true)
	public List<EmpKRA> appraisalCycleList(String empId);

	@Query(value = "SELECT * FROM EmpKRA WHERE userId = :empId", nativeQuery = true)
	public Optional<EmpKRA> findByempId(String empId);

	@Query(value = "SELECT * FROM EmpKRA WHERE EXISTS (SELECT * FROM EmpWorkDetails WHERE reportingManager = :managerId) AND userId = :empId", nativeQuery = true)
	public EmpKRA getManagerIdWithMangerId(String managerId, String empId);

	@Query(value = "SELECT * FROM EmpKRA WHERE depId = :depId AND userId = :empId AND isActive = '1' ORDER BY createdDate DESC", nativeQuery = true)
	public EmpKRA getKRAWithDepIdAndEmpId(String depId, String empId);

	@Query(value = "SELECT * FROM EmpKRA WHERE managerId = :managerId AND depId = :depId AND userId = :empId", nativeQuery = true)
	public EmpKRA getManagerIdWithMangerIdWithDepId(String managerId, String depId, String empId);

	@Query(value = "SELECT * FROM EmpKRA WHERE managerId = :empId AND depId = :depId OR managerId IN :empIds", nativeQuery = true)
	public List<EmpKRA> getEmpWithMangerIdOrReportingManager(String empId, String depId, List<String> empIds);

	@Query(value = "SELECT * FROM EmpKRA WHERE managerId = :empId AND depId = :depId", nativeQuery = true)
	public List<EmpKRA> getTeammetsTeamMangerId(String empId, String depId);

	@Query(value = "SELECT userId FROM EmpKRA WHERE managerId = :empId AND depId = :depId", nativeQuery = true)
	public List<String> getTeammetsTeamMangerId2(String empId, String depId);

	@Query(value = "SELECT * FROM EmpKRA WHERE depId = :depId AND userId = :empId AND isActive = '1' AND date = :date", nativeQuery = true)
	public EmpKRA getKRAWithDepIdAndEmpIdAndDate(String depId, String empId, String date);

	@Query(value = "SELECT * FROM EmpKRA WHERE userId = :empId AND isActive = '1'", nativeQuery = true)
	public List<EmpKRA> findEmpByEmpId(@Param("empId") String empId);

	@Query(value = "SELECT * FROM EmpKRA WHERE depId = :depId AND isActive = '1'", nativeQuery = true)
	public List<EmpKRA> findEmpByDepId(@Param("depId") String depId);

	/* For Duplicate KRA Check */
	@Query("SELECT e FROM EmpKRA e WHERE e.kraI = :kraI OR e.kraII = :kraII  OR e.kraIII = :kraIII OR e.kraIV = :kraIV OR e.kraV = :kraV OR e.kraVI = :kraVI OR e.kraVII = :kraVII OR e.kraVIII = :kraVIII OR e.kraIX = :kraIX OR e.kraX = :kraX")
	public Optional<EmpKRA> duplicateEmpKRACheck(String kraI, String kraII, String kraIII, String kraIV, String kraV,
			String kraVI, String kraVII, String kraVIII, String kraIX, String kraX);
}