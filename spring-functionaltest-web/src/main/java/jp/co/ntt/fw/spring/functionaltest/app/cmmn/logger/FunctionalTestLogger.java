/*
 * Copyright 2014-2018 NTT Corporation.
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
 */
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.logger;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;

public class FunctionalTestLogger {

    private static final String LOG_MESSAGE_FORMAT = "[{0}], {1}";

    private static final String UNDEFINED_MESSAGE_FORMAT = "UNDEFINED-MESSAGE id:{0} arg:{1}";

    private static ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    static {
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames("i18n/log-messages");
    }

    private final Logger logger;

    private FunctionalTestLogger(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    public static FunctionalTestLogger getLogger(Class<?> clazz) {
        return new FunctionalTestLogger(clazz);
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public void debug(String format, Object... args) {
        logger.debug(format, args);
    }

    public void trace(String id, Object... args) {
        if (logger.isTraceEnabled()) {
            logger.trace(createLogMessage(id, args));
        }
    }

    public void info(String id, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(createLogMessage(id, args));
        }
    }

    public void warn(String id, Object... args) {
        if (logger.isWarnEnabled()) {
            logger.warn(createLogMessage(id, args));
        }
    }

    public void warn(String id, Throwable t, Object... args) {
        if (logger.isWarnEnabled()) {
            logger.warn(createLogMessage(id, args), t);
        }
    }

    public void error(String id, Object... args) {
        if (logger.isErrorEnabled()) {
            logger.error(createLogMessage(id, args));
        }
    }

    public void error(String id, Throwable t, Object... args) {
        if (logger.isErrorEnabled()) {
            logger.error(createLogMessage(id, args), t);
        }
    }

    public void trace_noid(String id, Object... args) {
        if (logger.isTraceEnabled()) {
            logger.trace(createLogMessage_noid(id, args));
        }
    }

    private String createLogMessage(String id, Object... args) {

        return MessageFormat.format(LOG_MESSAGE_FORMAT, id, getMessage(id,
                args));
    }

    private String createLogMessage_noid(String id, Object... args) {

        return getMessage(id, args);
    }

    private String getMessage(String id, Object... args) {
        String message;
        try {
            message = messageSource.getMessage(id, args, Locale.getDefault());
        } catch (NoSuchMessageException e) {
            message = MessageFormat.format(UNDEFINED_MESSAGE_FORMAT, id, Arrays
                    .toString(args));
        }
        return message;
    }

}
