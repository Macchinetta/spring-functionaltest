/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import java.io.IOException;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.InvalidRequestException;
import jp.co.ntt.fw.spring.functionaltest.app.exhn.ArticleBatchRegisterForm.Register;

@Controller
@SessionAttributes(value = "articleSessionInfo")
public class EXHN0601002Controller {

    @Inject
    ArticleFileHelper articleFileHelper;

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

    @RequestMapping(params = "upload")
    public String uploadRegister(
            @Validated({ Register.class, Default.class }) ArticleBatchRegisterForm form,
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

        return "redirect:/exhn/0601/002?complete";
    }

}
