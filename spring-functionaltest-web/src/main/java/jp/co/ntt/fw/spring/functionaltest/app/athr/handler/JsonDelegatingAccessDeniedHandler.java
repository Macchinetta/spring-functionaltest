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
package jp.co.ntt.fw.spring.functionaltest.app.athr.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
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

    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (jsonRequestMatcher.matches(request)) {
            // エラー情報をJSON形式で応答

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            out.write(
                    "{\"errorResults\":[{\"message\":\"Forbidden error!\"}]}");
            out.flush();
            // ...
        } else {
            // エラーページをHTMLとして応答
            delegateHandler.handle(request, response, accessDeniedException);
        }
    }

}
