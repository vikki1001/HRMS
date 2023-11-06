package com.ksvsofttech.product.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "UPLOADDOCUMENT")
public class UploadDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TEMPLATENAME", length = 50)
	private String templateName;

	@Column(name = "ORIGINALNAME", length = 50)
	private String originalName;

	@Lob
	@Column(name = "FILE")
	private byte[] file;

	@Column(name = "FILETYPE")
	private String fileType;

	@Column(name = "SIZE")
	private Long size;

	@Column(name = "FILEDEPARTMENT", length = 20)
	private String fileDepartment;

	@Column(name = "ISACTIVE")
	private String isActive;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;

	@Column(name = "LASTMODDATE")
	private Date lastModifiedDate;

	@Column(name = "LASTMODBY", length = 50)
	private String lastModifiedBy;

	@Column(name = "EXTENSION", length = 50)
	private String extension;

	public UploadDocument() {
	}

	public UploadDocument(String templateName, String originalName, byte[] file, String fileType) {
		super();
		this.templateName = templateName;
		this.originalName = originalName;
		this.file = file;
		this.fileType = fileType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getFileDepartment() {
		return fileDepartment;
	}

	public void setFileDepartment(String fileDepartment) {
		this.fileDepartment = fileDepartment;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
}