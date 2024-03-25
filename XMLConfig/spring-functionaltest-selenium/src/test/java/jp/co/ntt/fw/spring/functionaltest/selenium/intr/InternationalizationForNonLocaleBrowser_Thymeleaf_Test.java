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
package jp.co.ntt.fw.spring.functionaltest.selenium.intr;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.By.id;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class InternationalizationForNonLocaleBrowser_Thymeleaf_Test extends
                                                         FunctionTestSupport {

    private static WebDriver noLocaleDriver;

    private static String VIEW_TYPE = "thymeleaf";

    public InternationalizationForNonLocaleBrowser_Thymeleaf_Test() {
        disableDefaultWebDriver();
    }

    @Before
    public void setUpWebDriver() {
        if (noLocaleDriver == null) {
            noLocaleDriver = webDriverCreator.createLocaleSpecifiedDriver("");
        }
        setCurrentWebDriver(noLocaleDriver);
    }

    /**
     * <ul>
     * <li>Springのロケールを解決する機能をBean定義した場合、JVMとブラウザのロケールでは、 <br>
     * ブラウザのロケールが優先されることを確認、 <br>
     * マルチバイト文字が設定値どおり出力可能なことを確認する。</li>
     * </ul>
     */
    @Test
    public void testINTR0101001() {
        // Locale 無し
        webDriverOperations.click(id("intr0101001_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));
        assertThat(webDriverOperations.getText(id("ageValue")), is("⑳"));

        webDriverOperations.saveScreenCapture("ForNonLocaleBrowser");
    }

    /**
     * <ul>
     * <li>Springのロケールを解決する機能をBean定義を省略した場合、<br/>
     * JVMとブラウザのロケールでは、ブラウザのロケールが優先されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testINTR0101002() {
        // Locale 無し
        webDriverOperations.click(id("intr0101002_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

        webDriverOperations.saveScreenCapture("ForNonLocaleBrowser");

    }

    /**
     * <ul>
     * <li>Springのロケールを解決する機能にサポートロケールとデフォルトロケールを定義し、<br/>
     * ロケールを指定しない場合、デフォルトロケールが使用されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testINTR0101006() {
        // Locale 無し
        webDriverOperations.click(id("intr0101006_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

    }

    /**
     * <ul>
     * <li>ブラウザのロケールは未設定、JVMのロケール、WebサーバのOSのロケールが異なる場合、<br/>
     * JVMのに設定されたロケールで値が表示されることの確認。</li>
     * </ul>
     */
    @Ignore("【手動実行】 WebサーバのJVMのロケールを'en'に変更する必要があるため。")
    @Test
    public void testINTR0102002() {
        // Localなし
        webDriverOperations.click(id("intr0102002_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));
        assertThat(webDriverOperations.getText(id("ageValue")), is("20"));

    }

    /**
     * <ul>
     * <li>ブラウザのロケールは未設定、JVMのロケール未設定の場合、WebサーバのOSのロケールで値が表示されることの確認。</li>
     * </ul>
     */
    @Test
    public void testINTR0102003() {
        // Localなし
        webDriverOperations.click(id("intr0102003_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

    }

    /**
     * <ul>
     * <li>リクエストパラメータに指定したロケールがセッションに保存され、リクエストされたロケールが有効になることの確認</li>
     * </ul>
     */
    @Test
    public void testINTR0201001() {
        // Locale 無し
        webDriverOperations.click(id("intr0201001_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

        webDriverOperations.saveScreenCapture();

        // クエリに英語ロケールを指定し、遷移する
        String localeAddURL = webDriverOperations.getCurrentUrl()
                + "?locale=en";
        webDriverOperations.getWebDriver().get(localeAddURL);

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

        // リクエストパラメータで送ったlocaleの値になっていること
        assertThat(webDriverOperations.getText(id("sessionLocale")), is("en"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("intr0201001_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

        // セッションに設定した値が有効になっていること
        assertThat(webDriverOperations.getText(id("sessionLocale")), is("en"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());

        // ブラウザのセッションIDを削除
        webDriverOperations.deleteCookie("JSESSIONID");

    }

    /**
     * <ul>
     * <li>リクエストパラメータのキー名を変更した場合、ロケールがセッションに保存され、リクエストされたロケールが有効になることの確認</li>
     * </ul>
     */
    @Test
    public void testINTR0201002() {
        // Locale 無し
        webDriverOperations.click(id("intr0201002_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

        webDriverOperations.saveScreenCapture();

        // クエリに英語ロケールを指定し、遷移する
        String localeAddURL = webDriverOperations.getCurrentUrl() + "?lang=en";
        webDriverOperations.getWebDriver().get(localeAddURL);

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

        // リクエストパラメータで送ったlocaleの値になっていること
        assertThat(webDriverOperations.getText(id("sessionLocale")), is("en"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("intr0201002_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

        // セッションに設定した値が有効になっていること
        assertThat(webDriverOperations.getText(id("sessionLocale")), is("en"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());

        // ブラウザのセッションIDを削除
        webDriverOperations.deleteCookie("JSESSIONID");

    }

    /**
     * <ul>
     * <li>Springのロケールリゾルバに定義したデフォルトロケールを使用し、リクエストパラメータにロケールを指定しなかった場合、デフォルトロケールが有効になることの確認</li>
     * </ul>
     */
    @Test
    public void testINTR0201003() {
        // Locale 無し
        webDriverOperations.click(id("intr0201003_" + VIEW_TYPE));

        // 出力メッセージの確認(デフォルトローケル)
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

        webDriverOperations.saveScreenCapture();

        // クエリに日本語ロケールを指定し、遷移する
        String localeAddURL = webDriverOperations.getCurrentUrl()
                + "?locale=ja";
        webDriverOperations.getWebDriver().get(localeAddURL);

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

        // リクエストパラメータで送ったlocaleの値になっていること
        assertThat(webDriverOperations.getText(id("sessionLocale")), is("ja"));

        // ブラウザのセッションIDを削除
        webDriverOperations.deleteCookie("JSESSIONID");
    }

    /**
     * <ul>
     * <li>リクエストパラメータに指定したロケールがセッションタイムアウトにより破棄された場合、OSロケールが有効になることの確認</li>
     * </ul>
     */
    @Test
    public void testINTR0201004() {
        // Locale 無し
        webDriverOperations.click(id("intr0201004_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

        webDriverOperations.saveScreenCapture();

        // クエリに英語ロケールを指定し、遷移する
        String localeAddURL = webDriverOperations.getCurrentUrl()
                + "?locale=en";
        webDriverOperations.getWebDriver().get(localeAddURL);

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

        // リクエストパラメータで送ったlocaleの値になっていること
        assertThat(webDriverOperations.getText(id("sessionLocale")), is("en"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());

        // セッションに格納されたロケールを削除
        webDriverOperations.click(id("removeLocale"));

        webDriverOperations.click(id("intr0201004_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

        // ブラウザのセッションIDを削除
        webDriverOperations.deleteCookie("JSESSIONID");
    }

    /**
     * <ul>
     * <li>存在しないロケールを指定した場合、ロケールがセッションに保存され、デフォルトプロパティから値が取得されることの確認</li>
     * </ul>
     */
    @Test
    public void testINTR0201005() {
        // Locale 無し
        webDriverOperations.click(id("intr0201005_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

        webDriverOperations.saveScreenCapture();

        // クエリに存在しないロケールを指定し、遷移する
        String localeAddURL = webDriverOperations.getCurrentUrl()
                + "?locale=xw";
        webDriverOperations.getWebDriver().get(localeAddURL);

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

        // リクエストパラメータで送ったlocaleの値になっていること
        assertThat(webDriverOperations.getText(id("sessionLocale")), is("xw"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());

        // ブラウザのセッションIDを削除
        webDriverOperations.deleteCookie("JSESSIONID");

    }

    /**
     * <ul>
     * <li>画面操作でロケールを変更した場合、ロケールがsessionに保存され、プロパティから値が取得されることの確認</li>
     * </ul>
     */
    @Test
    public void testINTR0201006() {
        // Locale 無し
        webDriverOperations.click(id("intr0201006_" + VIEW_TYPE));

        // 英語に変更
        webDriverOperations.click(id("english"));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("changeMessage")), is(
                "changed locale."));

        // 確認画面に遷移
        webDriverOperations.click(id("check"));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("checkMessage")), is(
                "Confirm change of locale on next screen"));
        webDriverOperations.saveScreenCapture();

        // back to /intr
        webDriverOperations.click(id("top"));

        // Locale 無し
        webDriverOperations.click(id("intr0202006_" + VIEW_TYPE));

        // 日本語に変更
        webDriverOperations.click(id("japanese"));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("changeMessage")), is(
                "ロケールを変更しました。"));

        // 確認画面に遷移
        webDriverOperations.click(id("check"));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("checkMessage")), is(
                "次の画面でのロケール変更を確認"));

        // ブラウザのセッションIDを削除
        webDriverOperations.deleteCookie("JSESSIONID");

    }

    /**
     * <ul>
     * <li>ignoreInvalidLocaleプロパティにfalseを設定する場合、無効なロケールのリクエストでエラーが発生することの確認</li>
     * </ul>
     */
    // Thymeleaf3.1より、#requestが使えなくなりX-Trackを画面上で取得できなくなった
    // このテストではControllerを介さずにエラー画面へ遷移するのでModelにX-Trackを格納できないためjspのみ実施する
    @Ignore("JSP専用試験、削除するとJSP用seleniumと差分が出るため@Ignoreで残す")
    @Test
    public void testINTR0201007() {
        {
            // Locale 無し
            webDriverOperations.click(id("intr0201007_" + VIEW_TYPE));

            // 無効なロケールに変更
            webDriverOperations.click(id("invalidLocale"));

            // 確認画面に遷移
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                    webDriverOperations.getXTrack(), null,
                    "Locale part \"j@\" contains invalid characters",
                    "java\\.lang\\.IllegalArgumentException");
        }

        // ブラウザのセッションIDを削除
        webDriverOperations.deleteCookie("JSESSIONID");

    }

    /**
     * <ul>
     * <li>ignoreInvalidLocaleプロパティにtrueを設定する場合、無効なロケールのリクエストでエラーが発生しないことの確認</li>
     * </ul>
     */
    @Test
    public void testINTR0201008() {
        // Locale 無し
        webDriverOperations.click(id("intr0201008_" + VIEW_TYPE));

        // 日本語に変更
        webDriverOperations.click(id("japanese"));

        // 無効なロケールに変更
        webDriverOperations.click(id("invalidLocale"));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("changeMessage")), is(
                "ロケールを変更しました。"));

        // 確認画面に遷移
        webDriverOperations.click(id("check"));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("checkMessage")), is(
                "次の画面でのロケール変更を確認"));

        // ブラウザのセッションIDを削除
        webDriverOperations.deleteCookie("JSESSIONID");

    }

    /**
     * <ul>
     * <li>リクエストパラメータに指定したロケールがCookieに保存され、リクエストされたロケールが有効になることの確認</li>
     * </ul>
     */
    @Test
    public void testINTR0202001() {
        // Locale 無し
        webDriverOperations.click(id("intr0202001_" + VIEW_TYPE));

        // 出力メッセージの確認(サーバのJVMの値)
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

        webDriverOperations.saveScreenCapture();

        // クエリに英語ロケールを指定し、遷移する
        String localeAddURL = webDriverOperations.getCurrentUrl()
                + "?locale=en";
        webDriverOperations.getWebDriver().get(localeAddURL);

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

        // Cookieに enロケールが指定されていること
        String localeCookieName = "org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE";
        assertThat(webDriverOperations.getCookie(localeCookieName).getValue(),
                is("en"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("intr0202001_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

        // Cookieに enロケールが指定されていること
        assertThat(webDriverOperations.getCookie(localeCookieName).getValue(),
                is("en"));

        // Cookieの破棄
        webDriverOperations.deleteCookie(localeCookieName);

    }

    /**
     * <ul>
     * <li>リクエストパラメータに指定したロケールがCookieに保存され、リクエストされたロケールが有効になることの確認</li>
     * </ul>
     */
    @Test
    public void testINTR0202002() {
        // Locale 無し
        webDriverOperations.click(id("intr0202002_" + VIEW_TYPE));

        // 出力メッセージの確認(デフォルトロケールの値)
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

        webDriverOperations.saveScreenCapture();

        // クエリに日本語ロケールを指定し、遷移する
        String localeAddURL = webDriverOperations.getCurrentUrl()
                + "?locale=ja";
        webDriverOperations.getWebDriver().get(localeAddURL);

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

        // Cookieに jaロケールが指定されていること
        String localeCookieName = "org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE";
        assertThat(webDriverOperations.getCookie(localeCookieName).getValue(),
                is("ja"));

        // Cookieの破棄
        webDriverOperations.deleteCookie(localeCookieName);

    }

    /**
     * <ul>
     * <li>SpringのロケールリゾルバにCookie名を定義し、リクエストパラメータにロケールを指定した場合、<br/>
     * 指定したCookie名に保存されていることの確認</li>
     * </ul>
     */
    @Test
    public void testINTR0202003() {
        // Locale 無し
        webDriverOperations.click(id("intr0202003_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

        webDriverOperations.saveScreenCapture();

        // クエリに英語ロケールを指定し、遷移する
        String localeAddURL = webDriverOperations.getCurrentUrl()
                + "?locale=en";
        webDriverOperations.getWebDriver().get(localeAddURL);

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

        // Cookieに enロケールが指定されていること
        String localeCookieName = "localeCookie";
        assertThat(webDriverOperations.getCookie(localeCookieName).getValue(),
                is("en"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("intr0202003_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

        // Cookieに enロケールが指定されていること
        assertThat(webDriverOperations.getCookie(localeCookieName).getValue(),
                is("en"));

        webDriverOperations.deleteCookie(localeCookieName);
    }

    /**
     * <ul>
     * <li>ロケールをリクエストパラメータに指定後、Cookieを破棄した場合、OSのロケールが有効になることの確認</li>
     * </ul>
     */
    @Test
    public void testINTR0202004() {
        // Locale 無し
        webDriverOperations.click(id("intr0202004_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

        webDriverOperations.saveScreenCapture();

        // クエリに英語ロケールを指定し、遷移する
        String localeAddURL = webDriverOperations.getCurrentUrl()
                + "?locale=en";
        webDriverOperations.getWebDriver().get(localeAddURL);

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

        // Cookieに enロケールが指定されていること
        String localeCookieName = "org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE";
        assertThat(webDriverOperations.getCookie(localeCookieName).getValue(),
                is("en"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());

        // Cookieの破棄
        webDriverOperations.deleteCookie(localeCookieName);

        webDriverOperations.click(id("intr0202004_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));
    }

    /**
     * <ul>
     * <li>存在しないロケールを指定した場合、ロケールがCookieに保存され、デフォルトプロパティから値が取得されることの確認</li>
     * </ul>
     */
    @Test
    public void testINTR0202005() {
        // Locale 無し
        webDriverOperations.click(id("intr0202005_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

        webDriverOperations.saveScreenCapture();

        // クエリに存在しないロケールを指定し、遷移する
        String localeAddURL = webDriverOperations.getCurrentUrl()
                + "?locale=xw";
        webDriverOperations.getWebDriver().get(localeAddURL);

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));

        // Cookieに xwロケールが指定されていること
        String localeCookieName = "org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE";
        assertThat(webDriverOperations.getCookie(localeCookieName).getValue(),
                is("xw"));

        // Cookieの破棄
        webDriverOperations.deleteCookie(localeCookieName);

    }

    /**
     * <ul>
     * <li>画面操作でロケールを変更した場合、ロケールがCookieに保存され、プロパティから値が取得されることの確認</li>
     * </ul>
     */
    @Test
    public void testINTR0202006() {
        // Locale 無し
        webDriverOperations.click(id("intr0202006_" + VIEW_TYPE));

        // 英語に変更
        webDriverOperations.click(id("english"));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("changeMessage")), is(
                "changed locale."));

        // 確認画面に遷移
        webDriverOperations.click(id("check"));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("checkMessage")), is(
                "Confirm change of locale on next screen"));
        webDriverOperations.saveScreenCapture();

        // back to /intr
        webDriverOperations.click(id("top"));

        // Locale 無し
        webDriverOperations.click(id("intr0202006_" + VIEW_TYPE));

        // 日本語に変更
        webDriverOperations.click(id("japanese"));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("changeMessage")), is(
                "ロケールを変更しました。"));

        // 確認画面に遷移
        webDriverOperations.click(id("check"));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("checkMessage")), is(
                "次の画面でのロケール変更を確認"));

        // Cookieの破棄
        String localeCookieName = "org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE";
        webDriverOperations.deleteCookie(localeCookieName);

    }

    /**
     * <ul>
     * <li>Controllerを経由せずにJSPに直接遷移する場合に、ロケールが有効にならないことの確認</li>
     * </ul>
     */
    @Ignore("JSP専用試験、削除するとJSP用seleniumと差分が出るため@Ignoreで残す")
    @Test
    public void testINTR0301001() {
        // 実施条件1
        // Locale 無し
        // Login
        webDriverOperations.click(id("intr0301-login_" + VIEW_TYPE));

        // 入力条件設定
        // JoshはROLE_USER
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // エラーページへ遷移
        webDriverOperations.click(id("intr0301001_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("errorMessage")), is(
                "アクセス権限がありません。"));

        webDriverOperations.saveScreenCapture();

        // 実施条件2
        // クエリに英語ロケールを指定し、遷移する
        String localeAddURL = webDriverOperations.getCurrentUrl()
                + "?locale=en";
        webDriverOperations.getWebDriver().get(localeAddURL);

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("errorMessage")), is(
                "アクセス権限がありません。"));

        webDriverOperations.saveScreenCapture();

        // 実施条件3
        // back to /intr
        webDriverOperations.click(id("top"));

        // エラーページへ遷移
        webDriverOperations.click(id("intr0301001_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("errorMessage")), is(
                "アクセス権限がありません。"));

        webDriverOperations.saveScreenCapture();

        // back to /intr
        webDriverOperations.click(id("top"));

        // Logout
        webDriverOperations.click(id("intr0301-login_" + VIEW_TYPE));
        webDriverOperations.click(id("logout"));

        // ent test
        webDriverOperations.displayPage(getPackageRootUrl());

    }

    /**
     * <ul>
     * <li>Controllerを経由してJSPに遷移する場合に、ロケールが有効になることの確認</li>
     * </ul>
     */
    @Ignore("JSP専用試験、削除するとJSP用seleniumと差分が出るため@Ignoreで残す")
    @Test
    public void testINTR0301002() {
        // 実施条件1
        // Locale 無し
        // Login
        webDriverOperations.click(id("intr0301-login_" + VIEW_TYPE));

        // 入力条件設定
        // JoshはROLE_USER
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // エラーページへ遷移
        webDriverOperations.click(id("intr0301002_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("errorMessage")), is(
                "アクセス権限がありません。"));

        webDriverOperations.saveScreenCapture();

        // 実施条件2
        // クエリに英語ロケールを指定し、遷移する
        String localeAddURL = webDriverOperations.getCurrentUrl()
                + "?locale=en";
        webDriverOperations.getWebDriver().get(localeAddURL);

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("errorMessage")), is(
                "Access Denied!"));

        webDriverOperations.saveScreenCapture();

        // 実施条件3
        // back to /intr
        webDriverOperations.click(id("top"));

        // エラーページへ遷移
        webDriverOperations.click(id("intr0301002_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("errorMessage")), is(
                "Access Denied!"));

        webDriverOperations.saveScreenCapture();

        // back to /intr
        webDriverOperations.click(id("top"));

        // Logout
        webDriverOperations.click(id("intr0301-login_" + VIEW_TYPE));
        webDriverOperations.click(id("logout"));

        // ent test
        webDriverOperations.displayPage(getPackageRootUrl());

    }

}
