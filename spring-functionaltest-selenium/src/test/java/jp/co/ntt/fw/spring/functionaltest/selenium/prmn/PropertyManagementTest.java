/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.prmn;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import java.io.IOException;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

import org.junit.Ignore;
import org.junit.Test;

public class PropertyManagementTest extends FunctionTestSupport {

    /**
     * <ul>
     * <li>自プロジェクトのプロパティの表示を確認する。</li>
     * </ul>
     */
    @Test
    public void testPRMN0101001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("prmn0101001"));

        // プロパティ取得値の確認
        assertThat(webDriverOperations.getText(id("propertyValue0001")),
                is("あああ"));

    }

    /**
     * <ul>
     * <li>他プロジェクトのプロパティの表示を確認する。</li>
     * </ul>
     */
    @Test
    public void testPRMN0101002() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("prmn0101002"));

        // プロパティ取得値の確認
        assertThat(webDriverOperations.getText(id("propertyValue0002")),
                is("20"));

    }

    /**
     * <ul>
     * <li>プロパティキー重複で無指定の場合、JVMのプロパティ値が優先して表示されることを確認する。</li>
     * <p>
     * <br>
     * <br>
     * Java起動時に<br>
     * -Di.sf.prmn.0007="70" -Di.sf.prmn.0008="70"<br>
     * <br>
     * OSの環境変数に<br>
     * i.sf.prmn.0007=80<br>
     * i.sf.prmn.0008=80<br>
     * i.sf.prmn.0010=140<br>
     * <br>
     * を設定しないと意味が無い試験項目で、実行するマシンすべてに設定するのが現実的では無いため、@Ignoreとする。<br>
     * </p>
     * </ul>
     */
    @Ignore("環境変数をOS、JVMに設定しないといけないため。詳細はJavaDoc参照")
    @Test
    public void testPRMN0102001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("prmn0102001"));

        // プロパティ取得値の確認
        assertThat(webDriverOperations.getText(id("propertyValue0007")),
                is("70"));

    }

    /**
     * <ul>
     * <li>プロパティキー重複で、local-override="true"指定の場合、アプリケーション定義のプロパティ値が優先して表示されることを確認する。</li>
     * <p>
     * <br>
     * <br>
     * Java起動時に<br>
     * -Di.sf.prmn.0007="70" -Di.sf.prmn.0008="70"<br>
     * <br>
     * OSの環境変数に<br>
     * i.sf.prmn.0007=80<br>
     * i.sf.prmn.0008=80<br>
     * i.sf.prmn.0010=140<br>
     * <br>
     * を設定しないと意味が無い試験項目だが、アプリ定義のみで正常終了するため残す。<br>
     * </p>
     * </ul>
     */
    @Test
    public void testPRMN0102002() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("prmn0102002"));

        // プロパティ取得値の確認
        assertThat(webDriverOperations.getText(id("propertyValue0008")),
                is("100"));

    }

    /**
     * <ul>
     * <li>プロパティキー重複で、local-override="true"指定の場合、アプリケーション定義のプロパティ値が優先して表示されることを確認する。</li>
     * <p>
     * <br>
     * <br>
     * Java起動時に<br>
     * -Di.sf.prmn.0007="70" -Di.sf.prmn.0008="70"<br>
     * <br>
     * OSの環境変数に<br>
     * i.sf.prmn.0007=80<br>
     * i.sf.prmn.0008=80<br>
     * i.sf.prmn.0010=140<br>
     * <br>
     * を設定しないと意味が無い試験項目だが、アプリ定義のみで正常終了するため残す。<br>
     * </p>
     * </ul>
     */
    @Test
    public void testPRMN0102003() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("prmn0102003"));

        // プロパティ取得値の確認
        assertThat(webDriverOperations.getText(id("propertyValue0009")),
                is("120"));

    }

    /**
     * <ul>
     * <li>プロパティキー重複で、local-override="true"指定の場合、アプリケーション定義のプロパティ値が優先して表示されることを確認する。</li>
     * <p>
     * <br>
     * <br>
     * Java起動時に<br>
     * -Di.sf.prmn.0007="70" -Di.sf.prmn.0008="70"<br>
     * <br>
     * OSの環境変数に<br>
     * i.sf.prmn.0007=80<br>
     * i.sf.prmn.0008=80<br>
     * i.sf.prmn.0010=140<br>
     * <br>
     * を設定しないと意味が無い試験項目で、実行するマシンすべてに設定するのが現実的では無いため、@Ignoreとする。<br>
     * </p>
     * </ul>
     */
    @Ignore("環境変数をOS、JVMに設定しないといけないため。詳細はJavaDoc参照")
    @Test
    public void testPRMN0103001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("prmn0103001"));

        // プロパティ取得値の確認
        assertThat(webDriverOperations.getText(id("propertyValue0010")),
                is("140"));

    }

    /**
     * <ul>
     * <li>bean定義ファイルのプロパティの表示を確認する。</li>
     * </ul>
     */
    @Test
    public void testPRMN0201001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("prmn0201001"));

        // プロパティ取得値の確認
        assertThat(webDriverOperations.getText(id("propertyValue0101")),
                is("1000"));

    }

    /**
     * <ul>
     * <li>bean定義ファイルのプロパティが存在しない場合のデフォルト値の表示を確認する。</li>
     * </ul>
     */
    @Test
    public void testPRMN0202001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("prmn0202001"));

        // プロパティ取得値の確認
        assertThat(webDriverOperations.getText(id("propertyValue0200")),
                is("3000"));

    }

    /**
     * <ul>
     * <li>controllerでの取得プロパティの表示を確認する。</li>
     * </ul>
     */
    @Test
    public void testPRMN0301001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("prmn0301001"));

        // プロパティ取得値の確認
        assertThat(webDriverOperations.getText(id("uploadTitle")),
                is("list of update file"));
        assertThat(webDriverOperations.getText(id("uploadDir")),
                is("file:/tmp/upload"));
        assertThat(webDriverOperations.getText(id("maxUpdateFileNum")),
                is("10"));

    }

    /**
     * <ul>
     * <li>controllerでの取得プロパティが存在しない場合のデフォルト値の表示を確認する。</li>
     * </ul>
     */
    @Test
    public void testPRMN0302001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("prmn0302001"));

        // プロパティ取得値の確認
        assertThat(webDriverOperations.getText(id("prmnDefaultValue")),
                is("4000"));

    }

    /**
     * <ul>
     * <li>暗号化されたプロパティの復号化後の表示を確認する。</li>
     * </ul>
     */
    @Test
    public void testPRMN0401001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("prmn0401001"));

        // プロパティ取得値の確認
        assertThat(webDriverOperations.getText(id("propertyValue0401001")),
                is("これは暗号化されたメッセージです"));

    }

}
