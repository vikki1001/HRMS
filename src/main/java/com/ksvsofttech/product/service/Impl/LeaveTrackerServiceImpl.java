package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.LeaveTrackerDao;
import com.ksvsofttech.product.entities.EmpPersonalDetails;
import com.ksvsofttech.product.entities.LeaveTracker;
import com.ksvsofttech.product.service.LeaveTrackerService;

@Service
public class LeaveTrackerServiceImpl implements LeaveTrackerService {
	private static final Logger LOGGER = LogManager.getLogger(LeaveTrackerServiceImpl.class);

	@Autowired
	private LeaveTrackerDao leaveTrackerDao;

	@Override
	public List<LeaveTracker> getEmpDetails(String empId) throws Exception {
		List<LeaveTracker> leaveTracker;
		try {
			leaveTracker = leaveTrackerDao.getEmpDetails(empId);
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
			empPersonalDetails = leaveTrackerDao.getMarriedOnly(empId);
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
				return leaveTrackerDao.updateLeave(leaveTracker);
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
		Optional<LeaveTracker> optional = leaveTrackerDao.findByEmpId(empId);
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
		List<LeaveTracker> leaveTracker = leaveTrackerDao.getActiveList();
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
		List<LeaveTracker> leaveTracker = leaveTrackerDao.getInActiveList();
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
	public LeaveTracker cancelAddLeave(LeaveTracker leaveTracker) throws Exception {
		try {
			leaveTrackerDao.cancelAddLeave(leaveTracker);
		} catch (Exception e) {
			LOGGER.error("------Error occur while cancel work report------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No branch deactivate......");
		}
		return leaveTracker;
	}

	@Override
	public LeaveTracker save(LeaveTracker leaveTracker) throws Exception {
		try {
			if (Objects.nonNull(leaveTracker)) {
				return leaveTrackerDao.save(leaveTracker);
			}
		} catch (Exception e) {
			LOGGER.error("------ Error occur while update leave ------");
			throw new Exception("Not update leave");
		}
		return leaveTracker;
	}

	@Override
	public Optional<LeaveTracker> getEmpDetailsById(Long id) throws Exception {
		Optional<LeaveTracker> optional = leaveTrackerDao.getEmpDetailsById(id);
		try {
			if (optional.isPresent()) {
				return optional;
			}
		} catch (Exception e) {
			LOGGER.error("------ Error occur while find all employee  ------");
			throw new Exception("No employee record ");
		}
		return optional;
	}

	@Override
	public List<String> getAllEmp() throws Exception {
		List<String> getAllEmp = leaveTrackerDao.getAllEmp();
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
		List<Float> getAllEmpTotalLeave = leaveTrackerDao.getAllEmpTotalLeave();
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
			leaveTrackerDao.updateTotalLeave(totalleave, empId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while updateTotalLeave ------");
			throw new Exception("No record exist.. ");
		}
	}

	@Override
	public List<Float> getAllEmpPaidLeave() throws Exception {
		List<Float> getAllEmpPaidLeave = leaveTrackerDao.getAllEmpPaidLeave();
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
			leaveTrackerDao.updatePaidLeave(paidleave, empId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while updatePaidLeave ------");
			throw new Exception("No record exist.. ");
		}
	}

	@Override
	public List<Float> getAllEmpMaternityLeave() throws Exception {
		List<Float> getAllEmpMaternityLeave = leaveTrackerDao.getAllEmpMaternityLeave();
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
			leaveTrackerDao.updateMaternityLeave(maternityLeave, empId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while updateMaternityLeave ------");
			throw new Exception("No record exist.. ");
		}
	}

	@Override
	public List<String> getMarriedEmployee() throws Exception {
		List<String> getMarriedEmployee = leaveTrackerDao.getMarriedEmployee();
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