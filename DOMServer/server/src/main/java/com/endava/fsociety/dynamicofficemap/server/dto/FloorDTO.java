package com.endava.fsociety.dynamicofficemap.server.dto;

import com.endava.fsociety.dynamicofficemap.server.model.Floor;

/**
 * Created by fstancu on 5/11/2016.
 */

public class FloorDTO {

    private String id;
    private String name;
    private String map;
    private Integer persons;

    public FloorDTO() {
    }

    public FloorDTO(Floor floor) {
        this.id = floor.getId();
        this.name = floor.getName();
        this.map = floor.getMap();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }
}
