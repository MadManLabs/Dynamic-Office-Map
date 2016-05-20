package com.endava.fsociety.dynamicofficemap.server.controller;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetTypeDTO;
import com.endava.fsociety.dynamicofficemap.server.viewservice.AssetTypeViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@RestController
@RequestMapping("/assetType")
public class AssetTypeController {

    @Autowired
    private AssetTypeViewService assetTypeViewService;

    @RequestMapping(method = RequestMethod.GET)
    public List<AssetTypeDTO> findAll() {
        return assetTypeViewService.findAll();
    }

}
