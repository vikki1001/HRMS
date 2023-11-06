package com.ksvsofttech.product.dao.impl;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ksvsofttech.product.dao.ReportDao;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.LeaveMst;
import com.ksvsofttech.product.repository.EmpBasicRepository;
import com.ksvsofttech.product.repository.LeaveRepository;

@Controller
public class ReportDaoImpl implements ReportDao {
	private static final Logger LOG = LogManager.getLogger(ReportDaoImpl.class);

	@Autowired
	private EmpBasicRepository basicRepository;
	
	@Autowired
	private LeaveRepository leaveRepository;
		
	@Override
	public List<EmpBasicDetails> getBySearch(String empId, String fullName, String departName,
			String grade) throws Exception {
		List<EmpBasicDetails> empBasicDetailsList =  basicRepository.getBySearch(empId, fullName, departName, grade);
		try {
			if (!empBasicDetailsList.isEmpty()) {
				return empBasicDetailsList;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while display getDepartIdorNameorempIdorGrade EmpBasicDetails list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No EmpBasicDetails record exist ......");
		}
		return empBasicDetailsList;
	}
		
	@Override
	public List<LeaveMst> activeLeave() throws Exception {
		List<LeaveMst> leaveMsts = leaveRepository.activeLeave();
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
		List<LeaveMst> leaveMsts = leaveRepository.getApprovedLeave();
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
		List<LeaveMst> leaveMsts = leaveRepository.getApprovedAndLeaveType(leaveType);
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