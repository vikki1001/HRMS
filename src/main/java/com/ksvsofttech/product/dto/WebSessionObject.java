package com.ksvsofttech.product.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ksvsofttech.product.entities.CheckInCheckOut;
import com.ksvsofttech.product.entities.ExitActivity;
import com.ksvsofttech.product.entities.ExpenseReimb;
import com.ksvsofttech.product.entities.LeaveMst;
import com.ksvsofttech.product.entities.MenuMst;

public class WebSessionObject implements Serializable {
	private static final long serialVersionUID = 1L;

	private String loginId = "";
	private String loginType = "";
	private String username = "";
	private String userDisplayName = "";
	private String branch = "";
	private String baseBranch = "";
	private String mainRole = "";
	private String userGroup = "";
	private String custNo = "";
	private Date approveDate;
	private String tele1 = "";
	private String userDateJoiningStr = "";
	private String tele2 = "";
	private String firstName = "";
	private String prevPwdChgonStr = "";
	private Date prevPwdChgon;
	private String bossLoginId = "";
	private String stateCode = "";
	private Date dob;
	private String suspendedReason = "";
	private Integer forceMinLoginFreqDays;
	private Date lastSuccessFullLoginDateTimeStr;
	private String userLastNameOnPhotoId = "";
	private Date userDateOfJoining;
	private String reportingUserCode = "";
	private String bloodGroup = "";
	private String currentSuccessfullLoginDateTimeStr = "";
	private String cityCode = "";
	private String department = "";
	private Integer isActive;
	private Date userDateOfResignation;
	private String countryCode = "";
	private String religion = "";
	private Date nxtPwdChgon;
	private Integer allowSwitchingUserYn;
	private String nextPwdChgonStr = "";
	private String proofType = "";
	private Date currentSuccessfullLoginDateTime;
	private String escalationManager = "";
	private Integer isAdminFlag;
	private Date lastModifieDate;
	private String education = "";
	private Date createdDate;
	private String userFirstName = "";
	private Date dateOfDeactivation;
	private String description = "";
	private String userMiddleNameOnPhotoId = "";
	private String userType = "";
	private String relationManager = "";
	private String userDateOfBirthStr = "";
	private Date inActiveFromDateTime;
	private String language;
	private Integer appLoginYn;
	private String ipAddress = "";
	private Integer consequetiveBadLoginCount;
	private String bossEmailId = "";
	private String defLangCode = "";
	private Date userDateOfRetirement;
	private Long version;
	private String adsLoginYn = "";
	private String tenantId = "";
	private Date userLastDate;
	private String faxNo = "";
	private Integer allowConCurrentLogin;
	private Integer forcePwdChgDays;
	private String userMiddleName = "";
	private String userDateOfRetirementStr = "";
	private String userDateOfResignationStr = "";
	private String loginSessionID = "";
	private String lastName = "";
	private Integer isMaker;
	private String approvedBy = "";
	private Integer isChecker;
	private Integer forceMinLoginReqYn;
	private Integer userFirstNameOnPhotoId;
	private Integer adsAutoLoginYn;
	private String defModuleCode = "";
	private Integer isUserLoggedIn;
	private Integer idAmlUser;
	private Date lastSuccessfullLoginDateTime;
	private String pwdSalt = "";
	private String emailId = "";
	private String accountNo = "";
	private Integer isUserLocked;
	private Date userDateOfBirth;
	private String userTypeCode = "";
	private String userLastDateStr = "";
	private String userBaseBranchCode = "";
	private String inactiveTillDateTimeStr = "";
	private String resiAddressProofRefNo = "";
	private Integer isLoginSuspended;
	private String gender = "";
	private String address1 = "";
	private Integer loginSessionActive;
	private String createdBy = "";
	private String address2 = "";
	private String address3 = "";
	private String postalCode = "";
	private String mobileNo1 = "";
	private String mobileNo2 = "";
	private Date inactiveTillDateTime;
	private String secondaryUser = "";
	private String userLastName = "";
	private String inactiveFromDateTimeStr = "";
	private Integer forcePwdChangeYn;
	private String userSalutation = "";
	private String lastModifiedBy = "";
	private String hostName = "";
	private String authStatus = "";
	private String title = "";
	private String empId = "";
	private byte[] file;
	private String tenantLogoPath = "";
	private String tenantFaviconPath = "";
	private List<CheckInCheckOut> addNewReqNotification;
	private List<ExitActivity> exitActivityNotification;
	private List<ExpenseReimb> expenseReimbNotification;
	private List<LeaveMst> leaveMstNotification;

	private Map<String, List<MenuMst>> menuLHMap = new LinkedHashMap<String, List<MenuMst>>();

	public Map<String, List<MenuMst>> getMenuLHMap() {
		return menuLHMap;
	}

	public void setMenuLHMap(Map<String, List<MenuMst>> menuLHMap) {
		this.menuLHMap = menuLHMap;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
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

	public int getForceMinLoginFreqDays() {
		return forceMinLoginFreqDays;
	}

	public void setForceMinLoginFreqDays(int forceMinLoginFreqDays) {
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
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

	public int getAllowSwitchingUserYn() {
		return allowSwitchingUserYn;
	}

	public void setAllowSwitchingUserYn(int allowSwitchingUserYn) {
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

	public int getIsAdminFlag() {
		return isAdminFlag;
	}

	public void setIsAdminFlag(int isAdminFlag) {
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

	public int getAppLoginYn() {
		return appLoginYn;
	}

	public void setAppLoginYn(int appLoginYn) {
		this.appLoginYn = appLoginYn;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getConsequetiveBadLoginCount() {
		return consequetiveBadLoginCount;
	}

	public void setConsequetiveBadLoginCount(int consequetiveBadLoginCount) {
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

	public int getAllowConCurrentLogin() {
		return allowConCurrentLogin;
	}

	public void setAllowConCurrentLogin(int allowConCurrentLogin) {
		this.allowConCurrentLogin = allowConCurrentLogin;
	}

	public int getForcePwdChgDays() {
		return forcePwdChgDays;
	}

	public void setForcePwdChgDays(int forcePwdChgDays) {
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

	public int getIsMaker() {
		return isMaker;
	}

	public void setIsMaker(int isMaker) {
		this.isMaker = isMaker;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public int getIsChecker() {
		return isChecker;
	}

	public void setIsChecker(int isChecker) {
		this.isChecker = isChecker;
	}

	public int getForceMinLoginReqYn() {
		return forceMinLoginReqYn;
	}

	public void setForceMinLoginReqYn(int forceMinLoginReqYn) {
		this.forceMinLoginReqYn = forceMinLoginReqYn;
	}

	public int getUserFirstNameOnPhotoId() {
		return userFirstNameOnPhotoId;
	}

	public void setUserFirstNameOnPhotoId(int userFirstNameOnPhotoId) {
		this.userFirstNameOnPhotoId = userFirstNameOnPhotoId;
	}

	public int getAdsAutoLoginYn() {
		return adsAutoLoginYn;
	}

	public void setAdsAutoLoginYn(int adsAutoLoginYn) {
		this.adsAutoLoginYn = adsAutoLoginYn;
	}

	public String getDefModuleCode() {
		return defModuleCode;
	}

	public void setDefModuleCode(String defModuleCode) {
		this.defModuleCode = defModuleCode;
	}

	public int getIsUserLoggedIn() {
		return isUserLoggedIn;
	}

	public void setIsUserLoggedIn(int isUserLoggedIn) {
		this.isUserLoggedIn = isUserLoggedIn;
	}

	public int getIdAmlUser() {
		return idAmlUser;
	}

	public void setIdAmlUser(int idAmlUser) {
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

	public int getIsUserLocked() {
		return isUserLocked;
	}

	public void setIsUserLocked(int isUserLocked) {
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

	public int getIsLoginSuspended() {
		return isLoginSuspended;
	}

	public void setIsLoginSuspended(int isLoginSuspended) {
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

	public int getLoginSessionActive() {
		return loginSessionActive;
	}

	public void setLoginSessionActive(int loginSessionActive) {
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

	public int getForcePwdChangeYn() {
		return forcePwdChangeYn;
	}

	public void setForcePwdChangeYn(int forcePwdChangeYn) {
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

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getTenantLogoPath() {
		return tenantLogoPath;
	}

	public void setTenantLogoPath(String tenantLogoPath) {
		this.tenantLogoPath = tenantLogoPath;
	}

	public String getTenantFaviconPath() {
		return tenantFaviconPath;
	}

	public void setTenantFaviconPath(String tenantFaviconPath) {
		this.tenantFaviconPath = tenantFaviconPath;
	}

	public List<CheckInCheckOut> getAddNewReqNotification() {
		return addNewReqNotification;
	}

	public void setAddNewReqNotification(List<CheckInCheckOut> addNewReqNotification) {
		this.addNewReqNotification = addNewReqNotification;
	}

	public List<ExitActivity> getExitActivityNotification() {
		return exitActivityNotification;
	}

	public void setExitActivityNotification(List<ExitActivity> exitActivityNotification) {
		this.exitActivityNotification = exitActivityNotification;
	}

	public List<ExpenseReimb> getExpenseReimbNotification() {
		return expenseReimbNotification;
	}

	public void setExpenseReimbNotification(List<ExpenseReimb> expenseReimbNotification) {
		this.expenseReimbNotification = expenseReimbNotification;
	}

	public List<LeaveMst> getLeaveMstNotification() {
		return leaveMstNotification;
	}

	public void setLeaveMstNotification(List<LeaveMst> leaveMstNotification) {
		this.leaveMstNotification = leaveMstNotification;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
