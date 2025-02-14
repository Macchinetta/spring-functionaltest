/*
 * Copyright(c) 2024 NTT Corporation.
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import org.terasoluna.gfw.common.time.ClockFactory;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfDate;
import com.lowagie.text.pdf.PdfDictionary;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfDownloadView extends AbstractPdfView {
    private static final Logger logger = LoggerFactory.getLogger(PdfDownloadView.class);

    @Inject
    ClockFactory clockFactory;

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // set output contents and embed the font
        BaseFont bf = BaseFont.createFont("HeiseiKakuGo-W5", "UniJIS-UCS2-H", false);
        Font normalFont = new Font(bf, 12);
        Font titleFont = new Font(bf, 18);
        Font headerFont = new Font(bf, 24);

        // create the content to be displayed
        HeaderFooter header = new HeaderFooter(new Phrase("銀行", headerFont), false);
        header.setAlignment(Element.ALIGN_CENTER);
        document.setHeader(header);

        HeaderFooter footer =
                new HeaderFooter(new Phrase("-", headerFont), new Phrase("-", headerFont));
        footer.setAlignment(Element.ALIGN_CENTER);
        document.setFooter(footer);

        document.open();

        document.add(new Paragraph(model.get("serverTime").toString()));

        // title
        Paragraph paragraph = new Paragraph(new Phrase("新規口座開設申込書", titleFont));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        // empty line
        document.add(Chunk.NEWLINE);

        // parent table
        PdfPTable parentTable = new PdfPTable(2);
        parentTable.setWidthPercentage(100.0f);
        int relativeWidth[] = {5, 95};
        parentTable.setWidths(relativeWidth);
        parentTable.getDefaultCell().setBorder(0000);

        // 申込日 table
        PdfPTable applicationDateTable = new PdfPTable(12);
        float TableWidth[] = {mm2pixel(20.0f), mm2pixel(7.0f), mm2pixel(7.0f), mm2pixel(7.0f),
                mm2pixel(7.0f), mm2pixel(7.0f), mm2pixel(7.0f), mm2pixel(7.0f), mm2pixel(7.0f),
                mm2pixel(7.0f), mm2pixel(7.0f), mm2pixel(7.0f)};
        applicationDateTable.setTotalWidth(TableWidth);
        applicationDateTable.setLockedWidth(true);
        applicationDateTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        applicationDateTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        applicationDateTable.getDefaultCell().setBorder(0000);
        applicationDateTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        applicationDateTable.addCell(new Phrase("お申込日", normalFont));

        // 年月日
        PdfPCell applicationYearCell = new PdfPCell(new Phrase(
                Integer.toString(LocalDateTime.now(clockFactory.fixed()).getYear()), normalFont));
        applicationYearCell.setColspan(4);
        applicationYearCell.setBorder(0);
        applicationYearCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        applicationDateTable.addCell(applicationYearCell);
        applicationDateTable.addCell(new Phrase("年", normalFont));
        PdfPCell applicationMonthCell = new PdfPCell(new Phrase(
                Integer.toString(LocalDateTime.now(clockFactory.fixed()).getMonthValue()),
                normalFont));
        applicationMonthCell.setColspan(2);
        applicationMonthCell.setBorder(0);
        applicationMonthCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        applicationDateTable.addCell(applicationMonthCell);
        applicationDateTable.addCell(new Phrase("月", normalFont));
        PdfPCell applicationDayCell = new PdfPCell(new Phrase(
                Integer.toString(LocalDateTime.now(clockFactory.fixed()).getDayOfMonth()),
                normalFont));
        applicationDayCell.setColspan(2);
        applicationDayCell.setBorder(0);
        applicationDayCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        applicationDateTable.addCell(applicationDayCell);
        applicationDateTable.addCell(new Phrase("日", normalFont));

        // add table to parent table
        parentTable.addCell("");
        parentTable.addCell(applicationDateTable);

        // empty lines
        parentTable.addCell(" ");
        parentTable.addCell(" ");
        parentTable.addCell(" ");
        parentTable.addCell(" ");

        // instructions
        paragraph = new Paragraph(new Phrase("以下の項目にご記入くださいますようお願いいたします。", normalFont));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        // document.add(paragraph);

        parentTable.addCell("");
        parentTable.addCell(paragraph);

        // empty lines
        parentTable.addCell(" ");
        parentTable.addCell(" ");
        parentTable.addCell(" ");
        parentTable.addCell(" ");

        // name and address table
        PdfPTable nameAndAddressTable = new PdfPTable(2);
        float nameAndAddressTableWidth[] = {mm2pixel(20.0f), mm2pixel(120.0f)};
        nameAndAddressTable.setTotalWidth(nameAndAddressTableWidth);
        nameAndAddressTable.setLockedWidth(true);
        nameAndAddressTable.getDefaultCell().setPadding(2.0f);
        nameAndAddressTable.getDefaultCell().setBorderWidth(1.5f);
        nameAndAddressTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        nameAndAddressTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        nameAndAddressTable.getDefaultCell().setFixedHeight(60.0f);

        nameAndAddressTable.setHorizontalAlignment(Element.ALIGN_LEFT);

        nameAndAddressTable.addCell(new Phrase("お名前", normalFont));

        nameAndAddressTable.addCell(new Phrase((String) (model.get("name")), normalFont));

        nameAndAddressTable.addCell(new Phrase("ご住所", normalFont));

        nameAndAddressTable.addCell(new Phrase(String.valueOf(model.get("address")), normalFont));

        nameAndAddressTable.addCell(new Phrase("生年月日", normalFont));

        // 生年月日 table
        PdfPTable birthDateTable = new PdfPTable(11);
        float birthDateTableWidth[] = {mm2pixel(7.0f), mm2pixel(7.0f), mm2pixel(7.0f),
                mm2pixel(7.0f), mm2pixel(7.0f), mm2pixel(7.0f), mm2pixel(7.0f), mm2pixel(7.0f),
                mm2pixel(7.0f), mm2pixel(7.0f), mm2pixel(7.0f)};
        birthDateTable.setTotalWidth(birthDateTableWidth);
        birthDateTable.setLockedWidth(true);
        birthDateTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        birthDateTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        birthDateTable.getDefaultCell().setBorder(0000);

        LocalDate birthdate = LocalDate.parse((String) model.get("birthdate"),
                DateTimeFormatter.ofPattern("yyyyMMdd"));

        PdfPCell birthdateYearCell =
                new PdfPCell(new Phrase(String.valueOf(birthdate.getYear()), normalFont));
        birthdateYearCell.setColspan(4);
        birthdateYearCell.setBorder(0);
        birthdateYearCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        birthDateTable.addCell(birthdateYearCell);
        birthDateTable.addCell(new Phrase("年", normalFont));

        PdfPCell birthdateMonthCell =
                new PdfPCell(new Phrase(String.valueOf(birthdate.getMonthValue()), normalFont));
        birthdateMonthCell.setColspan(2);
        birthdateMonthCell.setBorder(0);
        birthdateMonthCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        birthDateTable.addCell(birthdateMonthCell);
        birthDateTable.addCell(new Phrase("月", normalFont));

        PdfPCell birthdateDayCell =
                new PdfPCell(new Phrase(String.valueOf(birthdate.getDayOfMonth()), normalFont));
        birthdateDayCell.setColspan(2);
        birthdateDayCell.setBorder(0);
        birthdateDayCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        birthDateTable.addCell(birthdateDayCell);
        birthDateTable.addCell(new Phrase("日", normalFont));

        nameAndAddressTable.addCell(birthDateTable);

        parentTable.addCell("");
        parentTable.addCell(nameAndAddressTable);

        // empty lines
        parentTable.addCell(" ");
        parentTable.addCell(" ");
        parentTable.addCell(" ");
        parentTable.addCell(" ");

        // instructions
        parentTable.addCell("");
        paragraph = new Paragraph(new Phrase("以下のリストからいずれかの本人確認書類をご用意ください。", normalFont));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        parentTable.addCell(paragraph);

        // empty lines
        parentTable.addCell(" ");
        parentTable.addCell(" ");
        parentTable.addCell(" ");
        parentTable.addCell(" ");

        // Create a numbered List with 30-point field for the numbers.
        List list = new List(true, 30);
        list.setIndentationLeft(25.0f);
        list.add(new ListItem(new Phrase("運転免許証", normalFont)));
        list.add(new ListItem(new Phrase("外国人登録証明証 + パスポート", normalFont)));
        list.add(new ListItem(new Phrase("各種健康保険証 + 公共料金明細書", normalFont)));

        document.add(parentTable);

        document.add(list);

        // Add a separator
        document.add(Chunk.NEWLINE);

        // set fixed CreationDate and ModDate
        PdfDictionary info = writer.getInfo();
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(clockFactory.fixed().instant()));
        info.put(PdfName.CREATIONDATE, new PdfDate(cal));
        info.put(PdfName.MODDATE, new PdfDate(cal));

        // set filename to the response
        Browser browser = Browser.detect(request.getHeader("user-agent"));
        logger.debug("detected {}", browser);
        String filename = "日本語ファイル名.pdf";
        response.setContentType("application/pdf");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition",
                "attachment; " + browser.appendAndEncodeFilename(filename));
    }

    // mmを、PDF解像度のpixelに変換
    private static float mm2pixel(final float mm) {
        // 1インチ = 25.4 ミリメートル
        return (mm / 25.4f) * 72.0f;
    }

}
