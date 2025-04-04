/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice(basePackages = "jp.co.ntt.fw.spring.functionaltest.app.vldt")
public class ConstraintViolationExceptionHandler {
    private static final Logger logger =
            LoggerFactory.getLogger(ConstraintViolationExceptionHandler.class);

    @ExceptionHandler
    public String handleConstraintViolationException(ConstraintViolationException e, Model model) {
        logger.error("ConstraintViolations[\n{}\n]", e.getConstraintViolations());
        return "jsp/common/error/systemError";
    }
}
