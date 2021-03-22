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
package jp.co.ntt.fw.spring.functionaltest.app.stpr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("stpr")
@Controller
public class STPR03Controller {

    @ModelAttribute
    public StringProcessing03Form setStringProcessing03Form() {
        return new StringProcessing03Form();
    }

    @RequestMapping(value = "0301/001", method = RequestMethod.GET)
    public String handle0301001() {
        return "stpr/lengthConsideringSurrogateInput";
    }

    @RequestMapping(value = "0301/001/lengthConsideringSurrogate", method = RequestMethod.POST)
    public String handle0301001LengthConsideringSurrogate(Model model,
            StringProcessing03Form form) {

        String targetValue = form.getTargetValue();
        int targetValueLength = targetValue.length();
        model.addAttribute("resultLength", targetValueLength);
        model.addAttribute("resultLengthConsideringSurrogate", targetValue
                .codePointCount(0, targetValueLength));

        return "stpr/lengthConsideringSurrogateResult";
    }

    @RequestMapping(value = "0302/001", method = RequestMethod.GET)
    public String handle0302001() {
        return "stpr/substringConsideringSurrogateInput";
    }

    @RequestMapping(value = "0302/001/substringConsideringSurrogate", method = RequestMethod.POST)
    public String handle0302001SubstringConsideringSurrogate(Model model,
            @Validated StringProcessing03Form form, BindingResult result) {

        if (result.hasErrors()) {
            return handle0302001();
        }
        String targetValue = form.getTargetValue();
        int startIndex = form.getStartIndex();
        int endIndex = form.getEndIndex();

        model.addAttribute("resultString", targetValue.substring(startIndex,
                endIndex));

        model.addAttribute("resultStringConsideringSurrogate", targetValue
                .substring(targetValue.offsetByCodePoints(0, startIndex),
                        targetValue.offsetByCodePoints(0, endIndex)));

        return "stpr/substringConsideringSurrogateResult";
    }

    @RequestMapping(value = "0303/001", method = RequestMethod.GET)
    public String handle0303001() {
        return "stpr/splitInput";
    }

    @RequestMapping(value = "0303/001/split", method = RequestMethod.POST)
    public String handle0303001Split(Model model, StringProcessing03Form form) {

        String str = form.getTargetValue();

        String[] subStrings = str.split(form.getSplitRegex());
        model.addAttribute("resultString", subStrings);

        return "stpr/splitResult";
    }

}
