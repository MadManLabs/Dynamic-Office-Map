package com.endava.fsociety.dynamicofficemap.server.controller;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetDTO;
import com.endava.fsociety.dynamicofficemap.server.viewservice.AssetViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fstancu on 5/20/2016.
 */

@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetViewService assetViewService;

    @RequestMapping(value = "/{assetId}", method = RequestMethod.GET)
    public AssetDTO findById(@PathVariable("assetId") String assetId) {
        return assetViewService.findById(assetId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public AssetDTO save(@RequestBody AssetDTO assetDTO) {
        return assetViewService.save(assetDTO);
    }

}
