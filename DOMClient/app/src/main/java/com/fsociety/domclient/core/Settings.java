package com.fsociety.domclient.core;

import com.fsociety.domclient.dto.PersonDTO;

public class Settings {
	private PersonDTO loggedInPersonDTO;
	private PersonDTO administerPersonDTO;
	private boolean enableBeaconUpdates;
	private String serverIp;
	private String serverPort;

	public PersonDTO getLoggedInPersonDTO() {
		return loggedInPersonDTO;
	}

	public void setLoggedInPersonDTO(PersonDTO loggedInPersonDTO) {
		this.loggedInPersonDTO = loggedInPersonDTO;
	}

	public boolean getEnableBeaconUpdates() {
		return enableBeaconUpdates;
	}

	public void setEnableBeaconUpdates(boolean enableBeaconUpdates) {
		this.enableBeaconUpdates = enableBeaconUpdates;
	}

	public String getServerIp() {
		return (serverIp==null)?"192.168.168.107":serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerPort() {
		return (serverPort==null)?"8080":serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public PersonDTO getAdministerPersonDTO() {
		return administerPersonDTO;
	}

	public void setAdministerPersonDTO(PersonDTO administerPersonDTO) {
		this.administerPersonDTO = administerPersonDTO;
	}
}
