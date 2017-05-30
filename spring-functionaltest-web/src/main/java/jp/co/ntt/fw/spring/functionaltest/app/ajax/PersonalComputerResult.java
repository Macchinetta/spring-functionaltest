/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import java.io.Serializable;
import java.util.List;

public class PersonalComputerResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
