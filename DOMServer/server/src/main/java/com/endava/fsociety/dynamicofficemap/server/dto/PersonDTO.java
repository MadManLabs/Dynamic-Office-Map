package com.endava.fsociety.dynamicofficemap.server.dto;

import com.endava.fsociety.dynamicofficemap.server.model.Person;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by fstancu on 5/20/2016.
 */
public class PersonDTO {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String mac;

    private String temporaryZoneId;

    private String temporaryZoneCode;

    private String temporaryZoneName;

    private String permanentZoneId;

    private String permanentZoneCode;

    private String permanentZoneName;

    private String permanentDeskId;

    private String permanentDeskCode;


    public PersonDTO(String id, String firstName, String lastName, String email, String mac, String temporaryZoneId, String temporaryZoneCode, String temporaryZoneName, String permanentZoneId, String permanentZoneCode, String permanentZoneName, String permanentDeskId, String permanentDeskCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mac = mac;
        this.temporaryZoneId = temporaryZoneId;
        this.temporaryZoneCode = temporaryZoneCode;
        this.temporaryZoneName = temporaryZoneName;
        this.permanentZoneId = permanentZoneId;
        this.permanentZoneCode = permanentZoneCode;
        this.permanentZoneName = permanentZoneName;
        this.permanentDeskId = permanentDeskId;
        this.permanentDeskCode = permanentDeskCode;
    }

    public PersonDTO(Person person) {
        if (person != null) {
            this.id = person.getId();
            this.firstName = person.getFirstName();
            this.lastName = person.getLastName();
            this.email = person.getEmail();
            this.mac = person.getMac();
            if (person.getTemporaryZone() != null) {
                this.temporaryZoneId = person.getTemporaryZone().getId();
                this.temporaryZoneCode = person.getTemporaryZone().getCode();
                this.temporaryZoneName = person.getTemporaryZone().getName();
            }
            if (person.getPermanentDesk() != null) {
                if (person.getPermanentDesk().getZone() != null) {
                    this.permanentZoneId = person.getPermanentDesk().getZone().getId();
                    this.permanentZoneCode = person.getPermanentDesk().getZone().getCode();
                    this.permanentZoneName = person.getPermanentDesk().getZone().getName();
                }
                this.permanentDeskId = person.getPermanentDesk().getId();
                this.permanentDeskCode = person.getPermanentDesk().getCode();
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

    public String getPermanentZoneId() {
        return permanentZoneId;
    }

    public void setPermanentZoneId(String permanentZoneId) {
        this.permanentZoneId = permanentZoneId;
    }

    public String getPermanentZoneCode() {
        return permanentZoneCode;
    }

    public void setPermanentZoneCode(String permanentZoneCode) {
        this.permanentZoneCode = permanentZoneCode;
    }

    public String getPermanentZoneName() {
        return permanentZoneName;
    }

    public void setPermanentZoneName(String permanentZoneName) {
        this.permanentZoneName = permanentZoneName;
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


}
