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
package jp.co.ntt.fw.spring.functionaltest.selenium.hlch;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class HealthCheckTest extends FunctionTestSupport {

    /**
     * <ul>
     * <li>ヘルスチェック成功時、スタータスコード200とOK.の3文字が返ってくることを確認する。</li>
     * <li>JSP版はweb.xmlにtrim-directive-whitespacesタグを設定することで、余計な改行を削除している。</li>
     * </ul>
     */
    @Test
    public void testHLCH0101001() throws IOException {
        String url = applicationContextUrl + "/hlch/0101/001";
        HttpGet request = new HttpGet(url);
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpResponse response = client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();
            String body = EntityUtils.toString(response.getEntity());

            assertThat(statusCode, is(200));
            assertThat(body, is("OK."));
        }
    }

    /**
     * <ul>
     * <li>ヘルスチェック成功時、スタータスコード200とOK.の3文字が返ってくることを確認する。</li>
     * <li>JSP版はpageディレクティブにtrimDirectiveWhitespacesを設定したJSPを成功画面として出力する。</li>
     * <li>Weblogic環境ではスキップする。詳細は#798を参照。</li>
     * </ul>
     */
    @IfProfileValue(name = "test.environment.weblogic", value = "false")
    @Test
    public void testHLCH0101002() throws IOException {
        String url = applicationContextUrl + "/hlch/0101/002";
        HttpGet request = new HttpGet(url);
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpResponse response = client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();
            String body = EntityUtils.toString(response.getEntity());

            assertThat(statusCode, is(200));
            assertThat(body, is("OK."));
        }
    }

    /**
     * <ul>
     * <li>ヘルスチェック失敗時、スタータスコード500が返ってくることを確認する。合わせてログも確認する。</li>
     * </ul>
     */
    @Test
    public void testHLCH0201001() throws IOException, InterruptedException {
        String url = applicationContextUrl + "/hlch/0201/001";
        HttpGet request = new HttpGet(url);
        String xTrack = null;
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpResponse response = client.execute(request);
            Header[] headervals = response.getHeaders("X-Track");
            for (Header header : headervals) {
                xTrack = header.getValue();
            }
            int statusCode = response.getStatusLine().getStatusCode();
            assertThat(statusCode, is(500));
        }
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(xTrack,
                    "^org.terasoluna.gfw.common.exception.ExceptionLogger$",
                    "^\\[e.sf.cmmn.9009\\] throw DB Error$");
            dbLogAssertOperations.assertNotContainsWarn(xTrack);
        }

    }

}
