package com.ksvsofttech.product.entities;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "TENANTCONFIG")
public class TenantConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "WRITEOFFTEMPLATEFILELINK", length = 30)
	private String writeOffTemplateFileLink;

	@Column(name = "PAGINATIONSIZE")
	private Integer paginationSize;

	@Column(name = "FILENAMETXN", length = 30)
	private String fileNameTxn;

	@Column(name = "CTRTEMPLATEPATH", length = 30)
	private String ctrTemplatePath;

	@Column(name = "CLEANOUTPATH", length = 30)
	private String cleanOutPath;

	@Column(name = "FILEOUTPATH", length = 30)
	private String fileOutPath;

	@Column(name = "ALERTMGMTBRCODE", length = 30)
	private String alertMgmtbrCode;

	@Column(name = "TENANTBASECURERNCY", length = 30)
	private String tenantBaseCurerncy;

	@Column(name = "DECIMALCHARACTER", length = 30)
	private String decimalCharacter;

	@Column(name = "PATHFORAMLFILES", length = 30)
	private String pathForAmlFiles;

	@Column(name = "STRDOWNLOADPATH", length = 30)
	private String StrDownLoadPath;

	@Column(name = "ISACTIVELOGINDAYS")
	private Integer isactiveLoginDays;

	@Column(name = "AUTOLOGINYN")
	private Integer autoLoginYn;

	@Column(name = "SWIFTCOUNTRYRISKLOW")
	private Integer swiftCountryRiskLow;

	@Column(name = "EMAILRETRYCOUNT")
	private Integer emailRetryCount;

	@Column(name = "FILENAMECUSTRELATEDPERSON", length = 30)
	private String fileNameCustRelatedPerson;

	@Column(name = "ALERTTAT", length = 30)
	private String alertTat;

	@Column(name = "REJECTEDINPATH", length = 30)
	private String rejectedInPath;

	@Column(name = "PROCESSNAME", length = 30)
	private String processName;

	@Column(name = "ISACTIVE")
	private String isActive;

	@Column(name = "PATHFORSDNUPLOAD", length = 30)
	private String pathForSdnUpload;

	@Column(name = "AMOUNTFORMAT", length = 30)
	private String amountFormat;

	@Column(name = "TOPDOWNALERTESCALATION")
	private Integer topDownAlertEscalation;

	@Column(name = "FILENAMEACCOUNTMST", length = 30)
	private String fileNameAccountMst;

	@Column(name = "SWIFTHOLDREMINDER1DAYS")
	private Integer swiftHoldRemainder1Days;

	@Column(name = "CASEREMINDER3DAYS")
	private Integer caseReminder3Days;

	@Column(name = "ALLOWSWITCHINGUSERYN")
	private Integer allowSwitchingUserYn;

	@Column(name = "ALERTFORWARDINGBRCODE", length = 30)
	private String alertForWardingBrcode;

	@Column(name = "SMSRETRYCOUNT")
	private Integer smsRetryCount;

	@Column(name = "ALERTTATUOM", length = 30)
	private String alertTatuom;

	@Column(name = "PATHFORRULEFILEUPLOAD", length = 30)
	private String pathForRuleFileUpload;

	@Column(name = "CASEMGMTBRCODE", length = 30)
	private String caseMgmtBrcode;

	@Column(name = "LASTMODIFIEDDATE")
	private Date lastModifiedDate;

	@Column(name = "NEWCUSTOMERDAYS")
	private Integer newCustomerDays;

	@Column(name = "PATHFORCASEFILES", length = 30)
	private String pathForCaseFiles;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "CASEFORWARDINGBRCODE", length = 30)
	private String caseForWardingBrcode;

	@Column(name = "QUICKLINKS")
	private Integer QuickLinks;

	@Column(name = "DESCRIPTION", length = 90)
	private String description;

	@Column(name = "VIOLATEDOUTPATH", length = 30)
	private String violatedOutPath;

	@Column(name = "TXNTYPELASTMONTHS", length = 30)
	private String txnTypeLastMonths;

	@Column(name = "NEWACCOUNTDAYS")
	private Integer newAccountDays;

	@Column(name = "FILENAMELOOKUP", length = 30)
	private String fileNameLookup;

	@Column(name = "TXNTRENDAVGMONTHS", length = 30)
	private String txnTrendAvgMonths;

	@Column(name = "CLEANINPATH", length = 30)
	private String cleanInPath;

	@DateTimeFormat(pattern = "HH:mm:ss")
	@Column(name = "TIMEFORMAT")
	private String timeFormat;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DATEPICKERFORMAT")
	private Date datePickerFormat;

	@Column(name = "CTRDOWNLOADPATH", length = 30)
	private String ctrDownLoadPath;

	@Column(name = "SWIFTCOUNTRYRISKMED")
	private Integer swiftCountryRiskMed;

	@Column(name = "APPLOGINYN")
	private Integer AppLoginYn;

	@Column(name = "DIGITSEPARATOR", length = 30)
	private String DigitSeparator;

	@Column(name = "BACKUPOUTPATH", length = 30)
	private String backupOutPath;

	@Column(name = "NAMEMATCHPERCENTAGE")
	private Long nameMatchPercentage;

	@Column(name = "NUMBERDECIMALCOUNT", length = 30)
	private String numberDecimalCount;

	@Column(name = "FREETEXTPROXIMITY")
	private Integer freeTextProximity;

	@Column(name = "RECENTLINKS")
	private Integer recentLinks;

	@Column(name = "BOTTOMUPALERTESCALATION")
	private Integer bottomUpAlertEscalation;

	@Column(name = "CASEWIPTAT", length = 30)
	private String caseWiptat;

	@Column(name = "FILENAMEBANKBRANCH", length = 30)
	private String fileNameBankBranch;

	@Column(name = "SWIFTINFILEPOLLINTERVAL")
	private Integer swiftInFilePollInterval;

	@Column(name = "VERSION")
	private Long version;

	@Column(name = "AMLFILESDATEFORMAT", length = 30)
	private String amlFilesDateFormat;

	@Column(name = "SMSRETRYINTERVALSEC")
	private Integer smsRetryIntervalSec;

	@Column(name = "CTRLIMIT", length = 30)
	private String ctrLimit;

	@Column(name = "PATHFORSIGNATUREUPLOAD", length = 30)
	private String pathForSignatureUpload;

	@Column(name = "ADSLOGINYN")
	private Integer adsLoginYn;

	@Column(name = "TENANTID", length = 30)
	private String tenantId;

	@Column(name = "ALLOWCONCURRENTLOGIN")
	private Integer allowConCurrentLogin;

	@Column(name = "CASEASSIGNEDPICKUPTAT", length = 30)
	private String caseAssignedPickupTat;

	@Column(name = "RULEVIOTIMEPERIOD", length = 30)
	private String ruleVioTimePeriod;

	@Column(name = "TENANTLANGUAGE", length = 30)
	private String tenantLanguage;

	@Column(name = "FILENAMEACCOUNTNOMINEE", length = 30)
	private String fileNameAccountNominee;

	@Column(name = "SESSIONTIMEOUTINMIN")
	private Integer sessionTimeOutInMin;

	@Column(name = "PWDCHGNOTIFICATIONMAXDAYS")
	private Integer pwdChgNotificationMaxDays;

	@Column(name = "CASEREMINDER2DAYS")
	private Integer caseReminder2Days;

	@Column(name = "VIOLATEDINPATH", length = 30)
	private String violatedInPath;

	@Column(name = "PATHFORPHOTOUPLOAD", length = 30)
	private String pathForPhotoUpload;

	@Column(name = "SWIFTWORKFLOWYN")
	private Integer swiftWorkFlowYn;

	@Column(name = "NFSCLIENTBASE24YN", length = 30)
	private String nfsClientBase24Yn;

	@Column(name = "NUMBERFORMAT", length = 30)
	private String numberFormat;

	@Column(name = "CANUSERLOGINATEOD")
	private Integer canUserLoginAtEod;

	@Column(name = "TENANTDEFCURRENCYCODE")
	private String tenantDefCurrencyCode;

	@Column(name = "ERROROUTPATH", length = 30)
	private String errorOutPath;

	@Column(name = "UPLOADINTEGRITYCHECKFLAG")
	private Integer uploadIntegrityCheckFlag;

	@Column(name = "TENANTDEFCOUNTRYCODE", length = 30)
	private String tenantDefCountryCode;

	@Column(name = "CONSECUTIVEBADLOGINCOUNT")
	private Integer consecutiveBadLoginCount;

	@Column(name = "FILENAMEJOINTACCOUNT", length = 30)
	private String fileNameJoIntegerAccount;

	@Column(name = "FILENAMEBICCODE", length = 30)
	private String fileNameBicCode;

	@Column(name = "SWIFTHOME", length = 30)
	private String swiftHome;

	@Column(name = "EMAILRETRYINTERVALSEC")
	private Integer emailRetryIntervalSec;

	@Column(name = "FILEINPATH", length = 30)
	private String fileInPath;

	@Column(name = "CASEWIPTATUOM", length = 30)
	private String caseWiptatuom;

	@Column(name = "STRTEMPLATEPATH", length = 30)
	private String strTemplatePath;

	@Column(name = "PATHFORSYSTEMFILES", length = 30)
	private String pathForSystemFiles;

	@Column(name = "CASEASSIGNEDPICKUPTATUOM", length = 30)
	private String caseAssignedPickupTatuom;

	@Column(name = "FILENAMECUSTADDR", length = 30)
	private String fileNameCustAddr;

	@Column(name = "CASEREMINDER1DAYS")
	private Integer caseReminder1Days;

	@Column(name = "HOSTEDSRVR", length = 30)
	private String hostedSrvr;

	@Column(name = "BATCHSIZE")
	private Integer BatchSize;

	@Column(name = "BACKUPINPATH", length = 30)
	private String backupInPath;

	@Column(name = "PATHFORCTRFILE", length = 30)
	private String pathForCtrFile;

	@Column(name = "SWIFTCOUNTRYRISKHIGH")
	private Integer swiftCountryRiskHigh;

	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;

	@Column(name = "PATHFORDOCUMENTUPLOAD", length = 30)
	private String pathForDocumentUpload;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DATEFORMAT")
	private Date dateFormat;

	@Column(name = "ERRORINPATH", length = 30)
	private String erroringPath;

	@Column(name = "REPORTBASEURL", length = 30)
	private String reportBaseUrl;

	@Column(name = "PWDCHGNOTIFICATIONDAYS")
	private Integer pwdChgNotificationDays;

	@Column(name = "DOWNLOADPATH", length = 30)
	private String downloadPath;

	@Column(name = "FILENAMECUSTMST", length = 30)
	private String fileNameCustMst;

	@Column(name = "LASTMODIFIEDBY", length = 50)
	private String lastModifiedBy;

	@Column(name = "REJECTEDOUTPATH", length = 30)
	private String rejectedOutPath;

	@Column(name = "AUTHSTATUS", length = 30)
	private String authStatus;

	@Column(name = "OVERALLPERCENTAGE")
	private Long overAllPercentage;

	@Column(name = "CASEREPORTAMT", length = 30)
	private String caseReportAmt;

	@Column(name = "SESSIONTIME")
	private Integer sessionTime;

	@Column(name = "AMOUNTLENGTH")
	private Integer amountLength;

	@Column(name = "WEEKENDDAYS", length = 30)
	private String weekendDays;

	@Column(name = "COUNTRY", length = 30)
	private String country;

	@Column(name = "CURRENCY", length = 30)
	private String currency;

	@Column(name = "PHTOTBLOB")
	private Blob photopath;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Blob getPhotopath() {
		return photopath;
	}

	public void setPhotopath(Blob photopath) {
		this.photopath = photopath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWriteOffTemplateFileLink() {
		return writeOffTemplateFileLink;
	}

	public void setWriteOffTemplateFileLink(String writeOffTemplateFileLink) {
		this.writeOffTemplateFileLink = writeOffTemplateFileLink;
	}

	public Integer getPaginationSize() {
		return paginationSize;
	}

	public void setPaginationSize(Integer paginationSize) {
		this.paginationSize = paginationSize;
	}

	public String getFileNameTxn() {
		return fileNameTxn;
	}

	public void setFileNameTxn(String fileNameTxn) {
		this.fileNameTxn = fileNameTxn;
	}

	public String getCtrTemplatePath() {
		return ctrTemplatePath;
	}

	public void setCtrTemplatePath(String ctrTemplatePath) {
		this.ctrTemplatePath = ctrTemplatePath;
	}

	public String getCleanOutPath() {
		return cleanOutPath;
	}

	public void setCleanOutPath(String cleanOutPath) {
		this.cleanOutPath = cleanOutPath;
	}

	public String getFileOutPath() {
		return fileOutPath;
	}

	public void setFileOutPath(String fileOutPath) {
		this.fileOutPath = fileOutPath;
	}

	public String getAlertMgmtbrCode() {
		return alertMgmtbrCode;
	}

	public void setAlertMgmtbrCode(String alertMgmtbrCode) {
		this.alertMgmtbrCode = alertMgmtbrCode;
	}

	public String getTenantBaseCurerncy() {
		return tenantBaseCurerncy;
	}

	public void setTenantBaseCurerncy(String tenantBaseCurerncy) {
		this.tenantBaseCurerncy = tenantBaseCurerncy;
	}

	public String getDecimalCharacter() {
		return decimalCharacter;
	}

	public void setDecimalCharacter(String decimalCharacter) {
		this.decimalCharacter = decimalCharacter;
	}

	public String getPathForAmlFiles() {
		return pathForAmlFiles;
	}

	public void setPathForAmlFiles(String pathForAmlFiles) {
		this.pathForAmlFiles = pathForAmlFiles;
	}

	public String getStrDownLoadPath() {
		return StrDownLoadPath;
	}

	public void setStrDownLoadPath(String strDownLoadPath) {
		StrDownLoadPath = strDownLoadPath;
	}

	public Integer getIsactiveLoginDays() {
		return isactiveLoginDays;
	}

	public void setIsactiveLoginDays(Integer isactiveLoginDays) {
		this.isactiveLoginDays = isactiveLoginDays;
	}

	public Integer getAutoLoginYn() {
		return autoLoginYn;
	}

	public void setAutoLoginYn(Integer autoLoginYn) {
		this.autoLoginYn = autoLoginYn;
	}

	public Integer getSwiftCountryRiskLow() {
		return swiftCountryRiskLow;
	}

	public void setSwiftCountryRiskLow(Integer swiftCountryRiskLow) {
		this.swiftCountryRiskLow = swiftCountryRiskLow;
	}

	public Integer getEmailRetryCount() {
		return emailRetryCount;
	}

	public void setEmailRetryCount(Integer emailRetryCount) {
		this.emailRetryCount = emailRetryCount;
	}

	public String getFileNameCustRelatedPerson() {
		return fileNameCustRelatedPerson;
	}

	public void setFileNameCustRelatedPerson(String fileNameCustRelatedPerson) {
		this.fileNameCustRelatedPerson = fileNameCustRelatedPerson;
	}

	public String getAlertTat() {
		return alertTat;
	}

	public void setAlertTat(String alertTat) {
		this.alertTat = alertTat;
	}

	public String getRejectedInPath() {
		return rejectedInPath;
	}

	public void setRejectedInPath(String rejectedInPath) {
		this.rejectedInPath = rejectedInPath;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getPathForSdnUpload() {
		return pathForSdnUpload;
	}

	public void setPathForSdnUpload(String pathForSdnUpload) {
		this.pathForSdnUpload = pathForSdnUpload;
	}

	public String getAmountFormat() {
		return amountFormat;
	}

	public void setAmountFormat(String amountFormat) {
		this.amountFormat = amountFormat;
	}

	public Integer getTopDownAlertEscalation() {
		return topDownAlertEscalation;
	}

	public void setTopDownAlertEscalation(Integer topDownAlertEscalation) {
		this.topDownAlertEscalation = topDownAlertEscalation;
	}

	public String getFileNameAccountMst() {
		return fileNameAccountMst;
	}

	public void setFileNameAccountMst(String fileNameAccountMst) {
		this.fileNameAccountMst = fileNameAccountMst;
	}

	public Integer getSwiftHoldRemainder1Days() {
		return swiftHoldRemainder1Days;
	}

	public void setSwiftHoldRemainder1Days(Integer swiftHoldRemainder1Days) {
		this.swiftHoldRemainder1Days = swiftHoldRemainder1Days;
	}

	public Integer getCaseReminder3Days() {
		return caseReminder3Days;
	}

	public void setCaseReminder3Days(Integer caseReminder3Days) {
		this.caseReminder3Days = caseReminder3Days;
	}

	public Integer getAllowSwitchingUserYn() {
		return allowSwitchingUserYn;
	}

	public void setAllowSwitchingUserYn(Integer allowSwitchingUserYn) {
		this.allowSwitchingUserYn = allowSwitchingUserYn;
	}

	public String getAlertForWardingBrcode() {
		return alertForWardingBrcode;
	}

	public void setAlertForWardingBrcode(String alertForWardingBrcode) {
		this.alertForWardingBrcode = alertForWardingBrcode;
	}

	public Integer getSmsRetryCount() {
		return smsRetryCount;
	}

	public void setSmsRetryCount(Integer smsRetryCount) {
		this.smsRetryCount = smsRetryCount;
	}

	public String getAlertTatuom() {
		return alertTatuom;
	}

	public void setAlertTatuom(String alertTatuom) {
		this.alertTatuom = alertTatuom;
	}

	public String getPathForRuleFileUpload() {
		return pathForRuleFileUpload;
	}

	public void setPathForRuleFileUpload(String pathForRuleFileUpload) {
		this.pathForRuleFileUpload = pathForRuleFileUpload;
	}

	public String getCaseMgmtBrcode() {
		return caseMgmtBrcode;
	}

	public void setCaseMgmtBrcode(String caseMgmtBrcode) {
		this.caseMgmtBrcode = caseMgmtBrcode;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Integer getNewCustomerDays() {
		return newCustomerDays;
	}

	public void setNewCustomerDays(Integer newCustomerDays) {
		this.newCustomerDays = newCustomerDays;
	}

	public String getPathForCaseFiles() {
		return pathForCaseFiles;
	}

	public void setPathForCaseFiles(String pathForCaseFiles) {
		this.pathForCaseFiles = pathForCaseFiles;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCaseForWardingBrcode() {
		return caseForWardingBrcode;
	}

	public void setCaseForWardingBrcode(String caseForWardingBrcode) {
		this.caseForWardingBrcode = caseForWardingBrcode;
	}

	public Integer getQuickLinks() {
		return QuickLinks;
	}

	public void setQuickLinks(Integer quickLinks) {
		QuickLinks = quickLinks;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getViolatedOutPath() {
		return violatedOutPath;
	}

	public void setViolatedOutPath(String violatedOutPath) {
		this.violatedOutPath = violatedOutPath;
	}

	public String getTxnTypeLastMonths() {
		return txnTypeLastMonths;
	}

	public void setTxnTypeLastMonths(String txnTypeLastMonths) {
		this.txnTypeLastMonths = txnTypeLastMonths;
	}

	public Integer getNewAccountDays() {
		return newAccountDays;
	}

	public void setNewAccountDays(Integer newAccountDays) {
		this.newAccountDays = newAccountDays;
	}

	public String getFileNameLookup() {
		return fileNameLookup;
	}

	public void setFileNameLookup(String fileNameLookup) {
		this.fileNameLookup = fileNameLookup;
	}

	public String getTxnTrendAvgMonths() {
		return txnTrendAvgMonths;
	}

	public void setTxnTrendAvgMonths(String txnTrendAvgMonths) {
		this.txnTrendAvgMonths = txnTrendAvgMonths;
	}

	public String getCleanInPath() {
		return cleanInPath;
	}

	public void setCleanInPath(String cleanInPath) {
		this.cleanInPath = cleanInPath;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public Date getDatePickerFormat() {
		return datePickerFormat;
	}

	public void setDatePickerFormat(Date datePickerFormat) {
		this.datePickerFormat = datePickerFormat;
	}

	public String getCtrDownLoadPath() {
		return ctrDownLoadPath;
	}

	public void setCtrDownLoadPath(String ctrDownLoadPath) {
		this.ctrDownLoadPath = ctrDownLoadPath;
	}

	public Integer getSwiftCountryRiskMed() {
		return swiftCountryRiskMed;
	}

	public void setSwiftCountryRiskMed(Integer swiftCountryRiskMed) {
		this.swiftCountryRiskMed = swiftCountryRiskMed;
	}

	public Integer getAppLoginYn() {
		return AppLoginYn;
	}

	public void setAppLoginYn(Integer appLoginYn) {
		AppLoginYn = appLoginYn;
	}

	public String getDigitSeparator() {
		return DigitSeparator;
	}

	public void setDigitSeparator(String digitSeparator) {
		DigitSeparator = digitSeparator;
	}

	public String getBackupOutPath() {
		return backupOutPath;
	}

	public void setBackupOutPath(String backupOutPath) {
		this.backupOutPath = backupOutPath;
	}

	public Long getNameMatchPercentage() {
		return nameMatchPercentage;
	}

	public void setNameMatchPercentage(Long nameMatchPercentage) {
		this.nameMatchPercentage = nameMatchPercentage;
	}

	public String getNumberDecimalCount() {
		return numberDecimalCount;
	}

	public void setNumberDecimalCount(String numberDecimalCount) {
		this.numberDecimalCount = numberDecimalCount;
	}

	public Integer getFreeTextProximity() {
		return freeTextProximity;
	}

	public void setFreeTextProximity(Integer freeTextProximity) {
		this.freeTextProximity = freeTextProximity;
	}

	public Integer getRecentLinks() {
		return recentLinks;
	}

	public void setRecentLinks(Integer recentLinks) {
		this.recentLinks = recentLinks;
	}

	public Integer getBottomUpAlertEscalation() {
		return bottomUpAlertEscalation;
	}

	public void setBottomUpAlertEscalation(Integer bottomUpAlertEscalation) {
		this.bottomUpAlertEscalation = bottomUpAlertEscalation;
	}

	public String getCaseWiptat() {
		return caseWiptat;
	}

	public void setCaseWiptat(String caseWiptat) {
		this.caseWiptat = caseWiptat;
	}

	public String getFileNameBankBranch() {
		return fileNameBankBranch;
	}

	public void setFileNameBankBranch(String fileNameBankBranch) {
		this.fileNameBankBranch = fileNameBankBranch;
	}

	public Integer getSwiftInFilePollInterval() {
		return swiftInFilePollInterval;
	}

	public void setSwiftInFilePollInterval(Integer swiftInFilePollInterval) {
		this.swiftInFilePollInterval = swiftInFilePollInterval;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getAmlFilesDateFormat() {
		return amlFilesDateFormat;
	}

	public void setAmlFilesDateFormat(String amlFilesDateFormat) {
		this.amlFilesDateFormat = amlFilesDateFormat;
	}

	public Integer getSmsRetryIntervalSec() {
		return smsRetryIntervalSec;
	}

	public void setSmsRetryIntervalSec(Integer smsRetryIntervalSec) {
		this.smsRetryIntervalSec = smsRetryIntervalSec;
	}

	public String getCtrLimit() {
		return ctrLimit;
	}

	public void setCtrLimit(String ctrLimit) {
		this.ctrLimit = ctrLimit;
	}

	public String getPathForSignatureUpload() {
		return pathForSignatureUpload;
	}

	public void setPathForSignatureUpload(String pathForSignatureUpload) {
		this.pathForSignatureUpload = pathForSignatureUpload;
	}

	public Integer getAdsLoginYn() {
		return adsLoginYn;
	}

	public void setAdsLoginYn(Integer adsLoginYn) {
		this.adsLoginYn = adsLoginYn;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getAllowConCurrentLogin() {
		return allowConCurrentLogin;
	}

	public void setAllowConCurrentLogin(Integer allowConCurrentLogin) {
		this.allowConCurrentLogin = allowConCurrentLogin;
	}

	public String getCaseAssignedPickupTat() {
		return caseAssignedPickupTat;
	}

	public void setCaseAssignedPickupTat(String caseAssignedPickupTat) {
		this.caseAssignedPickupTat = caseAssignedPickupTat;
	}

	public String getRuleVioTimePeriod() {
		return ruleVioTimePeriod;
	}

	public void setRuleVioTimePeriod(String ruleVioTimePeriod) {
		this.ruleVioTimePeriod = ruleVioTimePeriod;
	}

	public String getTenantLanguage() {
		return tenantLanguage;
	}

	public void setTenantLanguage(String tenantLanguage) {
		this.tenantLanguage = tenantLanguage;
	}

	public String getFileNameAccountNominee() {
		return fileNameAccountNominee;
	}

	public void setFileNameAccountNominee(String fileNameAccountNominee) {
		this.fileNameAccountNominee = fileNameAccountNominee;
	}

	public Integer getSessionTimeOutInMin() {
		return sessionTimeOutInMin;
	}

	public void setSessionTimeOutInMin(Integer sessionTimeOutInMin) {
		this.sessionTimeOutInMin = sessionTimeOutInMin;
	}

	public Integer getPwdChgNotificationMaxDays() {
		return pwdChgNotificationMaxDays;
	}

	public void setPwdChgNotificationMaxDays(Integer pwdChgNotificationMaxDays) {
		this.pwdChgNotificationMaxDays = pwdChgNotificationMaxDays;
	}

	public Integer getCaseReminder2Days() {
		return caseReminder2Days;
	}

	public void setCaseReminder2Days(Integer caseReminder2Days) {
		this.caseReminder2Days = caseReminder2Days;
	}

	public String getViolatedInPath() {
		return violatedInPath;
	}

	public void setViolatedInPath(String violatedInPath) {
		this.violatedInPath = violatedInPath;
	}

	public String getPathForPhotoUpload() {
		return pathForPhotoUpload;
	}

	public void setPathForPhotoUpload(String pathForPhotoUpload) {
		this.pathForPhotoUpload = pathForPhotoUpload;
	}

	public Integer getSwiftWorkFlowYn() {
		return swiftWorkFlowYn;
	}

	public void setSwiftWorkFlowYn(Integer swiftWorkFlowYn) {
		this.swiftWorkFlowYn = swiftWorkFlowYn;
	}

	public String getNfsClientBase24Yn() {
		return nfsClientBase24Yn;
	}

	public void setNfsClientBase24Yn(String nfsClientBase24Yn) {
		this.nfsClientBase24Yn = nfsClientBase24Yn;
	}

	public String getNumberFormat() {
		return numberFormat;
	}

	public void setNumberFormat(String numberFormat) {
		this.numberFormat = numberFormat;
	}

	public Integer getCanUserLoginAtEod() {
		return canUserLoginAtEod;
	}

	public void setCanUserLoginAtEod(Integer canUserLoginAtEod) {
		this.canUserLoginAtEod = canUserLoginAtEod;
	}

	public String getTenantDefCurrencyCode() {
		return tenantDefCurrencyCode;
	}

	public void setTenantDefCurrencyCode(String tenantDefCurrencyCode) {
		this.tenantDefCurrencyCode = tenantDefCurrencyCode;
	}

	public String getErrorOutPath() {
		return errorOutPath;
	}

	public void setErrorOutPath(String errorOutPath) {
		this.errorOutPath = errorOutPath;
	}

	public Integer getUploadIntegrityCheckFlag() {
		return uploadIntegrityCheckFlag;
	}

	public void setUploadIntegrityCheckFlag(Integer uploadIntegrityCheckFlag) {
		this.uploadIntegrityCheckFlag = uploadIntegrityCheckFlag;
	}

	public String getTenantDefCountryCode() {
		return tenantDefCountryCode;
	}

	public void setTenantDefCountryCode(String tenantDefCountryCode) {
		this.tenantDefCountryCode = tenantDefCountryCode;
	}

	public Integer getConsecutiveBadLoginCount() {
		return consecutiveBadLoginCount;
	}

	public void setConsecutiveBadLoginCount(Integer consecutiveBadLoginCount) {
		this.consecutiveBadLoginCount = consecutiveBadLoginCount;
	}

	public String getFileNameJoIntegerAccount() {
		return fileNameJoIntegerAccount;
	}

	public void setFileNameJoIntegerAccount(String fileNameJoIntegerAccount) {
		this.fileNameJoIntegerAccount = fileNameJoIntegerAccount;
	}

	public String getFileNameBicCode() {
		return fileNameBicCode;
	}

	public void setFileNameBicCode(String fileNameBicCode) {
		this.fileNameBicCode = fileNameBicCode;
	}

	public String getSwiftHome() {
		return swiftHome;
	}

	public void setSwiftHome(String swiftHome) {
		this.swiftHome = swiftHome;
	}

	public Integer getEmailRetryIntervalSec() {
		return emailRetryIntervalSec;
	}

	public void setEmailRetryIntervalSec(Integer emailRetryIntervalSec) {
		this.emailRetryIntervalSec = emailRetryIntervalSec;
	}

	public String getFileInPath() {
		return fileInPath;
	}

	public void setFileInPath(String fileInPath) {
		this.fileInPath = fileInPath;
	}

	public String getCaseWiptatuom() {
		return caseWiptatuom;
	}

	public void setCaseWiptatuom(String caseWiptatuom) {
		this.caseWiptatuom = caseWiptatuom;
	}

	public String getStrTemplatePath() {
		return strTemplatePath;
	}

	public void setStrTemplatePath(String strTemplatePath) {
		this.strTemplatePath = strTemplatePath;
	}

	public String getPathForSystemFiles() {
		return pathForSystemFiles;
	}

	public void setPathForSystemFiles(String pathForSystemFiles) {
		this.pathForSystemFiles = pathForSystemFiles;
	}

	public String getCaseAssignedPickupTatuom() {
		return caseAssignedPickupTatuom;
	}

	public void setCaseAssignedPickupTatuom(String caseAssignedPickupTatuom) {
		this.caseAssignedPickupTatuom = caseAssignedPickupTatuom;
	}

	public String getFileNameCustAddr() {
		return fileNameCustAddr;
	}

	public void setFileNameCustAddr(String fileNameCustAddr) {
		this.fileNameCustAddr = fileNameCustAddr;
	}

	public Integer getCaseReminder1Days() {
		return caseReminder1Days;
	}

	public void setCaseReminder1Days(Integer caseReminder1Days) {
		this.caseReminder1Days = caseReminder1Days;
	}

	public String getHostedSrvr() {
		return hostedSrvr;
	}

	public void setHostedSrvr(String hostedSrvr) {
		this.hostedSrvr = hostedSrvr;
	}

	public Integer getBatchSize() {
		return BatchSize;
	}

	public void setBatchSize(Integer batchSize) {
		BatchSize = batchSize;
	}

	public String getBackupInPath() {
		return backupInPath;
	}

	public void setBackupInPath(String backupInPath) {
		this.backupInPath = backupInPath;
	}

	public String getPathForCtrFile() {
		return pathForCtrFile;
	}

	public void setPathForCtrFile(String pathForCtrFile) {
		this.pathForCtrFile = pathForCtrFile;
	}

	public Integer getSwiftCountryRiskHigh() {
		return swiftCountryRiskHigh;
	}

	public void setSwiftCountryRiskHigh(Integer swiftCountryRiskHigh) {
		this.swiftCountryRiskHigh = swiftCountryRiskHigh;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getPathForDocumentUpload() {
		return pathForDocumentUpload;
	}

	public void setPathForDocumentUpload(String pathForDocumentUpload) {
		this.pathForDocumentUpload = pathForDocumentUpload;
	}

	public Date getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(Date dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getErroringPath() {
		return erroringPath;
	}

	public void setErroringPath(String erroringPath) {
		this.erroringPath = erroringPath;
	}

	public String getReportBaseUrl() {
		return reportBaseUrl;
	}

	public void setReportBaseUrl(String reportBaseUrl) {
		this.reportBaseUrl = reportBaseUrl;
	}

	public Integer getPwdChgNotificationDays() {
		return pwdChgNotificationDays;
	}

	public void setPwdChgNotificationDays(Integer pwdChgNotificationDays) {
		this.pwdChgNotificationDays = pwdChgNotificationDays;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public String getFileNameCustMst() {
		return fileNameCustMst;
	}

	public void setFileNameCustMst(String fileNameCustMst) {
		this.fileNameCustMst = fileNameCustMst;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getRejectedOutPath() {
		return rejectedOutPath;
	}

	public void setRejectedOutPath(String rejectedOutPath) {
		this.rejectedOutPath = rejectedOutPath;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public Long getOverAllPercentage() {
		return overAllPercentage;
	}

	public void setOverAllPercentage(Long overAllPercentage) {
		this.overAllPercentage = overAllPercentage;
	}

	public String getCaseReportAmt() {
		return caseReportAmt;
	}

	public void setCaseReportAmt(String caseReportAmt) {
		this.caseReportAmt = caseReportAmt;
	}

	public Integer getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(Integer sessionTime) {
		this.sessionTime = sessionTime;
	}

	public Integer getAmountLength() {
		return amountLength;
	}

	public void setAmountLength(Integer amountLength) {
		this.amountLength = amountLength;
	}

	public String getWeekendDays() {
		return weekendDays;
	}

	public void setWeekendDays(String weekendDays) {
		this.weekendDays = weekendDays;
	}
}
