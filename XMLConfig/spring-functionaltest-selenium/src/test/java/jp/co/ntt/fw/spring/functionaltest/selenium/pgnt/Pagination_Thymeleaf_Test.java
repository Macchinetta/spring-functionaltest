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
package jp.co.ntt.fw.spring.functionaltest.selenium.pgnt;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

import java.io.IOException;
import java.time.Duration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class Pagination_Thymeleaf_Test extends FunctionTestSupport {

    private static String VIEW_TYPE = "thymeleaf";

    /**
     * <ul>
     * <li>リクエストパラメータに検索対象のページネーション構成情報を指定した場合、コントローラの引数に設定されることの確認</li>
     * </ul>
     */
    @Test
    public void testPGNT0101001() throws IOException {

        webDriverOperations.click(id("pgnt0101001_" + VIEW_TYPE));
        webDriverOperations.select(id("sort"), "No. DESC");
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
     * <li>リクエストパラメータに検索対象のページネーション構成情報を指定しない場合、コントローラの引数にデフォルト値のページネーション構成情報オブジェクトが設定されることの確認</li>
     * </ul>
     */
    @Test
    public void testPGNT0101002() throws IOException {

        webDriverOperations.click(id("pgnt0101002_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
     * <li>取得件数として許可する最大値を超過した件数をリクエストパラメータに指定した場合、デフォルト値の取得件数に変更されることの確認</li>
     * </ul>
     */
    @Test
    public void testPGNT0101003() throws IOException {

        webDriverOperations.click(id("pgnt0101003_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
     * <li>取得件数として許可する最大値を変更した場合、指定した最大値に設定されることを確認</li>
     * </ul>
     */
    @Test
    public void testPGNT0102001() throws IOException {

        webDriverOperations.click(id("pgnt0102001_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
        webDriverOperations.click(id("pgnt0102001_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
     * <li>アプリケーション全体のページ位置、取得件数、ソート条件のデフォルト値を指定した場合、デフォルト値が設定した値になることを確認</li>
     * </ul>
     */
    @Test
    public void testPGNT0102002() throws IOException {
        webDriverOperations.click(id("pgnt0102002_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
        webDriverOperations.click(id("pgnt0102002_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
     * <li>ページネーション構成情報を設定するアノテーションを利用し、リクエストパラメータにもページネーション情報がない場合、 アノテーションの設定値でページネーション情報が設定されることを確認</li>
     * </ul>
     */
    @Test
    public void testPGNT0202001() throws IOException {

        webDriverOperations.click(id("pgnt0202001_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
        webDriverOperations.click(id("pgnt0202001_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
     * <li>ページネーション構成情報を設定するアノテーションを利用し、リクエストパラメータにもページネーション情報がない場合、アノテーションのデフォルト値でページネーション情報が設定されることを確認</li>
     * </ul>
     */
    @Test
    public void testPGNT0202002() throws IOException {
        webDriverOperations.click(id("pgnt0202002_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
        webDriverOperations.click(id("pgnt0202002_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
     * <li>ページネーション構成情報を設定するアノテーションに取得件数のみを設定した場合、アノテーションの設定値でページネーション情報が設定されることを確認</li>
     * </ul>
     */
    @Test
    public void testPGNT0202003() throws IOException {
        webDriverOperations.click(id("pgnt0202003_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
        webDriverOperations.click(id("pgnt0202003_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
     * <li>ソート情報を設定するアノテーションを利用する場合、ページネーション情報を構成するオブジェクトにソート値が設定されることを確認</li>
     * </ul>
     */
    @Test
    public void testPGNT0202004() throws IOException {
        webDriverOperations.click(id("pgnt0202004_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
        webDriverOperations.click(id("pgnt0202004_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
     * <li>ソート項目のデフォルト値のみ指定する場合、ページネーション情報を構成するオブジェクトにソート値が設定されることを確認</li>
     * </ul>
     */
    @Test
    public void testPGNT0202005() throws IOException {
        webDriverOperations.click(id("pgnt0202005_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
        webDriverOperations.click(id("pgnt0202005_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
     * <li>Mybatis3のデータアクセス機能を利用した場合、JSTLのタグを使用して一覧に複数件表示でき、 ページネーションが複数ページ分表示されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testPGNT0502001() throws IOException {
        webDriverOperations.click(id("pgnt0502001_" + VIEW_TYPE));
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
     * <li>Mybatis3のデータアクセス機能を利用した場合、JSTLのタグを使用して一覧に複数件表示でき、ページネーションが単数ページ表分示されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testPGNT0502002() throws IOException {
        webDriverOperations.click(id("pgnt0502002_" + VIEW_TYPE));
        ;
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
     * <li>Mybatis3のデータアクセス機能を利用し、検索結果が0件となる場合、JSTLのタグを使用し、一覧もページネーションも表示されないことを確認する。</li>
     * </ul>
     */
    @Test
    public void testPGNT0502003() throws IOException {
        webDriverOperations.click(id("pgnt0502003_" + VIEW_TYPE));

        webDriverOperations.appendText(id("firstName"), "spring");
        webDriverOperations.click(id("searchBtn"));
        webDriverOperations.waitForDisplayed(urlContains("Search"));

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
     * <li>ページ数がmaxDisplayCount(デフォルト=10)より小さい場合に全ページ用のページネーションが出力されることのテスト デフォルトのHTMLタグ、クラス、クエリでページネーションが作成されることのテスト</li>
     * </ul>
     */
    @Test
    public void testPGNT0602001() {
        webDriverOperations.click(id("pgnt0602001_" + VIEW_TYPE));

        // all page display check
        assertThat(webDriverOperations.getText(By.xpath("//li[3]/a")), is("1"));
        assertThat(webDriverOperations.getText(By.xpath("//li[4]/a")), is("2"));
        assertThat(webDriverOperations.getText(By.xpath("//li[5]/a")), is("3"));

        // currentPage query check
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, 'javascript:void(0)')])[3]")), is("1"));

        // HTML tags outside "<ul>" check
        String pgntXPath = "/html/body/div[2]/div/ul";
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath)),
                notNullValue());
        // HTML tags inside "<li>" check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath + "/li[1]")),
                notNullValue());

        // previousLink, nextLink, firstLink, lastLink check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[1]/a")), is("<<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[2]/a")), is("<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[6]/a")), is(">"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[7]/a")), is(">>"));

        // previousLink value "javascript:void(0)" check
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, 'javascript:void(0)')])[2]")),
                notNullValue());

        // "active" class check
        assertThat(webDriverOperations.getText(By.cssSelector("li.active > a")),
                is("1"));

        // "disabled" class check
        assertThat(webDriverOperations.getText(By.cssSelector(
                "li.disabled > a")), is("<<"));

        // screen capture
        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(By.linkText(">"));

        // move page 2 page check
        assertThat(webDriverOperations.getText(By.cssSelector("li.active > a")),
                is("2"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(By.linkText(">>"));

        // move page 3 page check
        assertThat(webDriverOperations.getText(By.cssSelector("li.active > a")),
                is("3"));

        // screen capture
        webDriverOperations.saveScreenCapture();

    }

    /**
     * <ul>
     * <li>ページ数=maxDisplayCountの場合に全ページ用のページネーションが出力されることのテスト</li>
     * </ul>
     */
    @Test
    public void testPGNT0602002() {
        webDriverOperations.click(id("pgnt0602002_" + VIEW_TYPE));

        // all page display check
        for (int i = 1; i < 11; i++) {
            String elemnetNumber = String.valueOf(i + 2);
            assertThat(webDriverOperations.getText(By.xpath("//li["
                    + elemnetNumber + "]/a")), is(String.valueOf(i)));
        }

        // HTML tags outside "<ul>" check
        String pgntXPath = "/html/body/div[2]/div/ul";
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath)),
                notNullValue());
        // HTML tags inside "<li>" check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath + "/li[1]")),
                notNullValue());

        // previousLink, nextLink, firstLink, lastLink check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[1]/a")), is("<<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[2]/a")), is("<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[13]/a")), is(">"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[14]/a")), is(">>"));

        // previousLink value "javascript:void(0)" check
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, 'javascript:void(0)')])[2]")),
                notNullValue());

        // "active" class check
        assertThat(webDriverOperations.getText(By.cssSelector("li.active > a")),
                is("1"));

        // "disabled" class check
        assertThat(webDriverOperations.getText(By.cssSelector(
                "li.disabled > a")), is("<<"));

        // screen capture
        webDriverOperations.saveScreenCapture();

        for (int i = 2; i < 11; i++) {
            webDriverOperations.click(By.linkText(">"));
            // active page number check
            assertThat(webDriverOperations.getText(By.xpath("//h1")), is(String
                    .valueOf(i) + " Page"));

            // screen capture
            webDriverOperations.saveScreenCapture();
        }
    }

    /**
     * <ul>
     * <li>ページ数がmaxDisplayCountより大きい場合に全ページ用のページネーションが出力されることのテスト</li>
     * </ul>
     */
    @Test
    public void testPGNT0602003() {
        webDriverOperations.click(id("pgnt0602003_" + VIEW_TYPE));

        // all page display check
        for (int i = 1; i < 11; i++) {
            String elemnetNumber = String.valueOf(i + 2);
            assertThat(webDriverOperations.getText(By.xpath("//li["
                    + elemnetNumber + "]/a")), is(String.valueOf(i)));
        }

        // 11 page no display
        assertThat(webDriverOperations.getText(By.xpath("//li[13]/a")), not(
                "11"));

        // HTML tags outside "<ul>" check
        String pgntXPath = "/html/body/div[2]/div/ul";
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath)),
                notNullValue());
        // HTML tags inside "<li>" check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath + "/li[1]")),
                notNullValue());

        // previousLink, nextLink, firstLink, lastLink check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[1]/a")), is("<<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[2]/a")), is("<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[13]/a")), is(">"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[14]/a")), is(">>"));

        // previousLink value "javascript:void(0)" check
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, 'javascript:void(0)')])[2]")),
                notNullValue());

        // "active" class check
        assertThat(webDriverOperations.getText(By.cssSelector("li.active > a")),
                is("1"));

        // "disabled" class check
        assertThat(webDriverOperations.getText(By.cssSelector(
                "li.disabled > a")), is("<<"));

        webDriverOperations.click(By.linkText("6"));

        // move page 6 page check
        assertThat(webDriverOperations.getText(By.cssSelector("li.active > a")),
                is("6"));

        // all page display check
        for (int i = 1; i < 11; i++) {
            String elemnetNumber = String.valueOf(i + 2);
            assertThat(webDriverOperations.getText(By.xpath("//li["
                    + elemnetNumber + "]/a")), is(String.valueOf(i)));
        }

        webDriverOperations.click(By.linkText("7"));

        // move page 7 page check
        assertThat(webDriverOperations.getText(By.cssSelector("li.active > a")),
                is("7"));

        // all page display check
        for (int i = 2; i < 12; i++) {
            String elemnetNumber = String.valueOf(i + 1);
            assertThat(webDriverOperations.getText(By.xpath("//li["
                    + elemnetNumber + "]/a")), is(String.valueOf(i)));
        }

        webDriverOperations.click(By.linkText("<<"));

        for (int i = 1; i < 21; i++) {
            // active page number check
            assertThat(webDriverOperations.getText(By.xpath("//h1")), is(String
                    .valueOf(i) + " Page"));
            // screen capture
            webDriverOperations.saveScreenCapture();

            webDriverOperations.click(By.linkText(">"));
        }

    }

    /**
     * <ul>
     * <li>ページ数が0の場合にページネーションが出力されないことのテスト</li>
     * </ul>
     */
    @Test(expected = NoSuchElementException.class)
    public void testPGNT0602004() {
        webDriverOperations.click(id("pgnt0602004_" + VIEW_TYPE));

        try {
            // Immediate time-out value set
            webDriverOperations.getWebDriver().manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(1));

            // pagination no display
            webDriverOperations.getWebDriver().findElement(By.xpath(
                    "//li[3]/a"));
            fail("error route");
        } catch (NoSuchElementException e) {

            // screen capture
            webDriverOperations.saveScreenCapture();

            throw e;
        }
    }

    /**
     * <ul>
     * <li>Pageオブジェクトがnullの場合にページネーションが出力されないことのテスト</li>
     * </ul>
     */
    @Test(expected = NoSuchElementException.class)
    public void testPGNT0602005() {
        webDriverOperations.click(id("pgnt0602005_" + VIEW_TYPE));

        try {
            // Immediate time-out value set
            webDriverOperations.getWebDriver().manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(1));
            
            // pagination no display
            webDriverOperations.getWebDriver().findElement(By.xpath(
                    "//li[3]/a"));
            fail("error route");
        } catch (NoSuchElementException e) {

            // screen capture
            webDriverOperations.saveScreenCapture();

            throw e;
        }
    }

    /**
     * <ul>
     * <li>currentPage=firstPageの場合にジャンプリンクがactive/disabledになっているかのテスト</li>
     * </ul>
     */
    @Test
    public void testPGNT0602006() {
        webDriverOperations.click(id("pgnt0602006_" + VIEW_TYPE));

        // firstLink disabled
        assertThat(webDriverOperations.getText(By.xpath(
                "//a[contains(@href, 'javascript:void(0)')]")), is("<<"));
        // previousLink disabled
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, 'javascript:void(0)')])[2]")), is("<"));
        // nextLink active
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, '?page=1&size=10')])[2]")), is(">"));
        // lastLink active
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, '?page=2&size=10')])[2]")), is(">>"));

        // HTML tags outside "<ul>" check
        String pgntXPath = "/html/body/div[2]/div/ul";
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath)),
                notNullValue());
        // HTML tags inside "<li>" check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath + "/li[1]")),
                notNullValue());
        // previousLink, nextLink, firstLink, lastLink check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[1]/a")), is("<<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[2]/a")), is("<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[6]/a")), is(">"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[7]/a")), is(">>"));

        // previousLink value "javascript:void(0)" check
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, 'javascript:void(0)')])[2]")),
                notNullValue());

        // "active" class check
        assertThat(webDriverOperations.getText(By.cssSelector("li.active > a")),
                is("1"));

        // "disabled" class check
        assertThat(webDriverOperations.getText(By.cssSelector(
                "li.disabled > a")), is("<<"));

        for (int i = 1; i < 4; i++) {
            // active page number check
            assertThat(webDriverOperations.getText(By.xpath("//h1")), is(String
                    .valueOf(i) + " Page"));

            // screen capture
            webDriverOperations.saveScreenCapture();

            webDriverOperations.click(By.linkText(">"));
        }
    }

    /**
     * <ul>
     * <li>@{@literal firstPage < currentPage < lastPage}の場合にジャンプリンクがactive/disabledになっているかのテスト</li>
     * </ul>
     */
    @Test
    public void testPGNT0602007() {
        webDriverOperations.click(id("pgnt0602007_" + VIEW_TYPE));

        webDriverOperations.click(By.linkText("2"));

        // firstLink active
        assertThat(webDriverOperations.getText(By.xpath(
                "//a[contains(@href, '?page=0&size=10')]")), is("<<"));
        // previousLink active
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, '?page=0&size=10')])[2]")), is("<"));
        // nextLink active
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, '?page=2&size=10')])[2]")), is(">"));
        // lastLink active
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, '?page=2&size=10')])[3]")), is(">>"));

        // HTML tags outside "<ul>" check
        String pgntXPath = "/html/body/div[2]/div/ul";
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath)),
                notNullValue());
        // HTML tags inside "<li>" check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath + "/li[1]")),
                notNullValue());

        // previousLink, nextLink, firstLink, lastLink check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[1]/a")), is("<<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[2]/a")), is("<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[6]/a")), is(">"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[7]/a")), is(">>"));

        // "active" class check
        assertThat(webDriverOperations.getText(By.cssSelector("li.active > a")),
                is("2"));

        webDriverOperations.click(By.linkText("<<"));
        for (int i = 1; i < 4; i++) {
            // active page number check
            assertThat(webDriverOperations.getText(By.xpath("//h1")), is(String
                    .valueOf(i) + " Page"));

            // screen capture
            webDriverOperations.saveScreenCapture();

            webDriverOperations.click(By.linkText(">"));
        }
    }

    /**
     * <ul>
     * <li>currentPage = lastPageの場合にジャンプリンクがactive/disabledになっているかのテスト</li>
     * </ul>
     */
    @Test
    public void testPGNT0602008() {
        webDriverOperations.click(id("pgnt0602008_" + VIEW_TYPE));

        webDriverOperations.click(By.linkText("3"));

        // firstLink active
        assertThat(webDriverOperations.getText(By.xpath(
                "//a[contains(@href, '?page=0&size=10')]")), is("<<"));
        // previousLink active
        assertThat(webDriverOperations.getText(By.xpath(
                "//a[contains(@href, '?page=1&size=10')]")), is("<"));
        // current page disabled
        assertThat(webDriverOperations.getText(By.xpath(
                "//a[contains(@href, 'javascript:void(0)')]")), is("3"));
        // nextLink disabled
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, 'javascript:void(0)')])[2]")), is(">"));
        // lastLink disabled
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, 'javascript:void(0)')])[3]")), is(">>"));

        // HTML tags outside "<ul>" check
        String pgntXPath = "/html/body/div[2]/div/ul";
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath)),
                notNullValue());
        // HTML tags inside "<li>" check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath + "/li[1]")),
                notNullValue());

        // previousLink, nextLink, firstLink, lastLink check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[1]/a")), is("<<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[2]/a")), is("<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[6]/a")), is(">"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[7]/a")), is(">>"));

        // previousLink value "javascript:void(0)" check
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, 'javascript:void(0)')])[2]")),
                notNullValue());

        // "active" class check
        assertThat(webDriverOperations.getText(By.cssSelector("li.active > a")),
                is("3"));

        // "disabled" class check
        assertThat(webDriverOperations.getText(By.cssSelector(
                "li.disabled > a")), is(">"));

        webDriverOperations.click(By.linkText("<<"));
        for (int i = 1; i < 4; i++) {
            // active page number check
            assertThat(webDriverOperations.getText(By.xpath("//h1")), is(String
                    .valueOf(i) + " Page"));

            // screen capture
            webDriverOperations.saveScreenCapture();

            webDriverOperations.click(By.linkText(">"));
        }
    }

    /**
     * <ul>
     * <li>@{@literal fistPage < currentPage < maxDisplayCount < lastPage}の場合にジャンプリンクがactive/disabledになっているかのテスト</li>
     * </ul>
     */
    @Test
    public void testPGNT0602009() {
        webDriverOperations.click(id("pgnt0602009_" + VIEW_TYPE));

        webDriverOperations.click(By.linkText("3"));

        // firstLink active
        assertThat(webDriverOperations.getText(By.xpath(
                "//a[contains(@href, '?page=0&size=15')]")), is("<<"));
        // previousLink active
        assertThat(webDriverOperations.getText(By.xpath(
                "//a[contains(@href, '?page=1&size=15')]")), is("<"));
        // nextLink disabled
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, '?page=3&size=15')])[2]")), is(">"));
        // lastLink disabled
        assertThat(webDriverOperations.getText(By.xpath(
                "//a[contains(@href, '?page=19&size=15')]")), is(">>"));

        // HTML tags outside "<ul>" check
        String pgntXPath = "/html/body/div[2]/div/ul";
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath)),
                notNullValue());
        // HTML tags inside "<li>" check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath + "/li[1]")),
                notNullValue());

        // previousLink, nextLink, firstLink, lastLink check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[1]/a")), is("<<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[2]/a")), is("<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[13]/a")), is(">"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[14]/a")), is(">>"));

        // "active" class check
        assertThat(webDriverOperations.getText(By.cssSelector("li.active > a")),
                is("3"));

        webDriverOperations.click(By.linkText("<<"));
        for (int i = 1; i < 21; i++) {
            // active page number check
            assertThat(webDriverOperations.getText(By.xpath("//h1")), is(String
                    .valueOf(i) + " Page"));

            // screen capture
            webDriverOperations.saveScreenCapture();

            webDriverOperations.click(By.linkText(">"));
        }

    }

    /**
     * <ul>
     * <li>@{@literal fistPage < maxDisplayCount < currentPage < lastPage}の場合にジャンプリンクがactive/disabledになっているかのテスト</li>
     * </ul>
     */
    @Test
    public void testPGNT0602010() {
        webDriverOperations.click(id("pgnt0602010_" + VIEW_TYPE));

        webDriverOperations.click(By.linkText("10"));
        webDriverOperations.click(By.linkText("13"));

        // firstLink active
        assertThat(webDriverOperations.getText(By.xpath(
                "//a[contains(@href, '?page=0&size=15')]")), is("<<"));
        // previousLink active
        assertThat(webDriverOperations.getText(By.xpath(
                "//a[contains(@href, '?page=11&size=15')]")), is("<"));
        // nextLink disabled
        assertThat(webDriverOperations.getText(By.xpath(
                "(//a[contains(@href, '?page=13&size=15')])[2]")), is(">"));
        // lastLink disabled
        assertThat(webDriverOperations.getText(By.xpath(
                "//a[contains(@href, '?page=19&size=15')]")), is(">>"));

        // HTML tags outside "<ul>" check
        String pgntXPath = "/html/body/div[2]/div/ul";
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath)),
                notNullValue());
        // HTML tags inside "<li>" check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath + "/li[1]")),
                notNullValue());

        // previousLink, nextLink, firstLink, lastLink check
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[1]/a")), is("<<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[2]/a")), is("<"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[13]/a")), is(">"));
        assertThat(webDriverOperations.getText(By.xpath(pgntXPath
                + "/li[14]/a")), is(">>"));

        // "active" class check
        assertThat(webDriverOperations.getText(By.cssSelector("li.active > a")),
                is("13"));

        webDriverOperations.click(By.linkText("<<"));
        for (int i = 1; i < 21; i++) {
            // active page number check
            assertThat(webDriverOperations.getText(By.xpath("//h1")), is(String
                    .valueOf(i) + " Page"));

            // screen capture
            webDriverOperations.saveScreenCapture();

            webDriverOperations.click(By.linkText(">"));
        }
    }

    /**
     * <ul>
     * <li>active/disabledのページリンクを押下しても、リクエストが送信されないこと。（デフォルト"javascript:void(0)"のため）</li>
     * </ul>
     */
    @Test
    public void testPGNT0602011() {
        webDriverOperations.click(id("pgnt0602011_" + VIEW_TYPE));

        // default page
        String orgXtrack = webDriverOperations.getText(By.id("xTrack"));

        // firstLink disabled click
        webDriverOperations.click(By.linkText("<<"));
        String firstLinkXtrack = webDriverOperations.getText(By.id("xTrack"));
        // not change xtrack
        assertThat(firstLinkXtrack, is(orgXtrack));

        // previousLink disabled click
        webDriverOperations.click(By.linkText("<"));
        String previousLinkXtrack = webDriverOperations.getText(By.id(
                "xTrack"));
        // not change xtrack
        assertThat(previousLinkXtrack, is(orgXtrack));

        // currentPage(1Page) active click
        webDriverOperations.click(By.linkText("<"));
        String currentPageXtrack = webDriverOperations.getText(By.id("xTrack"));
        // not change xtrack
        assertThat(currentPageXtrack, is(orgXtrack));

        // nextLink click
        webDriverOperations.click(By.linkText(">"));
        String nextLinkXtrack = webDriverOperations.getText(By.id("xTrack"));
        // change xtrack
        assertThat(nextLinkXtrack, is(not(orgXtrack)));
    }

    /**
     * <ul>
     * <li>結果情報を用いてページネーションに関連する情報出力する場合、 合計ページ数、ページ数、合計件数を出力することが可能なことを確認する</li>
     * </ul>
     */
    @Test
    public void testPGNT0603001() throws IOException {
        webDriverOperations.click(id("pgnt0603001_" + VIEW_TYPE));
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
     * <li>結果情報が0件の場合、合計ページ、表示ページ、合計件数を出力することが可能なことを確認する</li>
     * </ul>
     */
    @Test
    public void testPGNT0603002() throws IOException {
        webDriverOperations.click(id("pgnt0603002_" + VIEW_TYPE));

        webDriverOperations.appendText(id("firstName"), "spring");
        webDriverOperations.click(id("searchBtn"));

        // 取得結果の確認
        assertThat(webDriverOperations.getText(By.xpath("//div[4]")), is(
                "0 results"));

    }
}
