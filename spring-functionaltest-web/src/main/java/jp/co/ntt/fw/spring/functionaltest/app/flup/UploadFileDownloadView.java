/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UploadFile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.terasoluna.gfw.web.download.AbstractFileDownloadView;

@Component("flup/uploadFileDownload")
public class UploadFileDownloadView extends AbstractFileDownloadView {

    @Value("${app.upload.directory}")
    File uploadDirectory;

    @Override
    protected InputStream getInputStream(Map<String, Object> model,
            HttpServletRequest request) throws IOException {
        UploadFile uploadFile = extractUploadFile(model);
        InputStream downloadStream;
        if (uploadFile.getContent() != null) {
            downloadStream = new ByteArrayInputStream(uploadFile.getContent());
        } else {
            Path uploadDirectoryPath = Paths.get(uploadDirectory.getPath());
            Path uploadFilePath = Paths.get(uploadDirectory.getPath(),
                    uploadFile.getFileId());

            if (0 != uploadDirectoryPath.compareTo(uploadFilePath.normalize()
                    .getParent())) {
                throw new FileNotFoundException("File not found : "
                        + uploadFilePath.toString());
            }

            downloadStream = new FileSystemResource(new File(uploadDirectory, uploadFile
                    .getFileId())).getInputStream();
        }
        return downloadStream;
    }

    @Override
    protected void addResponseHeader(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) {
        UploadFile downloadFile = extractUploadFile(model);
        String downloadFileName;
        try {
            downloadFileName = URLEncoder.encode(new File(downloadFile
                    .getFileName()).getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
        setContentType(downloadFile.getContentType());
        response.setHeader("Content-Disposition", "attachment; filename="
                + downloadFileName);

    }

    private UploadFile extractUploadFile(Map<String, Object> model) {
        return UploadFile.class.cast(model.get("uploadFile"));
    }

}
