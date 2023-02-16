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
package jp.co.ntt.fw.spring.functionaltest.app.encr;

import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.encr.BytesKeys;
import jp.co.ntt.fw.spring.functionaltest.domain.service.encr.EncryptionDataService;

@Controller
@RequestMapping("encr")
public class ENCR02Controller {

    @Inject
    EncryptionDataService encryptionDataService;

    @GetMapping(value = "/0201/001")
    public String handle0201001(Model model, EncryptionDataForm form) {
        return "encr/generateBytesKey";
    }

    @GetMapping(value = "/0202/001")
    public String handle0202001(Model model, EncryptionDataForm form) {
        return "encr/generateSameBytesKey";
    }

    @GetMapping(value = "/0203/001")
    public String handle0203001(Model model, EncryptionDataForm form) {
        return "encr/generateStringKey";
    }

    @PostMapping(value = "0201/001/generateBytesKey")
    public String generateBytesKey(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0201001(model, form);
        }

        model.addAttribute("generatedKey", Base64.getEncoder().encodeToString(
                encryptionDataService.generateBytesKey(form.getKeyLength())));
        return "encr/generateBytesKeyComplete";
    }

    @PostMapping(value = "0202/001/generateSameBytesKey")
    public String generateSameBytesKey(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0202001(model, form);
        }

        BytesKeys generatedByteKeys = encryptionDataService
                .generateSameBytesKey(form.getKeyLength());
        model.addAttribute("generatedKey", Base64.getEncoder().encodeToString(
                generatedByteKeys.getKey1()));
        model.addAttribute("generatedKey2", Base64.getEncoder().encodeToString(
                generatedByteKeys.getKey2()));
        return "encr/generateSameBytesKeyComplete";
    }

    @PostMapping(value = "0203/001/generateStringKey")
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
