package com.endava.fsociety.dynamicofficemap.server.controller;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetDTO;
import com.endava.fsociety.dynamicofficemap.server.service.ExportService;
import com.endava.fsociety.dynamicofficemap.server.viewservice.AssetViewService;
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
    private ExportService exportService;


    @RequestMapping(value = "/asset/floor/{floorId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]>  exportAssets(@PathVariable("floorId") String floorId) {
        List<AssetDTO> assetDTOs = assetViewService.findByFloor(floorId);
        return exportService.createReponseForExcelExportedDocument(exportService.exportAssets(assetDTOs));
    }

    @RequestMapping(value = "/asset/qr/{assetId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]>  exportQRPerAsset(@PathVariable("assetId") String assetId) {
        AssetDTO assetDTO = assetViewService.findById(assetId);
        return exportService.createReponseForPNGExportedDocument(exportService.exportQR("ASSET="+assetDTO.getCode()));
    }



}
