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
package jp.co.ntt.fw.spring.functionaltest.app.encr;

import java.security.KeyPair;
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
import jp.co.ntt.fw.spring.functionaltest.domain.service.encr.EncryptionDataService;

@Controller
@RequestMapping("jsp")
public class ENCR_JSP_03Controller {

    @Inject
    EncryptionDataService encryptionDataService;

    @GetMapping(value = "0301/001")
    public String handle0301001(Model model, EncryptionDataForm form) {
        return "jsp/encr/publicKeyEncryptByJCADecryptByJCA";
    }

    @GetMapping(value = "0302/001")
    public String handle0302001(Model model, EncryptionDataForm form) {
        return "jsp/encr/publicKeyEncryptByJCADecryptByOpenSSL";
    }

    @GetMapping(value = "0303/001")
    public String handle0303001(Model model, EncryptionDataForm form) {
        return "jsp/encr/publicKeyEncryptByOpenSSLDecryptByJCA";
    }

    @PostMapping(value = "0301/001/publicKeyEncryptByJCADecryptByJCA")
    public String publicKeyEncryptByJCADecryptByJCA(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0301001(model, form);
        }

        KeyPair keyPair = encryptionDataService.generateKeyPairByJCA();
        byte[] encryptedBytes = encryptionDataService.encryptByPublicKey(form
                .getRawText(), keyPair.getPublic());
        model.addAttribute("encryptedText", Base64.getEncoder().encodeToString(
                encryptedBytes));
        model.addAttribute("decryptedText", encryptionDataService
                .decryptByPrivateKey(encryptedBytes, keyPair.getPrivate()));
        return "jsp/encr/publicKeyEncryptionComplete";
    }

    @PostMapping(value = "0302/001/publicKeyEncryptByJCADecryptByOpenSSL")
    public String publicKeyEncryptByJCADecryptByOpenSSL(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0302001(model, form);
        }

        byte[] encryptedBytes = encryptionDataService
                .encryptByJCAWithPublicKeyOfOpenSSL(form.getRawText());
        model.addAttribute("encryptedText", Base64.getEncoder().encodeToString(
                encryptedBytes));
        model.addAttribute("decryptedText", encryptionDataService
                .openSSLDecrypt(encryptedBytes));

        return "jsp/encr/publicKeyEncryptionComplete";
    }

    @PostMapping(value = "0303/001/publicKeyEncryptByOpenSSLDecryptByJCA")
    public String publicKeyEncryptByOpenSSLDecryptByJCA(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0303001(model, form);
        }

        byte[] encryptedBytes = encryptionDataService.openSSLEncrypt(form
                .getRawText());

        model.addAttribute("encryptedText", Base64.getEncoder().encodeToString(
                encryptedBytes));
        model.addAttribute("decryptedText", encryptionDataService
                .decryptByJCAWithPrivateKeyOfOpenSSL(encryptedBytes));

        return "jsp/encr/publicKeyEncryptionComplete";
    }

}
