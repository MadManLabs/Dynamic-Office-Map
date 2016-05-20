package com.endava.fsociety.dynamicofficemap.server.dto;

import com.endava.fsociety.dynamicofficemap.server.model.Zone;

/**
 * Created by fstancu on 5/11/2016.
 */

public class ZoneDTO {

    private String id;
    private String name;
    private String code;

    private String parentId;
    private String parentName;

    private String floorId;
    private String floorName;

    public ZoneDTO() {
    }

    public ZoneDTO(Zone zone) {
        this.id = zone.getId();
        this.name = zone.getName();
        this.code = zone.getCode();

        if (zone.getParent() != null) {
            this.parentId = zone.getParent().getId();
            this.parentName = zone.getParent().getName();
        }

        this.floorId = zone.getFloor().getId();
        this.floorName = zone.getFloor().getName();
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
