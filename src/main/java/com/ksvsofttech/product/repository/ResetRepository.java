package com.ksvsofttech.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.UserMst;

@Repository
public interface ResetRepository extends JpaRepository<UserMst, String> {

	@Query("SELECT u FROM UserMst u WHERE u.isActive=1")
	public List<UserMst> getIsActive();

	@Query("SELECT u FROM UserMst u WHERE u.passwordToken=:passwordToken")
	public UserMst getPasswordPolicy(String passwordToken);
}
