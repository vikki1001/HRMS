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
@Table(name = "TENANTMST")
public class TenantMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "AUTHSTATUS", length = 50)
	private String authStatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "ESTBDATE")
	private Date estbDate;

	@Column(name = "ISACTIVE", length = 10)
	private String isActive;

	@Column(name = "PARENTTENANTID", length = 50)
	private String parentTenantId;

	@Column(name = "REPORTINGBRANCHCODE", length = 50)
	private String reportingBranchCode;

	@Column(name = "SWIFTHOME", length = 50)
	private String swiftHome;

	@Column(name = "SWIFTINFILEINTERVAL", length = 20)
	private String swiftInFileInterval;

	@Column(name = "TENANTABBRNAME", length = 50)
	private String tenantAbbrName;

	@Column(name = "TENANTID", length = 20)
	private String tenantId;

	@Column(name = "TENANTLOGO", length = 50)
	private String tenantLogo;

	@Column(name = "TENANTNAME", length = 50)
	private String tenantName;

	@Column(name = "TENANTLOGOPATH", length = 50)
	private String tenantLogoPath;

	@Column(name = "TENANTFAVICONPATH", length = 50)
	private String tenantFaviconPath;

	@Column(name = "TENANTTHEME", length = 50)
	private String tenantTheme;

	@Column(name = "TENANTTYPE", length = 50)
	private String tenantType;

	@Column(name = "TENANTURL", length = 255)
	private String tenantUrl;

	@Column(name = "SECTORCODE", length = 50)
	private String sectorCode;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;

	@Column(name = "LASTMODIFIEDDATE")
	private Date lastModifiedDate;

	@Column(name = "LASTMODIFIEDBY", length = 50)
	private String lastModifiedBy;

	@Column(name = "COMPANYNAME")
	private String companyName;

	@Column(name = "COMPANYADDRESS")
	private String companyAddress;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public Date getEstbDate() {
		return estbDate;
	}

	public void setEstbDate(Date estbDate) {
		this.estbDate = estbDate;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getParentTenantId() {
		return parentTenantId;
	}

	public void setParentTenantId(String parentTenantId) {
		this.parentTenantId = parentTenantId;
	}

	public String getReportingBranchCode() {
		return reportingBranchCode;
	}

	public void setReportingBranchCode(String reportingBranchCode) {
		this.reportingBranchCode = reportingBranchCode;
	}

	public String getSwiftHome() {
		return swiftHome;
	}

	public void setSwiftHome(String swiftHome) {
		this.swiftHome = swiftHome;
	}

	public String getSwiftInFileInterval() {
		return swiftInFileInterval;
	}

	public void setSwiftInFileInterval(String swiftInFileInterval) {
		this.swiftInFileInterval = swiftInFileInterval;
	}

	public String getTenantAbbrName() {
		return tenantAbbrName;
	}

	public void setTenantAbbrName(String tenantAbbrName) {
		this.tenantAbbrName = tenantAbbrName;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantLogo() {
		return tenantLogo;
	}

	public void setTenantLogo(String tenantLogo) {
		this.tenantLogo = tenantLogo;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
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

	public String getTenantTheme() {
		return tenantTheme;
	}

	public void setTenantTheme(String tenantTheme) {
		this.tenantTheme = tenantTheme;
	}

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

	public String getTenantUrl() {
		return tenantUrl;
	}

	public void setTenantUrl(String tenantUrl) {
		this.tenantUrl = tenantUrl;
	}

	public String getSectorCode() {
		return sectorCode;
	}

	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

}
