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
package jp.co.ntt.fw.spring.functionaltest.selenium.djpa;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupportForMultiBrowser;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebDriverOperations;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.BookDetailsPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.JPAHomePage;

/**
 * EXCN 排他制御テスト<br>
 * <p>
 * </p>
 */
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class DataAccessExclusiveJPATest extends
                                        FunctionTestSupportForMultiBrowser {

    @Value("${selenium.excn.waitForNextRequest.offsetSeconds:0}")
    private int offsetSecondsOfWaitForNextRequest;

    /**
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param testId テスト画面を表示するためのリンクを識別するためのID
     */
    private void setUpWebDriver(int webDriverId, String testId) {
        WebDriverOperations operations = setUpWebDriverOperations(webDriverId);
        operations.click(id(testId));
    }

    /**
     * DJPA0304001
     * <ul>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testDJPA0304001() throws InterruptedException {
        String testId = "djpa0304001";
        String testIdUpperCase = testId.toUpperCase();

        // テスト実行
        {
            // 疑似アプリの画面を表示するブラウザを2つ起動
            setUpWebDriver(0, testId);
            setUpWebDriver(1, testId);

            final CountDownLatch startSignal = new CountDownLatch(1);
            final CountDownLatch doneSignal = new CountDownLatch(2);

            // This time is less than that specified in query hint.
            // So no exception is expected in request sent through the second browser
            final long sleepMillisThatWaitNextRequest = 9000;

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        selectForUpdateNoExcp(0, "2",
                                sleepMillisThatWaitNextRequest, startSignal);
                    } finally {
                        doneSignal.countDown();
                    }
                }
            }).start();

            // 画面2を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 画面1->画面2の順で画面操作が行われるようにするため、画面1でボタンが押下されるまで待機
                        startSignal.await();
                        // 画面1のボタン押下と実際にリクエストがサーバに届くタイムラグを考慮し、一定時間待機
                        suspendWebDriver(1, (sleepMillisThatWaitNextRequest
                                / 2));
                        selectForUpdateNoExcp(1, "2", 0);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    } finally {
                        doneSignal.countDown();
                    }
                }
            }).start();

            // 画面1と2の両方でボタンが押下されるまで待機
            doneSignal.await();
        }

        // 結果確認
        {
            // 画面1が、以下の条件を満たすこと
            // ・完了画面に遷移すること
            // ・入力した変更内容が反映されること
            assertCompleteView(0, testIdUpperCase, testIdUpperCase, 2, 0);

            WebDriverOperations operations = getWebDriverOperations(0);
            WebDriver driver = operations.getWebDriver();
            BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);

            String dataBase = bookDetailsPage.getDataBaseId();

            // As H2 DB does no support query hints in JPQL
            if (!"H2".equals(dataBase)) {
                // The second browser should also get lock on the record once the
                // first browser has released one
                assertCompleteView(1, testIdUpperCase, testIdUpperCase, 2, 0);
            }
        }
    }

    /**
     * DJPA0304002
     * <ul>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testDJPA0304002() throws InterruptedException {
        String testId = "djpa0304002";
        String testIdUpperCase = testId.toUpperCase();

        // テスト実行
        {
            // 疑似アプリの画面を表示するブラウザを2つ起動
            setUpWebDriver(0, testId);
            setUpWebDriver(1, testId);

            final CountDownLatch startSignal = new CountDownLatch(1);
            final CountDownLatch doneSignal = new CountDownLatch(2);

            final long sleepMillisThatWaitNextRequest = 20000;

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        selectForUpdate(0, "2", sleepMillisThatWaitNextRequest,
                                startSignal);
                    } finally {
                        doneSignal.countDown();
                    }
                }
            }).start();

            // 画面2を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 画面1->画面2の順で画面操作が行われるようにするため、画面1でボタンが押下されるまで待機
                        startSignal.await();
                        // 画面1のボタン押下と実際にリクエストがサーバに届くタイムラグを考慮し、一定時間待機
                        suspendWebDriver(1, (sleepMillisThatWaitNextRequest
                                / 2));
                        selectForUpdate(1, "2", 20000);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    } finally {
                        doneSignal.countDown();
                    }
                }
            }).start();

            // 画面1と2の両方でボタンが押下されるまで待機
            doneSignal.await();
        }

        // 結果確認
        {
            // 画面1が、以下の条件を満たすこと
            // ・完了画面に遷移すること
            // ・入力した変更内容が反映されること
            assertCompleteView(0, testIdUpperCase, testIdUpperCase, 2, 0);

            WebDriverOperations operations = getWebDriverOperations(0);
            WebDriver driver = operations.getWebDriver();
            BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);

            String dataBase = bookDetailsPage.getDataBaseId();

            // 画面2が、以下の条件を満たすこと
            // ・業務エラー完了画面に遷移すること
            // ・入力した変更内容が反映されないこと
            // TODO:Hibernateのバグ対応版（5.2.1.Final）にアップデート後、if文の条件を!"H2".equals(dataBase)に戻す
            // 修正理由:Hibernateのバグ(HHH-10797)によって、PostgreSQLで@QueryHint(name = "javax.persistence.lock.timeout", value =
            // "0")を指定しても、
            // NOWAIT句が付かなくなってしまったため
            // 横展開としてガイドラインのissueを挙げている(https://github.com/terasolunaorg/guideline/issues/2372)
            if (!"H2".equals(dataBase) && !"POSTGRESQL".equals(dataBase)) {
                // As the first browser has locked the record, the second browser lock timeouts
                // and exception is thrown
                assertFailureView(1,
                        "Exclusive Lock Error!\nOther user has already acquired Lock for update!!");
            }
        }
    }

    /**
     * @param webDriverId
     * @param bookId
     * @param sleepMillis
     */
    private void selectForUpdate(int webDriverId, String bookId,
            long sleepMillis) {
        selectForUpdate(webDriverId, bookId, sleepMillis, null);
    }

    /**
     * @param webDriverId
     * @param bookId
     * @param sleepMillis
     */
    private void selectForUpdateNoExcp(int webDriverId, String bookId,
            long sleepMillis) {
        selectForUpdateNoExcp(webDriverId, bookId, sleepMillis, null);
    }

    /**
     * @param webDriverId
     * @param bookId
     * @param sleepMillis
     * @param startSignal
     */
    private void selectForUpdate(int webDriverId, String bookId,
            long sleepMillis, CountDownLatch startSignal) {

        WebDriverOperations operations = getWebDriverOperations(webDriverId);
        WebDriver driver = operations.getWebDriver();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        // JPAHomePage jpaHomePage = jpaIndexPage.djpa0202001Click();
        jpaHomePage.setBookIdForDefJPASearchOpn(bookId);
        jpaHomePage.setSleepTime(String.valueOf(sleepMillis));
        if (startSignal != null) {
            startSignal.countDown();
        }
        jpaHomePage.selectBookForUpdateByPessimisticLock();
    }

    /**
     * @param webDriverId
     * @param bookId
     * @param sleepMillis
     * @param startSignal
     */
    private void selectForUpdateNoExcp(int webDriverId, String bookId,
            long sleepMillis, CountDownLatch startSignal) {

        WebDriverOperations operations = getWebDriverOperations(webDriverId);
        WebDriver driver = operations.getWebDriver();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        // JPAHomePage jpaHomePage = jpaIndexPage.djpa0202001Click();
        jpaHomePage.setBookIdForDefJPASearchOpn(bookId);
        jpaHomePage.setSleepTime(String.valueOf(sleepMillis));
        if (startSignal != null) {
            startSignal.countDown();
        }
        jpaHomePage.selectBookForUpdateByPessimisticLockNoExcp();
    }

    /**
     * 完了画面をアサートする。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param itemCode
     * @param itemName
     * @param quantity
     * @param version
     */
    private void assertCompleteView(int webDriverId, String itemCode,
            String itemName, int quantity, int version) {
        WebDriverOperations operations = getWebDriverOperations(webDriverId);
        WebDriver driver = operations.getWebDriver();
        BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);
        assertThat(bookDetailsPage.getBookIdVal(), is("2"));
        assertThat(bookDetailsPage.getTitle(), is("Manual Title 2"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getClobCode(), is("54455322"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("40"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2013/01/02"));
    }

    /**
     * エラー画面をアサートする。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param message エラーメッセージ
     */
    private void assertFailureView(int webDriverId, String message) {
        WebDriverOperations operations = getWebDriverOperations(webDriverId);
        assertThat(operations.getText(id("wrapper")), is(message));
    }

    /**
     * ブラウザの実行を停止する。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param waitTime
     */
    private void suspendWebDriver(int webDriverId, long waitTime) {
        getWebDriverOperations(webDriverId).suspend(waitTime,
                TimeUnit.MILLISECONDS);
    }

}
