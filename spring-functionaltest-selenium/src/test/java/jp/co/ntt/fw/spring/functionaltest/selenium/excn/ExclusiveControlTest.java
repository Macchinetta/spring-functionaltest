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
package jp.co.ntt.fw.spring.functionaltest.selenium.excn;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupportForMultiBrowser;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebDriverOperations;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.ExclusionResultPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.StockPage;

/**
 * EXCN 排他制御テスト<br>
 * <p>
 * </p>
 */
// Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class ExclusiveControlTest extends FunctionTestSupportForMultiBrowser {

    @Value("${selenium.excn.waitForNextRequest.offsetSeconds:0}")
    private int offsetSecondsOfWaitForNextRequest;

    /**
     * 複数画面を起動して、テスト画面まで遷移させる。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param testId テスト画面を表示するためのリンクを識別するためのID
     */
    private void setUpWebDriver(int webDriverId, String testId) {
        WebDriverOperations operations = setUpWebDriverOperations(webDriverId);
        operations.click(id(testId));
    }

    /**
     * EXCN0301001
     * <ul>
     * <li>同一レコードを更新するリクエストを連続で実行した場合、RDBMSの行ロック機能が有効になることを確認する。</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testEXCN0301001() throws InterruptedException {
        String testId = "excn0301001";
        String testIdUpperCase = testId.toUpperCase();

        // テスト実行
        {
            // 疑似アプリの画面を表示するブラウザを2つ起動
            setUpWebDriver(0, testId);
            setUpWebDriver(1, testId);

            final CountDownLatch doneSignal = new CountDownLatch(2);

            final long sleepMillisThatWaitNextRequest = TimeUnit.SECONDS
                    .toMillis(3 + offsetSecondsOfWaitForNextRequest);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        buy(0, 3, sleepMillisThatWaitNextRequest / 2);
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
                        buy(1, 4, sleepMillisThatWaitNextRequest / 2);
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
            if (isUpdateSuccued(0)) {
                assertionForTestEXCN0301001(0, 1, testIdUpperCase, 2);
            } else {
                assertionForTestEXCN0301001(1, 0, testIdUpperCase, 1);
            }
        }
    }

    /**
     * EXCN0302001
     * <ul>
     * <li>Serviceクラスの一連の処理で、バージョンカラムの取得、更新を行う場合、更新時にバージョンの値が取得時と異なるとき、 楽観ロック例外を明示的にスロー可能なことを確認する。</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testEXCN0302001() throws InterruptedException {
        String testId = "excn0302001";
        String testIdUpperCase = testId.toUpperCase();

        // テスト実行（1メソッド目）
        {
            final CountDownLatch doneSignal = new CountDownLatch(2);
            // 疑似アプリの画面を表示するブラウザを2つ起動
            setUpWebDriver(0, testId);
            setUpWebDriver(1, testId);
            // 画面1を操作するスレッドを起動
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        buy(0, 3);
                    } finally {
                        doneSignal.countDown();
                    }
                }
            }).start();
            // 画面2を操作するスレッドを起動
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        buy(1, 4);
                    } finally {
                        doneSignal.countDown();
                    }
                }
            }).start();
            // 画面1と2の両方でボタンが押下されるまで待機
            doneSignal.await();
        }
        {
            if (isUpdateSuccued(0)) {
                // 画面1が更新できた場合
                assertionForTestEXCN0302001(0, 1, testIdUpperCase, 2);
            } else {
                assertionForTestEXCN0302001(1, 0, testIdUpperCase, 1);
            }
        }
    }

    /**
     * EXCN0302002
     * <ul>
     * <li>複数リクエストにまたがったトランザクションの場合、別画面で取得したバージョンカラムの値を更新処理で参照することで、 バージョンの値が異なるとき、楽観ロック例外を明示的にスロー可能なことを確認する。</li>
     * </ul>
     */
    @Test
    public void testEXCN0302002() throws InterruptedException {
        String testId = "excn0302002";
        String testIdUpperCase = testId.toUpperCase();

        {
            final CountDownLatch doneSignal = new CountDownLatch(2);
            // 疑似アプリの画面を表示するブラウザを2つ起動
            setUpWebDriver(0, testId);
            setUpWebDriver(1, testId);

            // 画面1を操作するスレッドを起動
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        buy(0, 3);
                    } finally {
                        doneSignal.countDown();
                    }
                }
            }).start();
            // 画面2を操作するスレッドを起動
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        buy(1, 4);
                    } finally {
                        doneSignal.countDown();
                    }
                }
            }).start();
            // 画面1と2の両方でボタンが押下されるまで待機
            doneSignal.await();
        }
        {
            if (isUpdateSuccued(0)) {
                assertionForTestEXCN0302002(0, 1, testIdUpperCase, 2);
            } else {
                assertionForTestEXCN0302002(1, 0, testIdUpperCase, 1);
            }
        }
    }

    /**
     * EXCN0401001
     * <ul>
     * <li>楽観ロックエラーをリクエスト単位でハンドリングする必要がない場合、コントローラ単位でのハンドリングが可能なことを確認する。</li>
     * </ul>
     * @throws InterruptedException
     * @see ExclusiveControlTest#testEXCN0302001()
     */
    @Ignore("testEXCN0302001で実施")
    public void testEXCN0401001() throws InterruptedException {
        testEXCN0302001();
    }

    /**
     * EXCN0401002
     * <ul>
     * <li>楽観ロックエラーをリクエスト単位でハンドリングする必要がある場合、リクエスト単位でのハンドリング可能なことを確認する。</li>
     * </ul>
     * @see ExclusiveControlTest#testEXCN0302002()
     */
    @Ignore("testEXCN0302002で実施")
    public void testEXCN0401002() throws InterruptedException {
        testEXCN0302002();
    }

    /**
     * EXCN0402001
     * <ul>
     * <li>悲観ロックエラーをリクエスト単位でハンドリングする必要がない場合、コントローラ単位でのハンドリングが可能なことを確認する。</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testEXCN0402001() throws InterruptedException {
        String testId = "excn0402001";
        String testIdUpperCase = testId.toUpperCase();

        // テスト実行（1メソッド目）
        {
            // 疑似アプリの画面を表示するブラウザを2つ起動
            setUpWebDriver(0, testId);
            setUpWebDriver(1, testId);

            final CountDownLatch doneSignal = new CountDownLatch(2);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        buy(0, 3);
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
                        buy(1, 4);
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
            if (isUpdateSuccued(0)) {
                assertionForTestEXCN0402001(0, 1, testIdUpperCase, 2);
            } else {
                assertionForTestEXCN0402001(1, 0, testIdUpperCase, 1);
            }
        }
    }

    /**
     * EXCN0402002
     * <ul>
     * <li>悲観ロックエラーをリクエスト単位でハンドリングする必要がある場合、リクエスト単位でのハンドリング可能なことを確認する。</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testEXCN0402002() throws InterruptedException {
        String testId = "excn0402002";
        String testIdUpperCase = testId.toUpperCase();

        // テスト実行
        {
            // 疑似アプリの画面を表示するブラウザを2つ起動
            setUpWebDriver(0, testId);
            setUpWebDriver(1, testId);

            final CountDownLatch doneSignal = new CountDownLatch(2);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        buy(0, 3);
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
                        buy(1, 4);
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
            if (isUpdateSuccued(0)) {
                assertionForTestEXCN0402002(0, 1, testIdUpperCase, 2);
            } else {
                assertionForTestEXCN0402002(1, 0, testIdUpperCase, 1);
            }
        }
    }

    /**
     * EXCN0501001
     * <ul>
     * <li>同一レコードを更新するリクエストを連続で実行した場合、RDBMSの行ロック機能が有効になることを確認する。</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testEXCN0501001() throws InterruptedException {
        String testId = "excn0501001";
        String testIdUpperCase = testId.toUpperCase();

        // テスト実行
        {
            // 疑似アプリの画面を表示するブラウザを2つ起動
            setUpWebDriver(0, testId);
            setUpWebDriver(1, testId);

            final CountDownLatch doneSignal = new CountDownLatch(2);

            final long sleepMillisThatWaitNextRequest = TimeUnit.SECONDS
                    .toMillis(3 + offsetSecondsOfWaitForNextRequest);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        jpaBuy(0, 3, sleepMillisThatWaitNextRequest / 2);
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
                        jpaBuy(1, 4, sleepMillisThatWaitNextRequest / 2);
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
            if (isUpdateSuccued(0)) {
                assertionForTestEXCN0501001(0, 1, testIdUpperCase, 2);
            } else {
                assertionForTestEXCN0501001(1, 0, testIdUpperCase, 1);
            }
        }
    }

    /**
     * EXCN0502001
     * <ul>
     * <li>OptimisticLocking Exception</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testEXCN0502001() throws InterruptedException {
        String testId = "excn0502001";
        String testIdUpperCase = testId.toUpperCase();

        // テスト実行（1メソッド目）
        {
            // 疑似アプリの画面を表示するブラウザを2つ起動
            setUpWebDriver(0, testId);
            setUpWebDriver(1, testId);

            final CountDownLatch doneSignal = new CountDownLatch(2);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        jpaBuy(0, 3);
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
                        jpaBuy(1, 4);
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
            if (isUpdateSuccued(0)) {
                assertionForTestEXCN0502001(0, 1, testIdUpperCase, 2);
            } else {
                assertionForTestEXCN0502001(1, 0, testIdUpperCase, 1);
            }
        }
    }

    /**
     * EXCN0502002
     * <ul>
     * <li>Pessimistic Locking</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testEXCN0502002() throws InterruptedException {
        String testId = "excn0502002";
        String testIdUpperCase = testId.toUpperCase();

        // テスト実行（1メソッド目）
        {
            // 疑似アプリの画面を表示するブラウザを2つ起動
            // 疑似アプリの画面を表示するブラウザを2つ起動
            setUpWebDriver(0, testId);
            setUpWebDriver(1, testId);

            final CountDownLatch startSignal = new CountDownLatch(1);
            final CountDownLatch doneSignal = new CountDownLatch(2);

            final long sleepMillisThatWaitNextRequest = TimeUnit.SECONDS
                    .toMillis(3 + offsetSecondsOfWaitForNextRequest);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        jpaBuy(0, 3, 1000, startSignal);
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
                        suspendWebDriver(1, sleepMillisThatWaitNextRequest / 2);
                        jpaBuy(1, 1, 0);
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
            ExclusionResultPage exclusionResultPage = new ExclusionResultPage(getWebDriverOperations(
                    0).getWebDriver());
            if ("1".equals(exclusionResultPage.getVersion())) {
                // 画面1の処理が先に行われた場合
                assertionForTestEXCN0502002(0, 1, testIdUpperCase, 2, 1);
            } else {
                assertionForTestEXCN0502002(1, 0, testIdUpperCase, 4, 1);
            }
        }
    }

    /**
     * EXCN0602001
     * <ul>
     * <li>OptimisticLocking Exception</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testEXCN0602001() throws InterruptedException {
        String testId = "excn0602001";
        String testIdUpperCase = testId.toUpperCase();

        // テスト実行（1メソッド目）
        {
            // 疑似アプリの画面を表示するブラウザを2つ起動
            setUpWebDriver(0, testId);
            setUpWebDriver(1, testId);

            final CountDownLatch doneSignal = new CountDownLatch(2);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        jpaBuy(0, 3);
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
                        jpaBuy(1, 4);
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
            if (isUpdateSuccued(0)) {
                assertionForTestEXCN0602001(0, 1, testIdUpperCase, 2);
            } else {
                assertionForTestEXCN0602001(1, 0, testIdUpperCase, 1);
            }
        }
    }

    /**
     * EXCN0602002
     * <ul>
     * <li>OptimisticLocking Exception</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testEXCN0602002() throws InterruptedException {
        String testId = "excn0602002";
        String testIdUpperCase = testId.toUpperCase();

        // テスト実行（1メソッド目）
        {
            // 疑似アプリの画面を表示するブラウザを2つ起動
            setUpWebDriver(0, testId);
            setUpWebDriver(1, testId);

            final CountDownLatch doneSignal = new CountDownLatch(2);

            final long sleepMillisThatWaitNextRequest = TimeUnit.SECONDS
                    .toMillis(3 + offsetSecondsOfWaitForNextRequest);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        jpaBuy(0, 3, sleepMillisThatWaitNextRequest / 2);
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
                        jpaBuy(1, 1, sleepMillisThatWaitNextRequest / 2);
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
            int completedId;
            // 更新画面を開いている画面からDB名を取得する
            if (isUpdateSuccued(0)) {
                completedId = 0;
            } else {
                completedId = 1;
            }
            WebDriverOperations operations = getWebDriverOperations(
                    completedId);
            WebDriver driver = operations.getWebDriver();
            ExclusionResultPage exclusionResultPage = new ExclusionResultPage(driver);

            String database = exclusionResultPage.getDatabase();
            // TODO:Hibernateのバグ対応版（5.2.1.Final）にアップデート後、条件"POSTGRESQL".equals(database)を除去する
            // 修正理由:Hibernateのバグ(HHH-10797)によって、PostgreSQLで@QueryHint(name = "javax.persistence.lock.timeout", value =
            // "0")を指定しても、
            // NOWAIT句が付かなくなってしまったため
            // 横展開としてガイドラインのissueを挙げている(https://github.com/terasolunaorg/guideline/issues/2372)
            if ("H2".equals(database) || "POSTGRESQL".equals(database)) {
                if ("1".equals(exclusionResultPage.getVersion())) {
                    assertionForTestEXCN0602002WithoutNoWaitOption(0, 1,
                            testIdUpperCase, 2, 1);
                } else {
                    assertionForTestEXCN0602002WithoutNoWaitOption(1, 0,
                            testIdUpperCase, 4, 1);
                }
            } else {
                if (completedId == 0) {
                    assertionForTestEXCN0602002(0, 1, testIdUpperCase, 2);
                } else {
                    assertionForTestEXCN0602002(1, 0, testIdUpperCase, 4);
                }
            }
        }
    }

    /**
     * 変更画面に入力し、画面遷移する。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param purchaseQuantity 減算する数量
     */
    private void jpaBuy(int webDriverId, int purchaseQuantity) {
        jpaBuy(webDriverId, purchaseQuantity, -1);
    }

    /**
     * 変更画面に入力し、画面遷移する。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param purchaseQuantity 減算する数量
     * @param sleepMillis ロックする時間（厳密には、Thread#sleepする時間）
     */
    private void jpaBuy(int webDriverId, int purchaseQuantity,
            long sleepMillis) {
        jpaBuy(webDriverId, purchaseQuantity, sleepMillis, null);
    }

    /**
     * 変更画面に入力し、画面遷移する。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param purchaseQuantity 減算する数量
     * @param sleepMillis ロックする時間（厳密には、Thread#sleepする時間）
     * @param startSignal 待機待ちの別スレッドに開始シグナルを送るためのオブジェクト
     */
    private void jpaBuy(int webDriverId, int purchaseQuantity, long sleepMillis,
            CountDownLatch startSignal) {
        WebDriverOperations operations = getWebDriverOperations(webDriverId);
        WebDriver driver = operations.getWebDriver();
        StockPage stockPage = new StockPage(driver);
        stockPage.setPurchaseQuantity(purchaseQuantity);
        if (sleepMillis != -1) {
            stockPage.setSleepTime(sleepMillis);
        }
        if (startSignal != null) {
            startSignal.countDown();
        }
        stockPage.buyWithRowLock();
    }

    /**
     * 変更画面に入力し、画面遷移する。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param purchaseQuantity 減算する数量
     */
    private void buy(int webDriverId, int purchaseQuantity) {
        buy(webDriverId, purchaseQuantity, -1);
    }

    /**
     * 変更画面に入力し、画面遷移する。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param purchaseQuantity 減算する数量
     * @param sleepMillis ロックする時間（厳密には、Thread#sleepする時間）
     */
    private void buy(int webDriverId, int purchaseQuantity, long sleepMillis) {
        WebDriverOperations operations = getWebDriverOperations(webDriverId);
        operations.overrideText(id("purchasingQuantity"), Integer.toString(
                purchaseQuantity));
        if (sleepMillis != -1) {
            operations.overrideText(id("sleepMillis"), Long.toString(
                    sleepMillis));
        }
        operations.click(id("buy"));
    }

    /**
     * 対象のブラウザが更新完了画面に遷移したかを返す。<br>
     * @param id ブラウザid
     * @return true 更新完了画面に遷移した
     */
    private boolean isUpdateSuccued(int id) {
        return getWebDriverOperations(id).exists(id("screenTitle"));
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
        assertThat(operations.getText(id("screenTitle")), is("売買完了"));
        assertThat(operations.getText(id("result_itemCode")), is(itemCode));
        assertThat(operations.getText(id("result_itemName")), is(itemName));
        assertThat(operations.getText(id("result_quantity")), is(Integer
                .toString(quantity)));
        assertThat(operations.getText(id("result_version")), is(Integer
                .toString(version)));
    }

    /**
     * 完了画面をアサートする。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param itemCode
     * @param itemName
     * @param quantity
     * @param version
     */
    private void assertCompletePageView(int webDriverId, String itemCode,
            String itemName, int quantity, int version) {

        WebDriverOperations operations = getWebDriverOperations(webDriverId);
        WebDriver driver = operations.getWebDriver();
        ExclusionResultPage exclusionResultPage = new ExclusionResultPage(driver);
        assertThat(operations.getText(id("screenTitle")), is("売買完了"));
        assertThat(exclusionResultPage.getItemCode(), is(itemCode));
        assertThat(exclusionResultPage.getItemName(), is(itemName));
        assertThat(exclusionResultPage.getQuantity(), is(Integer.toString(
                quantity)));
        assertThat(exclusionResultPage.getVersion(), is(Integer.toString(
                version)));
    }

    /**
     * 変更画面をアサートする。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param itemCode
     * @param itemName
     * @param quantity
     * @param version
     */
    private void assertUpdatePageView(int webDriverId, String itemCode,
            String itemName, int quantity, int version) {
        WebDriverOperations operations = getWebDriverOperations(webDriverId);
        WebDriver driver = operations.getWebDriver();
        ExclusionResultPage exclusionResultPage = new ExclusionResultPage(driver);
        assertThat(exclusionResultPage.getItemCode(), is(itemCode));
        assertThat(exclusionResultPage.getItemName(), is(itemName));
        assertThat(exclusionResultPage.getQuantity(), is(Integer.toString(
                quantity)));
        assertThat(exclusionResultPage.getVersion(), is(Integer.toString(
                version)));
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
     * 変更画面をアサートする。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param itemCode
     * @param itemName
     * @param quantity
     * @param version
     */
    private void assertUpdateView(int webDriverId, String itemCode,
            String itemName, int quantity, int version) {
        WebDriverOperations operations = getWebDriverOperations(webDriverId);
        assertThat(operations.getText(id("result_itemCode")), is(itemCode));
        assertThat(operations.getText(id("result_itemName")), is(itemName));
        assertThat(operations.getText(id("result_quantity")), is(Integer
                .toString(quantity)));
        assertThat(operations.getText(id("result_version")), is(Integer
                .toString(version)));
    }

    private void assertionForTestEXCN0301001(int completedId, int failedId,
            String testIdUpperCase, int quantity) {
        assertionTemplete(completedId, failedId, testIdUpperCase, quantity, 0,
                "/0301/001", "Business Error!\nOther user updated!!");
    }

    private void assertionForTestEXCN0302001(int completedId, int failedId,
            String testIdUpperCase, int quantity) {
        assertionTemplete(completedId, failedId, testIdUpperCase, quantity, 1,
                "/0302/001",
                "Exclusive Lock Error!\nOther user updated!!(controller)");
    }

    private void assertionForTestEXCN0302002(int completedId, int failedId,
            String testIdUpperCase, int quantity) {
        assertionTemplete(completedId, failedId, testIdUpperCase, quantity, 1,
                "/0302/002",
                "Exclusive Lock Error!\nOther user updated!!(request)");
    }

    private void assertionForTestEXCN0402001(int completedId, int failedId,
            String testIdUpperCase, int quantity) {
        assertionTemplete(completedId, failedId, testIdUpperCase, quantity, 0,
                "/0402/001",
                "Exclusive Lock Error!\nOther user updated!!(controller)");
    }

    private void assertionForTestEXCN0402002(int completedId, int failedId,
            String testIdUpperCase, int quantity) {
        assertionTemplete(completedId, failedId, testIdUpperCase, quantity, 0,
                "/0402/002",
                "Exclusive Lock Error!\nOther user updated!!(request)");
    }

    private void assertionForTestEXCN0501001(int completedId, int failedId,
            String testIdUpperCase, int quantity) {
        assertionTemplete(completedId, failedId, testIdUpperCase, quantity, 0,
                "/0501/001",
                "Business Error!\nNot enough stock. Please, change quantity!!");
    }

    private void assertionForTestEXCN0502001(int completedId, int failedId,
            String testIdUpperCase, int quantity) {
        assertionTemplete(completedId, failedId, testIdUpperCase, quantity, 1,
                "/0502/001",
                "Exclusive Lock Error!\nOther user updated!!(controller)");
    }

    private void assertionForTestEXCN0502002(int completedId1, int completedId2,
            String testIdUpperCase, int quantity1, int quantity2) {
        assertionUpdatingAllCompletedTemplate(completedId1, completedId2,
                testIdUpperCase, quantity1, quantity2, "/0502/002");
    }

    private void assertionForTestEXCN0602001(int completedId, int failedId,
            String testIdUpperCase, int quantity) {
        assertionTemplete(completedId, failedId, testIdUpperCase, quantity, 1,
                "/0602/001", "Exclusive Lock Error!\nOther user updated!!");
    }

    private void assertionForTestEXCN0602002(int completedId, int failedId,
            String testIdUpperCase, int quantity) {
        assertionTemplete(completedId, failedId, testIdUpperCase, quantity, 1,
                "/0602/002", "Exclusive Lock Error!\nOther user updated!!");
    }

    private void assertionForTestEXCN0602002WithoutNoWaitOption(
            int completedId1, int completedId2, String testIdUpperCase,
            int quantity1, int quantity2) {
        assertionUpdatingAllCompletedTemplate(completedId1, completedId2,
                testIdUpperCase, quantity1, quantity2, "/0602/002");
    }

    /**
     * 片方が更新に成功し、もう片方が失敗するテストのアサーションのテンプレート。
     * @param completedId 更新に成功したブラウザID
     * @param failedId 更新に失敗したブラウザID
     * @param testIdUpperCase テストID
     * @param quantity 在庫数
     * @param version バージョン
     * @param path テストケースのパス
     * @param message エラーメッセージ
     */
    private void assertionTemplete(int completedId, int failedId,
            String testIdUpperCase, int quantity, int version, String path,
            String message) {
        // 更新に成功したブラウザが完了画面に遷移すること・入力した変更内容が反映されること
        assertCompleteView(completedId, testIdUpperCase, testIdUpperCase,
                quantity, version);
        // 更新に失敗したブラウザが排他エラー完了画面に遷移すること・入力した変更内容が反映されないこと
        assertFailureView(failedId, message);
        getWebDriverOperations(failedId).displayPage(getPackageRootUrl()
                + path);
        assertUpdateView(failedId, testIdUpperCase, testIdUpperCase, quantity,
                version);
    }

    /**
     * 両ブラウザで更新に成功するテストのアサーションのテンプレート。
     * @param completedId1 バージョン1のブラウザID
     * @param completedId2 バージョン2のブラウザID
     * @param testIdUpperCase テストID
     * @param quantity1 バージョン1の画面の在庫数
     * @param quantity2 バージョン2の画面の在庫数
     * @param path テストケースのパス
     */
    private void assertionUpdatingAllCompletedTemplate(int completedId1,
            int completedId2, String testIdUpperCase, int quantity1,
            int quantity2, String path) {
        assertCompletePageView(completedId1, testIdUpperCase, testIdUpperCase,
                quantity1, 1);
        getWebDriverOperations(completedId2).displayPage(getPackageRootUrl()
                + path);
        assertUpdatePageView(completedId2, testIdUpperCase, testIdUpperCase,
                quantity2, 2);
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
