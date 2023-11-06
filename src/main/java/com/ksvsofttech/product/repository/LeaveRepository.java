package com.ksvsofttech.product.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.LeaveMst;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveMst, Long> {

	@Query(value = "SELECT * FROM LeaveMst WHERE managerId = :empId AND isActive = '1' ORDER BY date DESC", nativeQuery = true)
	public List<LeaveMst> getEmpWithManger( String empId);
	
	@Query(value = "SELECT * FROM LeaveMst WHERE managerId = :empId AND isActive = '1' AND status = 'Pending'", nativeQuery = true)
	public List<LeaveMst> getLeavePending(String empId);
	
	@Query(value = "SELECT * FROM LeaveMst WHERE empId = :empId AND isActive = '1' ORDER BY date DESC", nativeQuery = true)
	public List<LeaveMst> getActiveLeave(String empId);

	@Query(value = "SELECT * FROM LeaveMst WHERE empId = :empId AND isActive = 0 ORDER BY date DESC", nativeQuery = true)
	public List<LeaveMst> getInactiveLeave(String empId);

	@Query(value = "SELECT * FROM LeaveMst WHERE empId = :empId AND isActive = '1'", nativeQuery = true)
	public List<LeaveMst> getLeaveApply(String empId);

	@Query(value = "SELECT * FROM LeaveMst WHERE date = CURDATE() AND id = :id AND isActive = '1'", nativeQuery = true)
	public List<LeaveMst> getAcceptLeaveById(Long id);

	@Transactional
	@Modifying
	@Query("update LeaveMst u set u.status = ?1, u.flag = ?2  where u.id = ?3")
	void acceptStatus(String status, String flag, Long id);

	@Query(value = "SELECT * FROM LeaveMst WHERE leaveType = :leaveType AND isActive = '1'", nativeQuery = true)
	public List<LeaveMst> getLeaveByLeaveType(@Param("leaveType") String leaveType);

	@Query(value = "SELECT * FROM LeaveMst WHERE status = 'Approved' AND isActive = '1' ORDER BY date DESC", nativeQuery = true)
	public List<LeaveMst> getApprovedLeave();
	
	@Query(value = "SELECT * FROM LeaveMst WHERE status = 'Approved' AND leaveType = :leaveType AND isActive = '1'", nativeQuery = true)
	public List<LeaveMst> getApprovedAndLeaveType(@Param("leaveType") String leaveType);
	
	@Query(value="SELECT * FROM LeaveMst WHERE isActive = '1' ORDER BY date DESC", nativeQuery = true)
	public List<LeaveMst> activeLeave();
	
	@Query(value = "SELECT * FROM LeaveMst WHERE isActive = '1' AND status = 'Approved' AND DATE(fromDate) <= CURRENT_DATE() AND DATE(toDate) >= CURRENT_DATE() ORDER BY date DESC",nativeQuery = true)
	public List<LeaveMst> onLeaveToday();
	
	/* DAYS OVERVIEW THIS MONTH */
	@Query(value = "SELECT count(*) FROM LeaveMst WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND empId = :userId AND isActive = 1", nativeQuery = true)
	public long getLeaveDays(String userId);
	
	/* DashBoard Count for HR */
	@Query(value = "SELECT count(*) FROM LeaveMst WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND status = 'Approved' AND isActive = '1'", nativeQuery = true)
	public long getAllLeaves();
	
	@Query(value = "SELECT count(*) FROM LeaveMst WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND status = 'Pending' AND isActive = '1'", nativeQuery = true)
	public long getAllPendingLeaves();
	
	/* Link */
	@Query(value = "SELECT * FROM LeaveMst WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND status = 'Approved' AND isActive = '1' ORDER BY date DESC", nativeQuery = true)
	public List<LeaveMst> getTotalLeave();
	
	@Query(value = "SELECT * FROM LeaveMst WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND status = 'Pending' AND isActive = '1' ORDER BY date DESC", nativeQuery = true)
	public List<LeaveMst> getTotalPendingLeave();

	@Query(value = "SELECT * FROM LeaveMst WHERE notification = 'Unread' AND isActive = '1' AND managerId = :userId", nativeQuery = true)
	public List<LeaveMst> unreadNotification(String userId);
	
	@Query(value = "SELECT * FROM LeaveMst WHERE managerId = :userId AND isActive = '1' ORDER BY createdDate DESC", nativeQuery = true)
	public List<LeaveMst> leaveMstsNotificationBell(String userId);

}