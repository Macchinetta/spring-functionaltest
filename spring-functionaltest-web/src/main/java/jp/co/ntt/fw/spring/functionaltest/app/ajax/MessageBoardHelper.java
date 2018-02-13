/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
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
