package com.ksvsofttech.product.dao;

import java.util.List;

import com.ksvsofttech.product.entities.UserMst;

public interface ResetPasswordDao {

	public List<UserMst> getIsActiveUsers() throws Exception;

	public UserMst saveUser(UserMst user) throws Exception;

	public UserMst getUserByToken(String passwordToken) throws Exception;
}
