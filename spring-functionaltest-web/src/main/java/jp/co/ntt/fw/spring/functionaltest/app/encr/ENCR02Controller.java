/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.encr;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.encr.BytesKeys;
import jp.co.ntt.fw.spring.functionaltest.domain.service.encr.EncryptionDataService;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("encr")
public class ENCR02Controller {

    @Inject
    EncryptionDataService encryptionDataService;

    @RequestMapping(value = "/0201/001", method = RequestMethod.GET)
    public String handle0201001(Model model, EncryptionDataForm form) {
        return "encr/generateBytesKey";
    }

    @RequestMapping(value = "/0202/001", method = RequestMethod.GET)
    public String handle0202001(Model model, EncryptionDataForm form) {
        return "encr/generateSameBytesKey";
    }

    @RequestMapping(value = "/0203/001", method = RequestMethod.GET)
    public String handle0203001(Model model, EncryptionDataForm form) {
        return "encr/generateStringKey";
    }

    @RequestMapping(value = "0201/001/generateBytesKey", method = RequestMethod.POST)
    public String generateBytesKey(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0201001(model, form);
        }

        model.addAttribute("generatedKey", new String(Base64
                .encode(encryptionDataService.generateBytesKey(form
                        .getKeyLength()))));
        return "encr/generateBytesKeyComplete";
    }

    @RequestMapping(value = "0202/001/generateSameBytesKey", method = RequestMethod.POST)
    public String generateSameBytesKey(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0202001(model, form);
        }

        BytesKeys generatedByteKeys = encryptionDataService
                .generateSameBytesKey(form.getKeyLength());
        model.addAttribute("generatedKey", new String(Base64
                .encode(generatedByteKeys.getKey1())));
        model.addAttribute("generatedKey2", new String(Base64
                .encode(generatedByteKeys.getKey2())));
        return "encr/generateSameBytesKeyComplete";
    }

    @RequestMapping(value = "0203/001/generateStringKey", method = RequestMethod.POST)
    public String generateStringKey(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0203001(model, form);
        }

        model.addAttribute("generatedKey", encryptionDataService
                .generateStringKey());
        return "encr/generateStringKeyComplete";
    }

}
