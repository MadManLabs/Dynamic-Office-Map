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

    private boolean isOnMap = false;

    private String typeId;
    private String typeName;

    private String zoneId;
    private String floorId;
    private String closeByAssetId;
    private String tenantId;

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
        this.isOnMap = asset.isOnMap();
        if (asset.getCloseBy() != null) {
            this.closeByAssetId = asset.getCloseBy().getId();
        }
        if (asset.getTenant() != null) {
            this.tenantId = asset.getTenant().getId();
        }

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

    public String getCloseByAssetId() {
        return closeByAssetId;
    }

    public void setCloseByAssetId(String closeByAssetId) {
        this.closeByAssetId = closeByAssetId;
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

    public boolean isOnMap() {
        return isOnMap;
    }

    public void setOnMap(boolean onMap) {
        isOnMap = onMap;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
