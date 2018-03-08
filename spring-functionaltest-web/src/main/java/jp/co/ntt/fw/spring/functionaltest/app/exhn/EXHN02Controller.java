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
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import java.io.IOException;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.InvalidRequestException;
import jp.co.ntt.fw.spring.functionaltest.app.exhn.ArticleBatchRegisterForm.Confirm;
import jp.co.ntt.fw.spring.functionaltest.app.exhn.ArticleBatchRegisterForm.Register;
import jp.co.ntt.fw.spring.functionaltest.domain.service.exhn.ArticleFileService;

@Controller
@RequestMapping("exhn")
public class EXHN02Controller {

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

    @RequestMapping(value = "0202/001")
    public String handle0202001(Model model) {
        model.addAttribute("count", articleFileService.countAll());
        model.addAttribute("testNumber", "0200");
        return "exhn/articleBatchRegister";
    }

    @RequestMapping(value = "0203/001")
    public String handle0203001(Model model) {
        model.addAttribute("count", articleFileService.countAll());
        model.addAttribute("testNumber", "0200");
        return "exhn/articleBatchRegister";
    }

    @RequestMapping(value = "0200/confirm", params = "upload", method = RequestMethod.POST)
    public String uploadConfirm(@Validated({ Confirm.class,
            Default.class }) ArticleBatchRegisterForm form,
            BindingResult result, ArticleSessionInfo articleSessionInfo,
            Model model, RedirectAttributes redirectAttrs) throws IOException {

        if (result.hasErrors()) {
            model.addAttribute("testNumber", "0200");
            return "exhn/articleBatchRegister";
        }

        String uploadTemporaryFileId = articleFileHelper.createTemporaryFile(
                form);
        articleSessionInfo.setUploadTemporaryFileId(uploadTemporaryFileId);
        model.addAttribute("testNumber", "0200");
        return "exhn/articleBatchConfirm";
    }

    @RequestMapping(value = "0200/register", params = "upload", method = RequestMethod.POST)
    public String uploadRegister(@Validated({ Register.class,
            Default.class }) ArticleBatchRegisterForm form,
            BindingResult result, ArticleSessionInfo articleSessionInfo,
            RedirectAttributes redirectAttrs) throws IOException {

        String uploadTemporaryFileId = articleSessionInfo
                .getUploadTemporaryFileId();
        if (result.hasErrors() || StringUtils.isEmpty(uploadTemporaryFileId)) {
            throw new InvalidRequestException(result.toString());
        }

        articleFileHelper.uploadToDb(form, uploadTemporaryFileId);

        redirectAttrs.addFlashAttribute("title", form.getTitle());
        redirectAttrs.addFlashAttribute("uploadedFileName", form.getFileName());

        return "redirect:/exhn/0200?complete";
    }

    @RequestMapping(value = "0200", params = "complete")
    public String articleComplete(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        articleSessionInfo.setUploadTemporaryFileId("");
        return "exhn/articleBatchComplete";
    }

    @RequestMapping(value = "delete/view", method = RequestMethod.GET)
    public String deleteTemporaryFileView() {
        return "exhn/articleTemporaryFileDelete";
    }

    @RequestMapping(value = "delete")
    public String deleteTemporaryFile(
            @RequestParam("temporaryFileName") String temporaryFileName) throws IOException {
        articleFileHelper.deleteTemporaryFile(temporaryFileName);
        return "exhn/index";
    }

    @RequestMapping(value = "copy/temporaryFile")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void copyTemporaryFile(
            @RequestParam("temporaryFileName") String temporaryFileName) throws IOException {
        articleFileHelper.copyTemporaryFile(temporaryFileName);
    }

    @RequestMapping(value = "delete/article", method = RequestMethod.GET)
    public String deleteArticleFileBeforeTest() throws IOException {
        deleteArticleFile();
        return "exhn/index";
    }

    @RequestMapping(value = "delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteArticleFile() throws IOException {
        articleFileHelper.deleteAll();
    }
}
