package com.fsociety.domclient.dto;

public class PersonDTO {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String mac;
	private String temporaryZoneId;
	private String temporaryZoneCode;
	private String temporaryZoneName;
	private String permanentZoneId;
	private String permanentZoneCode;
	private String permanentZoneName;
	private String permanentDeskId;
	private String permanentDeskCode;
	private String temporaryFloorName;
	private String permanentFloorName;
	private String role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getPermanentDeskCode() {
		return permanentDeskCode;
	}

	public void setPermanentDeskCode(String permanentDeskCode) {
		this.permanentDeskCode = permanentDeskCode;
	}

	public String getPermanentDeskId() {
		return permanentDeskId;
	}

	public void setPermanentDeskId(String permanentDeskId) {
		this.permanentDeskId = permanentDeskId;
	}

	public String getPermanentZoneCode() {
		return permanentZoneCode;
	}

	public void setPermanentZoneCode(String permanentZoneCode) {
		this.permanentZoneCode = permanentZoneCode;
	}

	public String getPermanentZoneId() {
		return permanentZoneId;
	}

	public void setPermanentZoneId(String permanentZoneId) {
		this.permanentZoneId = permanentZoneId;
	}

	public String getPermanentZoneName() {
		return permanentZoneName;
	}

	public void setPermanentZoneName(String permanentZoneName) {
		this.permanentZoneName = permanentZoneName;
	}

	public String getTemporaryZoneCode() {
		return temporaryZoneCode;
	}

	public void setTemporaryZoneCode(String temporaryZoneCode) {
		this.temporaryZoneCode = temporaryZoneCode;
	}

	public String getTemporaryZoneId() {
		return temporaryZoneId;
	}

	public void setTemporaryZoneId(String temporaryZoneId) {
		this.temporaryZoneId = temporaryZoneId;
	}

	public String getTemporaryZoneName() {
		return temporaryZoneName;
	}

	public void setTemporaryZoneName(String temporaryZoneName) {
		this.temporaryZoneName = temporaryZoneName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPermanentFloorName() {
		return permanentFloorName;
	}

	public void setPermanentFloorName(String permanentFloorName) {
		this.permanentFloorName = permanentFloorName;
	}

	public String getTemporaryFloorName() {
		return temporaryFloorName;
	}

	public void setTemporaryFloorName(String temporaryFloorName) {
		this.temporaryFloorName = temporaryFloorName;
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
