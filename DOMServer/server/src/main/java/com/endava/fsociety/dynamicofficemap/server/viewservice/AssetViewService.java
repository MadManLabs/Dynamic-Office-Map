package com.endava.fsociety.dynamicofficemap.server.viewservice;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetDTO;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

public interface AssetViewService {

    AssetDTO findById(String id);

    AssetDTO save(AssetDTO assetDTO);

    List<AssetDTO> findByFloor(String floorId);

    /**
     * This will return the assets that are added on the map.
     * 
     * @param floorId
     * @return
     */

    List<AssetDTO> findByMap(String floorId);

    List<AssetDTO> findByFloorAndTypeId(String floorId, String assetTypeId);

    AssetDTO removeFromMap(String id);

    AssetDTO updateTenantOnAsset(String assetCode, String tenantId);
}
