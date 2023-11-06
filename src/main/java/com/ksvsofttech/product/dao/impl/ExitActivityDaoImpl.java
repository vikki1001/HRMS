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

import com.ksvsofttech.product.dao.ExitActivityDao;
import com.ksvsofttech.product.entities.ExitActivity;
import com.ksvsofttech.product.repository.ExitActivityRepository;

@Repository
public class ExitActivityDaoImpl implements ExitActivityDao {
	private static final Logger LOGGER = LogManager.getLogger(ExitActivityDaoImpl.class);

	@Autowired
	private ExitActivityRepository exitActivityRepository;

	/* List of IsActive Activity */
	@Override
	public List<ExitActivity> isActiveExitActivity(String empId) throws Exception {
		List<ExitActivity> exitList = exitActivityRepository.isActiveExitActivity(empId);
		try {
			if (!exitList.isEmpty()) {
				return exitList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive exit list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No exit record exist ......");
		}
		return new ArrayList<>();
	}

	/* List of InActive Activity */
	@Override
	public List<ExitActivity> cancelExitActivity(String empId) throws Exception {
		List<ExitActivity> exitList = exitActivityRepository.cancelExitActivity(empId);
		try {
			if (!exitList.isEmpty()) {
				return exitList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display inactive exit list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No exit record exist ......");
		}
		return new ArrayList<>();
	}

	@Override
	public List<ExitActivity> getAllEmp() throws Exception {
		List<ExitActivity> exitActivity = exitActivityRepository.findAll();
		try {
			if (!exitActivity.isEmpty()) {
				return exitActivity;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while get Employee list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No getAll employee record exist ......");
		}

		return exitActivity;
	}

	/* For Save Exit Activity */
	@Override
	public ExitActivity saveExitActivity(ExitActivity exitActivity) throws Exception {
		String empId;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		empId = authentication.getName();
		try {
			if (Objects.isNull(exitActivity.getId())) {
				exitActivity.setIsActive("1");
				exitActivity.setManagerId(exitActivity.getEmpId().getEmpWorkDetails().getReportingManager());
				exitActivity.setStatus("Pending");
				exitActivity.setNotification("Unread");
				exitActivity.setCreatedDate(new Date());
				exitActivity.setCreatedBy(empId);
				exitActivityRepository.save(exitActivity);
			} else {
				return exitActivity;
			}
		} catch (Exception e) {
			LOGGER.error("------ Error occur while save Exit Activity ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record save......" + exitActivity.getEmpId().getEmpId());
		}
		return exitActivity;
	}

	/* Deactive Registered ExitActivity */
	@Override
	public ExitActivity cancelById(ExitActivity exitActivity) throws Exception {
		try {
			Optional<ExitActivity> optional = exitActivityRepository.cancelById(exitActivity.getId());
			if (optional.isPresent()) {
				ExitActivity newExitActivity = optional.get();
				newExitActivity.setIsActive(exitActivity.getIsActive());
				newExitActivity.setIsActive("0");

				exitActivityRepository.save(newExitActivity);
				return newExitActivity;
			} else {
				return exitActivity;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactivate registered exit activity ------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No registered exit activity deactivate......" + exitActivity.getId());
		}
	}

	@Override
	public List<ExitActivity> getExitActivityById(String empId) throws Exception {
		try {
			return exitActivityRepository.getExitActivityById(empId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while get exit activity by empId -----" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + empId);
		}
	}

	@Override
	public List<ExitActivity> getEmpWithManger(String empId) throws Exception {
		try {
			return exitActivityRepository.getEmpWithManger(empId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while display exit activity to manager -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + empId);
		}
	}

	@Override
	public void acceptStatus(String status, String flag, Long id) throws Exception {
		try {
			exitActivityRepository.acceptStatus(status, flag, id);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while update exit activity to manager approved/Reject request -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + id);
		}
	}

	@Override
	public List<ExitActivity> acceptExitActivityById(Long id) throws Exception {
		try {
			return exitActivityRepository.acceptExitActivityById(id);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while get exit activity by Id -----" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + id);
		}
	}

	@Override
	public List<ExitActivity> getEmpWithMangerWithPending(String userId) throws Exception {
		try {
			return exitActivityRepository.getEmpWithMangerWithPending(userId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while display exit activity to manager with pending status -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + userId);
		}
	}

	@Override
	public List<ExitActivity> getTotalExitActivity() throws Exception {
		List<ExitActivity> exitActivities = exitActivityRepository.getTotalExitActivity();
		try {
			if (Objects.nonNull(exitActivities)) {
				return exitActivities;
			} else {
				System.out.println("Null Data Get :::::::: ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while get list of total exit activity " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public long getAllExitActivity() throws Exception {
		try {
			return exitActivityRepository.getAllExitActivity();
		} catch (Exception e) {
			LOGGER.error("Error occuring while get all exit activity of employees in current month "
					+ ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... ");
		}
	}

	@Override
	public ExitActivity notificationRead(ExitActivity exitActivity) throws Exception {
		try {
			
			Optional<ExitActivity> optional = exitActivityRepository.findById(exitActivity.getId());
			if (optional.isPresent()) {
				ExitActivity activity = optional.get();
				activity.setNotification("Read");
				
				exitActivityRepository.save(activity);
				return activity;
			} else {

			}
		} catch (Exception e) {
			LOGGER.error("Error occur while save notification /n" + ExceptionUtils.getStackTrace(e));
		}
		return exitActivity;
	}
}
