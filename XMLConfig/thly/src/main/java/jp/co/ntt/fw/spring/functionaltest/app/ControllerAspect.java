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
package jp.co.ntt.fw.spring.functionaltest.app;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;

public class ControllerAspect implements MethodInterceptor, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        // noop
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        Object[] args = invocation.getArguments();
        for (Object arg : args) {
            if (arg instanceof Model m) {
                m.addAttribute("xTrack", request.getAttribute("X-Track"));
            }
        }

        Object returnObj = invocation.proceed();
        if (returnObj == null) {
            return returnObj;
        }

        return returnObj;
    }
}
