package com.endava.fsociety.dynamicofficemap.server.service.impl;

import com.endava.fsociety.dynamicofficemap.server.model.Asset;
import com.endava.fsociety.dynamicofficemap.server.model.AssetType;
import com.endava.fsociety.dynamicofficemap.server.model.Floor;
import com.endava.fsociety.dynamicofficemap.server.model.Zone;
import com.endava.fsociety.dynamicofficemap.server.repository.AssetRepository;
import com.endava.fsociety.dynamicofficemap.server.service.AssetService;
import com.endava.fsociety.dynamicofficemap.server.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private ZoneService zoneService;

    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    public List<Asset> findByAssetType(AssetType assetType) {
        return assetRepository.findByAssetType(assetType);
    }

    public Asset findByCode(String code) {
        return assetRepository.findByCode(code);
    }

    @Override
    public Asset findById(String id) {
        return assetRepository.findOne(id);
    }

    public Asset save(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> findByFloor(Floor floor) {
        List<Asset> assets = new ArrayList<Asset>();
        List<Zone> zones = zoneService.findByFloor(floor);
        if (zones != null) {
            for (Zone zone : zones) {
                List<Asset> assetsByZone = assetRepository.findByZone(zone);
                if (assetsByZone != null) {
                    assets.addAll(assetsByZone);
                }
            }
        }
        return assets;
    }

    @Override
    public List<Asset> findByMap(Floor floor) {
        List<Asset> assets = new ArrayList<Asset>();
        List<Zone> zones = zoneService.findByFloor(floor);
        if (zones != null) {
            for (Zone zone : zones) {
                List<Asset> assetsByZone = assetRepository.findByZoneAndIsOnMap(zone, true);
                if (assetsByZone != null) {
                    assets.addAll(assetsByZone);
                }
            }
        }
        return assets;
    }

    @Override
    public List<Asset> findByZoneMap(Zone zone) {
        return assetRepository.findByZoneAndIsOnMap(zone, true);
    }

}
