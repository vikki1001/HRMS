package com.ksvsofttech.product.service;

import com.ksvsofttech.product.entities.UserMst;

public interface ChangePwdService {
	
	public UserMst getUserByLoginId(String loginId) throws Exception;
	
	public UserMst changePwd(UserMst userMst) throws Exception;
}
