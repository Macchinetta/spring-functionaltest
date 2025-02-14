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

import java.net.URI;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.terasoluna.gfw.common.exception.SystemException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

@Service
public class SourceRestClientServiceImpl implements SourceRestClientService {

    private static final Logger logger = LoggerFactory.getLogger(RestClientServiceImpl.class);

    @Inject
    RestTemplate restTemplate;

    @Inject
    RestTemplate sourceRestTemplate;

    @Value("${rscl.applicationContextUrl}/api/v1/rscl/{opt}")
    String uri;

    @Override
    public UserResource exchangeWithSourceHttpMessageConverter(UserResource user) {

        URI targetUri = this.getUri(this.uri, "");
        UserResource resUser = new UserResource();

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
        RequestEntity<Source> requestEntity =
                RequestEntity.post(targetUri).contentType(MediaType.APPLICATION_XML)
                        .accept(MediaType.APPLICATION_XML).body(sendSrc);
        ResponseEntity<DOMSource> res =
                this.sourceRestTemplate.exchange(requestEntity, DOMSource.class);

        DOMSource rcvSrc = res.getBody();
        for (String contentType : res.getHeaders().get("Content-Type")) {
            logger.info("RSCL0204001 : {}", contentType);
        }
        logger.info("RSCL0204001 : {}", res.getStatusCode());

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
