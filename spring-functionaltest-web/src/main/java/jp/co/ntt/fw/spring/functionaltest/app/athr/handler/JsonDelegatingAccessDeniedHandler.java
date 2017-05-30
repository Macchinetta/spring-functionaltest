/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.athr.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class JsonDelegatingAccessDeniedHandler implements AccessDeniedHandler {

    private final RequestMatcher jsonRequestMatcher;

    private final AccessDeniedHandler delegateHandler;

    public JsonDelegatingAccessDeniedHandler(RequestMatcher jsonRequestMatcher,
            AccessDeniedHandler delegateHandler) {
        this.jsonRequestMatcher = jsonRequestMatcher;
        this.delegateHandler = delegateHandler;
    }

    public void handle(HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (jsonRequestMatcher.matches(request)) {
            // エラー情報をJSON形式で応答

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            out.write("{\"errorResults\":[{\"message\":\"Forbidden error!\"}]}");
            out.flush();
            // ...
        } else {
            // エラーページをHTMLとして応答
            delegateHandler.handle(request, response, accessDeniedException);
        }
    }

}
