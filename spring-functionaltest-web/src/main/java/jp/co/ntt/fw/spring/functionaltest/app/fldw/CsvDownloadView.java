/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
