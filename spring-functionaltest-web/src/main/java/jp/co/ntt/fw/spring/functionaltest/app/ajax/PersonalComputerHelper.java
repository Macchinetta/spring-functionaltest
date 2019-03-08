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
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.bean.ErrorResults;
import jp.co.ntt.fw.spring.functionaltest.domain.model.PersonalComputer;
import jp.co.ntt.fw.spring.functionaltest.domain.service.ajax.PersonalComputerService;

import com.github.dozermapper.core.Mapper;
import org.springframework.context.MessageSource;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;

@Component
public class PersonalComputerHelper {

    @Inject
    MessageSource messageSource;

    @Inject
    Mapper beanMapper;

    @Inject
    PersonalComputerService personalComputerService;

    void setPersonalComputer(int id, PersonalComputerForm form) {
        PersonalComputer personalComputer = personalComputerService
                .getPersonalComputer(id);
        beanMapper.map(personalComputer, form);
    }

    PersonalComputerResult updateAndBindPersonalComputerResult(
            PersonalComputerForm form, Locale locale) {

        update(form);

        PersonalComputerResult personalComputerResult = new PersonalComputerResult();
        personalComputerResult.setMessages(Arrays.asList(messageSource
                .getMessage("i.sf.ajax.0001", null, locale)));

        return personalComputerResult;
    }

    PersonalComputerWithErrorResults updateAndBindPersonalComputerWithErrorResults(
            PersonalComputerForm form, Locale locale) {

        update(form);

        PersonalComputerWithErrorResults personalComputerWithErrorResult = new PersonalComputerWithErrorResults();
        personalComputerWithErrorResult.setMessages(Arrays.asList(messageSource
                .getMessage("i.sf.ajax.0001", null, locale)));

        return personalComputerWithErrorResult;
    }

    PersonalComputerWithErrorResults setPersonalComputerErrorResults(
            BindingResult bresult, Locale locale) {
        PersonalComputerWithErrorResults personalComputerWithErrorResults = new PersonalComputerWithErrorResults();
        for (FieldError fieldError : bresult.getFieldErrors()) {
            personalComputerWithErrorResults.addErrorResults(fieldError
                    .getCode(), messageSource.getMessage(fieldError, locale),
                    fieldError.getField());
        }

        return personalComputerWithErrorResults;
    }

    private void update(PersonalComputerForm form) {
        PersonalComputer personalComputer = new PersonalComputer();
        beanMapper.map(form, personalComputer);

        // ID:1 のみ更新
        personalComputer.setPersonalComputerId(1);
        personalComputerService.update(personalComputer);
    }

    ErrorResults setErrorResults(List<FieldError> fieldErrors, Locale locale) {
        ErrorResults errorResults = new ErrorResults();

        for (FieldError fieldError : fieldErrors) {
            errorResults.add(fieldError.getCode(), messageSource.getMessage(
                    fieldError, locale), fieldError.getField());
        }

        return errorResults;
    }

    ErrorResults setHttpMessageNotReadableExceptionResults(
            HttpMessageNotReadableException e, Locale locale) {
        ErrorResults errorResults = new ErrorResults();

        JsonMappingException jme = (JsonMappingException) e.getCause();
        List<Reference> errorObj = jme.getPath();

        for (Reference reference : errorObj) {
            errorResults.add("e.sf.ajax.8002", messageSource.getMessage(
                    "e.sf.ajax.8002", Arrays.asList(reference.getFieldName())
                            .toArray(), locale), reference.getFieldName());
        }

        return errorResults;
    }

    ErrorResults setBusinessExceptionErrorResults(BusinessException e,
            Locale locale) {
        ErrorResults errorResults = new ErrorResults();

        for (ResultMessage resultMessage : e.getResultMessages().getList()) {
            errorResults.add(resultMessage.getCode(), messageSource.getMessage(
                    resultMessage.getCode(), resultMessage.getArgs(), locale),
                    "");
        }

        return errorResults;
    }
}
