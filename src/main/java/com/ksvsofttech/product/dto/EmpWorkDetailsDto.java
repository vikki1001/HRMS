package com.ksvsofttech.product.dto;

import java.util.List;

import com.ksvsofttech.product.entities.EmpWorkDetails;

public class EmpWorkDetailsDto {

	private List<EmpWorkDetails> listEmpWorkDetails;

	public EmpWorkDetailsDto() {
	}

	public EmpWorkDetailsDto(List<EmpWorkDetails> listEmpWorkDetails) {
		super();
		this.listEmpWorkDetails = listEmpWorkDetails;
	}

	public List<EmpWorkDetails> getListEmpWorkDetails() {
		return listEmpWorkDetails;
	}

	public void setListEmpWorkDetails(List<EmpWorkDetails> listEmpWorkDetails) {
		this.listEmpWorkDetails = listEmpWorkDetails;
	}

}
