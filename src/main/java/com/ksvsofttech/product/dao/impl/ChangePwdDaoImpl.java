package com.ksvsofttech.product.dao.impl;

import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.ChangePwdDao;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.repository.UserRepository;

@Repository
public class ChangePwdDaoImpl implements ChangePwdDao {
	private static final Logger LOGGER = LogManager.getLogger(ChangePwdDaoImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserMst getUserByLoginId(String loginId) throws Exception {
		UserMst userMst = new UserMst();
		try {
			userMst = userRepository.findByLoginId(loginId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while open change password page ------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Change password page not open......");
		}
		return userMst;
	}

	@Override
	public UserMst changePwd(UserMst userMst) throws Exception {
		String loginId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			loginId = authentication.getName();
			userMst.setLastModifieDate(new Date());
			userMst.setLastModifiedBy(loginId);
			userRepository.save(userMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while change password------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No user record exist......");
		}
		return userMst;
	}
}
