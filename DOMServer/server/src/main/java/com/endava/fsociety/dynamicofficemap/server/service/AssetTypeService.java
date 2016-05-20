package com.endava.fsociety.dynamicofficemap.server.service;

import com.endava.fsociety.dynamicofficemap.server.model.AssetType;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

public interface AssetTypeService {

    AssetType findById(String id);

    List<AssetType> findAll();

}
