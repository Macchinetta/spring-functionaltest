/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.fldw;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRCsvExporter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.jasperreports.JasperReportsCsvView;

@Component
public class CsvDownloadView extends JasperReportsCsvView {

    @Override
    protected JRExporter createExporter() {

        JRExporter csvExporter = new JRCsvExporter();

        csvExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,
                "Windows-31J");

        return csvExporter;
    }

}
