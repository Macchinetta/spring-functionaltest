/*
 * Copyright(c) 2014 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.service.vldt.CmdExecService;

@RequestMapping("vldt")
@Controller
public class VLDT07Controller {

    @Inject
    CmdExecService cmdExecService;

    @ModelAttribute
    public OsCommandInjectionForm setUpOsCommandInjectionForm() {
        return new OsCommandInjectionForm();
    }

    @RequestMapping(value = "0701/001", method = RequestMethod.GET)
    public String handle() {
        return "vldt/osCommandInjectionForm";
    }

    @RequestMapping(value = "0701/001", method = RequestMethod.POST)
    public String handleValidate(@Validated OsCommandInjectionForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return handle();
        }

        cmdExecService.exec(form.getCmdStr(), form.getArg());

        return "redirect:/vldt/0701/001";
    }

}
