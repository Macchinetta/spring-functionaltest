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

import java.security.KeyPair;

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
public class ENCR03Controller {

    @Inject
    EncryptionDataService encryptionDataService;

    @RequestMapping(value = "/0301/001", method = RequestMethod.GET)
    public String handle0301001(Model model, EncryptionDataForm form) {
        return "encr/publicKeyEncryptByJCADecryptByJCA";
    }

    @RequestMapping(value = "/0302/001", method = RequestMethod.GET)
    public String handle0302001(Model model, EncryptionDataForm form) {
        return "encr/publicKeyEncryptByJCADecryptByOpenSSL";
    }

    @RequestMapping(value = "/0303/001", method = RequestMethod.GET)
    public String handle0303001(Model model, EncryptionDataForm form) {
        return "encr/publicKeyEncryptByOpenSSLDecryptByJCA";
    }

    @RequestMapping(value = "0301/001/publicKeyEncryptByJCADecryptByJCA", method = RequestMethod.POST)
    public String publicKeyEncryptByJCADecryptByJCA(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0301001(model, form);
        }

        KeyPair keyPair = encryptionDataService.generateKeyPairByJCA();
        byte[] encryptedBytes = encryptionDataService.encryptByPublicKey(form
                .getRawText(), keyPair.getPublic());
        model.addAttribute("encryptedText", new String(Base64.encode(
                encryptedBytes)));
        model.addAttribute("decryptedText", encryptionDataService
                .decryptByPrivateKey(encryptedBytes, keyPair.getPrivate()));
        return "encr/publicKeyEncryptionComplete";
    }

    @RequestMapping(value = "0302/001/publicKeyEncryptByJCADecryptByOpenSSL", method = RequestMethod.POST)
    public String publicKeyEncryptByJCADecryptByOpenSSL(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0302001(model, form);
        }

        byte[] encryptedBytes = encryptionDataService
                .encryptByJCAWithPublicKeyOfOpenSSL(form.getRawText());
        model.addAttribute("encryptedText", new String(Base64.encode(
                encryptedBytes)));
        model.addAttribute("decryptedText", encryptionDataService
                .openSSLDecrypt(encryptedBytes));

        return "encr/publicKeyEncryptionComplete";
    }

    @RequestMapping(value = "0303/001/publicKeyEncryptByOpenSSLDecryptByJCA", method = RequestMethod.POST)
    public String publicKeyEncryptByOpenSSLDecryptByJCA(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0303001(model, form);
        }

        byte[] encryptedBytes = encryptionDataService.openSSLEncrypt(form
                .getRawText());

        model.addAttribute("encryptedText", new String(Base64.encode(
                encryptedBytes)));
        model.addAttribute("decryptedText", encryptionDataService
                .decryptByJCAWithPrivateKeyOfOpenSSL(encryptedBytes));

        return "encr/publicKeyEncryptionComplete";
    }

}
