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
@Table(name = "SMSTEMPLATE")
public class SMSTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TENANTID", length = 10)
	private String tenantId;

	@Column(name = "LASTMODIFIEDDATE")
	private Date lastModifiedDate;

	@Column(name = "ADDITIONALNO", length = 8000)
	private String additionalNo;

	@Column(name = "SMSMSG", length = 50)
	private String smsMsg;

	@Column(name = "ISACTIVE", length = 10)
	private String isActive;

	@Column(name = "DEFAULTNO", length = 50)
	private String defaultNo;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "TEMPLATEDESC", length = 100)
	private String templateDesc;

	@Column(name = "LASTMODIFIEDBY", length = 50)
	private String lastModifiedBy;

	@Column(name = "SMSFLAG", length = 10)
	private Integer smsFlag;

	@Column(name = "VERSION", length = 10)
	private Integer version;

	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;

	public String getTenantId() {
		return tenantId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSmsMsg() {
		return smsMsg;
	}

	public void setSmsMsg(String smsMsg) {
		this.smsMsg = smsMsg;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getAdditionalNo() {
		return additionalNo;
	}

	public void setAdditionalNo(String additionalNo) {
		this.additionalNo = additionalNo;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getDefaultNo() {
		return defaultNo;
	}

	public void setDefaultNo(String defaultNo) {
		this.defaultNo = defaultNo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getTemplateDesc() {
		return templateDesc;
	}

	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Integer getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(Integer smsFlag) {
		this.smsFlag = smsFlag;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
