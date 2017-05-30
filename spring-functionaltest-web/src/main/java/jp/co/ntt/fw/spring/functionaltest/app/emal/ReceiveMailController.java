/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.emal;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.MailMessage;
import jp.co.ntt.fw.spring.functionaltest.domain.service.emal.MailReceivingSharedService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("emal")
public class ReceiveMailController {

    @Inject
    MailReceivingSharedService mailReceivingSharedService;

    @ModelAttribute
    EmailReceivingForm setUpForm() {
        EmailReceivingForm form = new EmailReceivingForm();
        return form;
    }

    @RequestMapping(value = "receivemail", method = RequestMethod.GET)
    public String handle() {
        return "emal/receiveMail";
    }

    @RequestMapping(value = "receivemail", method = RequestMethod.POST)
    public String handleReceiveMail(Model model, EmailReceivingForm form) {
        mailReceivingSharedService.connect(form.getHost(), form.getPort(), form
                .getUser(), form.getPassword());
        MailMessage mail = mailReceivingSharedService.receive(form
                .getIdentifier(), 5);
        model.addAttribute("mail", mail);
        mailReceivingSharedService.close();
        return "emal/receiveMail";
    }

}
