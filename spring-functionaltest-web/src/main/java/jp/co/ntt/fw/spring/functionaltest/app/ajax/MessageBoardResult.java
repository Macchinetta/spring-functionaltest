/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageBoardResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> resultMessages;

    public List<String> getResultMessages() {
        return resultMessages;
    }

    public void setResultMessages(List<String> resultMessages) {
        this.resultMessages = resultMessages;
    }

}
