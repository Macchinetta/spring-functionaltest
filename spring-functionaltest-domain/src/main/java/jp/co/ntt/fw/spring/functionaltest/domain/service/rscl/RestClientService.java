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
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

public interface RestClientService {

    UserResource getForObject(String path);

    UserResource getForEntity(String path);

    UserResource exchange(String path);

    UserResource exchangeJson(String path);

    UserResource postForObject(String path, UserResource user);

    UserResource postForEntity(String path, UserResource user);

    UserResource exchangeByPost(String path, UserResource user);

    UserResource exchangeJsonByPost(String path, UserResource user);

    void delete(String path);

    void exchangeByDelete(String path);

    void put(String path, UserResource user);

    void exchangeByPut(String path, UserResource user);

    List<String> exchangeWithByteArrayHttpMessageConverter(String message);

    String exchangeWithStringHttpMessageConverter(String message);

    List<String> exchangeWithResourceHttpMessageConverter(String message);

    UserResource exchangeWithAllEncompassingFormHttpMessageConverter(
            UserResource user);

    UserResource exchangeWithMappingJackson2HttpMessageConverter(
            UserResource user);

    UserResource exchangeWithJaxb2RootElementHttpMessageConverter(
            UserResource user);

    UserResource exchangeWithAuthentication(String username, String password);

    void handleException01();

    void handleException02();

    void handleException03();

    UserResource handleException04();

    UserResource upload();

}
