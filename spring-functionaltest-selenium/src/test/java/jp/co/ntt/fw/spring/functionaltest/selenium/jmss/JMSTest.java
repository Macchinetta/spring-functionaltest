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
package jp.co.ntt.fw.spring.functionaltest.selenium.jmss;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.openqa.selenium.By.id;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = {"jsp"})
public class JMSTest extends FunctionTestSupport {

    @Value("${selenium.jmss.retryForExpectedString.interval}")
    private int retryInterval;

    @Value("${selenium.jmss.retryForExpectedString.max}")
    private int retryCount;

    @After
    public void clear() {
        restOperations.getForEntity(applicationContextUrl + "/jmss/clear", Void.class);
    }

    /**
     * JMSS0101001
     * <ul>
     * <li>ActiveMQ固有の定義でコネクションファクトリを取得し、メッセージが送受信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0101001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0101001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));

    }

    /**
     * JMSS0102001
     * <ul>
     * <li>JNDI名による名前解決でコネクションファクトリを取得し、メッセージが送受信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0102001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0102001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));

    }

    /**
     * JMSS0103001
     * <ul>
     * <li>CachingConnectionFactoryを使用し、メッセージが送受信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0103001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0103001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));

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

    /**
     * JMSS0301001
     * <ul>
     * <li>JmsTemplateを使用し、JMSのMessageが送信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0301001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0301001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));

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

    /**
     * JMSS0301003
     * <ul>
     * <li>JmsMessagingTemplateのsendメソッドを使用しメッセージ送信ができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0301003() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0301003"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));

    }

    /**
     * JMSS0301004
     * <ul>
     * <li>JmsMessagingTemplateのsendメソッドを使用しトピックにメッセージ送信ができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0301004() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0301004"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                "All of the subscriber has been received.");

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")),
                is("All of the subscriber has been received."));
    }

    /**
     * JMSS0302001
     * <ul>
     * <li>同一メッセージの大量送信の動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0302001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0302001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("receiveCount"), "3");

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("receiveCount")), is("3"));
    }

    /**
     * JMSS0303001
     * <ul>
     * <li>メッセージヘッダ(デリバリモード、優先度、TTL)を編集して送信した際の動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0303001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0303001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));
    }

    /**
     * JMSS0303002
     * <ul>
     * <li>メッセージヘッダ(デリバリモード、優先度、TTL)を編集して送信した際の動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0303002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0303002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // クリックする要素が出現するまで待機
        webDriverOperations.setDefaultTimeoutForImplicitlyWait(30);
        webDriverOperations
                .waitForDisplayed(ExpectedConditions.elementToBeClickable(By.id("receiveMessage")));

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is("Not Received!"));
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

    /**
     * JMSS0401002
     * <ul>
     * <li>selectorによりメッセージの絞込みができることを確認する。</li>
     * </ul>
     */
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
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is("Not Received!"));
    }

    /**
     * JMSS0401003
     * <ul>
     * <li>selectorによりメッセージの絞込みができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0401003() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0401003"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));

    }

    /**
     * JMSS0402001
     * <ul>
     * <li>@SendToにより、メッセージが送信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0402001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0402001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));
    }

    /**
     * JMSS0403001
     * <ul>
     * <li>JmsResponseにより、メッセージが送信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0403001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0403001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));
        assertThat(webDriverOperations.getText(id("receiveQueue")), is("TestQueue0403001B"));

    }

    /**
     * JMSS0403002
     * <ul>
     * <li>JmsResponseにより、メッセージが送信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0403002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0403002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));
        assertThat(webDriverOperations.getText(id("receiveQueue")), is("TestQueue0403001C"));
    }

    /**
     * JMSS403003
     * <ul>
     * <li>JmsResponseにより、メッセージが送信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0403003() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0403003"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));
        assertThat(webDriverOperations.getText(id("receiveQueue")), is("TestQueue0403003B"));
    }

    /**
     * JMSS0404001
     * <ul>
     * <li>Concurrencyの設定によるマルチスレッドの動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0404001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0404001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(checkThreadIdAssertion(false, identifier), is(true));
    }

    /**
     * JMSS0404002
     * <ul>
     * <li>Concurrencyの設定によるマルチスレッドの動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0404002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0404002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(checkThreadIdAssertion(true, identifier), is(true));
    }

    /**
     * JMSS0501001
     * <ul>
     * <li>メッセージの同期受信の動作を確認する。</li>
     * </ul>
     */
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

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));
    }

    /**
     * JMSS0601001
     * <ul>
     * <li>送信時のトランザクション管理について動作を確認する。</li>
     * </ul>
     */
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

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));

    }

    /**
     * JMSS0601002
     * <ul>
     * <li>送信時のトランザクション管理について動作を確認する。</li>
     * </ul>
     */
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
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is("Not Received!"));

    }

    /**
     * JMSS0602001
     * <ul>
     * <li>同期受信時のトランザクション管理について動作を確認する。</li>
     * </ul>
     */
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

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));

    }

    /**
     * JMSS0602002
     * <ul>
     * <li>同期受信時のトランザクション管理について動作を確認する。</li>
     * </ul>
     */
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
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is("Not Received!"));

    }

    /**
     * JMSS0603001
     * <ul>
     * <li>@JmsListenerを使用したときのトランザクション管理について動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0603001() {
        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0603001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));

    }

    /**
     * JMSS0603002
     * <ul>
     * <li>@JmsListenerを使用したときのトランザクション管理について動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0603002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0603002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(
                applicationContextUrl + "/jmss/await?jmsTodoId=" + "rollbacked_" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), "rollbacked_" + identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")),
                is("rollbacked_" + identifier));

    }

    /**
     * JMSS0604001
     * <ul>
     * <li>送信時のJMSとDBの両方使用したときのトランザクション管理について動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0604001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0604001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));

    }

    /**
     * JMSS0604002
     * <ul>
     * <li>送信時のJMSとDBの両方使用したときのトランザクション管理について動作を確認する。</li>
     * </ul>
     */
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
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")),
                is("rollbacked_MQandDB_" + identifier));

    }

    /**
     * JMSS0604005
     * <ul>
     * <li>非同期受信時のJMSとDBの両方使用したときのトランザクション管理について動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0604005() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0604005"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));

    }

    /**
     * JMSS0604006
     * <ul>
     * <li>非同期受信時のJMSとDBの両方使用したときのトランザクション管理について動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0604006() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0604006"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(
                applicationContextUrl + "/jmss/await?jmsTodoId=" + "rollbacked_" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                "rollbacked_MQ_" + identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")),
                is("rollbacked_MQ_" + identifier));
    }

    /**
     * JMSS0701001
     * <ul>
     * <li>受信データの入力チェック</li>
     * </ul>
     */
    @Test
    public void testJMSS0701001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));
    }

    /**
     * JMSS0701002
     * <ul>
     * <li>受信データの入力チェック</li>
     * </ul>
     */
    @Test
    public void testJMSS0701002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                "Validated!! :" + identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")),
                is("Validated!! :" + identifier));
    }

    /**
     * JMSS0701003
     * <ul>
     * <li>受信データの入力チェック</li>
     * </ul>
     */
    @Test
    public void testJMSS0701003() {
        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701003"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));
    }

    /**
     * JMSS0701004
     * <ul>
     * <li>受信データの入力チェック</li>
     * </ul>
     */
    @Test
    public void testJMSS0701004() {
        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701004"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));
    }

    /**
     * JMSS0701005
     * <ul>
     * <li>受信データの入力チェック</li>
     * </ul>
     */
    @Test
    public void testJMSS0701005() {
        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701005"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));
    }

    /**
     * JMSS0701006
     * <ul>
     * <li>受信データの入力チェック</li>
     * </ul>
     */
    @Test
    public void testJMSS0701006() {
        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701006"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));
    }

    /**
     * JMSS0701007
     * <ul>
     * <li>受信データの入力チェック</li>
     * </ul>
     */
    @Test
    public void testJMSS0701007() {
        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701007"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                "rollbacked_DB_" + identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")),
                is("rollbacked_DB_" + identifier));
    }

    /**
     * JMSS0701008
     * <ul>
     * <li>受信データの入力チェック</li>
     * </ul>
     */
    @Test
    public void testJMSS0701008() {
        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0701008"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                "not_rollbacked_MQandDB_" + identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")),
                is("not_rollbacked_MQandDB_" + identifier));
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

    /**
     * JMSS0802001
     * <ul>
     * <li>非同期受信メソッド内で例外をスローしたときの例外処理について動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0802001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0802001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        // エラーログが出力されるよう待機
        dbLogAssertOperations.waitForAssertion();

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage(null, null, "SystemException Error.*");
    }

    /**
     * JMSS0803001
     * <ul>
     * <li>非同期受信メソッド内で例外をスローしたときのtry-catchによる例外処理が行われているか確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0803001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0803001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        // エラーログが出力されるよう待機
        dbLogAssertOperations.waitForAssertion();

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage(null, null,
                "ResultMessage \\[code=e.sf.js.8004");
    }

    /**
     * JMSS0803002
     * <ul>
     * <li>非同期受信メソッド内で例外をスローしたときのtry-catchによる例外処理が行われているか確認する。</li>
     * </ul>
     */
    @Test
    public void testJMSS0803002() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0803002"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"),
                "BusinessException! :" + identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")),
                is("BusinessException! :" + identifier));
    }

    /**
     * JMSS0901001
     * <ul>
     * <li>サイズの大きなデータの送受信</li>
     * </ul>
     */
    @Test
    public void testJMSS0901001() {

        String identifier = UUID.randomUUID().toString();

        // メニュー画面の操作
        webDriverOperations.click(id("jmss0901001"));

        // メッセージの送信
        webDriverOperations.overrideText(id("jmsTodoId"), identifier);
        webDriverOperations.click(id("sendMessage"));

        // RestTemplate で受信するまで待機する
        restOperations.getForEntity(applicationContextUrl + "/jmss/await?jmsTodoId=" + identifier,
                Void.class);

        // 結果の取得
        webDriverOperations.click(id("receiveMessage"));

        this.tryAssertAndRefreshUntilReceiving(id("uniqueIdentifier"), identifier);

        // メッセージ受信内容の確認
        assertThat(webDriverOperations.getText(id("uniqueIdentifier")), is(identifier));
    }

    /**
     * ThreadIDに関するAssert(testJMSS0404001, testJMSS0404002)
     * @param concurrent
     * @param identifier
     * @return
     */
    private boolean checkThreadIdAssertion(boolean concurrentMultiple, String identifier) {

        boolean assertion = true;

        List<String> threadIdLogs = dbLogAssertOperations.getLogByRegexMessage(null, null,
                "Message : " + identifier + ", ConcurrentThreadID is ..*");
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
