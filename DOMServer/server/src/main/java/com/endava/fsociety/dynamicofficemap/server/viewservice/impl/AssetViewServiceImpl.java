package com.endava.fsociety.dynamicofficemap.server.viewservice.impl;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetDTO;
import com.endava.fsociety.dynamicofficemap.server.exception.BadDataException;
import com.endava.fsociety.dynamicofficemap.server.exception.BadUrlException;
import com.endava.fsociety.dynamicofficemap.server.model.Asset;
import com.endava.fsociety.dynamicofficemap.server.model.AssetType;
import com.endava.fsociety.dynamicofficemap.server.model.Zone;
import com.endava.fsociety.dynamicofficemap.server.service.AssetService;
import com.endava.fsociety.dynamicofficemap.server.service.AssetTypeService;
import com.endava.fsociety.dynamicofficemap.server.service.ZoneService;
import com.endava.fsociety.dynamicofficemap.server.viewservice.AssetViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fstancu on 5/20/2016.
 */

@Service
public class AssetViewServiceImpl implements AssetViewService {

    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetTypeService assetTypeService;

    @Autowired
    private ZoneService zoneService;

    @Override
    public AssetDTO findById(String id) {
        Asset asset = assetService.findById(id);
        if (asset == null) {
            throw new BadUrlException("There is no asset with id " + id);
        }
        return new AssetDTO(asset);
    }

    @Override
    public AssetDTO save(AssetDTO assetDTO) {
        Asset asset;
        if (assetDTO.getId() != null) {
            asset = assetService.findById(assetDTO.getId());
            if (asset == null) {
                throw new BadDataException("There is no asset with id " + assetDTO.getId());
            }
        } else {
            asset = new Asset();
        }

        AssetType assetType = assetTypeService.findById(assetDTO.getTypeId());
        if (assetType == null) {
            throw new BadDataException("There is no asset type with id " + assetDTO.getTypeId());
        }

        Zone zone = zoneService.findById(assetDTO.getZoneId());
        if (zone == null) {
            throw new BadDataException("There is no zone with id " + assetDTO.getZoneId());
        }

        asset.setZone(zone);
        asset.setAssetType(assetType);
        asset.setName(assetDTO.getName());
        asset.setCode(assetDTO.getCode());

        return new AssetDTO(assetService.save(asset));
    }

}
