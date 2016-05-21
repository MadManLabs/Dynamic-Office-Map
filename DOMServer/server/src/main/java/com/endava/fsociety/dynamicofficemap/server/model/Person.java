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
    private String username;

    @Indexed(unique = true)
    private String mac;

    @DBRef
    private Asset permanentDesk;

    @DBRef
    private Zone temporaryZone;

    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (username != null ? !username.equals(person.username) : person.username != null) return false;
        if (mac != null ? !mac.equals(person.mac) : person.mac != null) return false;
        if (permanentDesk != null ? !permanentDesk.equals(person.permanentDesk) : person.permanentDesk != null)
            return false;
        return temporaryZone != null ? temporaryZone.equals(person.temporaryZone) : person.temporaryZone == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (mac != null ? mac.hashCode() : 0);
        result = 31 * result + (permanentDesk != null ? permanentDesk.hashCode() : 0);
        result = 31 * result + (temporaryZone != null ? temporaryZone.hashCode() : 0);
        return result;
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
