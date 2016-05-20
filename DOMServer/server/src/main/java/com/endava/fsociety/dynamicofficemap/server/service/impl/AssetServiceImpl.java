package com.endava.fsociety.dynamicofficemap.server.service.impl;

import com.endava.fsociety.dynamicofficemap.server.model.Asset;
import com.endava.fsociety.dynamicofficemap.server.model.AssetType;
import com.endava.fsociety.dynamicofficemap.server.repository.AssetRepository;
import com.endava.fsociety.dynamicofficemap.server.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

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

}
