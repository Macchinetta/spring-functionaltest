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
