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
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("thymeleaf/0102/002")
public class VLDT_Thymeleaf_0102002Controller {

    @ModelAttribute
    public NestedCollectionValidationForm setUpForm() {
        NestedCollectionValidationForm form = new NestedCollectionValidationForm();
        LinkedList<AddressForm> addresses = new LinkedList<AddressForm>();
        addresses.add(new AddressForm());
        form.setAddresses(addresses);
        return form;
    }

    @GetMapping
    public String handle() {
        return "thymeleaf/vldt/nestedCollectionValidationView";
    }

    @PostMapping(params = "validate")
    public String handleValidate(@Validated NestedCollectionValidationForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "thymeleaf/vldt/nestedCollectionValidationView";
        }
        return "redirect:/thymeleaf/0102/002";
    }

    @PostMapping(params = "add")
    public String handleAdd(NestedCollectionValidationForm form, Model model) {

        LinkedList<AddressForm> addresses = form.getAddresses();
        addresses.addFirst(new AddressForm());
        model.addAttribute("addresses", addresses);
        return "thymeleaf/vldt/nestedCollectionValidationView";
    }

    @PostMapping(params = "remove")
    public String handleRemove(NestedCollectionValidationForm form,
            Model model) {

        LinkedList<AddressForm> addresses = form.getAddresses();
        addresses.removeLast();
        model.addAttribute("addresses", addresses);
        return "thymeleaf/vldt/nestedCollectionValidationView";
    }
}
