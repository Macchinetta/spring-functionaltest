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
package jp.co.ntt.fw.spring.functionaltest.app.prmn;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;

@RequestMapping("jsp")
@Controller
public class PRMN_JSP_01Controller {

    @Inject
    PrmnDefines prmnDefines;

    @GetMapping(value = "0101/001")
    public String handle0101001(Model model) {
        model.addAttribute("prmnDefines", prmnDefines);
        return "jsp/prmn/ownProjectPropertiesDsp";
    }

    @GetMapping(value = "0101/002")
    public String handle0101002(Model model) {
        model.addAttribute("prmnDefines", prmnDefines);
        return "jsp/prmn/otherProjectPropertiesDsp";
    }

    @GetMapping(value = "0102/001")
    public String handle0102001(Model model) {
        model.addAttribute("prmnDefines", prmnDefines);
        return "jsp/prmn/orderJvmEnvValDsp";
    }

    @GetMapping(value = "0102/002")
    public String handle0102002(Model model) {
        model.addAttribute("prmnDefines", prmnDefines);
        return "jsp/prmn/orderAppEnvValDsp";
    }

    @GetMapping(value = "0102/003")
    public String handle0102003(Model model) {
        model.addAttribute("prmnDefines", prmnDefines);
        return "jsp/prmn/orderOrderEnvValDsp";
    }

    @GetMapping(value = "0103/001")
    public String handle0103001(Model model) {
        model.addAttribute("prmnDefines", prmnDefines);
        return "jsp/prmn/orderOsEnvValThanAppDsp";
    }

}
