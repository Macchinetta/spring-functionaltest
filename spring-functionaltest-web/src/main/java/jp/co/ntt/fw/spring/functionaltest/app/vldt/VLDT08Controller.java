/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("vldt")
public class VLDT08Controller {

    @ModelAttribute
    public ConfirmFormUsingCompare setUpForm() {
        return new ConfirmFormUsingCompare();
    }

    @RequestMapping(value = "0801/001", method = RequestMethod.GET)
    public String handle01001() {
        return "vldt/correlationValidationByUsingCompareView";
    }

    @RequestMapping(value = "0801/001", method = RequestMethod.POST, params = "validate")
    public String handleValidate01001(@Validated ConfirmFormUsingCompare form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/correlationValidationByUsingCompareView";
        }
        return "redirect:/vldt/0801/001";
    }
}
