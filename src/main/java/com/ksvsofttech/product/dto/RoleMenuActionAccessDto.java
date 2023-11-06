package com.ksvsofttech.product.dto;

import java.util.List;

import com.ksvsofttech.product.entities.RoleMenuActionAccess;

public class RoleMenuActionAccessDto {
	private List<RoleMenuActionAccess> actionAccesses;

	public RoleMenuActionAccessDto() {
	}

	public RoleMenuActionAccessDto(List<RoleMenuActionAccess> actionAccesses) {
		super();
		this.actionAccesses = actionAccesses;
	}

	public void addRoleMenu(RoleMenuActionAccess roleMenuActionAccess) {
		this.actionAccesses.add(roleMenuActionAccess);
	}

	public List<RoleMenuActionAccess> getActionAccesses() {
		return actionAccesses;
	}

	public void setActionAccesses(List<RoleMenuActionAccess> actionAccesses) {
		this.actionAccesses = actionAccesses;
	}
}
