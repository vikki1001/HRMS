package com.ksvsofttech.product.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EMPPERSONALDETAILS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmpPersonalDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "BIRTHDATE")
	private Date birthDate;

	@Column(name = "MARITALSTATUS", length = 30)
	private String maritalStatus;

	@Column(name = "AGE")
	private String age;

	@Column(name = "ADDHARNUMBER", length = 15)
	private String addharNumber;

	@Column(name = "PLACEOFBIRTH", length = 50)
	private String placeOfBirth;

	@Column(name = "PANNUMBER", length = 20)
	private String panNumber;

	@Column(name = "BLOODGROUP", length = 10)
	private String bloodGroup;

	@Column(name = "PERSONALEMAILID1", length = 50)
	private String personalEmailId1;

	@Column(name = "PERSONALEMAILID2", length = 50)
	private String personalEmailId2;

	@Column(name = "CONTACTNO", length = 10)
	private String contactNo;

	@Column(name = "ALTERNATECONTACTNO", length = 50)
	private String alternateContactNo;

	@Column(name = "RELIGION", length = 20)
	private String religion;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;

	@Column(name = "LASTMODDATE")
	private Date lastModifiedDate;

	@Column(name = "LASTMODBY", length = 50)
	private String lastModifiedBy;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMPLOYEEID", unique = true)
	private EmpBasicDetails empBasicDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddharNumber() {
		return addharNumber;
	}

	public void setAddharNumber(String addharNumber) {
		this.addharNumber = addharNumber;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getPersonalEmailId1() {
		return personalEmailId1;
	}

	public void setPersonalEmailId1(String personalEmailId1) {
		this.personalEmailId1 = personalEmailId1;
	}

	public String getPersonalEmailId2() {
		return personalEmailId2;
	}

	public void setPersonalEmailId2(String personalEmailId2) {
		this.personalEmailId2 = personalEmailId2;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAlternateContactNo() {
		return alternateContactNo;
	}

	public void setAlternateContactNo(String alternateContactNo) {
		this.alternateContactNo = alternateContactNo;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
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

	public EmpBasicDetails getEmpBasicDetails() {
		return empBasicDetails;
	}

	public void setEmpBasicDetails(EmpBasicDetails empBasicDetails) {
		this.empBasicDetails = empBasicDetails;
	}

}
