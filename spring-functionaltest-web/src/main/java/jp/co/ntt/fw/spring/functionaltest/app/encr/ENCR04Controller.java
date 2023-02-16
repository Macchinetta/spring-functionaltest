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

import java.security.KeyPair;

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
@RequestMapping("encr")
public class ENCR04Controller {

    @Inject
    EncryptionDataService encryptionDataService;

    @GetMapping(value = "/0401/001")
    public String handle0401001(Model model, EncryptionDataForm form) {
        return "encr/encryptDecryptByHybrid";
    }

    @PostMapping(value = "0401/001/encryptDecryptByHybrid")
    public String encryptDecryptByHybrid(Model model,
            @Validated EncryptionDataForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return handle0401001(model, form);
        }

        KeyPair keyPair = encryptionDataService.generateKeyPairByJCA();

        String encrypted = encryptionDataService.encryptByHybrid(form
                .getRawText(), keyPair.getPublic());
        model.addAttribute("encryptedText", encrypted);
        model.addAttribute("decryptedText", encryptionDataService
                .decryptByHybrid(encrypted, keyPair.getPrivate()));
        return "encr/encryptCompleteByHybrid";
    }

}
