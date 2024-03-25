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
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.inject.Inject;

@Controller
public class EXHN_JSP_08Controller {

    @Value("${app.base.web.path}")
    private String baseWeb;

    @Inject
    MessageSource messageSource;

    @GetMapping("001")
    public String handle0801001() {
        // 意図的にRuntimeExceptionをthrowしているためSonarQube指摘には未対応としています。
        throw new RuntimeException(messageSource.getMessage("e.sf.exhn.8030",
                null, null, Locale.getDefault()));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public String handleException(Model model, RuntimeException e) {
        model.addAttribute("baseWeb", this.baseWeb);
        return "index";
    }
}
