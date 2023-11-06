package com.ksvsofttech.product.appraisal;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.ksvsofttech.product.entities.EmpBasicDetails;

@Entity
@Table(name = "EMPKRA")
public class EmpKRA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "KRAID")
	private String kraId;

	@Column(name = "USERID")
	private String userId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMPID")
	private EmpBasicDetails empBasicDetails;

	@Column(name = "DEPID")
	private String depId;

	@Column(name = "GRADE")
	private String grade;

	@Column(name = "KRAI")
	private String kraI;

	@Column(name = "KRAII")
	private String kraII;

	@Column(name = "KRAIII")
	private String kraIII;

	@Column(name = "KRAIV")
	private String kraIV;

	@Column(name = "KRAV")
	private String kraV;

	@Column(name = "KRAVI")
	private String kraVI;

	@Column(name = "KRAVII")
	private String kraVII;

	@Column(name = "KRAVIII")
	private String kraVIII;

	@Column(name = "KRAIX")
	private String kraIX;

	@Column(name = "KRAX")
	private String kraX;

	@Column(name = "WEIGHTAGEI")
	private BigDecimal weightageI;

	@Column(name = "WEIGHTAGEII")
	private BigDecimal weightageII;

	@Column(name = "WEIGHTAGEIII")
	private BigDecimal weightageIII;

	@Column(name = "WEIGHTAGEIV")
	private BigDecimal weightageIV;

	@Column(name = "WEIGHTAGEV")
	private BigDecimal weightageV;

	@Column(name = "WEIGHTAGEVI")
	private BigDecimal weightageVI;

	@Column(name = "WEIGHTAGEVII")
	private BigDecimal weightageVII;

	@Column(name = "WEIGHTAGEVIII")
	private BigDecimal weightageVIII;

	@Column(name = "WEIGHTAGEIX")
	private BigDecimal weightageIX;

	@Column(name = "WEIGHTAGEX")
	private BigDecimal weightageX;

	@Column(name = "DESCRIPTIONI")
	private String descriptionI;

	@Column(name = "DESCRIPTIONII")
	private String descriptionII;

	@Column(name = "DESCRIPTIONIII")
	private String descriptionIII;

	@Column(name = "DESCRIPTIONIV")
	private String descriptionIV;

	@Column(name = "DESCRIPTIONV")
	private String descriptionV;

	@Column(name = "DESCRIPTIONVI")
	private String descriptionVI;

	@Column(name = "DESCRIPTIONVII")
	private String descriptionVII;

	@Column(name = "DESCRIPTIONVIII")
	private String descriptionVIII;

	@Column(name = "DESCRIPTIONIX")
	private String descriptionIX;

	@Column(name = "DESCRIPTIONX")
	private String descriptionX;

	@Column(name = "SELFRATINGI")
	private BigDecimal selfRatingI;

	@Column(name = "SELFRATINGII")
	private BigDecimal selfRatingII;

	@Column(name = "SELFRATINGIII")
	private BigDecimal selfRatingIII;

	@Column(name = "SELFRATINGIV")
	private BigDecimal selfRatingIV;

	@Column(name = "SELFRATINGV")
	private BigDecimal selfRatingV;

	@Column(name = "SELFRATINGVI")
	private BigDecimal selfRatingVI;

	@Column(name = "SELFRATINGVII")
	private BigDecimal selfRatingVII;

	@Column(name = "SELFRATINGVIII")
	private BigDecimal selfRatingVIII;

	@Column(name = "SELFRATINGIX")
	private BigDecimal selfRatingIX;

	@Column(name = "SELFRATINGX")
	private BigDecimal selfRatingX;

	@Column(name = "LEV1APPRATINGI")
	private BigDecimal level1ApproverRatingI;

	@Column(name = "LEV1APPRATINGII")
	private BigDecimal level1ApproverRatingII;

	@Column(name = "LEV1APPRATINGIII")
	private BigDecimal level1ApproverRatingIII;

	@Column(name = "LEV1APPRATINGIV")
	private BigDecimal level1ApproverRatingIV;

	@Column(name = "LEV1APPRATINGV")
	private BigDecimal level1ApproverRatingV;

	@Column(name = "LEV1APPRATINGVI")
	private BigDecimal level1ApproverRatingVI;

	@Column(name = "LEV1APPRATINGVII")
	private BigDecimal level1ApproverRatingVII;

	@Column(name = "LEV1APPRATINGVIII")
	private BigDecimal level1ApproverRatingVIII;

	@Column(name = "LEV1APPRATINGIX")
	private BigDecimal level1ApproverRatingIX;

	@Column(name = "LEV1APPRATINGX")
	private BigDecimal level1ApproverRatingX;

	@Column(name = "LEV2APPRATINGI")
	private BigDecimal level2ApproverRatingI;

	@Column(name = "LEV2APPRATINGII")
	private BigDecimal level2ApproverRatingII;

	@Column(name = "LEV2APPRATINGIII")
	private BigDecimal level2ApproverRatingIII;

	@Column(name = "LEV2APPRATINGIV")
	private BigDecimal level2ApproverRatingIV;

	@Column(name = "LEV2APPRATINGV")
	private BigDecimal level2ApproverRatingV;

	@Column(name = "LEV2APPRATINGVI")
	private BigDecimal level2ApproverRatingVI;

	@Column(name = "LEV2APPRATINGVII")
	private BigDecimal level2ApproverRatingVII;

	@Column(name = "LEV2APPRATINGVIII")
	private BigDecimal level2ApproverRatingVIII;

	@Column(name = "LEV2APPRATINGIX")
	private BigDecimal level2ApproverRatingIX;

	@Column(name = "LEV2APPRATINGX")
	private BigDecimal level2ApproverRatingX;

	@Column(name = "ISACTIVE")
	private String isActive;

	@Column(name = "TENANTID")
	private String tenantId;

	@Column(name = "MANAGERID")
	private String managerId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE")
	private Date date;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "CREATEDBY")
	private String createdBy;

	@Column(name = "LASTMODIFIEDDATE")
	private Date lastModifiedDate;

	@Column(name = "LASTMODIFIEDBY")
	private String lastModifiedBy;

	@Column(name = "VERSION")
	private Long version;

	@Column(name = "SELFAPPSTATUS")
	private String selfAppStatus;

	@Column(name = "LEVELIAPPSTATUS")
	private String levelIAppStatus;

	@Column(name = "LEVELIIAPPSTATUS")
	private String levelIIAppStatus;

	@Column(name = "TOTALSELF")
	private BigDecimal totalSelf;

	@Column(name = "TOTALLEVELI")
	private BigDecimal totalLevelI;

	@Column(name = "TOTALLEVELII")
	private BigDecimal totalLevelII;

	@Column(name = "SELFRATING")
	private String selfRating;

	@Column(name = "LEVELIRATING")
	private String levelIRating;

	@Column(name = "LEVELIIRATING")
	private String levelIIRating;

	@Temporal(TemporalType.DATE)
	@Column(name = "SUBMITTEDON")
	private Date submittedOn;

	@Column(name = "TRAININGRECO")
	private String trainingReco;

	@Column(name = "TRAININGRECOCOMMENT")
	private String trainingRecoComment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKraId() {
		return kraId;
	}

	public void setKraId(String kraId) {
		this.kraId = kraId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public EmpBasicDetails getEmpBasicDetails() {
		return empBasicDetails;
	}

	public void setEmpBasicDetails(EmpBasicDetails empBasicDetails) {
		this.empBasicDetails = empBasicDetails;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getKraI() {
		return kraI;
	}

	public void setKraI(String kraI) {
		this.kraI = kraI;
	}

	public String getKraII() {
		return kraII;
	}

	public void setKraII(String kraII) {
		this.kraII = kraII;
	}

	public String getKraIII() {
		return kraIII;
	}

	public void setKraIII(String kraIII) {
		this.kraIII = kraIII;
	}

	public String getKraIV() {
		return kraIV;
	}

	public void setKraIV(String kraIV) {
		this.kraIV = kraIV;
	}

	public String getKraV() {
		return kraV;
	}

	public void setKraV(String kraV) {
		this.kraV = kraV;
	}

	public String getKraVI() {
		return kraVI;
	}

	public void setKraVI(String kraVI) {
		this.kraVI = kraVI;
	}

	public String getKraVII() {
		return kraVII;
	}

	public void setKraVII(String kraVII) {
		this.kraVII = kraVII;
	}

	public String getKraVIII() {
		return kraVIII;
	}

	public void setKraVIII(String kraVIII) {
		this.kraVIII = kraVIII;
	}

	public String getKraIX() {
		return kraIX;
	}

	public void setKraIX(String kraIX) {
		this.kraIX = kraIX;
	}

	public String getKraX() {
		return kraX;
	}

	public void setKraX(String kraX) {
		this.kraX = kraX;
	}

	public BigDecimal getWeightageI() {
		return weightageI;
	}

	public void setWeightageI(BigDecimal weightageI) {
		this.weightageI = weightageI;
	}

	public BigDecimal getWeightageII() {
		return weightageII;
	}

	public void setWeightageII(BigDecimal weightageII) {
		this.weightageII = weightageII;
	}

	public BigDecimal getWeightageIII() {
		return weightageIII;
	}

	public void setWeightageIII(BigDecimal weightageIII) {
		this.weightageIII = weightageIII;
	}

	public BigDecimal getWeightageIV() {
		return weightageIV;
	}

	public void setWeightageIV(BigDecimal weightageIV) {
		this.weightageIV = weightageIV;
	}

	public BigDecimal getWeightageV() {
		return weightageV;
	}

	public void setWeightageV(BigDecimal weightageV) {
		this.weightageV = weightageV;
	}

	public BigDecimal getWeightageVI() {
		return weightageVI;
	}

	public void setWeightageVI(BigDecimal weightageVI) {
		this.weightageVI = weightageVI;
	}

	public BigDecimal getWeightageVII() {
		return weightageVII;
	}

	public void setWeightageVII(BigDecimal weightageVII) {
		this.weightageVII = weightageVII;
	}

	public BigDecimal getWeightageVIII() {
		return weightageVIII;
	}

	public void setWeightageVIII(BigDecimal weightageVIII) {
		this.weightageVIII = weightageVIII;
	}

	public BigDecimal getWeightageIX() {
		return weightageIX;
	}

	public void setWeightageIX(BigDecimal weightageIX) {
		this.weightageIX = weightageIX;
	}

	public BigDecimal getWeightageX() {
		return weightageX;
	}

	public void setWeightageX(BigDecimal weightageX) {
		this.weightageX = weightageX;
	}

	public String getDescriptionI() {
		return descriptionI;
	}

	public void setDescriptionI(String descriptionI) {
		this.descriptionI = descriptionI;
	}

	public String getDescriptionII() {
		return descriptionII;
	}

	public void setDescriptionII(String descriptionII) {
		this.descriptionII = descriptionII;
	}

	public String getDescriptionIII() {
		return descriptionIII;
	}

	public void setDescriptionIII(String descriptionIII) {
		this.descriptionIII = descriptionIII;
	}

	public String getDescriptionIV() {
		return descriptionIV;
	}

	public void setDescriptionIV(String descriptionIV) {
		this.descriptionIV = descriptionIV;
	}

	public String getDescriptionV() {
		return descriptionV;
	}

	public void setDescriptionV(String descriptionV) {
		this.descriptionV = descriptionV;
	}

	public String getDescriptionVI() {
		return descriptionVI;
	}

	public void setDescriptionVI(String descriptionVI) {
		this.descriptionVI = descriptionVI;
	}

	public String getDescriptionVII() {
		return descriptionVII;
	}

	public void setDescriptionVII(String descriptionVII) {
		this.descriptionVII = descriptionVII;
	}

	public String getDescriptionVIII() {
		return descriptionVIII;
	}

	public void setDescriptionVIII(String descriptionVIII) {
		this.descriptionVIII = descriptionVIII;
	}

	public String getDescriptionIX() {
		return descriptionIX;
	}

	public void setDescriptionIX(String descriptionIX) {
		this.descriptionIX = descriptionIX;
	}

	public String getDescriptionX() {
		return descriptionX;
	}

	public void setDescriptionX(String descriptionX) {
		this.descriptionX = descriptionX;
	}

	public BigDecimal getSelfRatingI() {
		return selfRatingI;
	}

	public void setSelfRatingI(BigDecimal selfRatingI) {
		this.selfRatingI = selfRatingI;
	}

	public BigDecimal getSelfRatingII() {
		return selfRatingII;
	}

	public void setSelfRatingII(BigDecimal selfRatingII) {
		this.selfRatingII = selfRatingII;
	}

	public BigDecimal getSelfRatingIII() {
		return selfRatingIII;
	}

	public void setSelfRatingIII(BigDecimal selfRatingIII) {
		this.selfRatingIII = selfRatingIII;
	}

	public BigDecimal getSelfRatingIV() {
		return selfRatingIV;
	}

	public void setSelfRatingIV(BigDecimal selfRatingIV) {
		this.selfRatingIV = selfRatingIV;
	}

	public BigDecimal getSelfRatingV() {
		return selfRatingV;
	}

	public void setSelfRatingV(BigDecimal selfRatingV) {
		this.selfRatingV = selfRatingV;
	}

	public BigDecimal getSelfRatingVI() {
		return selfRatingVI;
	}

	public void setSelfRatingVI(BigDecimal selfRatingVI) {
		this.selfRatingVI = selfRatingVI;
	}

	public BigDecimal getSelfRatingVII() {
		return selfRatingVII;
	}

	public void setSelfRatingVII(BigDecimal selfRatingVII) {
		this.selfRatingVII = selfRatingVII;
	}

	public BigDecimal getSelfRatingVIII() {
		return selfRatingVIII;
	}

	public void setSelfRatingVIII(BigDecimal selfRatingVIII) {
		this.selfRatingVIII = selfRatingVIII;
	}

	public BigDecimal getSelfRatingIX() {
		return selfRatingIX;
	}

	public void setSelfRatingIX(BigDecimal selfRatingIX) {
		this.selfRatingIX = selfRatingIX;
	}

	public BigDecimal getSelfRatingX() {
		return selfRatingX;
	}

	public void setSelfRatingX(BigDecimal selfRatingX) {
		this.selfRatingX = selfRatingX;
	}

	public BigDecimal getLevel1ApproverRatingI() {
		return level1ApproverRatingI;
	}

	public void setLevel1ApproverRatingI(BigDecimal level1ApproverRatingI) {
		this.level1ApproverRatingI = level1ApproverRatingI;
	}

	public BigDecimal getLevel1ApproverRatingII() {
		return level1ApproverRatingII;
	}

	public void setLevel1ApproverRatingII(BigDecimal level1ApproverRatingII) {
		this.level1ApproverRatingII = level1ApproverRatingII;
	}

	public BigDecimal getLevel1ApproverRatingIII() {
		return level1ApproverRatingIII;
	}

	public void setLevel1ApproverRatingIII(BigDecimal level1ApproverRatingIII) {
		this.level1ApproverRatingIII = level1ApproverRatingIII;
	}

	public BigDecimal getLevel1ApproverRatingIV() {
		return level1ApproverRatingIV;
	}

	public void setLevel1ApproverRatingIV(BigDecimal level1ApproverRatingIV) {
		this.level1ApproverRatingIV = level1ApproverRatingIV;
	}

	public BigDecimal getLevel1ApproverRatingV() {
		return level1ApproverRatingV;
	}

	public void setLevel1ApproverRatingV(BigDecimal level1ApproverRatingV) {
		this.level1ApproverRatingV = level1ApproverRatingV;
	}

	public BigDecimal getLevel1ApproverRatingVI() {
		return level1ApproverRatingVI;
	}

	public void setLevel1ApproverRatingVI(BigDecimal level1ApproverRatingVI) {
		this.level1ApproverRatingVI = level1ApproverRatingVI;
	}

	public BigDecimal getLevel1ApproverRatingVII() {
		return level1ApproverRatingVII;
	}

	public void setLevel1ApproverRatingVII(BigDecimal level1ApproverRatingVII) {
		this.level1ApproverRatingVII = level1ApproverRatingVII;
	}

	public BigDecimal getLevel1ApproverRatingVIII() {
		return level1ApproverRatingVIII;
	}

	public void setLevel1ApproverRatingVIII(BigDecimal level1ApproverRatingVIII) {
		this.level1ApproverRatingVIII = level1ApproverRatingVIII;
	}

	public BigDecimal getLevel1ApproverRatingIX() {
		return level1ApproverRatingIX;
	}

	public void setLevel1ApproverRatingIX(BigDecimal level1ApproverRatingIX) {
		this.level1ApproverRatingIX = level1ApproverRatingIX;
	}

	public BigDecimal getLevel1ApproverRatingX() {
		return level1ApproverRatingX;
	}

	public void setLevel1ApproverRatingX(BigDecimal level1ApproverRatingX) {
		this.level1ApproverRatingX = level1ApproverRatingX;
	}

	public BigDecimal getLevel2ApproverRatingI() {
		return level2ApproverRatingI;
	}

	public void setLevel2ApproverRatingI(BigDecimal level2ApproverRatingI) {
		this.level2ApproverRatingI = level2ApproverRatingI;
	}

	public BigDecimal getLevel2ApproverRatingII() {
		return level2ApproverRatingII;
	}

	public void setLevel2ApproverRatingII(BigDecimal level2ApproverRatingII) {
		this.level2ApproverRatingII = level2ApproverRatingII;
	}

	public BigDecimal getLevel2ApproverRatingIII() {
		return level2ApproverRatingIII;
	}

	public void setLevel2ApproverRatingIII(BigDecimal level2ApproverRatingIII) {
		this.level2ApproverRatingIII = level2ApproverRatingIII;
	}

	public BigDecimal getLevel2ApproverRatingIV() {
		return level2ApproverRatingIV;
	}

	public void setLevel2ApproverRatingIV(BigDecimal level2ApproverRatingIV) {
		this.level2ApproverRatingIV = level2ApproverRatingIV;
	}

	public BigDecimal getLevel2ApproverRatingV() {
		return level2ApproverRatingV;
	}

	public void setLevel2ApproverRatingV(BigDecimal level2ApproverRatingV) {
		this.level2ApproverRatingV = level2ApproverRatingV;
	}

	public BigDecimal getLevel2ApproverRatingVI() {
		return level2ApproverRatingVI;
	}

	public void setLevel2ApproverRatingVI(BigDecimal level2ApproverRatingVI) {
		this.level2ApproverRatingVI = level2ApproverRatingVI;
	}

	public BigDecimal getLevel2ApproverRatingVII() {
		return level2ApproverRatingVII;
	}

	public void setLevel2ApproverRatingVII(BigDecimal level2ApproverRatingVII) {
		this.level2ApproverRatingVII = level2ApproverRatingVII;
	}

	public BigDecimal getLevel2ApproverRatingVIII() {
		return level2ApproverRatingVIII;
	}

	public void setLevel2ApproverRatingVIII(BigDecimal level2ApproverRatingVIII) {
		this.level2ApproverRatingVIII = level2ApproverRatingVIII;
	}

	public BigDecimal getLevel2ApproverRatingIX() {
		return level2ApproverRatingIX;
	}

	public void setLevel2ApproverRatingIX(BigDecimal level2ApproverRatingIX) {
		this.level2ApproverRatingIX = level2ApproverRatingIX;
	}

	public BigDecimal getLevel2ApproverRatingX() {
		return level2ApproverRatingX;
	}

	public void setLevel2ApproverRatingX(BigDecimal level2ApproverRatingX) {
		this.level2ApproverRatingX = level2ApproverRatingX;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getSelfAppStatus() {
		return selfAppStatus;
	}

	public void setSelfAppStatus(String selfAppStatus) {
		this.selfAppStatus = selfAppStatus;
	}

	public String getLevelIAppStatus() {
		return levelIAppStatus;
	}

	public void setLevelIAppStatus(String levelIAppStatus) {
		this.levelIAppStatus = levelIAppStatus;
	}

	public String getLevelIIAppStatus() {
		return levelIIAppStatus;
	}

	public void setLevelIIAppStatus(String levelIIAppStatus) {
		this.levelIIAppStatus = levelIIAppStatus;
	}

	public BigDecimal getTotalSelf() {
		return totalSelf;
	}

	public void setTotalSelf(BigDecimal totalSelf) {
		this.totalSelf = totalSelf;
	}

	public BigDecimal getTotalLevelI() {
		return totalLevelI;
	}

	public void setTotalLevelI(BigDecimal totalLevelI) {
		this.totalLevelI = totalLevelI;
	}

	public BigDecimal getTotalLevelII() {
		return totalLevelII;
	}

	public void setTotalLevelII(BigDecimal totalLevelII) {
		this.totalLevelII = totalLevelII;
	}

	public String getSelfRating() {
		return selfRating;
	}

	public void setSelfRating(String selfRating) {
		this.selfRating = selfRating;
	}

	public String getLevelIRating() {
		return levelIRating;
	}

	public void setLevelIRating(String levelIRating) {
		this.levelIRating = levelIRating;
	}

	public String getLevelIIRating() {
		return levelIIRating;
	}

	public void setLevelIIRating(String levelIIRating) {
		this.levelIIRating = levelIIRating;
	}

	public Date getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}

	public String getTrainingReco() {
		return trainingReco;
	}

	public void setTrainingReco(String trainingReco) {
		this.trainingReco = trainingReco;
	}

	public String getTrainingRecoComment() {
		return trainingRecoComment;
	}

	public void setTrainingRecoComment(String trainingRecoComment) {
		this.trainingRecoComment = trainingRecoComment;
	}

}