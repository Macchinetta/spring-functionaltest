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
package jp.co.ntt.fw.spring.functionaltest.app.xspr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("jsp")
public class XSPR_JSP_Controller {

    @GetMapping(value = "0101")
    public String output() {
        return "jsp/xspr/outputEscaping";
    }

    @PostMapping(value = "0101/output")
    public String output_InputData(
            @RequestParam("outputData") String outputData, Model model) {
        model.addAttribute("outputData", outputData);

        return "jsp/xspr/outputEscaping";
    }

    @GetMapping(value = "0201/001")
    public String javascriptXSSMeasures_0201_001(Model model) {

        model.addAttribute("warnCode", "';alert('XSS Attack!');aaa='message");

        return "jsp/xspr/javascriptOutput";
    }

    @GetMapping(value = "0201/002")
    public String javascriptXSSMeasures_0201_002(Model model) {

        model.addAttribute("warnCode", "';alert(\"XSS Attack!\");aaa='message");

        return "jsp/xspr/javascriptOutput";
    }

    @GetMapping(value = "0201/003")
    public String javascriptXSSMeasures_0201_003(Model model) {

        model.addAttribute("warnCode", "Spring Framework");

        return "jsp/xspr/javascriptOutput";
    }

    @GetMapping(value = "0301/001")
    public String eventHandlerXSSMeasures_0301_001(Model model) {

        model.addAttribute("warnCode", "\"); alert('XSS Attack!'); //");

        return "jsp/xspr/eventHandlerOutput";
    }

    @GetMapping(value = "0301/002")
    public String eventHandlerXSSMeasures_0301_002(Model model) {

        model.addAttribute("warnCode", "\"); alert(\"XSS Attack!\"); //");

        return "jsp/xspr/eventHandlerOutput";
    }

    @GetMapping(value = "0301/003")
    public String eventHandlerXSSMeasures_0301_003(Model model) {

        model.addAttribute("warnCode", "Spring Framework");

        return "jsp/xspr/eventHandlerOutput";
    }

}
