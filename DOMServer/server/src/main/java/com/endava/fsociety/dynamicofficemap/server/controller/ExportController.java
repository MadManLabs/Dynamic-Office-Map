package com.endava.fsociety.dynamicofficemap.server.controller;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetDTO;
import com.endava.fsociety.dynamicofficemap.server.dto.ZoneDTO;
import com.endava.fsociety.dynamicofficemap.server.service.ExportService;
import com.endava.fsociety.dynamicofficemap.server.viewservice.AssetViewService;
import com.endava.fsociety.dynamicofficemap.server.viewservice.ZoneViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/export")
public class ExportController {


    @Autowired
    private AssetViewService assetViewService;

    @Autowired
    private ZoneViewService zoneViewService;

    @Autowired
    private ExportService exportService;


    @RequestMapping(value = "/asset/floor/{floorId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]>  exportAssets(@PathVariable("floorId") String floorId) {
        List<AssetDTO> assetDTOs = assetViewService.findByFloor(floorId);
        return exportService.createReponseForExcelExportedDocument(exportService.exportAssets(assetDTOs));
    }

    @RequestMapping(value = "/asset/floor/{floorId}/assetType/{assetTypeId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]>  exportAssetsByFloorAndType(@PathVariable("floorId") String floorId, @PathVariable("assetTypeId") String assetTypeId) {
        List<AssetDTO> assetDTOs = assetViewService.findByFloorAndTypeId(floorId, assetTypeId);
        return exportService.createReponseForExcelExportedDocument(exportService.exportAssets(assetDTOs));
    }

    @RequestMapping(value = "/asset/qr/{assetId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]>  exportQRPerAsset(@PathVariable("assetId") String assetId) {
        AssetDTO assetDTO = assetViewService.findById(assetId);
        return exportService.createReponseForPNGExportedDocument(exportService.exportQR("ASSET=" + assetDTO.getCode()));
    }

    @RequestMapping(value = "/zone/qr/{zoneId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]>  exportQRPerZone(@PathVariable("zoneId") String zoneId) {
        ZoneDTO zoneDTO = zoneViewService.findById(zoneId);
        return exportService.createReponseForPNGExportedDocument(exportService.exportQR("ZONE="+zoneDTO.getCode()));
    }



}
