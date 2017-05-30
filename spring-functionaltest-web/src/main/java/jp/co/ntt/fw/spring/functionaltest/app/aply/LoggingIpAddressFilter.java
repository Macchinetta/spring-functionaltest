/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class LoggingIpAddressFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory
            .getLogger(LoggingIpAddressFilter.class);

    private static final String ATTRIBUTE_NAME = "X-Forwarded-For";

    protected final void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String remoteIp = request.getHeader(ATTRIBUTE_NAME);
        if (remoteIp == null) {
            remoteIp = request.getRemoteAddr();
        }

        logger.info("Client IP Adress:{}", remoteIp);

        filterChain.doFilter(request, response);
    }
}
