/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.serverinfo;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.annotation.Nullable;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetServerInfoInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {

        if (modelAndView != null) {
            ServletContext context = request.getServletContext();
            String info = context.getServerInfo();
            modelAndView.addObject("serverInfo", info);
        }
    }
}
