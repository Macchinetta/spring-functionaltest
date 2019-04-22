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
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jp.co.ntt.fw.spring.functionaltest.domain.service.exhn.ArticleFileService;

@Component
public class ArticleFileHelper {

    @Value("${app.upload.temporaryDirectory}")
    String uploadTemporaryDirectory;

    @Value("${app.upload.temporaryDirectory}completed/")
    String uploadCompletedTemporaryDirectory;

    @Inject
    ArticleFileService articleFileService;

    String createTemporaryFile(
            ArticleBatchRegisterForm form) throws IOException {

        String uploadTemporaryFileId = UUID.randomUUID().toString();

        Path uploadTemporaryDirectoryPath = Paths.get(uploadTemporaryDirectory);
        Path uploadCompletedTemporaryDirectoryPath = Paths.get(
                uploadCompletedTemporaryDirectory);

        if (Files.notExists(uploadTemporaryDirectoryPath)) {
            Files.createDirectories(uploadTemporaryDirectoryPath);
        }
        if (Files.notExists(uploadCompletedTemporaryDirectoryPath)) {
            Files.createDirectories(uploadCompletedTemporaryDirectoryPath);
        }

        Path uploadTemporaryFile = uploadTemporaryDirectoryPath.resolve(
                uploadTemporaryFileId);

        Files.copy(form.getMultipartFile().getInputStream(),
                uploadTemporaryFile, StandardCopyOption.REPLACE_EXISTING);

        form.setFileName(form.getMultipartFile().getOriginalFilename());

        return uploadTemporaryFile.getFileName().toString();
    }

    void uploadToDb(ArticleBatchRegisterForm form,
            String uploadTemporaryFileId) throws IOException {
        articleFileService.save(uploadTemporaryFileId, form.getTitle());
    }

    void deleteTemporaryFile(String temporaryFileName) throws IOException {
        Path uploadTemporaryFile = Paths.get(uploadTemporaryDirectory,
                temporaryFileName);
        Files.deleteIfExists(uploadTemporaryFile);
    }

    void deleteAll() throws IOException {
        Path uploadTemporaryDirectoryPath = Paths.get(uploadTemporaryDirectory);
        if (Files.exists(uploadTemporaryDirectoryPath)) {
            Files.walkFileTree(uploadTemporaryDirectoryPath,
                    new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path path,
                                BasicFileAttributes attributes) throws IOException {
                            Files.delete(path);
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path path,
                                IOException exception) throws IOException {
                            Files.delete(path);
                            return FileVisitResult.CONTINUE;
                        }
                    });
        }
        articleFileService.deleteAll();
    }

    void copyTemporaryFile(String temporaryFileName) throws IOException {
        // testEXHN0203001でIOエラーが発生するように先にコピーしておく
        Files.copy(Paths.get(uploadTemporaryDirectory, temporaryFileName), Paths
                .get(uploadCompletedTemporaryDirectory, temporaryFileName));
    }
}
