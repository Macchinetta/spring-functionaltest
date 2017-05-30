/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "jp.co.ntt.fw.spring.functionaltest.app.vldt")
public class ConstraintViolationExceptionHandler {
    private static final Logger log = LoggerFactory
            .getLogger(ConstraintViolationExceptionHandler.class);

    @ExceptionHandler
    public String handleConstraintViolationException(
            ConstraintViolationException e) {
        log.error("ConstraintViolations[\n{}\n]", e.getConstraintViolations());
        return "common/error/systemError";
    }
}
