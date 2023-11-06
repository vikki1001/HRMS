package com.ksvsofttech.product.service.Impl;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.ReportDao;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.LeaveMst;
import com.ksvsofttech.product.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	private static final Logger LOG = LogManager.getLogger(ReportServiceImpl.class);

	@Autowired
	private ReportDao reportDao;

	@Override
	public List<EmpBasicDetails> getBySearch(String empId, String fullName, String departName, String grade)
			throws Exception {
		List<EmpBasicDetails> empBasicDetailsList = reportDao.getBySearch(empId, fullName, departName, grade);
		try {
			if (!empBasicDetailsList.isEmpty()) {
				return empBasicDetailsList;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while display getDepartIdorNameorempIdorGrade EmpBasicDetails list------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No  EmpBasicDetails record exist......");
		}
		return empBasicDetailsList;
	}

	@Override
	public List<LeaveMst> activeLeave() throws Exception {
		List<LeaveMst> leaveMsts = reportDao.activeLeave();
		try {
			if (!leaveMsts.isEmpty()) {
				return leaveMsts;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while display all active leaves ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
		return leaveMsts;
	}

	@Override
	public List<LeaveMst> getApprovedLeave() throws Exception {
		List<LeaveMst> leaveMsts = reportDao.getApprovedLeave();
		try {
			if (!leaveMsts.isEmpty()) {
				return leaveMsts;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while display approved leaves ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
		return leaveMsts;
	}

	@Override
	public List<LeaveMst> getApprovedAndLeaveType(String leaveType) throws Exception {
		List<LeaveMst> leaveMsts = reportDao.getApprovedAndLeaveType(leaveType);
		try {
			if (!leaveMsts.isEmpty()) {
				return leaveMsts;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while get leave by type of leave ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
		return leaveMsts;
	}

}
