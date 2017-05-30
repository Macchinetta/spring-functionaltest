/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.hlch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.test.annotation.IfProfileValue;

public class HealthCheckTest extends FunctionTestSupport {

    /**
     * <ul>
     * <li>ヘルスチェック成功時、スタータスコード200とOK.の3文字が返ってくることを確認する。web.xmlにtrim-directive-whitespacesタグを設定することで、余計な改行を削除している。</li>
     * <li>詳細は#798を参照。</li>
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
     * <li>ヘルスチェック成功時、スタータスコード200とOK.の3文字が返ってくることを確認する。JSPのpageディレクティブにtrimDirectiveWhitespacesを設定することで、余計な改行を削除している。</li>
     * <li>WebLogic環境では、pageディレクティブより前になにか記載があった時、trimDirectiveWhitespacesが効かない場合がある。そのため、WebLogic環境では下記のテストはスキップする。</li>
     * <li>詳細は#798を参照。</li>
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
                    "^\\[e.sf.cmn.9002\\] throw DB Error$");
            dbLogAssertOperations.assertNotContainsWarn(xTrack);
        }

    }

}
