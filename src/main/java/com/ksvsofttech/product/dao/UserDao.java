package com.ksvsofttech.product.dao;

import java.util.List;
import java.util.Optional;

import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.UserMst;

public interface UserDao {

	public UserMst saveOrUpdateUser(UserMst user) throws Exception;

	public UserMst getUserById(String loginId) throws Exception;

	public List<UserMst> getIsActiveUsers() throws Exception;

	public List<UserMst> getInActiveUsers() throws Exception;
	
	public UserMst getUserDetails(String loginId) throws Exception;
	
	public UserMst deactiveUser(UserMst userMst) throws Exception;

	public UserMst activateUser(UserMst userMst) throws Exception;
	
	public Optional<UserMst> getPasswordCheck(String password) throws Exception;
	
	public boolean userExists(String password) throws Exception;
	
	public Optional<UserMst> findLoginByLoginId(String loginId) throws Exception;

	public boolean loginIdExists(String loginId) throws Exception;
	
	public void resetPassword(String password, String passwordToken, String emailId) throws Exception;

	public void setRandomOTP(int randomOTP, String emailId) throws Exception;
	
	public List<EmpBasicDetails> getSearchEmpByIdAndFullName(String value) throws Exception;
}
