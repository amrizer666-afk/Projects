package com.solverminds.projectmanagement.repository.dto;

import java.time.LocalDateTime;

public class ActivityLogDTO {

	private String username;

	private String role;

	private String actionType;

	private String moduleName;

	private String referenceId;

	private String description;

	private String ipAddress;

	private LocalDateTime createdAt;

	public ActivityLogDTO() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "ActivityLogDTO [username=" + username + ", role=" + role + ", actionType=" + actionType
				+ ", moduleName=" + moduleName + ", referenceId=" + referenceId + ", description=" + description
				+ ", ipAddress=" + ipAddress + ", createdAt=" + createdAt + "]";
	}

}