/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.terasoluna.gfw.common.message.ResultMessages;

@Component
public class TemporaryFilesHelper {
    private static final Logger logger = LoggerFactory.getLogger(
            TemporaryFilesHelper.class);

    @Value("${app.upload.temporaryDirectory}")
    File temporaryDirectory;

    public File saveTemporaryFile(
            MultipartFile multipartFile) throws IOException {

        Path temporaryDirectoryPath = temporaryDirectory.toPath();

        if (Files.notExists(temporaryDirectoryPath)) {
            Files.createDirectories(temporaryDirectoryPath);
        }

        String temporaryFileId = UUID.randomUUID().toString();
        File temporaryFile = new File(temporaryDirectory, temporaryFileId);

        Files.copy(multipartFile.getInputStream(), temporaryFile.toPath());

        return temporaryFile;
    }

    public void bindTemporaryFileToModel(Model model) {
        List<UploadedContent> temporaryFiles = new ArrayList<>();

        File[] targetFiles = temporaryDirectory.listFiles();
        for (File targetFile : targetFiles) {
            UploadedContent uploadedFile = new UploadedContent();
            uploadedFile.setFileId(targetFile.getName());
            uploadedFile.setSize(targetFile.length());
            uploadedFile.setUploadedAt(new Date(targetFile.lastModified()));
            temporaryFiles.add(uploadedFile);
        }
        if (temporaryFiles.size() == 0) {
            model.addAttribute(ResultMessages.info().add("i.sf.flup.0001"));
        }
        model.addAttribute("temporaryFiles", temporaryFiles);
    }

    public void clearTemporarydirectory() throws IOException {
        if (!existsTemporaryDirectory()) {
            throw new IllegalStateException("temporaryDirectory does not exist.");
        }

        File[] targetFiles = temporaryDirectory.listFiles();
        if (targetFiles.length == 0) {
            return;
        }

        for (File targetFile : targetFiles) {
            // 誤ファイル削除防止のため、削除対象ファイルを確認
            checkDeleteFile(targetFile);
            Files.delete(targetFile.toPath());
            logger.debug("アップロード一時ディレクトリのファイルを削除しました。ファイル名：" + targetFile
                    .getAbsolutePath());
        }
    }

    public boolean existsTemporaryDirectory() {
        return temporaryDirectory != null && temporaryDirectory.exists();
    }

    private void checkDeleteFile(File file) {
        String fileName = file.getName();

        if (file.isDirectory()) {
            throw new IllegalStateException("アップロード一時ディレクトリ「"
                    + temporaryDirectory.getAbsolutePath() + "」に" + "サブディレクトリ「"
                    + fileName + "」が存在します。" + "一時ディレクト内ファイルの削除処理を中断します。");
        }
        // UUID簡易チェック
        try {
            UUID.fromString(fileName);
        } catch (IllegalArgumentException iae) {
            throw new IllegalStateException("アップロード一時ディレクトリ「"
                    + temporaryDirectory.getAbsolutePath() + "」に"
                    + "UUID形式でないファイル「" + fileName + "」が存在します。"
                    + "一時ディレクト内ファイルの削除処理を中断します。", iae);
        }
    }

}
