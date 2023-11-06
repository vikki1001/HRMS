package com.ksvsofttech.product.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUDITRECORD")
public class AuditRecord extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "SUBSECTIONCODE")
	private String subSectionCode;

	@Column(name = "TENANTID")
	private String tenantId;

	@Column(name = "ACTIVITYCODE")
	private String activityCode;

	@Column(name = "RECORDIDENTIFIER")
	private String recordIdentifier;

	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "IPADDR")
	private String ipAddr;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "LOGINID")
	private String loginId;

	@Column(name = "BRANCHCODE")
	private String branchCode;

	@Column(name = "APPROVEDBY")
	private String approvedBy;

	@Column(name = "ACTIONFROM")
	private String actionFrom;

	@Column(name = "APPROVEDDTTM")
	private Date approvedDateTime;

	@Column(name = "ROLECODE")
	private String roleCode;

	@Column(name = "REFID")
	private String refId;

	@Column(name = "SECTIONCODE")
	private String sectionCode;

	@Column(name = "SUBMENUCODE")
	private String subMenuCode;

	@Column(name = "HOSTNAME")
	private String hostName;

	@Column(name = "VERSION")
	private String version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubSectionCode() {
		return subSectionCode;
	}

	public void setSubSectionCode(String subSectionCode) {
		this.subSectionCode = subSectionCode;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getRecordIdentifier() {
		return recordIdentifier;
	}

	public void setRecordIdentifier(String recordIdentifier) {
		this.recordIdentifier = recordIdentifier;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getActionFrom() {
		return actionFrom;
	}

	public void setActionFrom(String actionFrom) {
		this.actionFrom = actionFrom;
	}

	public Date getApprovedDateTime() {
		return approvedDateTime;
	}

	public void setApprovedDateTime(Date approvedDateTime) {
		this.approvedDateTime = approvedDateTime;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getSubMenuCode() {
		return subMenuCode;
	}

	public void setSubMenuCode(String subMenuCode) {
		this.subMenuCode = subMenuCode;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
