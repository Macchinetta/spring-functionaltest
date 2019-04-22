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
package jp.co.ntt.fw.spring.functionaltest.selenium;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.ws.webfault.ErrorBean;
import jp.co.ntt.fw.spring.functionaltest.ws.webfault.WebFaultType;

import org.springframework.beans.factory.annotation.Value;

public class SOAPTestSupport extends FunctionTestSupport {

    // FIXME 例外発生時、ステータスコードを取得できないため、暫定でフラグをもたせて切り替える仕組みとしている。
    // 対応方針が決定次第、当該箇所を修正すること。 [#640]
    @Value("${selenium.soap.httpStatusCode.enableToAssert}")
    boolean enableToAssertHttpStatusCode;

    /**
     * ログ出力されたHTTPステータスコードを検証する。
     * @param expected HTTPステータスコード
     */
    protected void assertHttpStatusCode(int expected) {
        if (enableToAssertHttpStatusCode) {
            dbLogAssertOperations.assertContainsByMessage(webDriverOperations
                    .getXTrack(), null, String.format("httpStatusCode=%s",
                            expected));
        }
    }

    /**
     * ログ出力されたWebFaultExceptionを検証する。
     * @param type　エラー種別
     * @param errors エラー詳細
     */
    protected void assertWebFault(WebFaultType type, ErrorBean... errors) {
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                webDriverOperations.getXTrack(), null, "e.sf.cmmn.9001",
                "jp.co.ntt.fw.spring.functionaltest.ws.webfault.WebFaultException");
        dbLogAssertOperations.assertContainsByMessage(webDriverOperations
                .getXTrack(), null, String.format("webFaultType=%s", type
                        .toString()));

        if (errors != null) {
            dbLogAssertOperations.assertContainsByMessage(webDriverOperations
                    .getXTrack(), null, String.format("errorBeanSize=%s",
                            errors.length));
            for (ErrorBean error : errors) {
                dbLogAssertOperations.assertContainsByMessage(
                        webDriverOperations.getXTrack(), null, String.format(
                                "code:message:path=%s:%s:%s", error.getCode(),
                                error.getMessage(), error.getPath()));
            }
        }
    }

    /**
     * ログ出力されたWebFaultExceptionを検証する。
     * @param type　エラー種別
     * @param errors エラー詳細
     */
    protected void assertWebFaultIsInErrorBeans(WebFaultType type,
            ErrorBean... errors) {
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                webDriverOperations.getXTrack(), null, "e.sf.cmmn.9001",
                "jp.co.ntt.fw.spring.functionaltest.ws.webfault.WebFaultException");
        dbLogAssertOperations.assertContainsByMessage(webDriverOperations
                .getXTrack(), null, String.format("webFaultType=%s", type
                        .toString()));

        if (errors != null) {
            dbLogAssertOperations.assertContainsByMessage(webDriverOperations
                    .getXTrack(), null, "errorBeanSize=1");
            List<String> list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(), null,
                    "code:message:path*");
            assertThat(list, hasSize(1));
            String logMessage = list.get(0);
            List<String> messageList = new ArrayList<String>();
            for (ErrorBean error : errors) {
                messageList.add(String.format("code:message:path=%s:%s:%s",
                        error.getCode(), error.getMessage(), error.getPath()));
            }
            assertThat(logMessage, isIn(messageList));
        }
    }
}
