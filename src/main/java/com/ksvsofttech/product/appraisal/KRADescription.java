package com.ksvsofttech.product.appraisal;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KRADESCRIPTION")
public class KRADescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "KRADESCRIPTIONID")
	private Long kraDescriptionId;

	@Column(name = "DESCRIPTION")
	private String description;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID", unique = true)
	private KRA kra;

	
	public KRADescription() {
	}

	public Long getKraDescriptionId() {
		return kraDescriptionId;
	}

	public void setKraDescriptionId(Long kraDescriptionId) {
		this.kraDescriptionId = kraDescriptionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public KRA getKra() {
		return kra;
	}

	public void setKra(KRA kra) {
		this.kra = kra;
	}
}