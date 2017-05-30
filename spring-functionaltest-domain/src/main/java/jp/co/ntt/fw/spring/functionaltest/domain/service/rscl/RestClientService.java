/*
 * Copyright(c) 2014-2017 NTT Corporation.
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

    UserResource exchangeWithSourceHttpMessageConverter(UserResource user);

    UserResource exchangeWithAllEncompassingFormHttpMessageConverter(
            UserResource user);

    UserResource exchangeWithMappingJackson2HttpMessageConverter(
            UserResource user);

    UserResource exchangeWithJaxb2RootElementHttpMessageConverter(
            UserResource user);

    UserResource exchangeWithAuthentication(String userid, String password);

    void handleException01();

    void handleException02();

    void handleException03();

    UserResource handleException04();

    UserResource upload();

}
