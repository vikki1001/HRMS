package com.ksvsofttech.product.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLEMENUACTIONACCESSMST")
public class RoleMenuActionAccess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ROLEMENUCODE", length = 10)
	private String roleMenuCode;

	@Column(name = "TENANTID", length = 10)
	private String tenantId;

	@Column(name = "MODULECODE", length = 40)
	private String moduleCode;

	@Column(name = "LASTMODIFIEDDATE")
	private Date lastModifiedDate;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "DESCRIPTION", length = 90)
	private String description;

	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;

	@Column(name = "MENUCODE", length = 90)
	private String menuCode;

	@Column(name = "ROLECODE", length = 40)
	private String roleCode;

	@Column(name = "ISACTIVE", length = 10)
	private String isActive;

	@Column(name = "LASTMODIFIEDBY", length = 50)
	private String lastModifiedBy;

	@Column(name = "MENUDISPLAYRANK", length = 40)
	private Integer menuDisplayRank;

	@Column(name = "AUTHSTATUS", nullable = true, length = 10)
	private String authStatus;

	@Column(name = "VERSION", nullable = true, length = 90)
	private Integer version;

	@Column(name = "VIEWACTION", nullable = true, length = 90)
	private Integer viewAction;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getRoleMenuCode() {
		return roleMenuCode;
	}

	public void setRoleMenuCode(String roleMenuCode) {
		this.roleMenuCode = roleMenuCode;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Integer getMenuDisplayRank() {
		return menuDisplayRank;
	}

	public void setMenuDisplayRank(Integer menuDisplayRank) {
		this.menuDisplayRank = menuDisplayRank;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getViewAction() {
		return viewAction;
	}

	public void setViewAction(Integer viewAction) {
		this.viewAction = viewAction;
	}

}
