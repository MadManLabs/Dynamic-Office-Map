package com.endava.fsociety.dynamicofficemap.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@Document
public class Person {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String mac;

    @DBRef
    private Asset permanentDesk;

    @DBRef
    private Zone temporaryZone;

    public Person() {
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

    public Zone getTemporaryZone() {
        return temporaryZone;
    }

    public void setTemporaryZone(Zone temporaryZone) {
        this.temporaryZone = temporaryZone;
    }

    public Asset getPermanentDesk() {
        return permanentDesk;
    }

    public void setPermanentDesk(Asset permanentDesk) {
        this.permanentDesk = permanentDesk;
    }
}
