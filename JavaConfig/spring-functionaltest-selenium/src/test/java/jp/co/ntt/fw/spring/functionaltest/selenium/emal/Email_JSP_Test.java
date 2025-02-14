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
package jp.co.ntt.fw.spring.functionaltest.selenium.emal;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import java.io.IOException;
import java.util.UUID;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.IfProfileValue;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class Email_JSP_Test extends FunctionTestSupport {

    @Value("${mail.noauth.pop3.host}")
    private String mailNoauthPop3Host;

    @Value("${mail.noauth.pop3.port}")
    private String mailNoauthPop3Port;

    @Value("${selenium.app.noauth.email.domain}")
    private String mailNoauthDomain;

    @Value("${mail.auth.pop3.host}")
    private String mailAuthPop3Host;

    @Value("${mail.auth.pop3.port}")
    private String mailAuthPop3Port;

    @Value("${selenium.app.auth.email.domain}")
    private String mailAuthDomain;

    @Value("${mail.noauth.from.address}")
    private String from;

    private static final String VIEW_TYPE = "jsp";

    /**
     * <ul>
     * <li>APサーバのメールセッションを使用して、メール送信できることを確認する。</li>
     * <li>SimpleMailMessageを使用してto、本文に値を設定し、メール送信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEMAL0201001() {

        String identifier = UUID.randomUUID().toString();
        String text = "Hello!" + "<!-- " + identifier + " -->";

        // メニュー画面の操作
        webDriverOperations.click(id("emal0201001_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("text"), text);
        webDriverOperations.click(id("sendMail"));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("from")), is(from));
        assertThat(webDriverOperations.getText(id("subject")), is("Registration confirmation."));
        assertThat(webDriverOperations.getText(id("to")), is("hoge@" + mailNoauthDomain));
        assertThat(webDriverOperations.getText(id("body")), is(text));
    }

    /**
     * <ul>
     * <li>APサーバのメールセッションを使用せず、認証なしの場合に、メール送信できることを確認する。</li>
     * <li>SimpleMailMessageを使用して設定可能なプロパティをすべて設定した場合に、メール送信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEMAL0201002() {

        String identifier = UUID.randomUUID().toString();
        String text = "Hello!" + "<!-- " + identifier + " -->";

        // メニュー画面の操作
        webDriverOperations.click(id("emal0201002_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("cc0"), "foo@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("bcc0"), "bar@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("replyTo"), "reply@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("text"), text);
        webDriverOperations.click(id("sendMail"));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        // GreenMail用（1件受信してチェック）
        assertThat(webDriverOperations.getText(id("from")), is(from));
        assertThat(webDriverOperations.getText(id("subject")), is("Registration confirmation."));
        assertThat(webDriverOperations.getText(id("to")), is("hoge@" + mailNoauthDomain));
        assertThat(webDriverOperations.getText(id("cc")), is("foo@" + mailNoauthDomain));
        assertThat(webDriverOperations.getText(id("replyTo")), is("reply@" + mailNoauthDomain));
        assertThat(webDriverOperations.getText(id("date")), startsWith("2015/07/06"));
        assertThat(webDriverOperations.getText(id("body")), is(text));

    }

    /**
     * <ul>
     * <li>APサーバのメールセッションを使用せず、認証なしの場合に、メール送信できることを確認する。</li>
     * <li>SimpleMailMessageを使用して設定可能なプロパティをすべて設定した場合に、メール送信できることを確認する。</li>
     * </ul>
     */
    @IfProfileValue(name = "test.environment", value = "mailServer")
    @Test
    public void testEMAL0201003() {

        String identifier = UUID.randomUUID().toString();
        String text = "Hello!" + "<!-- " + identifier + " -->";

        // メニュー画面の操作
        webDriverOperations.click(id("emal0201002_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("cc0"), "foo@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("bcc0"), "bar@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("replyTo"), "reply@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("text"), text);
        webDriverOperations.click(id("sendMail"));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("from")), is(from));
        assertThat(webDriverOperations.getText(id("subject")), is("Registration confirmation."));
        assertThat(webDriverOperations.getText(id("to")), is("hoge@" + mailNoauthDomain));
        assertThat(webDriverOperations.getText(id("cc")), is("foo@" + mailNoauthDomain));
        assertThat(webDriverOperations.getText(id("replyTo")), is("reply@" + mailNoauthDomain));
        assertThat(webDriverOperations.getText(id("date")), startsWith("2015/07/06"));
        assertThat(webDriverOperations.getText(id("body")), is(text));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "foo");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        assertThat(webDriverOperations.getText(id("body")), is(text));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "bar");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("body")), is(text));
    }

    /**
     * <ul>
     * <li>SimpleMailMessageを使用して複数の宛先を設定した場合に、メール送信できることを確認する。</li>
     * </ul>
     */
    @IfProfileValue(name = "test.environment", value = "nonMailServer")
    @Test
    public void testEMAL0201004() {

        String identifier = UUID.randomUUID().toString();
        String text = "Hello!" + "<!-- " + identifier + " -->";

        // メニュー画面の操作
        webDriverOperations.click(id("emal0201003_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("to1"), "hoge2@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("cc0"), "foo@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("cc1"), "foo2@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("bcc0"), "bar@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("bcc1"), "bar2@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("text"), text);
        webDriverOperations.click(id("sendMail"));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        // GreenMail用（1件受信してチェック）
        assertThat(webDriverOperations.getText(id("body")), is(text));

    }

    /**
     * <ul>
     * <li>APサーバのメールセッションを使用せず、認証ありの場合に、メール送信できることを確認する。</li>
     * <li>SimpleMailMessageを使用して複数の宛先を設定した場合に、メール送信できることを確認する。</li>
     * </ul>
     */
    @IfProfileValue(name = "test.environment", value = "mailServer")
    @Test
    public void testEMAL0201005() {

        String identifier = UUID.randomUUID().toString();
        String text = "Hello!" + "<!-- " + identifier + " -->";

        // メニュー画面の操作
        webDriverOperations.click(id("emal0201003_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailAuthDomain);
        webDriverOperations.overrideText(id("to1"), "hoge2@" + mailAuthDomain);
        webDriverOperations.overrideText(id("cc0"), "foo@" + mailAuthDomain);
        webDriverOperations.overrideText(id("cc1"), "foo2@" + mailAuthDomain);
        webDriverOperations.overrideText(id("bcc0"), "bar@" + mailAuthDomain);
        webDriverOperations.overrideText(id("bcc1"), "bar2@" + mailAuthDomain);
        webDriverOperations.overrideText(id("text"), text);
        webDriverOperations.click(id("sendMail"));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailAuthPop3Host);
        webDriverOperations.overrideText(id("port"), mailAuthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("body")), is(text));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailAuthPop3Host);
        webDriverOperations.overrideText(id("port"), mailAuthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge2");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("body")), is(text));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailAuthPop3Host);
        webDriverOperations.overrideText(id("port"), mailAuthPop3Port);
        webDriverOperations.overrideText(id("user"), "foo");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("body")), is(text));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailAuthPop3Host);
        webDriverOperations.overrideText(id("port"), mailAuthPop3Port);
        webDriverOperations.overrideText(id("user"), "foo2");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("body")), is(text));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailAuthPop3Host);
        webDriverOperations.overrideText(id("port"), mailAuthPop3Port);
        webDriverOperations.overrideText(id("user"), "bar");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("body")), is(text));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailAuthPop3Host);
        webDriverOperations.overrideText(id("port"), mailAuthPop3Port);
        webDriverOperations.overrideText(id("user"), "bar2");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("body")), is(text));
    }

    /**
     * <ul>
     * <li>MimeMessageを使用してテキストメールが送信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEMAL0301001() {

        String identifier = UUID.randomUUID().toString();
        String text = "あいう①～③" + "<!-- " + identifier + " -->";

        // メニュー画面の操作
        webDriverOperations.click(id("emal0301001_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("text"), text);
        webDriverOperations.click(id("sendMail"));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("from")), is("\"髙山\" <" + from + ">"));
        assertThat(webDriverOperations.getText(id("subject")), is("お知らせ①"));
        assertThat(webDriverOperations.getText(id("to")), is("hoge@" + mailNoauthDomain));
        assertThat(webDriverOperations.getText(id("body")), is(text));
        assertThat(webDriverOperations.getText(id("contentType")), containsString("text/plain"));
    }

    /**
     * <ul>
     * <li>MimeMessageを使用してHTMLメールが送信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEMAL0302001() {

        String identifier = UUID.randomUUID().toString();
        String text = "<html><body>あいう①～③</body></html>" + "<!-- " + identifier + " -->";

        // メニュー画面の操作
        webDriverOperations.click(id("emal0302001_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("text"), text);
        webDriverOperations.click(id("sendMail"));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("from")), is("\"髙山\" <" + from + ">"));
        assertThat(webDriverOperations.getText(id("subject")), is("お知らせ①"));
        assertThat(webDriverOperations.getText(id("to")), is("hoge@" + mailNoauthDomain));
        assertThat(webDriverOperations.getText(id("body")), is(text));
        assertThat(webDriverOperations.getText(id("contentType")), containsString("text/html"));
    }

    /**
     * <ul>
     * <li>MimeMessageを使用して添付ファイル付きメールが送信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEMAL0303001() throws IOException {

        String identifier = UUID.randomUUID().toString();
        String text = "あいう①～③" + "<!-- " + identifier + " -->";

        // メニュー画面の操作
        webDriverOperations.click(id("emal0303001_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("text"), text);
        webDriverOperations.overrideText(id("filename"), "説明①.txt");
        webDriverOperations.referUploadFile(id("multipartFile"),
                new ClassPathResource("testdata/emal/添付ファイル.txt").getFile());
        webDriverOperations.click(id("sendMail"));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("from")), is("\"髙山\" <" + from + ">"));
        assertThat(webDriverOperations.getText(id("subject")), is("お知らせ①"));
        assertThat(webDriverOperations.getText(id("to")), is("hoge@" + mailNoauthDomain));
        assertThat(webDriverOperations.getText(id("body")), is(text));
        assertThat(webDriverOperations.getText(id("attachment")), is("説明①.txt"));
    }

    /**
     * <ul>
     * <li>MimeMessageを使用してインラインリソース付きメールが送信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEMAL0304001() throws IOException {

        String identifier = UUID.randomUUID().toString();
        String text = "<html><head><link rel=\"stylesheet\" href='cid:identifier1234'>"
                + "</head><body>あいう①～③</body></html>" + "<!-- " + identifier + " -->";

        // メニュー画面の操作
        webDriverOperations.click(id("emal0304001_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("text"), text);
        webDriverOperations.overrideText(id("cid"), "identifier1234");
        webDriverOperations.referUploadFile(id("multipartFile"),
                new ClassPathResource("testdata/emal/inline.css").getFile());
        webDriverOperations.click(id("sendMail"));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("from")), is("\"髙山\" <" + from + ">"));
        assertThat(webDriverOperations.getText(id("subject")), is("お知らせ①"));
        assertThat(webDriverOperations.getText(id("to")), is("hoge@" + mailNoauthDomain));
        assertThat(webDriverOperations.getText(id("body")), is(text));
        assertThat(webDriverOperations.getText(id("inline")), is("<identifier1234>"));
    }

    /**
     * <ul>
     * <li>SMTP認証エラー時にMailAuthenticationExceptionがスローされることを確認する。</li>
     * </ul>
     */
    @IfProfileValue(name = "test.environment", value = "mailServer")
    @Test
    public void testEMAL0401001() {

        // メニュー画面の操作
        webDriverOperations.click(id("emal0401001_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("text"), "Hello!");
        webDriverOperations.click(id("sendMail"));

        webDriverOperations.waitForDisplayed(textToBe(By.xpath("//h1"), "System Error!"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        webDriverOperations.saveScreenCapture();
    }

    /**
     * <ul>
     * <li>メールメッセージのプロパティに不正な値が設定されている場合にMailParseExceptionがスローされることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEMAL0401002() {

        // メニュー画面の操作
        webDriverOperations.click(id("emal0401002_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@example..com");
        webDriverOperations.overrideText(id("text"), "Hello!");
        webDriverOperations.click(id("sendMail"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        webDriverOperations.saveScreenCapture();
    }

    /**
     * <ul>
     * <li>メールメッセージを作成中に想定外のエラーが起きた場合にMailPreparationExceptionがスローされることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEMAL0401003() {

        // メニュー画面の操作
        webDriverOperations.click(id("emal0401003_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("text"), "Hello!");
        webDriverOperations.select(id("templateName"), "non-existence");
        webDriverOperations.click(id("sendMail"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        webDriverOperations.saveScreenCapture();
    }

    /**
     * <ul>
     * <li>メールの送信エラーが起きた場合にMailSendExceptionがスローされることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEMAL0401004() {

        // メニュー画面の操作
        webDriverOperations.click(id("emal0401004_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("text"), "Hello!");
        webDriverOperations.click(id("sendMail"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        webDriverOperations.saveScreenCapture();
    }

    /**
     * <ul>
     * <li>FreeMarkerを使用してメール本文が作成できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEMAL0501001() {

        String identifier = UUID.randomUUID().toString();
        String text = "<script>alert(\"あいう①～③\");</script>" + "<!-- " + identifier + " -->";

        // メニュー画面の操作
        webDriverOperations.click(id("emal0501001_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("text"), text);
        webDriverOperations.select(id("templateName"), "registration-confirmation");
        webDriverOperations.click(id("sendMail"));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("from")), is("\"髙山\" <" + from + ">"));
        assertThat(webDriverOperations.getText(id("subject")), is("お知らせ①"));
        assertThat(webDriverOperations.getText(id("to")), is("hoge@" + mailNoauthDomain));
        String expectedBodyPattern = "<html>\\s*<body>\\s*"
                + "<h3>Hi &lt;script&gt;alert\\(&quot;あいう①～③&quot;\\);&lt;/script&gt;" + ".*"
                + identifier + ".*" + ", welcome to Macchinetta\\.github\\.io!</h3>\\s*"
                + "</body>\\s*</html>";
        assertTrue(webDriverOperations.getText(id("body")).matches(expectedBodyPattern));
        assertThat(webDriverOperations.getText(id("contentType")), containsString("text/html"));
    }

    /**
     * <ul>
     * <li>ガイドライン記載のISO-2022-JP文字化け対象7文字が変換処理で代替文字に変換してメール送信できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEMAL0601001() {

        String identifier = UUID.randomUUID().toString();
        String iso2022jpBadChars = "―－～∥￠￡￢";
        String text = iso2022jpBadChars + "<!-- " + identifier + " -->";

        // メニュー画面の操作
        webDriverOperations.click(id("emal0601001_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"),
                "\"" + iso2022jpBadChars + "\" <hoge@" + mailNoauthDomain + ">");
        webDriverOperations.overrideText(id("text"), text);
        webDriverOperations.click(id("sendMail"));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        String expectedConverted = "—−〜‖¢£¬";
        assertThat(webDriverOperations.getText(id("from")),
                is("\"" + expectedConverted + "\" <" + from + ">"));
        assertThat(webDriverOperations.getText(id("subject")), is(expectedConverted));
        assertThat(webDriverOperations.getText(id("to")),
                is("\"" + expectedConverted + "\" <hoge@" + mailNoauthDomain + ">"));
        assertThat(webDriverOperations.getText(id("body")),
                is(expectedConverted + "<!-- " + identifier + " -->"));
    }

    /**
     * <ul>
     * <li>x-windows-iso2022jpを使用してISO-2022-JPの範囲外となる拡張文字をメール送信できることを確認する。</li>
     * </ul>
     */
    @Test
    @Ignore
    public void testEMAL0601002() {

        String identifier = UUID.randomUUID().toString();
        String externalChars = "㈱ｻﾝﾌﾟﾙ～①";
        String text = externalChars + "<!-- " + identifier + " -->";

        // メニュー画面の操作
        webDriverOperations.click(id("emal0601002_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"),
                "\"" + externalChars + "\" <hoge@" + mailNoauthDomain + ">");
        webDriverOperations.overrideText(id("text"), text);
        webDriverOperations.click(id("sendMail"));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("from")),
                is("\"" + externalChars + "\" <" + from + ">"));
        assertThat(webDriverOperations.getText(id("subject")), is(externalChars));
        assertThat(webDriverOperations.getText(id("to")),
                is("\"" + externalChars + "\" <hoge@" + mailNoauthDomain + ">"));
        assertThat(webDriverOperations.getText(id("body")), is(text));
    }

    /**
     * <ul>
     * <li>JNDI参照でsessionを作成し、メールが送信できることを確認する。</li>
     * <li>JakartaEEアプリケーションが予期する"java:comp/env/"プレフィックスを付与して参照を行う。</li>
     * </ul>
     */
    @Test
    public void testEMAL0701001() {

        String identifier = UUID.randomUUID().toString();
        String text = "Hello!" + "<!-- " + identifier + " -->";

        // メニュー画面の操作
        webDriverOperations.click(id("emal0701001_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("text"), text);
        webDriverOperations.click(id("sendMail"));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("from")), is(from));
        assertThat(webDriverOperations.getText(id("subject")), is("JNDI Lookup(Container Prefix)"));
        assertThat(webDriverOperations.getText(id("to")), is("hoge@" + mailNoauthDomain));
        assertThat(webDriverOperations.getText(id("body")), is(text));
    }

    /**
     * <ul>
     * <li>JNDI参照でsessionを作成し、メールが送信できることを確認する。</li>
     * <li>文字列をもとにネーミングコンテキストに直接参照を行う。</li>
     * </ul>
     */
    @Test
    public void testEMAL0701002() {

        String identifier = UUID.randomUUID().toString();
        String text = "Hello!" + "<!-- " + identifier + " -->";

        // メニュー画面の操作
        webDriverOperations.click(id("emal0701002_" + VIEW_TYPE));

        // メールの送信
        webDriverOperations.overrideText(id("to0"), "hoge@" + mailNoauthDomain);
        webDriverOperations.overrideText(id("text"), text);
        webDriverOperations.click(id("sendMail"));

        // メールの受信
        webDriverOperations.overrideText(id("host"), mailNoauthPop3Host);
        webDriverOperations.overrideText(id("port"), mailNoauthPop3Port);
        webDriverOperations.overrideText(id("user"), "hoge");
        webDriverOperations.overrideText(id("password"), "Ntt01");
        webDriverOperations.overrideText(id("identifier"), identifier);
        webDriverOperations.click(id("receiveMail"));

        // 送信内容（＝受信内容）の確認
        assertThat(webDriverOperations.getText(id("from")), is(from));
        assertThat(webDriverOperations.getText(id("subject")), is("JNDI Lookup(Origin)"));
        assertThat(webDriverOperations.getText(id("to")), is("hoge@" + mailNoauthDomain));
        assertThat(webDriverOperations.getText(id("body")), is(text));
    }
}
