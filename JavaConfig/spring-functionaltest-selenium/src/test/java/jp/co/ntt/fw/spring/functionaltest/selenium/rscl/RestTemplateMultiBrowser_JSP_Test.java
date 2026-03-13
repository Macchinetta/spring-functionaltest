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
package jp.co.ntt.fw.spring.functionaltest.selenium.rscl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupportForMultiBrowser;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebDriverOperations;

/**
 * RestTemplateの複数画面使用テスト<br>
 */
public class RestTemplateMultiBrowser_JSP_Test extends FunctionTestSupportForMultiBrowser {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateMultiBrowser_JSP_Test.class);

    @Override
    protected String getPackageRootUrl() {
        return super.getPackageRootUrl() + "-client";
    }

    /**
     * HowToExtends関連 - SSL自己署名証明書
     * <ul>
     * <li>RSCL1402001：setMaxConnPerRouteメソッドの動作確認</li>
     * <li>対象API：https://localhost:8991/api/v1/rscl2（6秒待機）</li>
     * <li>対象API：https://localhost:8991/api/v1/rscl3（6秒待機）</li>
     * <li>対象メソッド：GET</li>
     * <li>MaxConnPerRoute=1</li>
     * <li>MaxConnTotal=2</li>
     * </ul>
     * 
     * @throws InterruptedException
     */
    @Test
    public void testRSCL1402001() throws InterruptedException {
        // ブラウザを2つ起動
        setUpWebDriver(1, "restClient_jsp"); // localhost:8991
        WebDriverOperations operations1 = getWebDriverOperations(1);
        operations1.waitForDisplayed(textToBe(By.xpath("//h1"), "RSCL RESTクライアント処理"));
        operations1.click(id("rstpSslSelfSigned2"));

        setUpWebDriver(2, "restClient_jsp"); // localhost:8991
        WebDriverOperations operations2 = getWebDriverOperations(2);
        operations2.waitForDisplayed(textToBe(By.xpath("//h1"), "RSCL RESTクライアント処理"));
        operations2.click(id("rstpSslSelfSigned3"));

        final CountDownLatch startSignal = new CountDownLatch(2);

        Thread thread1 = createThread(1, startSignal);
        Thread thread2 = createThread(2, startSignal);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        // ブラウザ1の画面表示確認
        operations1.waitForDisplayed(textToBe(By.xpath("//h1"), "RESTクライアント処理結果【ユーザ情報出力】"));

        WebDriver driver1 = operations1.getWebDriver();
        ResultUserInfPage resultUserInfPage1 = new ResultUserInfPage(driver1);
        assertThat(resultUserInfPage1.getUserName(), is("test"));
        assertThat(resultUserInfPage1.getUserAge(), is("20"));

        LocalDateTime completionDateTime1 = LocalDateTime.parse(resultUserInfPage1.getCompletionDateTime(),
                DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSSSSSSSS"));
        Long executeMillis1 = Long.valueOf(resultUserInfPage1.getExecuteTime());

        // ブラウザ2の画面表示確認
        operations2.waitForDisplayed(textToBe(By.xpath("//h1"), "RESTクライアント処理結果【ユーザ情報出力】"));

        WebDriver driver2 = operations2.getWebDriver();
        ResultUserInfPage resultUserInfPage2 = new ResultUserInfPage(driver2);
        assertThat(resultUserInfPage2.getUserName(), is("test"));
        assertThat(resultUserInfPage2.getUserAge(), is("20"));

        LocalDateTime completionDateTime2 = LocalDateTime.parse(resultUserInfPage2.getCompletionDateTime(),
                DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSSSSSSSS"));
        Long executeMillis2 = Long.valueOf(resultUserInfPage2.getExecuteTime());

        // ブラウザをquit
        quitWebDriverWebDriverOperations(1);
        quitWebDriverWebDriverOperations(2);

        logger.info("ブラウザ1 処理時間:" + executeMillis1 + "[ms]");
        logger.info("ブラウザ2 処理時間:" + executeMillis2 + "[ms]");

        // MaxConnPerRoute = 1 のため、同一宛先に対しては最大1コネクションのみとなる
        // 先行処理は6秒前後
        // 後続処理は12秒前後
        // 誤差を考慮し、サーバ処理時間 ± 2秒 とする
        if (completionDateTime2.isAfter(completionDateTime1)) {
            // ブラウザ1が先行
            if (executeMillis1 < 4_000L || 8_000L < executeMillis1) {
                fail("期待値:4000[ms]～8000[ms], 処理時間:" + executeMillis1 + "[ms]");
            }
            if (executeMillis2 < 10_000L || 14_000L < executeMillis2) {
                fail("期待値:10000[ms]～14000[ms], 処理時間:" + executeMillis2 + "[ms]");
            }
        } else {
            // ブラウザ2が先行
            if (executeMillis1 < 10_000L || 14_000L < executeMillis1) {
                fail("期待値:10000[ms]～14000[ms], 処理時間:" + executeMillis1 + "[ms]");
            }
            if (executeMillis2 < 4_000L || 8_000L < executeMillis2) {
                fail("期待値:4000[ms]～8000[ms], 処理時間:" + executeMillis2 + "[ms]");
            }
        }
    }

    /**
     * HowToExtends関連 - SSL自己署名証明書
     * <ul>
     * <li>RSCL1403001：setMaxConnTotalメソッドの動作確認</li>
     * <li>対象API：https://localhost:8991/api/v1/rscl2（6秒待機）</li>
     * <li>対象API：https://localhost:8992/api/v1/rscl2（6秒待機）</li>
     * <li>対象API：https://localhost:8993/api/v1/rscl2（6秒待機）</li>
     * <li>対象メソッド：GET</li>
     * <li>MaxConnPerRoute=1</li>
     * <li>MaxConnTotal=2</li>
     * </ul>
     * 
     * @throws InterruptedException
     */
    @Test
    public void testRSCL1403001() throws InterruptedException {
        // ブラウザを3つ起動
        setUpWebDriver(1, "restClient_jsp"); // localhost:8991
        WebDriverOperations operations1 = getWebDriverOperations(1);
        operations1.waitForDisplayed(textToBe(By.xpath("//h1"), "RSCL RESTクライアント処理"));
        operations1.click(id("rstpSslSelfSigned2"));

        setUpWebDriver(2, "restClient_jsp"); // localhost:8991
        WebDriverOperations operations2 = getWebDriverOperations(2);
        operations2.waitForDisplayed(textToBe(By.xpath("//h1"), "RSCL RESTクライアント処理"));
        operations2.click(id("rstpSslSelfSigned5"));

        setUpWebDriver(3, "restClient_jsp"); // localhost:8991
        WebDriverOperations operations3 = getWebDriverOperations(3);
        operations3.waitForDisplayed(textToBe(By.xpath("//h1"), "RSCL RESTクライアント処理"));
        operations3.click(id("rstpSslSelfSigned6"));

        final CountDownLatch startSignal = new CountDownLatch(3);

        Thread thread1 = createThread(1, startSignal);
        Thread thread2 = createThread(2, startSignal);
        Thread thread3 = createThread(3, startSignal);
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        // ブラウザ1の画面表示確認
        operations1.waitForDisplayed(textToBe(By.xpath("//h1"), "RESTクライアント処理結果【ユーザ情報出力】"));

        WebDriver driver1 = operations1.getWebDriver();
        ResultUserInfPage resultUserInfPage1 = new ResultUserInfPage(driver1);
        assertThat(resultUserInfPage1.getUserName(), is("test"));
        assertThat(resultUserInfPage1.getUserAge(), is("20"));

        LocalDateTime completionDateTime1 = LocalDateTime.parse(resultUserInfPage1.getCompletionDateTime(),
                DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSSSSSSSS"));
        Long executeMillis1 = Long.valueOf(resultUserInfPage1.getExecuteTime());

        // ブラウザ2の画面表示確認
        operations2.waitForDisplayed(textToBe(By.xpath("//h1"), "RESTクライアント処理結果【ユーザ情報出力】"));

        WebDriver driver2 = operations2.getWebDriver();
        ResultUserInfPage resultUserInfPage2 = new ResultUserInfPage(driver2);
        assertThat(resultUserInfPage2.getUserName(), is("test"));
        assertThat(resultUserInfPage2.getUserAge(), is("20"));

        LocalDateTime completionDateTime2 = LocalDateTime.parse(resultUserInfPage2.getCompletionDateTime(),
                DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSSSSSSSS"));
        Long executeMillis2 = Long.valueOf(resultUserInfPage2.getExecuteTime());

        // ブラウザ3の画面表示確認
        operations3.waitForDisplayed(textToBe(By.xpath("//h1"), "RESTクライアント処理結果【ユーザ情報出力】"));

        WebDriver driver3 = operations3.getWebDriver();
        ResultUserInfPage resultUserInfPage3 = new ResultUserInfPage(driver3);
        assertThat(resultUserInfPage3.getUserName(), is("test"));
        assertThat(resultUserInfPage3.getUserAge(), is("20"));

        LocalDateTime completionDateTime3 = LocalDateTime.parse(resultUserInfPage3.getCompletionDateTime(),
                DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSSSSSSSS"));
        Long executeMillis3 = Long.valueOf(resultUserInfPage3.getExecuteTime());

        // ブラウザをquit
        quitWebDriverWebDriverOperations(1);
        quitWebDriverWebDriverOperations(2);
        quitWebDriverWebDriverOperations(3);

        logger.info("ブラウザ1 処理時間:" + executeMillis1 + "[ms]");
        logger.info("ブラウザ2 処理時間:" + executeMillis2 + "[ms]");
        logger.info("ブラウザ3 処理時間:" + executeMillis3 + "[ms]");

        // MaxConnTotal = 2 のため、MaxConnPerRoute = 1であっても別々の宛先に送ることで2コネクションまで取得できる
        // 先行処理は6秒前後
        // 後続処理は12秒前後
        // 誤差を考慮し、サーバ処理時間 ± 2秒 とする
        if (completionDateTime2.isAfter(completionDateTime1)) {
            if (completionDateTime3.isAfter(completionDateTime2)) {
                // ブラウザ1/ブラウザ2が先行
                if (executeMillis1 < 4_000L || 8_000L < executeMillis1) {
                    fail("期待値:4000[ms]～8000[ms], 処理時間:" + executeMillis1 + "[ms]");
                }
                if (executeMillis2 < 4_000L || 8_000L < executeMillis2) {
                    fail("期待値:4000[ms]～8000[ms], 処理時間:" + executeMillis2 + "[ms]");
                }
                if (executeMillis3 < 10_000L || 14_000L < executeMillis3) {
                    fail("期待値:10000[ms]～14000[ms], 処理時間:" + executeMillis3 + "[ms]");
                }
            } else {
                // ブラウザ1/ブラウザ3が先行
                if (executeMillis1 < 4_000L || 8_000L < executeMillis1) {
                    fail("期待値:4000[ms]～8000[ms], 処理時間:" + executeMillis1 + "[ms]");
                }
                if (executeMillis2 < 10_000L || 14_000L < executeMillis2) {
                    fail("期待値:10000[ms]～14000[ms], 処理時間:" + executeMillis2 + "[ms]");
                }
                if (executeMillis3 < 4_000L || 8_000L < executeMillis3) {
                    fail("期待値:4000[ms]～8000[ms], 処理時間:" + executeMillis3 + "[ms]");
                }
            }
        } else {
            if (completionDateTime3.isAfter(completionDateTime1)) {
                // ブラウザ1/ブラウザ2が先行
                if (executeMillis1 < 4_000L || 8_000L < executeMillis1) {
                    fail("期待値:4000[ms]～8000[ms], 処理時間:" + executeMillis1 + "[ms]");
                }
                if (executeMillis2 < 4_000L || 8_000L < executeMillis2) {
                    fail("期待値:4000[ms]～8000[ms], 処理時間:" + executeMillis2 + "[ms]");
                }
                if (executeMillis3 < 10_000L || 14_000L < executeMillis3) {
                    fail("期待値:10000[ms]～14000[ms], 処理時間:" + executeMillis3 + "[ms]");
                }
            } else {
                // ブラウザ2/ブラウザ3が先行
                if (executeMillis1 < 10_000L || 14_000L < executeMillis1) {
                    fail("期待値:10000[ms]～14000[ms], 処理時間:" + executeMillis1 + "[ms]");
                }
                if (executeMillis2 < 4_000L || 8_000L < executeMillis2) {
                    fail("期待値:4000[ms]～8000[ms], 処理時間:" + executeMillis2 + "[ms]");
                }
                if (executeMillis3 < 4_000L || 8_000L < executeMillis3) {
                    fail("期待値:4000[ms]～8000[ms], 処理時間:" + executeMillis3 + "[ms]");
                }
            }
        }
    }

    private Thread createThread(final int webDriverId, final CountDownLatch startSignal) {
        return new Thread(() -> {
            WebDriverOperations operations = getWebDriverOperations(webDriverId);
            WebDriver driver = operations.getWebDriver();
            SetSelfSignedCertificatePage setSelfSignedCertificatePage = new SetSelfSignedCertificatePage(driver);

            startSignal.countDown();
            try {
                startSignal.await();
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }

            setSelfSignedCertificatePage.clickSetBtn();
        });
    }

    /**
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param testId      テスト画面を表示するためのリンクを識別するためのID
     */
    private void setUpWebDriver(int webDriverId, String testId) {
        WebDriverOperations operations = setUpWebDriverOperations(webDriverId);
        operations.click(id(testId));
    }

}
