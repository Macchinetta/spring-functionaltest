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

import org.springframework.http.HttpStatus;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ThymeleafCommonControllerAdvice {

    @ModelAttribute
    public void setRequestInfo(Model model, HttpServletRequest request) {
        model.addAttribute("xTrack", (String) request.getAttribute("X-Track"));
        model.addAttribute("contextPath", request.getContextPath());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleIllegalStateException(IllegalStateException e) {
        if (e.getMessage().contains("Expected session attribute")) {
            ExtendedModelMap modelMap = new ExtendedModelMap();

            modelMap.addAttribute(e.getMessage());
            return new ModelAndView("thymeleaf/common/error/operationError", modelMap);
        }
        throw e;
    }
}
