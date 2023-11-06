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

import com.ksvsofttech.product.dao.LeaveDao;
import com.ksvsofttech.product.entities.LeaveMst;
import com.ksvsofttech.product.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {
	private static final Logger LOGGER = LogManager.getLogger(LeaveServiceImpl.class);

	@Autowired
	private LeaveDao leaveDao;

	/* Save & Update Leave */
	@Override
	public LeaveMst saveOrUpdateLeave(LeaveMst leaveMst) throws Exception {
		try {
			leaveDao.saveOrUpdateLeave(leaveMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update leave ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No leave save & update......" + leaveMst.toString());
		}
		return leaveMst;
	}

	/* List Of Leaves */
	@Override
	public List<LeaveMst> getLeaveList() throws Exception {
		List<LeaveMst> leaveMst;
		try {
			leaveMst = leaveDao.getLeaveList();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display leave list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No leave record exist......");
		}
		return leaveMst;
	}

	/* Active List Of Leaves */
	@Override
	public List<LeaveMst> getActiveLeave(String empId) throws Exception {
		List<LeaveMst> leaveMst;
		try {
			leaveMst = leaveDao.getActiveLeave(empId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while current emp active leave list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No leave record exist......");
		}
		return leaveMst;
	}

	/* Inactive List Of Leaves */
	@Override
	public List<LeaveMst> getInactiveLeave(String empId) throws Exception {
		List<LeaveMst> leaveMst;
		try {
			leaveMst = leaveDao.getInactiveLeave(empId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while current emp active leave list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No leave record exist......");
		}
		return leaveMst;
	}

	/* Cancel Leave by ID */
	@Override
	public LeaveMst cancelLeave(LeaveMst leaveMst) throws Exception {
		try {
			leaveMst = leaveDao.cancelLeave(leaveMst);
		} catch (Exception e) {
			LOGGER.error("------Error while cancel leave ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No leave cancel......");
		}
		return leaveMst;
	}

	/* Leave Apply Mail Send Project Manager & HR */
	@Override
	public List<LeaveMst> getLeaveApply(String empId) throws Exception {
		List<LeaveMst> leaveMst;
		try {
			leaveMst = leaveDao.getLeaveApply(empId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while send mail to leave apply ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee exist......");
		}
		return leaveMst;
	}

	/* Active Leave List for acceptLeave */
	@Override
	public List<LeaveMst> getRejectLeave(String empId) throws Exception {
		List<LeaveMst> leaveMst;
		try {
			leaveMst = leaveDao.getRejectLeave(empId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display active leave ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee exist......");
		}
		return leaveMst;
	}

	/* Active Leave by Id List for acceptLeave */
	@Override
	public List<LeaveMst> getAcceptLeaveById(Long id) throws Exception {
		List<LeaveMst> leaveMst;
		try {
			leaveMst = leaveDao.getAcceptLeaveById(id);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display active leave ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee exist......");
		}
		return leaveMst;
	}
	
	/* acceptLeave by manager */
	@Override
	public void acceptStatus(String status, String flag, Long id) throws Exception {
		try {
			leaveDao.acceptStatus(status, flag, id);
		} catch (Exception e) {
			LOGGER.error("------Error occur while acceptLeave ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee exist......");
		}
	}

	/* Get Leave List By Leave Type */
	@Override
	public List<LeaveMst> getLeaveByLeaveType(String leaveType) throws Exception {
		List<LeaveMst> leaveMst;
		try {
			leaveMst = leaveDao.getLeaveByLeaveType(leaveType);
		} catch (Exception e) {
			LOGGER.error("------Error occur while get Apply leave list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No apply leave found......");
		}
		return leaveMst;
	}

	/* Get Leave List of Emp with Manager */
	@Override
	public List<LeaveMst> getEmpWithManger(String empId) throws Exception {
		List<LeaveMst> leaveMst;
		try {
			leaveMst = leaveDao.getEmpWithManger(empId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display  list if emp  with managerId------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee found......");
		}
		return leaveMst;
	}

	/* Display Employee Leave request in Manager Dashboard */
	@Override
	public List<LeaveMst> getLeavePending(String userId) throws Exception {
		List<LeaveMst> leaveMst;
		try {
			leaveMst = leaveDao.getLeavePending(userId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display leave request in manager dashboard ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee found...... " +userId);
		}
		return leaveMst;
	}
	@Override
	public List<LeaveMst> onLeaveToday() throws Exception {
		List<LeaveMst> leaveMst = leaveDao.onLeaveToday();
		try {
			if (Objects.nonNull(leaveMst)) {
				return leaveMst;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display employee today leave on dashboard ------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee found...... ");
		}
		return leaveMst;
	}

	/* Employee leave Find by id */
	@Override
	public Optional<LeaveMst> findLeaveById(Long id) throws Exception {
		Optional<LeaveMst> optional =  leaveDao.findLeaveById(id);
		try {
			if (optional.isPresent()) {
				return optional;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display find employee leave by id ------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No leave found...... " + id);
		}
		return optional;
	}
	
	@Override
	public List<LeaveMst> getTotalLeave() throws Exception {
		List<LeaveMst> leaveMsts = leaveDao.getTotalLeave();
		try {
			if (Objects.nonNull(leaveMsts)) {
				return leaveMsts;
			} else {
				System.out.println("Null Data Get :::::::: ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while get list of total leave " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}
	
	@Override
	public List<LeaveMst> getTotalPendingLeave() throws Exception {
		List<LeaveMst> leaveMsts = leaveDao.getTotalPendingLeave();
		try {
			if (Objects.nonNull(leaveMsts)) {
				return leaveMsts;
			} else {
				System.out.println("Null Data Get :::::::: ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while get list of total pending leave " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public long getLeaveDays(String userId) throws Exception {
		try {
			return leaveDao.getLeaveDays(userId);
		} catch (Exception e) {
			LOGGER.error("Error while get leave count(days) of employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " + userId);
		}
	}

	@Override
	public long getAllLeaves() throws Exception {
		try {
			return leaveDao.getAllLeaves();
		} catch (Exception e) {
			LOGGER.error("Error while get all leave days of employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... ");
		}
	}

	@Override
	public long getAllPendingLeaves() throws Exception {
		try {
			return leaveDao.getAllPendingLeaves();
		} catch (Exception e) {
			LOGGER.error("Error while get all pending leave days of employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... ");
		}
	}
	
	@Override
	public LeaveMst notificationRead(LeaveMst leaveMst) throws Exception {
		try {
			leaveDao.notificationRead(leaveMst);
		} catch (Exception e) {
			LOGGER.error("Error occur while save notification /n" + ExceptionUtils.getStackTrace(e));
		}
		return leaveMst;
	}
}