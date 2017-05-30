/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dmly;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class DeliveryOrderFormMethodArgumentResolver implements
                                                    HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return DeliveryOrderForm.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        DeliveryOrderForm params = new DeliveryOrderForm();
        params.setDeliveryNo(Integer.parseInt(webRequest
                .getParameter("deliveryNo")));
        params.setDeliveryTypeName(webRequest.getParameter("deliveryTypeName"));
        params.setSenderName(webRequest.getParameter("senderName"));
        params.setSenderAddress(webRequest.getParameter("senderAddress"));
        params.setRecieverName(webRequest.getParameter("recieverName"));
        params.setRecieverAddress(webRequest.getParameter("recieverAddress"));
        String acceptDatetime = webRequest.getParameter("acceptDatetime");
        if (!acceptDatetime.isEmpty()) {
            params.setAcceptDatetime(LocalDateTime.parse(acceptDatetime,
                    DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")));
        }
        String completionDatetime = webRequest
                .getParameter("completionDatetime");
        if (!completionDatetime.isEmpty()) {
            params.setCompletionDatetime(LocalDateTime.parse(
                    completionDatetime, DateTimeFormat
                            .forPattern("yyyy/MM/dd HH:mm:ss")));
        }
        params.setDeliveryDriver(webRequest.getParameter("deliveryDriver"));
        params.setDeliveryStatus(webRequest.getParameter("deliveryStatus"));
        return params;
    }

}
