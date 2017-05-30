/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception;

import java.text.MessageFormat;

import org.terasoluna.gfw.common.exception.ExceptionLogger;

public class FunctionalTestExceptionLogger extends ExceptionLogger {

    private final String LOG_MESSAGE_FORMAT = "[{0}][{1}] {2}";

    @Override
    protected String makeLogMessage(Exception ex) {
        return MessageFormat.format(LOG_MESSAGE_FORMAT, ex.getClass(), ex
                .getCause(), ex.getMessage());
    }
}
