package com.endava.fsociety.dynamicofficemap.server.dto;

import com.endava.fsociety.dynamicofficemap.server.model.BusinessProject;

/**
 * Created by caldea on 5/21/2016.
 */
public class ProjectDTO {
    private String zoneId;
    private String zoneCode;
    private String zoneName;
    private String id;
    private String logo;
    private String description;
    private String name;
    private String technologies;

    public ProjectDTO(String zoneId, String zoneCode, String zoneName, String id, String logo, String description, String name, String technologies) {
        this.zoneId = zoneId;
        this.zoneCode = zoneCode;
        this.zoneName = zoneName;
        this.id = id;
        this.logo = logo;
        this.description = description;
        this.name = name;
        this.technologies = technologies;
    }

    public ProjectDTO(BusinessProject businessProject) {
        if (businessProject.getZone() != null) {
            this.zoneId = businessProject.getZone().getId();
            this.zoneCode = businessProject.getZone().getCode();
            this.zoneName = businessProject.getZone().getName();
        }
        this.id = businessProject.getId();
        this.logo = businessProject.getLogo();
        this.description = businessProject.getDescription();
        this.name = businessProject.getName();
        this.technologies = businessProject.getTechnologies();
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }
}
