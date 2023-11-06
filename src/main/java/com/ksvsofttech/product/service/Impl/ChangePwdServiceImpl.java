package com.ksvsofttech.product.service.Impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.ChangePwdDao;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.service.ChangePwdService;

@Service
public class ChangePwdServiceImpl implements ChangePwdService {
	private static final Logger LOGGER = LogManager.getLogger(ChangePwdServiceImpl.class);

	@Autowired
	private ChangePwdDao changePwdDao;

	@Override
	public UserMst getUserByLoginId(String loginId) throws Exception {
		UserMst userMst = new UserMst();
		try {
			userMst = changePwdDao.getUserByLoginId(loginId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while open change password page ------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Change password page not open......");
		}
		return userMst;
	}

	@Override
	public UserMst changePwd(UserMst userMst) throws Exception {
		try {
			changePwdDao.changePwd(userMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while change password------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No user record exist......");
		}
		return userMst;
	}

}
