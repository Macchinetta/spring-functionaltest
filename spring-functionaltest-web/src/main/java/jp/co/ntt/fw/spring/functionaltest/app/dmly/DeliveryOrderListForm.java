/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dmly;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

public class DeliveryOrderListForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDateTime fromAcceptDatetime;

    private LocalDateTime toAcceptDatetime;

    private LocalDateTime updateCompletionDatetime;

    public LocalDateTime getFromAcceptDatetime() {
        return fromAcceptDatetime;
    }

    public void setFromAcceptDatetime(LocalDateTime fromAcceptDatetime) {
        this.fromAcceptDatetime = fromAcceptDatetime;
    }

    public LocalDateTime getToAcceptDatetime() {
        return toAcceptDatetime;
    }

    public void setToAcceptDatetime(LocalDateTime toAcceptDatetime) {
        this.toAcceptDatetime = toAcceptDatetime;
    }

    public LocalDateTime getUpdateCompletionDatetime() {
        return updateCompletionDatetime;
    }

    public void setUpdateCompletionDatetime(
            LocalDateTime updateCompletionDatetime) {
        this.updateCompletionDatetime = updateCompletionDatetime;
    }

}
