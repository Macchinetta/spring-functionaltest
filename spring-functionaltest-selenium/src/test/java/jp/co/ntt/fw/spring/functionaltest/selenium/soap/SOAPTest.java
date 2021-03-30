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
package jp.co.ntt.fw.spring.functionaltest.selenium.soap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.By.id;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.ApServerName;
import jp.co.ntt.fw.spring.functionaltest.selenium.SOAPTestSupport;
import jp.co.ntt.fw.spring.functionaltest.ws.webfault.ErrorBean;
import jp.co.ntt.fw.spring.functionaltest.ws.webfault.WebFaultType;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class SOAPTest extends SOAPTestSupport {

    private static final int TODO_MAX_COUNT = 5;

    @Override
    protected void onFinished() {
        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("deleteTodos"));
    }

    /**
     * <ul>
     * <li>クライアントからSOAPサーバのWebサービスが実行できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testSOAP0101001() throws Exception {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("SOAP0101001-01"));
        }
        // 結果の確認
        {
            assertThat(webDriverOperations.exists(id("todos")), is(false));

            dbLogAssertOperations.waitForAssertion();
            assertHttpStatusCode(200);
        }

    }

    /**
     * <ul>
     * <li>ローカルにあるwsdlファイルを用いたクライアントのWebサービスが実行できることを確認する。</li>
     * <li>エンドポイントを指定したクライアントからSOAPサーバのWebサービスが実行できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testSOAP0102002() throws Exception {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("SOAP0102002-01"));
        }
        // 結果の確認
        {
            assertThat(webDriverOperations.exists(id("todos")), is(false));

            dbLogAssertOperations.waitForAssertion();
            assertHttpStatusCode(200);
        }

    }

    /**
     * <ul>
     * <li>SOAPサーバのWebサービスで引数の入力チェックが実行できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testSOAP0201001() throws Exception {

        String todoId;

        // 【１．TODO作成 - 成功】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201001-01"));
            }
            // 作成情報入力
            {
                webDriverOperations.overrideText(id("title"), "テスト");
                webDriverOperations.overrideText(id("description"),
                        "test description");
                webDriverOperations.click(id("create"));
            }
            // 結果の確認
            {
                // 次処理のインプット情報取得
                todoId = webDriverOperations.getText(id("todoId"));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【２．TODO取得 - 成功】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201001-02"));
            }
            // 取得情報入力
            {
                webDriverOperations.overrideText(id("todoId"), todoId);
                webDriverOperations.click(id("get"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.getText(id("title")), is("テスト"));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【３．TODO取得 - 失敗（検索キーがnull）】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201001-03"));
            }
            // 取得情報入力
            {
                webDriverOperations.clearText(id("todoId"));
                webDriverOperations.click(id("get"));
            }
            // 結果の確認
            {
                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(500);

                assertWebFaultIsInErrorBeans(WebFaultType.ValidationFault,
                        new ErrorBean("NotNull", "null は許可されていません", "todoId"),
                        new ErrorBean("NotNull", "null は許可されていません", "arg0"));
            }
        }

    }

    /**
     * <ul>
     * <li>SOAPサーバのWebサービスでJavaBeanの入力チェックが実行できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testSOAP0201002() throws Exception {

        // 【１．TODO作成 - 成功】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201002-01"));
            }
            // 作成情報入力
            {
                webDriverOperations.overrideText(id("title"), "テスト");
                webDriverOperations.overrideText(id("description"),
                        "test description");
                webDriverOperations.click(id("create"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.getText(id("title")), is("テスト"));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【２．TODO一覧取得 - 成功】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201002-02"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.exists(id("todos")), is(true));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【３．TODO作成 - 失敗（タイトルnull）】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201002-03"));
            }
            // 作成情報入力
            {
                webDriverOperations.clearText(id("title"));
                webDriverOperations.overrideText(id("description"),
                        "test description");
                webDriverOperations.click(id("create"));
            }
            // 結果の確認
            {
                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(500);
                assertWebFault(WebFaultType.ValidationFault,
                        new ErrorBean("NotNull", "null は許可されていません", "title"));
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【４．TODO一覧取得 - 成功】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201002-04"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.exists(id("todos")), is(true));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

    }

    /**
     * <ul>
     * <li>SOAPサーバのWebサービスでグループ化を用いたJavaBeanの入力チェックが実行できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testSOAP0201003() throws Exception {

        String createdTodoId;
        String createdAt;
        String forUpdateTitle = "アップデートテスト";
        String forUpdateDescription = "update test description";

        String beforeTodoId;
        String beforeTitle;
        String beforeDescription;
        String beforeFinished;
        String beforeCreatedAt;

        // 【１．TODO作成 - 成功】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201003-01"));
            }
            // 作成情報入力
            {
                webDriverOperations.overrideText(id("title"), "テスト");
                webDriverOperations.overrideText(id("description"),
                        "test description");
                webDriverOperations.click(id("create"));
            }
            // 結果の確認
            {
                createdTodoId = webDriverOperations.getText(id("todoId"));
                createdAt = webDriverOperations.getText(id("createdAt"));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【２．TODO更新 - 成功】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201003-02"));
            }
            // 作成情報入力
            {
                webDriverOperations.overrideText(id("todoId"), createdTodoId);
                webDriverOperations.overrideText(id("title"), forUpdateTitle);
                webDriverOperations.overrideText(id("description"),
                        forUpdateDescription);
                webDriverOperations.click(id("finished1"));
                webDriverOperations.click(id("update"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.getText(id("todoId")), is(
                        createdTodoId));
                assertThat(webDriverOperations.getText(id("title")), is(
                        forUpdateTitle));
                assertThat(webDriverOperations.getText(id("description")), is(
                        forUpdateDescription));
                assertThat(webDriverOperations.getText(id("finished")), is(
                        "true"));
                assertThat(webDriverOperations.getText(id("createdAt")), is(
                        createdAt));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【３．TODO一覧取得】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201003-03"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.exists(id("todos")), is(true));

                beforeTodoId = webDriverOperations.getText(id("todoId1"));
                beforeTitle = webDriverOperations.getText(id("title1"));
                beforeDescription = webDriverOperations.getText(id(
                        "description1"));
                beforeFinished = webDriverOperations.getText(id("finished1"));
                beforeCreatedAt = webDriverOperations.getText(id("createdAt1"));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【４．TODO更新 - 失敗（todoIdがnull）】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201003-04"));
            }
            // 更新情報入力
            {
                webDriverOperations.clearText(id("todoId"));
                webDriverOperations.overrideText(id("title"), "再アップデートテスト");
                webDriverOperations.overrideText(id("description"),
                        "update test description again");
                webDriverOperations.click(id("finished1"));
                webDriverOperations.click(id("update"));
            }
            // 結果の確認
            {
                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(500);
                assertWebFault(WebFaultType.ValidationFault,
                        new ErrorBean("NotNull", "null は許可されていません", "todoId"));
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【５．TODO一覧取得】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201003-05"));
            }
            // 結果の確認

            {
                // updateされていないことの確認
                assertThat(webDriverOperations.exists(id("todos")), is(true));

                assertThat(webDriverOperations.getText(id("todoId1")), is(
                        beforeTodoId));
                assertThat(webDriverOperations.getText(id("title1")), is(
                        beforeTitle));
                assertThat(webDriverOperations.getText(id("description1")), is(
                        beforeDescription));
                assertThat(webDriverOperations.getText(id("finished1")), is(
                        beforeFinished));
                assertThat(webDriverOperations.getText(id("createdAt1")), is(
                        beforeCreatedAt));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

    }

    /**
     * <ul>
     * <li>SOAPサーバのWebサービスで複数項目の入力チェックが実行できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testSOAP0201004() throws Exception {

        String beforeTodoId;
        String beforeTitle;
        String beforeDescription;
        String beforeFinished;
        String beforeCreatedAt;

        // 【１．TODO作成 - 成功】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201004-01"));
            }
            // 作成情報入力
            {
                webDriverOperations.overrideText(id("title"), "テスト");
                webDriverOperations.overrideText(id("description"),
                        "test description");
                webDriverOperations.click(id("create"));
            }
            // 結果の確認
            {
                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【２．TODO一覧取得】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201004-02"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.exists(id("todos")), is(true));

                beforeTodoId = webDriverOperations.getText(id("todoId1"));
                beforeTitle = webDriverOperations.getText(id("title1"));
                beforeDescription = webDriverOperations.getText(id(
                        "description1"));
                beforeFinished = webDriverOperations.getText(id("finished1"));
                beforeCreatedAt = webDriverOperations.getText(id("createdAt1"));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【３．TODO更新 - 失敗（TODO_IDおよびタイトルがnull）】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201004-03"));
            }
            // 更新情報入力
            {
                webDriverOperations.clearText(id("todoId"));
                webDriverOperations.clearText(id("title"));
                webDriverOperations.overrideText(id("description"),
                        "update test description");
                webDriverOperations.click(id("finished1"));
                webDriverOperations.click(id("update"));
            }
            // 結果の確認
            {
                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(500);
                assertWebFault(WebFaultType.ValidationFault,
                        new ErrorBean("NotNull", "null は許可されていません", "todoId"),
                        new ErrorBean("NotNull", "null は許可されていません", "title"));
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【４．TODO一覧取得】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0201004-04"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.exists(id("todos")), is(true));

                // updateされていないことの確認
                assertThat(webDriverOperations.getText(id("todoId1")), is(
                        beforeTodoId));
                assertThat(webDriverOperations.getText(id("title1")), is(
                        beforeTitle));
                assertThat(webDriverOperations.getText(id("description1")), is(
                        beforeDescription));
                assertThat(webDriverOperations.getText(id("finished1")), is(
                        beforeFinished));
                assertThat(webDriverOperations.getText(id("createdAt1")), is(
                        beforeCreatedAt));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

    }

    /**
     * <ul>
     * <li>クライアントからBASIC認証の認証情報を送信し、SOAPサーバでBASIC認証が実行できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testSOAP0301001() throws Exception {

        // 【１．TODO作成 - 成功】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0301001-01"));
            }
            // 作成情報入力
            {
                webDriverOperations.overrideText(id("title"), "テスト");
                webDriverOperations.overrideText(id("description"),
                        "test description");
                webDriverOperations.click(id("create"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.getText(id("title")), is("テスト"));
                assertThat(webDriverOperations.getText(id("description")), is(
                        "test description by testuser"));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【２．TODO一覧取得】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0301001-02"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.exists(id("todos")), is(true));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【３．TODO作成 - 失敗（認証エラー）】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0301001-03"));
            }
            // 作成情報入力
            {
                webDriverOperations.overrideText(id("title"), "テスト");
                webDriverOperations.overrideText(id("description"),
                        "test description");
                webDriverOperations.click(id("create"));
            }
            // 結果の確認
            {
                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(401);
                String exceptionClassName;
                // Issue1231. WebSphere Traditionalだけ発生する例外クラスが異なるため
                // チェックする名称を変更する。
                if (webDriverOperations
                        .getApServerName() == ApServerName.WEBSPHERETR) {
                    exceptionClassName = "org.springframework.remoting.jaxws.JaxWsSoapFaultException";
                } else {
                    exceptionClassName = "org.springframework.remoting.RemoteAccessException";
                }
                dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                        webDriverOperations.getXTrack(), null, "e.sf.cmmn.9001",
                        exceptionClassName);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【４．TODO一覧取得】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0301001-04"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.exists(id("todos")), is(true));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

    }

    /**
     * <ul>
     * <li>SOAPサーバでServiceでの認可ができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testSOAP0301002() throws Exception {

        String todoId;

        // 【１．TODO作成 - 成功】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0301002-01"));
            }
            // 作成情報入力
            {
                webDriverOperations.overrideText(id("title"), "テスト");
                webDriverOperations.overrideText(id("description"),
                        "test description");
                webDriverOperations.click(id("create"));
            }
            // 結果の確認
            {
                todoId = webDriverOperations.getText(id("todoId"));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【２．TODO一覧取得】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0301002-02"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.exists(id("todos")), is(true));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【３．TODO削除 - 成功】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0301002-03"));
            }
            // 作成情報入力
            {
                webDriverOperations.overrideText(id("todoId"), todoId);
                webDriverOperations.click(id("delete"));
            }
            // 結果の確認
            {
                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【４．TODO一覧取得】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0301002-04"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.exists(id("todos")), is(false));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【５．TODO作成 - 成功】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0301002-05"));
            }
            // 作成情報入力
            {
                webDriverOperations.overrideText(id("title"), "テスト");
                webDriverOperations.overrideText(id("description"),
                        "test description");
                webDriverOperations.click(id("create"));
            }
            // 結果の確認
            {
                todoId = webDriverOperations.getText(id("todoId"));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【６．TODO一覧取得】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0301002-06"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.exists(id("todos")), is(true));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【７．TODO削除 - 失敗（認可エラー）】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0301002-07"));
            }
            // 作成情報入力
            {
                webDriverOperations.overrideText(id("todoId"), todoId);
                webDriverOperations.click(id("delete"));
            }
            // 結果の確認
            {
                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(500);
                assertWebFault(WebFaultType.AccessDeniedFault,
                        new ErrorBean("org.springframework.security.access.AccessDeniedException", "Access is denied", null));
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【８．TODO一覧取得】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0301002-08"));
            }
            // 結果の確認
            {
                assertThat(webDriverOperations.exists(id("todos")), is(true));

                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

    }

    /**
     * <ul>
     * <li>SOAPサーバで発生したリソース未検出エラーをSOAPFaultにラップしてスローできることを確認する。</li>
     * <li>SOAPFaultをクライアントでキャッチして処理できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testSOAP0401002() throws Exception {

        // 【１．TODO作成 - 成功】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0401002-01"));
            }
            // 作成情報入力
            {
                webDriverOperations.overrideText(id("title"), "テスト");
                webDriverOperations.overrideText(id("description"),
                        "test description");
                webDriverOperations.click(id("create"));
            }
            // 結果の確認
            {
                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(200);
            }
        }

        webDriverOperations.displayPage(getPackageRootUrl());

        // 【２．TODO取得 - 失敗（検索キーが存在しない）】
        {
            // メニュー画面の操作
            {
                webDriverOperations.click(id("SOAP0401002-02"));
            }
            // 取得情報入力
            {
                webDriverOperations.overrideText(id("todoId"), "aaa");
                webDriverOperations.click(id("get"));
            }
            // 結果の確認
            {
                dbLogAssertOperations.waitForAssertion();
                assertHttpStatusCode(500);
                assertWebFault(WebFaultType.ResourceNotFoundFault,
                        new ErrorBean("e.sf.soap.5001", "Resource not found", null));
            }
        }

    }

    /**
     * <ul>
     * <li>SOAPサーバで発生した業務エラーをSOAPFaultにラップしてスローできることを確認する。</li>
     * <li>SOAPFaultをクライアントでキャッチして処理できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testSOAP0401003() throws Exception {

        // 【１．TODO作成 - 失敗（101個目TODO作成で業務例外）】
        {
            int count = TODO_MAX_COUNT + 1;
            for (int i = 0; i < count; i++) {
                webDriverOperations.displayPage(getPackageRootUrl());
                webDriverOperations.click(id("SOAP0401003-01"));

                webDriverOperations.overrideText(id("title"), "テスト " + (i + 1));
                webDriverOperations.overrideText(id("description"),
                        "test description " + (i + 1));
                webDriverOperations.click(id("create"));
            }
        }
        // 結果の確認
        {
            dbLogAssertOperations.waitForAssertion();
            assertHttpStatusCode(500);
            assertWebFault(WebFaultType.BusinessFault,
                    new ErrorBean("e.sf.soap.8001", "Exceed todo count max", null));
        }

    }

    /**
     * <ul>
     * <li>SOAPサーバの例外ハンドラーで設定されていない例外が発生したとき、システム例外をスローすることを確認する。</li>
     * </ul>
     */
    @Test
    public void testSOAP0401004() throws Exception {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("SOAP0401004-01"));
        }
        // 結果の確認
        {
            dbLogAssertOperations.waitForAssertion();
            assertHttpStatusCode(500);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                    webDriverOperations.getXTrack(), null, "e.sf.cmmn.9001",
                    "org.springframework.remoting.jaxws.JaxWsSoapFaultException");
        }

    }

    /**
     * <ul>
     * <li>MTOMを利用したファイル転送ができることを確認する</li>
     * </ul>
     */
    @Test
    public void testSOAP0501001() throws Exception {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("SOAP0501001-01"));
        }
        // 入力
        {
            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/soap/test.png").getFile());
            webDriverOperations.click(id("upload"));
        }
        // 結果の確認
        {
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "SOAP SOAP Web Service（サーバ/クライアント）"));

            dbLogAssertOperations.waitForAssertion();
            assertHttpStatusCode(200);
        }

    }

    /**
     * <ul>
     * <li>クライアントで設定したSOAPサーバへのリクエストタイムアウトになることを確認する</li>
     * </ul>
     */
    @Test
    public void testSOAP0601001() throws Exception {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("SOAP0601001-01"));
        }
        // 結果の確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                    webDriverOperations.getXTrack(), null, "e.sf.cmmn.9001",
                    "java.net.SocketTimeoutException");
        }

    }

    /**
     * <ul>
     * <li>クライアントで設定したSOAPサーバへのコネクションタイムアウトになることを確認する</li>
     * </ul>
     */
    @Ignore("コネクションタイムアウトを意図的に発生させることができないため")
    public void testSOAP0601002() throws Exception {

    }

    /**
     * <ul>
     * <li>wsimportを利用し、クライアントからSOAPサーバのWebサービスが実行できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testSOAP0701001() throws Exception {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("SOAP0701001-01"));
        }
        // 結果の確認
        {
            assertThat(webDriverOperations.exists(id("todos")), is(false));

            dbLogAssertOperations.waitForAssertion();
            assertHttpStatusCode(200);
        }

    }

}
