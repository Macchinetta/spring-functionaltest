/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.api.rest.member;

import java.lang.reflect.Type;

import org.joda.time.LocalDateTime;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

@ControllerAdvice
public class AddStartTimeRequestBodyAdvice extends RequestBodyAdviceAdapter {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {

        return MemberResourceWithAdvice.class.isAssignableFrom(methodParameter
                .getParameterType());

    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
            MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {

        MemberResourceWithAdvice resource = (MemberResourceWithAdvice) body;

        resource.setStartDateTime(new LocalDateTime(2015, 12, 16, 13, 10, 4, 5));

        return resource;
    }

}
