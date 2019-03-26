/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.fldw;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;

@Component
public class ExcelDownloadView extends AbstractXlsxView {

    @Inject
    JodaTimeDateFactory dateFactory;

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // POIはシステムプロパティの「user.name」に設定されている値を作成者として設定する仕様になっている。
        // 現時点では、この作成者を明示的に設定する方法は用意されていない。
        // 本ロジックは、返却されるダウンロードデータが環境依存しないようにするために、一時的に「user.name」を変更している。
        String currentUserName = System.getProperty("user.name");
        System.setProperty("user.name", "ExcelDownloadView");
        try {
            super.render(model, request, response);
        } finally {
            System.setProperty("user.name", currentUserName);
        }

    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Sheet sheet = workbook.createSheet("新規口座開設申込書");
        sheet.setFitToPage(true);

        // Create merged region and corresponding row for the report header
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 8));
        Row headerRow = sheet.createRow(1);

        // Create merged region and corresponding row for the report title
        sheet.addMergedRegion(new CellRangeAddress(4, 5, 1, 8));
        Row titleRow = sheet.createRow(4);

        // Create merged region and corresponding row for application date value
        CellRangeAddress applicationDateValueRangeAddress = new CellRangeAddress(7, 7, 3, 4);
        sheet.addMergedRegion(applicationDateValueRangeAddress);
        Row applicationDateRow = sheet.createRow(7);

        // Create merged region and corresponding row for the report
        // instructions
        sheet.addMergedRegion(new CellRangeAddress(9, 10, 1, 8));
        Row instructionsRow = sheet.createRow(9);

        // Create merged region and corresponding row for name table
        CellRangeAddress nameRangeAddress = new CellRangeAddress(12, 14, 3, 7);
        CellRangeAddress nameValueRangeAddress = new CellRangeAddress(12, 14, 2, 2);
        sheet.addMergedRegion(nameRangeAddress);
        sheet.addMergedRegion(nameValueRangeAddress);
        Row nameRow = sheet.createRow(12);

        // Create merged region and corresponding row for address table
        CellRangeAddress addressValueRangeAddress = new CellRangeAddress(15, 17, 2, 2);
        CellRangeAddress addressRangeAddress = new CellRangeAddress(15, 17, 3, 7);
        sheet.addMergedRegion(addressValueRangeAddress);
        sheet.addMergedRegion(addressRangeAddress);
        Row addressRow = sheet.createRow(15);

        // Create merged region and corresponding row for birthdate table
        CellRangeAddress birthdateRangeAddress = new CellRangeAddress(18, 19, 2, 2);
        CellRangeAddress birthdateValueRangeAddress = new CellRangeAddress(18, 19, 3, 4);
        sheet.addMergedRegion(birthdateValueRangeAddress);
        sheet.addMergedRegion(birthdateRangeAddress);
        Row birthdateRow = sheet.createRow(18);

        // Create merged region and corresponding row for the required documents
        // info region
        sheet.addMergedRegion(new CellRangeAddress(21, 22, 1, 8));
        Row reqdocRow = sheet.createRow(21);

        // Create merged region and corresponding row for the required documents
        // list region
        sheet.addMergedRegion(new CellRangeAddress(24, 24, 2, 6));
        Row docListItem1Row = sheet.createRow(24);
        sheet.addMergedRegion(new CellRangeAddress(25, 25, 2, 6));
        Row docListItem2Row = sheet.createRow(25);
        sheet.addMergedRegion(new CellRangeAddress(26, 26, 2, 6));
        Row docListItem3Row = sheet.createRow(26);

        // This is for Header Style
        CellStyle headerCellStyle = (CellStyle) workbook.createCellStyle();
        Font setFont = (Font) workbook.createFont();
        setFont.setFontHeightInPoints((short) 18);
        setFont.setBold(true);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerCellStyle.setWrapText(true);
        headerCellStyle.setFont(setFont);

        // This is for title Style
        CellStyle titleCellStyle = (CellStyle) workbook.createCellStyle();
        Font setTitleFont = (Font) workbook.createFont();
        setTitleFont.setFontHeightInPoints((short) 14);
        setTitleFont.setBold(true);
        titleCellStyle.setFont(setTitleFont);
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
        titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleCellStyle.setWrapText(true);

        // This is for Data Style
        CellStyle dataCellStyle = (CellStyle) workbook.createCellStyle();
        Font setDataFont = (Font) workbook.createFont();
        setDataFont.setFontHeightInPoints((short) 10);
        setDataFont.setBold(true);
        dataCellStyle.setFont(setDataFont);
        dataCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        dataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // This is for List Style
        CellStyle ListCellStyle = (CellStyle) workbook.createCellStyle();
        Font setListFont = (Font) workbook.createFont();
        setListFont.setFontHeightInPoints((short) 10);
        setListFont.setBold(true);
        ListCellStyle.setFont(setListFont);
        ListCellStyle.setIndention((short) 1);
        ListCellStyle.setAlignment(HorizontalAlignment.LEFT);
        ListCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // fill in values in header row
        Cell headerCell = headerRow.createCell(1, CellType.BLANK);
        headerCell.setCellValue("銀行");
        headerCell.setCellStyle(headerCellStyle);

        // fill in values in title row
        Cell titleCell = titleRow.createCell(1, CellType.BLANK);
        titleCell.setCellValue("新規口座開設申込書");
        titleCell.setCellStyle(titleCellStyle);

        // fill in application date area
        Cell applicationDateCell = applicationDateRow.createCell(2,
                CellType.BLANK);
        applicationDateCell.setCellValue("申込日");
        applicationDateCell.setCellStyle(dataCellStyle);

        Cell applicationDateValueCell = applicationDateRow.createCell(3,
                CellType.BLANK);
        applicationDateValueCell.setCellValue(DateFormatUtils.format(dateFactory
                .newDate(), "yyyy年MM月dd日"));
        applicationDateValueCell.setCellStyle(dataCellStyle);

        // fill in instructions
        Cell instructionsCell = instructionsRow.createCell(1, CellType.BLANK);
        instructionsCell.setCellValue("以下の項目にご記入くださいますようお願いいたします。");
        instructionsCell.setCellStyle(dataCellStyle);

        // fill in name, address and birthdate table
        Cell nameCell = nameRow.createCell(2, CellType.BLANK);
        nameCell.setCellValue("お名前");
        nameCell.setCellStyle(dataCellStyle);

        Cell nameValueCell = nameRow.createCell(3, CellType.BLANK);
        nameValueCell.setCellValue((String) model.get("name"));
        nameValueCell.setCellStyle(ListCellStyle);

        Cell addressCell = addressRow.createCell(2, CellType.BLANK);
        addressCell.setCellValue("お住所");
        addressCell.setCellStyle(dataCellStyle);

        Cell addressValueCell = addressRow.createCell(3, CellType.BLANK);
        addressValueCell.setCellValue((String) model.get("address"));
        addressValueCell.setCellStyle(ListCellStyle);

        Cell birthdateCell = birthdateRow.createCell(2, CellType.BLANK);
        birthdateCell.setCellValue("生年月日");
        birthdateCell.setCellStyle(dataCellStyle);

        Cell birthdateValueCell = birthdateRow.createCell(3, CellType.BLANK);
        birthdateValueCell.setCellValue(DateFormatUtils.format(DateUtils
                .parseDate((String) model.get("birthdate"), "yyyyMMdd"),
                "yyyy年MM月dd日"));
        birthdateValueCell.setCellStyle(ListCellStyle);

        // fill in required documents information
        Cell reqDocCell = reqdocRow.createCell(1, CellType.BLANK);
        reqDocCell.setCellValue("以下のリストからいずれかの本人確認書類をご用意ください。");
        reqDocCell.setCellStyle(dataCellStyle);

        // fill in document list items
        Cell DocListItem1Cell = docListItem1Row.createCell(2, CellType.BLANK);
        DocListItem1Cell.setCellValue("1. 運転免許証");
        DocListItem1Cell.setCellStyle(ListCellStyle);
        Cell DocListItem2Cell = docListItem2Row.createCell(2, CellType.BLANK);
        DocListItem2Cell.setCellValue("2. 外国人登録証明証 + パスポート");
        DocListItem2Cell.setCellStyle(ListCellStyle);
        Cell DocListItem3Cell = docListItem3Row.createCell(2, CellType.BLANK);
        DocListItem3Cell.setCellValue("3. 各種健康保険証 + 公共料金明細書");
        DocListItem3Cell.setCellStyle(ListCellStyle);

        // give borders to all the tables
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, nameRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, nameRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, nameRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, nameRangeAddress, sheet);

        RegionUtil.setBorderTop(BorderStyle.MEDIUM, nameValueRangeAddress,
                sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, nameValueRangeAddress,
                sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, nameValueRangeAddress,
                sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, nameValueRangeAddress,
                sheet);

        RegionUtil.setBorderTop(BorderStyle.MEDIUM, addressRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, addressRangeAddress,
                sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, addressRangeAddress,
                sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, addressRangeAddress,
                sheet);

        RegionUtil.setBorderTop(BorderStyle.MEDIUM, addressValueRangeAddress,
                sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, addressValueRangeAddress,
                sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, addressValueRangeAddress,
                sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, addressValueRangeAddress,
                sheet);

        RegionUtil.setBorderTop(BorderStyle.MEDIUM, birthdateRangeAddress,
                sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, birthdateRangeAddress,
                sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, birthdateRangeAddress,
                sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, birthdateRangeAddress,
                sheet);

        RegionUtil.setBorderTop(BorderStyle.MEDIUM, birthdateValueRangeAddress,
                sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM,
                birthdateValueRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, birthdateValueRangeAddress,
                sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM,
                birthdateValueRangeAddress, sheet);

        String filename = "日本語ファイル名.xlsx";
        Browser browser = Browser.detect(request.getHeader("user-agent"));
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;" + browser
                .appendAndEncodeFilename(filename));
    }

}
