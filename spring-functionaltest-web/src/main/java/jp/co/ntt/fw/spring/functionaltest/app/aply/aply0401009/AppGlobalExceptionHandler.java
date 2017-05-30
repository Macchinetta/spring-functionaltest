/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply.aply0401009;

import jp.co.ntt.fw.spring.functionaltest.app.aply.CommonParameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@ControllerAdvice(value = "jp.co.ntt.fw.spring.functionaltest.app.aply.aply0401009.test1")
public class AppGlobalExceptionHandler {

    private static final Logger logger = LoggerFactory
            .getLogger(AppGlobalExceptionHandler.class);

    // パラメータ指定無しだと全てのAdviceクラスのパラメータに対してinitBinderを実施する為、今回の試験ではパラメータを指定
    @InitBinder("commonParam1")
    public void initBinder() {
        logger.info("[APLY0401009]AppGlobalExceptionHandler initBinder Method Called");
    }

    @ModelAttribute
    public CommonParameters setUpCommonParameters01(
            @RequestParam(value = "commonParam1", defaultValue = "defCommonParam1") String commonParam1) {
        logger.info("[APLY0401009]AppGlobalExceptionHandler setUpCommonParameters01 Method Called");
        CommonParameters params = new CommonParameters();
        params.setCommonParam1(commonParam1);
        return params;
    }
}
