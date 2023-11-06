package com.ksvsofttech.product.service;

import java.util.List;

import com.ksvsofttech.product.entities.UserMst;

public interface ResetPasswordService {

	public List<UserMst> getAllUsers() throws Exception;

	public void saveUser(UserMst user) throws Exception;

	public UserMst getUserByToken(String passwordToken) throws Exception;
}
