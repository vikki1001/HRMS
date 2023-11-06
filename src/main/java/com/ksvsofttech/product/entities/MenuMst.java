
package com.ksvsofttech.product.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MENUMST")
public class MenuMst {

	@Id
	@GeneratedValue
	@Column(name = "MENUID", length = 10)
	private Integer menuId;

	@Column(name = "USERID", length = 10)
	private String userId;

	@Column(name = "ISMAKERCHECKER", length = 50)
	private Integer iSMakerChecker;

	@Column(name = "SUBSECTIONCODE", length = 30)
	private String subSectionCode;

	@Column(name = "AUTHMOD", length = 20)
	private Integer authMode;

	@Column(name = "TENANTID", length = 10)
	private String tenantId;

	@Column(name = "MENUEDITPTH", length = 200)
	private String menuFilePath;

	@Column(name = "LASTMODBY")
	private Date lastModifiedBy;

	@Column(name = "LASTMODDATE", length = 50)
	private Date lastModifiedDate;

	@Column(name = "ISVISIBLE", length = 20)
	private Integer isVisible;

	@Column(name = "MENUEDITPATH", length = 20)
	private String menuDiPath;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "SECTIONDISPLAYNAME", length = 100)
	private String sectionDisplayName;

	@Column(name = "DESCRIPTION", length = 100)
	private String description;

	@Column(name = "AUTHACTION", length = 50)
	private String authAction;

	@Column(name = "ISQUICKLINK", length = 50)
	private String isQuickLink;

	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;

	@Column(name = "MENUNAME", length = 100)
	private String menuName;

	@Column(name = "MENUCODE", length = 50)
	private String menuCode;

	@Column(name = "MENUICON", length = 100)
	private String menuIcon;

	@Column(name = "ISACTIVE", columnDefinition = "varchar(10) default '1'")
	private String isActive;

	@Column(name = "SECTIONCODE", length = 30)
	private String sectionCode;

	@Column(name = "LANGUAGECOD", length = 30)
	private String languageCode;

	@Column(name = "SUBSECTIONDISPLAYNAME", length = 50)
	private String subSectionDisplayName;

	@Column(name = "MENUMOD", length = 30)
	private String menuMode;

	@Column(name = "VERSION", length = 20)
	private Integer version;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getiSMakerChecker() {
		return iSMakerChecker;
	}

	public void setiSMakerChecker(Integer iSMakerChecker) {
		this.iSMakerChecker = iSMakerChecker;
	}

	public String getSubSectionCode() {
		return subSectionCode;
	}

	public void setSubSectionCode(String subSectionCode) {
		this.subSectionCode = subSectionCode;
	}

	public Integer getAuthMode() {
		return authMode;
	}

	public void setAuthMode(Integer authMode) {
		this.authMode = authMode;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getMenuFilePath() {
		return menuFilePath;
	}

	public void setMenuFilePath(String menuFilePath) {
		this.menuFilePath = menuFilePath;
	}

	public Date getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(Date lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Integer getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}

	public String getMenuDiPath() {
		return menuDiPath;
	}

	public void setMenuDiPath(String menuDiPath) {
		this.menuDiPath = menuDiPath;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getSectionDisplayName() {
		return sectionDisplayName;
	}

	public void setSectionDisplayName(String sectionDisplayName) {
		this.sectionDisplayName = sectionDisplayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthAction() {
		return authAction;
	}

	public void setAuthAction(String authAction) {
		this.authAction = authAction;
	}

	public String getIsQuickLink() {
		return isQuickLink;
	}

	public void setIsQuickLink(String isQuickLink) {
		this.isQuickLink = isQuickLink;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getSubSectionDisplayName() {
		return subSectionDisplayName;
	}

	public void setSubSectionDisplayName(String subSectionDisplayName) {
		this.subSectionDisplayName = subSectionDisplayName;
	}

	public String getMenuMode() {
		return menuMode;
	}

	public void setMenuMode(String menuMode) {
		this.menuMode = menuMode;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authAction == null) ? 0 : authAction.hashCode());
		result = prime * result + ((authMode == null) ? 0 : authMode.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((iSMakerChecker == null) ? 0 : iSMakerChecker.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((isQuickLink == null) ? 0 : isQuickLink.hashCode());
		result = prime * result + ((isVisible == null) ? 0 : isVisible.hashCode());
		result = prime * result + ((languageCode == null) ? 0 : languageCode.hashCode());
		result = prime * result + ((lastModifiedBy == null) ? 0 : lastModifiedBy.hashCode());
		result = prime * result + ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
		result = prime * result + ((menuCode == null) ? 0 : menuCode.hashCode());
		result = prime * result + ((menuDiPath == null) ? 0 : menuDiPath.hashCode());
		result = prime * result + ((menuFilePath == null) ? 0 : menuFilePath.hashCode());
		result = prime * result + ((menuIcon == null) ? 0 : menuIcon.hashCode());
		result = prime * result + ((menuMode == null) ? 0 : menuMode.hashCode());
		result = prime * result + ((menuName == null) ? 0 : menuName.hashCode());
		result = prime * result + ((sectionCode == null) ? 0 : sectionCode.hashCode());
		result = prime * result + ((sectionDisplayName == null) ? 0 : sectionDisplayName.hashCode());
		result = prime * result + ((subSectionCode == null) ? 0 : subSectionCode.hashCode());
		result = prime * result + ((subSectionDisplayName == null) ? 0 : subSectionDisplayName.hashCode());
		result = prime * result + ((tenantId == null) ? 0 : tenantId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuMst other = (MenuMst) obj;
		if (authAction == null) {
			if (other.authAction != null)
				return false;
		} else if (!authAction.equals(other.authAction))
			return false;
		if (authMode == null) {
			if (other.authMode != null)
				return false;
		} else if (!authMode.equals(other.authMode))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (iSMakerChecker == null) {
			if (other.iSMakerChecker != null)
				return false;
		} else if (!iSMakerChecker.equals(other.iSMakerChecker))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (isQuickLink == null) {
			if (other.isQuickLink != null)
				return false;
		} else if (!isQuickLink.equals(other.isQuickLink))
			return false;
		if (isVisible == null) {
			if (other.isVisible != null)
				return false;
		} else if (!isVisible.equals(other.isVisible))
			return false;
		if (languageCode == null) {
			if (other.languageCode != null)
				return false;
		} else if (!languageCode.equals(other.languageCode))
			return false;
		if (lastModifiedBy == null) {
			if (other.lastModifiedBy != null)
				return false;
		} else if (!lastModifiedBy.equals(other.lastModifiedBy))
			return false;
		if (lastModifiedDate == null) {
			if (other.lastModifiedDate != null)
				return false;
		} else if (!lastModifiedDate.equals(other.lastModifiedDate))
			return false;
		if (menuCode == null) {
			if (other.menuCode != null)
				return false;
		} else if (!menuCode.equals(other.menuCode))
			return false;
		if (menuDiPath == null) {
			if (other.menuDiPath != null)
				return false;
		} else if (!menuDiPath.equals(other.menuDiPath))
			return false;
		if (menuFilePath == null) {
			if (other.menuFilePath != null)
				return false;
		} else if (!menuFilePath.equals(other.menuFilePath))
			return false;
		if (menuIcon == null) {
			if (other.menuIcon != null)
				return false;
		} else if (!menuIcon.equals(other.menuIcon))
			return false;
		if (menuMode == null) {
			if (other.menuMode != null)
				return false;
		} else if (!menuMode.equals(other.menuMode))
			return false;
		if (menuName == null) {
			if (other.menuName != null)
				return false;
		} else if (!menuName.equals(other.menuName))
			return false;
		if (sectionCode == null) {
			if (other.sectionCode != null)
				return false;
		} else if (!sectionCode.equals(other.sectionCode))
			return false;
		if (sectionDisplayName == null) {
			if (other.sectionDisplayName != null)
				return false;
		} else if (!sectionDisplayName.equals(other.sectionDisplayName))
			return false;
		if (subSectionCode == null) {
			if (other.subSectionCode != null)
				return false;
		} else if (!subSectionCode.equals(other.subSectionCode))
			return false;
		if (subSectionDisplayName == null) {
			if (other.subSectionDisplayName != null)
				return false;
		} else if (!subSectionDisplayName.equals(other.subSectionDisplayName))
			return false;
		if (tenantId == null) {
			if (other.tenantId != null)
				return false;
		} else if (!tenantId.equals(other.tenantId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
}
