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
package jp.co.ntt.fw.spring.functionaltest.app.encr;

import java.nio.charset.StandardCharsets;

import javax.inject.Inject;

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
public class ENCR01Controller {

    @Inject
    EncryptionDataService encryptionDataService;

    @RequestMapping(value = "/0101/001", method = RequestMethod.GET)
    public String handle0101001(Model model, EncryptionDataForm form) {
        return "encr/encryptDecryptByTextEncryptor";
    }

    @RequestMapping(value = "/0102/001", method = RequestMethod.GET)
    public String handle0102001(Model model, EncryptionDataForm form) {
        return "encr/encryptByTextEncryptorForSameResult";
    }

    @RequestMapping(value = "/0103/001", method = RequestMethod.GET)
    public String handle0103001(Model model, EncryptionDataForm form) {
        return "encr/encryptDecryptByBytesEncryptor";
    }

    @RequestMapping(value = "/0104/001", method = RequestMethod.GET)
    public String handle0104001(Model model, EncryptionDataForm form) {
        return "encr/encryptDecryptTextByAesWithGcm";
    }

    @RequestMapping(value = "/0105/001", method = RequestMethod.GET)
    public String handle0105001(Model model, EncryptionDataForm form) {
        return "encr/encryptDecryptBytesByAesWithGcm";
    }

    @RequestMapping(value = "0101/001/encryptDecryptByTextEncryptor", method = RequestMethod.POST)
    public String encryptByTextEncryptor(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0101001(model, form);
        }

        String encryptedText = encryptionDataService.encryptText(form
                .getRawText());
        model.addAttribute("encryptedText", encryptedText);
        model.addAttribute("decryptedText", encryptionDataService.decryptText(
                encryptedText));
        return "encr/encryptCompleteByTextEncryptor";
    }

    @RequestMapping(value = "0102/001/encryptByTextEncryptorForSameResult", method = RequestMethod.POST)
    public String encryptFirstByTextEncryptor(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0101001(model, form);
        }

        model.addAttribute("encryptedText", encryptionDataService
                .encryptQueryableText(form.getRawText()));
        model.addAttribute("encryptedText2", encryptionDataService
                .encryptQueryableText(form.getRawText()));
        return "encr/encryptCompleteByTextEncryptorForSameResult";
    }

    @RequestMapping(value = "0103/001/encryptDecryptByBytesEncryptor", method = RequestMethod.POST)
    public String encryptByBytesEncryptor(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0103001(model, form);
        }

        byte[] encryptedBytes = encryptionDataService.encryptBytes(form
                .getRawText().getBytes(StandardCharsets.UTF_8));
        model.addAttribute("encryptedText", new String(Base64.encode(
                encryptedBytes)));
        model.addAttribute("decryptedText", new String(encryptionDataService
                .decryptBytes(encryptedBytes), StandardCharsets.UTF_8));
        return "encr/encryptCompleteByBytesEncryptor";
    }

    @RequestMapping(value = "0104/001/encryptDecryptTextByAesWithGcm", method = RequestMethod.POST)
    public String encryptTextByAesWithGcm(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0104001(model, form);
        }

        String encryptedText = encryptionDataService.encryptTextByAesWithGcm(
                form.getRawText());
        model.addAttribute("encryptedText", encryptedText);
        model.addAttribute("decryptedText", encryptionDataService
                .decryptTextByAesWithGcm(encryptedText));
        return "encr/encryptCompleteTextByAesWithGcm";
    }

    @RequestMapping(value = "0105/001/encryptDecryptBytesByAesWithGcm", method = RequestMethod.POST)
    public String encryptBytesByAesWithGcm(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0105001(model, form);
        }

        String encryptedBytes = encryptionDataService.encryptBytesByAesWithGcm(
                form.getRawText());
        model.addAttribute("encryptedText", encryptedBytes);
        model.addAttribute("decryptedText", encryptionDataService
                .decryptBytesByAesWithGcm(encryptedBytes));
        return "encr/encryptCompleteBytesByAesWithGcm";
    }

}
