package com.ksvsofttech.product.dao;

import java.util.List;

import org.springframework.mobile.device.Device;

import com.ksvsofttech.product.entities.CheckInCheckOut;
import com.ksvsofttech.product.entities.EmpPersonalDetails;

public interface CheckInCheckOutDao {

	public List<CheckInCheckOut> getcheckInOutList() throws Exception;

	public List<CheckInCheckOut> findByDateorUserIdorTenantId(String from, String to, String userId, String tenantId)
			throws Exception;

	public List<CheckInCheckOut> findByDateorUserId(String from, String to, String userId) throws Exception;

	public List<CheckInCheckOut> findByDateorTenantId(String from, String to, String tenantId) throws Exception;

	public List<CheckInCheckOut> findAllEmp(String from, String to) throws Exception;

	public CheckInCheckOut saveCheckIn(CheckInCheckOut checkInCheckOut, Device device) throws Exception;

	public CheckInCheckOut saveCheckOut(CheckInCheckOut checkInCheckOut, Device device) throws Exception;

	public List<CheckInCheckOut> getTotalTime(String userId) throws Exception;

	public List<CheckInCheckOut> findByDayOfTheWeek(String from, String to, String userId) throws Exception;

	public List<String> getNineHourNotComplete() throws Exception;

	public List<EmpPersonalDetails> getUpcomingEmpBirthday() throws Exception;

	public CheckInCheckOut updateNewRequest(CheckInCheckOut checkInCheckOut, Device device) throws Exception;

	public CheckInCheckOut cancelAddReq(CheckInCheckOut checkInCheckOut) throws Exception;

	public List<CheckInCheckOut> getAttendancePending(String userId) throws Exception;

	public List<String> getDateAndUserId() throws Exception;

	public void insertEmployee(String userId, String date, String dayOfTheWeek, String tenantId, String isActive,
			String checkinDateTime, String checkoutDateTime, String timeDuration, String status) throws Exception;

	public CheckInCheckOut findByEmpId(String userId) throws Exception;

	public long getPresentDays(String userId) throws Exception;

	public long getLateDays(String userId) throws Exception;

	public long getHalfDays(String userId) throws Exception;

	public long getAbsentDays(String userId) throws Exception;

	public List<CheckInCheckOut> getNewReqList(String empId) throws Exception;

	public List<CheckInCheckOut> cancelReqList(String empId) throws Exception;

	public CheckInCheckOut sendMail(String userId) throws Exception;

	public List<CheckInCheckOut> getEmpWithManger(String empId) throws Exception;

	public List<CheckInCheckOut> getAcceptLeaveById(Long id) throws Exception;

	public void acceptStatus(String approvalReq, String status, String isActive, String flag, Long id) throws Exception;

	public List<CheckInCheckOut> getByUserIdAndCurentDate(List<String> userId) throws Exception;
	
	public List<CheckInCheckOut> getTotalAbsentEmp() throws Exception;
	
	public List<CheckInCheckOut> getTotalAttendance() throws Exception;
	
	public List<CheckInCheckOut> getTotalNewReq() throws Exception;
	
	public long getAllAttendance() throws Exception;
	
	public long getAllEmpAbsent() throws Exception;
	
	public List<CheckInCheckOut> getCurrentUser(String userId) throws Exception;
	
	public long getAllNewReq() throws Exception;

	public CheckInCheckOut findByDateAndCurrentUser2(String userId) throws Exception;

	public CheckInCheckOut notificationRead(CheckInCheckOut checkInCheckOut) throws Exception;
}
