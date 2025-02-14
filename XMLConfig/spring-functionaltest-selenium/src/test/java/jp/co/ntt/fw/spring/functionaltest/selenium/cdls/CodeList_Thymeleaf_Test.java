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
package jp.co.ntt.fw.spring.functionaltest.selenium.cdls;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementWithText;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Value;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class CodeList_Thymeleaf_Test extends FunctionTestSupport {

    @Value("${selenium.cdls.refreshInterval.offsetSeconds:15}")
    private int cronRefreshCodeListInterval;

    private static final String VIEW_TYPE = "thymeleaf";

    @Before
    public void setUpTest() {
        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());
    }

    /**
     * <ul>
     * <li>xmlファイルに定義したSimpleMapCodeListを読み込み、画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0101001() {
        Map<String, String> expectedValue = expectedOrderStatusValueFactory();
        expectedValue.put("", "--Select--");
        moveTestViewAndAssertOptions("cdls0101001_" + VIEW_TYPE, expectedValue);
    }

    /**
     * <ul>
     * <li>xmlファイルに定義したSimpleMapCodeListをJavaクラスで読み込み、画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0101002() {
        moveTestViewAndAssertOptionValue("cdls0101002_" + VIEW_TYPE,
                expectedOrderStatusValueFactory());
    }

    /**
     * <ul>
     * <li>xmlファイルに定義したNumberRangeCodeListを読み込み、画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0102001() {
        moveTestViewAndAssertOptions("cdls0102001_" + VIEW_TYPE, expectedDepMonthAscValueFactory());
    }

    /**
     * <ul>
     * <li>xmlファイルに定義したNumberRangeCodeListをJavaクラスで読み込み、画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0102002() {
        moveTestViewAndAssertOptionValue("cdls0102002_" + VIEW_TYPE,
                expectedDepMonthAscValueFactory());
    }

    /**
     * <ul>
     * <li>DBに定義したコードリストを読み込み、画面にセレクトボックスで表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0103001() {
        moveTestViewAndAssertOptions("cdls0103001_" + VIEW_TYPE, expectedAuthorityValueFactory());
    }

    /**
     * <ul>
     * <li>DBに定義したコードリストをJavaクラスで読み込み、画面にセレクトボックスで表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0103002() {
        moveTestViewAndAssertOptionValue("cdls0103002_" + VIEW_TYPE,
                expectedAuthorityValueFactory());
    }

    /**
     * <ul>
     * <li>DBに定義したコードリストを読み込み、画面にチェックボックスで表示できること。</li>
     * <li>チェックボックスを選択してSubmitすると、選択したチェックボックスの値を表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0103003() {
        String testId = "cdls0103003_" + VIEW_TYPE;

        // メニュー画面の操作
        webDriverOperations.click(id(testId));
        webDriverOperations.waitForDisplayed(
                invisibilityOfElementWithText(By.id("screenTitle"), "CDLS コードリスト機能"));

        // 遷移先のページでのcheckboxの内容assert
        List<WebElement> spans = webDriverOperations.getWebDriver().findElement(By.id(testId))
                .findElements(By.tagName("span"));
        if (spans.size() == 0) {
            fail("The span tags could not get.");
        }
        Object[] keys = expectedAuthorityValueFactory().keySet().toArray();
        int i = 0;
        for (WebElement span : spans) {
            String inputValue = span.findElement(By.tagName("input")).getAttribute("value");
            assertThat(inputValue, is((String) keys[i++]));
            assertThat(span.findElement(By.tagName("label")).getText(),
                    is(expectedAuthorityValueFactory().get(inputValue)));
        }

        // checkboxを複数選択してsubmitボタン押下
        webDriverOperations.click(id("authorities2"));
        webDriverOperations.click(id("authorities4"));
        webDriverOperations.click(id(testId + "Submit"));
        webDriverOperations.waitForDisplayed(
                invisibilityOfElementWithText(By.id("screenTitle"), "CDLS コードリスト機能"));

        // 表示された内容assert
        List<WebElement> divs = webDriverOperations.getWebDriver()
                .findElement(By.id(testId + "Result")).findElements(By.tagName("div"));
        if (divs.size() != 2) {
            fail("The div tags count is not two.");
        }
        assertThat(divs.get(0).getText(), is("MASTER_MANAGEMENT from DB"));
        assertThat(divs.get(1).getText(), is("ORDER_MANAGEMENT from DB"));
    }

    /**
     * <ul>
     * <li>JavaのEnumに定義したEnumCodeListを読み込み、画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0104001() {
        moveTestViewAndAssertOptions("cdls0104001_" + VIEW_TYPE, expectedOrderStatusValueFactory());
    }

    /**
     * <ul>
     * <li>JavaのEnumに定義したEnumCodeListを他のJavaクラスで読み込み、画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0104002() {
        moveTestViewAndAssertOptionValue("cdls0104002_" + VIEW_TYPE,
                expectedOrderStatusValueFactory());
    }

    /**
     * <ul>
     * <li>Localeが"ja"のとき、xmlファイルに定義したSimpleI18nCodeListを読み込み、日本語で画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0105001() {
        moveTestViewAndAssertOptions("cdls0105001_" + VIEW_TYPE,
                expectedPriceXmlFactory().get("ja"));
    }

    /**
     * <ul>
     * <li>Localeが"en"のとき、xmlファイルに定義したSimpleI18nCodeListを読み込み、英語で画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0105002() {
        moveTestViewAndAssertOptions("cdls0105002_" + VIEW_TYPE,
                expectedPriceXmlFactory().get("en"));
    }

    /**
     * <ul>
     * <li>Localeが"ja"のとき、xmlファイルに定義したSimpleI18nCodeListをJavaクラスで読み込み、日本語で画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0105003() {
        moveTestViewAndAssertOptionValue("cdls0105003_" + VIEW_TYPE,
                expectedPriceXmlFactory().get("ja"));
    }

    /**
     * <ul>
     * <li>Localeが"en"のとき、xmlファイルに定義したSimpleI18nCodeListをJavaクラスで読み込み、英語で画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0105004() {
        moveTestViewAndAssertOptionValue("cdls0105004_" + VIEW_TYPE,
                expectedPriceXmlFactory().get("en"));
    }

    /**
     * <ul>
     * <li>Localeが"ja"のとき、DBに定義したSimpleI18nCodeListの値を読み込み、日本語で画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0105005() {
        moveTestViewAndAssertOptions("cdls0105005_" + VIEW_TYPE,
                expectedPriceDBFactory().get("ja"));
    }

    /**
     * <ul>
     * <li>Localeが"en"のとき、DBに定義したSimpleI18nCodeListの値を読み込み、英語で画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0105006() {
        moveTestViewAndAssertOptions("cdls0105006_" + VIEW_TYPE,
                expectedPriceDBFactory().get("en"));
    }

    /**
     * <ul>
     * <li>Localeが"ja"のとき、DBに定義したSimpleI18nCodeListの値をJavaクラスで読み込み、日本語で画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0105007() {
        moveTestViewAndAssertOptionValue("cdls0105007_" + VIEW_TYPE,
                expectedPriceDBFactory().get("ja"));
    }

    /**
     * <ul>
     * <li>Localeが"en"のとき、DBに定義したSimpleI18nCodeListの値をJavaクラスで読み込み、英語で画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0105008() {
        moveTestViewAndAssertOptionValue("cdls0105008_" + VIEW_TYPE,
                expectedPriceDBFactory().get("en"));
    }

    /**
     * <ul>
     * <li>コードリストの特定のコード値を指定し、対応するコード名を画面に表示できること</li>
     * </ul>
     */
    @Test
    public void testCDLS0106001() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("cdls0106001_" + VIEW_TYPE));
        }

        // 遷移先のページでのoptionの内容
        {
            assertThat(webDriverOperations.getText(id("cdls0106001_" + VIEW_TYPE)),
                    is("Order Status : Sent"));
        }
    }

    /**
     * <ul>
     * <li>コードリストの入力チェックを行い、コード値にある値のときエラーにならないこと</li>
     * </ul>
     */
    @Test
    public void testCDLS0107001() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("cdls0107001_" + VIEW_TYPE));
        }

        // Submit成功ページに遷移すること
        {
            getOptionSelector(By.id("cdls0107001_" + VIEW_TYPE)).selectByIndex(1);
            webDriverOperations.click(By.id("submitCDLS0107001"));
            assertThat(webDriverOperations.getText(id("submitSuscess")), is("Submit Success"));
        }
    }

    /**
     * <ul>
     * <li>コードリストの入力チェックを行い、コード値にない値のときエラーになること</li>
     * </ul>
     */
    @Test
    public void testCDLS0107002() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("cdls0107002_" + VIEW_TYPE));
        }

        // 入力チェックエラーメッセージが出力をされていること
        {
            getOptionSelector(By.id("cdls0107002_" + VIEW_TYPE)).selectByIndex(0);
            webDriverOperations.click(By.id("submitCDLS0107002"));
            assertThat(webDriverOperations.getText(id("errorCDLS0107002")),
                    is("\"id\" must exist in code list of CL_ORDERSTATUS."));
        }
    }

    /**
     * <ul>
     * <li>JdbcCodeListを使ったコードリストを一定時間ごとに再読み込みし、更新後のコードリストを画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0201001() {

        final String optionsElementId = "cdls0201001_" + VIEW_TYPE;
        final String submitButtonId = "updateCdls0201001_" + VIEW_TYPE;
        final String textAriaId = "cronValue";

        // メニュー画面の操作
        {
            webDriverOperations.click(id(optionsElementId));
        }

        // DBの値を元に戻す
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadCodeListInitValueFactory().values()), textAriaId,
                    submitButtonId);
            webDriverOperations.suspend(cronRefreshCodeListInterval, TimeUnit.SECONDS);
            webDriverOperations.refresh();
        }

        // 遷移先のページでのoptionの内容
        {
            assertOptions(optionsElementId, expectedReloadCodeListInitValueFactory());
        }

        // DBに保存されている Code List のアップデート
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadCodeListUpdatedValueFactory().values()),
                    textAriaId, submitButtonId);
        }

        // Task Scheduler でリフレッシュされたらページをリロードして、更新後の値でアサート
        {
            webDriverOperations.suspend(cronRefreshCodeListInterval, TimeUnit.SECONDS);
            webDriverOperations.refresh();

            assertOptions(optionsElementId, expectedReloadCodeListUpdatedValueFactory());
        }
    }

    /**
     * <ul>
     * <li>ControllerクラスでJdbcCodeListを使ったコードリストのrefreshメソッドを呼び出し、更新後のコードリストを画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0201002() {

        final String optionsElementId = "cdls0201002_" + VIEW_TYPE;
        final String submitButtonId = "updateCdls0201002_" + VIEW_TYPE;
        final String textAriaId = "refreshValue";

        // メニュー画面の操作
        {
            webDriverOperations.click(id(optionsElementId));
        }

        // DBの値を元に戻す
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadCodeListInitValueFactory().values()), textAriaId,
                    submitButtonId);
            webDriverOperations.click(By.id("codeListRefreshEndPoint"));
        }

        // 遷移先のページでのoptionの内容
        {
            assertOptions(optionsElementId, expectedReloadCodeListInitValueFactory());
        }

        // DBに保存されている Code List のアップデート
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadCodeListUpdatedValueFactory().values()),
                    textAriaId, submitButtonId);
        }

        // リフレッシュエンドポイントをクリックし、更新後の値でアサート
        {
            webDriverOperations.click(By.id("codeListRefreshEndPoint"));
            assertOptions(optionsElementId, expectedReloadCodeListUpdatedValueFactory());
        }

        // DBの値を元に戻す
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadCodeListInitValueFactory().values()), textAriaId,
                    submitButtonId);
            webDriverOperations.click(By.id("codeListRefreshEndPoint"));
        }
    }

    /**
     * <ul>
     * <li>SimpleReloadableI18nCodeListを使ったlocaleがjaのコードリストを一定時間ごとに再読み込みし、更新後のコードリストを画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0202001() {

        final String optionsElementId = "cdls0202001_" + VIEW_TYPE;
        final String submitButtonId = "updateCdls0202001_" + VIEW_TYPE;
        final String textAriaId = "cronValue2";

        // メニュー画面の操作
        {
            webDriverOperations.click(id(optionsElementId));
        }

        // DBの値を元に戻す
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadPriceJaCodeListInitValueFactory().values()),
                    textAriaId, submitButtonId);
            webDriverOperations.suspend(cronRefreshCodeListInterval, TimeUnit.SECONDS);
            webDriverOperations.refresh();
        }

        // 遷移先のページでのoptionの内容
        {
            assertOptions(optionsElementId, expectedReloadPriceJaCodeListInitValueFactory());
        }

        // DBに保存されている Code List のアップデート
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadPriceJaCodeListUpdatedValueFactory().values()),
                    textAriaId, submitButtonId);
        }

        // Task Scheduler でリフレッシュされたらページをリロードして、更新後の値でアサート
        {
            webDriverOperations.suspend(cronRefreshCodeListInterval, TimeUnit.SECONDS);
            webDriverOperations.refresh();

            assertOptions(optionsElementId, expectedReloadPriceJaCodeListUpdatedValueFactory());
        }
    }

    /**
     * <ul>
     * <li>SimpleReloadableI18nCodeListを使ったlocaleがenのコードリストを一定時間ごとに再読み込みし、更新後のコードリストを画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0202002() {

        final String optionsElementId = "cdls0202002_" + VIEW_TYPE;
        final String submitButtonId = "updateCdls0202002_" + VIEW_TYPE;
        final String textAriaId = "cronValue3";

        // メニュー画面の操作
        {
            webDriverOperations.click(id(optionsElementId));
        }

        // DBの値を元に戻す
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadPriceEnCodeListInitValueFactory().values()),
                    textAriaId, submitButtonId);
            webDriverOperations.suspend(cronRefreshCodeListInterval, TimeUnit.SECONDS);
            webDriverOperations.refresh();
        }

        // 遷移先のページでのoptionの内容
        {
            assertOptions(optionsElementId, expectedReloadPriceEnCodeListInitValueFactory());
        }

        // DBに保存されている Code List のアップデート
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadPriceEnCodeListUpdatedValueFactory().values()),
                    textAriaId, submitButtonId);
        }

        // Task Scheduler でリフレッシュされたらページをリロードして、更新後の値でアサート
        {
            webDriverOperations.suspend(cronRefreshCodeListInterval, TimeUnit.SECONDS);
            webDriverOperations.refresh();

            assertOptions(optionsElementId, expectedReloadPriceEnCodeListUpdatedValueFactory());
        }
    }

    /**
     * <ul>
     * <li>ControllerクラスでSimpleReloadableI18nCodeListを使ったlocaleがjaのコードリストのrefreshメソッドを呼び出し、更新後のコードリストを画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0202003() {

        final String optionsElementId = "cdls0202003_" + VIEW_TYPE;
        final String submitButtonId = "updateCdls0202003_" + VIEW_TYPE;
        final String textAriaId = "refreshValue2";

        // メニュー画面の操作
        {
            webDriverOperations.click(id(optionsElementId));
        }

        // DBの値を元に戻す
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadPriceJaCodeListInitValueFactory().values()),
                    textAriaId, submitButtonId);
            webDriverOperations.click(By.id("codeListRefreshEndPoint2"));
        }

        // 遷移先のページでのoptionの内容
        {
            assertOptions(optionsElementId, expectedReloadPriceJaCodeListInitValueFactory());
        }

        // DBに保存されている Code List のアップデート
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadPriceJaCodeListUpdatedValueFactory().values()),
                    textAriaId, submitButtonId);
        }

        // リフレッシュエンドポイントをクリックし、更新後の値でアサート
        {
            webDriverOperations.click(By.id("codeListRefreshEndPoint2"));
            assertOptions(optionsElementId, expectedReloadPriceJaCodeListUpdatedValueFactory());
        }

        // DBの値を元に戻す
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadPriceJaCodeListInitValueFactory().values()),
                    textAriaId, submitButtonId);
            webDriverOperations.click(By.id("codeListRefreshEndPoint2"));
        }
    }

    /**
     * <ul>
     * <li>ControllerクラスでSimpleReloadableI18nCodeListを使ったlocaleがjaのコードリストのrefreshメソッドを呼び出し、更新後のコードリストを画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0202004() {

        final String optionsElementId = "cdls0202004_" + VIEW_TYPE;
        final String submitButtonId = "updateCdls0202004_" + VIEW_TYPE;
        final String textAriaId = "refreshValue3";

        // メニュー画面の操作
        {
            webDriverOperations.click(id(optionsElementId));
        }

        // DBの値を元に戻す
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadPriceEnCodeListInitValueFactory().values()),
                    textAriaId, submitButtonId);
            webDriverOperations.click(By.id("codeListRefreshEndPoint3"));
        }

        // 遷移先のページでのoptionの内容
        {
            assertOptions(optionsElementId, expectedReloadPriceEnCodeListInitValueFactory());
        }

        // DBに保存されている Code List のアップデート
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadPriceEnCodeListUpdatedValueFactory().values()),
                    textAriaId, submitButtonId);
        }

        // リフレッシュエンドポイントをクリックし、更新後の値でアサート
        {
            webDriverOperations.click(By.id("codeListRefreshEndPoint3"));
            assertOptions(optionsElementId, expectedReloadPriceEnCodeListUpdatedValueFactory());
        }

        // DBの値を元に戻す
        {
            updateReloadableCodeListDBValue(optionsElementId,
                    new ArrayList<>(expectedReloadPriceEnCodeListInitValueFactory().values()),
                    textAriaId, submitButtonId);
            webDriverOperations.click(By.id("codeListRefreshEndPoint3"));
        }
    }

    /**
     * <ul>
     * <li>AbstractCodeList を実装した独自カスタマイズコードリストから、コードリストを取得し、画面に表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0301001() {

        final String optionsElementId = "cdls0301001_" + VIEW_TYPE;
        // メニュー画面の操作
        webDriverOperations.click(id(optionsElementId));
        // システムの西暦を画面から取得
        String systemYear = webDriverOperations.getText(By.id("systemYear"));

        assertOptions(optionsElementId, exptectedDepYearFactory(systemYear));
    }

    /**
     * <ul>
     * <li>Localeが"ja"のとき、xmlファイルで定義したSimpleI18nCodeListのrowsプロパティに対して、”MapのMap”を設定し、画面に表示できること(外側のMapのkeyはjava.lang.Localeである)。</li>
     * </ul>
     */
    @Test
    public void testCDLS0401001() {
        moveTestViewAndAssertOptions("cdls0401001_" + VIEW_TYPE,
                expectedPriceXmlFactory().get("ja"));
    }

    /**
     * <ul>
     * <li>Localeが"en"のとき、xmlファイルで定義したSimpleI18nCodeListのrowsプロパティに対して、”MapのMap”を設定し、画面に表示できること(外側のMapのkeyはコード値である)。</li>
     * </ul>
     */
    @Test
    public void testCDLS0401002() {
        moveTestViewAndAssertOptions("cdls0401002_" + VIEW_TYPE,
                expectedPriceXmlFactory().get("en"));
    }

    /**
     * <ul>
     * <li>Localeが"ja"のとき、xmlファイルで定義したSimpleI18nCodeListのrowsプロパティに対して、”MapのMap”を設定し、画面に表示できること(外側のMapのkeyはコード値)。</li>
     * </ul>
     */
    @Test
    public void testCDLS0402001() {
        moveTestViewAndAssertOptions("cdls0402001_" + VIEW_TYPE,
                expectedPriceXmlFactory().get("ja"));
    }

    /**
     * <ul>
     * <li>Localeが"en"のとき、xmlファイルで定義したSimpleI18nCodeListのrowsプロパティに対して、”MapのMap”を設定し、画面に表示できること(外側のMapのkeyはコード値)。</li>
     * </ul>
     */
    @Test
    public void testCDLS0402002() {
        moveTestViewAndAssertOptions("cdls0402002_" + VIEW_TYPE,
                expectedPriceXmlFactory().get("en"));
    }

    /**
     * <ul>
     * <li>xmlファイルに定義したNumberRangeCodeListで降順になるように設定し、画面に降順に数値が表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0403001() {
        moveTestViewAndAssertOptions("cdls0403001_" + VIEW_TYPE, expectedDepMonthDesValueFactory());
    }

    /**
     * <ul>
     * <li>xmlファイルに定義したNumberRangeCodeListでinvetval値を2以上に設定し、interval値分だけ数値が増加していること。</li>
     * <li>intervalの値分増加した時に、toの値と同じになって繰り返しが終了すること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0403002() {
        moveTestViewAndAssertOptions("cdls0403002_" + VIEW_TYPE,
                expectedNumberRange10IntervalValueFactory());
    }

    /**
     * <ul>
     * <li>xmlファイルに定義したNumberRangeCodeListでinvetval値を2以上に設定し、interval値分だけ数値が増加していること。</li>
     * <li>intervalの値分増加した時に、toの値を超えた値が表示されないこと。</li>
     * </ul>
     */
    @Test
    public void testCDLS0403003() {
        moveTestViewAndAssertOptions("cdls0403003_" + VIEW_TYPE,
                expectedNumberRange10IntervalValueFactory());
    }

    /**
     * <ul>
     * <li>テンプレートエンジンでSpEL式を記述して、コードリストBeanを参照できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0501001() {
        moveTestViewAndAssertOptions("cdls0501001_" + VIEW_TYPE, expectedOrderStatusValueFactory());
    }

    /**
     * <ul>
     * <li>ロケールが"ja"のとき、テンプレートエンジンでSpEL式を記述して、SimpleI18nCodeList Beanを参照し、日本語で表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0501002() {
        moveTestViewAndAssertOptions("cdls0501002_" + VIEW_TYPE,
                expectedPriceXmlFactory().get("ja"));
    }

    /**
     * <ul>
     * <li>ロケールが"en"のとき、テンプレートエンジンでSpEL式を記述して、SimpleI18nCodeList Beanを参照し、英語で表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0501003() {
        moveTestViewAndAssertOptions("cdls0501003_" + VIEW_TYPE,
                expectedPriceXmlFactory().get("en"));
    }

    /**
     * <ul>
     * <li>ロケールがSimpleI18nCodeList Beanに定義されているロケール以外のとき、テンプレートエンジンでSpEL式を記述して、SimpleI18nCodeList
     * Beanを参照し、英語で表示できること。</li>
     * </ul>
     */
    @Test
    public void testCDLS0501004() {
        moveTestViewAndAssertOptions("cdls0501004_" + VIEW_TYPE,
                expectedPriceXmlFactory().get("en"));
    }

    /**
     * <ul>
     * <li>コードリストの更新でDBの値を更新する。</li>
     * </ul>
     * 
     * @param testId 試験項目id
     * @param values 更新後の値
     * @param textAriaId 更新後の値を書き込むテキストボックスのID
     * @param submitButtonId submit ボタンのID
     */
    private void updateReloadableCodeListDBValue(String testId, List<String> values,
            String textAriaId, String submitButtonId) {
        for (int i = 0; i < values.size(); i++) {
            getOptionSelector(By.id(testId)).selectByIndex(i);
            inputTextFormSubmit(textAriaId, values.get(i), submitButtonId);
        }
    }

    /**
     * <ul>
     * <li>指定したテキストボックスに入力し、submit ボタンを押下する。</li>
     * </ul>
     * 
     * @param textAriaId テキストボックスID
     * @param text 入力するテキストの内容
     * @param submitButtonId submit ボタンID
     */
    private void inputTextFormSubmit(String textAriaId, String text, String submitButtonId) {
        webDriverOperations.overrideText(By.id(textAriaId), text);
        webDriverOperations.click(id(submitButtonId));
    }

    /**
     * <ul>
     * <li>assert本体</li>
     * </ul>
     * 
     * @param testId 試験項目id
     * @param expectedValue 期待値
     */
    private void moveTestViewAndAssertOptions(String testId, Map<String, String> expectedValue) {
        // メニュー画面の操作
        webDriverOperations.click(id(testId));
        webDriverOperations.waitForDisplayed(
                invisibilityOfElementWithText(By.id("screenTitle"), "CDLS コードリスト機能"));

        // 遷移先のページでのoptionの内容assert
        assertOptions(testId, expectedValue);
    }

    /**
     * <ul>
     * <li>assert本体</li>
     * </ul>
     * 
     * @param testId 試験項目id
     * @param expectedValue 期待値
     */
    private void moveTestViewAndAssertOptionValue(String testId,
            Map<String, String> expectedValue) {
        // メニュー画面の操作
        webDriverOperations.click(id(testId));
        webDriverOperations.waitForDisplayed(
                invisibilityOfElementWithText(By.id("screenTitle"), "CDLS コードリスト機能"));

        // 遷移先のページでのoptionの内容assert
        assertOptionValue(testId, expectedValue);
    }

    /**
     * <ul>
     * <li>optionタグのassert</li>
     * </ul>
     * 
     * @param testId 試験項目id
     * @param expectedValue 期待値
     */
    private void assertOptions(String testId, Map<String, String> expectedValue) {
        List<WebElement> actualOptions = getOptionSelector(By.id(testId)).getOptions();
        if (actualOptions.size() == 0) {
            fail("The option tags could not get.");
        }
        for (WebElement webElement : actualOptions) {
            assertThat(webElement.getText(),
                    is(expectedValue.get(webElement.getAttribute("value").trim())));
        }
    }

    /**
     * <ul>
     * <li>key値をsubmitして、対応するvalue値が出力されるかをアサートする。</li>
     * </ul>
     * 
     * @param testId testId 試験項目id
     * @param expectedValue 期待値
     */
    private void assertOptionValue(String testId, Map<String, String> expectedValue) {

        int actualOptionsSize = getOptionSelector(By.id(testId)).getOptions().size();
        if (actualOptionsSize == 0) {
            fail("The option tags could not get.");
        }

        String submitButtonId = testId + "Submit";
        String resultTextId = testId + "Result";
        for (String codeListKey : expectedValue.keySet()) {

            getOptionSelector(By.id(testId)).selectByValue(codeListKey);
            webDriverOperations.click(id(submitButtonId));

            assertThat(webDriverOperations.getText(By.id(resultTextId)),
                    is(expectedValue.get(codeListKey)));
        }
    }

    /**
     * <ul>
     * <li>selectオブジェクトを取得する。</li>
     * </ul>
     * 
     * @param by selectタグの指定
     * @return select オブジェクト
     */
    private Select getOptionSelector(By by) {
        return new Select(webDriverOperations.getWebDriver().findElement(by));
    }

    /**
     * SimpleMapCodeList使用時の期待値を作成する。
     * 
     * @return SimpleMapCodeList使用時の期待値
     */
    private Map<String, String> expectedOrderStatusValueFactory() {
        Map<String, String> orderStatus = new LinkedHashMap<>();
        orderStatus.put("1", "Received");
        orderStatus.put("2", "Sent");
        orderStatus.put("3", "Cancelled");
        return orderStatus;
    }

    /**
     * NumberRangeCodeListを使用して1から12まで昇順で生成した時の期待値を作成する。
     * 
     * @return NumberRangeCodeList使用時の期待値
     */
    private Map<String, String> expectedDepMonthAscValueFactory() {
        Map<String, String> depMonth = new LinkedHashMap<>();
        for (int i = 1; i <= 12; i++) {
            depMonth.put(String.valueOf(i), String.format("%02d", i));
        }
        return depMonth;
    }

    /**
     * NumberRangeCodeListを使用して1から12まで降順で生成した時の期待値を作成する。
     * 
     * @return NumberRangeCodeList使用時の期待値
     */
    private Map<String, String> expectedDepMonthDesValueFactory() {
        Map<String, String> depMonth = new LinkedHashMap<>();
        for (int i = 12; i >= 1; i--) {
            depMonth.put(String.valueOf(i), String.format("%02d", i));
        }
        return depMonth;
    }

    /**
     * NumberRangeCodeListでinterval値を10に設定したときの期待値を作成する。
     * 
     * @return NumberRangeCodeList使用時の期待値
     */
    private Map<String, String> expectedNumberRange10IntervalValueFactory() {
        Map<String, String> intervalRange = new LinkedHashMap<>();
        for (int i = 10; i <= 55; i += 10) {
            intervalRange.put(String.valueOf(i), String.format("%02d", i));
        }
        return intervalRange;
    }

    /**
     * JdbcCodeList使用時の期待値を作成する。
     * 
     * @return JdbcCodeList使用時の期待値
     */
    private Map<String, String> expectedAuthorityValueFactory() {
        Map<String, String> authority = new LinkedHashMap<>();
        authority.put("01", "STAFF_MANAGEMENT from DB");
        authority.put("02", "MASTER_MANAGEMENT from DB");
        authority.put("03", "STOCK_MANAGEMENT from DB");
        authority.put("04", "ORDER_MANAGEMENT from DB");
        authority.put("05", "SHOW_SHOPPING_CENTER from DB");
        return authority;
    }

    /**
     * SimpleI18nCodeList使用してxmlコード値設定した時の期待値を作成する。
     * 
     * @return SimpleI18nCodeList使用時の期待値
     */
    private Map<String, Map<String, String>> expectedPriceXmlFactory() {

        Map<String, String> i18nEn = new LinkedHashMap<>();
        i18nEn.put("0", "unlimited");
        i18nEn.put("10000", "Less than \\10,000");
        i18nEn.put("20000", "Less than \\20,000");
        i18nEn.put("30000", "Less than \\30,000");

        Map<String, String> i18nJa = new LinkedHashMap<>();
        i18nJa.put("0", "上限なし");
        i18nJa.put("10000", "10,000円以下");
        i18nJa.put("20000", "20,000円以下");
        i18nJa.put("30000", "30,000円以下");

        Map<String, Map<String, String>> price = new LinkedHashMap<>();
        price.put("en", i18nEn);
        price.put("ja", i18nJa);

        return price;
    }

    /**
     * SimpleI18nCodeList使用してDBでコード値設定した時の期待値を作成する。
     * 
     * @return SimpleI18nCodeList使用時の期待値
     */
    private Map<String, Map<String, String>> expectedPriceDBFactory() {

        Map<String, String> i18nEn = new LinkedHashMap<>();
        i18nEn.put("0", "unlimited from DB");
        i18nEn.put("10000", "Less than \\10,000 from DB");
        i18nEn.put("20000", "Less than \\20,000 from DB");
        i18nEn.put("30000", "Less than \\30,000 from DB");

        Map<String, String> i18nJa = new LinkedHashMap<>();
        i18nJa.put("0", "上限なし DBから取得");
        i18nJa.put("10000", "10,000円以下 DBから取得");
        i18nJa.put("20000", "20,000円以下 DBから取得");
        i18nJa.put("30000", "30,000円以下 DBから取得");

        Map<String, Map<String, String>> price = new LinkedHashMap<>();
        price.put("en", i18nEn);
        price.put("ja", i18nJa);

        return price;
    }

    /**
     * AbstractCodeList使用時の期待値を作成する。
     * 
     * @return AbstractCodeList使用時の期待値
     */
    private Map<String, String> exptectedDepYearFactory(String dateTime) {

        String thisYear = dateTime;
        String nextYear = String.valueOf(Integer.valueOf(dateTime) + 1);

        Map<String, String> year = new LinkedHashMap<>();
        year.put(thisYear, thisYear);
        year.put(nextYear, nextYear);
        return year;
    }

    /**
     * t_authorityテーブルのコードリストの再読み込み時試験のときの初期値を作成する。
     * 
     * @return t_authorityテーブルのコードリストの再読み込み時試験のときの初期値
     */
    private Map<String, String> expectedReloadCodeListInitValueFactory() {
        Map<String, String> reloadCodeListInitValues = new LinkedHashMap<>();
        reloadCodeListInitValues.put("01", "STAFF_MANAGEMENT from DB");
        reloadCodeListInitValues.put("02", "MASTER_MANAGEMENT from DB");
        reloadCodeListInitValues.put("03", "STOCK_MANAGEMENT from DB");
        reloadCodeListInitValues.put("04", "ORDER_MANAGEMENT from DB");
        reloadCodeListInitValues.put("05", "SHOW_SHOPPING_CENTER from DB");
        return reloadCodeListInitValues;
    }

    /**
     * t_authorityテーブルのコードリストの再読み込み時試験のときの更新後の期待値を作成する。
     * 
     * @return t_authorityテーブルのコードリストの再読み込み時試験のときの更新後の期待値
     */
    private Map<String, String> expectedReloadCodeListUpdatedValueFactory() {
        Map<String, String> reloadCodeListInitValues = new LinkedHashMap<>();
        for (int i = 1; i <= 5; i++) {
            reloadCodeListInitValues.put(String.format("%02d", i), "-" + (i * 100));
        }
        return reloadCodeListInitValues;
    }

    /**
     * t_priceテーブルのlocaleがjaの場合のコードリストの再読み込み時試験のときの初期値を作成する。
     * 
     * @return コードリストの再読み込み時試験のときの初期値
     */
    private Map<String, String> expectedReloadPriceJaCodeListInitValueFactory() {
        Map<String, String> reloadCodeListInitValues = new LinkedHashMap<>();
        reloadCodeListInitValues.put("0", "上限なし DBから取得");
        reloadCodeListInitValues.put("10000", "10,000円以下 DBから取得");
        reloadCodeListInitValues.put("20000", "20,000円以下 DBから取得");
        reloadCodeListInitValues.put("30000", "30,000円以下 DBから取得");
        return reloadCodeListInitValues;
    }

    /**
     * t_priceテーブルのlocaleがjaの場合のコードリストの再読み込み時試験のときの更新後の期待値を作成する。
     * 
     * @return コードリストの再読み込み時試験のときの更新後の期待値
     */
    private Map<String, String> expectedReloadPriceJaCodeListUpdatedValueFactory() {
        Map<String, String> reloadCodeListInitValues = new LinkedHashMap<>();
        reloadCodeListInitValues.put("0", "上限なし DBから取得 更新後");
        reloadCodeListInitValues.put("10000", "10,000円以下 DBから取得 更新後");
        reloadCodeListInitValues.put("20000", "20,000円以下 DBから取得 更新後");
        reloadCodeListInitValues.put("30000", "30,000円以下 DBから取得 更新後");
        return reloadCodeListInitValues;
    }

    /**
     * t_priceテーブルのlocaleがenの場合のコードリストの再読み込み時試験のときの初期値を作成する。
     * 
     * @return コードリストの再読み込み時試験のときの初期値
     */
    private Map<String, String> expectedReloadPriceEnCodeListInitValueFactory() {
        Map<String, String> reloadCodeListInitValues = new LinkedHashMap<>();
        reloadCodeListInitValues.put("0", "unlimited from DB");
        reloadCodeListInitValues.put("10000", "Less than \\10,000 from DB");
        reloadCodeListInitValues.put("20000", "Less than \\20,000 from DB");
        reloadCodeListInitValues.put("30000", "Less than \\30,000 from DB");
        return reloadCodeListInitValues;
    }

    /**
     * t_priceテーブルのlocaleがenの場合のコードリストの再読み込み時試験のときの更新後の期待値を作成する。
     * 
     * @return コードリストの再読み込み時試験のときの更新後の期待値
     */
    private Map<String, String> expectedReloadPriceEnCodeListUpdatedValueFactory() {
        Map<String, String> reloadCodeListInitValues = new LinkedHashMap<>();
        reloadCodeListInitValues.put("0", "unlimited from DB after update");
        reloadCodeListInitValues.put("10000", "Less than \\10,000 from DB after update");
        reloadCodeListInitValues.put("20000", "Less than \\20,000 from DB after update");
        reloadCodeListInitValues.put("30000", "Less than \\30,000 from DB after update");
        return reloadCodeListInitValues;
    }
}
