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
package jp.co.ntt.fw.spring.functionaltest.selenium.encr;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.openqa.selenium.By.*;

import java.util.Base64;

import org.junit.Test;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class EncryptionTest extends FunctionTestSupport {

    /**
     * <ul>
     * <li>TextEncryptorを使用してテキストの暗号化、復号ができることを確認。</li>
     * </ul>
     */
    @Test
    public void testENCR0101001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("encr0101001"));
        }

        // 暗号化情報入力
        {
            webDriverOperations.overrideText(id("rawText"), "John");
            webDriverOperations.click(id("encrypt"));
        }

        // 暗復号結果確認
        {
            assertThat(webDriverOperations.getText(id("decryptedText")), is(
                    "John"));
        }

    }

    /**
     * <ul>
     * <li>BytesEncryptorを使用してバイト配列の暗号化、復号ができることを確認。</li>
     * </ul>
     */
    @Test
    public void testENCR0103001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("encr0103001"));
        }

        // 暗号化情報入力
        {
            webDriverOperations.overrideText(id("rawText"), "John");
            webDriverOperations.click(id("encrypt"));
        }

        // 暗復号結果確認
        {
            assertThat(webDriverOperations.getText(id("decryptedText")), is(
                    "John"));
        }

    }

    /**
     * <ul>
     * <li>AES with GCMを使用してテキストの暗号化、復号ができることを確認。</li>
     * </ul>
     */
    @Test
    public void testENCR0104001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("encr0104001"));
        }

        // 暗号化情報入力
        {
            webDriverOperations.overrideText(id("rawText"), "John");
            webDriverOperations.click(id("encrypt"));
        }

        // 暗復号結果確認
        {
            assertThat(webDriverOperations.getText(id("decryptedText")), is(
                    "John"));
        }

    }

    /**
     * <ul>
     * <li>AES with GCMを使用してバイト配列の暗号化、復号ができることを確認。</li>
     * </ul>
     */
    @Test
    public void testENCR0105001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("encr0105001"));
        }

        // 暗号化情報入力
        {
            webDriverOperations.overrideText(id("rawText"), "John");
            webDriverOperations.click(id("encrypt"));
        }

        // 暗復号結果確認
        {
            assertThat(webDriverOperations.getText(id("decryptedText")), is(
                    "John"));
        }

    }

    /**
     * <ul>
     * <li>ByteKeyGeneratorを使用してバイト配列型の疑似乱数(鍵)が生成できることを確認。</li>
     * </ul>
     */
    @Test
    public void testENCR0201001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("encr0201001"));
        }

        // 鍵長入力
        {
            webDriverOperations.overrideText(id("keyLength"), "16");
            webDriverOperations.click(id("generate"));
        }

        // 疑似乱数(鍵)生成結果確認
        {
            assertThat(Base64.getDecoder().decode(webDriverOperations.getText(
                    id("generatedKey")).getBytes()).length, is(16));
        }

    }

    /**
     * <ul>
     * <li>sharedメソッドを使用して同一の鍵を返すByteKeyGeneratorが生成されることを確認。</li>
     * </ul>
     */
    @Test
    public void testENCR0202001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("encr0202001"));
        }

        // 鍵長入力
        {
            webDriverOperations.overrideText(id("keyLength"), "16");
            webDriverOperations.click(id("generate"));
        }

        // 疑似乱数(鍵)生成結果確認
        {
            assertThat(Base64.getDecoder().decode(webDriverOperations.getText(
                    id("generatedKey")).getBytes()).length, is(16));
            assertEquals(webDriverOperations.getText(id("generatedKey")),
                    webDriverOperations.getText(id("generatedKey2")));
        }

    }

    /**
     * <ul>
     * <li>StringKeyGeneratorを使用してテキスト型の疑似乱数(鍵)が生成できることを確認。</li>
     * </ul>
     */
    @Test
    public void testENCR0203001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("encr0203001"));
        }

        {
            webDriverOperations.click(id("generate"));
        }

        // 疑似乱数(鍵)生成結果確認
        {
            assertThat(webDriverOperations.getText(id("generatedKey")).length(),
                    is(8 * 2));
        }

    }

    /**
     * <ul>
     * <li>JCAで生成したキーペアを使用してJCAで暗号化、JCAで復号ができることを確認。</li>
     * </ul>
     */
    @Test
    public void testENCR0301001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("encr0301001"));
        }

        // 暗号化情報入力
        {
            webDriverOperations.overrideText(id("rawText"), "John");
            webDriverOperations.click(id("encrypt"));
        }

        // 暗復号結果確認
        {
            assertThat(webDriverOperations.getText(id("decryptedText")), is(
                    "John"));
        }

    }

    /**
     * <ul>
     * <li>OpenSSLで生成したキーペアを使用してJCAで暗号化、OpenSSLで復号できることを確認。</li>
     * </ul>
     */
    @Test
    public void testENCR0302001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("encr0302001"));
        }

        // 暗号化情報入力
        {
            webDriverOperations.overrideText(id("rawText"), "John");
            webDriverOperations.click(id("encrypt"));
        }

        // 暗復号結果確認
        {
            assertThat(webDriverOperations.getText(id("decryptedText")), is(
                    "John"));
        }

    }

    /**
     * <ul>
     * <li>OpenSSLで生成したキーペアを使用してOpenSSLで暗号化、JCAで復号できることを確認。</li>
     * </ul>
     */
    @Test
    public void testENCR0303001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("encr0303001"));
        }

        // 暗号化情報入力
        {
            webDriverOperations.overrideText(id("rawText"), "John");
            webDriverOperations.click(id("encrypt"));
        }

        // 暗復号結果確認
        {
            assertThat(webDriverOperations.getText(id("decryptedText")), is(
                    "John"));
        }

    }

    /**
     * <ul>
     * <li>ハイブリッド暗号化方式を使用してテキストの暗号化、復号ができることを確認。</li>
     * </ul>
     */
    @Test
    public void testENCR0401001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("encr0401001"));
        }

        // 暗号化情報入力
        {
            webDriverOperations.overrideText(id("rawText"), "John");
            webDriverOperations.click(id("encrypt"));
        }

        // 暗復号結果確認
        {
            assertThat(webDriverOperations.getText(id("decryptedText")), is(
                    "John"));
        }

    }

}
