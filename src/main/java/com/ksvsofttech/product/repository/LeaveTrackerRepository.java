package com.ksvsofttech.product.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.LeaveTracker;

@Repository
@Transactional
public interface LeaveTrackerRepository extends JpaRepository<LeaveTracker, Long> {

	@Query(value = "SELECT * FROM LeaveTracker WHERE empId = :empId AND isActive = 1", nativeQuery = true)
	public List<LeaveTracker> getEmpDetails(String empId);

	@Query(value = "SELECT * FROM LeaveTracker WHERE empId = :empId AND isActive = 1", nativeQuery = true)
	public Optional<LeaveTracker> findByEmpId(String empId);

	/* BY ID */
	@Query(value = "SELECT * FROM LeaveTracker WHERE id = :id AND isActive = 1", nativeQuery = true)
	public Optional<LeaveTracker> getEmpDetailsById(Long id);

	@Modifying
	@Query("update LeaveTracker u set u.totalLeave = ?1 where u.id = ?2 and u.isActive = 1")
	void updateTotalLeaveById(Float totalLeave, Long id);

	@Modifying
	@Query("update LeaveTracker u set u.paidLeave = ?1 where u.id = ?2 and u.isActive = 1")
	void updatePaidLeaveById(Float paidLeave, Long id);

	@Modifying
	@Query("update LeaveTracker u set u.maternityLeave = ?1 where u.id = ?2 and u.isActive = 1")
	void updateMaternityLeaveById(Float maternityLeave, Long id);

	@Query("SELECT e FROM LeaveTracker e WHERE e.isActive='1'")
	public List<LeaveTracker> getActiveList();

	@Query("SELECT e FROM LeaveTracker e WHERE e.isActive='0'")
	public List<LeaveTracker> getInActiveList();

	@Modifying
	@Query(value = "UPDATE LeaveTracker e SET e.totalLeave = :totalLeave WHERE e.empId = :empId AND e.isActive = '1'", nativeQuery = true)
	public void updateTotalLeave(float totalLeave, String empId);

	@Query("SELECT totalLeave FROM LeaveTracker e WHERE e.isActive='1'")
	public List<Float> getAllEmpTotalLeave();

	@Modifying
	@Query(value = "UPDATE LeaveTracker e SET e.paidLeave = :paidLeave WHERE e.empId = :empId AND e.isActive = '1'", nativeQuery = true)
	public void updatePaidLeave(float paidLeave, String empId);

	@Query("SELECT paidLeave FROM LeaveTracker e WHERE e.isActive='1'")
	public List<Float> getAllEmpPaidLeave();

	@Modifying
	@Query(value = "UPDATE LeaveTracker e SET e.maternityLeave = :maternityLeave WHERE e.empId = :empId AND e.isActive = '1'", nativeQuery = true)
	public void updateMaternityLeave(float maternityLeave, String empId);

	@Query("SELECT maternityLeave FROM LeaveTracker e WHERE e.isActive='1'")
	public List<Float> getAllEmpMaternityLeave();

	@Query("SELECT empId FROM LeaveTracker e WHERE e.isActive='1'")
	public List<String> getAllEmp();

}
