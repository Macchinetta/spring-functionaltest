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
package jp.co.ntt.fw.spring.functionaltest.selenium.ajax;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class AjaxTest extends FunctionTestSupport {

    /**
     * <ul>
     * <li>SpringMVCのAjax機能を使用した場合、JSON形式のレスポンスを返却することができることを確認する。
     * </ul>
     * @throws InterruptedException
     **/
    @Test
    public void testAJAX0101001() throws IOException, InterruptedException {
        webDriverOperations.click(id("ajax0101001"));

        webDriverOperations.appendText(id("personalComputerName"),
                "Spring Test Server 1");
        webDriverOperations.click(id("searchBtn"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(By.xpath("//td[2]/a"));

        // 戻り値の確認
        assertThat(webDriverOperations.getText(id("returnStatus")), is(String
                .valueOf(HttpStatus.OK.value())));

        MediaType contentType = MediaType.parseMediaType((webDriverOperations
                .getText(id("contentType"))));
        assertThat(contentType.getType(), is("application"));
        assertThat(contentType.getSubtype(), is("json"));

        assertThat(webDriverOperations.getText(By.xpath("//td[2]/a")), is(
                "Spring Test Server 1"));
        assertThat(webDriverOperations.getText(By.xpath("//td[3]")), is(
                "Spring OS 64bit"));
        assertThat(webDriverOperations.getText(By.xpath("//td[4]")), is(
                "Spring Core i9-440"));
        assertThat(webDriverOperations.getText(By.xpath("//td[5]")), is(
                "DDR3-1600 16GB"));
        assertThat(webDriverOperations.getText(By.xpath("//td[6]")), is(
                "Spring Chip"));
        assertThat(webDriverOperations.getText(By.xpath("//td[7]")), is(
                "Spring SSD 500GB"));
        assertThat(webDriverOperations.getText(By.xpath("//td[8]")), is(
                "Spring Power 800W"));
        assertThat(webDriverOperations.getText(By.xpath("//td[9]")), is(
                "100000円"));
    }

    /**
     * <ul>
     * <li>Ajaxを使ってフォームのデータをPOSTした場合、Controllerのフォームで受け取ることができることを確認する。
     * </ul>
     * @throws InterruptedException
     **/
    @Test
    public void testAJAX0102001() throws IOException, InterruptedException {

        webDriverOperations.click(id("ajax0102001"));

        webDriverOperations.overrideText(id("personalComputerName"),
                "Spring Test Server 11");
        webDriverOperations.overrideText(id("os"), "Spring OS 2 64bit");
        webDriverOperations.overrideText(id("cpu"), "Spring Core i9-449");
        webDriverOperations.overrideText(id("ram"), "DDR3-3600 16GB");
        webDriverOperations.overrideText(id("videocard"), "Spring Chip 5");
        webDriverOperations.overrideText(id("hdd"), "Spring SSD 1TB");
        webDriverOperations.overrideText(id("power"), "Spring Power 1200W");
        webDriverOperations.overrideText(id("price"), "200000");

        webDriverOperations.click(id("edit"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(By.xpath(
                "//div[2]/div/div[2]/ul/li"));

        // 戻り値の確認
        assertThat(webDriverOperations.getText(id("returnStatus")), is(String
                .valueOf(HttpStatus.OK.value())));

        MediaType contentType = MediaType.parseMediaType((webDriverOperations
                .getText(id("contentType"))));
        assertThat(contentType.getType(), is("application"));
        assertThat(contentType.getSubtype(), is("json"));

        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div/div[2]/ul/li")), is("登録PCの編集に成功しました。"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("retrunToIndex"));
        webDriverOperations.click(id("ajax0102001"));

        // 変更されていることを確認
        assertThat(webDriverOperations.getInputFieldValue(id(
                "personalComputerName")).trim(), is("Spring Test Server 11"));
        assertThat(webDriverOperations.getInputFieldValue(id("os")).trim(), is(
                "Spring OS 2 64bit"));
        assertThat(webDriverOperations.getInputFieldValue(id("cpu")).trim(), is(
                "Spring Core i9-449"));
        assertThat(webDriverOperations.getInputFieldValue(id("ram")).trim(), is(
                "DDR3-3600 16GB"));
        assertThat(webDriverOperations.getInputFieldValue(id("videocard"))
                .trim(), is("Spring Chip 5"));
        assertThat(webDriverOperations.getInputFieldValue(id("hdd")).trim(), is(
                "Spring SSD 1TB"));
        assertThat(webDriverOperations.getInputFieldValue(id("power")).trim(),
                is("Spring Power 1200W"));
        assertThat(webDriverOperations.getInputFieldValue(id("price")).trim(),
                is("200000"));

        webDriverOperations.saveScreenCapture();

        // データ戻し
        initData();
    }

    /**
     * <ul>
     * <li>AjaxでフォームのデータをJSON形式に変換してからPOSTした場合、Controllerのフォームで受け取ることができることを確認する。
     * </ul>
     * @throws InterruptedException
     **/
    @Test
    public void testAJAX0103001() throws IOException, InterruptedException {

        webDriverOperations.click(id("ajax0103001"));

        webDriverOperations.overrideText(id("personalComputerName"),
                "Spring Test Server 11");
        webDriverOperations.overrideText(id("os"), "Spring OS 2 64bit");
        webDriverOperations.overrideText(id("cpu"), "Spring Core i9-449");
        webDriverOperations.overrideText(id("ram"), "DDR3-3600 16GB");
        webDriverOperations.overrideText(id("videocard"), "Spring Chip 5");
        webDriverOperations.overrideText(id("hdd"), "Spring SSD 1TB");
        webDriverOperations.overrideText(id("power"), "Spring Power 1200W");
        webDriverOperations.overrideText(id("price"), "200000");

        webDriverOperations.click(id("edit"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(By.xpath(
                "//div[2]/div/div[2]/ul/li"));

        // 戻り値の確認
        assertThat(webDriverOperations.getText(id("returnStatus")), is(String
                .valueOf(HttpStatus.OK.value())));

        MediaType contentType = MediaType.parseMediaType((webDriverOperations
                .getText(id("contentType"))));
        assertThat(contentType.getType(), is("application"));
        assertThat(contentType.getSubtype(), is("json"));

        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div/div[2]/ul/li")), is("登録PCの編集に成功しました。"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("retrunToIndex"));
        webDriverOperations.click(id("ajax0103001"));

        // 変更されていることを確認
        assertThat(webDriverOperations.getInputFieldValue(id(
                "personalComputerName")).trim(), is("Spring Test Server 11"));
        assertThat(webDriverOperations.getInputFieldValue(id("os")).trim(), is(
                "Spring OS 2 64bit"));
        assertThat(webDriverOperations.getInputFieldValue(id("cpu")).trim(), is(
                "Spring Core i9-449"));
        assertThat(webDriverOperations.getInputFieldValue(id("ram")).trim(), is(
                "DDR3-3600 16GB"));
        assertThat(webDriverOperations.getInputFieldValue(id("videocard"))
                .trim(), is("Spring Chip 5"));
        assertThat(webDriverOperations.getInputFieldValue(id("hdd")).trim(), is(
                "Spring SSD 1TB"));
        assertThat(webDriverOperations.getInputFieldValue(id("power")).trim(),
                is("Spring Power 1200W"));
        assertThat(webDriverOperations.getInputFieldValue(id("price")).trim(),
                is("200000"));

        webDriverOperations.saveScreenCapture();

        // データ戻し
        initData();

    }

    /**
     * <ul>
     * <li>AjaxでフォームのデータをXML形式に変換してからPOSTした場合、Controllerのフォームで受け取ることができることを確認する。
     * </ul>
     * @throws InterruptedException
     **/
    @Test
    public void testAJAX0104001() throws IOException, InterruptedException {

        File xXETargetFile = new ClassPathResource("testdata/ajax/dummy.txt")
                .getFile();
        String xXETargetString = "<!DOCTYPE comment [<!ELEMENT comment ANY ><!ENTITY xxe SYSTEM \""
                + xXETargetFile.getAbsolutePath()
                + "\" >]><messageBoardForm><comment>&xxe;Test</comment></messageBoardForm>";

        // デフォルトで設定されるHttpMessageConverterを使用した場合の動作検証
        {
            // データ初期化
            clearDate();

            webDriverOperations.click(id("ajax0104001001"));

            webDriverOperations.overrideText(id("comment"), xXETargetString);
            webDriverOperations.click(id("writeBtn"));

            // 要素が見つかるまでアサーションを待つ
            webDriverOperations.waitForDisplayed(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath(
                            "//div[2]/div[1]/div[1]/ul/li")));

            // 戻り値の確認
            assertThat(webDriverOperations.getText(id("returnStatus")), is(
                    String.valueOf(HttpStatus.BAD_REQUEST.value())));

            webDriverOperations.saveScreenCapture();
        }

        // 明示的にspring-oxmから提供されているMarshallingHttpMessageConverter+Jaxb2Marshallerを使用した場合の動作検証
        {
            // データ初期化
            clearDate();

            webDriverOperations.displayPage(getPackageRootUrl());
            webDriverOperations.click(id("ajax0104001"));

            webDriverOperations.overrideText(id("comment"), xXETargetString);
            webDriverOperations.click(id("writeBtn"));

            // 要素が見つかるまでアサーションを待つ
            webDriverOperations.waitForDisplayed(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath(
                            "//div[2]/div[1]/div[1]/ul/li")));

            // 戻り値の確認
            assertThat(webDriverOperations.getText(id("returnStatus")), is(
                    String.valueOf(HttpStatus.BAD_REQUEST.value())));

            webDriverOperations.saveScreenCapture();
        }
    }

    /**
     * <ul>
     * <li>リクエストパラメータとして送信したデータをJavaBeanにバインドする際に、入力値に不正な値が指定された場合、発生する例外をハンドリングできることを確認
     * </ul>
     * @throws InterruptedException
     **/
    @Test
    public void testAJAX0201001() throws IOException, InterruptedException {
        webDriverOperations.click(id("ajax0201001"));

        webDriverOperations.overrideText(id("price"), "abc");
        webDriverOperations.click(id("edit"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(By.xpath(
                "//div[2]/div/div[2]/ul/li"));

        // 戻り値の確認
        assertThat(webDriverOperations.getText(id("returnStatus")), is(String
                .valueOf(HttpStatus.BAD_REQUEST.value())));

        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div/div[2]/ul/li")), is("PRICEは数値で入力してください。"));

    }

    /**
     * <ul>
     * <li>リクエストBodyに格納されているデータをJavaBeanにバインドする際に、入力値に不正な値が指定された場合、発生する例外をハンドリングできることを確認
     * </ul>
     * @throws InterruptedException
     **/
    @Test
    public void testAJAX0201002() throws IOException, InterruptedException {
        webDriverOperations.click(id("ajax0201002"));

        webDriverOperations.clearText(id("videocard"));
        webDriverOperations.click(id("edit"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(By.xpath(
                "//div[2]/div/div[2]/ul/li"));

        // 戻り値の確認
        assertThat(webDriverOperations.getText(id("returnStatus")), is(String
                .valueOf(HttpStatus.BAD_REQUEST.value())));

        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div/div[2]/ul/li")), is(
                        "VIDEOCARDは1以上50以下の長さで入力してください。"));

    }

    /**
     * <ul>
     * <li>リクエストBodyに格納されているデータをJavaBeanにバインドする際に、Bodyに格納されているデータからJavaBeanを生成できなかった場合、発生する例外をハンドリングできることを確認
     * </ul>
     * @throws InterruptedException
     **/
    @Test
    public void testAJAX0201003() throws IOException, InterruptedException {
        webDriverOperations.click(id("ajax0201003"));

        webDriverOperations.overrideText(id("price"), "abc");
        webDriverOperations.click(id("edit"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(By.xpath(
                "//div[2]/div/div[2]/ul/li"));

        // 戻り値の確認
        assertThat(webDriverOperations.getText(id("returnStatus")), is(String
                .valueOf(HttpStatus.BAD_REQUEST.value())));

        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div/div[2]/ul/li")), is("priceに入力された値が不正です。"));

    }

    /**
     * <ul>
     * <li>正常終了時に返却するJavaBeanと入力エラー時に返却するJavaBeanの型が同じ場合、エラー情報を設定するオブジェクトを処理メソッドの引数として受けることでエラーハンドリングできることを確認
     * </ul>
     * @throws InterruptedException
     **/
    @Test
    public void testAJAX0202001() throws IOException, InterruptedException {
        webDriverOperations.click(id("ajax0202001"));

        webDriverOperations.clearText(id("videocard"));
        webDriverOperations.click(id("edit"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(By.xpath(
                "//div[2]/div/div[2]/ul/li"));

        // 戻り値の確認
        assertThat(webDriverOperations.getText(id("returnStatus")), is(String
                .valueOf(HttpStatus.OK.value())));

        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div/div[2]/ul/li")), is(
                        "VIDEOCARDは1以上50以下の長さで入力してください。"));

    }

    /**
     * <ul>
     * <li>エラー情報を設定するオブジェクトを処理メソッドの引数として受け取る場合、HTTPステータスコードを処理結果によって分けることができることを確認
     * </ul>
     * @throws InterruptedException
     **/
    @Test
    public void testAJAX0202002() throws IOException, InterruptedException {
        webDriverOperations.click(id("ajax0202002"));

        webDriverOperations.clearText(id("videocard"));
        webDriverOperations.click(id("edit"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(By.xpath(
                "//div[2]/div/div[2]/ul/li"));

        // 戻り値の確認
        assertThat(webDriverOperations.getText(id("returnStatus")), is(String
                .valueOf(HttpStatus.BAD_REQUEST.value())));

        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div/div[2]/ul/li")), is(
                        "VIDEOCARDは1以上50以下の長さで入力してください。"));

    }

    /**
     * <ul>
     * <li>複数のメソッドに対するリクエストで同じエラー処理を実装する必要がある場合、発生する業務例外を共通的にハンドリングできることを確認
     * </ul>
     * @throws InterruptedException
     **/
    @Test
    public void testAJAX0301001() throws IOException, InterruptedException {
        webDriverOperations.click(id("ajax0301001001"));

        webDriverOperations.overrideText(id("personalComputerName"),
                "Spring Test Server 2");
        webDriverOperations.click(id("edit"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(By.xpath(
                "//div[2]/div/div[2]/ul/li"));

        // 戻り値の確認
        assertThat(webDriverOperations.getText(id("returnStatus")), is(String
                .valueOf(HttpStatus.CONFLICT.value())));

        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div/div[2]/ul/li")), is("同一のPC名がすでに登録されています。"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("retrunToIndex"));
        webDriverOperations.click(id("ajax0301001002"));

        webDriverOperations.overrideText(id("personalComputerName"),
                "Spring Test Server 3");
        webDriverOperations.click(id("edit"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.getWebDriver().findElement(By.xpath(
                "//div[2]/div/div[2]/ul/li"));

        // 戻り値の確認
        assertThat(webDriverOperations.getText(id("returnStatus")), is(String
                .valueOf(HttpStatus.CONFLICT.value())));

        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div/div[2]/ul/li")), is("同一のPC名がすでに登録されています。"));
    }

    /**
     * <ul>
     * <li>コントローラで業務例外が発生する処理をtry-catchする場合、リクエスト毎にエラーハンドリングできることを確認
     * </ul>
     * @throws InterruptedException
     **/
    @Test
    public void testAJAX0302001() throws IOException, InterruptedException {

        webDriverOperations.click(id("ajax0302001"));

        webDriverOperations.overrideText(id("personalComputerName"),
                "Spring Test Server 2");
        webDriverOperations.click(id("edit"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(By.xpath(
                "//div[2]/div/div[2]/ul/li"));

        // 戻り値の確認
        assertThat(webDriverOperations.getText(id("returnStatus")), is(String
                .valueOf(HttpStatus.CONFLICT.value())));

        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div/div[2]/ul/li")), is("同一のPC名がすでに登録されています。"));

    }

    /**
     * AJAX0104001用の関連データをクリアする。
     */
    private void clearDate() {
        restOperations.delete(getPackageRootUrl() + "/testdata/messageBoards");
    }

    private void initData() {
        webDriverOperations.overrideText(id("personalComputerName"),
                "Spring Test Server 1");
        webDriverOperations.overrideText(id("os"), "Spring OS 64bit");
        webDriverOperations.overrideText(id("cpu"), "Spring Core i9-440");
        webDriverOperations.overrideText(id("ram"), "DDR3-1600 16GB");
        webDriverOperations.overrideText(id("videocard"), "Spring Chip");
        webDriverOperations.overrideText(id("hdd"), "Spring SSD 500GB");
        webDriverOperations.overrideText(id("power"), "Spring Power 800W");
        webDriverOperations.overrideText(id("price"), "100000");

        webDriverOperations.click(id("edit"));
    }
}
