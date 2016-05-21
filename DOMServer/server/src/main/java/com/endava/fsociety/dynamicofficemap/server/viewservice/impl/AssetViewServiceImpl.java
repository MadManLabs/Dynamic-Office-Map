package com.endava.fsociety.dynamicofficemap.server.viewservice.impl;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetDTO;
import com.endava.fsociety.dynamicofficemap.server.exception.BadDataException;
import com.endava.fsociety.dynamicofficemap.server.exception.BadUrlException;
import com.endava.fsociety.dynamicofficemap.server.model.*;
import com.endava.fsociety.dynamicofficemap.server.service.*;
import com.endava.fsociety.dynamicofficemap.server.viewservice.AssetViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private FloorService floorService;

    @Autowired
    private PersonService personService;

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
        if (assetDTO.getCloseByAssetId() != null) {
            Asset closeByAsset = assetService.findById(assetDTO.getCloseByAssetId());
            if (closeByAsset != null) {
                asset.setCloseBy(closeByAsset);
            }
        }

        if (assetDTO.getTenantId() != null) {
            Person person = personService.findById(assetDTO.getTenantId());
            asset.setTenant(person);
        } else {
            asset.setTenant(null);
        }

        asset.setAssetType(assetType);
        asset.setName(assetDTO.getName());
        asset.setCode(assetDTO.getCode());
        asset.setOnMap(assetDTO.isOnMap());

        return new AssetDTO(assetService.save(asset));
    }

    @Override
    public List<AssetDTO> findByFloor(String floorId) {
        Floor floor = floorService.findById(floorId);
        if (floor == null) {
            throw new BadUrlException("There is no floor with id " + floorId);
        }
        List<Asset> assets = assetService.findByFloor(floor);
        List<AssetDTO> assetDTOs = new ArrayList<AssetDTO>();
        for (Asset asset : assets) {
            assetDTOs.add(new AssetDTO(asset));
        }
        return assetDTOs;
    }

    @Override
    public List<AssetDTO> findByMap(String floorId) {
        Floor floor = floorService.findById(floorId);
        if (floor == null) {
            throw new BadUrlException("There is no floor with id " + floorId);
        }
        List<Asset> assets = assetService.findByMap(floor);
        List<AssetDTO> assetDTOs = new ArrayList<AssetDTO>();
        for (Asset asset : assets) {
            assetDTOs.add(new AssetDTO(asset));
        }
        return assetDTOs;
    }

    @Override
    public AssetDTO removeFromMap(String id) {
        Asset asset = assetService.findById(id);
        if (asset == null) {
            throw new BadUrlException("There is no asset with id " + id);
        }
        asset.setOnMap(false);
        return new AssetDTO(assetService.save(asset));
    }

}
