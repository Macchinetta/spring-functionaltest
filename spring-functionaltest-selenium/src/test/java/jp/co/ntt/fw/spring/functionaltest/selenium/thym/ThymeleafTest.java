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
package jp.co.ntt.fw.spring.functionaltest.selenium.thym;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.web.client.RestTemplate;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

@IfProfileValue(name = "test.environment.view", values = { "thymeleaf" })
public class ThymeleafTest extends FunctionTestSupport {

    @Inject
    private RestTemplate restTemplate;

    @Value("${selenium.applicationContextUrl}")
    private String applicationContextUrl;

    /**
     * <ul>
     * <li>カスタムデータ属性を用いて属性プロセッサが記述できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHYM0103001() throws IOException {

        // 検索画面に遷移
        webDriverOperations.click(id("thym0103001"));

        // 検証
        assertThat(webDriverOperations.getTitle(), is("Search Screen"));
        assertThat(webDriverOperations.getText(By.tagName("h1")), is("Search"));
        assertThat(webDriverOperations.getWebDriver().findElement(By.name(
                "searchForm")).getAttribute("action"), is(applicationContextUrl
                        + "/thym"));
        assertThat(webDriverOperations.getInputFieldValue(By.name(
                "fruitsPrice")), is("85"));
        webDriverOperations.saveScreenCapture();

    }

    /**
     * <ul>
     * <li>XHTMLの構文バリデーションが実施されないことを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHYM0104001() throws IOException {

        // 検索画面に遷移
        webDriverOperations.click(id("thym0104001"));

        // 検証
        assertThat(webDriverOperations.getTitle(), is(
                "Search No NameSpace Screen"));
        assertThat(webDriverOperations.getText(By.tagName("h1")), is(
                "Search No NameSpace"));
        assertThat(webDriverOperations.getWebDriver().findElement(By.name(
                "searchForm")).getAttribute("action"), is(applicationContextUrl
                        + "/thym"));
        // DIコンテナ生成時にWARNログが出力されていること。
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessageAndLevelsAndLogger(
                "[THYMELEAF].* Template Mode 'XHTML' is deprecated. Using Template Mode 'HTML' instead.",
                "WARN", "org.thymeleaf.templatemode.TemplateMode");
        webDriverOperations.saveScreenCapture();

    }

    /**
     * <ul>
     * <li>検索画面が表示できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHYM0201001() throws IOException {

        // 検索画面に遷移
        webDriverOperations.click(id("thym0201001"));

        // 検証
        assertThat(webDriverOperations.getTitle(), is("Search Screen"));
        assertThat(webDriverOperations.getText(By.tagName("h1")), is("Search"));
        assertThat(webDriverOperations.getWebDriver().findElement(By.name(
                "searchForm")).getAttribute("action"), is(applicationContextUrl
                        + "/thym/0201/002"));
        assertThat(webDriverOperations.getInputFieldValue(By.name(
                "fruitsName")), is(""));
        webDriverOperations.saveScreenCapture();

    }

    /**
     * <ul>
     * <li>検索結果画面が表示できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHYM0201002() throws IOException {

        // 検索結果画面に遷移
        webDriverOperations.click(id("thym0201002"));

        // 検証
        assertThat(webDriverOperations.getTitle(), is("Search Result Screen"));
        assertThat(webDriverOperations.getText(By.tagName("h1")), is(
                "Search Result"));
        // xpathでtdタグの一覧が取得できなかった為、2回に分けて取得している。
        List<WebElement> elementsName = webDriverOperations.getWebDriver()
                .findElements(By.xpath("//table/tbody/tr[*]/td[1]"));
        List<WebElement> elementsPrice = webDriverOperations.getWebDriver()
                .findElements(By.xpath("//table/tbody/tr[*]/td[2]"));

        // Controllerで画面表示用に設定する値
        String[] name = { "Peach", "Grape", "Melon", "PineApple", "Orange" };
        String[] price = { "1000", "2000", "3000", "4000", "5000" };

        for (int i = 0; i < elementsName.size(); i++) {
            assertThat(elementsName.get(i).getText(), is(name[i]));
            assertThat(elementsPrice.get(i).getText(), is(price[i]));
        }

        webDriverOperations.saveScreenCapture();

    }

    /**
     * <ul>
     * <li>検索画面と検索結果画面間の遷移が可能なことを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHYM0201003() throws IOException {

        // 検索画面に遷移
        webDriverOperations.click(id("thym0201001"));

        // 検証
        assertThat(webDriverOperations.getTitle(), is("Search Screen"));
        webDriverOperations.saveScreenCapture();

        // 検索結果画面に遷移
        webDriverOperations.click(By.tagName("button"));

        // 検証
        assertThat(webDriverOperations.getTitle(), is("Search Result Screen"));
        webDriverOperations.saveScreenCapture();

        // 検索画面に遷移
        webDriverOperations.click(id("back"));

        // 検証
        assertThat(webDriverOperations.getTitle(), is("Search Screen"));
        assertThat(webDriverOperations.getInputFieldValue(By.name(
                "fruitsName")), is("apple"));
        webDriverOperations.saveScreenCapture();

    }

    /**
     * <ul>
     * <li>HTMLコメントが出力されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHYM0301001() throws IOException {

        // 検索画面に遷移
        webDriverOperations.click(id("thym0301001"));

        // 検証
        assertThat(webDriverOperations.getWebDriver().getPageSource(), is(
                containsString("<!-- This is Search Form -->")));
        webDriverOperations.saveScreenCapture();

    }

    /**
     * <ul>
     * <li>パーサーレベルコメントで囲った要素が削除されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHYM0301002() throws IOException {

        // パーサーレベルコメント画面に遷移
        webDriverOperations.click(id("thym0301002"));

        // 検証
        assertThat(webDriverOperations.getTitle(), is(
                "Parser Level Comment Screen"));
        assertThat(webDriverOperations.getText(By.tagName("h1")), is(
                "Parser Level Comment"));
        List<WebElement> elements = webDriverOperations.getWebDriver()
                .findElements(By.xpath("//table/tbody/tr[*]"));
        assertThat(elements.size(), is(5));

        // パーサーレベルコメントにより削除するtable内のコンテンツが存在しないこと
        for (WebElement elm : elements) {
            assertThat(elm.findElement(By.xpath("./td[1]")).getText(), anyOf(
                    not("Banana"), not("Strawberry")));
        }

        webDriverOperations.saveScreenCapture();

    }

    /**
     * <ul>
     * <li>プロトタイプのみコメントで囲ったプロセッサが実行されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHYM0301003() throws IOException {

        // プロトタイプのみコメント画面に遷移
        webDriverOperations.click(id("thym0301003"));

        // 検証
        assertThat(webDriverOperations.getTitle(), is(
                "Prototype Only Comment Screen"));
        assertThat(webDriverOperations.getText(By.tagName("h1")), is(
                "Prototype Only Comment"));
        List<WebElement> elements = webDriverOperations.getWebDriver()
                .findElements(By.xpath("//table/tbody/tr[*]"));
        assertThat(elements.size(), is(5));
        webDriverOperations.saveScreenCapture();

    }

    /**
     * <ul>
     * <li>Content-Typeヘッダがリクエストを元に設定されること確認する。</li>
     * </ul>
     */
    @Test
    public void testTHYM0401001() throws IOException {

        // .jsonの拡張子付きURLでリクエスト
        ResponseEntity<byte[]> entity = restTemplate.getForEntity(
                applicationContextUrl + "/thym/0401/001.json", byte[].class);

        HttpHeaders headers = entity.getHeaders();
        assertThat(headers.getContentType().toString(), is(
                "application/json;charset=UTF-8"));

    }

    /**
     * <ul>
     * <li>Content-Typeヘッダがリクエストに依らず、固定で設定されること確認する。</li>
     * </ul>
     */
    @Test
    public void testTHYM0402001() throws IOException {

        // .jsonの拡張子付きURLでリクエスト
        ResponseEntity<byte[]> entity = restTemplate.getForEntity(
                applicationContextUrl + "/thym/0402/001.json", byte[].class);

        HttpHeaders headers = entity.getHeaders();
        assertThat(headers.getContentType().toString(), is(
                "text/html;charset=UTF-8"));

    }

    /**
     * <ul>
     * <li>作成したProcessorが適用されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHYM0501001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("thym0501001"));

        // Processorに定義した内容が適用されているかをチェック
        // 適用されていない場合は id="userNameLabel" を持つ要素と id="userName" を持つ要素がない
        assertThat(webDriverOperations.exists(id("userNameLabel")), is(true));
        assertThat(webDriverOperations.exists(id("userName")), is(true));

    }

    /**
     * <ul>
     * <li>Dialectの優先順位、Processorの優先順位の順番で比較し、値が低い属性が先に適用されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHYM0501002() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("thym0501002"));

        // Dialectの優先度(d) -> processorの優先度(p) の順番で値を比較し、小さいほうが適用されているかをチェック
        // d2000:p2000 と d2000:p3000 の組み合わせ d2000:p2000 が適用されているかをチェック
        assertThat(webDriverOperations.getText(id("001")), is("d2000:p2000"));

        // d2000:p2000 と d3000:p2000 の組み合わせ d2000:p2000 が適用されているかをチェック
        assertThat(webDriverOperations.getText(id("002")), is("d2000:p2000"));

        // d2000:p2000 と d3000:p3000 の組み合わせ d2000:p2000 が適用されているかをチェック
        assertThat(webDriverOperations.getText(id("003")), is("d2000:p2000"));

        // d2000:p3000 と d3000:p2000 の組み合わせ d2000:p3000 が適用されているかをチェック
        assertThat(webDriverOperations.getText(id("004")), is("d2000:p3000"));

        // d2000:p3000 と d3000:p3000 の組み合わせ d2000:p3000 が適用されているかをチェック
        assertThat(webDriverOperations.getText(id("005")), is("d2000:p3000"));

        // d3000:p2000 と d3000:p3000 の組み合わせ d3000:p2000 が適用されているかをチェック
        assertThat(webDriverOperations.getText(id("006")), is("d3000:p2000"));

    }

    /**
     * <ul>
     * <li>作成したExpressionObjectが適用されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHYM0502001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("thym0502001"));

        // ExpressionObjectに定義した内容が適用されているかをチェック
        // 適用されていない場合は "yyyy/MM/dd" が取得される
        assertThat(webDriverOperations.getText(id("creationDate")), is(
                "2017/11/15"));

    }

    /**
     * <ul>
     * <li>ExpressionObjectのキャッシュの設定が適用されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHYM0502002() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("thym0502002"));

        // ExpressionObjectのインスタンスを生成したことを表すログを取得する
        dbLogAssertOperations.waitForAssertion();
        List<String> logs = dbLogAssertOperations.getLogByRegexMessage(
                webDriverOperations.getXTrack(),
                "jp.co.ntt.fw.spring.functionaltest.dialects.thym.DateFormatSlashDialect",
                "Create '.*' Object");

        // 取得したログのサイズが3であるかチェックする
        assertThat(logs.size(), is(3));

        // キャッシュする設定にした "dateformatslash" は1回のみインスタンスが生成される
        assertThat(logs.get(0), is("Create 'dateformatslash' Object"));

        // キャッシュしない設定にした "nocachedateformat" は2回（呼び出された回数分）インスタンスが生成される
        assertThat(logs.get(1), is("Create 'nocachedateformat' Object"));
        assertThat(logs.get(2), is("Create 'nocachedateformat' Object"));

    }

    /**
     * <ul>
     * <li>全テンプレートに対するキャッシュが有効化されていることの確認</li>
     * </ul>
     */
    @Ignore("testTHYM0602001で実施")
    public void testTHYM0601001() {
    }

    /**
     * <ul>
     * <li>全テンプレートに対するキャッシュが無効化されていることの確認</li>
     * </ul>
     */
    @Test
    public void testTHYM0601002() {
        // メニュー画面の操作
        webDriverOperations.click(id("thym0601002"));

        // ログがDBに格納されるまで待機
        dbLogAssertOperations.waitForAssertion();

        // テンプレートキャッシュにテンプレートを追加するログが出力されていないことの確認
        dbLogAssertOperations.assertNotContainsByRegexMessage(null, null,
                ".*Adding cache entry in cache \"TEMPLATE_CACHE\".*");
    }

    /**
     * <ul>
     * <li>キャッシュの生存時間内にページ更新をするとキャッシュにヒットし、生存時間を過ぎた後にページ更新をするとテンプレートがキャッシュから削除されていることの確認</li>
     * </ul>
     */
    @Test
    public void testTHYM0602001() {
        // メニュー画面の操作
        webDriverOperations.click(id("thym0602001"));

        // 2秒間待機
        dbLogAssertOperations.waitForAssertion(2000);

        // ページの更新
        webDriverOperations.refresh();

        // ログがDBに格納されるまで待機
        dbLogAssertOperations.waitForAssertion();

        // キャッシュにヒットすることの確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(),
                "org.thymeleaf.TemplateEngine.cache.TEMPLATE_CACHE",
                ".*Cache hit in cache \"TEMPLATE_CACHE\" for key \"thym/cacheTTLMs\".*");

        // 3秒間待機
        dbLogAssertOperations.waitForAssertion(3000);

        // ページの更新
        webDriverOperations.refresh();

        // ログがDBに格納されるまで待機
        dbLogAssertOperations.waitForAssertion();

        // キャッシュされていたテンプレートが生存時間を過ぎたことで除外されたことの確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), ".*TEMPLATE_CACHE.*",
                ".*Entry \"thym/cacheTTLMs\" is not valid anymore.*");
    }

    /**
     * <ul>
     * <li>キャッシュの生存時間を指定しない場合、デフォルト値のnullが反映され、時間経過によるキャッシュ削除が行われないことの確認</li>
     * </ul>
     */
    @Ignore("有限時間内に確認不可能なためスキップ")
    public void testTHYM0602002() {
    }

    /**
     * <ul>
     * <li>キャッシュ対象に設定されたテンプレートのみがキャッシュに追加されることの確認</li>
     * </ul>
     */
    @Test
    public void testTHYM0603001() {
        // メニュー画面の操作
        webDriverOperations.click(id("thym060300101"));

        // ログがDBに格納されるまで待機
        dbLogAssertOperations.waitForAssertion();

        // ページの更新
        webDriverOperations.refresh();

        // メニュー画面の操作
        webDriverOperations.click(id("thym060300102"));

        // ログがDBに格納されるまで待機
        dbLogAssertOperations.waitForAssertion();

        // ページの更新
        webDriverOperations.refresh();

        // ログがDBに格納されるまで待機
        dbLogAssertOperations.waitForAssertion();

        // キャッシュにヒットすることの確認
        dbLogAssertOperations.assertContainsByRegexMessage(null,
                ".*TEMPLATE_CACHE.*",
                ".*Cache hit in cache \"TEMPLATE_CACHE\" for key \"thym/cacheablePatterns01\".*");

        // キャッシュにヒットしないことの確認
        dbLogAssertOperations.assertNotContainsByRegexMessage(null,
                ".*TEMPLATE_CACHE.*",
                ".*Cache hit in cache \"TEMPLATE_CACHE\" for key \"thym/cacheablePatterns02\".*");
    }

    /**
     * <ul>
     * <li>キャッシュ対象から除外する設定をされたテンプレートがキャッシュに追加されないことの確認</li>
     * </ul>
     */
    @Test
    public void testTHYM0603002() {
        // メニュー画面の操作
        webDriverOperations.click(id("thym060300201"));

        // ログがDBに格納されるまで待機
        dbLogAssertOperations.waitForAssertion();

        // ページの更新
        webDriverOperations.refresh();

        // メニュー画面の操作
        webDriverOperations.click(id("thym060300202"));

        // ログがDBに格納されるまで待機
        dbLogAssertOperations.waitForAssertion();

        // ページの更新
        webDriverOperations.refresh();

        // ログがDBに格納されるまで待機
        dbLogAssertOperations.waitForAssertion();

        // キャッシュにヒットすることの確認
        dbLogAssertOperations.assertContainsByRegexMessage(null,
                ".*TEMPLATE_CACHE.*",
                ".*Cache hit in cache \"TEMPLATE_CACHE\" for key \"thym/nonCacheablePatterns01\".*");

        // キャッシュにヒットしないことの確認
        dbLogAssertOperations.assertNotContainsByRegexMessage(null,
                ".*TEMPLATE_CACHE.*",
                ".*Cache hit in cache \"TEMPLATE_CACHE\" for key \"thym/nonCacheablePatterns02\".*");
    }

    /**
     * <ul>
     * <li>キャッシュの初期サイズの設定が反映されていることの確認</li>
     * </ul>
     */
    @Ignore
    public void testTHYM0604001() {
    }

    /**
     * <ul>
     * <li>キャッシュの初期サイズを指定しない場合、デフォルト値の20が反映されていることの確認</li>
     * </ul>
     */
    @Ignore
    public void testTHYM0604002() {
    }

    /**
     * <ul>
     * <li>キャッシュの最大サイズを設定し、その数を超える数のテンプレートを追加すると、古いテンプレートがキャッシュから削除されることの確認</li>
     * </ul>
     */
    @Test
    public void testTHYM0605001() {
        // メニュー画面の操作
        webDriverOperations.click(id("thym060500101"));
        webDriverOperations.click(id("thym060500102"));
        webDriverOperations.click(id("thym060500103"));

        // ログがDBに格納されるまで待機
        dbLogAssertOperations.waitForAssertion();

        // 最大サイズを超えたことでテンプレートがキャッシュから除外されたログのリストを取得
        List<String> logs = dbLogAssertOperations.getLogByRegexMessage(null,
                ".*TEMPLATE_CACHE.*",
                ".*\\[CACHE_REMOVE\\]\\[2\\] Max size exceeded for cache \"TEMPLATE_CACHE\".*");

        assertThat(logs.size() > 0, is(true));
    }

    /**
     * <ul>
     * <li>キャッシュの最大サイズを指定しない場合、デフォルト値の200が反映され、200を超えるテンプレートを追加すると、古いテンプレートが削除されることの確認</li>
     * </ul>
     */
    @Ignore
    public void testTHYM0605002() {
    }

    /**
     * <ul>
     * <li>キャッシュの最大サイズに-1を指定した場合、最大サイズの制限が無くなることの確認</li>
     * </ul>
     */
    @Ignore
    public void testTHYM0605003() {
    }

    /**
     * <ul>
     * <li>キャッシュの最大サイズに0を指定した場合、キャッシュが無効になることの確認</li>
     * </ul>
     */
    @Test
    public void testTHYM0605004() {
        // メニュー画面の操作
        webDriverOperations.click(id("thym0605004"));

        // ログがDBに格納されるまで待機
        dbLogAssertOperations.waitForAssertion();

        // テンプレートキャッシュにテンプレートを追加するログが出力されていないことの確認
        dbLogAssertOperations.assertNotContainsByRegexMessage(null, null,
                ".*Adding cache entry in cache \"TEMPLATE_CACHE\".*");
    }
}
