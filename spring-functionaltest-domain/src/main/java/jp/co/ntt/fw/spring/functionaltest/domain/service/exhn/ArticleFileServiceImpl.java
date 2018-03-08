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
package jp.co.ntt.fw.spring.functionaltest.domain.service.exhn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import org.terasoluna.gfw.common.exception.SystemException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.ArticleFile;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.exhn.ArticleFileRepository;

@Transactional
@Service
public class ArticleFileServiceImpl implements ArticleFileService {

    @Value("${app.upload.temporaryDirectory}")
    String uploadTemporaryDirectory;

    @Value("${app.upload.temporaryDirectory}completed/")
    String uploadCompletedTemporaryDirectory;

    @Inject
    ArticleFileRepository articleFileRepository;

    @Inject
    ExceptionLogger exceptionLogger;

    @Override
    public long countAll() {
        return articleFileRepository.countAll();
    }

    @Override
    public void save(String uploadTemporaryFileId,
            String title) throws IOException {

        Path uploadTemporaryFile = Paths.get(uploadTemporaryDirectory,
                uploadTemporaryFileId);
        Path uploadCompletedTemporaryDirectoryPath = Paths.get(
                uploadCompletedTemporaryDirectory);

        ArticleFile articleFile = new ArticleFile();
        articleFile.setFileId(uploadTemporaryFileId);
        articleFile.setTitle(title);

        try {
            articleFile.setContent(Files.readAllBytes(uploadTemporaryFile));
        } catch (IOException e) {
            throw new SystemException("e.sf.exhn.9000", "not found upload file. file is ["
                    + uploadTemporaryFile.toAbsolutePath() + "].", e);
        }

        articleFileRepository.register(articleFile);
        try {
            Files.move(uploadTemporaryFile,
                    uploadCompletedTemporaryDirectoryPath.resolve(
                            uploadTemporaryFile.getFileName()));
        } catch (IOException e) {
            exceptionLogger.log(e);
        }
    }

    @Override
    public void deleteAll() {
        articleFileRepository.deleteAll();
    }
}
