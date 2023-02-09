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

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assume.assumeTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import jp.co.ntt.fw.spring.functionaltest.selenium.ApServerName;
import jp.co.ntt.fw.spring.functionaltest.selenium.RestTestSupport;

public class RestAPITest extends RestTestSupport {

    /**
     * setUp
     * <ul>
     * <li>Remove all old member resources if any, and insert three new member resources every time</li>
     * </ul>
     */
    @Before
    public void setUp() throws Exception {
        // Get all existing member resources
        // @formatter:off
        List<String> memberIds = given()
                .when()
                .get("/members")
                .then()
                .extract()
                .jsonPath()
                .getList("memberId");
        // @formatter:on

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
            // @formatter:off
            given().body(jsonBody)
                   .contentType(ContentType.JSON)
                   .when()
                   .post("/members");
            // @formatter:on
        }
    }

    /**
     * testREST0201001
     * <ul>
     * <li>REST APIの実装</li>
     * <li>Get all the member resources</li>
     * </ul>
     */
    @Test
    public void testREST0201001() throws Exception {
        // Sending get request and confirming the body contents
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .get("/members")
               .then().statusCode(200)
               .contentType(containsString(MediaType.APPLICATION_JSON_VALUE))
               .body(
                     "[0].memberId", startsWith("M0"),
                     "[0].firstName", equalTo("FirstName1"),
                     "[0].lastName", equalTo("LastName1"),
                     "[0].genderCode", equalTo("1"),
                     "[0].dateOfBirth", equalTo("2015-01-01"),
                     "[0].emailAddress", equalTo("FirstName1@test.co.jp"),
                     "[0].telephoneNumber", equalTo("111111"),
                     "[0].zipCode", equalTo("1000001"),
                     "[0].address", equalTo("Address1"),
                     "[0].credential.signId", equalTo("firstname1@test.co.jp"),
                     "[1].memberId", startsWith("M0"),
                     "[1].firstName", equalTo("FirstName2"),
                     "[1].lastName", equalTo("LastName2"),
                     "[1].genderCode", equalTo("2"),
                     "[1].dateOfBirth", equalTo("2015-01-02"),
                     "[1].emailAddress", equalTo("FirstName2@test.co.jp"),
                     "[1].telephoneNumber", equalTo("111112"),
                     "[1].zipCode", equalTo("2000002"),
                     "[1].address", equalTo("Address2"),
                     "[1].credential.signId", equalTo("firstname2@test.co.jp"),
                     "[2].memberId", startsWith("M0"),
                     "[2].firstName", equalTo("FirstName3"),
                     "[2].lastName", equalTo("LastName3"),
                     "[2].genderCode", equalTo("1"),
                     "[2].dateOfBirth", equalTo("2015-01-03"),
                     "[2].emailAddress", equalTo("FirstName3@test.co.jp"),
                     "[2].telephoneNumber", equalTo("111113"),
                     "[2].zipCode", equalTo("3000003"),
                     "[2].address", equalTo("Address3"),
                     "[2].credential.signId", equalTo("firstname3@test.co.jp")
                    );
        // @formatter:on
    }

    /**
     * testREST0201002
     * <ul>
     * <li>REST APIの実装</li>
     * <li>Get all the member resources matching with Search criteria</li>
     * </ul>
     */
    @Test
    public void testREST0201002() throws Exception {
        // Sending get request with search criteria and confirming the body
        // contents
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .get("/members?name=FirstName&page=0&size=2")
               .then().statusCode(200)
               .contentType(containsString(MediaType.APPLICATION_JSON_VALUE))
               .body(
                     "content[0].memberId", startsWith("M0"),
                     "content[0].firstName", equalTo("FirstName1"),
                     "content[0].lastName", equalTo("LastName1"),
                     "content[0].genderCode", equalTo("1"),
                     "content[0].dateOfBirth", equalTo("2015-01-01"),
                     "content[0].emailAddress", equalTo("FirstName1@test.co.jp"),
                     "content[0].telephoneNumber", equalTo("111111"),
                     "content[0].zipCode", equalTo("1000001"),
                     "content[0].address", equalTo("Address1"),
                     "content[0].credential.signId", equalTo("firstname1@test.co.jp"),
                     "content[1].memberId", startsWith("M0"),
                     "content[1].firstName", equalTo("FirstName2"),
                     "content[1].lastName", equalTo("LastName2"),
                     "content[1].genderCode", equalTo("2"),
                     "content[1].dateOfBirth", equalTo("2015-01-02"),
                     "content[1].emailAddress", equalTo("FirstName2@test.co.jp"),
                     "content[1].telephoneNumber", equalTo("111112"),
                     "content[1].zipCode", equalTo("2000002"),
                     "content[1].address", equalTo("Address2"),
                     "content[1].credential.signId", equalTo("firstname2@test.co.jp"),
                     "totalElements", equalTo(3),
                     "totalPages", equalTo(2),
                     "size", equalTo(2)
                    );
        // @formatter:on
    }

    /**
     * testREST0202001
     * <ul>
     * <li>REST APIの実装</li>
     * <li>Add one member resource</li>
     * </ul>
     */
    @Test
    public void testREST0202001() throws Exception {

        // Assigning field values
        String firstName = "FirstName";
        String lastName = "LastName";
        String genderCode = "1";
        String dateOfBirth = "2015-02-02";
        String emailAddress = "FirstName@test.co.jp";
        String telephoneNumber = "123456789";
        String zipCode = "1116666";
        String address = "Address";
        String password = "password";

        // Creating credential Hash map for jsonBody
        Map<String, Object> credential = new HashMap<String, Object>();
        credential.put("password", password);

        // Creating Hash map for jsonBody
        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("firstName", firstName);
        jsonBody.put("lastName", lastName);
        jsonBody.put("genderCode", genderCode);
        jsonBody.put("dateOfBirth", dateOfBirth);
        jsonBody.put("emailAddress", emailAddress);
        jsonBody.put("telephoneNumber", telephoneNumber);
        jsonBody.put("zipCode", zipCode);
        jsonBody.put("address", address);
        jsonBody.put("credential", credential);

        // Executing post request to create member
        // @formatter:off
        given().body(jsonBody)
               .contentType(ContentType.JSON)
               .filters(new RequestLoggingFilter(captor),
                       new ResponseLoggingFilter(captor))
               .when()
               .post("/members")
               .then().statusCode(201)
               .contentType(containsString(MediaType.APPLICATION_JSON_VALUE))
               .body(
                     "memberId", startsWith("M0"),
                     "firstName", equalTo(firstName),
                     "lastName", equalTo(lastName),
                     "genderCode", equalTo(genderCode),
                     "dateOfBirth", equalTo(dateOfBirth),
                     "emailAddress", equalTo(emailAddress),
                     "telephoneNumber", equalTo(telephoneNumber),
                     "zipCode", equalTo(zipCode),
                     "address", equalTo(address),
                     "credential.signId", equalTo("firstname@test.co.jp"),
                     "credential.passwordLastChangedAt", equalTo("2013-12-09T04:50:12.100Z"),
                     "credential.lastModifiedAt", equalTo("2013-12-09T04:50:12.100Z"),
                     "createdAt", equalTo("2013-12-09T04:50:12.100Z"),
                     "lastModifiedAt", equalTo("2013-12-09T04:50:12.100Z")
                    );
        // @formatter:on
    }

    /**
     * testREST0203001
     * <ul>
     * <li>REST APIの実装</li>
     * <li>Get specified member resource</li>
     * </ul>
     */
    @Test
    public void testREST0203001() throws Exception {
        // Get all existing member resources for memberId
        // @formatter:off
        List<String> memberIds = given()
                .when()
                .get("/members")
                .then()
                .extract()
                .jsonPath()
                .getList("memberId");
        // @formatter:on

        // Get request for retrieving specified member resource &
        // Check the retrieved contents
        int i = 1;
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .get("/members/{memberId}", memberIds.get(0))
               .then().statusCode(200)
               .body(
                     "memberId", equalTo(memberIds.get(0)),
                     "firstName", equalTo("FirstName" + i),
                     "lastName", equalTo("LastName" + i),
                     "genderCode", equalTo("1"),
                     "dateOfBirth", equalTo("2015-01-0" + i),
                     "emailAddress", equalTo("FirstName" + i + "@test.co.jp"),
                     "telephoneNumber", equalTo("11111" + i),
                     "zipCode", equalTo(i + "00000" + i),
                     "address", equalTo("Address" + i),
                     "credential.signId", equalTo("firstname" + i + "@test.co.jp")
                    );
        // @formatter:on
    }

    /**
     * testREST0204001
     * <ul>
     * <li>REST APIの実装</li>
     * <li>Update specified member resource</li>
     * </ul>
     */
    @Test
    public void testREST0204001() throws Exception {
        // Get all existing member resources for memberId
        // @formatter:off
        List<String> memberIds = given()
                .when()
                .get("/members")
                .then()
                .extract()
                .jsonPath()
                .getList("memberId");
        // @formatter:on

        int i = 21;
        // Assigning field values
        String firstName = "FirstName" + i;
        String lastName = "LastName" + i;
        String genderCode = "1";
        String dateOfBirth = "2015-01-" + i;
        String emailAddress = "FirstName" + i + "@test.co.jp";
        String telephoneNumber = "11111" + i;
        String zipCode = i + "00000" + i;
        String address = "Address" + i;

        // Creating Hash map for jsonBody
        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("firstName", firstName);
        jsonBody.put("lastName", lastName);
        jsonBody.put("dateOfBirth", dateOfBirth);
        jsonBody.put("emailAddress", emailAddress);
        jsonBody.put("telephoneNumber", telephoneNumber);
        jsonBody.put("zipCode", zipCode);
        jsonBody.put("address", address);

        // Assigning dynamic value
        jsonBody.put("memberId", memberIds.get(0));
        jsonBody.put("genderCode", genderCode);

        // Put request for Updating specified member resource
        // @formatter:off
        given().body(jsonBody)
               .contentType(ContentType.JSON)
               .filters(new RequestLoggingFilter(captor),
                       new ResponseLoggingFilter(captor))
               .when()
               .put("/members/{memberId}", memberIds.get(0))
               .then().statusCode(200)
               .body(
                     "memberId", equalTo(memberIds.get(0)),
                     "firstName", equalTo(firstName),
                     "lastName", equalTo(lastName),
                     "genderCode", equalTo(genderCode),
                     "dateOfBirth", equalTo(dateOfBirth),
                     "emailAddress", equalTo(emailAddress),
                     "telephoneNumber", equalTo(telephoneNumber),
                     "zipCode", equalTo(zipCode),
                     "address", equalTo(address),
                     "createdAt", equalTo("2013-12-09T04:50:12.100Z"),
                     "lastModifiedAt", equalTo("2013-12-09T04:50:12.100Z")
                    );
        // @formatter:on

    }

    /**
     * testREST0205001
     * <ul>
     * <li>REST APIの実装</li>
     * <li>Delete specified member resource</li>
     * </ul>
     */
    @Test
    public void testREST0205001() throws Exception {
        // Get all existing member resources for memberId
        // @formatter:off
        List<String> memberIds = given().when()
                .get("/members")
                .then()
                .extract()
                .jsonPath()
                .getList("memberId");
        // @formatter:on

        // Delete member resources one by one
        for (String memberId : memberIds) {
            // @formatter:off
            given().filters(new RequestLoggingFilter(captor),
                    new ResponseLoggingFilter(captor))
                   .when()
                   .delete("/members/{memberId}", memberId)
                   .then().statusCode(204)
                   .body(equalTo(""));
            // @formatter:on
        }

        // Try to get all existing member resources and check that
        // there are empty response body
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .get("/members")
               .then().statusCode(200)
               .body(equalTo("[]"));
        // @formatter:on

    }

    /**
     * testREST0301001
     * <ul>
     * <li>例外ハンドリングの実装</li>
     * <li>Test Handle Exception Internal</li>
     * </ul>
     */
    @Test
    public void testREST0301001() throws Exception {
        // Put request with empty request body
        // Confirm the error contents
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .put("/members")
               .then().statusCode(405)
               .body(
                     "code", is("e.sf.cmmn.6001"),
                     "message", is("Request method not supported.")
                    );
        // @formatter:on
    }

    /**
     * testREST0302001
     * <ul>
     * <li>例外ハンドリングの実装</li>
     * <li>Test Method Argument Not Valid Exception</li>
     * </ul>
     */
    @Test
    public void testREST0302001() throws Exception {
        // Assign null value to the firstName field
        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("firstName", null);

        // Post request by having null value of firstName field
        // Confirm the error contents
        // @formatter:off
        given().body(jsonBody)
               .contentType(ContentType.JSON)
               .filters(new RequestLoggingFilter(captor),
                       new ResponseLoggingFilter(captor))
               .when()
               .post("/members")
               .then().statusCode(400)
               .body(
                     "code", is("e.sf.cmmn.7007"),
                     "message", is("Validation error occurred on item in the request body."),
                     "details.code", hasItems("NotEmpty"),
                     "details.message", hasItems("空要素は許可されていません"),
                     "details.target", hasItems("firstName")
                    );
        // @formatter:on
    }

    /**
     * testREST0302002
     * <ul>
     * <li>例外ハンドリングの実装</li>
     * <li>Test BindException</li>
     * </ul>
     */
    @Test
    public void testREST0302002() throws Exception {

        // Get request by keeping search condition field empty
        // Confirm the error contents
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .get("/members?name=")
               .then().statusCode(400)
               .body(
                     "code", is("e.sf.cmmn.7002"),
                     "message", is("Validation error occurred on item in the request parameters."),
                     "details.code", hasItems("NotEmpty"),
                     "details.message", hasItems("空要素は許可されていません"),
                     "details.target", hasItems("name")
                    );
        // @formatter:on
    }

    /**
     * testREST0302003
     * <ul>
     * <li>例外ハンドリングの実装</li>
     * <li>Test HttpMessage Not Readable Exception</li>
     * </ul>
     */
    @Test
    public void testREST0302003() throws Exception {
        // Creating json body with non-existing field
        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("fieldNotExist", "FirstName");

        // Post request with non-existing field
        // Confirm the error contents
        // @formatter:off
        given().body(jsonBody)
               .contentType(ContentType.JSON)
               .filters(new RequestLoggingFilter(captor),
                       new ResponseLoggingFilter(captor))
               .when()
               .post("/members")
               .then().statusCode(400)
               .body(
                     "code", is("e.sf.cmmn.7004"),
                     "message", is("Unknown field exists in JSON.")
                    );
        // @formatter:on
    }

    /**
     * testREST0303001
     * <ul>
     * <li>例外ハンドリングの実装</li>
     * <li>Test Resource Not Found Exception</li>
     * </ul>
     */
    @Test
    public void testREST0303001() throws Exception {
        // Get request for non existent member resource
        // Confirm the error contents
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .get("/members/non-existent-memberId")
               .then().statusCode(404)
               .body(
                     "code", is("e.sf.mm.5001"),
                     "message", is("Specified member not found. member id : non-existent-memberId")
                    );
        // @formatter:on
    }

    /**
     * testREST0304001
     * <ul>
     * <li>例外ハンドリングの実装</li>
     * <li>Test Business Exception</li>
     * </ul>
     */
    @Test
    public void testREST0304001() throws Exception {
        // Assign dummy memberID
        String memberId = "dummyID";

        // Put request for getting Business Exception
        // Confirm the error contents
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .put("/members/businessExp/{memberId}", memberId)
               .then().statusCode(409)
               .body(
                     "code", is("e.sf.mm.8001"),
                     "message", is("Cannot use specified member id. member id : dummyID")
                    );
        // @formatter:on
    }

    /**
     * testREST0305001
     * <ul>
     * <li>例外ハンドリングの実装</li>
     * <li>Test OptimisticLockingFailure Exception</li>
     * </ul>
     */
    @Test
    public void testREST0305001() throws Exception {

        // Put request for getting Business Exception
        // Confirm the error contents
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .put("/members/optimisticExp")
               .then().statusCode(409)
               .body(
                     "code", is("e.sf.cmmn.8006"),
                     "message", is("Conflict with other processing occurred.")
                    );
        // @formatter:on
    }

    /**
     * testREST0306001
     * <ul>
     * <li>例外ハンドリングの実装</li>
     * <li>Test Exception</li>
     * </ul>
     */
    @Test
    public void testREST0306001() throws Exception {

        // Put request for getting Business Exception
        // Confirm the error contents
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .put("/members/exp")
               .then().statusCode(500)
               .body(
                     "code", is("e.sf.cmmn.9001"),
                     "message", is("System error occurred!")
                    );
        // @formatter:on
    }

    /**
     * testREST0401001
     * <ul>
     * <li>サーブレットコンテナに通知されたエラーハンドリングの実装</li>
     * <li>サーブレットコンテナにエラー通知された後、Controllerでハンドリング</li>
     * </ul>
     */
    @Test
    public void testREST0401001() throws Exception {
        // Issue1204
        // WASの場合、Content-Typeがtext形式で返却され、かつ、
        // RestAssuredがtext形式に対応しておらずアサートが正しく動作しないため
        // テストはスキップする
        ApServerName apServerName = getApServerName();
        assumeTrue(!((apServerName == ApServerName.WEBSPHERELP)
                || (apServerName == ApServerName.WEBSPHERETR)));

        // Put request for getting Business Exception
        // Confirm the error contents
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .get("/members/httpVersionNotSupport")
               .then().statusCode(505)
               .body(
                     "code", is("e.sf.cmmn.9505"),
                     "message", is("HTTP Version not supported.")
                    );
        // @formatter:on
    }

    /**
     * testREST0401002
     * <ul>
     * <li>サーブレットコンテナに通知されたエラーハンドリングの実装</li>
     * <li>サーブレットコンテナにエラー通知された後、Controllerでハンドリング</li>
     * </ul>
     */
    @Test
    public void testREST0401002() throws Exception {
        // Issue1204
        // WASの場合、Content-Typeがtext形式で返却され、かつ、
        // RestAssuredがtext形式に対応しておらずアサートが正しく動作しないため
        // テストはスキップする
        ApServerName apServerName = getApServerName();
        assumeTrue(!((apServerName == ApServerName.WEBSPHERELP)
                || (apServerName == ApServerName.WEBSPHERETR)));

        // Put request for getting Business Exception
        // Confirm the error contents
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .get("/members/gatewayTimeout")
               .then().statusCode(504)
               .body(
                     "code", nullValue(),
                     "message", is(HttpStatus.GATEWAY_TIMEOUT.getReasonPhrase())
                    );
        // @formatter:on
    }

    /**
     * testREST0402001
     * <ul>
     * <li>サーブレットコンテナに通知されたエラーハンドリングの実装</li>
     * <li>静的なJSONファイルが応答されること</li>
     * </ul>
     */
    @Test
    public void testREST0402001() throws Exception {

        // Put request for getting Business Exception
        // Confirm the error contents
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .get("/members/unknownError")
               .then().statusCode(508)
               .contentType(containsString(MediaType.APPLICATION_JSON_VALUE))
               .body(
                     "code", is("e.sf.cmmn.0508"),
                     "message", is("サービス利用できないエラーが発生しました。")
                    );
        // @formatter:on
    }

    /**
     * testREST0501001
     * <ul>
     * <li>ハイパーメディアリンクの実装</li>
     * <li>Get specified member resource and check if hyper media link is added</li>
     * </ul>
     */
    @Test
    public void testREST0501001() throws Exception {
        // Get all existing member resources for memberId
        List<String> memberIds = given().when().get("/members").then().extract()
                .jsonPath().getList("memberId");

        // Get request for retrieving specified member resource &
        // Check the retrieved contents
        int i = 1;
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .get("/members/{memberId}", memberIds.get(0))
               .then().statusCode(200)
               .body("links.rel[0]", equalTo("self"))
               .body(
                     "links.href[0]", equalTo(applicationContextUrl 
                             + "/api/v1/DEFAULT_VIEW_INCLUSION-enable/members/"
                             + memberIds.get(0)),
                     "memberId", equalTo(memberIds.get(0)),
                     "firstName", equalTo("FirstName" + i),
                     "lastName", equalTo("LastName" + i)
                    );
        // @formatter:on
    }

    /**
     * testREST0601001
     * <ul>
     * <li>POST時のLocationヘッダの実装</li>
     * <li>Add one member resource and check if hyper media link is added in the Location of the Header part</li>
     * </ul>
     */
    @Test
    public void testREST0601001() throws Exception {

        // Assigning field values
        String firstName = "FirstName";
        String lastName = "LastName";
        String genderCode = "1";
        String dateOfBirth = "2015-02-02";
        String emailAddress = "FirstName@test.co.jp";
        String telephoneNumber = "123456789";
        String zipCode = "1116666";
        String address = "Address";
        String password = "password";

        // Creating credential Hash map for jsonBody
        Map<String, Object> credential = new HashMap<String, Object>();
        credential.put("password", password);

        // Creating Hash map for jsonBody
        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("firstName", firstName);
        jsonBody.put("lastName", lastName);
        jsonBody.put("genderCode", genderCode);
        jsonBody.put("dateOfBirth", dateOfBirth);
        jsonBody.put("emailAddress", emailAddress);
        jsonBody.put("telephoneNumber", telephoneNumber);
        jsonBody.put("zipCode", zipCode);
        jsonBody.put("address", address);
        jsonBody.put("credential", credential);

        // Executing post request to create member
        // @formatter:off
        given().body(jsonBody)
               .contentType(ContentType.JSON)
               .filters(new RequestLoggingFilter(captor),
                       new ResponseLoggingFilter(captor))
               .when()
               .post("/members")
               .then().statusCode(201)
               .header("Location", containsString(applicationContextUrl
                       + "/api/v1/DEFAULT_VIEW_INCLUSION-enable/members/M0"))
               .body(
                     "memberId", startsWith("M0"),
                     "firstName", equalTo(firstName),
                     "lastName", equalTo(lastName)
                    );
        // @formatter:on
    }

    /**
     * testREST0603001
     * <ul>
     * <li>OPTIONSメソッドの実装</li>
     * <li>Check Request method type OPTION implementation</li>
     * </ul>
     */
    @Test
    public void testREST0603001() throws Exception {
        // Get all existing member resources for memberId
        List<String> memberIds = given().when().get("/members").then().extract()
                .jsonPath().getList("memberId");

        // Get request for retrieving specified member resource &
        // Check the retrieved contents
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .options("/members/{memberId}", memberIds.get(0))
               .then().statusCode(200)//
               .header("Allow", containsString("GET"))
               .header("Allow", containsString("HEAD"))
               .header("Allow", containsString("PUT"))
               .header("Allow", containsString("DELETE"))
               .header("Content-Length", containsString("0"))
               .body(equalTo(""));
        // @formatter:on
    }

    /**
     * testREST0604001
     * <ul>
     * <li>HEADメソッドの実装</li>
     * <li>Check Request method type HEAD implementation</li>
     * </ul>
     */
    @Test
    public void testREST0604001() throws Exception {
        // Get all existing member resources for memberId
        List<String> memberIds = given().when().get("/members").then().extract()
                .jsonPath().getList("memberId");

        // Fetch content length of GET request
        // Response response = given().when().get("/members/{memberId}", memberIds
        // .get(0));
        // int getContentLength = response.getBody().asString().length();

        // HEAD request for retrieving specified member resource &
        // Check the retrieved contents are correct along with content length
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .head("/members/{memberId}", memberIds.get(0))
               .then().statusCode(200)
               .contentType(containsString(MediaType.APPLICATION_JSON_VALUE))
               // Servlet6.0からdoHeadのデフォルトではcontent-lengthを返却しなくなった
               // Content-Lengthを返却したい場合はweb.xmlでjakarta.servlet.http.legacyDoHeadをtrueに設定する必要がある 
               //.header("Content-Length", o -> Integer.parseInt(o), is(getContentLength))
               .body(equalTo(""));
     // @formatter:on
    }

    /**
     * testREST0701001
     * <ul>
     * <li>@JsonViewを使用したレスポンスの出力制御</li>
     * <li>Get specified member resource summary</li>
     * </ul>
     */
    @Test
    public void testREST0701001() throws Exception {
        // Get all existing member resources for memberId
        List<String> memberIds = given().when().get("/members").then().extract()
                .jsonPath().getList("memberId");

        // Get request for retrieving specified member resource &
        // Check the retrieved contents
        int i = 1;
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .get("/members/{memberId}?format=summary", memberIds.get(0))
               .then().statusCode(200)//
               .body(
                     "memberId", equalTo(memberIds.get(0)),
                     "firstName", equalTo("FirstName" + i),
                     "lastName", equalTo("LastName" + i),
                     "content", not(containsString("genderCode")),
                     "content", not(containsString("dateOfBirth")),
                     "content", not(containsString("emailAddress")),
                     "content", not(containsString("telephoneNumber")),
                     "content", not(containsString("zipCode")),
                     "content", not(containsString("address")),
                     "credential.signId", equalTo("firstname" + i + "@test.co.jp")
                    );
        // @formatter:on

    }

    /**
     * testREST0701002
     * <ul>
     * <li>@JsonViewを使用したレスポンスの出力制御</li>
     * <li>Get specified member resource detail</li>
     * </ul>
     */
    @Test
    public void testREST0701002() throws Exception {
        // Get all existing member resources for memberId
        List<String> memberIds = given().when().get("/members").then().extract()
                .jsonPath().getList("memberId");

        // Get request for retrieving specified member resource &
        // Check the retrieved contents
        int i = 1;
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .get("/members/{memberId}?format=detail", memberIds.get(0))
               .then().statusCode(200)
               .body(
                     "memberId", equalTo(memberIds.get(0)),
                     "firstName", equalTo("FirstName" + i),
                     "lastName", equalTo("LastName" + i),
                     "genderCode", equalTo("1"),
                     "dateOfBirth", equalTo("2015-01-0" + i),
                     "emailAddress", equalTo("FirstName" + i + "@test.co.jp"),
                     "telephoneNumber", equalTo("11111" + i),
                     "zipCode", equalTo(i + "00000" + i),
                     "address", equalTo("Address" + i),
                     "credential.signId", equalTo("firstname" + i + "@test.co.jp")
                    );
        // @formatter:on
    }

    /**
     * testREST0701003
     * <ul>
     * <li>@JsonViewを使用したレスポンスの出力制御</li>
     * <li>Get specified member resource summary only</li>
     * </ul>
     */
    @Test
    public void testREST0701003() throws Exception {
        // Get all existing member resources for memberId
        List<String> memberIds = given().when().get("/members").then().extract()
                .jsonPath().getList("memberId");

        // Set MapperFeature.DEFAULT_VIEW_INCLUSION to disable
        RestAssured.baseURI = applicationContextUrl
                + "/api/v1/DEFAULT_VIEW_INCLUSION-disable";

        // Get request for retrieving specified member resource &
        // Check the retrieved contents
        int i = 1;
        // @formatter:off
        given().filters(new RequestLoggingFilter(captor),
                new ResponseLoggingFilter(captor))
               .when()
               .get("/members/{memberId}?format=summary", memberIds.get(0))
               .then().statusCode(200)
               .body(
                     "memberId", equalTo(memberIds.get(0)),
                     "firstName", equalTo("FirstName" + i),
                     "lastName", equalTo("LastName" + i),
                     "content", not(containsString("genderCode")),
                     "content", not(containsString("dateOfBirth")),
                     "content", not(containsString("emailAddress")),
                     "content", not(containsString("telephoneNumber")),
                     "content", not(containsString("zipCode")),
                     "content", not(containsString("address")),
                     "content", not(containsString("credential"))
                    );
        // @formatter:on
    }

    /**
     * testREST0801001
     * <ul>
     * <li>リクエストとレスポンスへの共通的な処理</li>
     * <li>Get specified member resource and check if RequestBodyAdvice and ResponseBodyAdvice is processed</li>
     * </ul>
     */
    @Test
    public void testREST0801001() throws Exception {
        // Get all existing member resources for memberId
        List<String> memberIds = given().when().get("/members").then().extract()
                .jsonPath().getList("memberId");

        // Creating Hash map for jsonBody
        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("memberId", memberIds.get(0));

        // Executing post request to create member
        int i = 1;
        // @formatter:off
        given().body(jsonBody)
               .contentType(ContentType.JSON)
               .filters(new RequestLoggingFilter(captor),
                       new ResponseLoggingFilter(captor))
               .when()
               .post("/members/getMemberWithAdvice")
               .then().statusCode(200)
               .contentType(containsString(MediaType.APPLICATION_JSON_VALUE))
               .body(
                     "memberId", startsWith("M0"),
                     "firstName", equalTo("FirstName" + i),
                     "lastName", equalTo("LastName" + i),
                     "genderCode", equalTo("1"),
                     "dateOfBirth", equalTo("2015-01-0" + i),
                     "emailAddress", equalTo("FirstName" + i + "@test.co.jp"),
                     "telephoneNumber", equalTo("11111" + i),
                     "zipCode", equalTo(i + "00000" + i),
                     "address", equalTo("Address" + i),
                     "credential.signId", equalTo("firstname" + i + "@test.co.jp"),
                     "startDateTime", equalTo("2015-12-16T13:10:04.005"),
                     "endDateTime", equalTo("2015-12-16T14:10:04.005")
                    );
        // @formatter:on
    }
}
