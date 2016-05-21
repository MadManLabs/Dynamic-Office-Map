package com.endava.fsociety.dynamicofficemap.server.controller;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetDTO;
import com.endava.fsociety.dynamicofficemap.server.service.ExportService;
import com.endava.fsociety.dynamicofficemap.server.viewservice.AssetViewService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
        return exportService.createReponseForExportedDocument(exportService.exportAssets(assetDTOs));
    }



}
