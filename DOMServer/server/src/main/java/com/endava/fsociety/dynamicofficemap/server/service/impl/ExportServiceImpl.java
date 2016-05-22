package com.endava.fsociety.dynamicofficemap.server.service.impl;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetDTO;
import com.endava.fsociety.dynamicofficemap.server.service.ExportService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

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
    public static final String PNG_EXTENSION = "png";

    public static final String SPREADSHEET_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String PNG_CONTENT_TYPE = "image/png";
    private static final String ASSETS_DOCUMENT_NAME = "Export%s" + EXCEL_EXTENSION;
    private static final String PNG_DOCUMENT_NAME = "Image%s" + ".png";
    private static final String ASSETS_DOCUMENT_TIMESTAMP = "_YYYYMMdd_HHmmss";

    private static final String ASSETS_ERROR_WHILE_GENERATING =
            "A problem occurred on the Server while generating the Document containing Assets. " +
                    "Please Contact the Administrator.";

    public static final String[] ASSET_HEADER_TITLES =
            {"Code", "Name", "Type", "Tenant", "Zone", "Floor"};

    @Override
    public byte[] exportAssets(List<AssetDTO> assetDTOs) {
        Workbook workbook = createAssetWorkbook(assetDTOs);
        return getAsByteArray(workbook);
    }

    @Override
    public byte[] exportQR(String toBeEncoded) {
        return generateQR(toBeEncoded);
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
                    assetDTO.getTenantName(),
                    assetDTO.getZoneName(),
                    assetDTO.getFloorName()
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
    public ResponseEntity<byte[]> createReponseForExcelExportedDocument(byte[] excelDocumentContent) {
        if (excelDocumentContent.length == 0) {
            byte[] errorGeneratingDocumentOutput = (TAG_HTML_START + ASSETS_ERROR_WHILE_GENERATING + TAG_HTML_STOP).getBytes();
            return new ResponseEntity<byte[]>(errorGeneratingDocumentOutput, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();

        String contentType = SPREADSHEET_CONTENT_TYPE;
        headers.setContentType(MediaType.valueOf(contentType));

        String documentName = generateNewExcelExportName();
        headers.set(HEADER_CONTENT_DISPOSITION, String.format(HEADER_CONTENT_ATTACHMENT_FILE, documentName));

        return new ResponseEntity<byte[]>(excelDocumentContent, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> createReponseForPNGExportedDocument(byte[] pngDocumentContent) {
        if (pngDocumentContent.length == 0) {
            byte[] errorGeneratingDocumentOutput = (TAG_HTML_START + ASSETS_ERROR_WHILE_GENERATING + TAG_HTML_STOP).getBytes();
            return new ResponseEntity<byte[]>(errorGeneratingDocumentOutput, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();

        String contentType = PNG_CONTENT_TYPE;
        headers.setContentType(MediaType.valueOf(contentType));

        String documentName = generateNewPngExportName();
        //headers.set(HEADER_CONTENT_DISPOSITION, String.format(HEADER_CONTENT_ATTACHMENT_FILE, documentName));

        return new ResponseEntity<byte[]>(pngDocumentContent, headers, HttpStatus.OK);
    }

    private String generateNewExcelExportName() {
        return String.format(ASSETS_DOCUMENT_NAME, DateTimeFormat.forPattern(ASSETS_DOCUMENT_TIMESTAMP).print(DateTime.now()));
    }

    private String generateNewPngExportName() {
        return String.format(PNG_DOCUMENT_NAME, DateTimeFormat.forPattern(ASSETS_DOCUMENT_TIMESTAMP).print(DateTime.now()));
    }

    private byte[] generateQR(String argument) {
        String myCodeText = argument;
        int size = 250;
        String fileType = PNG_EXTENSION;
        try {

            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, fileType, baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
