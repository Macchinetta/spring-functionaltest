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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

@Service
public class HttpsRestClientServiceImpl implements HttpsRestClientService {

    @Inject
    RestTemplate httpsRestTemplate;

    // SoTimeoutを発生させるためのRestTemplate
    @Inject
    RestTemplate abnormalHttpsRestTemplate;

    @Value("${rscl.httpsserver1.uri}")
    String httpsServer1Uri;

    @Value("${rscl.httpsserver2.uri}")
    String httpsServer2Uri;

    @Value("${rscl.httpsserver3.uri}")
    String httpsServer3Uri;

    @Value("${rscl.httpsserver4.uri}")
    String httpsServer4Uri;

    @Value("${rscl.notExistHttpsserver.uri}")
    String notExistHttpsServerUri;

    @Value("${rscl.destination1}")
    String destination1;

    @Value("${rscl.destination2}")
    String destination2;

    @Value("${rscl.destination3}")
    String destination3;

    @Value("${rscl.destination4}")
    String destination4;

    @Override
    public UserResource connectHttps(@NotNull SERVERS servers, @NotNull DESTINATION destination,
            boolean isCausesSoTimeout) {

        // @formatter:off
        String serverUri = switch (servers) {
            case SERVER1 -> this.httpsServer1Uri; // port : 8991
            case SERVER2 -> this.httpsServer2Uri; // port : 8992
            case SERVER3 -> this.httpsServer3Uri; // port : 8993
            case SERVER4 -> this.httpsServer4Uri; // port : 8994
            case NOTEXISTS -> this.notExistHttpsServerUri; // 存在しないサーバ
            default -> throw new IllegalStateException();
        };
        // @formatter:on

        // @formatter:off
        String target = switch (destination) {
            case RSCL1 -> this.destination1; // 0秒待機
            case RSCL2 -> this.destination2; // 6秒待機
            case RSCL3 -> this.destination3; // 6秒待機
            case RSCL4 -> this.destination4; // 20秒待機
            default -> throw new IllegalStateException();
        };
        // @formatter:on

        RestTemplate restTemplate =
                isCausesSoTimeout ? abnormalHttpsRestTemplate : httpsRestTemplate;

        URI uri = URI.create(serverUri + target);
        RequestEntity<Void> req = RequestEntity.get(uri).build();

        ResponseEntity<UserResource> res = restTemplate.exchange(req, UserResource.class);

        UserResource user = res.getBody();

        return user;
    }

}
