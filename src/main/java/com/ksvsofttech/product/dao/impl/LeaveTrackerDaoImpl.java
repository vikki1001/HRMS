package com.ksvsofttech.product.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.LeaveTrackerDao;
import com.ksvsofttech.product.entities.EmpPersonalDetails;
import com.ksvsofttech.product.entities.LeaveTracker;
import com.ksvsofttech.product.repository.EmpPersonalRepository;
import com.ksvsofttech.product.repository.LeaveTrackerRepository;

@Repository
public class LeaveTrackerDaoImpl implements LeaveTrackerDao {
	private static final Logger LOGGER = LogManager.getLogger(LeaveTrackerDaoImpl.class);

	@Autowired
	private LeaveTrackerRepository leaveTrackerRepository;

	@Autowired
	private EmpPersonalRepository empPersonalRepository;

	@Override
	public List<LeaveTracker> getEmpDetails(String empId) throws Exception {
		List<LeaveTracker> leaveTracker;
		try {
			leaveTracker = leaveTrackerRepository.getEmpDetails(empId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while find emp id ------");
			throw new Exception("No user available for this emp id");
		}
		return leaveTracker;
	}

	/* Matenity Leave for Married Employee Only */
	@Override
	public EmpPersonalDetails getMarriedOnly(String empId) throws Exception {
		EmpPersonalDetails empPersonalDetails;
		try {
			empPersonalDetails = empPersonalRepository.getMarriedOnly(empId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while find married employee ------");
			throw new Exception("No user available for this emp id");
		}
		return empPersonalDetails;
	}

	/* Update Employee Leave Apply Time */
	@Override
	public LeaveTracker updateLeave(LeaveTracker leaveTracker) throws Exception {
		try {
			if (Objects.nonNull(leaveTracker)) {
				return leaveTrackerRepository.save(leaveTracker);
			}
		} catch (Exception e) {
			LOGGER.error("------ Error occur while update leave ------");
			throw new Exception("Not update leave");
		}
		return leaveTracker;
	}

	/* Find Employee leave details by employee id */
	@Override
	public Optional<LeaveTracker> findByEmpId(String empId) throws Exception {
		Optional<LeaveTracker> optional = leaveTrackerRepository.findByEmpId(empId);
		try {
			if (optional.isPresent()) {
				return optional;
			}
		} catch (Exception e) {
			LOGGER.error("------ Error occur while find employee & employee details ------");
			throw new Exception("No employee record " + empId);
		}
		return optional;
	}

	@Override
	public List<LeaveTracker> getActiveList() throws Exception {
		List<LeaveTracker> leaveTracker = leaveTrackerRepository.getActiveList();
		try {
			if (!leaveTracker.isEmpty()) {
				return leaveTracker;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display daily add leave list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
		return new ArrayList<>();
	}

	@Override
	public List<LeaveTracker> getInActiveList() throws Exception {
		List<LeaveTracker> leaveTracker = leaveTrackerRepository.getInActiveList();
		try {
			if (!leaveTracker.isEmpty()) {
				return leaveTracker;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display daily add leave list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
		return new ArrayList<>();
	}

	@Override
	public LeaveTracker save(LeaveTracker leaveTracker) throws Exception {
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			if (Objects.isNull(leaveTracker.getId())) {
				leaveTracker.setBookedTotalLeave((float) 0.0);
				leaveTracker.setBookedPaidLeave((float) 0.0);
				leaveTracker.setBookedMaternityLeave(((float) 0.0));
				leaveTracker.setIsActive("1");
				leaveTracker.setTenantId(null);
				leaveTracker.setCreatedDate(new Date());
				leaveTracker.setCreatedBy(empId);
				leaveTrackerRepository.save(leaveTracker);
			}
//			else {
//				Optional<LeaveTracker> leaveTrackers = leaveTrackerRepository.findById(leaveTracker.getId());
//				if (leaveTrackers.isPresent()) {
//					LeaveTracker tracker = leaveTrackers.get();
//					tracker.setTotalLeave(leaveTracker.getTotalLeave());
//					tracker.setPaidLeave(leaveTracker.getPaidLeave());
//					tracker.setMaternityLeave(leaveTracker.getMaternityLeave());
//					tracker.setAddLeave(leaveTracker.getAddLeave());
//					tracker.setLeaveType(leaveTracker.getLeaveType());
//					tracker.setLastModifiedBy(empId);
//					tracker.setLastModifiedDate(new Date());
//					leaveTrackerRepository.save(tracker);
//				} else {
//					return leaveTracker;
//				}
//			}
		} catch (Exception e) {
			LOGGER.error("------ Error occur while save Add Leave ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record save......");
		}
		return leaveTracker;
	}

	@Override
	public LeaveTracker cancelAddLeave(LeaveTracker leaveTracker) throws Exception {
		try {
			Optional<LeaveTracker> optional = leaveTrackerRepository.findById(leaveTracker.getId());
			if (optional.isPresent()) {
				LeaveTracker tracker = optional.get();
				tracker.setIsActive(leaveTracker.getIsActive());
				tracker.setIsActive("0");

				leaveTrackerRepository.save(leaveTracker);
			} else {
				return leaveTracker;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while in cancel work report------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No work add leave cancel......");
		}
		return leaveTracker;
	}

	@Override
	public Optional<LeaveTracker> getEmpDetailsById(Long id) throws Exception {
		Optional<LeaveTracker> optional = leaveTrackerRepository.getEmpDetailsById(id);
		try {
			if (optional.isPresent()) {
				return optional;
			}
		} catch (Exception e) {
			LOGGER.error("------ Error occur while find employee & employee details ------");
			throw new Exception("No employee record ");
		}
		return optional;
	}

	@Override
	public List<String> getAllEmp() throws Exception {
		List<String> getAllEmp = leaveTrackerRepository.getAllEmp();
		try {
			if (Objects.nonNull(getAllEmp)) {
				return getAllEmp;
			}
		} catch (Exception e) {
			LOGGER.error("------ Error occur while getAllEmp ------");
		}
		return new ArrayList<>();

	}

	@Override
	public List<Float> getAllEmpTotalLeave() throws Exception {
		List<Float> getAllEmpTotalLeave = leaveTrackerRepository.getAllEmpTotalLeave();
		try {
			if (Objects.nonNull(getAllEmpTotalLeave)) {
				return getAllEmpTotalLeave;
			}
		} catch (Exception e) {
			LOGGER.error("------ Error occur while getAllEmpTotalLeave  ------");
		}
		return new ArrayList<>();
	}

	@Override
	public void updateTotalLeave(float totalleave, String empId) throws Exception {
		try {
			leaveTrackerRepository.updateTotalLeave(totalleave, empId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while updateTotalLeave ------");
			throw new Exception("No record exist.. ");
		}
	}

	@Override
	public List<Float> getAllEmpPaidLeave() throws Exception {
		List<Float> getAllEmpPaidLeave = leaveTrackerRepository.getAllEmpPaidLeave();
		try {
			if (Objects.nonNull(getAllEmpPaidLeave)) {
				return getAllEmpPaidLeave;
			}
		} catch (Exception e) {
			LOGGER.error("------ Error occur while getAllEmpPaidLeave ------");
		}
		return new ArrayList<>();
	}

	@Override
	public void updatePaidLeave(float paidleave, String empId) throws Exception {
		try {
			leaveTrackerRepository.updatePaidLeave(paidleave, empId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while updatePaidLeave ------");
			throw new Exception("No record exist.. ");
		}
	}

	@Override
	public List<Float> getAllEmpMaternityLeave() throws Exception {
		List<Float> getAllEmpMaternityLeave = leaveTrackerRepository.getAllEmpMaternityLeave();
		try {
			if (Objects.nonNull(getAllEmpMaternityLeave)) {
				return getAllEmpMaternityLeave;
			}
		} catch (Exception e) {
			LOGGER.error("------ Error occur while getAllEmpMaternityLeave ------");
			throw new Exception("No record exist.. ");
		}
		return new ArrayList<>();
	}

	@Override
	public void updateMaternityLeave(float maternityLeave, String empId) throws Exception {
		try {
			leaveTrackerRepository.updateMaternityLeave(maternityLeave, empId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while updateMaternityLeave ------");
			throw new Exception("No record exist.. ");
		}
	}

	@Override
	public List<String> getMarriedEmployee() throws Exception {
		List<String> getMarriedEmployee = empPersonalRepository.getMarriedEmployee();
		try {
			if (Objects.nonNull(getMarriedEmployee)) {
				return getMarriedEmployee;
			}
		} catch (Exception e) {
			LOGGER.error("------ Error occur while getMarriedEmployee ------");
			throw new Exception("No record exist.. ");
		}
		return new ArrayList<>();
	}
}