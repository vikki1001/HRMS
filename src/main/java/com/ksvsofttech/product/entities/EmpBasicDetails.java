package com.ksvsofttech.product.entities;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ksvsofttech.product.appraisal.EmpKRA;

@Entity
@Table(name = "EMPBASICDETAILS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmpBasicDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMPLOYEEID", length = 50, unique = true)
	private String empId;

	@Column(name = "COMPANYEMAILID", length = 50)
	private String coEmailId;

	@Column(name = "FIRSTNAME", length = 25)
	private String firstName;

	@Column(name = "MIDDLENAME", length = 25)
	private String middleName;

	@Column(name = "LASTNAME", length = 25)
	private String lastName;

	@Column(name = "FULLNAME", length = 100)
	private String fullName;

	@Column(name = "GENDER", length = 30)
	private String gender;

	@Column(name = "GRADE", length = 10)
	private String grade;

	@Column(name = "LABEL", length = 10)
	private String label;

	@Column(name = "TENANTID", length = 10)
	private String tenantId;

	@Column(name = "ISACTIVE", length = 10)
	private String isActive;

	@Column(name = "DEPARTMENTNAME", length = 30)
	private String departName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;

	@Column(name = "LASTMODDATE")
	private Date lastModifiedDate;

	@Column(name = "LASTMODBY", length = 50)
	private String lastModifiedBy;

	@Lob
	@Column(name = "FILE")
	private byte[] file;

	@OneToOne(mappedBy = "empBasicDetails", cascade = CascadeType.ALL)
	private EmpPersonalDetails empPersonalDetails;

	@OneToOne(mappedBy = "empBasicDetails", cascade = CascadeType.ALL)
	private EmpPassportDetails empPassportDetails;

	@OneToOne(mappedBy = "empBasicDetails", cascade = CascadeType.ALL)
	private EmpSalaryDetails empSalaryDetails;

	@OneToOne(mappedBy = "empBasicDetails", cascade = CascadeType.ALL)
	private EmpWorkDetails empWorkDetails;

	@OneToOne(mappedBy = "empBasicDetails", cascade = CascadeType.ALL)
	private EmpEmergContactDetails empEmergContactDetails;

	@OneToOne(mappedBy = "empBasicDetails", cascade = CascadeType.ALL)
	private EmpAddressDetails empAddressDetails;

	@OneToOne(mappedBy = "empBasicDetails", cascade = CascadeType.ALL)
	private EmpSkillDetails empSkillDetails;

	@OneToOne(mappedBy = "empBasicDetails", cascade = CascadeType.ALL)
	private EmpCertificationDetails empCertificationDetails;

	@OneToOne(mappedBy = "empBasicDetails", cascade = CascadeType.ALL)
	private EmpKRA empKRA;

	public EmpKRA getEmpKRA() {
		return empKRA;
	}

	public void setEmpKRA(EmpKRA empKRA) {
		this.empKRA = empKRA;
	}

	public EmpBasicDetails() {
	}

	public EmpBasicDetails(byte[] file) {
		this.file = file;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getCoEmailId() {
		return coEmailId;
	}

	public void setCoEmailId(String coEmailId) {
		this.coEmailId = coEmailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
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

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public EmpPersonalDetails getEmpPersonalDetails() {
		return empPersonalDetails;
	}

	public void setEmpPersonalDetails(EmpPersonalDetails empPersonalDetails) {
		this.empPersonalDetails = empPersonalDetails;
	}

	public EmpPassportDetails getEmpPassportDetails() {
		return empPassportDetails;
	}

	public void setEmpPassportDetails(EmpPassportDetails empPassportDetails) {
		this.empPassportDetails = empPassportDetails;
	}

	public EmpSalaryDetails getEmpSalaryDetails() {
		return empSalaryDetails;
	}

	public void setEmpSalaryDetails(EmpSalaryDetails empSalaryDetails) {
		this.empSalaryDetails = empSalaryDetails;
	}

	public EmpWorkDetails getEmpWorkDetails() {
		return empWorkDetails;
	}

	public void setEmpWorkDetails(EmpWorkDetails empWorkDetails) {
		this.empWorkDetails = empWorkDetails;
	}

	public EmpEmergContactDetails getEmpEmergContactDetails() {
		return empEmergContactDetails;
	}

	public void setEmpEmergContactDetails(EmpEmergContactDetails empEmergContactDetails) {
		this.empEmergContactDetails = empEmergContactDetails;
	}

	public EmpAddressDetails getEmpAddressDetails() {
		return empAddressDetails;
	}

	public void setEmpAddressDetails(EmpAddressDetails empAddressDetails) {
		this.empAddressDetails = empAddressDetails;
	}

	public EmpSkillDetails getEmpSkillDetails() {
		return empSkillDetails;
	}

	public void setEmpSkillDetails(EmpSkillDetails empSkillDetails) {
		this.empSkillDetails = empSkillDetails;
	}

	public EmpCertificationDetails getEmpCertificationDetails() {
		return empCertificationDetails;
	}

	public void setEmpCertificationDetails(EmpCertificationDetails empCertificationDetails) {
		this.empCertificationDetails = empCertificationDetails;
	}

	@Override
	public String toString() {
		return "EmpBasicDetails [empId=" + empId + ", fullName=" + fullName + "]";
	}

	public static class Comparators {
		public static Comparator<EmpBasicDetails> EMPLOYEEID = new Comparator<EmpBasicDetails>() {

			@Override
			public int compare(EmpBasicDetails o1, EmpBasicDetails o2) {
				return o1.empId.compareTo(o2.empId);
			}
		};

		public static Comparator<EmpBasicDetails> TENANTID = new Comparator<EmpBasicDetails>() {

			@Override
			public int compare(EmpBasicDetails o1, EmpBasicDetails o2) {
				return o1.tenantId.compareTo(o2.tenantId);
			}
		};

		public static Comparator<EmpBasicDetails> DEPARTNAME = new Comparator<EmpBasicDetails>() {

			@Override
			public int compare(EmpBasicDetails o1, EmpBasicDetails o2) {
				return o1.departName.compareTo(o2.departName);
			}
		};
	}
}