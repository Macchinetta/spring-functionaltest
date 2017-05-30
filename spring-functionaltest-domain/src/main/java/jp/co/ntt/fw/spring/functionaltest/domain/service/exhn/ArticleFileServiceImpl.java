/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.exhn;

import java.io.FileNotFoundException;
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

    @Inject
    ArticleFileRepository articleFileRepository;

    @Inject
    ExceptionLogger exceptionLogger;

    @Override
    public long countAll() {
        return articleFileRepository.countAll();
    }

    @Override
    public void save(String uploadTemporaryFileId, String title) throws IOException {

        Path uploadTempDirectory = Paths.get(uploadTemporaryDirectory);
        Path uploadTemporaryFile = Paths.get(uploadTemporaryDirectory,
                uploadTemporaryFileId);

        if (0 != uploadTempDirectory.compareTo(uploadTemporaryFile.normalize()
                .getParent())) {
            throw new FileNotFoundException("File not found : "
                    + uploadTemporaryFile.toString());
        }

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
            // 試験のためスリープ処理を埋め込んでいる
            Thread.sleep(5000L);
            Files.delete(uploadTemporaryFile);
        } catch (IOException e) {
            exceptionLogger.log(e);
        } catch (InterruptedException e) {
            exceptionLogger.log(e);
        }
    }

    @Override
    public void deleteAll() {
        articleFileRepository.deleteAll();
    }

}
