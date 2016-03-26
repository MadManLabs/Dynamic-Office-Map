package com.fsociety.domclient.dto;

public class DeskDTO {
	private Integer id;
	private String code;
	private String xlayout;
	private String ylayout;
	private ZoneDTO zone;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getXlayout() {
		return xlayout;
	}

	public void setXlayout(String xlayout) {
		this.xlayout = xlayout;
	}

	public String getYlayout() {
		return ylayout;
	}

	public void setYlayout(String ylayout) {
		this.ylayout = ylayout;
	}

	public ZoneDTO getZone() {
		return zone;
	}

	public void setZone(ZoneDTO zone) {
		this.zone = zone;
	}
}
