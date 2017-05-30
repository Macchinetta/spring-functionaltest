/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jp.co.ntt.fw.spring.functionaltest.domain.service.exhn.ArticleFileService;

@Component
public class ArticleFileHelper {

    @Value("${app.upload.temporaryDirectory}")
    String uploadTemporaryDirectory;

    @Inject
    ArticleFileService articleFileService;

    String createTemporaryFile(ArticleBatchRegisterForm form) throws IOException {

        String uploadTemporaryFileId = UUID.randomUUID().toString();

        Path uploadTemporaryDirectoryPath = Paths.get(uploadTemporaryDirectory);

        if (Files.notExists(uploadTemporaryDirectoryPath)) {
            Files.createDirectories(uploadTemporaryDirectoryPath);
        }

        Path uploadTemporaryFile = Paths.get(uploadTemporaryDirectory,
                uploadTemporaryFileId);

        Files.copy(form.getMultipartFile().getInputStream(),
                uploadTemporaryFile, StandardCopyOption.REPLACE_EXISTING);

        form.setFileName(form.getMultipartFile().getOriginalFilename());

        return uploadTemporaryFile.getFileName().toString();
    }

    void uploadToDb(ArticleBatchRegisterForm form, String uploadTemporaryFileId) throws IOException {
        String title = form.getTitle();

        articleFileService.save(uploadTemporaryFileId, title);

    }

    void deleteTemporaryFile(String temporaryFileName) throws IOException {
        Path uploadTempDirecotry = Paths.get(uploadTemporaryDirectory);
        Path uploadTemporaryFile = Paths.get(uploadTemporaryDirectory,
                temporaryFileName);
        if (0 != uploadTempDirecotry.compareTo(uploadTemporaryFile.normalize()
                .getParent())) {
            throw new FileNotFoundException("File not found : "
                    + uploadTemporaryFile.toString());
        }
        Files.deleteIfExists(uploadTemporaryFile);
    }
}
