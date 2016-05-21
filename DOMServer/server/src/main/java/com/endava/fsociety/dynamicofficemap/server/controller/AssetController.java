package com.endava.fsociety.dynamicofficemap.server.controller;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetDTO;
import com.endava.fsociety.dynamicofficemap.server.viewservice.AssetViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/{assetId}/removeFromMap", method = RequestMethod.PUT)
    public AssetDTO removeFromMap(@PathVariable("assetId") String assetId) {
        return assetViewService.removeFromMap(assetId);
    }

    @RequestMapping(value = "/{assetCode}/tenant/{personId}", method = RequestMethod.PUT)
    public AssetDTO removeFromMap(@PathVariable("assetCode") String assetCode, @PathVariable("personId") String personId) {
        return assetViewService.updateTenantOnAsset(assetCode, personId);
    }

    @RequestMapping(value = "/floor/{floorId}", method = RequestMethod.GET)
    public List<AssetDTO> findByFloor(@PathVariable("floorId") String floorId) {
        return assetViewService.findByFloor(floorId);
    }

    @RequestMapping(value = "/map/{floorId}", method = RequestMethod.GET)
    public List<AssetDTO> findByMap(@PathVariable("floorId") String floorId) {
        return assetViewService.findByMap(floorId);
    }

    @RequestMapping(value = "/floor/{floorId}/assetType/{assetTypeId}", method = RequestMethod.GET)
    public List<AssetDTO> findByMapAndType(@PathVariable("floorId") String floorId, @PathVariable("assetTypeId") String assetTypeId) {
        return assetViewService.findByFloorAndTypeId(floorId, assetTypeId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public AssetDTO save(@RequestBody AssetDTO assetDTO) {
        return assetViewService.save(assetDTO);
    }

}
