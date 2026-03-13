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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mapping.MappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.terasoluna.gfw.common.message.ResultMessage;

import jakarta.servlet.http.HttpServletResponse;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.ApiError;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@RestController
@RequestMapping(value = "/api/v1/exception")
public class ExceptionAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAPIController.class);

    /**
     * <ul>
     * <li>返却するステータスコード変更する。</li>
     * 特定のステータスコード返却確認用。
     * </ul>
     * 
     * @param id
     * @return
     */
    @GetMapping(value = "{status}")
    public User exception(@PathVariable("status") int status, HttpServletResponse res) {
        LOGGER.info("Call API:exception:{}", status);
        res.setStatus(status);

        if (status >= 400 && status < 600) {
            return null;
        }
        return new User("exceptionTest", 20);
    }

    /**
     * <ul>
     * <li>503を返却するが、成功レコードの場合Userを返却。</li> <br>
     * リトライ処理確認用。
     * </ul>
     * 
     * @param retryCount
     * @return
     */
    @GetMapping(value = "retry/{successCount}")
    public User retry(@RequestHeader("x-Retry") int retryCount, @PathVariable("successCount") int successCount) {
        LOGGER.info("Call API:retry:{}", retryCount);

        User user = null;

        // 指定した成功レコード以外の場合は例外をスローする
        if (retryCount != successCount) {
            throw new MappingException(ResultMessage.fromCode("e.sf.rc.9002").getText());
        }

        user = new User();
        user.setName("test");
        user.setAge(20);

        return user;
    }

    /**
     * リトライ処理確認でスローするMappingException用のハンドラ
     * 
     * @param ex
     * @return
     */
    @ExceptionHandler({ MappingException.class })
    public ResponseEntity<ApiError> mappingExceptionHandler(Exception ex) {
        ApiError err = new ApiError(ResultMessage.fromCode("e.sf.rc.9002").getText());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).headers(new HttpHeaders())
                .body(err);
    }
}
