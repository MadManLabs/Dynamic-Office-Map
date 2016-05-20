package com.endava.fsociety.dynamicofficemap.server.controller;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetDTO;
import com.endava.fsociety.dynamicofficemap.server.viewservice.AssetViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fstancu on 5/20/2016.
 */

@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetViewService assetViewService;

    @RequestMapping(method = RequestMethod.POST)
    public AssetDTO save(@RequestBody AssetDTO assetDTO) {
        return assetViewService.save(assetDTO);
    }

}
