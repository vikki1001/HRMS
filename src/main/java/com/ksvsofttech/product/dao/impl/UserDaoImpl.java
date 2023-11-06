package com.ksvsofttech.product.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.UserDao;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.repository.EmpBasicRepository;
import com.ksvsofttech.product.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {
	private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmpBasicRepository empBasicRepository;

	/* List Of IsActive Users */
	@Override
	public List<UserMst> getIsActiveUsers() throws Exception {
		List<UserMst> userList = userRepository.getIsActive();
		try {
			if (!userList.isEmpty()) {
				return userList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display users list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No user record exist......");
		}
		return new ArrayList<>();
	}

	/* List Of InActive Users */
	@Override
	public List<UserMst> getInActiveUsers() throws Exception {
		List<UserMst> list = userRepository.getInActive();
		try {
			if (!list.isEmpty()) {
				return list;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while Inactive list not display------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No inactive user exist.....");
		}
		return new ArrayList<>();
	}

	/* Save & Update User */
	@Override
	public UserMst saveOrUpdateUser(UserMst userMst) throws Exception {
		String loginId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			loginId = authentication.getName();
			Optional<UserMst> user2 = userRepository.findById(userMst.getLoginId());
			if (user2.isPresent()) {
				UserMst newUserMst = user2.get();
				newUserMst.setLoginType(userMst.getLoginType());
				newUserMst.setTitle(userMst.getTitle());
				newUserMst.setFirstName(userMst.getFirstName());
				newUserMst.setUserMiddleName(userMst.getUserMiddleName());
				newUserMst.setLastName(userMst.getLastName());
				newUserMst.setUserDisplayName(userMst.getUserDisplayName());
				newUserMst.setGender(userMst.getGender());
				newUserMst.setDob(userMst.getDob());
				newUserMst.setEmailId(userMst.getEmailId().toLowerCase());
				newUserMst.setMobileNo1(userMst.getMobileNo1());
				newUserMst.setBaseBranch(userMst.getBaseBranch());
				newUserMst.setMainRole(userMst.getMainRole());
				newUserMst.setEscalationManager(userMst.getEscalationManager());
				newUserMst.setSecondaryUser(userMst.getSecondaryUser());
				newUserMst.setUserGroup(userMst.getUserGroup());
				newUserMst.setTenantId(userMst.getTenantId());
				newUserMst.setBranch(userMst.getBranch());
				newUserMst.setDepartment(userMst.getDepartment());				
				newUserMst.setLastModifieDate(new Date());
				newUserMst.setLastModifiedBy(loginId);
				newUserMst.setIsUserLocked(userMst.getIsUserLocked());
				newUserMst.setIsUserLoggedIn(0);

				userRepository.save(newUserMst);
				return newUserMst;
			} else {
				userMst.setAllowSwitchingUserYn(0);
				userMst.setAdsAutoLoginYn(0);
				userMst.setAllowConCurrentLogin(0);
				userMst.setAppLoginYn(0);
				userMst.setConsequetiveBadLoginCount(0);
				userMst.setForceMinLoginFreqDays(0);
				userMst.setForceMinLoginReqYn(0);
				userMst.setForcePwdChangeYn(0);
				userMst.setForcePwdChgDays(0);
				userMst.setIdAmlUser(0);
				userMst.setIsAdminFlag(0);
				userMst.setIsChecker(0);
				userMst.setIsLoginSuspended(0);
				userMst.setIsMaker(0);
				userMst.setLoginSessionActive(0);
				userMst.setUserFirstNameOnPhotoId(0);
				userMst.setIsActive(1);
				userMst.setCreatedDate(new Date());
				userMst.setCreatedBy(loginId);
				userMst.setIsUserLoggedIn(0);
				userMst.setEmailId(userMst.getEmailId().toLowerCase());

				/* Encrypt the Password */
				String encodedPassword = bCryptPasswordEncoder.encode(userMst.getPassword());
				userMst.setPassword(encodedPassword);
				/* Save token to database */
				userMst.getPasswordToken();
				userMst.setPasswordToken(UUID.randomUUID().toString());

				this.userRepository.save(userMst);
				return userMst;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update user------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No user save & update......" + userMst);
		}
	}

	/* get User by Id */
	@Override
	public UserMst getUserById(String loginId) throws Exception {
		Optional<UserMst> optional = userRepository.findById(loginId);
		UserMst userMst = null;
		try {
			if (optional.isPresent()) {
				userMst = optional.get();
			} else {
				return userMst;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while find user record for given id------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No user record exist for given id....." + loginId);
		}
		return userMst;
	}

	/* For Login Page */
	@Override
	public UserMst getUserDetails(String loginId) throws Exception {
		UserMst userDetail = new UserMst();
		try {
			userDetail = userRepository.getUserDetails(loginId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while find login id ------");
			throw new Exception("No user available for this login id");
		}
		return userDetail;
	}

	/* For Deactivate User */
	@Override
	public UserMst deactiveUser(UserMst userMst) throws Exception {
		try {
			Optional<UserMst> userMst2 = userRepository.findById(userMst.getLoginId());
			if (userMst2.isPresent()) {
				UserMst newUserMst = userMst2.get();
				newUserMst.setIsActive(userMst.getIsActive());
				newUserMst.setIsActive(0);

				userRepository.save(newUserMst);
			} else {
				return userMst;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactive user------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No User deactivate......");
		}
		return userMst;
	}

	/* For Activate User */
	@Override
	public UserMst activateUser(UserMst userMst) throws Exception {
		try {
			Optional<UserMst> userMst2 = userRepository.findById(userMst.getLoginId());
			if (userMst2.isPresent()) {
				UserMst newUserMst = userMst2.get();
				newUserMst.setIsActive(userMst.getIsActive());
				newUserMst.setIsActive(1);

				userRepository.save(newUserMst);
			} else {
				return userMst;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while active user------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No User activate......");
		}
		return userMst;
	}

	/* Password Check */
	@Override
	public Optional<UserMst> getPasswordCheck(String password) throws Exception {
		try {
			return userRepository.getPasswordCheck(password);
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
			return userRepository.findLoginByLoginId(loginId);
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

	public static final Integer MAX_FAILED_ATTEMPTS = 3;

	/* Login Attempted Failed */
	public void increaseFailedAttempts(UserMst user) {
		Integer newFailAttempts = user.getIsUserLoggedIn() + 1;
		userRepository.updateFailedAttempts(newFailAttempts, user.getLoginId());
	}

	public void resetFailedAttempts(String loginId) {
		userRepository.updateFailedAttempts(0, loginId);
	}

	public void lock(UserMst user) {
		user.setIsUserLocked(0);
		user.setLockTime(new Date());

		userRepository.save(user);
	}

	@Override
	public void resetPassword(String password, String passwordToken, String emailId) throws Exception {
		try {
			userRepository.resetPassword(password, passwordToken, emailId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while reset password ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}

	}

	@Override
	public void setRandomOTP(int randomOTP, String emailId) throws Exception {
		try {
			userRepository.setRandomOTP(randomOTP, emailId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save rendom number ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}

	}

	@Override
	public List<EmpBasicDetails> getSearchEmpByIdAndFullName(String value) throws Exception {
		try {
			List<EmpBasicDetails> basicDetailsList = empBasicRepository.getSearchEmpByIdAndFullName(value);
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
