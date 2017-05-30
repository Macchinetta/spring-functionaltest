/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception;

import org.terasoluna.gfw.common.exception.ResultMessagesNotificationException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

public class IntentionalException extends ResultMessagesNotificationException {

    /**  */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for specify a message.
     * <p>
     * generate a {@link ResultMessages} instance of error type and add a message.
     * </p>
     * @param message result message
     */
    public IntentionalException(String message) {
        super(ResultMessages.error().add(ResultMessage.fromText(message)));
    }

    /**
     * Constructor for specify messages.
     * <p>
     * Takes multiple {@code String} messages as argument.
     * </p>
     * @param message {@link ResultMessages} instance
     */
    public IntentionalException(ResultMessages messages) {
        super(messages);
    }

    /**
     * Constructor for specify messages and exception.
     * <p>
     * Takes multiple {@code String} messages and cause of exception as argument.
     * </p>
     * @param messages {@link ResultMessages} instance
     * @param cause {@link Throwable} instance
     */
    public IntentionalException(ResultMessages messages, Throwable cause) {
        super(messages, cause);
    }

}
