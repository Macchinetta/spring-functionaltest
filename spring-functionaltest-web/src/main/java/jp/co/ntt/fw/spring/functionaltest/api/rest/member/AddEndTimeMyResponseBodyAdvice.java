/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.api.rest.member;

import org.joda.time.LocalDateTime;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class AddEndTimeMyResponseBodyAdvice
                                           implements
                                           ResponseBodyAdvice<MemberResourceWithAdvice> {

    @Override
    public boolean supports(MethodParameter returnType,
            Class<? extends HttpMessageConverter<?>> converterType) {

        return MemberResourceWithAdvice.class.isAssignableFrom(returnType
                .getParameterType());
    }

    @Override
    public MemberResourceWithAdvice beforeBodyWrite(
            MemberResourceWithAdvice body, MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request, ServerHttpResponse response) {

        body.setEndDateTime(new LocalDateTime(2015, 12, 16, 14, 10, 4, 5));

        return body;
    }

}
