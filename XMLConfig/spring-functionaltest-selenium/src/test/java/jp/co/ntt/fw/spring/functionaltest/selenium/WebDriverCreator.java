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
package jp.co.ntt.fw.spring.functionaltest.selenium;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.context.support.ApplicationObjectSupport;

import jakarta.inject.Inject;

public class WebDriverCreator extends ApplicationObjectSupport {

    @Inject
    private WebDriverManagerConfigurer webDriverManagerConfigurer;

    /**
     * デフォルトのWebDriver(Beanファイルに定義されているWebDriver)を作成する。
     * @return デフォルトのWebDriver
     */
    public WebDriver createDefaultWebDriver() {
        return getApplicationContext().getBean(WebDriver.class);
    }

    /**
     * 任意のロケールを有効にした、WebDriverを作成する。
     * <p>
     * FireFox,Chromeのみをサポート<br>
     * 引数 に"en" を指定すると、英語ロケールで起動する。<br>
     * 引数に "" を指定した場合、ロケールなしで起動する。
     * </p>
     * @param localeStr
     * @return WebDriver 動作対象のブラウザ
     */
    public WebDriver createLocaleSpecifiedDriver(String localeStr) {
        webDriverManagerConfigurer.setUp();

        for (String activeProfile : getApplicationContext().getEnvironment()
                .getActiveProfiles()) {
            if ("chrome".equals(activeProfile)) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--lang=" + localeStr);
                return new ChromeDriver(options);
            } else if ("ie".equals(activeProfile)) {
                throw new UnsupportedOperationException("InternetExplorerを使用してロケール指定のブラウザ起動はできません。");
            } else if ("phantomJs".equals(activeProfile)) {
                throw new UnsupportedOperationException("PhantomJSを使用してロケール指定のブラウザ起動はできません。");
            }
        }

        // デフォルトのブラウザはFirefoxとする
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", localeStr);
        profile.setPreference("browser.startup.homepage_override.mstone",
                "ignore");
        profile.setPreference("network.proxy.type", 0);
        profile.setPreference("layout.css.devPixelsPerPx", "0.5");
        FirefoxOptions options = new FirefoxOptions().setProfile(profile);
        return new FirefoxDriver(options);
    }

    /**
     * ダウンロード機能を有効にしたWebDirverを起動する。
     * <p>
     * FireFoxのみをサポート<br>
     * </p>
     * @param downloadTempDirectory ダウンロード用の一時保存ディレクトリ
     * @return ダウンロード機能を有効にしたWebDirverインスタンス
     */
    public WebDriver createDownloadableWebDriver(String downloadTempDirectory) {
        webDriverManagerConfigurer.setUp();
        for (String activeProfile : getApplicationContext().getEnvironment()
                .getActiveProfiles()) {
            if ("chrome".equals(activeProfile) || "ie".equals(activeProfile)
                    || "phantomJs".equals(activeProfile)) {
                throw new UnsupportedOperationException("FireFox以外のブラウザでダウンロード機能を使用するテストを実行することはできません。");
            }
        }

        // デフォルトのブラウザはFirefoxとする
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir", downloadTempDirectory);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.lastDir",
                downloadTempDirectory);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.showWhenStarting",
                false);
        profile.setPreference(
                "services.sync.prefs.sync.browser.download.manager.showWhenStarting",
                false);
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/pdf, text/csv, application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, text/plain");
        profile.setPreference("browser.startup.homepage_override.mstone",
                "ignore");
        profile.setPreference("network.proxy.type", 0);
        FirefoxOptions options = new FirefoxOptions().setProfile(profile);
        WebDriver webDriver = new FirefoxDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return webDriver;
    }

}
