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
package jp.co.ntt.fw.spring.functionaltest.app.dbsp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("dbsp")
public class DBSP02Controller {

    private static final Logger logger = LoggerFactory.getLogger(
            DBSP02Controller.class);

    @ModelAttribute
    public UserCreateForm setUpForm() {
        return new UserCreateForm();
    }

    @RequestMapping(value = "0201/001/create", method = RequestMethod.GET, params = "form")
    public String createForm(UserCreateForm userCreateForm,
            BindingResult bindingResult) {
        return "dbsp/createForm";
    }

    @RequestMapping(value = "0201/001/create", method = RequestMethod.POST, params = "confirm")
    public String createConfirm(@Validated UserCreateForm userCreateForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "dbsp/createForm";
        }
        return "dbsp/createConfirm";
    }

    @RequestMapping(value = "0201/001/create", method = RequestMethod.POST)
    public String create(@Validated UserCreateForm userCreateForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "dbsp/createForm";
        }
        File file = new File(userCreateForm.getFilePath() + "\\userData.txt");
        String userDataText = "";
        try (FileWriter filewriter = new FileWriter(file)) {
            filewriter.write(userDataText + userCreateForm.getFirstName() + " "
                    + userCreateForm.getLastName() + "\n");
        } catch (IOException e) {
            logger.warn("I/O Error", e);
        }

        String output = "result register...";
        redirectAttributes.addFlashAttribute("output", output);
        return "redirect:/dbsp/0201/001/create?complete";
    }

    @RequestMapping(value = "0201/001/create", method = RequestMethod.GET, params = "complete")
    public String createComplete() {
        return "dbsp/createComplete";
    }
}
