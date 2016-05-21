package com.endava.fsociety.dynamicofficemap.server.service.impl;

import com.endava.fsociety.dynamicofficemap.server.model.AssetType;
import com.endava.fsociety.dynamicofficemap.server.repository.AssetTypeRepository;
import com.endava.fsociety.dynamicofficemap.server.service.AssetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@Service
public class AssetTypeServiceImpl implements AssetTypeService {

    @Autowired
    private AssetTypeRepository assetTypeRepository;

    @Override
    public AssetType findById(String id) {
        return assetTypeRepository.findOne(id);
    }

    public List<AssetType> findAll() {
        return assetTypeRepository.findAll();
    }

    @Override
    public AssetType save(AssetType assetType) {
        return assetTypeRepository.save(assetType);
    }

}
