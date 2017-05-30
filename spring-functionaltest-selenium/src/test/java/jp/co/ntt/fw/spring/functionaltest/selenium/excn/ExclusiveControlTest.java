/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.excn;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupportForMultiBrowser;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebDriverOperations;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.ExclusionResultPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.StockPage;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;

/**
 * EXCN 排他制御テスト<br>
 * <p>
 * </p>
 */
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

            final CountDownLatch startSignal = new CountDownLatch(1);
            final CountDownLatch doneSignal = new CountDownLatch(2);

            final long sleepMillisThatWaitNextRequest = TimeUnit.SECONDS
                    .toMillis(3 + offsetSecondsOfWaitForNextRequest);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        buy(0, 3, sleepMillisThatWaitNextRequest, startSignal);
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
                        suspendWebDriver(1,
                                (sleepMillisThatWaitNextRequest / 2));
                        buy(1, 4, 0);
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

            // 画面2が、以下の条件を満たすこと
            // ・業務エラー完了画面に遷移すること
            // ・入力した変更内容が反映されないこと
            assertFailureView(1, "Business Error!\nOther user updated!!");
            getWebDriverOperations(1).displayPage(
                    getPackageRootUrl() + "/0301/001");
            assertUpdateView(1, testIdUpperCase, testIdUpperCase, 2, 0);
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
                        buy(0, 3, sleepMillisThatWaitNextRequest, startSignal);
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
                        suspendWebDriver(1,
                                (sleepMillisThatWaitNextRequest / 2));
                        buy(1, 4, sleepMillisThatWaitNextRequest);
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

        // テスト実行（2メソッド目）
        {
            // 疑似アプリの画面を表示するブラウザを2つ起動
            setUpWebDriver(2, testId);
            setUpWebDriver(3, testId);

            final CountDownLatch startSignal = new CountDownLatch(1);
            final CountDownLatch doneSignal = new CountDownLatch(2);

            final long sleepMillisThatWaitNextRequest = TimeUnit.SECONDS
                    .toMillis(3 + offsetSecondsOfWaitForNextRequest);

            // 画面3を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sale(2, 3, sleepMillisThatWaitNextRequest, startSignal);
                    } finally {
                        doneSignal.countDown();
                    }
                }
            }).start();

            // 画面4を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 画面3->画面4の順で画面操作が行われるようにするため、画面3でボタンが押下されるまで待機
                        startSignal.await();
                        // 画面3のボタン押下と実際にリクエストがサーバに届くタイムラグを考慮し、一定時間待機
                        suspendWebDriver(3,
                                (sleepMillisThatWaitNextRequest / 2));
                        sale(3, 4, sleepMillisThatWaitNextRequest);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    } finally {
                        doneSignal.countDown();
                    }
                }
            }).start();

            // 画面3と4の両方でボタンが押下されるまで待機
            doneSignal.await();
        }

        // 結果確認
        {
            // 画面1が、以下の条件を満たすこと
            // ・完了画面に遷移すること
            // ・入力した変更内容が反映されること
            assertCompleteView(0, testIdUpperCase, testIdUpperCase, 2, 1);

            // 画面2が、以下の条件を満たすこと
            // ・排他エラー完了画面に遷移すること
            // ・入力した変更内容が反映されないこと
            assertFailureView(1,
                    "Exclusive Lock Error!\nOther user updated!!(controller)");
            getWebDriverOperations(1).getWebDriver().navigate().to(
                    applicationContextUrl + "/excn/0302/001");
            assertUpdateView(1, testIdUpperCase, testIdUpperCase, 5, 2);

            // 画面3が、以下の条件を満たすこと
            // ・完了画面に遷移すること
            // ・入力した変更内容が反映されること
            assertCompleteView(2, testIdUpperCase, testIdUpperCase, 5, 2);

            // 画面4が、以下の条件を満たすこと
            // ・排他エラー完了画面に遷移すること
            // ・入力した変更内容が反映されないこと
            assertFailureView(3,
                    "Exclusive Lock Error!\nOther user updated!!(controller)");
            getWebDriverOperations(3).displayPage(
                    getPackageRootUrl() + "/0302/001");
            assertUpdateView(3, testIdUpperCase, testIdUpperCase, 5, 2);
        }
    }

    /**
     * EXCN0302002
     * <ul>
     * <li>複数リクエストにまたがったトランザクションの場合、別画面で取得したバージョンカラムの値を更新処理で参照することで、 バージョンの値が異なるとき、楽観ロック例外を明示的にスロー可能なことを確認する。</li>
     * </ul>
     */
    @Test
    public void testEXCN0302002() {
        String testId = "excn0302002";
        String testIdUpperCase = testId.toUpperCase();

        // 疑似アプリの画面を表示するブラウザを2つ起動
        setUpWebDriver(0, testId);
        setUpWebDriver(1, testId);

        // 画面1を操作する
        {
            // 操作
            buy(0, 3, 0);

            // 画面1が、以下の条件を満たすこと
            // ・完了画面に遷移すること
            // ・入力した変更内容が反映されること
            assertCompleteView(0, testIdUpperCase, testIdUpperCase, 2, 1);
        }

        // 画面2を操作する
        {
            // 操作
            buy(1, 4, 0);

            // 画面2が、以下の条件を満たすこと
            // ・排他エラー完了画面に遷移すること
            // ・入力した変更内容が反映されないこと
            assertFailureView(1,
                    "Exclusive Lock Error!\nOther user updated!!(request)");
            getWebDriverOperations(1).displayPage(
                    getPackageRootUrl() + "/0302/002");
            assertUpdateView(1, testIdUpperCase, testIdUpperCase, 2, 1);
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
    public void testEXCN0401002() {
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

            final CountDownLatch startSignal = new CountDownLatch(1);
            final CountDownLatch doneSignal = new CountDownLatch(2);

            final long sleepMillisThatWaitNextRequest = TimeUnit.SECONDS
                    .toMillis(6 + offsetSecondsOfWaitForNextRequest);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        buy(0, 3, sleepMillisThatWaitNextRequest, startSignal);
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
                        suspendWebDriver(1,
                                (sleepMillisThatWaitNextRequest / 2));
                        buy(1, 4, 0);
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

        // テスト実行（2メソッド目）
        {
            // 疑似アプリの画面を表示するブラウザを2つ起動
            setUpWebDriver(2, testId);
            setUpWebDriver(3, testId);

            final CountDownLatch startSignal = new CountDownLatch(1);
            final CountDownLatch doneSignal = new CountDownLatch(2);

            final long sleepMillisThatWaitNextRequest = TimeUnit.SECONDS
                    .toMillis(6 + offsetSecondsOfWaitForNextRequest);

            // 画面3を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sale(2, 3, sleepMillisThatWaitNextRequest, startSignal);
                    } finally {
                        doneSignal.countDown();
                    }
                }
            }).start();

            // 画面4を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 画面3->画面4の順で画面操作が行われるようにするため、画面1でボタンが押下されるまで待機
                        startSignal.await();
                        // 画面3のボタン押下と実際にリクエストがサーバに届くタイムラグを考慮し、一定時間待機
                        suspendWebDriver(3,
                                (sleepMillisThatWaitNextRequest / 2));
                        sale(3, 4, 0);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    } finally {
                        doneSignal.countDown();
                    }
                }
            }).start();

            // 画面3と4の両方でボタンが押下されるまで待機
            doneSignal.await();
        }

        // 結果確認
        {
            // 画面1が、以下の条件を満たすこと
            // ・完了画面に遷移すること
            // ・入力した変更内容が反映されること
            assertCompleteView(0, testIdUpperCase, testIdUpperCase, 2, 0);

            // 画面2が、以下の条件を満たすこと
            // ・排他エラー完了画面に遷移すること
            // ・入力した変更内容が反映されないこと
            assertFailureView(1,
                    "Exclusive Lock Error!\nOther user updated!!(controller)");
            getWebDriverOperations(1).displayPage(
                    getPackageRootUrl() + "/0402/001");
            assertUpdateView(1, testIdUpperCase, testIdUpperCase, 5, 0);

            // 画面3が、以下の条件を満たすこと
            // ・完了画面に遷移すること
            // ・入力した変更内容が反映されること
            assertCompleteView(2, testIdUpperCase, testIdUpperCase, 5, 0);

            // 画面4が、以下の条件を満たすこと
            // ・排他エラー完了画面に遷移すること
            // ・入力した変更内容が反映されないこと
            assertFailureView(3,
                    "Exclusive Lock Error!\nOther user updated!!(controller)");
            getWebDriverOperations(3).displayPage(
                    getPackageRootUrl() + "/0402/001");
            assertUpdateView(3, testIdUpperCase, testIdUpperCase, 5, 0);
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

            final CountDownLatch startSignal = new CountDownLatch(1);
            final CountDownLatch doneSignal = new CountDownLatch(2);

            final long sleepMillisThatWaitNextRequest = TimeUnit.SECONDS
                    .toMillis(6 + offsetSecondsOfWaitForNextRequest);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        buy(0, 3, sleepMillisThatWaitNextRequest, startSignal);
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
                        suspendWebDriver(1,
                                (sleepMillisThatWaitNextRequest / 2));
                        buy(1, 4, 0);
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

            // 画面2が、以下の条件を満たすこと
            // ・排他エラー完了画面に遷移すること
            // ・入力した変更内容が反映されないこと
            assertFailureView(1,
                    "Exclusive Lock Error!\nOther user updated!!(request)");
            getWebDriverOperations(1).displayPage(
                    getPackageRootUrl() + "/0402/002");
            assertUpdateView(0, testIdUpperCase, testIdUpperCase, 2, 0);
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

            final CountDownLatch startSignal = new CountDownLatch(1);
            final CountDownLatch doneSignal = new CountDownLatch(2);

            final long sleepMillisThatWaitNextRequest = TimeUnit.SECONDS
                    .toMillis(3 + offsetSecondsOfWaitForNextRequest);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        jpaBuy(0, 3, sleepMillisThatWaitNextRequest,
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
                        suspendWebDriver(1,
                                (sleepMillisThatWaitNextRequest / 2));
                        jpaBuy(1, 4, 0);
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
            assertCompletePageView(0, testIdUpperCase, testIdUpperCase, 2, 0);

            // 画面2が、以下の条件を満たすこと
            // ・業務エラー完了画面に遷移すること
            // ・入力した変更内容が反映されないこと
            assertFailureView(1,
                    "Business Error!\nNot enough stock. Please, change quantity!!");
            getWebDriverOperations(1).displayPage(
                    getPackageRootUrl() + "/0501/001");
            assertUpdatePageView(1, testIdUpperCase, testIdUpperCase, 2, 0);
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

            final CountDownLatch startSignal = new CountDownLatch(1);
            final CountDownLatch doneSignal = new CountDownLatch(2);

            final long sleepMillisThatWaitNextRequest = TimeUnit.SECONDS
                    .toMillis(3 + offsetSecondsOfWaitForNextRequest);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        jpaBuy(0, 3, sleepMillisThatWaitNextRequest,
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
                        suspendWebDriver(1,
                                (sleepMillisThatWaitNextRequest / 2));
                        jpaBuy(1, 4, sleepMillisThatWaitNextRequest);
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
            assertCompletePageView(0, testIdUpperCase, testIdUpperCase, 2, 1);

            assertFailureView(1,
                    "Exclusive Lock Error!\nOther user updated!!(controller)");
            getWebDriverOperations(1).getWebDriver().navigate().to(
                    applicationContextUrl + "/excn/0502/001");
            assertUpdatePageView(1, testIdUpperCase, testIdUpperCase, 2, 1);

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
                        suspendWebDriver(1,
                                (sleepMillisThatWaitNextRequest / 2));
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
            assertCompletePageView(0, testIdUpperCase, testIdUpperCase, 2, 1);

            getWebDriverOperations(1).getWebDriver().navigate().to(
                    applicationContextUrl + "/excn/0502/002");
            assertUpdatePageView(1, testIdUpperCase, testIdUpperCase, 1, 2);

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

            final CountDownLatch startSignal = new CountDownLatch(1);
            final CountDownLatch doneSignal = new CountDownLatch(2);

            final long sleepMillisThatWaitNextRequest = TimeUnit.SECONDS
                    .toMillis(3 + offsetSecondsOfWaitForNextRequest);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        jpaBuy(0, 3, sleepMillisThatWaitNextRequest,
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
                        suspendWebDriver(1,
                                (sleepMillisThatWaitNextRequest / 2));
                        jpaBuy(1, 4, sleepMillisThatWaitNextRequest);
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
            assertCompletePageView(0, testIdUpperCase, testIdUpperCase, 2, 1);

            assertFailureView(1, "Exclusive Lock Error!\nOther user updated!!");
            getWebDriverOperations(1).getWebDriver().navigate().to(
                    applicationContextUrl + "/excn/0602/001");
            assertUpdatePageView(1, testIdUpperCase, testIdUpperCase, 2, 1);

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

            final CountDownLatch startSignal = new CountDownLatch(1);
            final CountDownLatch doneSignal = new CountDownLatch(2);

            final long sleepMillisThatWaitNextRequest = TimeUnit.SECONDS
                    .toMillis(3 + offsetSecondsOfWaitForNextRequest);

            // 画面1を操作するスレッドを起動。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        jpaBuy(0, 3, sleepMillisThatWaitNextRequest,
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
                        suspendWebDriver(1,
                                (sleepMillisThatWaitNextRequest / 2));
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
            WebDriverOperations operations = getWebDriverOperations(0);
            WebDriver driver = operations.getWebDriver();
            ExclusionResultPage exclusionResultPage = new ExclusionResultPage(driver);

            String database = exclusionResultPage.getDatabase();
            assertCompletePageView(0, testIdUpperCase, testIdUpperCase, 2, 1);
            // TODO:Hibernateのバグ対応版（5.2.1.Final）にアップデート後、条件"POSTGRESQL".equals(database)を除去する
            // 修正理由:Hibernateのバグ(HHH-10797)によって、PostgreSQLで@QueryHint(name = "javax.persistence.lock.timeout", value =
            // "0")を指定しても、
            // NOWAIT句が付かなくなってしまったため
            if ("H2".equals(database) || "POSTGRESQL".equals(database)) {

                getWebDriverOperations(1).getWebDriver().navigate().to(
                        applicationContextUrl + "/excn/0602/002");
                assertUpdatePageView(1, testIdUpperCase, testIdUpperCase, 1, 2);

            } else {
                assertFailureView(1,
                        "Exclusive Lock Error!\nOther user updated!!");
                getWebDriverOperations(1).getWebDriver().navigate().to(
                        applicationContextUrl + "/excn/0602/002");
                assertUpdatePageView(1, testIdUpperCase, testIdUpperCase, 2, 1);
            }
        }
    }

    /**
     * 変更画面に入力し、画面遷移する。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param purchaseQuantity 減算する数量
     * @param sleepMillis ロックする時間（厳密には、Thread#sleepする時間）
     */
    private void buy(int webDriverId, int purchaseQuantity, long sleepMillis) {
        buy(webDriverId, purchaseQuantity, sleepMillis, null);
    }

    /**
     * 変更画面に入力し、画面遷移する。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param purchaseQuantity 減算する数量
     * @param sleepMillis ロックする時間（厳密には、Thread#sleepする時間）
     */
    private void jpaBuy(int webDriverId, int purchaseQuantity, long sleepMillis) {
        jpaBuy(webDriverId, purchaseQuantity, sleepMillis, null);
    }

    /**
     * 変更画面に入力し、画面遷移する。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param purchaseQuantity 減算する数量
     * @param sleepMillis ロックする時間（厳密には、Thread#sleepする時間）
     * @param startSignal 待機待ちの別スレッドに開始シグナルを送るためのオブジェクト
     */
    private void jpaBuy(int webDriverId, int purchaseQuantity,
            long sleepMillis, CountDownLatch startSignal) {
        WebDriverOperations operations = getWebDriverOperations(webDriverId);
        WebDriver driver = operations.getWebDriver();
        StockPage stockPage = new StockPage(driver);
        stockPage.setPurchaseQuantity(purchaseQuantity);
        stockPage.setSleepTime(sleepMillis);
        if (startSignal != null) {
            startSignal.countDown();
        }
        stockPage.buyWithRowLock();
    }

    /**
     * 変更画面に入力し、画面遷移する。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param purchaseQuantity 減算する数量
     * @param sleepMillis ロックする時間（厳密には、Thread#sleepする時間）
     * @param startSignal 待機待ちの別スレッドに開始シグナルを送るためのオブジェクト
     */
    private void buy(int webDriverId, int purchaseQuantity, long sleepMillis,
            CountDownLatch startSignal) {
        WebDriverOperations operations = getWebDriverOperations(webDriverId);
        operations.overrideText(id("purchasingQuantity"), Integer
                .toString(purchaseQuantity));
        operations.overrideText(id("sleepMillis"), Long.toString(sleepMillis));
        if (startSignal != null) {
            startSignal.countDown();
        }
        operations.click(id("buy"));
    }

    /**
     * 変更画面に入力し、画面遷移する。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param purchaseQuantity 加算する数量
     * @param sleepMillis ロックする時間（厳密には、Thread#sleepする時間）
     */
    private void sale(int webDriverId, int purchaseQuantity, long sleepMillis) {
        sale(webDriverId, purchaseQuantity, sleepMillis, null);
    }

    /**
     * 変更画面に入力し、画面遷移する。<br>
     * @param webDriverId 操作するブラウザを識別するためのID
     * @param purchaseQuantity 加算する数量
     * @param sleepMillis ロックする時間（厳密には、Thread#sleepする時間）
     * @param startSignal 待機待ちの別スレッドに開始シグナルを送るためのオブジェクト
     */
    private void sale(int webDriverId, int purchaseQuantity, long sleepMillis,
            CountDownLatch startSignal) {
        WebDriverOperations operations = getWebDriverOperations(webDriverId);
        operations.overrideText(id("purchasingQuantity"), Integer
                .toString(purchaseQuantity));
        operations.overrideText(id("sleepMillis"), Long.toString(sleepMillis));
        if (startSignal != null) {
            startSignal.countDown();
        }
        operations.click(id("sale"));
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
        assertThat(exclusionResultPage.getQuantity(), is(Integer
                .toString(quantity)));
        assertThat(exclusionResultPage.getVersion(), is(Integer
                .toString(version)));
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
        assertThat(exclusionResultPage.getQuantity(), is(Integer
                .toString(quantity)));
        assertThat(exclusionResultPage.getVersion(), is(Integer
                .toString(version)));
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
