/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("common/error")
public class ErrorPageController {
    @RequestMapping("accessDeniedError")
    public String accessDeniedError() {
        return "common/error/accessDeniedError";
    }

    @RequestMapping("ajaxError")
    public String ajaxError() {
        return "common/error/ajaxError";
    }

    @RequestMapping("beanMappingError")
    public String beanMappingError() {
        return "common/error/beanMappingError";
    }

    @RequestMapping("customSystemError")
    public String customSystemError() {
        return "common/error/customSystemError";
    }

    @RequestMapping("dataAccessError")
    public String dataAccessError() {
        return "common/error/dataAccessError";
    }

    @RequestMapping("exclusiveLockError")
    public String exclusiveLockError() {
        return "common/error/exclusiveLockError";
    }

    @RequestMapping("fileUploadError")
    public String fileUploadError() {
        return "common/error/fileUploadError";
    }

    @RequestMapping("forbiddenError")
    public String forbiddenError() {
        return "common/error/forbiddenError";
    }

    @RequestMapping("intentionalError")
    public String intentionalError() {
        return "common/error/intentionalError";
    }

    @RequestMapping("invalidCsrfTokenError")
    public String invalidCsrfTokenError() {
        return "common/error/invalidCsrfTokenError";
    }

    @RequestMapping("methodNotAllowedError")
    public String methodNotAllowedError() {
        return "common/error/methodNotAllowedError";
    }

    @RequestMapping("missingCsrfTokenError")
    public String missingCsrfTokenError() {
        return "common/error/missingCsrfTokenError";
    }

    @RequestMapping("notAcceptableError")
    public String notAcceptableError() {
        return "common/error/notAcceptableError";
    }

    @RequestMapping("operationError")
    public String operationError() {
        return "common/error/operationError";
    }

    @RequestMapping("requestError")
    public String requestError() {
        return "common/error/requestError";
    }

    @RequestMapping("resourceNotFoundError")
    public String resourceNotFoundError() {
        return "common/error/resourceNotFoundError";
    }

    @RequestMapping("systemError")
    public String systemError() {
        return "common/error/systemError";
    }

    @RequestMapping("transactionTokenError")
    public String transactionTokenError() {
        return "common/error/transactionTokenError";
    }

    @RequestMapping("unsupportedMediaTypeError")
    public String unsupportedMediaTypeError() {
        return "common/error/unsupportedMediaTypeError";
    }
}
