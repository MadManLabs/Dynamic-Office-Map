package com.fsociety.domclient.dto;

public class PersonDTO {
	private String name;
	private String email;
	private ZoneDTO zone;
	private DeskDTO desk;
	private String mac;
	private Integer id;

	public ZoneDTO getZone() {
		return zone;
	}

	public void setZone(ZoneDTO zone) {
		this.zone = zone;
	}

	public DeskDTO getDesk() {
		return desk;
	}

	public void setDesk(DeskDTO desk) {
		this.desk = desk;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
