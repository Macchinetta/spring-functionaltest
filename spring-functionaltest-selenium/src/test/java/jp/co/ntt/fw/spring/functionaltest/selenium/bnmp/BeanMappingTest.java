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
package jp.co.ntt.fw.spring.functionaltest.selenium.bnmp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.openqa.selenium.By.id;

import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class BeanMappingTest extends FunctionTestSupport {

    /**
     * <ul>
     * <li>デフォルト設定で、同名同型フィールドのコピーができること。</li>
     * <li>(異名同型のフィールドはコピーされないこと。)</li>
     * <li>(コピー元に存在しないフィールドはコピーされないこと。)</li>
     * </ul>
     */
    @Test
    public void testBNMP0101001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0101001"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("firstName"), "やまだ");
            webDriverOperations.appendText(id("lastName"), "はなこ");
            webDriverOperations.appendText(id("age"), "20");
            webDriverOperations.appendText(id("birthDate"), "2011-11-11");
            webDriverOperations.click(id("copySameNameSameTypeField"));
        }

        // コピー先(Destination)の内容確認
        {
            // コピー元Beanからコピー先Beanへ、同名同型フィールドの値がコピーされること。
            assertThat(webDriverOperations.getText(id("getLastName")), is(
                    "はなこ"));
            assertThat(webDriverOperations.getText(id("getAge")), is("20"));
            assertThat(webDriverOperations.getText(id("getBirthDate")), is(
                    "2011/11/11"));

            // コピー元Beanとコピー先Beanでフィールド名が異なればコピーされないこと。
            assertThat(webDriverOperations.getText(id("getMyoji")), is(
                    "DestinationMyoji"));

            // コピー元に存在しないフィールドはコピーされないこと。
            assertThat(webDriverOperations.getText(id("getSex")), is("M"));
        }
    }

    /**
     * <ul>
     * <li>デフォルト設定で、同名異型フィールドのコピーができること。</li>
     * </ul>
     */
    @Test
    public void testBNMP0102001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0102001"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("firstName"), "やまだ");
            webDriverOperations.appendText(id("lastName"), "たかし");
            webDriverOperations.appendText(id("age"), "25");
            webDriverOperations.appendText(id("birthDate"), "2011-11-11");
            webDriverOperations.click(id("copySameNameDifferenceTypeBean"));
        }

        // コピー先(Destination)の内容確認
        {
            // コピー元Beanからコピー先Beanへ、同名異型フィールドの値がコピーされること。
            assertThat(webDriverOperations.getText(id("getFirstName")), is(
                    "やまだ"));
            assertThat(webDriverOperations.getText(id("getLastName")), is(
                    "たかし"));
            assertThat(webDriverOperations.getText(id("getAge")), is("25"));
            assertThat(webDriverOperations.getText(id("getBirthDate")), is(
                    "2011-11-11"));
        }
    }

    /**
     * <ul>
     * <li>カスタム設定で、マッピング定義した異名フィールドのコピーができること。</li>
     * <li>（マッピング定義していない異名フィールドはコピーされないこと。）</li>
     * </ul>
     */
    @Test
    public void testBNMP0103001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0103001"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("firstName"), "やまだ");
            webDriverOperations.appendText(id("lastName"), "たかし");
            webDriverOperations.appendText(id("age"), "25");
            webDriverOperations.appendText(id("birthDate"), "2011-11-11");
            webDriverOperations.click(id("copyDifferenceNameField"));
        }

        // コピー先(Destination)の内容確認
        {
            // コピー元Beanからコピー先Beanへ、異名フィールドの値がコピーされること。
            assertThat(webDriverOperations.getText(id("getMyoji")), is("やまだ"));
            assertThat(webDriverOperations.getText(id("getAge")), is("25"));
            assertThat(webDriverOperations.getText(id("getTanjobi")), is(
                    "2011/11/11"));

            // マッピング定義していない異名フィールドはコピーされないこと。
            assertThat(webDriverOperations.getText(id("getNamae")), is(""));
        }
    }

    /**
     * <ul>
     * <li>単方向のコピーができること。</li>
     * </ul>
     */
    @Test
    public void testBNMP0104001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0104001"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("carName"), "デニオ");
            webDriverOperations.appendText(id("carColor"), "赤");
            webDriverOperations.appendText(id("carReleaseDate"), "20140117");
            webDriverOperations.click(id("copyUnidirectionalBean"));
        }

        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getCarName")), is(
                    "デニオ"));
            assertThat(webDriverOperations.getText(id("getCarColor")), is("赤"));
            assertThat(webDriverOperations.getText(id("getCarReleaseDate")), is(
                    "20140117"));
        }
    }

    /**
     * <ul>
     * <li>単方向の逆コピーができないこと。</li>
     * </ul>
     */
    @Test
    public void testBNMP0104002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0104002"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("carName"), "デニオ");
            webDriverOperations.appendText(id("carColor"), "赤");
            webDriverOperations.appendText(id("carReleaseDate"), "20140117");
            webDriverOperations.click(id("copyUnidirectionalBean"));
        }

        // コピー先Bean作成後フィールドに値を設定し、Mapperを使用してコピー元Beanを新規作成する。（逆コピー）
        {
            webDriverOperations.click(id("copyUnidirectionalBean"));
        }

        // コピー先Beanからコピー元Beanへ、フィールドの値がコピーされないこと。
        {
            assertThat(webDriverOperations.getText(id("getCarName")), is(""));
            assertThat(webDriverOperations.getText(id("getCarColor")), is(""));
            assertThat(webDriverOperations.getText(id("getCarReleaseDate")), is(
                    ""));
        }
    }

    /**
     * <ul>
     * <li>双方向のコピーができること。</li>
     * </ul>
     */
    @Test
    public void testBNMP0104003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0104003"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("carName"), "デニオ");
            webDriverOperations.appendText(id("carColor"), "赤");
            webDriverOperations.appendText(id("carReleaseDate"), "20140117");
            webDriverOperations.click(id("copyBidirectionalBean"));
        }

        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getCarName")), is(
                    "デニオ"));
            assertThat(webDriverOperations.getText(id("getCarColor")), is("赤"));
            assertThat(webDriverOperations.getText(id("getCarReleaseDate")), is(
                    "20140117"));
        }
    }

    /**
     * <ul>
     * <li>双方向の逆コピーができること</li>
     * </ul>
     */
    @Test
    public void testBNMP0104004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0104004"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("carName"), "デニオ");
            webDriverOperations.appendText(id("carColor"), "赤");
            webDriverOperations.appendText(id("carReleaseDate"), "20140117");
            webDriverOperations.click(id("copyBidirectionalBean"));
        }

        // コピー先Bean作成後フィールドに値を設定し、Mapperを使用してコピー元Beanを新規作成する。（逆コピー）
        {
            webDriverOperations.click(id("copyBidirectionalBean"));
        }

        // コピー先Beanからコピー元Beanへ、フィールドの値がコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getCarName")), is(
                    "デニオ"));
            assertThat(webDriverOperations.getText(id("getCarColor")), is("赤"));
            assertThat(webDriverOperations.getText(id("getCarReleaseDate")), is(
                    "20140117"));
        }
    }

    /**
     * <ul>
     * <li>Dozer 6.2.0より、クラスベースでマッピングを定義している場合も、type="one-way"を付与すれば単方向の逆コピーができないこと。</li>
     * </ul>
     */
    @Test
    public void testBNMP0104005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0104005"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("name"), "デニオ");
            webDriverOperations.appendText(id("color"), "赤");
            webDriverOperations.appendText(id("releaseDate"), "20140117");
            webDriverOperations.click(id("copyUnidirectionalBean"));
        }

        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getCarName")), is(
                    "デニオ"));
            assertThat(webDriverOperations.getText(id("getCarColor")), is("赤"));
            assertThat(webDriverOperations.getText(id("getCarReleaseDate")), is(
                    "20140117"));
        }

        // コピー先Bean作成後フィールドに値を設定し、Mapperを使用してコピー元Beanを新規作成する。（逆コピー）
        {
            webDriverOperations.click(id("copyUnidirectionalBean"));
        }

        // コピー先Beanからコピー元Beanへ、フィールドの値がコピーされないこと。
        {
            assertThat(webDriverOperations.getText(id("getCarName")), is(""));
            assertThat(webDriverOperations.getText(id("getCarColor")), is(""));
            assertThat(webDriverOperations.getText(id("getCarReleaseDate")), is(
                    ""));
        }
    }

    /**
     * <ul>
     * <li>Dozer 6.2.0より、同名フィールドをfieldタグでマッピングを定義している場合も、type="one-way"を付与すれば単方向の逆コピーができないこと。</li>
     * </ul>
     */
    @Test
    public void testBNMP0104006() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0104006"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("name"), "デニオ");
            webDriverOperations.appendText(id("color"), "赤");
            webDriverOperations.appendText(id("releaseDate"), "20140117");
            webDriverOperations.click(id("copyUnidirectionalBean"));
        }

        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getCarName")), is(
                    "デニオ"));
            assertThat(webDriverOperations.getText(id("getCarColor")), is("赤"));
            assertThat(webDriverOperations.getText(id("getCarReleaseDate")), is(
                    "20140117"));
        }

        // コピー先Bean作成後フィールドに値を設定し、Mapperを使用してコピー元Beanを新規作成する。（逆コピー）
        {
            webDriverOperations.click(id("copyUnidirectionalBean"));
        }

        // コピー先Beanからコピー元Beanへ、フィールドの値がコピーされないこと。
        {
            assertThat(webDriverOperations.getText(id("getCarName")), is(""));
            assertThat(webDriverOperations.getText(id("getCarColor")), is(""));
            assertThat(webDriverOperations.getText(id("getCarReleaseDate")), is(
                    ""));
        }
    }

    /**
     * <ul>
     * <li>ネストされたフィールドのコピーができること。</li>
     * </ul>
     */
    @Test
    public void testBNMP0105001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0105001"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("firstName"), "さとう");
            webDriverOperations.appendText(id("lastName"), "はなこ");
            webDriverOperations.appendText(id("age"), "20");
            webDriverOperations.click(id("copyNestedFieldBean"));
        }

        // コピー先(Destination)の内容確認
        // コピー元Beanからコピー先Bean、及びコピー先でネストされたBeanへ、フィールドの値がコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getFirstName")), is(
                    "さとう"));
            assertThat(webDriverOperations.getText(id("getLastName")), is(
                    "はなこ"));
            assertThat(webDriverOperations.getText(id("getAge")), is("20"));
        }
    }

    /**
     * <ul>
     * <li>同名フィールド（Collection系）で値重複なしのコピーができること。</li>
     * </ul>
     */
    @Test
    public void testBNMP0106001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0106001"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("emails0.email"),
                    "hogehoge@example.com");
            webDriverOperations.appendText(id("emails1.email"),
                    "fugafuga@example.com");
            webDriverOperations.appendText(id("emails2.email"),
                    "mogemoge@example.com");
            webDriverOperations.click(id(
                    "copySameFieldCumulativeListToListSameType"));
        }

        // コピー先(Destination)の内容確認(List -> List 同じ要素)
        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getEmail1")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail2")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail3")), is(
                    "moge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail4")), is(
                    "hogehoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail5")), is(
                    "fugafuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail6")), is(
                    "mogemoge@example.com"));
        }

        webDriverOperations.back();

        // コピー元(Source)の内容入力
        {
            webDriverOperations.overrideText(id("emails0.email"),
                    "hogehoge@example.com");
            webDriverOperations.overrideText(id("emails1.email"),
                    "fugafuga@example.com");
            webDriverOperations.overrideText(id("emails2.email"),
                    "mogemoge@example.com");
            webDriverOperations.click(id(
                    "copySameFieldCumulativeListToListDifferenceType"));
        }

        // コピー先(Destination)の内容確認(List -> List 異なる要素)
        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getEmail1")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail2")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail3")), is(
                    "moge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail4")), is(
                    "hogehoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail5")), is(
                    "fugafuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail6")), is(
                    "mogemoge@example.com"));
        }

        webDriverOperations.back();

        // コピー元(Source)の内容入力
        {
            webDriverOperations.overrideText(id("emails0.email"),
                    "hogehoge@example.com");
            webDriverOperations.overrideText(id("emails1.email"),
                    "fugafuga@example.com");
            webDriverOperations.overrideText(id("emails2.email"),
                    "mogemoge@example.com");
            webDriverOperations.click(id("copySameFieldCumulativeListToSet"));
        }

        // コピー先(Destination)の内容確認(List -> Set)
        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getEmail1")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail2")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail3")), is(
                    "moge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail4")), is(
                    "hogehoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail5")), is(
                    "fugafuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail6")), is(
                    "mogemoge@example.com"));
        }
    }

    /**
     * <ul>
     * <li>異名フィールド（Collection系）で値重複なしのコピーができること。</li>
     * </ul>
     */
    @Test
    public void testBNMP0106002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0106002"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("emails0.email"),
                    "hogehoge@example.com");
            webDriverOperations.appendText(id("emails1.email"),
                    "fugafuga@example.com");
            webDriverOperations.appendText(id("emails2.email"),
                    "mogemoge@example.com");
            webDriverOperations.click(id(
                    "copyDifferenceFieldCumulativeListToListSameType"));
        }

        // コピー先(Destination)の内容確認
        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getEmail1")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail2")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail3")), is(
                    "moge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail4")), is(
                    "hogehoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail5")), is(
                    "fugafuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail6")), is(
                    "mogemoge@example.com"));
        }

        webDriverOperations.back();

        // コピー元(Source)の内容入力
        {
            webDriverOperations.overrideText(id("emails0.email"),
                    "hogehoge@example.com");
            webDriverOperations.overrideText(id("emails1.email"),
                    "fugafuga@example.com");
            webDriverOperations.overrideText(id("emails2.email"),
                    "mogemoge@example.com");
            webDriverOperations.click(id(
                    "copyDifferenceFieldCumulativeListToListDifferenceType"));
        }

        // コピー先(Destination)の内容確認(List -> List 異なる要素)
        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getEmail1")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail2")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail3")), is(
                    "moge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail4")), is(
                    "hogehoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail5")), is(
                    "fugafuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail6")), is(
                    "mogemoge@example.com"));
        }

        webDriverOperations.back();

        // コピー元(Source)の内容入力
        {
            webDriverOperations.overrideText(id("emails0.email"),
                    "hogehoge@example.com");
            webDriverOperations.overrideText(id("emails1.email"),
                    "fugafuga@example.com");
            webDriverOperations.overrideText(id("emails2.email"),
                    "mogemoge@example.com");
            webDriverOperations.click(id(
                    "copyDifferenceFieldCumulativeListToSet"));
        }

        // コピー先(Destination)の内容確認(List -> Set)
        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getEmail1")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail2")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail3")), is(
                    "moge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail4")), is(
                    "hogehoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail5")), is(
                    "fugafuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail6")), is(
                    "mogemoge@example.com"));
        }
    }

    /**
     * <ul>
     * <li>同名フィールド（Collection系）で値重複あり、重複コピー可のコピーができること。(cumulative)</li>
     * </ul>
     */
    @Test
    public void testBNMP0106003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0106003"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("emails0.email"),
                    "hoge@example.com");
            webDriverOperations.appendText(id("emails1.email"),
                    "fuga@example.com");
            webDriverOperations.appendText(id("emails2.email"),
                    "mogemoge@example.com");
            webDriverOperations.click(id(
                    "copySameFieldCumulativeListToListSameType"));
        }

        // コピー先(Destination)の内容確認
        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        // コピー先Beanに値が重複して存在すること。
        {
            assertThat(webDriverOperations.getText(id("getEmail1")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail2")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail3")), is(
                    "moge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail4")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail5")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail6")), is(
                    "mogemoge@example.com"));
        }
    }

    /**
     * <ul>
     * <li>異名フィールド（Collection系）で値重複あり、重複コピー可のコピーができること。(cumulative)</li>
     * </ul>
     */
    @Test
    public void testBNMP0106004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0106004"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("emails0.email"),
                    "hoge@example.com");
            webDriverOperations.appendText(id("emails1.email"),
                    "fuga@example.com");
            webDriverOperations.appendText(id("emails2.email"),
                    "mogemoge@example.com");
            webDriverOperations.click(id(
                    "copyDifferenceFieldCumulativeListToListSameType"));
        }

        // コピー先(Destination)の内容確認
        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        // コピー先Beanに値が重複して存在すること。
        {
            assertThat(webDriverOperations.getText(id("getEmail1")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail2")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail3")), is(
                    "moge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail4")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail5")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail6")), is(
                    "mogemoge@example.com"));
        }
    }

    /**
     * <ul>
     * <li>同名フィールド（Collection系）で値重複あり、重複コピー無視のコピーができること。(non-cumulative)</li>
     * </ul>
     */
    @Test
    public void testBNMP0106005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0106005"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("emails0.email"),
                    "hoge@example.com");
            webDriverOperations.appendText(id("emails1.email"),
                    "fuga@example.com");
            webDriverOperations.appendText(id("emails2.email"),
                    "mogemoge@example.com");
            webDriverOperations.click(id("copySameFieldNonCumulative"));
        }

        // コピー先(Destination)の内容確認
        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        // コピー先Beanに値が重複して存在しないこと。
        {
            assertThat(webDriverOperations.getText(id("getEmail1")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail2")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail3")), is(
                    "moge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail4")), is(
                    "mogemoge@example.com"));
        }
    }

    /**
     * <ul>
     * <li>異名フィールド（Collection系）で値重複あり、重複コピー無視のコピーができること。(non-cumulative)</li>
     * </ul>
     */
    @Test
    public void testBNMP0106006() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0106006"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("emails0.email"),
                    "hoge@example.com");
            webDriverOperations.appendText(id("emails1.email"),
                    "fuga@example.com");
            webDriverOperations.appendText(id("emails2.email"),
                    "mogemoge@example.com");
            webDriverOperations.click(id("copyDifferenceFieldNonCumulative"));
        }

        // コピー先(Destination)の内容確認
        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        // コピー先Beanに値が重複して存在しないこと。
        {
            assertThat(webDriverOperations.getText(id("getEmail1")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail2")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail3")), is(
                    "moge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail4")), is(
                    "mogemoge@example.com"));
        }
    }

    /**
     * <ul>
     * <li>同名フィールド（Collection系）でコピー先の中身破棄してディープコピーができること。(non-cumulativeかつremove-orphans=true)</li>
     * </ul>
     */
    @Test
    public void testBNMP0106007() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0106007"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("emails0.email"),
                    "hoge@example.com");
            webDriverOperations.appendText(id("emails1.email"),
                    "fuga@example.com");
            webDriverOperations.appendText(id("emails2.email"),
                    "mogemoge@example.com");
            webDriverOperations.click(id("copySameFieldNonCumulativeDeepCopy"));
        }

        // コピー先(Destination)の内容確認
        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        // コピー先Beanに値が重複して存在しないこと。
        {
            // 文字列の確認
            assertThat(webDriverOperations.getText(id("getEmail1")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail2")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail3")), is(
                    "mogemoge@example.com"));

            // コピー元Collectionとコピー先Collection参照先の一致確認
            // non-cumulativeかつremove-orphans=trueの場合、コピー先の参照先になる
            assertThat(webDriverOperations.getText(id(
                    "getCollectionRefferenceFromTo")), is("false"));
            assertThat(webDriverOperations.getText(id(
                    "getCollectionRefferenceDestTo")), is("true"));

            // コピー元Beanとコピー先Beanのオブジェクト参照先の一致確認(true or false)
            // ディープコピーの為、一致しない(false)
            assertThat(webDriverOperations.getText(id("getObjectRefference1")),
                    is("false"));
            assertThat(webDriverOperations.getText(id("getObjectRefference2")),
                    is("false"));
            assertThat(webDriverOperations.getText(id("getObjectRefference3")),
                    is("false"));
        }
    }

    /**
     * <ul>
     * <li>異名フィールド（Collection系）でコピー先の中身破棄してディープコピーができること。(non-cumulativeかつremove-orphans=true)</li>
     * </ul>
     */
    @Test
    public void testBNMP0106008() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0106008"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("emails0.email"),
                    "hoge@example.com");
            webDriverOperations.appendText(id("emails1.email"),
                    "fuga@example.com");
            webDriverOperations.appendText(id("emails2.email"),
                    "mogemoge@example.com");
            webDriverOperations.click(id(
                    "copyDifferenceFieldNonCumulativeDeepCopy"));
        }

        // コピー先(Destination)の内容確認
        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        // コピー先Beanに値が重複して存在しないこと。
        {
            // 文字列の確認
            assertThat(webDriverOperations.getText(id("getEmail1")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail2")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail3")), is(
                    "mogemoge@example.com"));

            // コピー元Collectionとコピー先Collection参照先の一致確認
            // non-cumulativeかつremove-orphans=trueの場合、コピー先の参照先になる
            assertThat(webDriverOperations.getText(id(
                    "getCollectionRefferenceFromTo")), is("false"));
            assertThat(webDriverOperations.getText(id(
                    "getCollectionRefferenceDestTo")), is("true"));

            // コピー元Beanとコピー先Beanのオブジェクト参照先の一致確認(true or false)
            // ディープコピーの為、一致しない(false)
            assertThat(webDriverOperations.getText(id("getObjectRefference1")),
                    is("false"));
            assertThat(webDriverOperations.getText(id("getObjectRefference2")),
                    is("false"));
            assertThat(webDriverOperations.getText(id("getObjectRefference3")),
                    is("false"));
        }
    }

    /**
     * <ul>
     * <li>同名フィールド（Collection系）でコピー先の中身破棄してシャローコピーができること。(copy-by-reference)</li>
     * </ul>
     */
    @Test
    public void testBNMP0106009() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0106009"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("emails0.email"),
                    "hoge@example.com");
            webDriverOperations.appendText(id("emails1.email"),
                    "fuga@example.com");
            webDriverOperations.appendText(id("emails2.email"),
                    "mogemoge@example.com");
            webDriverOperations.click(id(
                    "copySameFieldNonCumulativeShallowCopy"));
        }

        // コピー先(Destination)の内容確認
        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        // コピー先Beanに値が重複して存在しないこと。
        {
            // 文字列の確認
            assertThat(webDriverOperations.getText(id("getEmail1")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail2")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail3")), is(
                    "mogemoge@example.com"));

            // コピー元Collectionとコピー先Collection参照先の一致確認
            // copy-by-referenceの場合、コピー元の参照先になる
            assertThat(webDriverOperations.getText(id(
                    "getCollectionRefferenceFromTo")), is("true"));
            assertThat(webDriverOperations.getText(id(
                    "getCollectionRefferenceDestTo")), is("false"));

            // コピー元Beanとコピー先Beanのオブジェクト参照先の一致確認(true or false)
            // シャローコピーの為、一致する(true)
            assertThat(webDriverOperations.getText(id("getObjectRefference1")),
                    is("true"));
            assertThat(webDriverOperations.getText(id("getObjectRefference2")),
                    is("true"));
            assertThat(webDriverOperations.getText(id("getObjectRefference3")),
                    is("true"));
        }
    }

    /**
     * <ul>
     * <li>異名フィールド（Collection系）でコピー先の中身破棄してシャローコピーができること。(copy-by-reference)</li>
     * </ul>
     */
    @Test
    public void testBNMP0106010() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0106010"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("emails0.email"),
                    "hoge@example.com");
            webDriverOperations.appendText(id("emails1.email"),
                    "fuga@example.com");
            webDriverOperations.appendText(id("emails2.email"),
                    "mogemoge@example.com");
            webDriverOperations.click(id(
                    "copyDifferenceFieldNonCumulativeShallowCopy"));
        }

        // コピー先(Destination)の内容確認
        // コピー元Beanからコピー先Beanへ、フィールドの値がコピーされること。
        // コピー先Beanに値が重複して存在しないこと。
        {
            // 文字列の確認
            assertThat(webDriverOperations.getText(id("getEmail1")), is(
                    "hoge@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail2")), is(
                    "fuga@example.com"));
            assertThat(webDriverOperations.getText(id("getEmail3")), is(
                    "mogemoge@example.com"));

            // コピー元Collectionとコピー先Collection参照先の一致確認
            // copy-by-referenceの場合、コピー元の参照先になる
            assertThat(webDriverOperations.getText(id(
                    "getCollectionRefferenceFromTo")), is("true"));
            assertThat(webDriverOperations.getText(id(
                    "getCollectionRefferenceDestTo")), is("false"));

            // コピー元Beanとコピー先Beanのオブジェクト参照先の一致確認(true or false)
            // シャローコピーの為、一致する(true)
            assertThat(webDriverOperations.getText(id("getObjectRefference1")),
                    is("true"));
            assertThat(webDriverOperations.getText(id("getObjectRefference2")),
                    is("true"));
            assertThat(webDriverOperations.getText(id("getObjectRefference3")),
                    is("true"));
        }
    }

    /**
     * <ul>
     * <li>カスタムコンバーターを使用してコピーができること。</li>
     * </ul>
     */
    @Test
    public void testBNMP0201001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0201001"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("firstName"), "やまだ");
            webDriverOperations.appendText(id("lastName"), "たろう");
            webDriverOperations.appendText(id("age"), "30");
            webDriverOperations.appendText(id("birthDate"), "19840112");
            webDriverOperations.click(id("copyWithCustomConverter"));
        }

        // コピー先(Destination)の内容確認
        // コピー元Beanからコピー先Beanへ、フィールドの値が変換されてコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getFirstName")), is(
                    "やまだ"));
            assertThat(webDriverOperations.getText(id("getLastName")), is(
                    "たろう"));
            assertThat(webDriverOperations.getText(id("getAge")), is("30"));
            assertThat(webDriverOperations.getText(id("getBirthDate")), is(
                    "19840112"));
        }
    }

    /**
     * <ul>
     * <li>Beanの指定フィールドを除外してコピーできること。</li>
     * </ul>
     */
    @Test
    public void testBNMP0301001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0301001"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("firstName"), "さとう");
            webDriverOperations.appendText(id("lastName"), "ごろう");
            webDriverOperations.appendText(id("age"), "40");
            webDriverOperations.appendText(id("birthDate"), "19740112");
            webDriverOperations.click(id("fieldExclusions"));
        }

        // コピー先(Destination)の内容確認
        // 除外設定の有るフィールドはコピー元Beanからコピー先Beanへコピーされないこと。
        {
            assertThat(webDriverOperations.getText(id("getFirstName")), is(
                    "さとう"));
            assertThat(webDriverOperations.getText(id("getLastName")), is(
                    "ごろう"));
            assertThat(webDriverOperations.getText(id("getAge")), is("40"));
            assertThat(webDriverOperations.getText(id("getBirthDate")), is(
                    "99991231"));
        }
    }

    /**
     * <ul>
     * <li>Beanの適用範囲を設定してコピーできること。</li>
     * </ul>
     */
    @Test
    public void testBNMP0302001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0302001"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("firstName"), "やまだ");
            webDriverOperations.appendText(id("lastName"), "はなこ");
            webDriverOperations.appendText(id("age"), "20");
            webDriverOperations.appendText(id("birthDate"), "19940105");
            webDriverOperations.click(id("scopeSetting"));
        }

        // コピー先(Destination)の内容確認
        // 提供範囲のフィールドがコピー元Beanからコピー先Beanへコピーされること。
        {
            // 適用なし
            assertThat(webDriverOperations.getText(id("getFirstName")), is(
                    "やまだ"));
            assertThat(webDriverOperations.getText(id("getLastName")), is(
                    "はなこ"));
            assertThat(webDriverOperations.getText(id("getAge")), is("20"));
            assertThat(webDriverOperations.getText(id("getBirthDate")), is(
                    "19940105"));

            // 適用あり
            assertThat(webDriverOperations.getText(id("getScopedFirstName")),
                    is("やまだ"));
            assertThat(webDriverOperations.getText(id("getScopedLastName")), is(
                    "dummy"));
            assertThat(webDriverOperations.getText(id("getScopedAge")), is(
                    "20"));
            assertThat(webDriverOperations.getText(id("getScopedBirthDate")),
                    is("99991231"));
        }
    }

    /**
     * <ul>
     * <li>Beanのnull・空フィールドを除外してコピーできること。</li>
     * </ul>
     */
    @Test
    public void testBNMP0303001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0303001"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("firstName"), "さとう");
            // lastNameは未入力(empty)
            webDriverOperations.appendText(id("age"), "40");
            // birthDateは未入力(null)
            webDriverOperations.click(id("excludeNullEmpty"));
        }

        // コピー先(Destination)の内容確認
        // null・空フィールドはコピー元Beanからコピー先Beanへコピーされないこと。
        {
            assertThat(webDriverOperations.getText(id("getFirstName")), is(
                    "さとう"));
            assertThat(webDriverOperations.getText(id("getLastName")), is(
                    "dummy"));
            assertThat(webDriverOperations.getText(id("getAge")), is("40"));
            assertThat(webDriverOperations.getText(id("getBirthDate")), is(
                    "99991231"));
        }
    }

    /**
     * <ul>
     * <li>文字列型フィールドから日付・時刻型フィールドにコピーができること。</li>
     * </ul>
     */
    @Test
    public void testBNMP0304001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0304001"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("birthDate"),
                    "2013-10-10 11:11:11.111");
            webDriverOperations.appendText(id("birthDateSqlDate"),
                    "2013-10-10");
            webDriverOperations.appendText(id("birthDateSqlTime"),
                    "11:11:11.111");
            webDriverOperations.appendText(id("birthDateCalendar"),
                    "2014-10-10 11:11:11.111");
            webDriverOperations.appendText(id("birthDateGregorianCalendar"),
                    "2014-10-10 11:11:11.111");
            webDriverOperations.appendText(id("birthDateTimestamp"),
                    "2015-10-10 11:11:11.111");
            webDriverOperations.appendText(id("birthDateLocalDate"),
                    "2013-10-10");
            webDriverOperations.appendText(id("birthDateLocalTime"),
                    "11:11:11.111");
            webDriverOperations.appendText(id("birthDateLocalDateTime"),
                    "2013-10-10 11:11:11.111");
            webDriverOperations.appendText(id("birthDateOffsetTime"),
                    "11:11:11.111+09:00");
            webDriverOperations.appendText(id("birthDateOffsetDateTime"),
                    "2013-10-10 11:11:11.111+09:00");
            webDriverOperations.appendText(id("birthDateZonedDateTime"),
                    "2013-10-10 11:11:11.111+08:00[Asia/Shanghai]");
            webDriverOperations.click(id("stringToDate"));
        }

        // コピー先(Destination)の内容確認
        // コピー元Beanからコピー先Beanへコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getBirthDate")), is(
                    "2013/10/10 11:11:11.111"));
            assertThat(webDriverOperations.getText(id("getBirthDateSqlDate")),
                    is("2013/10/10"));
            assertThat(webDriverOperations.getText(id("getBirthDateSqlTime")),
                    is("11:11:11.111"));
            assertThat(webDriverOperations.getText(id("getBirthDateCalendar")),
                    is("2014/10/10 11:11:11.111"));
            assertThat(webDriverOperations.getText(id(
                    "getBirthDateGregorianCalendar")), is(
                            "2014/10/10 11:11:11.111"));
            assertThat(webDriverOperations.getText(id("getBirthDateTimestamp")),
                    is("2015/10/10 11:11:11.111"));
            assertThat(webDriverOperations.getText(id("getBirthDateLocalDate")),
                    is("2013/10/10"));
            assertThat(webDriverOperations.getText(id("getBirthDateLocalTime")),
                    is("11:11:11.111"));
            assertThat(webDriverOperations.getText(id(
                    "getBirthDateLocalDateTime")), is(
                            "2013/10/10 11:11:11.111"));
            assertThat(webDriverOperations.getText(id(
                    "getBirthDateOffsetTime")), is("11:11:11.111+09:00"));
            assertThat(webDriverOperations.getText(id(
                    "getBirthDateOffsetDateTime")), is(
                            "2013/10/10 11:11:11.111+09:00"));
            assertThat(webDriverOperations.getText(id(
                    "getBirthDateZonedDateTime")), is(
                            "2013/10/10 11:11:11.111+08:00[Asia/Shanghai]"));
        }
    }

    /**
     * <ul>
     * <li>日付・時刻型フィールドから文字列型フィールドにコピーができること。</li>
     * </ul>
     */
    @Test
    public void testBNMP0304002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0304002"));
        }

        // コピー元(Source)の内容入力
        // java.sqlは@DateTimeFormatに対応していないため、デフォルトの日付フォーマットで設定している。
        {
            webDriverOperations.appendText(id("birthDate"),
                    "2013/10/10 11:11:11.111");
            webDriverOperations.appendText(id("birthDateSqlDate"),
                    "2013-10-10");
            webDriverOperations.appendText(id("birthDateSqlTime"), "11:11:11");
            webDriverOperations.appendText(id("birthDateCalendar"),
                    "2014/10/10 11:11:11.111");
            webDriverOperations.appendText(id("birthDateGregorianCalendar"),
                    "2014/10/10 11:11:11.111");
            webDriverOperations.appendText(id("birthDateTimestamp"),
                    "2015-10-10 11:11:11.111");
            webDriverOperations.appendText(id("birthDateLocalDate"),
                    "2013/10/10");
            webDriverOperations.appendText(id("birthDateLocalTime"),
                    "11:11:11.111");
            webDriverOperations.appendText(id("birthDateLocalDateTime"),
                    "2013/10/10 11:11:11.111");
            webDriverOperations.appendText(id("birthDateOffsetTime"),
                    "11:11:11.111+09:00");
            webDriverOperations.appendText(id("birthDateOffsetDateTime"),
                    "2013/10/10 11:11:11.111+09:00");
            webDriverOperations.appendText(id("birthDateZonedDateTime"),
                    "2013/10/10 11:11:11.111+09:00[Asia/Tokyo]");
            webDriverOperations.click(id("dateToString"));
        }

        // コピー先(Destination)の内容確認
        // コピー元Beanからコピー先Beanへコピーされること。
        {
            assertThat(webDriverOperations.getText(id("getBirthDate")), is(
                    "2013-10-10 11:11:11.111"));
            assertThat(webDriverOperations.getText(id("getBirthDateSqlDate")),
                    is("2013-10-10"));
            assertThat(webDriverOperations.getText(id("getBirthDateSqlTime")),
                    is("11:11:11"));
            assertThat(webDriverOperations.getText(id("getBirthDateCalendar")),
                    is("2014-10-10 11:11:11.111"));
            assertThat(webDriverOperations.getText(id(
                    "getBirthDateGregorianCalendar")), is(
                            "2014-10-10 11:11:11.111"));
            assertThat(webDriverOperations.getText(id("getBirthDateTimestamp")),
                    is("2015-10-10 11:11:11.111"));
            assertThat(webDriverOperations.getText(id("getBirthDateLocalDate")),
                    is("2013-10-10"));
            assertThat(webDriverOperations.getText(id("getBirthDateLocalTime")),
                    is("11:11:11.111"));
            assertThat(webDriverOperations.getText(id(
                    "getBirthDateLocalDateTime")), is(
                            "2013-10-10 11:11:11.111"));
            assertThat(webDriverOperations.getText(id(
                    "getBirthDateOffsetTime")), is("11:11:11.111+09:00"));
            assertThat(webDriverOperations.getText(id(
                    "getBirthDateOffsetDateTime")), is(
                            "2013-10-10 11:11:11.111+09:00"));
            assertThat(webDriverOperations.getText(id(
                    "getBirthDateZonedDateTime")), is(
                            "2013-10-10 11:11:11.111+09:00[Asia/Tokyo]"));
        }
    }

    /**
     * <ul>
     * <li>マッピング処理に失敗するとMappingExceptionがスローされること。</li>
     * </ul>
     */
    @Test
    public void testBNMP0305001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("bnmp0305001"));
        }

        // コピー元(Source)の内容入力
        {
            webDriverOperations.appendText(id("birthDate"), "20131231");
            webDriverOperations.click(id("mappingFailed"));
        }

        // MappingExceptionが発生すること。
        {
            // https://github.com/DozerMapper/dozer/issues/251
            // JDKのロケールが英語でない場合、MappingExceptionではなくIllegalArgumentExceptionとなってしまう。
            // Dozerのバグが解消されるまで、システムエラーもテスト成功とみなす。
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), anyOf(is(
                            "[e.sf.bnmp.0001] マッピング処理に失敗しました。"), is(
                                    "[e.sf.cmmn.9001] System error occurred!")));
        }
    }
}
