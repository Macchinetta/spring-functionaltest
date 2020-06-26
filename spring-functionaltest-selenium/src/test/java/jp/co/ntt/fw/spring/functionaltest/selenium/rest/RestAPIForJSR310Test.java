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
package jp.co.ntt.fw.spring.functionaltest.selenium.rest;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assume.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;

import jp.co.ntt.fw.spring.functionaltest.selenium.RestTestSupport;

public class RestAPIForJSR310Test extends RestTestSupport {

    /**
     * setUp
     * <ul>
     * <li>Remove all old member resources if any, and insert three new member resources every time</li>
     * </ul>
     */
    @Before
    public void setUp() throws Exception {
        // Get all existing member resources
        List<String> memberIds = given().when().get("/members").then().extract()
                .jsonPath().getList("memberId");
        // Delete all existing members
        for (String memberId : memberIds) {
            given().when().delete("/members/{memberId}", memberId);
        }

        // Insert three members as an initial data
        for (int i = 1; i < 4; i++) {
            // Assigning field values
            String firstName = "FirstName" + i;
            String lastName = "LastName" + i;
            String genderCode = Integer.toString((i));
            if (i % 2 == 0)
                genderCode = "2";
            else
                genderCode = "1";
            String dateOfBirth = "2015-01-0" + i;
            String emailAddress = "FirstName" + i + "@test.co.jp";
            String telephoneNumber = "11111" + i;
            String zipCode = i + "00000" + i;
            String address = "Address" + i;
            String password = "password" + i;
            // Creating credential Hash map for jsonBody
            Map<String, Object> credential = new HashMap<String, Object>();
            credential.put("password", password);

            // Creating Hash map for jsonBody
            Map<String, Object> jsonBody = new HashMap<String, Object>();
            jsonBody.put("firstName", firstName);
            jsonBody.put("lastName", lastName);
            jsonBody.put("dateOfBirth", dateOfBirth);
            jsonBody.put("emailAddress", emailAddress);
            jsonBody.put("telephoneNumber", telephoneNumber);
            jsonBody.put("zipCode", zipCode);
            jsonBody.put("address", address);
            jsonBody.put("credential", credential);

            // Assigning dynamic value
            jsonBody.put("genderCode", genderCode);

            // posting request
            given().body(jsonBody).contentType(ContentType.JSON).when().post(
                    "/members");
        }
    }

    /**
     * testREST0901001
     * <ul>
     * <li>JSR310 Date and Time API を使用する処理</li>
     * <li>JSR310 Date and Time API を使用した処理を行うことができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testREST0901001() throws Exception {
        // RESTサーバのJavaの実行環境のバージョンを取得する
        int version = getJavaVersion();

        // RESTサーバのJavaの実行環境のバージョンが1.8以上であればテストを実行する
        assumeThat(8 <= version, is(true));

        // Request Body 作成
        LocalDate date = LocalDate.of(2016, 7, 14);
        LocalTime time = LocalTime.of(13, 43, 35, 200);

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("date", date.toString());
        jsonBody.put("time", time.toString());

        // Request Body 期待値作成
        java.time.LocalDateTime dateTime = java.time.LocalDateTime.of(date,
                time);
        String zoneDtStr = ZonedDateTime.of(dateTime, ZoneId.of(
                "America/Los_Angeles")).format(
                        DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        given().body(jsonBody).contentType(ContentType.JSON).filters(
                new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor)).when().post(
                        "/datetime/getAmericaDateTime").then().statusCode(200)
                .header("content-Type", containsString(MediaType.APPLICATION_JSON_VALUE))
                .body("dateTime", is(zoneDtStr));

    }
}
