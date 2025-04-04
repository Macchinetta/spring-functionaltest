/*
 * Copyright(c) 2025 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.selenium.soap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.in;
import java.util.ArrayList;
import java.util.List;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;
import jp.co.ntt.fw.spring.functionaltest.ws.webfault.ErrorBean;
import jp.co.ntt.fw.spring.functionaltest.ws.webfault.WebFaultType;

public abstract class SOAPTestSupport extends FunctionTestSupport {

    /**
     * ログ出力されたHTTPステータスコードを検証する。
     * 
     * @param expected HTTPステータスコード
     */
    protected void assertHttpStatusCode(Integer expected) {
        dbLogAssertOperations.assertContainsByMessage(webDriverOperations.getXTrack(), null,
                String.format("httpStatusCode=%s", expected));
    }

    /**
     * ログ出力されたHTTPレスポンスヘッダを検証する。
     * 
     * @param expected レスポンスヘッダ文字列
     */
    protected void assertHttpRespnseHeader(String expected) {
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), null,
                String.format("RespnseHeader=%s", expected));
    }

    /**
     * ログ出力されたWebFaultExceptionを検証する。
     * 
     * @param type エラー種別
     * @param errors エラー詳細
     */
    protected void assertWebFault(WebFaultType type, ErrorBean... errors) {
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(webDriverOperations.getXTrack(),
                null, "e.sf.cmmn.9001",
                "jp.co.ntt.fw.spring.functionaltest.ws.webfault.WebFaultException");
        dbLogAssertOperations.assertContainsByMessage(webDriverOperations.getXTrack(), null,
                String.format("webFaultType=%s", type.toString()));

        if (errors != null) {
            dbLogAssertOperations.assertContainsByMessage(webDriverOperations.getXTrack(), null,
                    String.format("errorBeanSize=%s", errors.length));
            for (ErrorBean error : errors) {
                dbLogAssertOperations.assertContainsByMessage(webDriverOperations.getXTrack(), null,
                        String.format("code:message:path=%s:%s:%s", error.getCode(),
                                error.getMessage(), error.getPath()));
            }
        }
    }

    /**
     * ログ出力されたWebFaultExceptionを検証する。
     * 
     * @param type エラー種別
     * @param errors エラー詳細
     */
    protected void assertWebFaultIsInErrorBeans(WebFaultType type, ErrorBean... errors) {
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(webDriverOperations.getXTrack(),
                null, "e.sf.cmmn.9001",
                "jp.co.ntt.fw.spring.functionaltest.ws.webfault.WebFaultException");
        dbLogAssertOperations.assertContainsByMessage(webDriverOperations.getXTrack(), null,
                String.format("webFaultType=%s", type.toString()));

        if (errors != null) {
            dbLogAssertOperations.assertContainsByMessage(webDriverOperations.getXTrack(), null,
                    "errorBeanSize=1");
            List<String> list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(), null, "code:message:path*");
            assertThat(list, hasSize(1));
            String logMessage = list.get(0);
            List<String> messageList = new ArrayList<String>();
            for (ErrorBean error : errors) {
                messageList.add(String.format("code:message:path=%s:%s:%s", error.getCode(),
                        error.getMessage(), error.getPath()));
            }
            assertThat(logMessage, is(in(messageList)));
        }
    }
}
