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
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;
import org.springframework.web.util.UriComponentsBuilder;
import org.terasoluna.gfw.common.exception.SystemException;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

@Service
public class RestClientServiceImpl implements RestClientService {

    private static final Logger logger = LoggerFactory.getLogger(RestClientServiceImpl.class);

    @Inject
    RestTemplate restTemplate;

    @Inject
    RestTemplate bufferingRestTemplate;

    @Value("${rscl.applicationContextUrl}/api/v1/rscl/{opt}")
    String uri;

    @Value("${rscl.retry.maxCount}")
    int retryMaxCount;

    @Value("${rscl.retry.retryWaitTimeCoefficient}")
    int retryWaitTimeCoefficient;

    @Value("${rscl.upload.filePath}")
    String sendFilePath;

    @Override
    public UserResource getForObject(String path) {
        return this.restTemplate.getForObject(this.uri, UserResource.class, path);
    }

    @Override
    public UserResource getForEntity(String path) {
        URI targetUri = this.getUri(this.uri, path);

        ResponseEntity<UserResource> res =
                this.restTemplate.getForEntity(targetUri, UserResource.class);

        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("RSCL0102001 : {}", contentType);
        }
        logger.info("RSCL0102001 : {}", res.getStatusCode());

        return res.getBody();
    }

    @Override
    public UserResource exchange(String path) {
        URI targetUri = this.getUri(this.uri, path);
        RequestEntity<Void> req = RequestEntity.get(targetUri).build();

        ResponseEntity<UserResource> res = this.restTemplate.exchange(req, UserResource.class);
        logger.info("RSCL0103001 : {}", res.getStatusCode());

        return res.getBody();
    }

    @Override
    public UserResource exchangeJson(String path) {
        URI targetUri = this.getUri(this.uri, path);
        // JSONで受取るようヘッダの指定。
        RequestEntity<Void> req =
                RequestEntity.get(targetUri).accept(MediaType.APPLICATION_JSON).build();

        ResponseEntity<UserResource> res = this.restTemplate.exchange(req, UserResource.class);

        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("RSCL0104001 : {}", contentType);
        }
        logger.info("RSCL0104001 : {}", res.getStatusCode());

        return res.getBody();
    }

    @Override
    public UserResource postForObject(String path, UserResource user) {
        URI targetUri = this.getUri(this.uri, path);

        return this.restTemplate.postForObject(targetUri, user, UserResource.class);
    }

    @Override
    public UserResource postForObjectAddContentLength(String path, UserResource user) {
        URI targetUri = this.getUri(this.uri, path);

        return this.bufferingRestTemplate.postForObject(targetUri, user, UserResource.class);
    }

    @Override
    public UserResource postForEntity(String path, UserResource user) {
        URI targetUri = this.getUri(this.uri, path);

        ResponseEntity<UserResource> res =
                this.restTemplate.postForEntity(targetUri, user, UserResource.class);
        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("RSCL0107001 : {}", contentType);
        }
        logger.info("RSCL0107001 : {}", res.getStatusCode());

        return res.getBody();
    }

    @Override
    public UserResource exchangeByPost(String path, UserResource user) {
        URI targetUri = this.getUri(this.uri, path);
        RequestEntity<UserResource> req = RequestEntity.post(targetUri).body(user);

        ResponseEntity<UserResource> res = this.restTemplate.exchange(req, UserResource.class);
        logger.info("RSCL0108001 : {}", res.getStatusCode());

        return res.getBody();
    }

    @Override
    public UserResource exchangeJsonByPost(String path, UserResource user) {
        URI targetUri = this.getUri(this.uri, path);
        // JSONで受取るようヘッダの指定。
        RequestEntity<UserResource> req =
                RequestEntity.post(targetUri).contentType(MediaType.APPLICATION_JSON).body(user);

        ResponseEntity<UserResource> res = this.restTemplate.exchange(req, UserResource.class);
        logger.info("RSCL0109001 : {}", res.getStatusCode());

        return res.getBody();
    }

    @Override
    public void delete(String path) {
        URI targetUri = this.getUri(this.uri, path);
        this.restTemplate.delete(targetUri);

    }

    @Override
    public void exchangeByDelete(String path) {
        URI targetUri = this.getUri(this.uri, path);
        RequestEntity<Void> req = RequestEntity.delete(targetUri).build();

        ResponseEntity<Void> res = this.restTemplate.exchange(req, Void.class);
        logger.info("RSCL0111001 : {}", res.getStatusCode());
    }

    @Override
    public void put(String path, UserResource user) {
        URI targetUri = this.getUri(this.uri, path);
        this.restTemplate.put(targetUri, user);

    }

    @Override
    public void exchangeByPut(String path, UserResource user) {
        URI targetUri = this.getUri(this.uri, path);
        RequestEntity<UserResource> req = RequestEntity.put(targetUri).body(user);

        ResponseEntity<Void> res = this.restTemplate.exchange(req, Void.class);
        logger.info("RSCL0113001 : {}", res.getStatusCode());
    }

    @Override
    public List<String> exchangeWithByteArrayHttpMessageConverter(String message) {

        URI targetUri = this.getUri(this.uri, "str");

        // 送信するbyteデータを作成する。
        byte[] bufs = null;
        File sendFile = null;
        try {
            sendFile = File.createTempFile("send", ".txt");
            FileUtils.writeStringToFile(sendFile, message + "\r\n", StandardCharsets.UTF_8);
            try (FileReader fileReader = new FileReader(sendFile)) {
                bufs = IOUtils.toByteArray(fileReader, StandardCharsets.UTF_8);
            }
        } catch (IOException e1) {
            throw new SystemException("e.sf.rc.9001", "input/output error.", e1);
        } finally {
            if (sendFile != null) {
                // deleteメソッドによる削除の成功失敗によってその後のアクションをとることは無いため、SonarQube指摘は未対応としています。
                sendFile.delete();
            }
        }

        // byteデータを送信して、byteデータを受信する。
        RequestEntity<byte[]> requestEntity = RequestEntity.post(targetUri)
                .contentType(MediaType.APPLICATION_OCTET_STREAM).body(bufs);
        ResponseEntity<byte[]> res = this.restTemplate.exchange(requestEntity, byte[].class);
        byte[] rcvBufs = res.getBody();
        List<String> cntns = null;

        // 受信したbyteデータからファイルを作成して内容確認。
        File rcvFile = null;
        try {
            rcvFile = File.createTempFile("rcv", ".txt");
            FileUtils.writeByteArrayToFile(rcvFile, rcvBufs);

            cntns = FileUtils.readLines(rcvFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new SystemException("e.sf.rc.9001", "input/output error.", e);
        } finally {
            if (rcvFile != null) {
                // deleteメソッドによる削除の成功失敗によってその後のアクションをとることは無いため、SonarQube指摘は未対応としています。
                rcvFile.delete();
            }
        }

        logger.info("RSCL0201001 : {}", res.getStatusCode());

        return cntns;
    }

    @Override
    public String exchangeWithStringHttpMessageConverter(String message) {
        URI targetUri = this.getUri(this.uri, "str");

        // Stringデータを送信して、Stringデータを受信する。
        String sendStr = message;
        RequestEntity<String> requestEntity = RequestEntity.post(targetUri)
                .contentType(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).body(sendStr);

        ResponseEntity<String> res = this.restTemplate.exchange(requestEntity, String.class);

        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("RSCL0202001 : {}", contentType);
        }

        logger.info("RSCL0202001 : {}", res.getStatusCode());

        return res.getBody();
    }

    @Override
    public List<String> exchangeWithResourceHttpMessageConverter(String message) {

        URI targetUri = this.getUri(this.uri, "str");

        // 送信するResourceデータを作成する。
        Resource sendRsc = null;
        File sendFile = null;
        try {
            sendFile = File.createTempFile("send", ".txt");
            FileUtils.writeStringToFile(sendFile, message + "\r\n", StandardCharsets.UTF_8);
            sendRsc = new FileSystemResource(sendFile);
        } catch (IOException e1) {
            throw new SystemException("e.sf.rc.9001", "input/output error.", e1);
        }

        // Resourceデータを送信して、Resourceデータを受信する。
        RequestEntity<Resource> requestEntity = RequestEntity.post(targetUri)
                .contentType(MediaType.APPLICATION_OCTET_STREAM).body(sendRsc);
        ResponseEntity<Resource> res = this.restTemplate.exchange(requestEntity, Resource.class);

        Resource rcvRsc = res.getBody();
        // 送信が終わったら送信ファイルは削除。
        // deleteメソッドによる削除の成功失敗によってその後のアクションが変わることは無いため、SonarQube指摘は未対応としています。
        sendFile.delete();

        // 受信したResourceデータからファイルを作成して内容確認。
        File rcvFile = null;
        List<String> cntns = null;
        try {
            rcvFile = File.createTempFile("rcvFile", ".txt");
            if (rcvRsc != null) {
                FileUtils.writeByteArrayToFile(rcvFile,
                        IOUtils.toByteArray(rcvRsc.getInputStream()));
            }
            cntns = FileUtils.readLines(rcvFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new SystemException("e.sf.rc.9001", "input/output error.", e);
        } finally {
            if (rcvFile != null) {
                // deleteメソッドによる削除の成功失敗によってその後のアクションをとることは無いため、SonarQube指摘は未対応としています。
                rcvFile.delete();
            }
        }

        logger.info("RSCL0203001 : {}", res.getStatusCode());

        return cntns;
    }

    @Override
    public UserResource exchangeApplicationWithAllEncompassingFormHttpMessageConverter(
            UserResource user) {

        URI targetUri = this.getUri(this.uri, "map");
        UserResource resUser = new UserResource();

        MultiValueMap<String, String> sendMap = new LinkedMultiValueMap<String, String>();
        sendMap.add("name", user.getName());
        sendMap.add("age", String.valueOf(user.getAge()));

        // MultiValueMapを送信して、MultiValueMapを受信する。
        RequestEntity<MultiValueMap<String, String>> requestEntity =
                RequestEntity.post(targetUri).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .accept(MediaType.APPLICATION_FORM_URLENCODED).body(sendMap);
        @SuppressWarnings("rawtypes")
        ResponseEntity<MultiValueMap> res =
                this.restTemplate.exchange(requestEntity, MultiValueMap.class);

        @SuppressWarnings("unchecked")
        MultiValueMap<String, String> rcvMap = res.getBody();

        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("RSCL0205001 : {}", contentType);
        }
        logger.info("RSCL0205001 : {}", res.getStatusCode());

        if (rcvMap != null) {
            resUser.setName(rcvMap.getFirst("name"));
            resUser.setAge(Integer.parseInt(rcvMap.getFirst("age")));
        }

        return resUser;
    }

    @Override
    public UserResource exchangeMultipartWithAllEncompassingFormHttpMessageConverter(
            UserResource user) {

        URI targetUri = this.getUri(this.uri, "multipart");
        UserResource resUser = new UserResource();

        MultiValueMap<String, Object> sendMap = new LinkedMultiValueMap<>();
        sendMap.add("name", user.getName());
        sendMap.add("age", user.getAge());

        // MultiValueMapを送信して、MultiValueMapを受信する。
        RequestEntity<MultiValueMap<String, Object>> requestEntity =
                RequestEntity.post(targetUri).contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.MULTIPART_FORM_DATA).body(sendMap);
        @SuppressWarnings("rawtypes")
        ResponseEntity<MultiValueMap> res =
                this.restTemplate.exchange(requestEntity, MultiValueMap.class);

        @SuppressWarnings("unchecked")
        MultiValueMap<String, Object> rcvMap = res.getBody();

        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("RSCL0206001 : {}", contentType);
        }
        logger.info("RSCL0206001 : {}", res.getStatusCode());

        if (rcvMap != null) {
            resUser.setName((String) rcvMap.getFirst("name"));
            resUser.setAge((Integer) rcvMap.getFirst("age"));
        }

        return resUser;
    }

    @Override
    public UserResource exchangeWithMappingJackson2HttpMessageConverter(UserResource user) {

        URI targetUri = this.getUri(this.uri, "");
        RequestEntity<UserResource> requestEntity =
                RequestEntity.post(targetUri).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).body(user);
        ResponseEntity<UserResource> res =
                this.restTemplate.exchange(requestEntity, UserResource.class);
        UserResource resUser = res.getBody();

        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("RSCL0209001 : {}", contentType);
        }
        logger.info("RSCL0209001 : {}", res.getStatusCode());

        return resUser;
    }

    @Override
    public UserResource exchangeWithJaxb2RootElementHttpMessageConverter(UserResource user) {

        URI targetUri = this.getUri(this.uri, "");
        RequestEntity<UserResource> requestEntity =
                RequestEntity.post(targetUri).contentType(MediaType.APPLICATION_XML)
                        .accept(MediaType.APPLICATION_XML).body(user);
        ResponseEntity<UserResource> res =
                this.restTemplate.exchange(requestEntity, UserResource.class);
        UserResource resUser = res.getBody();

        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("RSCL0211001 : {}", contentType);
        }
        logger.info("RSCL0211001 : {}", res.getStatusCode());

        return resUser;
    }

    @Override
    public UserResource exchangeWithAuthentication(String username, String password) {
        URI targetUri = this.getUri(this.uri, "basic");

        // Basic認証用資格情報ヘッダ作成
        String plainCredentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder()
                .encodeToString(plainCredentials.getBytes(StandardCharsets.UTF_8));

        RequestEntity<Void> req = RequestEntity.get(targetUri)
                .header("Authorization", "Basic " + base64Credentials).build();
        ResponseEntity<UserResource> res = this.restTemplate.exchange(req, UserResource.class);
        logger.info("RSCL0303001 : {}", res.getStatusCode());

        return res.getBody();
    }

    @Override
    public void handleException01() {

        URI targetUri = this.getUri(this.uri, "exception/1");
        try {
            this.restTemplate.getForObject(targetUri, UserResource.class);

        } catch (HttpClientErrorException e) {
            throw e;
        }

    }

    @Override
    public void handleException02() {

        URI targetUri = this.getUri(this.uri, "exception/2");
        try {
            this.restTemplate.getForObject(targetUri, UserResource.class);

        } catch (HttpServerErrorException e) {
            throw e;
        }

    }

    @Override
    public void handleException03() {

        URI targetUri = this.getUri(this.uri, "returnHttpStatus901");
        try {
            this.restTemplate.getForObject(targetUri, UserResource.class);

        } catch (UnknownHttpStatusCodeException e) {
            throw e;
        }

    }

    @Override
    public UserResource handleException04() {

        URI targetUri = this.getUri(this.uri, "retry");
        ResponseEntity<UserResource> res = null;
        UserResource resUser = new UserResource();

        for (int retryCount = 0; retryCount < retryMaxCount; retryCount++) {
            try {
                RequestEntity<Void> req = RequestEntity.get(targetUri)
                        .header("x-Retry", String.valueOf(retryCount)).build();
                res = restTemplate.exchange(req, UserResource.class);

                logger.info("RSCL0405001 : HttpStatus = {}, RetryCount = {}", res.getStatusCode(),
                        retryCount);
                logger.info("Success({})", res.getStatusCode());

                break;

            } catch (HttpServerErrorException e) {

                logger.error(e.getMessage(), e);;
                logger.warn(
                        "An error ({}) occurred on the server. (The number of retries：{} Times)",
                        e.getStatusCode(), retryCount);

                try {
                    Thread.sleep((long) (retryWaitTimeCoefficient * retryCount));
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (res != null) {
            resUser = res.getBody();
        }

        return resUser;
    }

    @Override
    public UserResource upload() {

        URI targetUri = this.getUri(this.uri, "upload");

        MultiValueMap<String, Object> multiPartBody = new LinkedMultiValueMap<>();
        multiPartBody.add("file", new ClassPathResource(sendFilePath));

        RequestEntity<MultiValueMap<String, Object>> requestEntity = RequestEntity.post(targetUri)
                .contentType(MediaType.MULTIPART_FORM_DATA).body(multiPartBody);

        ResponseEntity<UserResource> res =
                this.restTemplate.exchange(requestEntity, UserResource.class);

        logger.info("RSCL0901001 : {}", res.getStatusCode());

        return res.getBody();
    }

    /**
     * <ul>
     * <li>URIを取得する</li>
     * </ul>
     * @param args URIのパラメータ
     * @return URI
     */
    private URI getUri(String uri, Object... args) {
        String uriStr = UriComponentsBuilder.fromUriString(uri).buildAndExpand(args).toUriString();
        // argsが空の場合"/"終わりのURIが作成されてしまうため、最後が"/"の場合は削除してからURIを作成する
        return URI.create(StringUtils.removeEnd(uriStr, "/"));
    }

}
