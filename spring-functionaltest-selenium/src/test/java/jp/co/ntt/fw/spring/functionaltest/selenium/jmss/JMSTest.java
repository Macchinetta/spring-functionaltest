/*
 * Copyright 2014-2017 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.jmss;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class JMSTest extends FunctionTestSupport {

    @Value("${selenium.jmss.retryForExpectedString.interval}")
    private int retryInterval;

    @Value("${selenium.jmss.retryForExpectedString.max}")
    private int retryCount;

    @After
    public void clear() {
        restOperations.getForEntity(applicationContextUrl + "/jmss/clear",
                Void.class);
    }

    @Test
    public void testJMSS0101001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0101001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));

    }

    @Test
    public void testJMSS0102001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0102001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));

    }

    @Test
    public void testJMSS0103001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0103001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));

    }

    /**
     * JMSS0201001
     * <ul>
     * <li>物理名によりDestinationの名前解決ができることを確認する。</li>
     * </ul>
     */
    @Ignore("testJMSS0101001で実施")
    public void testJMSS0201001() {
        testJMSS0101001();
    }

    /**
     * JMSS0202001
     * <ul>
     * <li>物理名によりDestinationの名前解決ができることを確認する。</li>
     * </ul>
     */
    @Ignore("testJMSS0102001で実施")
    public void testJMSS0202001() {
        testJMSS0102001();
    }

    @Test
    public void testJMSS0301001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0301001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));

    }

    /**
     * JMSS0301002
     * <ul>
     * <li>JmsMessagingTemplateのconvertAndSendを使用しメッセージ送信ができることを確認する。</li>
     * </ul>
     */
    @Ignore("testJMSS0103001で実施")
    public void testJMSS0301002() {
        testJMSS0103001();
    }

    @Test
    public void testJMSS0301003() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0301003"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));

    }

    @Test
    public void testJMSS0301004() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0301004"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                "All of the subscriber has been received.");

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                "All of the subscriber has been received."));
    }

    @Test
    public void testJMSS0302001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0302001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("receiveCount"), "3");

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("receiveCount")), is("3"));
    }

    @Test
    public void testJMSS0303001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0303001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));
    }

    @Test
    public void testJMSS0303002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0303002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                "Not Received!"));
    }

    /**
     * JMSS0401001
     * <ul>
     * <li>@JmsListenerによりメッセージの非同期受信ができることを確認する。</li>
     * </ul>
     */
    @Ignore("testJMSS0103001で実施")
    public void testJMSS0401001() {
        testJMSS0103001();
    }

    @Test
    public void testJMSS0401002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0401002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                "Not Received!"));
    }

    @Test
    public void testJMSS0401003() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0401003"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));

    }

    @Test
    public void testJMSS0402001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0402001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));
    }

    @Test
    public void testJMSS0403001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0403001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));
        assertThat(webDriverOperations.getText(id("receiveQueue")), is(
                "TestQueue0403001B"));

    }

    @Test
    public void testJMSS0403002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0403002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));
        assertThat(webDriverOperations.getText(id("receiveQueue")), is(
                "TestQueue0403001C"));
    }

    @Test
    public void testJMSS0403003() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0403003"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));
        assertThat(webDriverOperations.getText(id("receiveQueue")), is(
                "TestQueue0403003B"));
    }

    @Test
    public void testJMSS0404001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0404001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(checkThreadIdAssertion(false, identifier), is(true));
    }

    @Test
    public void testJMSS0404002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0404002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(checkThreadIdAssertion(true, identifier), is(true));
    }

    @Test
    public void testJMSS0501001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0501001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));
    }

    @Test
    public void testJMSS0601001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0601001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));

    }

    @Test
    public void testJMSS0601002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0601002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                "Not Received!"));

    }

    @Test
    public void testJMSS0602001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0602001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));

    }

    @Test
    public void testJMSS0602002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0602002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                "Not Received!"));

    }

    @Test
    public void testJMSS0603001() {
        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0603001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));

    }

    @Test
    public void testJMSS0603002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0603002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + "rollbacked_" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                "rollbacked_" + identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                "rollbacked_" + identifier));

    }

    @Test
    public void testJMSS0604001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0604001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));

    }

    @Test
    public void testJMSS0604002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0604002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                "rollbacked_MQandDB_" + identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                "rollbacked_MQandDB_" + identifier));

    }

    @Test
    public void testJMSS0604005() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0604005"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));

    }

    @Test
    public void testJMSS0604006() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0604006"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + "rollbacked_" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                "rollbacked_MQ_" + identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                "rollbacked_MQ_" + identifier));
    }

    @Test
    public void testJMSS0701001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));
    }

    @Test
    public void testJMSS0701002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                "Validated!! :" + identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                "Validated!! :" + identifier));
    }

    @Test
    public void testJMSS0701003() {
        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701003"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));
    }

    @Test
    public void testJMSS0701004() {
        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701004"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));
    }

    @Test
    public void testJMSS0701005() {
        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701005"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));
    }

    @Test
    public void testJMSS0701006() {
        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701006"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));
    }

    @Test
    public void testJMSS0701007() {
        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701007"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                "rollbacked_DB_" + identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                "rollbacked_DB_" + identifier));
    }

    @Test
    public void testJMSS0701008() {
        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701008"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                "not_rollbacked_MQandDB_" + identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                "not_rollbacked_MQandDB_" + identifier));
    }

    /**
     * JMSS0801001
     * <ul>
     * <li>入力チェックエラー時の例外処理について動作を確認する。</li>
     * </ul>
     */
    @Ignore("testJMSS0701001で実施")
    public void testJMSS0801001() {
        testJMSS0701001();
    }

    @Test
    public void testJMSS0802001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0802001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        // エラーログが出力されるよう待機
        dbLogAssertOperations.waitForAssertion();

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage(null, null,
                "SystemException Error.*");
    }

    @Test
    public void testJMSS0803001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0803001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        // エラーログが出力されるよう待機
        dbLogAssertOperations.waitForAssertion();

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage(null, null,
                "ResultMessage \\[code=e.sf.jmss.8004");
    }

    @Test
    public void testJMSS0803002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0803002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                "BusinessException! :" + identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                "BusinessException! :" + identifier));
    }

    @Test
    public void testJMSS0901001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0901001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl
                + "/jmss/await?jmsTodoId=" + identifier, Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(
                identifier));
    }

    /**
     * ThreadIDに関するAssert(testJMSS0404001, testJMSS0404002)
     * @param concurrent
     * @param identifier
     * @return
     */
    private boolean checkThreadIdAssertion(boolean concurrentMultiple,
            String identifier) {

        boolean assertion = true;

        List<String> threadIdLogs = dbLogAssertOperations.getLogByRegexMessage(
                null, null, "Message : " + identifier
                        + ", ConcurrentThreadID is ..*");
        Collections.sort(threadIdLogs);

        if (threadIdLogs.size() != 0 && !concurrentMultiple) {
            String preThreadId = threadIdLogs.get(0);
            for (String threadIDLog : threadIdLogs) {
                if (!preThreadId.equals(threadIDLog)) {
                    return false;
                }
            }
        } else if (threadIdLogs.size() != 0 && concurrentMultiple) {
            String preThreadId = null;
            for (String threadIDLog : threadIdLogs) {
                if (preThreadId == null) {
                    preThreadId = threadIDLog;
                } else {
                    if (preThreadId.compareTo(threadIDLog) == 0) {
                        return false;
                    }
                }
            }
        }

        return assertion;
    }

    /**
     * 指定した要素に期待する文字列が出現するまで、画面を更新する。
     * <p>
     * <code>selenium.jmss.retryForExpectedString.max</code>プロパティで指定した回数分画面を更新しても、期待する文字列が出現しない場合、{@link Assert#fail(String)}する。
     * </p>
     * @param by 要素を探すための識別子
     * @param expected 期待する文字列
     */
    private void tryAssertAndRefreshUntilReceiving(By by, String expected) {
        for (int i = 0; i < retryCount; i++) {
            if (expected.equals(webDriverOperations.getText(by))) {
                return;
            }

            webDriverOperations.suspend(retryInterval, TimeUnit.MILLISECONDS);

            // 画面の再読み込み
            webDriverOperations.refresh();
        }
        fail("not found the expected text while executing the specified number of times.");
    }
}
