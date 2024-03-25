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
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

/**
 * アクセス制御用インターセプタ<br>
 * 閉塞状況を監視と、ステータスコード：503時のリトライ処理を行う。
 */
public class AccessCtrlInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(
            AccessCtrlInterceptor.class);

    @Value("${rscl.retry.maxCount}")
    int retryMaxCount;

    @Value("${rscl.retry.retryWaitTimeCoefficient}")
    int retryWaitTimeCoefficient;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        // 閉塞検知ファイルを探索。
        File tmpDir = new File(System.getProperty("java.io.tmpdir"));
        File[] trgFileLst = tmpDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("RSCL1104") && name.endsWith(".block");
            }
        });

        ClientHttpResponse response = null;

        // 閉塞検知ファイルを検知した場合
        if (trgFileLst != null && trgFileLst.length != 0) {
            logger.info("AccessCtrlInterceptor Obstruction File Called!");
            throw new BusinessException(ResultMessages.error().add(
                    "e.rc.fw.8001"));
        } else {
            response = this.execute(request, body, execution);
        }

        logger.info("AccessCtrlInterceptor Called!");

        return response;
    }

    /**
     * <ul>
     * <li>ステータスコード503の場合にリトライを行う。</li>
     * </ul>
     * @param request
     * @param body
     * @param execution
     * @return
     * @throws IOException
     */
    private ClientHttpResponse execute(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        ClientHttpResponse res = null;
        int retryCount = 0;
        HttpStatusCode statusCode = null;

        while (true) {
            List<String> xRetryValue = new ArrayList<String>();
            xRetryValue.add(String.valueOf(retryCount));
            request.getHeaders().put("x-Retry", xRetryValue);
            res = execution.execute(request, body);
            statusCode = res.getStatusCode();

            // ステータスコードが503はリトライ
            if (statusCode.value() != HttpStatus.SERVICE_UNAVAILABLE.value()) {
                logger.info("StatusCode({}) ", statusCode);
                break;
            } else {
                if (retryCount == retryMaxCount) {
                    break;
                }
                retryCount++;

                logger.warn(
                        "An error ({}) occurred on the server. (The number of retries：{} Times)",
                        statusCode, retryCount);

                try {
                    Thread.sleep((long) (retryWaitTimeCoefficient
                            * retryCount));
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        logger.info("AccessCtrlInterceptor SuccessCount : {}", retryCount);

        return res;
    }

}
