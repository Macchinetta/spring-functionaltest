/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;

public class MessageBoard implements Serializable {

    private static final long serialVersionUID = 1L;

    private int messageBoardId;

    private String comment;

    public int getMessageBoardId() {
        return messageBoardId;
    }

    public void setMessageBoardId(int messageBoardId) {
        this.messageBoardId = messageBoardId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
