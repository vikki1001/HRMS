package com.ksvsofttech.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ksvsofttech.product.entities.UserMst;

@Repository
public interface UserRepository extends JpaRepository<UserMst, String> {

	@Query("SELECT e FROM UserMst e WHERE e.loginId = :loginId AND e.isActive=1")
	public UserMst findByLoginId(String loginId);

	@Query("SELECT u FROM UserMst u WHERE u.isActive=1")
	public List<UserMst> getIsActive();

	@Query("SELECT u FROM UserMst u WHERE u.isActive=0")
	public List<UserMst> getInActive();

	@Query("SELECT u FROM UserMst u WHERE u.loginId = :loginId AND u.isActive=1")
	public UserMst getUserDetails(String loginId);
	
	/* Duplicate Login Id Check */
	@Query("SELECT e FROM UserMst e WHERE e.loginId = :loginId")
	public Optional<UserMst> findLoginByLoginId(String loginId);
	
	@Query("SELECT e FROM UserMst e WHERE e.password = :password")
	public Optional<UserMst> getPasswordCheck(String password);

	@Query("SELECT u FROM UserMst u WHERE u.loginId = :loginId AND u.isActive=1 AND u.tenantId=:tenantId")
	public UserMst getLoginAndTenantId(String loginId, String tenantId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE UserMst u SET u.isUserLoggedIn = ?1 WHERE u.loginId = ?2",nativeQuery = true)
    public void updateFailedAttempts(@Param("isUserLoggedIn") Integer isUserLoggedIn, @Param("loginId") String loginId);

	@Query("SELECT e FROM UserMst e WHERE e.password = :password")
	public Optional<UserMst> findPassword(String password);
	
	@Transactional
	@Modifying
	@Query("update UserMst u set u.randomOTP = ?1 where u.emailId = ?2")
	void setRandomOTP(Integer randomOTP, String emailId);
	
	@Transactional
	@Modifying
	@Query("update UserMst u set u.password = ?1, u.passwordToken = ?2  where u.emailId = ?3")
	void resetPassword(String password, String passwordToken, String emailId);

	@Query("SELECT e FROM UserMst e WHERE e.emailId = :emailId")
	public UserMst findByEmailId(String emailId);

	@Query(value = "SELECT * FROM UserMst WHERE isActive = '1' AND mainRole = 'HR'",nativeQuery = true)
	public String getHR();
}