/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UploadFile;
import jp.co.ntt.fw.spring.functionaltest.domain.service.flup.FileUploadService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 本アプリでは、原則、大項目単位でcontrollerを作成するが、 ファイルアップロード機能では、中項目単位でweb.xmlのパラメータを変更する試験が存在するため、 統一して中項目ごとにController作成をしている。
 */
@RequestMapping("flup/files")
@Controller
public class FLUPFilesController {

    @Inject
    FileUploadService fileUploadService;

    @RequestMapping(method = RequestMethod.GET, params = "download")
    public String download(@RequestParam(value = "fileId") String fileId,
            Model model) {
        UploadFile uploadFile = fileUploadService.getUploadFile(fileId);
        model.addAttribute(uploadFile);
        return "flup/uploadFileDownload";
    }

}
