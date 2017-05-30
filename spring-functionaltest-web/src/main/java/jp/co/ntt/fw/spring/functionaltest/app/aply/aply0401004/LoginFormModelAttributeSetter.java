/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply.aply0401004;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jp.co.ntt.fw.spring.functionaltest.app.aply.CommonParameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@ControllerAdvice(annotations = LoginFormModelAttributeSetter.LoginFormModelAttribute.class)
public class LoginFormModelAttributeSetter {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface LoginFormModelAttribute {
    }

    private static final Logger logger = LoggerFactory
            .getLogger(LoginFormModelAttributeSetter.class);

    // パラメータ指定無しだと全てのAdviceクラスのパラメータに対してinitBinderを実施する為、今回の試験ではパラメータを指定
    @InitBinder("commonParam1")
    public void initBinder() {
        logger.info("[APLY0401004]LoginFormModelAttributeSetter initBinder Method Called");
    }

    @ModelAttribute
    public CommonParameters setUpCommonParameters01(
            @RequestParam(value = "commonParam1", defaultValue = "defCommonParam1") String commonParam1) {
        logger.info("[APLY0401004]LoginFormModelAttributeSetter setUpCommonParameters01 Method Called");
        CommonParameters params = new CommonParameters();
        params.setCommonParam1(commonParam1);
        return params;
    }
}
