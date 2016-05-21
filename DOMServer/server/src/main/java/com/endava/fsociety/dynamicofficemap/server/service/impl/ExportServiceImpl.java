package com.endava.fsociety.dynamicofficemap.server.service.impl;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetDTO;
import com.endava.fsociety.dynamicofficemap.server.service.ExportService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by caldea on 5/21/2016.
 */
@Service
public class ExportServiceImpl implements ExportService {

    public static final String TAG_HTML_START = "<html>";
    public static final String TAG_HTML_STOP = "</html>";


    public static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
    public static final String HEADER_CONTENT_ATTACHMENT_FILE = "attachment; filename=\"%s\"";


    public static final String EXCEL_EXTENSION = ".xlsx";

    public static final String SPREADSHEET_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String ASSETS_DOCUMENT_NAME = "Export%s" + EXCEL_EXTENSION;
    private static final String ASSETS_DOCUMENT_TIMESTAMP = "_YYYYMMdd_HHmmss";

    private static final String ASSETS_ERROR_WHILE_GENERATING =
            "A problem occurred on the Server while generating the Document containing Assets. " +
                    "Please Contact the Administrator.";

    public static final String[] ASSET_HEADER_TITLES =
            {"Code", "Name", "Type"};

    @Override
    public byte[] exportAssets(List<AssetDTO> assetDTOs) {
        Workbook workbook = createAssetWorkbook(assetDTOs);
        return getAsByteArray(workbook);
    }

    private static byte[] getAsByteArray(Workbook workbook) {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public Workbook createAssetWorkbook(List<AssetDTO> assetDTOs) {
        final Workbook workbook = new XSSFWorkbook();
        final Sheet sheet = workbook.createSheet();

        int currentRowNumber = 0;

        final Row headerRow = sheet.createRow(currentRowNumber++);
        for (int i = 0; i < ASSET_HEADER_TITLES.length; i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(ASSET_HEADER_TITLES[i]);
        }
        for (AssetDTO assetDTO : assetDTOs) {
            final String[] cellValues = new String[]{
                    assetDTO.getCode(),
                    assetDTO.getName(),
                    assetDTO.getTypeName(),

            };

            final Row unsubmittedOrderRow = sheet.createRow(currentRowNumber++);
            for (int currentCellNumber = 0; currentCellNumber < cellValues.length; currentCellNumber++) {
                Cell currentCell = unsubmittedOrderRow.createCell(currentCellNumber);
                currentCell.setCellValue(cellValues[currentCellNumber]);
            }
        }
        return workbook;
    }

    @Override
    public ResponseEntity<byte[]> createReponseForExportedDocument(byte[] excelDocumentContent) {
        if (excelDocumentContent.length == 0) {
            byte[] errorGeneratingDocumentOutput = (TAG_HTML_START + ASSETS_ERROR_WHILE_GENERATING + TAG_HTML_STOP).getBytes();
            return new ResponseEntity<byte[]>(errorGeneratingDocumentOutput, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();

        String contentType = SPREADSHEET_CONTENT_TYPE;
        headers.setContentType(MediaType.valueOf(contentType));

        String documentName = generateNewExportName();
        headers.set(HEADER_CONTENT_DISPOSITION, String.format(HEADER_CONTENT_ATTACHMENT_FILE, documentName));

        return new ResponseEntity<byte[]>(excelDocumentContent, headers, HttpStatus.OK);
    }

    private String generateNewExportName() {
        return String.format(ASSETS_DOCUMENT_NAME, DateTimeFormat.forPattern(ASSETS_DOCUMENT_TIMESTAMP).print(DateTime.now()));
    }
}
