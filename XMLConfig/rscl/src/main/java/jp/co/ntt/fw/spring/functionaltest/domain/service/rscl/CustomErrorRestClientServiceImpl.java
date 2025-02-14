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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

@Service
public class CustomErrorRestClientServiceImpl implements CustomErrorRestClientService {

    private static final Logger logger =
            LoggerFactory.getLogger(CustomErrorRestClientServiceImpl.class);

    @Inject
    RestTemplate customErrorRestTemplate;

    @Value("${rscl.applicationContextUrl}/api/v1/rscl/{opt}")
    String uri;

    @Value("${rscl.retry.maxCount}")
    int retryMaxCount;

    @Value("${rscl.retry.retryWaitTimeCoefficient}")
    int retryWaitTimeCoefficient;

    @Override
    public UserResource handleException05() {

        URI targetUri = this.getUri(this.uri, "retry");
        ResponseEntity<UserResource> res = null;
        UserResource resUser = new UserResource();

        for (int retryCount = 0; retryCount < retryMaxCount; retryCount++) {
            RequestEntity<Void> req = RequestEntity.get(targetUri)
                    .header("x-Retry", String.valueOf(retryCount)).build();
            res = this.customErrorRestTemplate.exchange(req, UserResource.class);

            if (HttpStatus.OK.equals(res.getStatusCode())) {
                logger.info("RSCL0406001 : HttpStatus = {}, RetryCount = {}", res.getStatusCode(),
                        retryCount);
                break;

            } else if (res.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {

                logger.warn(
                        "An error ({}) occurred on the server. (The number of retries：{} Times)",
                        res.getStatusCode(), retryCount);

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

    /**
     * <ul>
     * <li>URIを取得する</li>
     * </ul>
     * @param args URIのパラメータ
     * @return URI
     */
    private URI getUri(String uri, Object... args) {
        return UriComponentsBuilder.fromUriString(uri).buildAndExpand(args).toUri();
    }

}
