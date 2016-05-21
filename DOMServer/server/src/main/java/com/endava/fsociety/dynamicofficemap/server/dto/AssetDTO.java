package com.endava.fsociety.dynamicofficemap.server.dto;

import com.endava.fsociety.dynamicofficemap.server.model.Asset;
import com.endava.fsociety.dynamicofficemap.server.model.AssetType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by fstancu on 5/11/2016.
 */

public class AssetDTO {

    private String id;
    private String name;
    private String code;

    private String typeId;
    private String typeName;

    private String zoneId;
    private String floorId;

    public AssetDTO() {
    }

    public AssetDTO(Asset asset) {
        this.id = asset.getId();
        this.name = asset.getName();
        this.code = asset.getCode();
        this.typeId = asset.getAssetType().getId();
        this.typeName = asset.getAssetType().getName();
        this.zoneId = asset.getZone().getId();
        this.floorId = asset.getZone().getFloor().getId();
    }

    @JsonIgnore
    public Asset getEntity() {
        Asset asset = new Asset();
        asset.setId(id);
        asset.setName(name);
        asset.setCode(code);

        AssetType assetType = new AssetType();
        assetType.setId(typeId);
        assetType.setName(typeName);
        asset.setAssetType(assetType);

        return asset;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }
}
