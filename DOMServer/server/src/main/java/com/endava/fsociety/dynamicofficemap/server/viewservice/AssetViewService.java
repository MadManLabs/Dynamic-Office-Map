package com.endava.fsociety.dynamicofficemap.server.viewservice;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetDTO;

/**
 * Created by fstancu on 5/11/2016.
 */

public interface AssetViewService {

    AssetDTO findById(String id);

    AssetDTO save(AssetDTO assetDTO);

}
