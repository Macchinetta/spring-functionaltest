/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.fldw;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class FileDownloadHelper {

    public void bindToModel(Model model, ContentDownloadForm form) throws UnsupportedEncodingException, IOException {
        model.addAttribute("name", form.getName());
        model.addAttribute("birthdate", form.getBirthdate());
        model.addAttribute("address", form.getAddress());
    }

    public JRDataSource getData(ContentDownloadForm form) {
        // Create an array list of Sales
        List<ContentDownloadResult> items = new ArrayList<ContentDownloadResult>();
        ContentDownloadResult item1 = new ContentDownloadResult();
        item1.setName(form.getName());
        item1.setBirthdate(form.getBirthdate());
        item1.setAddress(form.getAddress());

        items.add(item1);

        JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(items);
        return jrDataSource;
    }
}
