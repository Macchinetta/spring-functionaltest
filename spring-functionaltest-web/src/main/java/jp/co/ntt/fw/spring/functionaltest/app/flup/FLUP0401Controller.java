/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.groups.Default;

import jp.co.ntt.fw.spring.functionaltest.app.flup.SingleUploadForm.UploadFileMaxSize250byte;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 本アプリでは、原則、大項目単位でcontrollerを作成するが、 ファイルアップロード機能では、中項目単位でweb.xmlのパラメータを変更する試験が存在するため、 統一して中項目ごとにController作成をしている。
 */
@RequestMapping("flup/0401")
@Controller
public class FLUP0401Controller {

    @Inject
    FileUploadHelper fileUploadHelper;

    @ModelAttribute
    public MultiUploadForm setUpForm() {
        return new MultiUploadForm();
    }

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle001Form() {
        return form();
    }

    @RequestMapping(value = "001", method = RequestMethod.POST)
    public String handle001Upload(@Validated MultiUploadForm form,
            BindingResult result, RedirectAttributes redirectAttributes) throws IOException {
        return upload(form, result, redirectAttributes);
    }

    @RequestMapping(value = "002", method = RequestMethod.GET)
    public String handle002Form() {
        return form();
    }

    @RequestMapping(value = "002", method = RequestMethod.POST)
    public String handle002Upload(@Validated MultiUploadForm form,
            BindingResult result, RedirectAttributes redirectAttributes) throws IOException {
        return upload(form, result, redirectAttributes);
    }

    @RequestMapping(value = "003", method = RequestMethod.GET)
    public String handle003Form() {
        return form();
    }

    @RequestMapping(value = "003", method = RequestMethod.POST)
    public String handle003Upload(@Validated MultiUploadForm form,
            BindingResult result, RedirectAttributes redirectAttributes) throws IOException {
        return upload(form, result, redirectAttributes);
    }

    @RequestMapping(value = "004", method = RequestMethod.GET)
    public String handle004Form() {
        return form();
    }

    @RequestMapping(value = "004", method = RequestMethod.POST)
    public String handle004Upload(@Validated({ UploadFileMaxSize250byte.class,
            Default.class }) MultiUploadForm form, BindingResult result,
            RedirectAttributes redirectAttributes) throws IOException {
        return upload(form, result, redirectAttributes);
    }

    @RequestMapping(method = RequestMethod.GET, params = "complete")
    public String handleComplate() {
        return "flup/multiFileUploadComplete";
    }

    private String form() {
        return "flup/multiFileUploadForm";
    }

    private String upload(@Validated MultiUploadForm form,
            BindingResult result, RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            return "flup/multiFileUploadForm";
        }

        List<MultipartFile> multipartFiles = new ArrayList<MultipartFile>();
        for (SingleUploadForm uploadForm : form.getUploadForms()) {
            multipartFiles.add(uploadForm.getMultipartFile());
        }

        fileUploadHelper.bindToModel(multipartFiles, redirectAttributes);

        return "redirect:/flup/0401?complete";
    }

}
