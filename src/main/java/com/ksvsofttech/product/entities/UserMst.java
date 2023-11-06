package com.ksvsofttech.product.entities;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "USERMST")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LOGINID", length = 20, unique = true, updatable = false)
	private String loginId;

	@Column(name = "PWDTOKEN")
	private String passwordToken;

	@Column(name = "LOGINTYPE", length = 10)
	private String loginType;

	@Column(name = "USERNAME", length = 30)
	private String username;

	@Column(name = "password", length = 90)
	private String password;

	@Transient
	private String newPassword;

	@Transient
	private String confirmPassword;

	@Column(name = "USERDISPLAYNAME", length = 50)
	private String userDisplayName;

	@Column(name = "BASEBRANCH", length = 20)
	private String baseBranch;

	@Column(name = "MAINROLE", length = 20)
	private String mainRole;

	@Column(name = "USERGROUP", length = 20)
	private String userGroup;

	@Column(name = "CUSTNO", length = 30)
	private String custNo;

	@Column(name = "APPROVEDATE")
	private Date approveDate;

	@Column(name = "TELE1", length = 15)
	private String tele1;

	@Column(name = "USERDATEJOININGSTR", length = 30)
	private String userDateJoiningStr;

	@Column(name = "TELE2", length = 15)
	private String tele2;

	@Column(name = "FIRSTNAME", length = 30)
	private String firstName;

	@Column(name = "PREVPWDCHGONSTR", length = 50)
	private String prevPwdChgonStr;

	@Column(name = "PREVPWDCHGON")
	private Date prevPwdChgon;

	@Column(name = "BOSSLOGINID", length = 20)
	private String bossLoginId;

	@Column(name = "STATECODE", length = 20)
	private String stateCode;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DOB")
	private Date dob;

	@Column(name = "SUSPENDEDREASON", length = 50)
	private String suspendedReason;

	@Column(name = "LOGINFREQDAYS", length = 10)
	private Integer forceMinLoginFreqDays;

	@Column(name = "LASTSUCCESSLOGINDATETIMESTR")
	private Date lastSuccessFullLoginDateTimeStr;

	@Column(name = "USERLNAMEPHOTOID", length = 30)
	private String userLastNameOnPhotoId;

	@Column(name = "USERDATEJOINING")
	private Date userDateOfJoining;

	@Column(name = "REPORTINGUSERCOD", length = 20)
	private String reportingUserCode;

	@Column(name = "BLOODGROUP", length = 10)
	private String bloodGroup;

	@Column(name = "CURRSUCCESSLOGINDATETIMESTR", length = 50)
	private String currentSuccessfullLoginDateTimeStr;

	@Column(name = "CITYCOD", length = 10)
	private String cityCode;

	@Column(name = "DEPARTMENT", length = 20)
	private String department;

	@Column(name = "ISACTIVE")
	private Integer isActive;

	@Column(name = "DATEOFRESIGNATION")
	private Date userDateOfResignation;

	@Column(name = "COUNTRYCODE", length = 10)
	private String countryCode;

	@Column(name = "RELIGION")
	private String religion;

	@Column(name = "NXTPWDCHGON")
	private Date nxtPwdChgon;

	@Column(name = "ALLOWSWITCHUSERYN", length = 20)
	private Integer allowSwitchingUserYn;

	@Column(name = "NXTPWDCHGONSTR", length = 50)
	private String nextPwdChgonStr;

	@Column(name = "PROOFTYPE", length = 20)
	private String proofType;

	@Column(name = "CURRSUCCESSLOGINDATETIME")
	private Date currentSuccessfullLoginDateTime;

	@Column(name = "ESCALATIONMANAGER", length = 50)
	private String escalationManager;

	@Column(name = "ISADMINFLAG", length = 10)
	private Integer isAdminFlag;

	@Column(name = "LASTMODDATE")
	private Date lastModifieDate;

	@Column(name = "EDUCATION", length = 50)
	private String education;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "USERFNAME", length = 30)
	private String userFirstName;

	@Column(name = "DATEDEACTIVATION")
	private Date dateOfDeactivation;

	@Column(name = "DESCRIPTION", length = 100)
	private String description;

	@Column(name = "USERMNAMEPHOTOID", length = 50)
	private String userMiddleNameOnPhotoId;

	@Column(name = "USERTYPE", length = 10)
	private String userType;

	@Column(name = "RELATIONMGR", length = 20)
	private String relationManager;

	@Column(name = "USERDOBSTR", length = 50)
	private String userDateOfBirthStr;

	@Column(name = "INACTIVEDATETIME")
	private Date inActiveFromDateTime;

	@Column(name = "APPLGNYN", length = 20)
	private Integer appLoginYn;

	@Column(name = "IPADDRS", length = 50)
	private String ipAddress;

	@Column(name = "CONSEQBADLOGINCOUNT", length = 20)
	private Integer consequetiveBadLoginCount;

	@Column(name = "BOSSEMAILID", length = 50)
	private String bossEmailId;

	@Column(name = "DEFLNGCOD", length = 20)
	private String defLangCode;

	@Column(name = "DATEOFRETIREMENT")
	private Date userDateOfRetirement;

	@Column(name = "VERSION", length = 20)
	private Long version;

	@Column(name = "ADSLOGINYN", length = 50)
	private String adsLoginYn;

	@Column(name = "TENANTID", length = 10)
	private String tenantId;

	@Column(name = "USERLASTDATE")
	private Date userLastDate;

	@Column(name = "FAXNO", length = 20)
	private String faxNo;

	@Column(name = "ALLOWCONCURRENTLOGIN", length = 20)
	private Integer allowConCurrentLogin;

	@Column(name = "FORCEPWDCHGDAYS", length = 20)
	private Integer forcePwdChgDays;

	@Column(name = "USERMNAME", length = 30)
	private String userMiddleName;

	@Column(name = "DATEOFRETIREMENTSTR", length = 30)
	private String userDateOfRetirementStr;

	@Column(name = "UDTOFRESIGSTR", length = 30)
	private String userDateOfResignationStr;

	@Column(name = "LOGINSESSIONID", length = 30)
	private String loginSessionID;

	@Column(name = "LASTNAME", length = 30)
	private String lastName;

	@Column(name = "ISMAKER", length = 30)
	private Integer isMaker;

	@Column(name = "APPROVEDBY", length = 30)
	private String approvedBy;

	@Column(name = "ISCHECKER", length = 30)
	private Integer isChecker;

	@Column(name = "FORCELOGINFREQYN", length = 20)
	private Integer forceMinLoginReqYn;

	@Column(name = "USERFNAMEPHOTOID", length = 30)
	private Integer userFirstNameOnPhotoId;

	@Column(name = "ADSAUTOLOGINYN", length = 20)
	private Integer adsAutoLoginYn;

	@Column(name = "DEFMODULECOD", length = 30)
	private String defModuleCode;

	@Column(name = "ISUSERLOGGEDIN", length = 20)
	private Integer isUserLoggedIn;

	@Column(name = "ISAMLUSER", length = 20)
	private Integer idAmlUser;

	@Column(name = "LASTSUCCESSFLOGINDATETIME")
	private Date lastSuccessfullLoginDateTime;

	@Column(name = "PWDSALT", length = 30)
	private String pwdSalt;

	@Column(name = "EMAILID", length = 30)
	private String emailId;

	@Column(name = "ACCOUNTNO", length = 30)
	private String accountNo;

	@Column(name = "ISUSERLOCKED", length = 30)
	private Integer isUserLocked;

	@Column(name = "USERDOB")
	private Date userDateOfBirth;

	@Column(name = "USERTYPECOD", length = 20)
	private String userTypeCode;

	@Column(name = "USERLASTDATESTR", length = 20)
	private String userLastDateStr;

	@Column(name = "BASEBRANCHCOD", length = 20)
	private String userBaseBranchCode;

	@Column(name = "BRANCH", length = 20)
	private String branch;

	@Column(name = "INACTIVETILLDATETIMESTR", length = 30)
	private String inactiveTillDateTimeStr;

	@Column(name = "RESIADDRESSPROOFREFNO", length = 30)
	private String resiAddressProofRefNo;

	@Column(name = "ISLOGINSUSPENDED", length = 20)
	private Integer isLoginSuspended;

	@Column(name = "GENDER", length = 20)
	private String gender;

	@Column(name = "ADDRESS1", length = 50)
	private String address1;

	@Column(name = "LOGINSESSIONACTIVE", length = 20)
	private Integer loginSessionActive;

	@Column(name = "CREATEDBY", length = 20)
	private String createdBy;

	@Column(name = "ADDRESS2", length = 50)
	private String address2;

	@Column(name = "ADDRESS3", length = 50)
	private String address3;

	@Column(name = "POSTALCOD", length = 10)
	private String postalCode;

	@Column(name = "MOBNO1", length = 10)
	private String mobileNo1;

	@Column(name = "MOBNO2", length = 10)
	private String mobileNo2;

	@Column(name = "INACTIVETILLDATETIME")
	private Date inactiveTillDateTime;

	@Column(name = "SECONDARYUSER", length = 30)
	private String secondaryUser;

	@Column(name = "USERLNAME", length = 20)
	private String userLastName;

	@Column(name = "INACTIVEFROMDATETIMESTR", length = 50)
	private String inactiveFromDateTimeStr;

	@Column(name = "FRCPWDCHGYN", length = 20)
	private Integer forcePwdChangeYn;

	@Column(name = "USERSALUTATION", length = 50)
	private String userSalutation;

	@Column(name = "LASTMODBY", length = 20)
	private String lastModifiedBy;

	@Column(name = "HOSTNAME", length = 30)
	private String hostName;

	@Column(name = "AUTHSTATUS", length = 30)
	private String authStatus;

	@Column(name = "TITLE", length = 20)
	private String title;

	@Column(name = "PASSWORDEXPIREDATE")
	private Date passwordExpireDate;

	@Column(name = "LASTLOGIN")
	private Date lastLogin;

	@Column(name = "LOCKTIME")
	private Date lockTime;

	@Column(name = "randomOTP")
	private Integer randomOTP;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPasswordToken() {
		return passwordToken;
	}

	public void setPasswordToken(String passwordToken) {
		this.passwordToken = passwordToken;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public String getBaseBranch() {
		return baseBranch;
	}

	public void setBaseBranch(String baseBranch) {
		this.baseBranch = baseBranch;
	}

	public String getMainRole() {
		return mainRole;
	}

	public void setMainRole(String mainRole) {
		this.mainRole = mainRole;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getTele1() {
		return tele1;
	}

	public void setTele1(String tele1) {
		this.tele1 = tele1;
	}

	public String getUserDateJoiningStr() {
		return userDateJoiningStr;
	}

	public void setUserDateJoiningStr(String userDateJoiningStr) {
		this.userDateJoiningStr = userDateJoiningStr;
	}

	public String getTele2() {
		return tele2;
	}

	public void setTele2(String tele2) {
		this.tele2 = tele2;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPrevPwdChgonStr() {
		return prevPwdChgonStr;
	}

	public void setPrevPwdChgonStr(String prevPwdChgonStr) {
		this.prevPwdChgonStr = prevPwdChgonStr;
	}

	public Date getPrevPwdChgon() {
		return prevPwdChgon;
	}

	public void setPrevPwdChgon(Date prevPwdChgon) {
		this.prevPwdChgon = prevPwdChgon;
	}

	public String getBossLoginId() {
		return bossLoginId;
	}

	public void setBossLoginId(String bossLoginId) {
		this.bossLoginId = bossLoginId;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSuspendedReason() {
		return suspendedReason;
	}

	public void setSuspendedReason(String suspendedReason) {
		this.suspendedReason = suspendedReason;
	}

	public Integer getForceMinLoginFreqDays() {
		return forceMinLoginFreqDays;
	}

	public void setForceMinLoginFreqDays(Integer forceMinLoginFreqDays) {
		this.forceMinLoginFreqDays = forceMinLoginFreqDays;
	}

	public Date getLastSuccessFullLoginDateTimeStr() {
		return lastSuccessFullLoginDateTimeStr;
	}

	public void setLastSuccessFullLoginDateTimeStr(Date lastSuccessFullLoginDateTimeStr) {
		this.lastSuccessFullLoginDateTimeStr = lastSuccessFullLoginDateTimeStr;
	}

	public String getUserLastNameOnPhotoId() {
		return userLastNameOnPhotoId;
	}

	public void setUserLastNameOnPhotoId(String userLastNameOnPhotoId) {
		this.userLastNameOnPhotoId = userLastNameOnPhotoId;
	}

	public Date getUserDateOfJoining() {
		return userDateOfJoining;
	}

	public void setUserDateOfJoining(Date userDateOfJoining) {
		this.userDateOfJoining = userDateOfJoining;
	}

	public String getReportingUserCode() {
		return reportingUserCode;
	}

	public void setReportingUserCode(String reportingUserCode) {
		this.reportingUserCode = reportingUserCode;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getCurrentSuccessfullLoginDateTimeStr() {
		return currentSuccessfullLoginDateTimeStr;
	}

	public void setCurrentSuccessfullLoginDateTimeStr(String currentSuccessfullLoginDateTimeStr) {
		this.currentSuccessfullLoginDateTimeStr = currentSuccessfullLoginDateTimeStr;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Date getUserDateOfResignation() {
		return userDateOfResignation;
	}

	public void setUserDateOfResignation(Date userDateOfResignation) {
		this.userDateOfResignation = userDateOfResignation;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public Date getNxtPwdChgon() {
		return nxtPwdChgon;
	}

	public void setNxtPwdChgon(Date nxtPwdChgon) {
		this.nxtPwdChgon = nxtPwdChgon;
	}

	public Integer getAllowSwitchingUserYn() {
		return allowSwitchingUserYn;
	}

	public void setAllowSwitchingUserYn(Integer allowSwitchingUserYn) {
		this.allowSwitchingUserYn = allowSwitchingUserYn;
	}

	public String getNextPwdChgonStr() {
		return nextPwdChgonStr;
	}

	public void setNextPwdChgonStr(String nextPwdChgonStr) {
		this.nextPwdChgonStr = nextPwdChgonStr;
	}

	public String getProofType() {
		return proofType;
	}

	public void setProofType(String proofType) {
		this.proofType = proofType;
	}

	public Date getCurrentSuccessfullLoginDateTime() {
		return currentSuccessfullLoginDateTime;
	}

	public void setCurrentSuccessfullLoginDateTime(Date currentSuccessfullLoginDateTime) {
		this.currentSuccessfullLoginDateTime = currentSuccessfullLoginDateTime;
	}

	public String getEscalationManager() {
		return escalationManager;
	}

	public void setEscalationManager(String escalationManager) {
		this.escalationManager = escalationManager;
	}

	public Integer getIsAdminFlag() {
		return isAdminFlag;
	}

	public void setIsAdminFlag(Integer isAdminFlag) {
		this.isAdminFlag = isAdminFlag;
	}

	public Date getLastModifieDate() {
		return lastModifieDate;
	}

	public void setLastModifieDate(Date lastModifieDate) {
		this.lastModifieDate = lastModifieDate;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public Date getDateOfDeactivation() {
		return dateOfDeactivation;
	}

	public void setDateOfDeactivation(Date dateOfDeactivation) {
		this.dateOfDeactivation = dateOfDeactivation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserMiddleNameOnPhotoId() {
		return userMiddleNameOnPhotoId;
	}

	public void setUserMiddleNameOnPhotoId(String userMiddleNameOnPhotoId) {
		this.userMiddleNameOnPhotoId = userMiddleNameOnPhotoId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRelationManager() {
		return relationManager;
	}

	public void setRelationManager(String relationManager) {
		this.relationManager = relationManager;
	}

	public String getUserDateOfBirthStr() {
		return userDateOfBirthStr;
	}

	public void setUserDateOfBirthStr(String userDateOfBirthStr) {
		this.userDateOfBirthStr = userDateOfBirthStr;
	}

	public Date getInActiveFromDateTime() {
		return inActiveFromDateTime;
	}

	public void setInActiveFromDateTime(Date inActiveFromDateTime) {
		this.inActiveFromDateTime = inActiveFromDateTime;
	}

	public Integer getAppLoginYn() {
		return appLoginYn;
	}

	public void setAppLoginYn(Integer appLoginYn) {
		this.appLoginYn = appLoginYn;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Integer getConsequetiveBadLoginCount() {
		return consequetiveBadLoginCount;
	}

	public void setConsequetiveBadLoginCount(Integer consequetiveBadLoginCount) {
		this.consequetiveBadLoginCount = consequetiveBadLoginCount;
	}

	public String getBossEmailId() {
		return bossEmailId;
	}

	public void setBossEmailId(String bossEmailId) {
		this.bossEmailId = bossEmailId;
	}

	public String getDefLangCode() {
		return defLangCode;
	}

	public void setDefLangCode(String defLangCode) {
		this.defLangCode = defLangCode;
	}

	public Date getUserDateOfRetirement() {
		return userDateOfRetirement;
	}

	public void setUserDateOfRetirement(Date userDateOfRetirement) {
		this.userDateOfRetirement = userDateOfRetirement;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getAdsLoginYn() {
		return adsLoginYn;
	}

	public void setAdsLoginYn(String adsLoginYn) {
		this.adsLoginYn = adsLoginYn;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Date getUserLastDate() {
		return userLastDate;
	}

	public void setUserLastDate(Date userLastDate) {
		this.userLastDate = userLastDate;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public Integer getAllowConCurrentLogin() {
		return allowConCurrentLogin;
	}

	public void setAllowConCurrentLogin(Integer allowConCurrentLogin) {
		this.allowConCurrentLogin = allowConCurrentLogin;
	}

	public Integer getForcePwdChgDays() {
		return forcePwdChgDays;
	}

	public void setForcePwdChgDays(Integer forcePwdChgDays) {
		this.forcePwdChgDays = forcePwdChgDays;
	}

	public String getUserMiddleName() {
		return userMiddleName;
	}

	public void setUserMiddleName(String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}

	public String getUserDateOfRetirementStr() {
		return userDateOfRetirementStr;
	}

	public void setUserDateOfRetirementStr(String userDateOfRetirementStr) {
		this.userDateOfRetirementStr = userDateOfRetirementStr;
	}

	public String getUserDateOfResignationStr() {
		return userDateOfResignationStr;
	}

	public void setUserDateOfResignationStr(String userDateOfResignationStr) {
		this.userDateOfResignationStr = userDateOfResignationStr;
	}

	public String getLoginSessionID() {
		return loginSessionID;
	}

	public void setLoginSessionID(String loginSessionID) {
		this.loginSessionID = loginSessionID;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getIsMaker() {
		return isMaker;
	}

	public void setIsMaker(Integer isMaker) {
		this.isMaker = isMaker;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Integer getIsChecker() {
		return isChecker;
	}

	public void setIsChecker(Integer isChecker) {
		this.isChecker = isChecker;
	}

	public Integer getForceMinLoginReqYn() {
		return forceMinLoginReqYn;
	}

	public void setForceMinLoginReqYn(Integer forceMinLoginReqYn) {
		this.forceMinLoginReqYn = forceMinLoginReqYn;
	}

	public Integer getUserFirstNameOnPhotoId() {
		return userFirstNameOnPhotoId;
	}

	public void setUserFirstNameOnPhotoId(Integer userFirstNameOnPhotoId) {
		this.userFirstNameOnPhotoId = userFirstNameOnPhotoId;
	}

	public Integer getAdsAutoLoginYn() {
		return adsAutoLoginYn;
	}

	public void setAdsAutoLoginYn(Integer adsAutoLoginYn) {
		this.adsAutoLoginYn = adsAutoLoginYn;
	}

	public String getDefModuleCode() {
		return defModuleCode;
	}

	public void setDefModuleCode(String defModuleCode) {
		this.defModuleCode = defModuleCode;
	}

	public Integer getIsUserLoggedIn() {
		return isUserLoggedIn;
	}

	public void setIsUserLoggedIn(Integer isUserLoggedIn) {
		this.isUserLoggedIn = isUserLoggedIn;
	}

	public Integer getIdAmlUser() {
		return idAmlUser;
	}

	public void setIdAmlUser(Integer idAmlUser) {
		this.idAmlUser = idAmlUser;
	}

	public Date getLastSuccessfullLoginDateTime() {
		return lastSuccessfullLoginDateTime;
	}

	public void setLastSuccessfullLoginDateTime(Date lastSuccessfullLoginDateTime) {
		this.lastSuccessfullLoginDateTime = lastSuccessfullLoginDateTime;
	}

	public String getPwdSalt() {
		return pwdSalt;
	}

	public void setPwdSalt(String pwdSalt) {
		this.pwdSalt = pwdSalt;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Integer getIsUserLocked() {
		return isUserLocked;
	}

	public void setIsUserLocked(Integer isUserLocked) {
		this.isUserLocked = isUserLocked;
	}

	public Date getUserDateOfBirth() {
		return userDateOfBirth;
	}

	public void setUserDateOfBirth(Date userDateOfBirth) {
		this.userDateOfBirth = userDateOfBirth;
	}

	public String getUserTypeCode() {
		return userTypeCode;
	}

	public void setUserTypeCode(String userTypeCode) {
		this.userTypeCode = userTypeCode;
	}

	public String getUserLastDateStr() {
		return userLastDateStr;
	}

	public void setUserLastDateStr(String userLastDateStr) {
		this.userLastDateStr = userLastDateStr;
	}

	public String getUserBaseBranchCode() {
		return userBaseBranchCode;
	}

	public void setUserBaseBranchCode(String userBaseBranchCode) {
		this.userBaseBranchCode = userBaseBranchCode;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getInactiveTillDateTimeStr() {
		return inactiveTillDateTimeStr;
	}

	public void setInactiveTillDateTimeStr(String inactiveTillDateTimeStr) {
		this.inactiveTillDateTimeStr = inactiveTillDateTimeStr;
	}

	public String getResiAddressProofRefNo() {
		return resiAddressProofRefNo;
	}

	public void setResiAddressProofRefNo(String resiAddressProofRefNo) {
		this.resiAddressProofRefNo = resiAddressProofRefNo;
	}

	public Integer getIsLoginSuspended() {
		return isLoginSuspended;
	}

	public void setIsLoginSuspended(Integer isLoginSuspended) {
		this.isLoginSuspended = isLoginSuspended;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public Integer getLoginSessionActive() {
		return loginSessionActive;
	}

	public void setLoginSessionActive(Integer loginSessionActive) {
		this.loginSessionActive = loginSessionActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getMobileNo1() {
		return mobileNo1;
	}

	public void setMobileNo1(String mobileNo1) {
		this.mobileNo1 = mobileNo1;
	}

	public String getMobileNo2() {
		return mobileNo2;
	}

	public void setMobileNo2(String mobileNo2) {
		this.mobileNo2 = mobileNo2;
	}

	public Date getInactiveTillDateTime() {
		return inactiveTillDateTime;
	}

	public void setInactiveTillDateTime(Date inactiveTillDateTime) {
		this.inactiveTillDateTime = inactiveTillDateTime;
	}

	public String getSecondaryUser() {
		return secondaryUser;
	}

	public void setSecondaryUser(String secondaryUser) {
		this.secondaryUser = secondaryUser;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getInactiveFromDateTimeStr() {
		return inactiveFromDateTimeStr;
	}

	public void setInactiveFromDateTimeStr(String inactiveFromDateTimeStr) {
		this.inactiveFromDateTimeStr = inactiveFromDateTimeStr;
	}

	public Integer getForcePwdChangeYn() {
		return forcePwdChangeYn;
	}

	public void setForcePwdChangeYn(Integer forcePwdChangeYn) {
		this.forcePwdChangeYn = forcePwdChangeYn;
	}

	public String getUserSalutation() {
		return userSalutation;
	}

	public void setUserSalutation(String userSalutation) {
		this.userSalutation = userSalutation;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPasswordExpireDate() {
		return passwordExpireDate;
	}

	public void setPasswordExpireDate(Date passwordExpireDate) {
		this.passwordExpireDate = passwordExpireDate;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public Integer getRandomOTP() {
		return randomOTP;
	}

	public void setRandomOTP(Integer randomOTP) {
		this.randomOTP = randomOTP;
	}

	public static class Comparators {
		public static Comparator<UserMst> LOGINID = new Comparator<UserMst>() {

			@Override
			public int compare(UserMst o1, UserMst o2) {
				return o1.loginId.compareTo(o2.loginId);
			}
		};
	}
}