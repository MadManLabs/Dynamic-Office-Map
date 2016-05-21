package com.endava.fsociety.dynamicofficemap.server.service;

import com.endava.fsociety.dynamicofficemap.server.model.Asset;
import com.endava.fsociety.dynamicofficemap.server.model.AssetType;
import com.endava.fsociety.dynamicofficemap.server.model.Floor;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

public interface AssetService {

    List<Asset> findAll();

    List<Asset> findByAssetType(AssetType assetType);

    Asset findByCode(String code);

    Asset findById(String id);

    Asset save(Asset asset);

    List<Asset> findByFloor(Floor floor);

    List<Asset> findByMap(Floor floor);

}
