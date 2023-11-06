package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.UserDao;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	/* List Of IsActive Users */
	@Override
	public List<UserMst> getIsActiveUsers() throws Exception {
		List<UserMst> userMst;
		try {
			userMst = userDao.getIsActiveUsers();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display active user list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No isactive user record exist......");
		}
		return userMst;
	}

	/* List Of InActive Users */
	@Override
	public List<UserMst> getInActiveUsers() throws Exception {
		List<UserMst> userMst;
		try {
			userMst = userDao.getInActiveUsers();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display inactive role list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No inactive user record exist......");
		}
		return userMst;
	}

	/* Save & Update User */
	@Override
	public UserMst saveOrUpdateUser(UserMst userMst) throws Exception {
		try {
			userDao.saveOrUpdateUser(userMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update user------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No user save & update......" + userMst);
		}
		return userMst;
	}

	/* get User by Id */
	@Override
	public UserMst getUserById(String loginId) throws Exception {
		UserMst userMst = new UserMst();
		try {
			userMst = userDao.getUserById(loginId);
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while find user record for given id------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No user record exist for given id....." + loginId);
		}
		return userMst;
	}

	/* For Login Page */
	@Override
	public UserMst getUserDetails(String loginId) throws Exception {
		UserMst userMst = new UserMst();
		try {
			userMst = userDao.getUserDetails(loginId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while find login id ------");
			throw new Exception("No user available for this login id");
		}
		return userMst;
	}

	/* For Deactivate User */
	@Override
	public UserMst deactiveUser(UserMst userMst) throws Exception {
		try {
			userDao.deactiveUser(userMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactive user------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No User deactivate......" + userMst);
		}
		return userMst;
	}

	/* For Activate User */
	@Override
	public UserMst activateUser(UserMst userMst) throws Exception {
		try {
			userDao.activateUser(userMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while active user------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No User activate......" + userMst);
		}
		return userMst;
	}

	/* Password Check */
	@Override
	public Optional<UserMst> getPasswordCheck(String password) throws Exception {
		try {
			return userDao.getPasswordCheck(password);
		} catch (Exception e) {
			LOGGER.error("------Error occur while password check------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No User passowrd exist......");
		}

	}

	@Override
	public boolean userExists(String password) throws Exception {
		try {
			return getPasswordCheck(password).isPresent();
		} catch (Exception e) {
			LOGGER.error("------Error occur while User check------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No User exist......");
		}

	}

	/* Login ID Verify */
	@Override
	public Optional<UserMst> findLoginByLoginId(String loginId) throws Exception {
		try {
			return userDao.findLoginByLoginId(loginId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while find login id ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No login id exist......");
		}

	}

	@Override
	public boolean loginIdExists(String loginId) throws Exception {
		try {
			return findLoginByLoginId(loginId).isPresent();
		} catch (Exception e) {
			LOGGER.error("------Error occur while find login id ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No login id exist......");
		}

	}
	
	@Override
	public void resetPassword(String password, String passwordToken, String emailId) throws Exception {
		try {
			userDao.resetPassword(password, passwordToken, emailId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while reset password ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}

	}

	@Override
	public void setRandomOTP(int randomOTP, String emailId) throws Exception {
		try {
			userDao.setRandomOTP(randomOTP, emailId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save rendom number ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}

	}

	@Override
	public List<EmpBasicDetails> getSearchEmpByIdAndFullName(String value) throws Exception {
		try {
			List<EmpBasicDetails> basicDetailsList = userDao.getSearchEmpByIdAndFullName(value);
			if (!basicDetailsList.isEmpty()) {
				return basicDetailsList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while search employee by id and name ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("EmpBasicDetails record not exist......");
		}
		return new ArrayList<>();
	}
}
