package com.ksvsofttech.product.dao;

import com.ksvsofttech.product.entities.UserMst;

public interface ChangePwdDao {

	public UserMst getUserByLoginId(String loginId) throws Exception;
	
	public UserMst changePwd(UserMst userMst) throws Exception;
	
}
