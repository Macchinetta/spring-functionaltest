/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.terasoluna.gfw.web.util.RequestUtils;

public class AjaxSupportAccessDeniedHandlerImpl extends
                                                AccessDeniedHandlerImpl {

    @Inject
    MessageSource messageSource;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (!response.isCommitted()) {

            if (RequestUtils.isAjaxRequest(request)) {
                String jsonObject = "{\"errorResults\":[{\"message\":\""
                        + messageSource.getMessage("e.sf.cspr.0001", null,
                                request.getLocale()) + "\"}]}";

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                PrintWriter out = response.getWriter();
                out.print(jsonObject);
                out.flush();
                return;
            }
        }

        super.handle(request, response, accessDeniedException);
    }

}
