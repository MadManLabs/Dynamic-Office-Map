package com.endava.fsociety.dynamicofficemap.server.dto;

import com.endava.fsociety.dynamicofficemap.server.model.Person;
import com.endava.fsociety.dynamicofficemap.server.util.MD5Util;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by fstancu on 5/20/2016.
 */
public class PersonDTO {

    private String id;

    private String firstName;

    private String lastName;

    private String email;
    private String hashGravatar;

    private String username;

    private String mac;

    private String temporaryZoneId;
    private String temporaryZoneCode;
    private String temporaryZoneName;

    private String permanentDeskId;
    private String permanentDeskCode;

    private String temporaryFloorName;
    private String permanentFloorName;

    public PersonDTO(Person person) {
        if (person != null) {
            this.id = person.getId();
            this.firstName = person.getFirstName();
            this.lastName = person.getLastName();

            this.email = person.getEmail();
            if (person.getEmail() != null) {
                this.hashGravatar = MD5Util.md5Hex(person.getEmail());
            }

            this.username = person.getUsername();
            this.mac = person.getMac();

            if (person.getTemporaryZone() != null) {
                this.temporaryZoneId = person.getTemporaryZone().getId();
                this.temporaryZoneCode = person.getTemporaryZone().getCode();
                this.temporaryZoneName = person.getTemporaryZone().getName();
                this.temporaryFloorName = person.getTemporaryZone().getFloor().getName();
            }

            if (person.getPermanentDesk() != null) {
                this.permanentDeskId = person.getPermanentDesk().getId();
                this.permanentDeskCode = person.getPermanentDesk().getCode();
                this.permanentFloorName = person.getPermanentDesk().getZone().getFloor().getName();
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getTemporaryZoneId() {
        return temporaryZoneId;
    }

    public void setTemporaryZoneId(String temporaryZoneId) {
        this.temporaryZoneId = temporaryZoneId;
    }

    public String getTemporaryZoneCode() {
        return temporaryZoneCode;
    }

    public void setTemporaryZoneCode(String temporaryZoneCode) {
        this.temporaryZoneCode = temporaryZoneCode;
    }

    public String getTemporaryZoneName() {
        return temporaryZoneName;
    }

    public void setTemporaryZoneName(String temporaryZoneName) {
        this.temporaryZoneName = temporaryZoneName;
    }

    public String getPermanentDeskId() {
        return permanentDeskId;
    }

    public void setPermanentDeskId(String permanentDeskId) {
        this.permanentDeskId = permanentDeskId;
    }

    public String getPermanentDeskCode() {
        return permanentDeskCode;
    }

    public void setPermanentDeskCode(String permanentDeskCode) {
        this.permanentDeskCode = permanentDeskCode;
    }


    public String getHashGravatar() {
        return hashGravatar;
    }

    public void setHashGravatar(String hashGravatar) {
        this.hashGravatar = hashGravatar;
    }

    public String getTemporaryFloorName() {
        return temporaryFloorName;
    }

    public String getPermanentFloorName() {
        return permanentFloorName;
    }
}
