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
package jp.co.ntt.fw.spring.functionaltest.selenium.tlly;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class TilesLayoutTest extends FunctionTestSupport {

    /**
     * <ul>
     * <li>Tilesレイアウトを適用するパスにリクエストした場合、定義したレイアウトが有効になることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTLLY0101001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("tlly0101001"));
        }

        // デフォルトレイアウトの確認（タイトル固定、X-Trackあり）
        {
            assertThat(webDriverOperations.getTitle(), is(
                    "Spring Functional Test"));
            assertThat(webDriverOperations.getText(id("bodyTitle")), is(
                    "Register Staff Information"));
            assertThat(webDriverOperations.getXTrack(), not(""));
        }

    }

    /**
     * <ul>
     * <li>Tilesレイアウトを適用していないパスにリクエストした場合、定義したレイアウトが有効にならないことを確認する。</li>
     * </ul>
     */
    @Test
    public void testTLLY0102001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("tlly0102001"));
        }

        // レイアウト未適応の確認（X-Trackなし）
        {
            assertThat(webDriverOperations.getTitle(), is(
                    "Spring Functional Test"));
            assertThat(webDriverOperations.getText(id("bodyTitle")), is(
                    "Delete Staff Information"));

            By idOfXTrack = webDriverOperations.getIdOfXTrack();
            assertFalse(webDriverOperations.exists(idOfXTrack));

        }

    }

    /**
     * <ul>
     * <li>Tilesの定義ファイルに、複数のレイアウトが定義されている場合、URLのパターンでレイアウトを振り分けることができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTLLY0201001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("tlly0201001"));
        }

        // 複数レイアウト定義時の一つ目適応確認（タイトル固定、X-Trackあり）
        {
            assertThat(webDriverOperations.getTitle(), is(
                    "Update Staff Information (TLLY template is valid.)"));
            assertThat(webDriverOperations.getText(id("bodyTitle")), is(
                    "Update Staff Information"));
            assertThat(webDriverOperations.getXTrack(), not(""));
        }

    }

    /**
     * <ul>
     * <li>Tilesの定義ファイルに、複数のレイアウトが定義されている場合、URLのパターンでレイアウトを振り分けることができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTLLY0201002() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("tlly0201002"));
        }

        // 複数レイアウト定義時の二つ目適応確認（タイトル固定、X-Trackあり）
        {
            assertThat(webDriverOperations.getTitle(), is(
                    "Search Staff Information (TLLY template is valid.)"));
            assertThat(webDriverOperations.getText(id("bodyTitle")), is(
                    "Search Staff Information"));
            assertThat(webDriverOperations.getXTrack(), not(""));
        }

    }

    /**
     * <ul>
     * <li>Tilesの定義ファイルに、複数のレイアウトが定義されている場合、Tilesの定義ファイルに記述した順番で、 最初にURLパターンにマッチするレイアウトが使用されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTLLY0202001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("tlly0202001"));
        }

        // 複数レイアウト定義時の一つ目適応確認（タイトル固定、X-Trackあり）
        {
            assertThat(webDriverOperations.getTitle(), is(
                    "Update Staff Information (TLLY template is valid.)"));
            assertThat(webDriverOperations.getText(id("bodyTitle")), is(
                    "Update Staff Information"));
            assertThat(webDriverOperations.getXTrack(), not(""));
        }

    }

}
