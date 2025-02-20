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
package jp.co.ntt.fw.spring.functionaltest.selenium.webdrivers;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.util.StringUtils;

import io.github.bonigarcia.wdm.WebDriverManager;
import jp.co.ntt.fw.spring.functionaltest.selenium.BrowserLocale;

public class WebDriverCreator extends ApplicationObjectSupport implements InitializingBean {

    private boolean headless = true;

    private String propertyFileLocation;

    public void setHeadless(boolean headless) {
        this.headless = headless;
    }

    public void setPropertyFileLocation(String propertyFileLocation) {
        this.propertyFileLocation = propertyFileLocation;
    }

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
     * 引数 に"en" を指定すると、英語ロケールで起動する。<br>
     * 引数に "" を指定した場合、ロケールなしで起動する。
     * </p>
     * @param localeStr ロケール
     * @return WebDriver 動作対象のブラウザ
     */
    public WebDriver createLocaleSpecifiedDriver(BrowserLocale locale) {

        // WebdriverManagerの設定
        setUpWebDriverManager();

        String[] profiles = getApplicationContext().getEnvironment().getDefaultProfiles();
        WebDriver webDriver = null;

        if (Arrays.asList(profiles).contains("chrome")) {
            webDriver = createChromeDriverWithLocale(locale);
        } else if (Arrays.asList(profiles).contains("edge")) {
            webDriver = createEdgeDriverWithLocale(locale);
        } else {
            // デフォルトはFireFox
            webDriver = createFirefoxDriverWithLocale(locale);
        }

        return webDriver;
    }

    private ChromeDriver createChromeDriverWithLocale(BrowserLocale locale) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=" + locale.getForChrome());
        options.addArguments("--remote-allow-origins=*");

        if (this.headless) {
            options.addArguments("--headless=new");
        }

        return new ChromeDriver(options);
    }

    private EdgeDriver createEdgeDriverWithLocale(BrowserLocale locale) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--lang=" + locale.getForEdge());
        options.addArguments("--remote-allow-origins=*");

        if (this.headless) {
            options.addArguments("--headless=new");
        }

        return new EdgeDriver(options);
    }

    private FirefoxDriver createFirefoxDriverWithLocale(BrowserLocale locale) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", locale.getForFirefox());
        profile.setPreference("browser.startup.homepage_override.mstone", "ignore");
        profile.setPreference("network.proxy.type", 0);
        profile.setPreference("layout.css.devPixelsPerPx", "0.5");

        FirefoxOptions options = new FirefoxOptions().setProfile(profile);
        options.addArguments("--lang=" + locale.getForFirefox());

        if (this.headless) {
            options.addArguments("--headless");
        }

        return new FirefoxDriver(options);
    }

    /**
     * ダウンロード機能を有効にしたWebDirverを起動する。
     * @param downloadTempDirectory ダウンロード用の一時保存ディレクトリ
     * @return ダウンロード機能を有効にしたWebDirverインスタンス
     */
    public WebDriver createDownloadableWebDriver(String downloadTempDirectory) {

        // WebdriverManagerの設定
        setUpWebDriverManager();

        String[] profiles = getApplicationContext().getEnvironment().getDefaultProfiles();
        WebDriver webDriver = null;

        if (Arrays.asList(profiles).contains("chrome")) {
            webDriver = createDownloadChromeDriver(downloadTempDirectory);
        } else if (Arrays.asList(profiles).contains("edge")) {
            webDriver = createDownloadEdgeDriver(downloadTempDirectory);
        } else {
            // デフォルトはFireFox
            webDriver = createDownloadFirefoxDriver(downloadTempDirectory);
        }

        return webDriver;
    }

    private WebDriver createDownloadFirefoxDriver(String downloadTempDirectory) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir", downloadTempDirectory);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.lastDir", downloadTempDirectory);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting",
                false);
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/pdf, text/csv, application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, text/plain");
        profile.setPreference("browser.startup.homepage_override.mstone", "ignore");
        profile.setPreference("network.proxy.type", 0);

        FirefoxOptions options = new FirefoxOptions().setProfile(profile);

        if (this.headless) {
            options.addArguments("--headless");
        }

        WebDriver webDriver = new FirefoxDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        return webDriver;
    }

    private WebDriver createDownloadChromeDriver(String downloadTempDirectory) {
        HashMap<String, Object> chromeHashMap = new HashMap<>();
        chromeHashMap.put("download.default_directory", downloadTempDirectory);
        chromeHashMap.put("profile.default_content_settings.popups", 0);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromeHashMap);
        options.addArguments("--remote-allow-origins=*");

        if (this.headless) {
            options.addArguments("--headless=new");
        }

        WebDriver webDriver = new ChromeDriver(options);

        return webDriver;
    }

    private WebDriver createDownloadEdgeDriver(String downloadTempDirectory) {
        HashMap<String, Object> edgeHashMap = new HashMap<>();
        edgeHashMap.put("download.default_directory", downloadTempDirectory);
        edgeHashMap.put("profile.default_content_settings.popups", 0);
        EdgeOptions options = new EdgeOptions();
        options.setExperimentalOption("prefs", edgeHashMap);
        options.addArguments("--remote-allow-origins=*");

        if (this.headless) {
            options.addArguments("--headless=new");
        }

        WebDriver webDriver = new EdgeDriver(options);

        return webDriver;
    }

    private void setUpWebDriverManager() {

        if (System.getenv("webdriver.driver") != null) {
            return;
        }

        String[] profiles = getApplicationContext().getEnvironment().getDefaultProfiles();
        WebDriverManager manager = null;

        if (Arrays.asList(profiles).contains("chrome")) {
            manager = WebDriverManager.chromedriver();
        } else if (Arrays.asList(profiles).contains("edge")) {
            manager = WebDriverManager.edgedriver();
        } else {
            manager = WebDriverManager.firefoxdriver();
        }

        if (this.propertyFileLocation != null) {
            manager.config().setProperties(this.propertyFileLocation);
        }

        manager.setup();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Prioritize startup arguments
        String headlessProperty = System.getProperty("selenium.headless");
        if (StringUtils.hasLength(headlessProperty)) {
            this.headless = Boolean.valueOf(headlessProperty);
        }
    }
}
