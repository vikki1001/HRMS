package com.ksvsofttech.product.service.Impl;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.ResetPasswordDao;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.service.ResetPasswordService;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {
	private static final Logger LOGGER = LogManager.getLogger(ResetPasswordServiceImpl.class);

	@Autowired
	private ResetPasswordDao resetPasswordDao;

	public List<UserMst> getAllUsers() throws Exception {
		List<UserMst> userMst;
		try {
			userMst = resetPasswordDao.getIsActiveUsers();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display users list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No user record exist......");
		}
		return userMst;
	}

	public void saveUser(UserMst user) throws Exception {
		try {
			this.resetPasswordDao.saveUser(user);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update user------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No user save or update......");
		}
	}

	public UserMst getUserByToken(String passwordToken) throws Exception {
		UserMst userMst = new UserMst();
		try {
			userMst = resetPasswordDao.getUserByToken(passwordToken);
		} catch (Exception e) {
			LOGGER.error("------Error occur while open reset password page ------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Reset password page not open......");
		}
		return userMst;
	}
}
