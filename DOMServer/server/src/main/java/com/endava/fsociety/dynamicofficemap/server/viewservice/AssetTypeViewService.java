package com.endava.fsociety.dynamicofficemap.server.viewservice;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetTypeDTO;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

public interface AssetTypeViewService {

    List<AssetTypeDTO> findAll();

    AssetTypeDTO save(AssetTypeDTO assetTypeDTO);

}
