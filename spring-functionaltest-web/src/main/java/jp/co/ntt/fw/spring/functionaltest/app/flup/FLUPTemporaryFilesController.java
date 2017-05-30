/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;

/**
 * 本アプリでは、原則、大項目単位でcontrollerを作成するが、 ファイルアップロード機能では、中項目単位でweb.xmlのパラメータを変更する試験が存在するため、 統一して中項目ごとにController作成をしている。
 */
@RequestMapping("flup/temporaryFiles")
@Controller
public class FLUPTemporaryFilesController {

    @Inject
    TemporaryFilesHelper temporaryFilesHelper;

    @RequestMapping(method = RequestMethod.GET, params = "list")
    public String list(@Validated ScreenFlowUploadForm form,
            BindingResult result, Model model) {
        temporaryFilesHelper.bindTemporaryFileToModel(model);
        return "flup/temporaryFileUploadList";
    }

    @RequestMapping(method = RequestMethod.POST, params = "delete")
    public String delete(RedirectAttributes redirectAttributes) throws IOException {
        temporaryFilesHelper.clearTemporarydirectory();
        redirectAttributes.addFlashAttribute(ResultMessages.success().add(
                "i.sf.flup.0003"));
        return "redirect:/flup/temporaryFiles?complete";
    }

    @RequestMapping(method = RequestMethod.GET, params = "complete")
    public String delete(Model model) {
        return "flup/temporaryFileUploadList";
    }

}
