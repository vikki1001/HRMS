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

import com.ksvsofttech.product.dao.ResetPasswordDao;
import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.repository.ResetRepository;

@Repository
public class ResetPasswordDaoImpl implements ResetPasswordDao {
	private static final Logger LOGGER = LogManager.getLogger(ResetPasswordDaoImpl.class);

	@Autowired
	ResetRepository resetRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<UserMst> getIsActiveUsers() throws Exception {
		List<UserMst> userList = resetRepository.getIsActive();
		try {
			if (!userList.isEmpty()) {
				return userList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No user record exist......");
		}
		return new ArrayList<>();
	}

	public UserMst saveUser(UserMst user) throws Exception {
		String loginId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			loginId = authentication.getName();
			Optional<UserMst> user2 = resetRepository.findById(user.getLoginId());
			if (user2.isPresent()) {
				UserMst newUser = user2.get();
				newUser.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
				newUser.setPasswordToken(UUID.randomUUID().toString());
				newUser.setLastModifieDate(new Date());
				newUser.setLastModifiedBy(loginId);
				
				return resetRepository.save(newUser);
			} else {
				return user;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while reset password ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("Password not reset  ......");
		}
	}

	public UserMst getUserByToken(String passwordToken) throws Exception {
		UserMst userMst = new UserMst();
		try {
			userMst = resetRepository.getPasswordPolicy(passwordToken);
		} catch (Exception e) {
			LOGGER.error("------Error occur while open reset password page ------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Reset password page not open......");
		}
		return userMst;
	}

}
