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

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@Service
public class ErrorHandleRestTemplateServiceImpl implements ErrorHandleRestTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandleRestTemplateServiceImpl.class);

    @Value("${rscl.applicationContextUrl}/api/v1")
    private String uri;

    @Value("${rscl.retry.maxCount}")
    private int retryMaxCount;

    @Value("${rscl.retry.retryWaitTimeCoefficient}")
    private int retryWaitTimeCoefficient;

    @Inject
    private RestTemplate restTemplate;

    @Inject
    private RestTemplate statusRestTemplate;

    @Inject
    private RestTemplate statusNoOpRestTemplate;

    @Inject
    private RestTemplate exceptionRetryRestTemplate;

    @Inject
    private RestTemplate statusRetryRestTemplate;

    @Override
    public User exception(int statusCode) {
        try {
            User user = restTemplate.getForObject(uri + "/exception/{status}", User.class, statusCode);
            logger.info("Success:name:{}, age:{}", user.getName(), user.getAge());
            return user;
        } catch (HttpClientErrorException e) {
            logger.error("Client Error:httpStatus:{}, responseHeader:{}, responseBody:{}", e.getStatusCode().value(),
                    e.getResponseHeaders(), e.getResponseBodyAsString(), e);
            throw e;
        } catch (HttpServerErrorException e) {
            logger.error("Server Error:httpStatus:{}, responseHeader:{}, responseBody:{}", e.getStatusCode().value(),
                    e.getResponseHeaders(), e.getResponseBodyAsString(), e);
            throw e;
        } catch (UnknownHttpStatusCodeException e) {
            // ここに入ることは実質ない。
            logger.error("Unknown Error:httpStatus:{}, responseHeader:{}, responseBody:{}", e.getStatusCode().value(),
                    e.getResponseHeaders(), e.getResponseBodyAsString(), e);
            throw e;
        }
    }

    @Override
    public User status(int statusCode) {

        ResponseEntity<User> response = statusRestTemplate.getForEntity(uri + "/exception/{status}", User.class,
                statusCode);

        if (response.getStatusCode().is2xxSuccessful()) {
            User user = response.getBody();
            logger.info("Success:name:{}, age:{}, httpStatus:{}", user.getName(), user.getAge(),
                    response.getStatusCode().value());
            return user;
        } else if (response.getStatusCode().is4xxClientError()) {
            logger.error("Client Error:httpStatus:{}", response.getStatusCode().value());
            return null;
        } else if (response.getStatusCode().is5xxServerError()) {
            logger.error("Server Error:httpStatus:{}", response.getStatusCode().value());
            return null;
        } else if (response.getStatusCode().value() == 901) {
            User user = response.getBody();
            logger.info("Unknown:name:{}, age:{}, httpStatus:{}", user.getName(), user.getAge(),
                    response.getStatusCode().value());
            return user;
        }
        throw new IllegalStateException("Unexpected Status Code");
    }

    @Override
    public User statusNoOpResponse(int statusCode) {

        ResponseEntity<User> response = statusNoOpRestTemplate.getForEntity(uri + "/exception/{status}", User.class,
                statusCode);

        if (response.getStatusCode().is2xxSuccessful()) {
            User user = response.getBody();
            logger.info("NoOp Success:name:{}, age:{}, httpStatus:{}", user.getName(), user.getAge(),
                    response.getStatusCode().value());
            return user;
        } else if (response.getStatusCode().is4xxClientError()) {
            logger.error("NoOp Client Error:httpStatus:{}", response.getStatusCode().value());
            return null;
        } else if (response.getStatusCode().is5xxServerError()) {
            logger.error("NoOp Server Error:httpStatus:{}", response.getStatusCode().value());
            return null;
        } else if (response.getStatusCode().value() == 901) {
            User user = response.getBody();
            logger.info("NoOp Unknown:name:{}, age:{}, httpStatus:{}", user.getName(), user.getAge(),
                    response.getStatusCode().value());
            return user;
        }
        throw new IllegalStateException("Unexpected Status Code");
    }

    @Override
    public User exceptionRetry(int throwSuccessRecord) {
        int retryCount = 0;
        ResponseEntity<User> responseEntity = null;
        User user = null;

        URI targetUri = UriComponentsBuilder.fromUriString(uri + "/exception/retry/{throwSuccessRecord}")
                .buildAndExpand(throwSuccessRecord).toUri();

        while (true) {
            try {

                RequestEntity<Void> requestEntity = RequestEntity.get(targetUri)
                        .header("x-Retry", String.valueOf(retryCount)).build();
                responseEntity = exceptionRetryRestTemplate.exchange(requestEntity, User.class);
                user = responseEntity.getBody();
                logger.info("Success: name:{}, age:{}, retryCount = {}", user.getName(), user.getAge(), retryCount);
                break;

            } catch (HttpServerErrorException e) {

                if (retryCount == retryMaxCount) {
                    logger.error("Max retry count reached", e);
                    throw e;
                }

                retryCount++;

                if (logger.isWarnEnabled()) {
                    logger.warn("An error ({}) occurred on the server. (The number of retries:{} Times)",
                            e.getStatusCode(),
                            retryCount);
                }

                try {
                    Thread.sleep(retryWaitTimeCoefficient * retryCount);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return user;
    }

    @Override
    public User statusRetry(int throwSuccessRecord) {
        int retryCount = 0;
        ResponseEntity<User> responseEntity = null;
        User user = null;

        URI targetUri = UriComponentsBuilder.fromUriString(uri + "/exception/retry/{throwSuccessRecord}")
                .buildAndExpand(throwSuccessRecord).toUri();

        while (true) {

            RequestEntity<Void> requestEntity = RequestEntity.get(targetUri)
                    .header("x-Retry", String.valueOf(retryCount)).build();
            responseEntity = statusRetryRestTemplate.exchange(requestEntity, User.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                user = responseEntity.getBody();
                logger.info("Success: name:{}, age:{}, retryCount = {}", user.getName(), user.getAge(), retryCount);
                break;

            } else if (responseEntity.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {

                if (retryCount == retryMaxCount) {
                    logger.error("Max retry count reached");
                    break;
                }

                retryCount++;

                if (logger.isWarnEnabled()) {
                    logger.warn("An error ({}) occurred on the server. (The number of retries:{} Times)",
                            responseEntity.getStatusCode(), retryCount);
                }

                try {
                    Thread.sleep(retryWaitTimeCoefficient * retryCount);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return user;
    }

}