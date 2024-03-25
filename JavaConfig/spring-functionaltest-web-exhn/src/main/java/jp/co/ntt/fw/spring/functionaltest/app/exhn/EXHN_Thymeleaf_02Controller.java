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
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.inject.Inject;
import jakarta.validation.groups.Default;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.InvalidRequestException;
import jp.co.ntt.fw.spring.functionaltest.app.exhn.ArticleBatchRegisterForm.Confirm;
import jp.co.ntt.fw.spring.functionaltest.app.exhn.ArticleBatchRegisterForm.Register;
import jp.co.ntt.fw.spring.functionaltest.domain.service.exhn.ArticleFileService;

@Controller
@RequestMapping("thymeleaf")
public class EXHN_Thymeleaf_02Controller {

    @Value("${app.base.web.path}")
    private String baseWeb;

    @Inject
    ArticleFileHelper articleFileHelper;

    @Inject
    ArticleFileService articleFileService;

    @Inject
    ArticleSessionInfo articleSessionInfo;

    @ModelAttribute
    public ArticleBatchRegisterForm setUpMultipartForm() {
        ArticleBatchRegisterForm articleBatchRegisterForm = new ArticleBatchRegisterForm();
        return articleBatchRegisterForm;
    }

    @ModelAttribute(value = "articleSessionInfo")
    public ArticleSessionInfo setUpArticleSessionInfo() {
        return articleSessionInfo;
    }

    @GetMapping(value = "0202/001")
    public String handle0202001(Model model) {
        model.addAttribute("count", articleFileService.countAll());
        model.addAttribute("testNumber", "0200");
        return "thymeleaf/exhn/articleBatchRegister";
    }

    @GetMapping(value = "0203/001")
    public String handle0203001(Model model) {
        model.addAttribute("count", articleFileService.countAll());
        model.addAttribute("testNumber", "0200");
        return "thymeleaf/exhn/articleBatchRegister";
    }

    @PostMapping(value = "0200/confirm", params = "upload")
    public String uploadConfirm(@Validated({ Confirm.class,
            Default.class }) ArticleBatchRegisterForm form,
            BindingResult result, ArticleSessionInfo articleSessionInfo,
            Model model, RedirectAttributes redirectAttrs) throws IOException {

        if (result.hasErrors()) {
            model.addAttribute("testNumber", "0200");
            return "thymeleaf/exhn/articleBatchRegister";
        }

        String uploadTemporaryFileId = articleFileHelper.createTemporaryFile(
                form);
        articleSessionInfo.setUploadTemporaryFileId(uploadTemporaryFileId);
        model.addAttribute("testNumber", "0200");
        return "thymeleaf/exhn/articleBatchConfirm";
    }

    @PostMapping(value = "0200/register", params = "upload")
    public String uploadRegister(@Validated({ Register.class,
            Default.class }) ArticleBatchRegisterForm form,
            BindingResult result, ArticleSessionInfo articleSessionInfo,
            RedirectAttributes redirectAttrs) throws IOException {

        String uploadTemporaryFileId = articleSessionInfo
                .getUploadTemporaryFileId();
        if (result.hasErrors() || !StringUtils.hasText(uploadTemporaryFileId)) {
            throw new InvalidRequestException(result.toString());
        }

        articleFileHelper.uploadToDb(form, uploadTemporaryFileId);

        redirectAttrs.addFlashAttribute("title", form.getTitle());
        redirectAttrs.addFlashAttribute("uploadedFileName", form.getFileName());

        return "redirect:/thymeleaf/0200?complete";
    }

    @GetMapping(value = "0200", params = "complete")
    public String articleComplete(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        articleSessionInfo.setUploadTemporaryFileId("");
        return "thymeleaf/exhn/articleBatchComplete";
    }

}
