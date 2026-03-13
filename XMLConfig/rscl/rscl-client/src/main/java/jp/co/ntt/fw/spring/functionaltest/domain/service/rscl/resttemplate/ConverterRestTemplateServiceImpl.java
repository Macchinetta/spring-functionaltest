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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.terasoluna.gfw.common.exception.SystemException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@Service
public class ConverterRestTemplateServiceImpl implements ConverterRestTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(ConverterRestTemplateServiceImpl.class);

    @Value("${rscl.applicationContextUrl}/api/v1")
    private String uri;

    @Inject
    private RestTemplate restTemplate;

    @Inject
    private RestTemplate sourceRestTemplate;

    @Override
    public List<String> exchangeWithByteArrayHttpMessageConverter(String message) {

        URI targetUri = URI.create(uri + "/converter/str");

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

        logger.info("HTTP Status : {}", res.getStatusCode());

        return cntns;
    }

    @Override
    public String exchangeWithStringHttpMessageConverter(String message) {

        URI targetUri = URI.create(uri + "/converter/str");

        // Stringデータを送信して、Stringデータを受信する。
        String sendStr = message;
        RequestEntity<String> requestEntity = RequestEntity.post(targetUri)
                .contentType(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).body(sendStr);

        ResponseEntity<String> res = this.restTemplate.exchange(requestEntity, String.class);

        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("Content-Type : {}", contentType);
        }

        logger.info("HTTP Status : {}", res.getStatusCode());

        return res.getBody();
    }

    @Override
    public List<String> exchangeWithResourceHttpMessageConverter(String message) {

        URI targetUri = URI.create(uri + "/converter/str");

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

        logger.info("HTTP Status : {}", res.getStatusCode());

        return cntns;
    }

    @Override
    public User exchangeWithSourceHttpMessageConverter(User user) {

        URI targetUri = URI.create(uri + "/users");
        User resUser = new User();

        // 送信するXMLソース作成
        Source sendSrc = null;
        try {
            DOMImplementation domImpl = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .getDOMImplementation();
            Document document = domImpl.createDocument("", "user", null);
            Element userElm = document.getDocumentElement();
            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(user.getName()));
            Element age = document.createElement("age");
            age.appendChild(document.createTextNode(String.valueOf(user.getAge())));

            userElm.appendChild(name);
            userElm.appendChild(age);

            sendSrc = new DOMSource(document);
        } catch (ParserConfigurationException e1) {

            throw new SystemException("e.sf.rc.9002", "parse configuration error.", e1);
        }

        // Sourceデータを送信して、Sourceデータを受信する。
        RequestEntity<Source> requestEntity = RequestEntity.post(targetUri).contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML).body(sendSrc);
        ResponseEntity<DOMSource> res = this.sourceRestTemplate.exchange(requestEntity, DOMSource.class);

        DOMSource rcvSrc = res.getBody();
        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("Content-Type : {}", contentType);
        }
        logger.info("HTTP Status : {}", res.getStatusCode());

        if (rcvSrc != null) {
            // 受信したXMlソースを解析
            Document doc = (Document) rcvSrc.getNode();

            resUser.setName(
                    doc.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
            resUser.setAge(Integer.parseInt(
                    doc.getElementsByTagName("age").item(0).getFirstChild().getNodeValue()));
        }

        return resUser;
    }

    @Override
    public User exchangeApplicationWithAllEncompassingFormHttpMessageConverter(User user) {

        URI targetUri = URI.create(uri + "/converter/map");
        User resUser = new User();

        MultiValueMap<String, String> sendMap = new LinkedMultiValueMap<String, String>();
        sendMap.add("name", user.getName());
        sendMap.add("age", String.valueOf(user.getAge()));

        // MultiValueMapを送信して、MultiValueMapを受信する。
        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity.post(targetUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED).body(sendMap);
        @SuppressWarnings("rawtypes")
        ResponseEntity<MultiValueMap> res = this.restTemplate.exchange(requestEntity, MultiValueMap.class);

        @SuppressWarnings("unchecked")
        MultiValueMap<String, String> rcvMap = res.getBody();

        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("Content-Type : {}", contentType);
        }
        logger.info("HTTP Status : {}", res.getStatusCode());

        if (rcvMap != null) {
            resUser.setName(rcvMap.getFirst("name"));
            resUser.setAge(Integer.parseInt(rcvMap.getFirst("age")));
        }

        return resUser;
    }

    @Override
    public User exchangeMultipartWithAllEncompassingFormHttpMessageConverter(User user) {

        URI targetUri = URI.create(uri + "/converter/multipart");
        User resUser = new User();

        MultiValueMap<String, Object> sendMap = new LinkedMultiValueMap<>();
        sendMap.add("name", user.getName());
        sendMap.add("age", user.getAge());

        // MultiValueMapを送信して、MultiValueMapを受信する。
        RequestEntity<MultiValueMap<String, Object>> requestEntity = RequestEntity.post(targetUri)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.MULTIPART_FORM_DATA).body(sendMap);
        @SuppressWarnings("rawtypes")
        ResponseEntity<MultiValueMap> res = this.restTemplate.exchange(requestEntity, MultiValueMap.class);

        @SuppressWarnings("unchecked")
        MultiValueMap<String, Object> rcvMap = res.getBody();

        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("Content-Type : {}", contentType);
        }
        logger.info("HTTP Status : {}", res.getStatusCode());

        if (rcvMap != null) {
            resUser.setName((String) rcvMap.getFirst("name"));
            resUser.setAge((Integer) rcvMap.getFirst("age"));
        }

        return resUser;
    }

    @Override
    public User exchangeWithMappingJackson2HttpMessageConverter(User user) {

        URI targetUri = URI.create(uri + "/users");
        RequestEntity<User> requestEntity = RequestEntity.post(targetUri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).body(user);
        ResponseEntity<User> res = this.restTemplate.exchange(requestEntity, User.class);
        User resUser = res.getBody();

        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("Content-Type : {}", contentType);
        }
        logger.info("HTTP Status : {}", res.getStatusCode());

        return resUser;
    }

    @Override
    public User exchangeWithJaxb2RootElementHttpMessageConverter(User user) {

        URI targetUri = URI.create(uri + "/users");
        RequestEntity<User> requestEntity = RequestEntity.post(targetUri).contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML).body(user);
        ResponseEntity<User> res = this.restTemplate.exchange(requestEntity, User.class);
        User resUser = res.getBody();

        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("Content-Type : {}", contentType);
        }
        logger.info("HTTP Status : {}", res.getStatusCode());

        return resUser;
    }
}