package com.ksvsofttech.product.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EMAILTEMPLATE") 
public class EmailTemplate implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="TEMPLATENAME", length = 50)
	private String templateName;
	
	@Column(name="EMAILSUB", length = 50)
	private String emailSub;

	@Column(name="EMAILMSG", length = 50)
	private String emailMsg;
	
	@Column(name="EMAILBCC", length = 8000)
	private String emailBcc;
	
	@Column(name="EMAILFROM", length = 50)
	private String emailFrom;
	
	@Column(name="TENANTID", length = 10)
	private String tenantId;
	
	@Column(name="LASTMODIFIEDDATE")
	private Date lastModifiedDate;
	
	@Column(name="EMAILFLAG", length = 10)
	private Integer emailFlag;
	
	@Column(name="ISACTIVE", length = 10)
	private String isActive;
	
	@Column(name="PROCESSEMAILPRIORITY", length = 50)
	private String processEmailPriority;
	
	@Column(name="PROCESSCODE", length = 50)
	private String processCode;
	
	@Temporal(TemporalType.DATE)
	@Column(name="CREATEDDATE")
	private Date createdDate;
	
	@Column(name="TEMPLATEDESC", length = 100)
	private String templateDesc;
	
	@Column(name="LASTMODIFIEDBY", length = 50)
	private String lastModifiedBy;
	
	@Column(name="EMAILCC", length = 8000)
	private String emailCc;
	
	@Column(name="DESCRIPTION", length = 100)
	private String description;
	
	@Column(name="VERSION", length = 10)
	private Integer version;
	
	@Column(name="CREATEDBY", length = 50)
	private String createdBy;
	
	@Column(name="EMAILTO", length = 8000)
	private String emailTo;
	
	@Column(name="TEMPLATECODE", length = 50)
	private String templateCode;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailSub() {
		return emailSub;
	}

	public void setEmailSub(String emailSub) {
		this.emailSub = emailSub;
	}

	public String getEmailMsg() {
		return emailMsg;
	}

	public void setEmailMsg(String emailMsg) {
		this.emailMsg = emailMsg;
	}

	public String getEmailBcc() {
		return emailBcc;
	}

	public void setEmailBcc(String emailBcc) {
		this.emailBcc = emailBcc;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getTenantId() {
		return tenantId;
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

	public Integer getEmailFlag() {
		return emailFlag;
	}

	public void setEmailFlag(Integer emailFlag) {
		this.emailFlag = emailFlag;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getProcessEmailPriority() {
		return processEmailPriority;
	}

	public void setProcessEmailPriority(String processEmailPriority) {
		this.processEmailPriority = processEmailPriority;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
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

	public String getEmailCc() {
		return emailCc;
	}

	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
}