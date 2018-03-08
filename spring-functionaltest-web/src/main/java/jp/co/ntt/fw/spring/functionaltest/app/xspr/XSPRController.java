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
package jp.co.ntt.fw.spring.functionaltest.app.xspr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("xspr")
public class XSPRController {

    @RequestMapping
    public String index() {
        return "xspr/index";
    }

    @RequestMapping(value = "0101", method = RequestMethod.GET)
    public String output() {
        return "xspr/outputEscaping";
    }

    @RequestMapping(value = "0101/output", method = RequestMethod.POST)
    public String output_InputData(
            @RequestParam("outputData") String outputData, Model model) {
        model.addAttribute("outputData", outputData);

        return "xspr/outputEscaping";
    }

    @RequestMapping(value = "0201/001", method = RequestMethod.GET)
    public String javascriptXSSMeasures_0201_001(Model model) {

        model.addAttribute("warnCode", "';alert('XSS Attack!');aaa='message");

        return "xspr/javascriptOutput";
    }

    @RequestMapping(value = "0201/002", method = RequestMethod.GET)
    public String javascriptXSSMeasures_0201_002(Model model) {

        model.addAttribute("warnCode", "';alert(\"XSS Attack!\");aaa='message");

        return "xspr/javascriptOutput";
    }

    @RequestMapping(value = "0201/003", method = RequestMethod.GET)
    public String javascriptXSSMeasures_0201_003(Model model) {

        model.addAttribute("warnCode", "Spring Framework");

        return "xspr/javascriptOutput";
    }

    @RequestMapping(value = "0301/001", method = RequestMethod.GET)
    public String eventHandlerXSSMeasures_0301_001(Model model) {

        model.addAttribute("warnCode", "'); alert('XSS Attack!'); //");

        return "xspr/eventHandlerOutput";
    }

    @RequestMapping(value = "0301/002", method = RequestMethod.GET)
    public String eventHandlerXSSMeasures_0301_002(Model model) {

        model.addAttribute("warnCode", "'); alert(\"XSS Attack!\"); //");

        return "xspr/eventHandlerOutput";
    }

    @RequestMapping(value = "0301/003", method = RequestMethod.GET)
    public String eventHandlerXSSMeasures_0301_003(Model model) {

        model.addAttribute("warnCode", "Spring Framework");

        return "xspr/eventHandlerOutput";
    }

}
