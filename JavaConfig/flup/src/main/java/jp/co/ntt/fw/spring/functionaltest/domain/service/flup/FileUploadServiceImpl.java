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
package jp.co.ntt.fw.spring.functionaltest.domain.service.flup;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.time.ClockFactory;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UploadFile;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.flup.UploadFileRepository;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${app.upload.temporaryDirectory}")
    String uploadTemporaryDirectory;

    @Value("${app.upload.directory}")
    String uploadDirectory;

    @Inject
    UploadFileRepository uploadFileRepository;

    @Inject
    ClockFactory clockFactory;

    public UploadFile saveFileToDisc(InputStream content, UploadFile newUploadFile)
            throws IOException {

        String fileId = generateFileId();

        newUploadFile.setFileId(fileId);
        newUploadFile.setUploadedAt(LocalDate.now(clockFactory.fixed()));

        // ファイルのDB登録
        uploadFileRepository.insert(newUploadFile);

        Path uploadDirectoryPath = Paths.get(uploadDirectory);

        if (Files.notExists(uploadDirectoryPath)) {
            Files.createDirectories(uploadDirectoryPath);
        }

        Path uploadFile = Paths.get(uploadDirectory, fileId);
        Files.copy(content, uploadFile);

        // ディスクへ保存した中身を設定
        newUploadFile.setContent(Files.readAllBytes(uploadFile));

        return newUploadFile;
    }

    @Override
    public UploadFile saveFileToDisc(String temporaryFileId, UploadFile newUploadFile)
            throws IOException {

        Path uploadTempDir = Paths.get(uploadTemporaryDirectory);
        Path temporaryFile = Paths.get(uploadTemporaryDirectory, temporaryFileId);

        if (0 != uploadTempDir.compareTo(temporaryFile.normalize().getParent())) {
            throw new FileNotFoundException("File not found : " + temporaryFile.toString());
        }

        Path uploadDirectoryPath = Paths.get(uploadDirectory);

        if (Files.notExists(uploadDirectoryPath)) {
            Files.createDirectories(uploadDirectoryPath);
        }

        String fileId = generateFileId();

        newUploadFile.setFileId(fileId);
        newUploadFile.setUploadedAt(LocalDate.now(clockFactory.fixed()));

        // ファイルのDB登録
        uploadFileRepository.insert(newUploadFile);

        Path uploadFile = Paths.get(uploadDirectory, fileId);
        Files.move(temporaryFile, uploadFile);

        // ディスクへ保存した中身を設定
        newUploadFile.setContent(Files.readAllBytes(uploadFile));

        return newUploadFile;
    }

    @Transactional
    @Override
    public UploadFile saveFileToDb(String temporaryFileId, UploadFile newUploadFile)
            throws IOException {

        Path uploadTempDir = Paths.get(uploadTemporaryDirectory);
        Path temporaryFile = Paths.get(uploadTemporaryDirectory, temporaryFileId);

        if (0 != uploadTempDir.compareTo(temporaryFile.normalize().getParent())) {
            throw new FileNotFoundException("File not found : " + temporaryFile.toString());
        }

        byte[] content = Files.readAllBytes(temporaryFile);

        String fileId = generateFileId();

        newUploadFile.setFileId(fileId);
        newUploadFile.setContent(content);
        newUploadFile.setUploadedAt(LocalDate.now(clockFactory.fixed()));

        // ファイルのDB登録
        uploadFileRepository.insert(newUploadFile);

        // 一時ファイルの削除
        Files.delete(temporaryFile);

        return newUploadFile;
    }

    @Transactional(readOnly = true)
    @Override
    public UploadFile getUploadFile(String fileId) {
        UploadFile loadedUploadFile = uploadFileRepository.findByFileId(fileId);
        if (loadedUploadFile == null) {
            throw new ResourceNotFoundException(
                    String.format("Specified uploaded file not found. file id is '%s'.", fileId));
        }
        return loadedUploadFile;
    }

    private String generateFileId() {
        return UUID.randomUUID().toString();
    }

}
