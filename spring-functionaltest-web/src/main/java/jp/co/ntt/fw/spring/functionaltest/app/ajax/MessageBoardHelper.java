/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class MessageBoardHelper {

    @Inject
    MessageSource messageSource;

    ErrorResults setErrorResults(List<FieldError> fildErrors, Locale locale) {
        ErrorResults errorResults = new ErrorResults();

        for (FieldError fieldError : fildErrors) {
            errorResults.add(fieldError.getCode(), messageSource.getMessage(
                    fieldError, locale), fieldError.getField());
        }
        return errorResults;
    }

}
