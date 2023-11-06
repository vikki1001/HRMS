package com.ksvsofttech.product.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PASSWORDPOLICY")
public class PasswordPolicy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TENANTID")
	private String tenatId;

	@Column(name = "MINPWDLENGTH")
	private Integer minimumPasswordLength;

	@Column(name = "MAXBADLOGINCNT")
	private Integer maximumBadLoginCount;

	@Column(name = "ISMIXCASE")
	private Integer isMixCase;

	@Column(name = "ISACTIVE")
	private String isActive;

	@Column(name = "MINSPECIALCHARS")
	private Integer minimumSpecialCharacter;

	@Column(name = "MINALPHALENGTH")
	private Integer minimumAlphabetsLength;

	@Column(name = "LASTNPASSWORDSNOTALLOWED")
	private Integer lastPasswordNotAllowedCount;

	@Column(name = "MAXPWDLENGTH")
	private Integer maximumPasswordLength;

	@Column(name = "ISUSERIDALLOWED")
	private String isUserIdAllowed;

	@Column(name = "SAMECONSCHARNOTALLOWEDCOUNT")
	private Integer sameConsCharNotAllowedCount;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "VERSION")
	private Long version;

	@Column(name = "MINDIGITS")
	private Integer minimumDigits;

	@Column(name = "PWDVALIDATEDAYS")
	private Integer passwordValidateDays;

	@Column(name = "PWDNOTIFICATIONDAYS")
	private Integer passwordNotificationDays;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "CREATEDDBY", length = 50)
	private String createdBy;

	@Column(name = "LASTMODDATE")
	private Date lastModifiedDate;

	@Column(name = "LASTMODBY", length = 50)
	private String lastModifiedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenatId() {
		return tenatId;
	}

	public void setTenatId(String tenatId) {
		this.tenatId = tenatId;
	}

	public Integer getMinimumPasswordLength() {
		return minimumPasswordLength;
	}

	public void setMinimumPasswordLength(Integer minimumPasswordLength) {
		this.minimumPasswordLength = minimumPasswordLength;
	}

	public Integer getMaximumBadLoginCount() {
		return maximumBadLoginCount;
	}

	public void setMaximumBadLoginCount(Integer maximumBadLoginCount) {
		this.maximumBadLoginCount = maximumBadLoginCount;
	}

	public Integer getIsMixCase() {
		return isMixCase;
	}

	public void setIsMixCase(Integer isMixCase) {
		this.isMixCase = isMixCase;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Integer getMinimumSpecialCharacter() {
		return minimumSpecialCharacter;
	}

	public void setMinimumSpecialCharacter(Integer minimumSpecialCharacter) {
		this.minimumSpecialCharacter = minimumSpecialCharacter;
	}

	public Integer getMinimumAlphabetsLength() {
		return minimumAlphabetsLength;
	}

	public void setMinimumAlphabetsLength(Integer minimumAlphabetsLength) {
		this.minimumAlphabetsLength = minimumAlphabetsLength;
	}

	public Integer getLastPasswordNotAllowedCount() {
		return lastPasswordNotAllowedCount;
	}

	public void setLastPasswordNotAllowedCount(Integer lastPasswordNotAllowedCount) {
		this.lastPasswordNotAllowedCount = lastPasswordNotAllowedCount;
	}

	public Integer getMaximumPasswordLength() {
		return maximumPasswordLength;
	}

	public void setMaximumPasswordLength(Integer maximumPasswordLength) {
		this.maximumPasswordLength = maximumPasswordLength;
	}

	public String getIsUserIdAllowed() {
		return isUserIdAllowed;
	}

	public void setIsUserIdAllowed(String isUserIdAllowed) {
		this.isUserIdAllowed = isUserIdAllowed;
	}

	public Integer getSameConsCharNotAllowedCount() {
		return sameConsCharNotAllowedCount;
	}

	public void setSameConsCharNotAllowedCount(Integer sameConsCharNotAllowedCount) {
		this.sameConsCharNotAllowedCount = sameConsCharNotAllowedCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Integer getMinimumDigits() {
		return minimumDigits;
	}

	public void setMinimumDigits(Integer minimumDigits) {
		this.minimumDigits = minimumDigits;
	}

	public Integer getPasswordValidateDays() {
		return passwordValidateDays;
	}

	public void setPasswordValidateDays(Integer passwordValidateDays) {
		this.passwordValidateDays = passwordValidateDays;
	}

	public Integer getPasswordNotificationDays() {
		return passwordNotificationDays;
	}

	public void setPasswordNotificationDays(Integer passwordNotificationDays) {
		this.passwordNotificationDays = passwordNotificationDays;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

}
