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
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import java.io.IOException;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.InvalidRequestException;
import jp.co.ntt.fw.spring.functionaltest.app.exhn.ArticleBatchRegisterForm.Confirm;
import jp.co.ntt.fw.spring.functionaltest.app.exhn.ArticleBatchRegisterForm.Register;
import jp.co.ntt.fw.spring.functionaltest.domain.service.exhn.ArticleFileService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.exhn.ThrowErrorService;

@Controller
@SessionAttributes(value = "articleSessionInfo")
@RequestMapping("exhn")
public class EXHN06Controller {

    @Inject
    EmployeeHelper employeeHelper;

    @Inject
    ArticleFileHelper articleFileHelper;

    @Inject
    ArticleFileService articleFileService;

    @Inject
    ArticleSessionInfo articleSessionInfo;

    @Inject
    ThrowErrorService throwErrorService;

    @ModelAttribute
    public EmployeeForm setUpForm() {
        EmployeeForm employeeForm = new EmployeeForm();
        return employeeForm;
    }

    @ModelAttribute
    public ArticleBatchRegisterForm setUpMultipartForm() {
        ArticleBatchRegisterForm articleBatchRegisterForm = new ArticleBatchRegisterForm();
        return articleBatchRegisterForm;
    }

    @ModelAttribute(value = "articleSessionInfo")
    public ArticleSessionInfo setUpArticleSessionInfo() {
        return articleSessionInfo;
    }

    @RequestMapping(value = "0601/001")
    public String handle0601001(Model model) {
        model.addAttribute("count", articleFileService.countAll());
        model.addAttribute("testNumber", "0601");
        return "exhn/articleBatchRegister";
    }

    @RequestMapping(value = "0601/002")
    public String handle0601002(Model model) {
        model.addAttribute("count", articleFileService.countAll());
        model.addAttribute("testNumber", "0601/002");
        return "exhn/articleBatchRegister";
    }

    @RequestMapping(value = "0601/004")
    public String handle0601004(EmployeeForm form, Model model) {
        employeeHelper.convertToForm(form);
        model.addAttribute("testNumber", "0601/004/001");

        return "exhn/employeeEdit";
    }

    @RequestMapping(value = "0601/register", params = "upload", method = RequestMethod.GET)
    public String uploadRegisterGet(
            @Validated({Register.class, Default.class}) ArticleBatchRegisterForm form,
            @RequestParam("uploadTemporaryFileId") String uploadTemporaryFileId,
            BindingResult result, RedirectAttributes redirectAttrs) throws IOException {
        if (result.hasErrors()) {
            throw new InvalidRequestException(result.toString());
        }

        articleFileHelper.uploadToDb(form, uploadTemporaryFileId);

        redirectAttrs.addFlashAttribute("title", form.getTitle());
        redirectAttrs.addFlashAttribute("uploadedFileName", form.getFileName());

        return "redirect:/exhn/0601?complete";
    }

    @RequestMapping(value = "0601/confirm", params = "upload", method = RequestMethod.POST)
    public String uploadConfirm(
            @Validated({Confirm.class, Default.class}) ArticleBatchRegisterForm form,
            BindingResult result, ArticleSessionInfo articleSessionInfo, Model model,
            RedirectAttributes redirectAttrs) throws IOException {

        if (result.hasErrors()) {
            model.addAttribute("testNumber", "0601");
            return "exhn/articleBatchRegister";
        }

        String uploadTemporaryFileId = articleFileHelper.createTemporaryFile(form);
        articleSessionInfo.setUploadTemporaryFileId(uploadTemporaryFileId);
        model.addAttribute("testNumber", "0601");
        return "exhn/articleBatchConfirm";
    }

    @RequestMapping(value = "0601/register", params = "upload", method = RequestMethod.POST)
    public String uploadRegisterPost(
            @Validated({Register.class, Default.class}) ArticleBatchRegisterForm form,
            BindingResult result, ArticleSessionInfo articleSessionInfo,
            RedirectAttributes redirectAttrs) throws IOException {
        String uploadTemporaryFileId = articleSessionInfo.getUploadTemporaryFileId();
        if (result.hasErrors() || !StringUtils.hasText(uploadTemporaryFileId)) {
            throw new InvalidRequestException(result.toString());
        }

        articleFileHelper.uploadToDb(form, uploadTemporaryFileId);

        redirectAttrs.addFlashAttribute("title", form.getTitle());
        redirectAttrs.addFlashAttribute("uploadedFileName", form.getFileName());

        return "redirect:/exhn/0601?complete";
    }

    @RequestMapping(value = "0601", params = "complete")
    public String articleComplete() {
        return "exhn/articleBatchComplete";
    }

    @RequestMapping(value = "0601/002/confirm", params = "upload", method = RequestMethod.POST)
    public String uploadConfirmChangeproperty(
            @Validated({Confirm.class, Default.class}) ArticleBatchRegisterForm form,
            BindingResult result, ArticleSessionInfo articleSessionInfo, Model model,
            RedirectAttributes redirectAttrs) throws IOException {

        if (result.hasErrors()) {
            model.addAttribute("testNumber", "0601/002");
            return "exhn/articleBatchRegister";
        }

        String uploadTemporaryFileId = articleFileHelper.createTemporaryFile(form);
        articleSessionInfo.setUploadTemporaryFileId(uploadTemporaryFileId);
        model.addAttribute("testNumber", "0601/002");
        return "exhn/articleBatchConfirm";
    }

    @RequestMapping(value = "0601/002", params = "complete")
    public String articleCompleteChangeproperty(ArticleSessionInfo articleSessionInfo,
            SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        articleSessionInfo.setUploadTemporaryFileId("");
        return "exhn/articleBatchComplete";
    }

    @RequestMapping(value = "0601/005")
    public String handle0601005() {

        throwErrorService.throwAssertionError();

        return "exhn/index";
    }

}
