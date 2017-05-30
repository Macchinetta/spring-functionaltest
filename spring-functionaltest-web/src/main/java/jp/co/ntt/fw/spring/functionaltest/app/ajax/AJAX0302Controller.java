/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import java.util.Locale;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.bean.ErrorResults;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.terasoluna.gfw.common.exception.BusinessException;

@RequestMapping("ajax")
@Controller
public class AJAX0302Controller {

    @Inject
    PersonalComputerHelper personalComputerHelper;

    @RequestMapping(value = "0302/001/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> edit(@Validated PersonalComputerForm form,
            Locale locale) {

        PersonalComputerResult result;
        try {
            result = personalComputerHelper
                    .updateAndBindPersonalComputerResult(form, locale);
        } catch (BusinessException e) {

            return new ResponseEntity<ErrorResults>(personalComputerHelper
                    .setBusinessExceptionErrorResults(e, locale), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<PersonalComputerResult>(result, HttpStatus.OK);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResults handleBindException(BindException e, Locale locale) {
        return personalComputerHelper.setErrorResults(e.getBindingResult()
                .getFieldErrors(), locale);
    }

}
