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
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.terasoluna.gfw.common.exception.SystemException;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@Service
public class DownloadRestTemplateServiceImpl implements DownloadRestTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(DownloadRestTemplateServiceImpl.class);

    @Inject
    private RestTemplate restTemplate;

    @Value("${rscl.applicationContextUrl}/api/v1")
    private String uri;

    @Override
    public List<User> byteDownload() {
        byte[] downloadContent = restTemplate.getForObject(uri + "/users/download", byte[].class);

        File tmpFile = null;
        List<User> usersList = new ArrayList<>();

        try {
            // Create temporary file
            tmpFile = File.createTempFile("rcv", ".txt");
            FileUtils.writeByteArrayToFile(tmpFile, downloadContent);

            // Create UsersList
            usersList = fileToUsersList(tmpFile);
        } catch (Exception e) {
            throw new SystemException("e.sf.rc.9001", "input/output error.", e);
        } finally {
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }
        logger.info("List Size : {}", usersList.size());
        return usersList;

    }

    @Override
    public List<User> resourceDownload() {
        Resource downloadContent = restTemplate.getForObject(uri + "/users/download", Resource.class);

        File tmpFile = null;
        List<User> usersList = new ArrayList<>();

        try {
            // Create temporary file
            tmpFile = File.createTempFile("rcv", ".txt");
            // Copy Resource to temporary file
            try (InputStream in = downloadContent.getInputStream()) {
                Files.copy(in, tmpFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            // Create UsersList
            usersList = fileToUsersList(tmpFile);
        } catch (Exception e) {
            throw new SystemException("e.sf.rc.9001", "input/output error.", e);
        } finally {
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }
        logger.info("List Size : {}", usersList.size());
        return usersList;

    }

    @Override
    public List<User> inputStreamDownload() {

        final ResponseExtractor<File> responseExtractor = new ResponseExtractor<File>() {
            @Override
            public File extractData(ClientHttpResponse response)
                    throws IOException {

                File rcvFile = File.createTempFile("rcvFile", "zip");
                FileCopyUtils.copy(response.getBody(), new FileOutputStream(rcvFile));
                return rcvFile;
            }
        };

        File tmpFile = null;
        List<User> usersList = new ArrayList<>();

        try {
            tmpFile = restTemplate.execute(uri + "/users/download", HttpMethod.GET, null, responseExtractor);
            // Create UsersList
            usersList = fileToUsersList(tmpFile);
        } catch (Exception e) {
            throw new SystemException("e.sf.rc.9001", "input/output error.", e);
        } finally {
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }
        logger.info("List Size : {}", usersList.size());
        return usersList;
    }

    @Override
    public List<User> inputStreamDownloadError() {

        final ResponseExtractor<File> responseExtractor = new ResponseExtractor<File>() {
            @Override
            public File extractData(ClientHttpResponse response)
                    throws IOException {

                File rcvFile = File.createTempFile("rcvFile", "zip");
                FileCopyUtils.copy(response.getBody(), new FileOutputStream(rcvFile));
                return rcvFile;
            }
        };

        File tmpFile = null;
        List<User> usersList = new ArrayList<>();

        try {
            tmpFile = restTemplate.execute(uri + "/users/download/{statusCode}", HttpMethod.GET, null,
                    responseExtractor, 404);
            // Create UsersList
            usersList = fileToUsersList(tmpFile);
        } catch (HttpClientErrorException e) {
            logger.error("Download Error.", e);
            throw new SystemException("e.sf.rc.9001", "Client Error.", e);
        } catch (Exception e) {
            throw new SystemException("e.sf.rc.9001", "input/output error.", e);
        } finally {
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }
        logger.info("List Size : {}", usersList.size());
        return usersList;
    }

    @Override
    public List<User> inputStreamResponse() {
        final ResponseExtractor<ResponseEntity<File>> responseExtractor = new ResponseExtractor<ResponseEntity<File>>() {
            @Override
            public ResponseEntity<File> extractData(ClientHttpResponse response)
                    throws IOException {
                File rcvFile = File.createTempFile("rcvFile", "zip");
                FileCopyUtils.copy(response.getBody(), new FileOutputStream(rcvFile));
                return ResponseEntity.status(response.getStatusCode())
                        .headers(response.getHeaders()).body(rcvFile);
            }
        };

        File tmpFile = null;
        List<User> usersList = new ArrayList<>();

        try {
            ResponseEntity<File> responseEntity = restTemplate.execute(uri + "/users/download", HttpMethod.GET, null,
                    responseExtractor);
            tmpFile = responseEntity.getBody();

            logger.info("HttpStatus: {}", responseEntity.getStatusCode().value());
            logger.info("HttpHeader: {}", responseEntity.getHeaders());

            // Create UsersList
            usersList = fileToUsersList(tmpFile);
        } catch (Exception e) {
            throw new SystemException("e.sf.rc.9001", "input/output error.", e);
        } finally {
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }
        logger.info("List Size : {}", usersList.size());
        return usersList;
    }

    @Override
    public List<User> inputStreamResponseError() {
        final ResponseExtractor<ResponseEntity<File>> responseExtractor = new ResponseExtractor<ResponseEntity<File>>() {
            @Override
            public ResponseEntity<File> extractData(ClientHttpResponse response)
                    throws IOException {
                File rcvFile = File.createTempFile("rcvFile", "zip");
                FileCopyUtils.copy(response.getBody(), new FileOutputStream(rcvFile));
                return ResponseEntity.status(response.getStatusCode())
                        .headers(response.getHeaders()).body(rcvFile);
            }
        };

        File tmpFile = null;
        List<User> usersList = new ArrayList<>();

        try {
            ResponseEntity<File> responseEntity = restTemplate.execute(uri + "/users/download/{statusCode}",
                    HttpMethod.GET, null,
                    responseExtractor, 404);
            tmpFile = responseEntity.getBody();

            logger.info("HttpStatus: {}", responseEntity.getStatusCode().value());
            logger.info("HttpHeader: {}", responseEntity.getHeaders());

            // Create UsersList
            usersList = fileToUsersList(tmpFile);
        } catch (HttpClientErrorException e) {
            logger.error("Download Error.", e);
            throw new SystemException("e.sf.rc.9001", "Client Error.", e);
        } catch (Exception e) {
            throw new SystemException("e.sf.rc.9001", "input/output error.", e);
        } finally {
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }
        logger.info("List Size : {}", usersList.size());
        return usersList;
    }

    // Create UsersList from downloaded file
    private List<User> fileToUsersList(File tmpFile) throws Exception {
        List<User> usersList = new ArrayList<>();
        // Commented out to measure memory usage up to file creation
        try (BufferedReader br = new BufferedReader(new FileReader(tmpFile.getAbsolutePath()))) {
            usersList = br.lines().map(line -> line.split(","))
                    .map(items -> new User(items[0], Integer.parseInt(items[1]))).toList();
        }
        return usersList;
    }
}