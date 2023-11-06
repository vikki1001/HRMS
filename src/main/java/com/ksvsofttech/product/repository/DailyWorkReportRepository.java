package com.ksvsofttech.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.DailyWorkReport;

@Repository
public interface DailyWorkReportRepository extends JpaRepository<DailyWorkReport, Long> {

	@Query("SELECT u FROM DailyWorkReport u WHERE u.empId = :empId AND u.isActive='1' ORDER BY date DESC")
	public List<DailyWorkReport> getIsActive(String empId);

	@Query("SELECT u FROM DailyWorkReport u WHERE u.empId = :empId AND u.isActive ='0' ORDER BY date DESC")
	public List<DailyWorkReport> getInActive(String empId);
	
	@Query("SELECT e FROM DailyWorkReport e WHERE e.id = :id")
	public Optional<DailyWorkReport> findByWorkReportId(Long id);
	
	@Query("SELECT e FROM DailyWorkReport e WHERE e.fullName = :fullName and e.isActive='1'")
	public Optional<DailyWorkReport> findByFullName(String fullName);
	
	/* DashBoard Count for HR */
	@Query(value = "SELECT count(*) FROM DailyWorkReport WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND isActive = '1'", nativeQuery = true)
	public long getAllWorkReport();
	
	/* Link */
	@Query(value = "SELECT * FROM DailyWorkReport WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND isActive = '1' ORDER BY date DESC", nativeQuery = true)
	public List<DailyWorkReport> getTotalWorkReport();	

}
