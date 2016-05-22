package com.endava.fsociety.dynamicofficemap.server.viewservice.impl;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetTypeDTO;
import com.endava.fsociety.dynamicofficemap.server.exception.BadDataException;
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

    @Override
    public AssetTypeDTO save(AssetTypeDTO assetTypeDTO) {
        AssetType assetType;
        if (assetTypeDTO.getId() != null) {
            assetType = assetTypeService.findById(assetTypeDTO.getId());
            if (assetType == null) {
                throw new BadDataException("There is no asset type with id " + assetTypeDTO.getId());
            }
        } else {
            assetType = new AssetType();
        }

        assetType.setName(assetTypeDTO.getName());
        assetType.setCode(assetTypeDTO.getCode());
        assetType.setImage(assetTypeDTO.getImage());

        return new AssetTypeDTO(assetTypeService.save(assetType));
    }
}
