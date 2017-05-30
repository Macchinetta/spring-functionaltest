/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("vldt/0102/002")
public class VLDT0102002Controller {

    @ModelAttribute
    public NestedCollectionValidationForm setUpForm() {
        NestedCollectionValidationForm form = new NestedCollectionValidationForm();
        LinkedList<AddressForm> addresses = new LinkedList<AddressForm>();
        addresses.add(new AddressForm());
        form.setAddresses(addresses);
        return form;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/nestedCollectionValidationView";
    }

    @RequestMapping(method = RequestMethod.POST, params = "validate")
    public String handleValidate(
            @Validated NestedCollectionValidationForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/nestedCollectionValidationView";
        }
        return "redirect:/vldt/0102/002";
    }

    @RequestMapping(method = RequestMethod.POST, params = "add")
    public String handleAdd(NestedCollectionValidationForm form, Model model) {

        LinkedList<AddressForm> addresses = form.getAddresses();
        addresses.addFirst(new AddressForm());
        model.addAttribute("addresses", addresses);
        return "vldt/nestedCollectionValidationView";
    }

    @RequestMapping(method = RequestMethod.POST, params = "remove")
    public String handleRemove(NestedCollectionValidationForm form, Model model) {

        LinkedList<AddressForm> addresses = form.getAddresses();
        addresses.removeLast();
        model.addAttribute("addresses", addresses);
        return "vldt/nestedCollectionValidationView";
    }
}
