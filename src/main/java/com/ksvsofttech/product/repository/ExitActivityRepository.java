package com.ksvsofttech.product.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ksvsofttech.product.entities.ExitActivity;

public interface ExitActivityRepository extends JpaRepository<ExitActivity, Long> {

	@Query(value = "SELECT * FROM ExitActivity  WHERE empId = :empId AND isActive = '1' ORDER BY createdDate DESC", nativeQuery = true)
	public List<ExitActivity> isActiveExitActivity(String empId);

	@Query(value = "SELECT * FROM ExitActivity  WHERE empId = :empId AND isActive = '0' ORDER BY createdDate DESC", nativeQuery = true)
	public List<ExitActivity> cancelExitActivity(String empId);

	@Query(value = "SELECT * FROM ExitActivity WHERE id = :id AND isActive = '1'", nativeQuery = true)
	public Optional<ExitActivity> cancelById(Long id);

	@Query(value = "SELECT * FROM ExitActivity WHERE empId = :empId AND isActive = '1'", nativeQuery = true)
	public List<ExitActivity> getExitActivityById(String empId);

	@Query(value = "SELECT * FROM ExitActivity WHERE managerId = :empId AND isActive = '1' ORDER BY createdDate DESC", nativeQuery = true)
	public List<ExitActivity> getEmpWithManger(String empId);

	@Query(value = "SELECT * FROM ExitActivity WHERE managerId = :empId AND isActive = '1' AND status = 'Pending' ORDER BY createdDate DESC", nativeQuery = true)
	public List<ExitActivity> getEmpWithMangerWithPending(String empId);

	@Transactional
	@Modifying
	@Query("update ExitActivity u set u.status = ?1, u.flag = ?2  where u.id = ?3")
	public void acceptStatus(String status, String flag, Long id);

	@Query(value = "SELECT * FROM ExitActivity WHERE id = :id AND isActive = '1'", nativeQuery = true)
	public List<ExitActivity> acceptExitActivityById(Long id);

	/* DashBoard Count for HR */
	@Query(value = "SELECT count(*) FROM ExitActivity WHERE MONTH(createdDate) = MONTH(CURRENT_DATE()) AND status = 'Pending' AND isActive = '1'", nativeQuery = true)
	public long getAllExitActivity();
	
	/* Link */
	@Query(value = "SELECT * FROM ExitActivity WHERE MONTH(createdDate) = MONTH(CURRENT_DATE()) AND status = 'Approved' AND isActive = '1' ORDER BY createdDate DESC", nativeQuery = true)
	public List<ExitActivity> getTotalExitActivity();

	@Query(value = "SELECT * FROM ExitActivity WHERE notification = 'Unread' AND isActive = '1' AND managerId = :userId", nativeQuery = true)
	public List<ExitActivity> unreadNotification(String userId);

	@Query(value = "SELECT * FROM ExitActivity WHERE managerId = :empId AND isActive = '1' ORDER BY createdDate DESC", nativeQuery = true)
	public List<ExitActivity> exitActivityNotificationBell(String empId);
}