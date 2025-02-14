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
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("common/error")
public class ErrorPageController {
    @GetMapping("accessDeniedError")
    public String accessDeniedError() {
        return "common/error/accessDeniedError";
    }

    @GetMapping("ajaxError")
    public String ajaxError() {
        return "common/error/ajaxError";
    }

    @GetMapping("customSystemError")
    public String customSystemError() {
        return "common/error/customSystemError";
    }

    @GetMapping("dataAccessError")
    public String dataAccessError() {
        return "common/error/dataAccessError";
    }

    @GetMapping("exclusiveLockError")
    public String exclusiveLockError() {
        return "common/error/exclusiveLockError";
    }

    @PostMapping("fileUploadError")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String fileUploadError() {
        return "common/error/fileUploadError";
    }

    @RequestMapping(value = "forbiddenError", method = {RequestMethod.GET, RequestMethod.POST})
    public String forbiddenError() {
        return "common/error/forbiddenError";
    }

    @GetMapping("intentionalError")
    public String intentionalError() {
        return "common/error/intentionalError";
    }

    @PostMapping("invalidCsrfTokenError")
    public String invalidCsrfTokenError() {
        return "common/error/invalidCsrfTokenError";
    }

    @GetMapping("methodNotAllowedError")
    public String methodNotAllowedError() {
        return "common/error/methodNotAllowedError";
    }

    @PostMapping("missingCsrfTokenError")
    public String missingCsrfTokenError() {
        return "common/error/missingCsrfTokenError";
    }

    @GetMapping("notAcceptableError")
    public String notAcceptableError() {
        return "common/error/notAcceptableError";
    }

    @GetMapping("operationError")
    public String operationError() {
        return "common/error/operationError";
    }

    @RequestMapping(value = "requestError", method = {RequestMethod.GET, RequestMethod.POST})
    public String requestError() {
        return "common/error/requestError";
    }

    @GetMapping("resourceNotFoundError")
    public String resourceNotFoundError() {
        return "common/error/resourceNotFoundError";
    }

    @GetMapping("systemError")
    public String systemError() {
        return "common/error/systemError";
    }

    @GetMapping("transactionTokenError")
    public String transactionTokenError() {
        return "common/error/transactionTokenError";
    }

    @GetMapping("unsupportedMediaTypeError")
    public String unsupportedMediaTypeError() {
        return "common/error/unsupportedMediaTypeError";
    }

    @GetMapping("serviceUnavailableError")
    public String serviceUnavailableError() {
        return "common/error/serviceUnavailableError";
    }
}
