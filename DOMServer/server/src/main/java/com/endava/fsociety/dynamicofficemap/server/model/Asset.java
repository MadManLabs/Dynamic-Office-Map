package com.endava.fsociety.dynamicofficemap.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by fstancu on 5/11/2016.
 */

@Document
public class Asset {

    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
    private String code;

    private boolean isOnMap = false;

    @DBRef
    private AssetType assetType;

    @DBRef
    private Zone zone;

    @DBRef
    private Asset closeBy;

    @DBRef
    private Person tenant;

    public Asset() {
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

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public boolean isOnMap() {
        return isOnMap;
    }

    public void setOnMap(boolean onMap) {
        isOnMap = onMap;
    }

    public Asset getCloseBy() {
        return closeBy;
    }

    public void setCloseBy(Asset closeBy) {
        this.closeBy = closeBy;
    }

    public Person getTenant() {
        return tenant;
    }

    public void setTenant(Person tenant) {
        this.tenant = tenant;
    }
}
