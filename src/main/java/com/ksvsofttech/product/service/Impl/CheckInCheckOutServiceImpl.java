package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.CheckInCheckOutDao;
import com.ksvsofttech.product.entities.CheckInCheckOut;
import com.ksvsofttech.product.entities.EmpPersonalDetails;
import com.ksvsofttech.product.service.CheckInCheckOutService;

@Service
public class CheckInCheckOutServiceImpl implements CheckInCheckOutService {
	private static final Logger LOGGER = LogManager.getLogger(CheckInCheckOutServiceImpl.class);

	@Autowired
	private CheckInCheckOutDao checkInCheckOutDao;

	/* Display All Emp in CheckInOut */
	@Override
	public List<CheckInCheckOut> getcheckInOutList() throws Exception {
		List<CheckInCheckOut> checkInOutList;
		try {
			checkInOutList = checkInCheckOutDao.getcheckInOutList();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display all  checkInOut list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No all checkInOut record exist......");
		}
		return checkInOutList;
	}

	/* Display data using from date, to date, UserId & tenantId */
	@Override
	public List<CheckInCheckOut> findByDateorUserIdorTenantId(String from, String to, String userId, String tenantId)
			throws Exception {
		try {
			if ("All".equalsIgnoreCase(userId) && tenantId.isEmpty()) {
				return checkInCheckOutDao.findAllEmp(from, to);
			} else if ("All".equalsIgnoreCase(userId) && !tenantId.isEmpty()) {
				return checkInCheckOutDao.findByDateorTenantId(from, to, tenantId);
			} else if ("All".equalsIgnoreCase(tenantId) && userId.isEmpty()) {
				return checkInCheckOutDao.findAllEmp(from, to);
			} else if ("All".equalsIgnoreCase(tenantId) && !userId.isEmpty()) {
				return checkInCheckOutDao.findByDateorTenantId(from, to, tenantId);
			} else if (!from.isEmpty() && !to.isEmpty() && !userId.isEmpty() && !tenantId.isEmpty()) {
				return checkInCheckOutDao.findByDateorUserIdorTenantId(from, to, userId, tenantId);
			} else if (!from.isEmpty() && !to.isEmpty() && !userId.isEmpty()) {
				return checkInCheckOutDao.findByDateorUserId(from, to, userId);
			} else if (!from.isEmpty() && !to.isEmpty() && !tenantId.isEmpty()) {
				return  checkInCheckOutDao.findByDateorTenantId(from, to, tenantId);
			} else if (!from.isEmpty() && !to.isEmpty()) {
				return  checkInCheckOutDao.findAllEmp(from, to);
			}

		} catch (Exception e) {
			LOGGER.error("------Error occur while display findByDateorUserId checkInOut list------"+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No findByDateorUserId checkInOut record exist......");
		}
		return new ArrayList<>();
	}

	@Override
	public CheckInCheckOut saveCheckIn(CheckInCheckOut checkInCheckOut, Device device) throws Exception {
		try {
			checkInCheckOutDao.saveCheckIn(checkInCheckOut, device);
		} catch (Exception e) {
			LOGGER.error("Error occur while save checkIn data /n" + ExceptionUtils.getStackTrace(e));
			throw new Exception("error occur in checkIn ");
		}
		return checkInCheckOut;
	}

	@Override
	public CheckInCheckOut saveCheckOut(CheckInCheckOut checkInCheckOut, Device device) throws Exception {
		try {
			checkInCheckOutDao.saveCheckOut(checkInCheckOut, device);
		} catch (Exception e) {
			LOGGER.error("Error occur while save checkOut data /n" + ExceptionUtils.getStackTrace(e));
			throw new Exception("error occur in checkOut ");
		}
		return checkInCheckOut;
	}

	/* Display Total Login Time of Employee by userId */
	@Override
	public List<CheckInCheckOut> getTotalTime(String userId) throws Exception {
		try {
			return checkInCheckOutDao.getTotalTime(userId);
		} catch (Exception e) {
			LOGGER.error("Error occur while display current user list" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No user found " + userId);
		}
	}

	/* Display Data for Employee Weekly */
	@Override
	public List<CheckInCheckOut> findByDayOfTheWeek(String from, String to, String userId) throws Exception {
		try {
			return checkInCheckOutDao.findByDayOfTheWeek(from, to, userId);
		} catch (Exception e) {
			LOGGER.error("Error occur while display current user weekly data list" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No user data found " + userId);
		}
	}

	/* Mail Trigger */
	@Override
	public List<String> getNineHourNotComplete() throws Exception {
		List<String> checkInOutList = checkInCheckOutDao.getNineHourNotComplete();
		try {
			if (!checkInOutList.isEmpty()) {
				return checkInOutList;
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display today date data" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No current date user data found ");
		}
		return checkInOutList;
	}

	/* Employee Upcoming Birthday */
	@Override
	public List<EmpPersonalDetails> getUpcomingEmpBirthday() throws Exception {
		List<EmpPersonalDetails> personalDetailsList = checkInCheckOutDao.getUpcomingEmpBirthday();
		try {
			if (!personalDetailsList.isEmpty()) {
				return personalDetailsList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display employee birthdays ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Employee record exist ......");
		}
		return new ArrayList<>();
	}

	/* Update New Request */
	@Override
	public CheckInCheckOut updateNewRequest(CheckInCheckOut checkInCheckOut, Device device) throws Exception {
		try {
			checkInCheckOutDao.updateNewRequest(checkInCheckOut, device);
		} catch (Exception e) {
			LOGGER.error("------Error occur while update new request------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No emp update new request ......" + checkInCheckOut.getUserId());
		}
		return checkInCheckOut;
	}

	/* Cancel New add req by employee */
	@Override
	public CheckInCheckOut cancelAddReq(CheckInCheckOut checkInCheckOut) throws Exception {
		try {
			checkInCheckOutDao.cancelAddReq(checkInCheckOut);
		} catch (Exception e) {
			LOGGER.error("------Error occur while cancel request-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No request activate" + checkInCheckOut.getUserId());
		}
		return checkInCheckOut;
	}

	/* Display Employee Attendance request in Manager Dashboard */
	@Override
	public List<CheckInCheckOut> getAttendancePending(String userId) throws Exception {
		try {
			return checkInCheckOutDao.getAttendancePending(userId);
		} catch (Exception e) {
			LOGGER.error("Error occur while display attendance request in manager dashboard" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No user data found " + userId);
		}
	}

	@Override
	public List<String> getDateAndUserId() throws Exception {
		List<String> listOfUserWithDate = checkInCheckOutDao.getDateAndUserId();
		try {
			if (Objects.nonNull(listOfUserWithDate)) {
				return listOfUserWithDate;
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while insert absent employee data in current date " + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found ");
		}
		return new ArrayList<>();
	}

	@Override
	public void insertEmployee(String userId, String date, String dayOfTheWeek, String tenantId, String isActive,
			String checkinDateTime, String checkoutDateTime, String timeDuration, String status) throws Exception {
		try {
			checkInCheckOutDao.insertEmployee(userId, date, dayOfTheWeek, tenantId, isActive, checkinDateTime,
					checkoutDateTime, timeDuration, status);
		} catch (Exception e) {
			LOGGER.error("Error occur while insert absent employee data " + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found ");
		}

	}

	@Override
	public CheckInCheckOut findByEmpId(String userId) throws Exception {
		CheckInCheckOut checkInCheckOut = checkInCheckOutDao.findByEmpId(userId);
		try {
			if (Objects.nonNull(checkInCheckOut)) {
				return checkInCheckOut;
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while find employee by employee ID " + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found... " + userId);
		}
		return checkInCheckOut;
	}

	@Override
	public long getPresentDays(String userId) throws Exception {
		try {
			return checkInCheckOutDao.getPresentDays(userId);
		} catch (Exception e) {
			LOGGER.error("Error while get present days of employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " + userId);
		}
	}

	@Override
	public long getLateDays(String userId) throws Exception {
		try {
			return checkInCheckOutDao.getLateDays(userId);
		} catch (Exception e) {
			LOGGER.error("Error while get late days of employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " + userId);
		}
	}

	@Override
	public long getHalfDays(String userId) throws Exception {
		try {
			return checkInCheckOutDao.getHalfDays(userId);
		} catch (Exception e) {
			LOGGER.error("Error while get half days of employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " + userId);
		}
	}

	@Override
	public long getAbsentDays(String userId) throws Exception {
		try {
			return checkInCheckOutDao.getAbsentDays(userId);
		} catch (Exception e) {
			LOGGER.error("Error while get absent days of employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " + userId);
		}
	}

	@Override
	public List<CheckInCheckOut> getNewReqList(String empId) throws Exception {
		List<CheckInCheckOut> checkInCheckOut = checkInCheckOutDao.getNewReqList(empId);
		try {
			if (Objects.nonNull(checkInCheckOut)) {
				return checkInCheckOut;
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display new request list " + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found " + empId);
		}
		return checkInCheckOut;
	}

	@Override
	public List<CheckInCheckOut> cancelReqList(String empId) throws Exception {
		List<CheckInCheckOut> checkInCheckOut = checkInCheckOutDao.cancelReqList(empId);
		try {
			if (Objects.nonNull(checkInCheckOut)) {
				return checkInCheckOut;
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display cancel request list " + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found " + empId);
		}
		return checkInCheckOut;
	}

	@Override
	public CheckInCheckOut sendMail(String userId) throws Exception {
		CheckInCheckOut checkInCheckOut = checkInCheckOutDao.sendMail(userId);
		try {
			if (Objects.nonNull(checkInCheckOut)) {
				return checkInCheckOut;
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while send mail to hr & manager " + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data send ");
		}
		return checkInCheckOut;
	}

	@Override
	public List<CheckInCheckOut> getEmpWithManger(String empId) throws Exception {
		List<CheckInCheckOut> checkInCheckOut = checkInCheckOutDao.getEmpWithManger(empId);
		try {
			if (Objects.nonNull(checkInCheckOut)) {
				return checkInCheckOut;
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display employee record to manager " + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found " + empId);
		}
		return checkInCheckOut;
	}

	@Override
	public List<CheckInCheckOut> getAcceptLeaveById(Long id) throws Exception {
		List<CheckInCheckOut> checkInCheckOut = checkInCheckOutDao.getAcceptLeaveById(id);
		try {
			if (Objects.nonNull(checkInCheckOut)) {
				return checkInCheckOut;
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while accept/reject request by id " + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found " + id);
		}
		return checkInCheckOut;
	}

	@Override
	public void acceptStatus(String approvalReq, String status, String isActive, String flag, Long id)
			throws Exception {
		try {
			checkInCheckOutDao.acceptStatus(approvalReq, status, isActive, flag, id);
		} catch (Exception e) {
			LOGGER.error("Error occur while save data if manager accept/reject request " + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found ");
		}
	}

	@Override
	public List<CheckInCheckOut> getByUserIdAndCurentDate(List<String> userId) throws Exception {
		List<CheckInCheckOut> checkInCheckOuts = checkInCheckOutDao.getByUserIdAndCurentDate(userId); 
		try {
			if (Objects.nonNull(checkInCheckOuts)) {
				return checkInCheckOuts;
			} else {
				System.out.println("Null Data Found :::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while get list of userId with currentDate " + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found ");
		}
		return new ArrayList<>();
	}
	@Override
	public List<CheckInCheckOut> getTotalAbsentEmp() throws Exception {
		List<CheckInCheckOut> checkInCheckOuts = checkInCheckOutDao.getTotalAbsentEmp();
		try {
			if (Objects.nonNull(checkInCheckOuts)) {
				return checkInCheckOuts;
			} else {
				System.out.println("Null Data Get :::::::: ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while get list of total absent emp " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}
	
	@Override
	public List<CheckInCheckOut> getTotalAttendance() throws Exception {
		List<CheckInCheckOut> checkInCheckOuts = checkInCheckOutDao.getTotalAttendance();
		try {
			if (Objects.nonNull(checkInCheckOuts)) {
				return checkInCheckOuts;
			} else {
				System.out.println("Null Data Get :::::::: ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while get list of total attendance " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}
	
	@Override
	public List<CheckInCheckOut> getTotalNewReq() throws Exception {
		List<CheckInCheckOut> checkInCheckOuts = checkInCheckOutDao.getTotalNewReq();
		try {
			if (Objects.nonNull(checkInCheckOuts)) {
				return checkInCheckOuts;
			} else {
				System.out.println("Null Data Get :::::::: ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while get list of total new req. " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public long getAllAttendance() throws Exception {
		try {
			return checkInCheckOutDao.getAllAttendance();
		} catch (Exception e) {
			LOGGER.error("Error occuring while get all attendance of employees in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " );
		}
	}

	@Override
	public long getAllEmpAbsent() throws Exception {
		try {
			return checkInCheckOutDao.getAllEmpAbsent();
		} catch (Exception e) {
			LOGGER.error("Error occuring while get all absent of employees in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " );
		}
	}

	@Override
	public long getAllNewReq() throws Exception {
		try {
			return checkInCheckOutDao.getAllNewReq();
		} catch (Exception e) {
			LOGGER.error("Error occuring while get all new req of employees in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " );
		}
	}

	@Override
	public List<CheckInCheckOut> getCurrentUser(String userId) throws Exception {
		List<CheckInCheckOut> checkInOutList = checkInCheckOutDao.getCurrentUser(userId);
		try {
			if (checkInOutList != null) {
				return checkInOutList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display list of current user ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No User record exist ......");
		}
		return new ArrayList<>();
	}

	@Override
	public CheckInCheckOut findByDateAndCurrentUser2(String userId) throws Exception {
		CheckInCheckOut checkInOutList = checkInCheckOutDao.findByDateAndCurrentUser2(userId);
		try {
			if (checkInOutList != null) {
				return checkInOutList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display current user with today date ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No User record exist ......");
		}
		return new CheckInCheckOut();
	}

	@Override
	public CheckInCheckOut notificationRead(CheckInCheckOut checkInCheckOut) throws Exception {
		try {
			checkInCheckOutDao.notificationRead(checkInCheckOut);
		} catch (Exception e) {
			LOGGER.error("Error occur while save notification /n" + ExceptionUtils.getStackTrace(e));
		}
		return checkInCheckOut;
	}
}