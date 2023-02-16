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
package jp.co.ntt.fw.spring.functionaltest.app.dmly;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class DeliveryOrderListFormMethodArgumentResolver implements
                                                         HandlerMethodArgumentResolver {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("uuuu/MM/dd HH:mm:ss").withLocale(Locale.JAPANESE)
            .withResolverStyle(ResolverStyle.STRICT);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return DeliveryOrderListForm.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        DeliveryOrderListForm params = new DeliveryOrderListForm();
        params.setFromAcceptDatetime(LocalDateTime.parse(webRequest
                .getParameter("fromAcceptDatetime"), DATE_TIME_FORMATTER));
        params.setToAcceptDatetime(LocalDateTime.parse(webRequest.getParameter(
                "toAcceptDatetime"), DATE_TIME_FORMATTER));
        params.setUpdateCompletionDatetime(LocalDateTime.parse(webRequest
                .getParameter("updateCompletionDatetime"),
                DATE_TIME_FORMATTER));
        return params;
    }

}
