package com.endava.fsociety.dynamicofficemap.server.service;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by caldea on 5/21/2016.
 */
public interface ExportService {
    byte[] exportAssets(List<AssetDTO> assetDTOs);

    byte[] exportQR(String toBeEncoded);

    ResponseEntity<byte[]> createReponseForExcelExportedDocument(byte[] excelDocumentContent);

    ResponseEntity<byte[]> createReponseForPNGExportedDocument(byte[] excelDocumentContent);
}
