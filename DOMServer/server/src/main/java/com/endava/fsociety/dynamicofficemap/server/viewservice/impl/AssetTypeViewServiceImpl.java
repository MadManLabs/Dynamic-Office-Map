package com.endava.fsociety.dynamicofficemap.server.viewservice.impl;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetTypeDTO;
import com.endava.fsociety.dynamicofficemap.server.model.AssetType;
import com.endava.fsociety.dynamicofficemap.server.service.AssetTypeService;
import com.endava.fsociety.dynamicofficemap.server.viewservice.AssetTypeViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@Service
public class AssetTypeViewServiceImpl implements AssetTypeViewService {

    @Autowired
    private AssetTypeService assetTypeService;

    public List<AssetTypeDTO> findAll() {
        List<AssetType> assetTypes = assetTypeService.findAll();
        List<AssetTypeDTO> assetTypeDTOs = new ArrayList<AssetTypeDTO>();
        for (AssetType assetType : assetTypes) {
            assetTypeDTOs.add(new AssetTypeDTO(assetType));
        }
        return assetTypeDTOs;
    }
}
