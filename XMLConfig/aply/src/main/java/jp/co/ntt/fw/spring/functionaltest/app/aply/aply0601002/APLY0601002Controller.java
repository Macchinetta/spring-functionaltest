/*
 * Copyright(c) 2025 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.aply.aply0601002;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class APLY0601002Controller {

    @GetMapping(value = "{version}")
    public String handle01002(@PathVariable("version") Integer version, Model model,
            HttpServletRequest request) {

        // Mappingの設定の関係でControllerを分割できないためController内でjspとthymeleafを判定
        String requestURI = request.getRequestURI();
        if (requestURI.contains("jsp")) {
            return "jsp/aply/suffixMatchComplete";

        } else if (requestURI.contains("thymeleaf")) {
            return "thymeleaf/aply/suffixMatchComplete";

        } else {
            throw new IllegalArgumentException("Invalid request URI: " + requestURI);
        }

    }
}
