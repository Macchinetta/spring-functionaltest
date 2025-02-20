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
package jp.co.ntt.fw.spring.functionaltest.api.common.error;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

@RequestMapping("error")
@RestController
public class ApiErrorPageController {

    @Inject
    ApiErrorCreator apiErrorCreator;

    private final Map<HttpStatus, String> errorCodeMap = new HashMap<HttpStatus, String>();

    public ApiErrorPageController() {
        errorCodeMap.put(HttpStatus.NOT_FOUND, "e.ex.fw.5001");
    }

    @RequestMapping
    public ResponseEntity<ApiError> handleErrorPage(WebRequest request) {
        HttpStatus httpStatus = HttpStatus
                .valueOf((Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE,
                        RequestAttributes.SCOPE_REQUEST));
        String errorCode = errorCodeMap.get(httpStatus);
        ApiError apiError =
                apiErrorCreator.createApiError(request, errorCode, httpStatus.getReasonPhrase());
        return ResponseEntity.status(httpStatus).body(apiError);
    }

}
