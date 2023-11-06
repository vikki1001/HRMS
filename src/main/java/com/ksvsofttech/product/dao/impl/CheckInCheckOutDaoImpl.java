package com.ksvsofttech.product.dao.impl;

import java.net.InetAddress;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.CheckInCheckOutDao;
import com.ksvsofttech.product.entities.CheckInCheckOut;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.EmpPersonalDetails;
import com.ksvsofttech.product.repository.CheckInCheckOutRepository;
import com.ksvsofttech.product.repository.EmpBasicRepository;
import com.ksvsofttech.product.repository.EmpPersonalRepository;

@Repository
public class CheckInCheckOutDaoImpl implements CheckInCheckOutDao {
	private static final Logger LOGGER = LogManager.getLogger(CheckInCheckOutDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private CheckInCheckOutRepository checkInOutRepository;
	@Autowired
	private EmpPersonalRepository empPersonalRepository;
	@Autowired
	private EmpBasicRepository empBasicRepository;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/* Display All Emp in CheckInOut */
	@Override
	public List<CheckInCheckOut> getcheckInOutList() throws Exception {
		List<CheckInCheckOut> checkInOutList = checkInOutRepository.findAll();
		try {
			if (!checkInOutList.isEmpty()) {
				return checkInOutList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display all checkInOut list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No all checkInOut record exist ......");
		}
		return new ArrayList<>();
	}

	/* Display data using from date, to date, UserId & tenantId */
	@Override
	public List<CheckInCheckOut> findByDateorUserIdorTenantId(String from, String to, String userId, String tenantId)
			throws Exception {
		List<CheckInCheckOut> checkInOutList = checkInOutRepository.findByDateorUserIdorTenantId(from, to, userId,
				tenantId);
		try {
			if (!checkInOutList.isEmpty()) {
				return checkInOutList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display findByDateorUserId checkInOut list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No findByDateorUserId checkInOut record exist ......");
		}
		return checkInOutList;
	}

	@Override
	public List<CheckInCheckOut> findByDateorUserId(String from, String to, String userId) throws Exception {
		List<CheckInCheckOut> checkInOutList = checkInOutRepository.findByDateorUserId(from, to, userId);
		try {
			if (!checkInOutList.isEmpty()) {
				return checkInOutList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display findByDateorUserId checkInOut list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No findByDateorUserId checkInOut record exist ......");
		}
		return checkInOutList;
	}

	@Override
	public List<CheckInCheckOut> findByDateorTenantId(String from, String to, String tenantId) throws Exception {
		List<CheckInCheckOut> checkInOutList = checkInOutRepository.findByDateorTenantId(from, to, tenantId);
		try {
			if (!checkInOutList.isEmpty()) {
				return checkInOutList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display findByDateorTenantId checkInOut list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No findByDateorTenantId checkInOut record exist ......");
		}
		return checkInOutList;
	}

	@Override
	public List<CheckInCheckOut> findAllEmp(String from, String to) throws Exception {
		List<CheckInCheckOut> checkInOutList = checkInOutRepository.findAllEmp(from, to);
		try {
			if (!checkInOutList.isEmpty()) {
				return checkInOutList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display findByDate checkInOut list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No findByDate checkInOut record exist ......");
		}
		return checkInOutList;
	}

	/* Check In */
	@Override
	public CheckInCheckOut saveCheckIn(CheckInCheckOut checkInCheckOut, Device device) throws Exception {
		String empId = null;

		/* Current user */
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		empId = authentication.getName();

		/* Enable device detection */
		String deviceType = "";
		if (device.isMobile()) {
			deviceType = "Mobile";
		} else if (device.isTablet()) {
			deviceType = "Tablet";
		} else if (device.isNormal()) {
			deviceType = "Browser";
		}

		/* IpAddress */
		InetAddress ip = InetAddress.getLocalHost();
		/* Check in Date & Time */
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		String dateTime = sdf.format(new Date());
		LOGGER.info("CHECK-IN DATE ::::" + dateTime);

		/* Day of the Week */
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");

		EmpBasicDetails basicDetails = empBasicRepository.getCurrentUser(empId);

		try {
			Optional<CheckInCheckOut> checkInCheckOut2 = checkInOutRepository.findByDateAndCurrentUser(empId);
			if (checkInCheckOut2.isPresent()) {
				CheckInCheckOut newCheckInCheckOut = checkInCheckOut2.get();
				newCheckInCheckOut.setUserId(userDetails.getUsername());
				newCheckInCheckOut.setCheckinActionFrom(deviceType);
				newCheckInCheckOut.setCheckinIpAddress(ip.getHostAddress());
				newCheckInCheckOut.setDate(date);
				newCheckInCheckOut.setCheckinDateTime(dateTime);
				newCheckInCheckOut.setDayOfTheWeek(simpleDateformat.format(now));
				newCheckInCheckOut.setTenantId(basicDetails.getTenantId());
				newCheckInCheckOut.setManagerId(basicDetails.getEmpWorkDetails().getReportingManager());
				newCheckInCheckOut.setFlag("Y");
				newCheckInCheckOut.setStatus("Present");
				newCheckInCheckOut.setIsActive("1");
				newCheckInCheckOut.setCheckoutDateTime(null);
				newCheckInCheckOut.setTimeDuration(null);
				newCheckInCheckOut.setLastModifiedBy(empId);
				newCheckInCheckOut.setLastModifiedDate(new Date());
				LOGGER.info("UPDATE EMP CHECK-IN SUCCESS:::::::");
				checkInOutRepository.save(newCheckInCheckOut);
			} else {
				checkInCheckOut.setUserId(userDetails.getUsername());
				checkInCheckOut.setCheckinActionFrom(deviceType);
				checkInCheckOut.setCheckinIpAddress(ip.getHostAddress());
				checkInCheckOut.setDate(date);
				checkInCheckOut.setCheckinDateTime(dateTime);
				checkInCheckOut.setDayOfTheWeek(simpleDateformat.format(now));
				checkInCheckOut.setTenantId(basicDetails.getTenantId());
				checkInCheckOut.setManagerId(basicDetails.getEmpWorkDetails().getReportingManager());
				checkInCheckOut.setFlag("Y");
				checkInCheckOut.setStatus("Present");
				checkInCheckOut.setIsActive("1");
				checkInCheckOut.setCreatedDate(new Date());
				checkInCheckOut.setCreatedBy(empId);

//				/* City,State & Country */

//				String IP = "2402:8100:3856:7419:b4d9:80f3:cc9f:5cf1";
//				String dbLocation = "D:\\ECLIPSE-PROJECT\\ksvsofttech\\src\\main\\resources\\geolite\\GeoLite2-City.mmdb";
//
//				//File database = new File(dbLocation);
//				
//				/* A File object pointing to your GeoLite2 database */
//				File dbFile = new File(dbLocation);
//
//				/* This creates the DatabaseReader object, which should be reused acros lookups. */
//				DatabaseReader reader = new DatabaseReader.Builder(dbFile).build();
//
//				/* A IP Address */
//				InetAddress ipAddress = InetAddress.getByName(IP);
//				//InetAddress ipAddress = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());
//				if (ipAddress != null) {
//
//						CityResponse response = reader.city(ipAddress);
//
//						Country country = response.getCountry();
//						checkInCheckOut.setCountry(country.getName());
//
//						Subdivision subdivision = response.getMostSpecificSubdivision();
//						checkInCheckOut.setState(subdivision.getName());
//
//						City city = response.getCity();
//						checkInCheckOut.setCity(city.getName());
			}
			LOGGER.info("NEW EMP CHECK-IN SUCCESS:::::::");
			checkInOutRepository.save(checkInCheckOut);

		} catch (Exception e) {
			LOGGER.error("Error occur while set checkin data----- \n" + ExceptionUtils.getStackTrace(e));
		}
		return checkInCheckOut;
	}

	/* Check Out */
	@Override
	public CheckInCheckOut saveCheckOut(CheckInCheckOut checkInCheckOut, Device device) throws Exception {
		String userId = null;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userId = authentication.getName();
		CheckInCheckOut userDetails = checkInOutRepository.findByDateAndCurrentUser2(userId);
		checkInCheckOut = userDetails;

		/* Enable device detection */
		String deviceType = "";
		if (device.isMobile()) {
			deviceType = "Mobile";
		} else if (device.isTablet()) {
			deviceType = "Tablet";
		} else if (device.isNormal()) {
			deviceType = "Browser";
		}

		/* IpAddress */
		InetAddress ip = InetAddress.getLocalHost();

		String dateTime = sdf.format(new Date());

		final DecimalFormat df = new DecimalFormat("00");

		try {
			Optional<CheckInCheckOut> checkInCheckOut2 = checkInOutRepository.findByDateAndCurrentUser(userId);
			if (checkInCheckOut2.isPresent()) {
				CheckInCheckOut newCheckInCheckOut = checkInCheckOut2.get();
				newCheckInCheckOut.setCheckoutActionFrom(deviceType);
				newCheckInCheckOut.setCheckoutIpAddress(ip.getHostAddress());
				newCheckInCheckOut.setCheckoutDateTime(dateTime);

				/* Time Duration */
				Date checkInDttm = sdf.parse(checkInCheckOut.getCheckinDateTime());
				Date checkOutDttm = sdf.parse(checkInCheckOut.getCheckoutDateTime());

				Long diff = checkOutDttm.getTime() - checkInDttm.getTime();
				Long hours = diff / (60 * 60 * 1000) % 24;
				Long minutes = diff / (60 * 1000) % 60;

				LOGGER.info("TOTAL TIME :::::" + hours + ":" + minutes);

				String s1 = df.format(hours);
				String s2 = df.format(minutes);

				String hourMinutes = s1 + ":" + s2 + " Hrs";
				newCheckInCheckOut.setTimeDuration(hourMinutes);

				newCheckInCheckOut.setFlag("N");
				LOGGER.info("UPDATE EMP CHECK-OUT SUCCESS:::::::");
				checkInOutRepository.save(newCheckInCheckOut);
			} else {
				checkInCheckOut.setCheckoutActionFrom(deviceType);
				checkInCheckOut.setCheckoutIpAddress(ip.getHostAddress());
				checkInCheckOut.setCheckoutDateTime(dateTime);

				/* Time Duration */
				Date checkInDttm = sdf.parse(checkInCheckOut.getCheckinDateTime());
				Date checkOutDttm = sdf.parse(checkInCheckOut.getCheckoutDateTime());

				Long diff = checkOutDttm.getTime() - checkInDttm.getTime();
				Long hours = diff / (60 * 60 * 1000) % 24;
				Long minutes = diff / (60 * 1000) % 60;

				LOGGER.info("TOTAL TIME :::::" + hours + ":" + minutes);

				String s1 = df.format(hours);
				String s2 = df.format(minutes);

				String hourMinutes = s1 + ":" + s2 + " Hrs";

				checkInCheckOut.setTimeDuration(hourMinutes);
				checkInCheckOut.setFlag("N");
				LOGGER.info("NEW EMP CHECK-OUT SUCCESS:::::::");
				checkInOutRepository.save(checkInCheckOut);
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while set checkout data----- \n" + ExceptionUtils.getStackTrace(e));
		}
		return checkInCheckOut;
	}

	/* Display Total Login Time of Employee by userId */
	@Override
	public List<CheckInCheckOut> getTotalTime(String userId) throws Exception {
		List<CheckInCheckOut> checkInOutList = checkInOutRepository.getCurrentUser(userId);
		try {
			if (checkInOutList != null) {
				return checkInOutList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display current user ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No User record exist ......");
		}
		return new ArrayList<>();
	}

	/* Display Data for Employee Weekly */
	@Override
	public List<CheckInCheckOut> findByDayOfTheWeek(String from, String to, String userId) throws Exception {
		List<CheckInCheckOut> checkInOutList = checkInOutRepository.findByDayOfTheWeek(from, to, userId);
		try {
			if (!checkInOutList.isEmpty()) {
				return checkInOutList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display current user weekly data ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No User record exist ......");
		}
		return new ArrayList<>();
	}

	/* Mail Trigger (If Employee work Hour <= 8 ) */
	@Override
	public List<String> getNineHourNotComplete() throws Exception {
		try {
			List<String> checkInOutList = checkInOutRepository.getNineHourNotComplete();
			if (Objects.nonNull(checkInOutList)) {
				return checkInOutList;
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while display today date data" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No current date user data found ");
		}
		return new ArrayList<>();
	}

	/* Employee Upcoming Birthday */
	@Override
	public List<EmpPersonalDetails> getUpcomingEmpBirthday() throws Exception {
		List<EmpPersonalDetails> personalDetailsList = empPersonalRepository.getUpcomingEmpBirthday();
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
		String userId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();

			EmpBasicDetails basicDetails = empBasicRepository.getCurrentUser(userId);
			final DecimalFormat df = new DecimalFormat("00");

			if (Objects.nonNull(checkInCheckOut)) {

				/* Time Duration */
				SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);

				Date from = myFormat.parse(checkInCheckOut.getCheckinDateTime());
				Date to = myFormat.parse(checkInCheckOut.getCheckoutDateTime());
				long diff = to.getTime() - from.getTime();
				/* Day Calculate */
				// float daysBetween = TimeUnit.MILLISECONDS.toDays(diff) % 365;

				/* Hour & Minute Calculate */
				long hourBetween = TimeUnit.MILLISECONDS.toHours(diff) % 24;
				long minitBetween = TimeUnit.MILLISECONDS.toMinutes(diff) % 60;

				String hours = df.format(hourBetween);
				String minit = df.format(minitBetween);

				String hourMinutes = hours + ":" + minit + " Hrs";

				/* Day of the Week */
				Date now = myFormat.parse(checkInCheckOut.getCheckinDateTime());
				SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");

				/* IpAddress */
				InetAddress ip = InetAddress.getLocalHost();

				/* Enable device detection */
				String deviceType = "";
				if (device.isMobile()) {
					deviceType = "Mobile";
				} else if (device.isTablet()) {
					deviceType = "Tablet";
				} else if (device.isNormal()) {
					deviceType = "Browser";
				}

				Optional<CheckInCheckOut> checkInCheckOut2 = checkInOutRepository
						.getByUserIdAndDate(checkInCheckOut.getDate(), userId);
				if (checkInCheckOut2.isPresent()) {
					CheckInCheckOut newCheckInCheckOut = checkInCheckOut2.get();
					newCheckInCheckOut.setUserId(userId);
					newCheckInCheckOut.setManagerId(basicDetails.getEmpWorkDetails().getReportingManager());
					newCheckInCheckOut.setApprovalReq("Pending");
					newCheckInCheckOut.setDayOfTheWeek(simpleDateformat.format(now));
					newCheckInCheckOut.setTimeDuration(hourMinutes);
					newCheckInCheckOut.setTenantId(basicDetails.getTenantId());
					newCheckInCheckOut.setLastModifiedBy(userId);
					newCheckInCheckOut.setLastModifiedDate(new Date());
					newCheckInCheckOut.setCheckinActionFrom(deviceType);
					newCheckInCheckOut.setCheckoutActionFrom(deviceType);
					newCheckInCheckOut.setCheckinIpAddress(ip.getHostAddress());
					newCheckInCheckOut.setCheckoutIpAddress(ip.getHostAddress());
					newCheckInCheckOut.setIsActive("1");
					newCheckInCheckOut.setFlag("Y");
					newCheckInCheckOut.setStatus("Present");
					newCheckInCheckOut.setAddNewReq("1");
					newCheckInCheckOut.setDate(checkInCheckOut.getDate());
					newCheckInCheckOut.setCheckinDateTime(checkInCheckOut.getCheckinDateTime());
					newCheckInCheckOut.setCheckoutDateTime(checkInCheckOut.getCheckoutDateTime());
					newCheckInCheckOut.setDescription(checkInCheckOut.getDescription());
					newCheckInCheckOut.setReason(checkInCheckOut.getReason());
					newCheckInCheckOut.setCreatedDate(new Date());
					newCheckInCheckOut.setNotification("Unread");

					checkInOutRepository.save(newCheckInCheckOut);
					return newCheckInCheckOut;
				} else {
					checkInCheckOut.setUserId(userId);
					checkInCheckOut.setManagerId(basicDetails.getEmpWorkDetails().getReportingManager());
					checkInCheckOut.setApprovalReq("Pending");
					checkInCheckOut.setDayOfTheWeek(simpleDateformat.format(now));
					checkInCheckOut.setTimeDuration(hourMinutes);
					checkInCheckOut.setTenantId(basicDetails.getTenantId());
					checkInCheckOut.setCreatedBy(userId);
					checkInCheckOut.setCreatedDate(new Date());
					checkInCheckOut.setCheckinActionFrom(deviceType);
					checkInCheckOut.setCheckoutActionFrom(deviceType);
					checkInCheckOut.setCheckinIpAddress(ip.getHostAddress());
					checkInCheckOut.setCheckoutIpAddress(ip.getHostAddress());
					checkInCheckOut.setIsActive("1");
					checkInCheckOut.setFlag("Y");
					checkInCheckOut.setStatus("Present");
					checkInCheckOut.setAddNewReq("1");
					checkInCheckOut.setCreatedDate(new Date());
					checkInCheckOut.setNotification("Unread");

					LOGGER.info("Data Insert Successfully...");
					checkInOutRepository.save(checkInCheckOut);
				}
			}
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
			Optional<CheckInCheckOut> checkInCheckOut2 = checkInOutRepository.findById(checkInCheckOut.getId());
			if (checkInCheckOut2.isPresent()) {
				CheckInCheckOut newcheckInCheckOut2 = checkInCheckOut2.get();
				newcheckInCheckOut2.setIsActive("0");
				checkInOutRepository.save(newcheckInCheckOut2);
			} else {
				checkInOutRepository.save(checkInCheckOut);
			}
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
			return checkInOutRepository.getAttendancePending(userId);
		} catch (Exception e) {
			LOGGER.error("Error occur while display attendance request in manager dashboard" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found " + userId);
		}
	}

	@Override
	public List<String> getDateAndUserId() throws Exception {
		List<String> listOfUserWithDate = checkInOutRepository.getDateAndUserId();
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
			checkInOutRepository.insertEmployee(userId, date, dayOfTheWeek, tenantId, isActive, checkinDateTime,
					checkoutDateTime, timeDuration, status);
		} catch (Exception e) {
			LOGGER.error("Error occur while insert absent employee data " + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found ");
		}
	}

	@Override
	public CheckInCheckOut findByEmpId(String userId) throws Exception {
		CheckInCheckOut checkInCheckOut = checkInOutRepository.findByEmpId(userId);
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
			return checkInOutRepository.getPresentDays(userId);
		} catch (Exception e) {
			LOGGER.error("Error while get present days of employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " + userId);
		}
	}

	@Override
	public long getLateDays(String userId) throws Exception {
		try {
			return checkInOutRepository.getLateDays(userId);
		} catch (Exception e) {
			LOGGER.error("Error while get late days of employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " + userId);
		}
	}

	@Override
	public long getHalfDays(String userId) throws Exception {
		try {
			return checkInOutRepository.getHalfDays(userId);
		} catch (Exception e) {
			LOGGER.error("Error while get half days of employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " + userId);
		}
	}

	@Override
	public long getAbsentDays(String userId) throws Exception {
		try {
			return checkInOutRepository.getAbsentDays(userId);
		} catch (Exception e) {
			LOGGER.error("Error while get absent days of employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " + userId);
		}
	}

	@Override
	public List<CheckInCheckOut> getNewReqList(String empId) throws Exception {
		List<CheckInCheckOut> checkInCheckOut = checkInOutRepository.getNewReqList(empId);
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
		List<CheckInCheckOut> checkInCheckOut = checkInOutRepository.cancelReqList(empId);
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
		CheckInCheckOut checkInCheckOut = checkInOutRepository.sendMail(userId);
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
		List<CheckInCheckOut> checkInCheckOut = checkInOutRepository.getEmpWithManger(empId);
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
		List<CheckInCheckOut> checkInCheckOut = checkInOutRepository.getAcceptLeaveById(id);
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
			checkInOutRepository.acceptStatus(approvalReq, status, isActive, flag, id);
		} catch (Exception e) {
			LOGGER.error("Error occur while save data if manager accept/reject request " + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found ");
		}
	}

	@Override
	public List<CheckInCheckOut> getByUserIdAndCurentDate(List<String> userId) throws Exception {
		List<CheckInCheckOut> checkInCheckOuts = checkInOutRepository.getByUserIdAndCurentDate(userId); 
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
		List<CheckInCheckOut> checkInCheckOuts = checkInOutRepository.getTotalAbsentEmp();
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
		List<CheckInCheckOut> checkInCheckOuts = checkInOutRepository.getTotalAttendance();
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
		List<CheckInCheckOut> checkInCheckOuts = checkInOutRepository.getTotalNewReq();
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
			return checkInOutRepository.getAllAttendance();
		} catch (Exception e) {
			LOGGER.error("Error occuring while get all attendance of employees in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " );
		}
	}

	@Override
	public long getAllEmpAbsent() throws Exception {
		try {
			return checkInOutRepository.getAllEmpAbsent();
		} catch (Exception e) {
			LOGGER.error("Error occuring while get all absent of employees in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " );
		}
	}

	@Override
	public long getAllNewReq() throws Exception {
		try {
			return checkInOutRepository.getAllNewReq();
		} catch (Exception e) {
			LOGGER.error("Error occuring while get all new req of employees in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " );
		}
	}

	@Override
	public List<CheckInCheckOut> getCurrentUser(String userId) throws Exception {
		List<CheckInCheckOut> checkInOutList = checkInOutRepository.getCurrentUser(userId);
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
		CheckInCheckOut checkInOutList = checkInOutRepository.findByDateAndCurrentUser2(userId);
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
			
			Optional<CheckInCheckOut> optional = checkInOutRepository.findById(checkInCheckOut.getId());
			if (optional.isPresent()) {
				CheckInCheckOut checkInOut = optional.get();
				checkInOut.setNotification("Read");
				
				checkInOutRepository.save(checkInOut);
				return checkInOut;
			} else {

			}
		} catch (Exception e) {
			LOGGER.error("Error occur while save notification /n" + ExceptionUtils.getStackTrace(e));
		}
		return checkInCheckOut;
	}
}