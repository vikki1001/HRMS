package com.ksvsofttech.product.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.CheckInCheckOut;

@Repository
public interface CheckInCheckOutRepository extends JpaRepository<CheckInCheckOut, Long> {

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE date between :from AND :to ORDER BY date DESC", nativeQuery = true)
	public List<CheckInCheckOut> findAllEmp(@Param("from") String from, @Param("to") String to);

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE date between :from AND :to AND userId = :userId AND tenantId = :tenantId ORDER BY date DESC", nativeQuery = true)
	public List<CheckInCheckOut> findByDateorUserIdorTenantId(@Param("from") String from, @Param("to") String to, @Param("userId") String userId, @Param("tenantId") String tenantId);

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE date between :from AND :to AND userId = :userId ORDER BY date DESC", nativeQuery = true)
	public List<CheckInCheckOut> findByDateorUserId(@Param("from") String from, @Param("to") String to,
			@Param("userId") String userId);

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE date between :from AND :to AND tenantId = :tenantId ORDER BY date DESC", nativeQuery = true)
	public List<CheckInCheckOut> findByDateorTenantId(@Param("from") String from, @Param("to") String to,
			@Param("tenantId") String tenantId);

	@Query(value = "SELECT * FROM CheckInCheckOut e WHERE e.userId = :userId AND e.isActive = '1' ORDER BY date DESC", nativeQuery = true)
	public List<CheckInCheckOut> getCurrentUser(String userId);

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE date between :from AND :to AND userId = :userId AND isActive = '1' ORDER BY date", nativeQuery = true)
	public List<CheckInCheckOut> findByDayOfTheWeek(@Param("from") String from, @Param("to") String to, String userId);

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE date = CURDATE() AND userId = :userId", nativeQuery = true)
	public Optional<CheckInCheckOut> findByDateAndCurrentUser(@Param("userId") String userId);

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE date = CURDATE() AND userId = :userId", nativeQuery = true)
	public CheckInCheckOut findByDateAndCurrentUser2(@Param("userId") String userId);

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE YEARWEEK(date) = YEARWEEK(NOW()) AND userId = :userId", nativeQuery = true)
	public List<CheckInCheckOut> findByCurrentDay(@Param("userId") String userId);

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE date = CURDATE() AND userId = :userId AND isActive='1'", nativeQuery = true)
	public CheckInCheckOut findByEmpId(@Param("userId") String userId);

	@Query("SELECT e FROM CheckInCheckOut e WHERE e.userId = :userId AND e.date = :date")
	public Optional<CheckInCheckOut> getByUserId(String userId, String date);

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE managerId = :userId AND addNewReq = '1' ORDER BY date DESC", nativeQuery = true)
	public List<CheckInCheckOut> getEmpWithManger(String userId);

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE managerId = :userId AND addNewReq = '1' AND isActive='1' AND approvalrequest = 'Pending'", nativeQuery = true)
	public List<CheckInCheckOut> getAttendancePending(String userId);

	/* For Approved/Reject Request */
	@Transactional
	@Modifying
	@Query("update CheckInCheckOut u set u.approvalReq = ?1, u.status = ?2, u.isActive = ?3, u.flag = ?4 where u.id = ?5")
	void acceptStatus(String approvalReq, String status, String isActive, String flag, Long id);

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE id = :id", nativeQuery = true)
	public List<CheckInCheckOut> getAcceptLeaveById(Long id);

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE date = CURDATE() AND userId = :userId", nativeQuery = true)
	public CheckInCheckOut sendMail(String userId);

	@Query("SELECT e FROM CheckInCheckOut e WHERE e.userId = :userId AND e.addNewReq = '1' AND isActive='1' ORDER BY date DESC")
	public List<CheckInCheckOut> getNewReqList(String userId);

	@Query("SELECT e FROM CheckInCheckOut e WHERE e.userId = :userId AND e.addNewReq = '1' AND isActive='0' ORDER BY date DESC")
	public List<CheckInCheckOut> cancelReqList(String userId);

	@Transactional                           
	@Modifying
	@Query(value = "INSERT INTO CheckInCheckOut (userId, date, dayOfTheWeek, tenantId, isActive, checkindttm, checkoutdttm, timeDuration, status) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)", nativeQuery = true)
	void insertEmployee(String userId, String date, String dayOfTheWeek, String tenantId, String isActive, String checkinDateTime, String checkoutDateTime,String timeDuration, String status);
	
	@Query(value = "SELECT userId FROM CheckInCheckOut WHERE date = CURDATE() AND isActive = 1",nativeQuery = true)
	public List<String> getDateAndUserId();

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE date = :date AND userId = :userId", nativeQuery = true)
	public Optional<CheckInCheckOut> getByUserIdAndDate(String date, String userId);
	
	@Query(value = "SELECT userId FROM CheckInCheckOut WHERE date = CURDATE() AND timeDuration <= '08:00 Hrs' OR date = CURDATE() AND timeDuration is NULL", nativeQuery = true)
	public List<String> getNineHourNotComplete();
	
	@Query(value = "SELECT * FROM CheckInCheckOut WHERE date = CURDATE() AND userId IN :userId", nativeQuery = true)
	public List<CheckInCheckOut> getByUserIdAndCurentDate(List<String> userId);
	
	/* DAYS OVERVIEW THIS MONTH */
	@Query(value = "SELECT count(*) FROM CheckInCheckOut WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND userId = :userId AND isActive = 1", nativeQuery = true)
	public long getPresentDays(String userId);

	@Query(value = "SELECT count(*) FROM CheckInCheckOut WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND DATE_FORMAT(checkindttm, '%H:%i:%s') > '10:30:00' AND userId = :userId", nativeQuery = true)
	public long getLateDays(String userId);

	@Query(value = "SELECT count(*) FROM CheckInCheckOut WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND timeDuration < '08:00 Hrs' AND userId = :userId", nativeQuery = true)
	public long getHalfDays(String userId);

	@Query(value = "SELECT count(*) FROM CheckInCheckOut WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND userId = :userId AND isActive = 0", nativeQuery = true)
	public long getAbsentDays(String userId);
	
	/* DashBoard Count for HR */
	@Query(value = "SELECT count(*) FROM CheckInCheckOut WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND status = 'Present' AND isActive = '1'", nativeQuery = true)
	public long getAllAttendance();
	
	@Query(value = "SELECT count(*) FROM CheckInCheckOut WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND isActive = 0", nativeQuery = true)
	public long getAllEmpAbsent();
	
	@Query(value = "SELECT count(*) FROM CheckInCheckOut WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND approvalrequest = 'Approved' AND addNewReq = '1' AND isActive = '1'", nativeQuery = true)
	public long getAllNewReq();
	
	/* Link */
	@Query(value = "SELECT * FROM CheckInCheckOut WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND status = 'Absent' AND isActive = '0' ORDER BY date DESC", nativeQuery = true)
	public List<CheckInCheckOut> getTotalAbsentEmp();
	
	@Query(value = "SELECT * FROM CheckInCheckOut WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND status = 'Present' AND isActive = '1' ORDER BY date DESC", nativeQuery = true)
	public List<CheckInCheckOut> getTotalAttendance();
	
	@Query(value = "SELECT * FROM CheckInCheckOut WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND approvalrequest = 'Approved' AND addNewReq = '1' AND isActive = '1' ORDER BY date DESC", nativeQuery = true)
	public List<CheckInCheckOut> getTotalNewReq();
	
	@Query(value = "SELECT * FROM CheckInCheckOut WHERE date = curdate() AND userId = :userId AND isActive = '1'", nativeQuery = true)
	public CheckInCheckOut getCheckInDateTime(String userId);

	@Query(value = "SELECT * FROM CheckInCheckOut WHERE notification = 'Unread' AND isActive = '1' AND managerId = :userId", nativeQuery = true)
	public List<CheckInCheckOut> unreadNotification(String userId);
		
	@Query(value = "SELECT * FROM CheckInCheckOut WHERE managerId = :userId AND isActive = '1' AND addNewReq='1' ORDER BY date DESC", nativeQuery = true)
	public List<CheckInCheckOut> addNewReqNotificationBell(String userId);
	
	@Query(value = "SELECT * FROM CheckInCheckOut WHERE date between :from AND :to AND userId = :userId AND isActive = '1'",nativeQuery = true)
	public List<CheckInCheckOut> nextButton(@Param("from") String from, @Param("to") String to, String userId);
}