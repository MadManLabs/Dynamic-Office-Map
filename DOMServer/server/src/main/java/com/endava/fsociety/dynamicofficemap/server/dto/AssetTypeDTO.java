package com.endava.fsociety.dynamicofficemap.server.dto;

import com.endava.fsociety.dynamicofficemap.server.model.AssetType;

/**
 * Created by fstancu on 5/11/2016.
 */

public class AssetTypeDTO {

    private String id;
    private String name;
    private String code;
    private String image;

    public AssetTypeDTO() {
    }

    public AssetTypeDTO(AssetType assetType) {
        this.id = assetType.getId();
        this.name = assetType.getName();
        this.code = assetType.getCode();
        this.image = assetType.getImage();
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
