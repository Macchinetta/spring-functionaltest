/*
 * Copyright 2014-2018 NTT Corporation.
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
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.pgnt;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class PaginationTest extends FunctionTestSupport {

    /**
     * <ul>
     * <li>リクエストパラメータに検索対象のページネーション構成情報を指定した場合、コントローラの引数に設定されることの確認
     * </ul>
     */
    @Test
    public void testPGNT0101001() throws IOException {

        webDriverOperations.click(id("pgnt0101001"));
        webDriverOperations.select(id("sort"), "No. DESC");
        webDriverOperations.click(id("searchBtn"));

        String searchURL = webDriverOperations.getCurrentUrl()
                + "&page=1&size=5";
        webDriverOperations.getWebDriver().get(searchURL);

        // 取得データの比較
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("21"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "20"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "19"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "18"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "17"));

        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is(
                "Duane"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[2]")), is(
                "Star"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[2]")), is(
                "Penny"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[2]")), is(
                "Dick"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[2]")), is(
                "Angeline"));

        assertThat(webDriverOperations.getText(By.xpath("//td[3]")), is(
                "Greenawa"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[3]")), is(
                "Brawn"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[3]")), is(
                "McCarthy"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[3]")), is(
                "Cook"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[3]")), is(
                "Dodd"));

    }

    /**
     * <ul>
     * <li>リクエストパラメータに検索対象のページネーション構成情報を指定しない場合、コントローラの引数にデフォルト値のページネーション構成情報オブジェクトが設定されることの確認
     * </ul>
     */
    @Test
    public void testPGNT0101002() throws IOException {

        webDriverOperations.click(id("pgnt0101002"));
        webDriverOperations.click(id("searchBtn"));

        // 取得データの比較
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "2"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "3"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "4"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "5"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td")), is(
                "6"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td")), is(
                "7"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td")), is(
                "8"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td")), is(
                "9"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td")), is(
                "10"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[11]/td")), is(
                "11"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[12]/td")), is(
                "12"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[13]/td")), is(
                "13"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[14]/td")), is(
                "14"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[15]/td")), is(
                "15"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[16]/td")), is(
                "16"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[17]/td")), is(
                "17"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[18]/td")), is(
                "18"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[19]/td")), is(
                "19"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[20]/td")), is(
                "20"));

        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[11]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[12]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[13]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[14]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[15]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[16]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[17]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[18]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[19]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[20]/td[2]")), is(
                "internal"));

        assertThat(webDriverOperations.getText(By.xpath("//td[3]")), is(
                "internal title 001"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[3]")), is(
                "internal title 002"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[3]")), is(
                "internal title 003"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[3]")), is(
                "internal title 004"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[3]")), is(
                "internal title 005"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td[3]")), is(
                "internal title 006"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td[3]")), is(
                "internal title 007"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td[3]")), is(
                "internal title 008"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td[3]")), is(
                "internal title 009"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td[3]")), is(
                "internal title 010"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[11]/td[3]")), is(
                "internal title 011"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[12]/td[3]")), is(
                "internal title 012"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[13]/td[3]")), is(
                "internal title 013"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[14]/td[3]")), is(
                "internal title 014"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[15]/td[3]")), is(
                "internal title 015"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[16]/td[3]")), is(
                "internal title 016"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[17]/td[3]")), is(
                "internal title 017"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[18]/td[3]")), is(
                "internal title 018"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[19]/td[3]")), is(
                "internal title 019"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[20]/td[3]")), is(
                "internal title 020"));

        assertThat(webDriverOperations.getText(By.xpath("//td[4]")), is(
                "overview0001"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[4]")), is(
                "overview0002"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[4]")), is(
                "overview0003"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[4]")), is(
                "overview0004"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[4]")), is(
                "overview0005"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td[4]")), is(
                "overview0006"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td[4]")), is(
                "overview0007"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td[4]")), is(
                "overview0008"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td[4]")), is(
                "overview0009"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td[4]")), is(
                "overview0010"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[11]/td[4]")), is(
                "overview0011"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[12]/td[4]")), is(
                "overview0012"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[13]/td[4]")), is(
                "overview0013"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[14]/td[4]")), is(
                "overview0014"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[15]/td[4]")), is(
                "overview0015"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[16]/td[4]")), is(
                "overview0016"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[17]/td[4]")), is(
                "overview0017"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[18]/td[4]")), is(
                "overview0018"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[19]/td[4]")), is(
                "overview0019"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[20]/td[4]")), is(
                "overview0020"));

        assertThat(webDriverOperations.getText(By.xpath("//td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[11]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[12]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[13]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[14]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[15]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[16]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[17]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[18]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[19]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[20]/td[5]")), is(
                "2013/01/01 01:01:01"));
    }

    /**
     * <ul>
     * <li>取得件数として許可する最大値を超過した件数をリクエストパラメータに指定した場合、デフォルト値の取得件数に変更されることの確認
     * </ul>
     */
    @Test
    public void testPGNT0101003() throws IOException {

        webDriverOperations.click(id("pgnt0101003"));
        webDriverOperations.click(id("searchBtn"));

        String searchURL = webDriverOperations.getCurrentUrl() + "&size=2000";
        webDriverOperations.getWebDriver().get(searchURL);

        // 取得データの比較(先頭の要素と最後の要素)
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2000]/td")), is(
                "2000"));

        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2000]/td[2]")),
                is("Entertainment"));

        assertThat(webDriverOperations.getText(By.xpath("//td[3]")), is(
                "internal title 001"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2000]/td[3]")),
                is("Entertainment title 500"));

        assertThat(webDriverOperations.getText(By.xpath("//td[4]")), is(
                "overview0001"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2000]/td[4]")),
                is("overview2000"));

        assertThat(webDriverOperations.getText(By.xpath("//td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2000]/td[5]")),
                is("2013/01/01 01:01:01"));

    }

    /**
     * <ul>
     * <li>取得件数として許可する最大値を変更した場合、指定した最大値に設定されることを確認
     * </ul>
     */
    @Test
    public void testPGNT0102001() throws IOException {

        webDriverOperations.click(id("pgnt0102001"));
        webDriverOperations.click(id("searchBtn"));

        String searchURL = webDriverOperations.getCurrentUrl() + "&size=5";
        webDriverOperations.getWebDriver().get(searchURL);

        // 取得データの比較(先頭の要素と最後の要素)
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "5"));

        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is(
                "Duke"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[2]")), is(
                "Bill"));

        assertThat(webDriverOperations.getText(By.xpath("//td[3]")), is(
                "Savage"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[3]")), is(
                "Brook"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("pgnt0102001"));
        webDriverOperations.click(id("searchBtn"));

        searchURL = webDriverOperations.getCurrentUrl() + "&size=6";
        webDriverOperations.getWebDriver().get(searchURL);

        // 取得データの比較(先頭の要素と最後の要素)
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "5"));

        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is(
                "Duke"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[2]")), is(
                "Bill"));

        assertThat(webDriverOperations.getText(By.xpath("//td[3]")), is(
                "Savage"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[3]")), is(
                "Brook"));
    }

    /**
     * <ul>
     * <li>アプリケーション全体のページ位置、取得件数、ソート条件のデフォルト値を指定した場合、デフォルト値が設定した値になることを確認
     * </ul>
     */
    @Test
    public void testPGNT0102002() throws IOException {
        webDriverOperations.click(id("pgnt0102002"));
        webDriverOperations.click(id("searchBtn"));

        String searchURL = webDriverOperations.getCurrentUrl()
                + "&page=1&size=5&sort=title,ASC";
        webDriverOperations.getWebDriver().get(searchURL);

        // 取得データの比較
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1506"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "1507"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "1508"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "1509"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "1510"));

        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[2]")), is(
                "Entertainment"));

        assertThat(webDriverOperations.getText(By.xpath("//td[3]")), is(
                "Entertainment title 006"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[3]")), is(
                "Entertainment title 007"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[3]")), is(
                "Entertainment title 008"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[3]")), is(
                "Entertainment title 009"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[3]")), is(
                "Entertainment title 010"));

        assertThat(webDriverOperations.getText(By.xpath("//td[4]")), is(
                "overview1506"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[4]")), is(
                "overview1507"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[4]")), is(
                "overview1508"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[4]")), is(
                "overview1509"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[4]")), is(
                "overview1510"));

        assertThat(webDriverOperations.getText(By.xpath("//td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[5]")), is(
                "2013/01/01 01:01:01"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("pgnt0102002"));
        webDriverOperations.click(id("searchBtn"));

        // 取得データの比較
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("2010"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "2009"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "2008"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "2007"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "2006"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td")), is(
                "2005"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td")), is(
                "2004"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td")), is(
                "2003"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td")), is(
                "2002"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td")), is(
                "2001"));

        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td[2]")), is(
                "Entertainment"));

        assertThat(webDriverOperations.getText(By.xpath("//td[3]")), is(
                "Entertainment title 510"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[3]")), is(
                "Entertainment title 509"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[3]")), is(
                "Entertainment title 508"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[3]")), is(
                "Entertainment title 507"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[3]")), is(
                "Entertainment title 506"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td[3]")), is(
                "Entertainment title 505"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td[3]")), is(
                "Entertainment title 504"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td[3]")), is(
                "Entertainment title 503"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td[3]")), is(
                "Entertainment title 502"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td[3]")), is(
                "Entertainment title 501"));

        assertThat(webDriverOperations.getText(By.xpath("//td[4]")), is(
                "overview2010"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[4]")), is(
                "overview2009"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[4]")), is(
                "overview2008"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[4]")), is(
                "overview2007"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[4]")), is(
                "overview2006"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td[4]")), is(
                "overview2005"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td[4]")), is(
                "overview2004"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td[4]")), is(
                "overview2003"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td[4]")), is(
                "overview2002"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td[4]")), is(
                "overview2001"));

        assertThat(webDriverOperations.getText(By.xpath("//td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td[5]")), is(
                "2013/01/01 01:01:01"));
    }

    /**
     * <ul>
     * <li>ページネーション構成情報を設定するアノテーションを利用し、リクエストパラメータにもページネーション情報がない場合、 アノテーションの設定値でページネーション情報が設定されることを確認
     * </ul>
     */
    @Test
    public void testPGNT0202001() throws IOException {

        webDriverOperations.click(id("pgnt0202001"));
        webDriverOperations.click(id("searchBtn"));

        String searchURL = webDriverOperations.getCurrentUrl()
                + "&page=1&size=5&sort=article_id,ASC";
        webDriverOperations.getWebDriver().get(searchURL);

        // 取得データの比較
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("6"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "7"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "8"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "9"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "10"));

        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[2]")), is(
                "internal"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[2]")), is(
                "internal"));

        assertThat(webDriverOperations.getText(By.xpath("//td[3]")), is(
                "internal title 006"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[3]")), is(
                "internal title 007"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[3]")), is(
                "internal title 008"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[3]")), is(
                "internal title 009"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[3]")), is(
                "internal title 010"));

        assertThat(webDriverOperations.getText(By.xpath("//td[4]")), is(
                "overview0006"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[4]")), is(
                "overview0007"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[4]")), is(
                "overview0008"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[4]")), is(
                "overview0009"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[4]")), is(
                "overview0010"));

        assertThat(webDriverOperations.getText(By.xpath("//td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[5]")), is(
                "2013/01/01 01:01:01"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("pgnt0202001"));
        webDriverOperations.click(id("searchBtn"));

        // 取得データの比較
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("2010"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "2009"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "2008"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "2007"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "2006"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td")), is(
                "2005"));

        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td[2]")), is(
                "Entertainment"));

        assertThat(webDriverOperations.getText(By.xpath("//td[3]")), is(
                "Entertainment title 510"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[3]")), is(
                "Entertainment title 509"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[3]")), is(
                "Entertainment title 508"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[3]")), is(
                "Entertainment title 507"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[3]")), is(
                "Entertainment title 506"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td[3]")), is(
                "Entertainment title 505"));

        assertThat(webDriverOperations.getText(By.xpath("//td[4]")), is(
                "overview2010"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[4]")), is(
                "overview2009"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[4]")), is(
                "overview2008"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[4]")), is(
                "overview2007"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[4]")), is(
                "overview2006"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td[4]")), is(
                "overview2005"));

        assertThat(webDriverOperations.getText(By.xpath("//td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td[5]")), is(
                "2013/01/01 01:01:01"));
    }

    /**
     * <ul>
     * <li>ページネーション構成情報を設定するアノテーションを利用し、リクエストパラメータにもページネーション情報がない場合、アノテーションのデフォルト値でページネーション情報が設定されることを確認
     * </ul>
     */
    @Test
    public void testPGNT0202002() throws IOException {
        webDriverOperations.click(id("pgnt0202002"));
        webDriverOperations.click(id("searchBtn"));

        String searchURL = webDriverOperations.getCurrentUrl()
                + "&page=1&size=5&sort=title,ASC";
        webDriverOperations.getWebDriver().get(searchURL);

        // 取得データの比較
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1506"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "1507"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "1508"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "1509"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "1510"));

        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[2]")), is(
                "Entertainment"));

        assertThat(webDriverOperations.getText(By.xpath("//td[3]")), is(
                "Entertainment title 006"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[3]")), is(
                "Entertainment title 007"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[3]")), is(
                "Entertainment title 008"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[3]")), is(
                "Entertainment title 009"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[3]")), is(
                "Entertainment title 010"));

        assertThat(webDriverOperations.getText(By.xpath("//td[4]")), is(
                "overview1506"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[4]")), is(
                "overview1507"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[4]")), is(
                "overview1508"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[4]")), is(
                "overview1509"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[4]")), is(
                "overview1510"));

        assertThat(webDriverOperations.getText(By.xpath("//td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[5]")), is(
                "2013/01/01 01:01:01"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("pgnt0202002"));
        webDriverOperations.click(id("searchBtn"));

        // 取得データの比較
        // 10件一覧表示しているかの確認
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "2"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "3"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "4"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "5"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td")), is(
                "6"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td")), is(
                "7"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td")), is(
                "8"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td")), is(
                "9"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td")), is(
                "10"));

    }

    /**
     * <ul>
     * <li>ページネーション構成情報を設定するアノテーションに取得件数のみを設定した場合、アノテーションの設定値でページネーション情報が設定されることを確認
     * </ul>
     */
    @Test
    public void testPGNT0202003() throws IOException {
        webDriverOperations.click(id("pgnt0202003"));
        webDriverOperations.click(id("searchBtn"));

        String searchURL = webDriverOperations.getCurrentUrl()
                + "&page=1&size=5&sort=title,ASC";
        webDriverOperations.getWebDriver().get(searchURL);

        // 取得データの比較
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1506"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "1507"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "1508"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "1509"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "1510"));

        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[2]")), is(
                "Entertainment"));

        assertThat(webDriverOperations.getText(By.xpath("//td[3]")), is(
                "Entertainment title 006"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[3]")), is(
                "Entertainment title 007"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[3]")), is(
                "Entertainment title 008"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[3]")), is(
                "Entertainment title 009"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[3]")), is(
                "Entertainment title 010"));

        assertThat(webDriverOperations.getText(By.xpath("//td[4]")), is(
                "overview1506"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[4]")), is(
                "overview1507"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[4]")), is(
                "overview1508"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[4]")), is(
                "overview1509"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[4]")), is(
                "overview1510"));

        assertThat(webDriverOperations.getText(By.xpath("//td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[5]")), is(
                "2013/01/01 01:01:01"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("pgnt0202003"));
        webDriverOperations.click(id("searchBtn"));

        // 取得データの比較
        // 11件一覧表示しているかの確認
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "2"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "3"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "4"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "5"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td")), is(
                "6"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td")), is(
                "7"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td")), is(
                "8"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td")), is(
                "9"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td")), is(
                "10"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[11]/td")), is(
                "11"));
    }

    /**
     * <ul>
     * <li>ソート情報を設定するアノテーションを利用する場合、ページネーション情報を構成するオブジェクトにソート値が設定されることを確認
     * </ul>
     */
    @Test
    public void testPGNT0202004() throws IOException {
        webDriverOperations.click(id("pgnt0202004"));
        webDriverOperations.click(id("searchBtn"));

        String searchURL = webDriverOperations.getCurrentUrl()
                + "&page=1&size=5&sort=title,ASC";
        webDriverOperations.getWebDriver().get(searchURL);

        // 取得データの比較
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1506"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "1507"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "1508"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "1509"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "1510"));

        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[2]")), is(
                "Entertainment"));

        assertThat(webDriverOperations.getText(By.xpath("//td[3]")), is(
                "Entertainment title 006"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[3]")), is(
                "Entertainment title 007"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[3]")), is(
                "Entertainment title 008"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[3]")), is(
                "Entertainment title 009"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[3]")), is(
                "Entertainment title 010"));

        assertThat(webDriverOperations.getText(By.xpath("//td[4]")), is(
                "overview1506"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[4]")), is(
                "overview1507"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[4]")), is(
                "overview1508"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[4]")), is(
                "overview1509"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[4]")), is(
                "overview1510"));

        assertThat(webDriverOperations.getText(By.xpath("//td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[5]")), is(
                "2013/01/01 01:01:01"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("pgnt0202004"));
        webDriverOperations.click(id("searchBtn"));

        // 取得データの比較
        // 12件一覧表示しているかの確認
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("2010"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "2009"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "2008"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "2007"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "2006"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td")), is(
                "2005"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td")), is(
                "2004"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td")), is(
                "2003"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td")), is(
                "2002"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td")), is(
                "2001"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[11]/td")), is(
                "2000"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[12]/td")), is(
                "1999"));
    }

    /**
     * <ul>
     * <li>ソート項目のデフォルト値のみ指定する場合、ページネーション情報を構成するオブジェクトにソート値が設定されることを確認
     * </ul>
     */
    @Test
    public void testPGNT0202005() throws IOException {
        webDriverOperations.click(id("pgnt0202005"));
        webDriverOperations.click(id("searchBtn"));

        String searchURL = webDriverOperations.getCurrentUrl()
                + "&page=1&size=5&sort=title,ASC";
        webDriverOperations.getWebDriver().get(searchURL);

        // 取得データの比較
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1506"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "1507"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "1508"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "1509"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "1510"));

        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[2]")), is(
                "Entertainment"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[2]")), is(
                "Entertainment"));

        assertThat(webDriverOperations.getText(By.xpath("//td[3]")), is(
                "Entertainment title 006"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[3]")), is(
                "Entertainment title 007"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[3]")), is(
                "Entertainment title 008"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[3]")), is(
                "Entertainment title 009"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[3]")), is(
                "Entertainment title 010"));

        assertThat(webDriverOperations.getText(By.xpath("//td[4]")), is(
                "overview1506"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[4]")), is(
                "overview1507"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[4]")), is(
                "overview1508"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[4]")), is(
                "overview1509"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[4]")), is(
                "overview1510"));

        assertThat(webDriverOperations.getText(By.xpath("//td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td[5]")), is(
                "2013/01/01 01:01:01"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td[5]")), is(
                "2013/01/01 01:01:01"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("pgnt0202005"));
        webDriverOperations.click(id("searchBtn"));

        // 取得データの比較
        // 10件一覧表示しているかの確認
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "2"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "3"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "4"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "5"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td")), is(
                "6"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td")), is(
                "7"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td")), is(
                "8"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td")), is(
                "9"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td")), is(
                "10"));

    }

    /**
     * <ul>
     * <li>Mybatis3のデータアクセス機能を利用した場合、JSTLのタグを使用して一覧に複数件表示でき、 ページネーションが複数ページ分表示されることを確認する。
     * </ul>
     */
    @Test
    public void testPGNT0502001() throws IOException {
        webDriverOperations.click(id("pgnt0502001"));
        webDriverOperations.click(id("searchBtn"));

        // 取得データの比較
        // 10件一覧表示しているかの確認
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "2"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "3"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "4"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "5"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td")), is(
                "6"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td")), is(
                "7"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td")), is(
                "8"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td")), is(
                "9"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td")), is(
                "10"));

        // ページネーションリンクが3ページ分表示されていることの確認
        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div[2]/ul/li/a")), is("<<"));
        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div[2]/ul/li[2]/a")), is("<"));
        assertThat(webDriverOperations.getText(By.xpath("//li[3]/a")), is("1"));
        assertThat(webDriverOperations.getText(By.xpath("//li[4]/a")), is("2"));
        assertThat(webDriverOperations.getText(By.xpath("//li[5]/a")), is("3"));
        assertThat(webDriverOperations.getText(By.xpath("//li[6]/a")), is(">"));
        assertThat(webDriverOperations.getText(By.xpath("//li[7]/a")), is(
                ">>"));
    }

    /**
     * <ul>
     * <li>Mybatis3のデータアクセス機能を利用した場合、JSTLのタグを使用して一覧に複数件表示でき、ページネーションが単数ページ表分示されることを確認する。
     * </ul>
     */
    @Test
    public void testPGNT0502002() throws IOException {
        webDriverOperations.click(id("pgnt0502002"));
        webDriverOperations.click(id("searchBtn"));

        String searchURL = webDriverOperations.getCurrentUrl() + "&size=26";
        webDriverOperations.getWebDriver().get(searchURL);

        // 取得データの比較
        // 26件一覧表示しているかの確認
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is(
                "2"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[3]/td")), is(
                "3"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[4]/td")), is(
                "4"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[5]/td")), is(
                "5"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[6]/td")), is(
                "6"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[7]/td")), is(
                "7"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[8]/td")), is(
                "8"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[9]/td")), is(
                "9"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[10]/td")), is(
                "10"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[11]/td")), is(
                "11"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[12]/td")), is(
                "12"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[13]/td")), is(
                "13"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[14]/td")), is(
                "14"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[15]/td")), is(
                "15"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[16]/td")), is(
                "16"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[17]/td")), is(
                "17"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[18]/td")), is(
                "18"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[19]/td")), is(
                "19"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[20]/td")), is(
                "20"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[21]/td")), is(
                "21"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[22]/td")), is(
                "22"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[23]/td")), is(
                "23"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[24]/td")), is(
                "24"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[25]/td")), is(
                "25"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[26]/td")), is(
                "26"));

        // ページネーションリンクが1ページ分表示されていることの確認
        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div[2]/ul/li/a")), is("<<"));
        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div[2]/ul/li[2]/a")), is("<"));
        assertThat(webDriverOperations.getText(By.xpath("//li[3]/a")), is("1"));
        assertThat(webDriverOperations.getText(By.xpath("//li[4]/a")), is(">"));
        assertThat(webDriverOperations.getText(By.xpath("//li[5]/a")), is(
                ">>"));

        // 取得結果の確認
        assertThat(webDriverOperations.getText(By.xpath("//div[4]")), is(
                "26 results"));
        assertThat(webDriverOperations.getText(By.xpath("//div[5]")), is(
                "1 / 1 Pages"));
    }

    /**
     * <ul>
     * <li>Mybatis3のデータアクセス機能を利用し、検索結果が0件となる場合、JSTLのタグを使用し、一覧もページネーションも表示されないことを確認する。
     * </ul>
     */
    @Test
    public void testPGNT0502003() throws IOException {
        webDriverOperations.click(id("pgnt0502003"));

        webDriverOperations.appendText(id("firstName"), "spring");
        webDriverOperations.click(id("searchBtn"));

        // 一覧のヘッダ部が出力されていることをチェック
        assertThat(webDriverOperations.exists(By.xpath("//th")), is(true));
        // 一覧のデータ部が出力されていないことのチェック
        assertThat(webDriverOperations.exists(By.xpath("//td")), is(false));
        // ページネーションリンクが表示されていないことのチェック
        assertThat(webDriverOperations.exists(By.xpath("//li[3]/a")), is(
                false));

    }

    /**
     * <ul>
     * <li>結果情報を用いてページネーションに関連する情報出力する場合、 合計ページ数、ページ数、合計件数を出力することが可能なことを確認する
     * </ul>
     */
    @Test
    public void testPGNT0603001() throws IOException {
        webDriverOperations.click(id("pgnt0603001"));
        webDriverOperations.click(id("searchBtn"));

        // 取得結果の確認
        assertThat(webDriverOperations.getText(By.xpath("//div[4]")), is(
                "26 results"));
        assertThat(webDriverOperations.getText(By.xpath("//div[5]")), is(
                "1 / 3 Pages"));

        webDriverOperations.saveScreenCapture();

        // 2ページ目に遷移する
        webDriverOperations.click(By.xpath("//li[4]/a"));

        // 取得結果の確認
        assertThat(webDriverOperations.getText(By.xpath("//div[4]")), is(
                "26 results"));
        assertThat(webDriverOperations.getText(By.xpath("//div[5]")), is(
                "2 / 3 Pages"));

    }

    /**
     * <ul>
     * <li>結果情報が0件の場合、合計ページ、表示ページ、合計件数を出力することが可能なことを確認する
     * </ul>
     */
    @Test
    public void testPGNT0603002() throws IOException {
        webDriverOperations.click(id("pgnt0603002"));

        webDriverOperations.appendText(id("firstName"), "spring");
        webDriverOperations.click(id("searchBtn"));

        // 取得結果の確認
        assertThat(webDriverOperations.getText(By.xpath("//div[4]")), is(
                "0 results"));

    }
}
