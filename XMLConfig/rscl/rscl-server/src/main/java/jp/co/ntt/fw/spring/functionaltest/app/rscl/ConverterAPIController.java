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
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@RestController
@RequestMapping(value = "/api/v1/converter")
public class ConverterAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConverterAPIController.class);

    /**
     * <ul>
     * <li>Stringの送受信を行う。</li> <br>
     * ByteArrayHttpMessageConverter確認用 <br>
     * StringHttpMessageConverter確認用 <br>
     * ResourceHttpMessageConverter確認用
     * </ul>
     * 
     * @return
     */
    @PostMapping(value = "str")
    public String str(@RequestHeader(name = "Content-Type", required = false) String contentType,
            @RequestBody String str) {
        LOGGER.info("Call API:str");
        LOGGER.info("Content-Type : {}", contentType);
        return str + "_received";
    }

    /**
     * <ul>
     * <li>MultiValueMap&lt;String, String&gt;の送受信を行う。</li> <br>
     * メディアタイプがapplication/x-www-form-urlencodedの場合のAllEncompassingFormHttpMessageConverter確認用。
     * </ul>
     * 
     * @param rcvMap
     * @return
     */
    @PostMapping(value = "map", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public MultiValueMap<String, String> map(@RequestHeader(name = "Content-Type", required = false) String contentType,
            @RequestBody MultiValueMap<String, String> rcvMap) {
        LOGGER.info("Call API:map");
        LOGGER.info("Content-Type : {}", contentType);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

        map.add("name", rcvMap.getFirst("name") + "_received");
        map.add("age", "20");

        return map;
    }

    /**
     * <ul>
     * <li>MultiValueMap&lt;String, Object&gt;の送受信を行う。</li> <br>
     * メディアタイプがmultipart/form-dataの場合AllEncompassingFormHttpMessageConverter確認用。
     * HTTPボディからMultivalueMapへ変換ができないためエラーが発生する。
     * </ul>
     * 
     * @param rcvMap
     * @return
     */
    @PostMapping(value = "multipart", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MultiValueMap<String, Object> mapMulti(
            @RequestHeader(name = "Content-Type", required = false) String contentType,
            @RequestBody MultiValueMap<String, Object> rcvMap) {

        LOGGER.info("Call API:multipart");
        LOGGER.info("Content-Type : {}", contentType);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

        map.add("name", rcvMap.getFirst("name") + "_received");
        map.add("age", "20");

        return map;
    }

    /**
     * <ul>
     * <li>CSV形式の文字列データを返す。</li> <br>
     * カスタムHttpMessageConverter確認用。
     * </ul>
     * 
     * @return CSV形式の文字列データ
     */
    @GetMapping(value = "csv", produces = "text/csv")
    public String getCsvData(@RequestHeader("Accept") String accept) {
        LOGGER.info("Call API:csv");
        LOGGER.info("Accept: {}", accept);
        StringBuilder csvData = new StringBuilder();
        csvData.append("name,age\n");
        csvData.append("testUser1,30\n");
        csvData.append("testUser2,31\n");
        csvData.append("testUser3,32\n");
        csvData.append("testUser4,33\n");
        csvData.append("testUser5,34\n");
        return csvData.toString();
    }

    /**
     * <ul>
     * <li>Userを受け取り、nameに文字を追加してUserを返却する。</li> <br>
     * POSTメソッド確認用。
     * </ul>
     * 
     * @return
     */
    @PostMapping(value = "json")
    public User postJson(
            @RequestHeader(name = "Content-Type", required = false) String contentType,
            @RequestHeader(name = "Accept", required = false) String accept,
            @RequestBody User user) {
        LOGGER.info("Call API:postJson");
        LOGGER.info("Content-Type: {}", contentType);
        LOGGER.info("Accept: {}", accept);

        user.setName(user.getName() + "_created");

        return user;
    }

}
