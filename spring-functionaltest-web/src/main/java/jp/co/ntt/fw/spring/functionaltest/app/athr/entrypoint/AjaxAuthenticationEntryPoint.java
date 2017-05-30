/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.athr.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.AuthenticationEntryPoint;

public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger log = LoggerFactory
            .getLogger(AjaxAuthenticationEntryPoint.class);

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {

        log.info("Execute AjaxAuthenticationEntryPoint. RequestetURI is {} ",
                request.getRequestURI());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
