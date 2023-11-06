package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.ExitActivityDao;
import com.ksvsofttech.product.entities.ExitActivity;
import com.ksvsofttech.product.service.ExitActivityService;

@Service
public class ExitActivityServiceImpl implements ExitActivityService {
	private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private ExitActivityDao exitActivityDao;

	@Override
	public List<ExitActivity> isActiveExitActivity(String empId) throws Exception {
		List<ExitActivity> exitList = new ArrayList<>();
		try {
			exitList = exitActivityDao.isActiveExitActivity(empId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive exit list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No isactive exit record exist......");	
			}
		return exitList;
	}	
	
	@Override
	public List<ExitActivity> cancelExitActivity(String empId) throws Exception {
		List<ExitActivity> exitList = new ArrayList<>();
		try {
			exitList = exitActivityDao.cancelExitActivity(empId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display inactive exit list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No inactive exit record exist......");
		}
		return exitList;
	}
	
	@Override
	public List<ExitActivity> getAllEmp() throws Exception {
		List<ExitActivity> exitActivity;
		try {
			exitActivity = exitActivityDao.getAllEmp();
		} catch (Exception e) {
			LOGGER.error("------Error occur while get Employee list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No getAll employee record exist ......");
		}
		return exitActivity;
	}
	
	/* For Save ExitActivity */
	@Override
	public ExitActivity saveExitActivity(ExitActivity exitActivity) throws Exception {
		try {
			this.exitActivityDao.saveExitActivity(exitActivity);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save Exit Activity------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record save ......" + exitActivity);		}
		
		return exitActivity;
	}

	/*Deactive Registered ExitActivity*/
	@Override
	public ExitActivity cancelById(ExitActivity exitActivity) throws Exception {
		try {
			exitActivity = exitActivityDao.cancelById(exitActivity);
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactivate registered exit activity ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No registered exit activity deactivate......" + exitActivity);	
		}
		return exitActivity;
	}

	@Override
	public List<ExitActivity> getExitActivityById(String empId) throws Exception {
		try {
			return exitActivityDao.getExitActivityById(empId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while get exit activity by empId -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + empId);
		}
	}

	@Override
	public List<ExitActivity> getEmpWithManger(String empId) throws Exception {
		try {
			return exitActivityDao.getEmpWithManger(empId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while display exit activity to manager -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + empId);
		}
	}

	@Override
	public void acceptStatus(String status, String flag, Long id) throws Exception {
		try {
			exitActivityDao.acceptStatus(status, flag, id);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while update exit activity to manager approved/Reject request -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + id);
		}		
	}

	@Override
	public List<ExitActivity> acceptExitActivityById(Long id) throws Exception {
		try {
			return exitActivityDao.acceptExitActivityById(id);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while get exit activity by Id -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + id);
		}		
	}

	@Override
	public List<ExitActivity> getEmpWithMangerWithPending(String userId) throws Exception {
		try {
			return exitActivityDao.getEmpWithMangerWithPending(userId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while display exit activity to manager with pending status -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + userId);
		}
	}
	@Override
	public List<ExitActivity> getTotalExitActivity() throws Exception {
		List<ExitActivity> exitActivities = exitActivityDao.getTotalExitActivity();
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
			return exitActivityDao.getAllExitActivity();
		} catch (Exception e) {
			LOGGER.error("Error occuring while get all exit activity of employees in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " );
		}
	}

	@Override
	public ExitActivity notificationRead(ExitActivity exitActivity) throws Exception {
		try {
			exitActivityDao.notificationRead(exitActivity);
		} catch (Exception e) {
			LOGGER.error("Error occur while save notification /n" + ExceptionUtils.getStackTrace(e));
		}
		return exitActivity;
	}
}