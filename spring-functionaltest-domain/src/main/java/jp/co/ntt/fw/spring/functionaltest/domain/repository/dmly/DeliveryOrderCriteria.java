/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly;

import java.io.Serializable;
import java.util.Date;

public class DeliveryOrderCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date fromAcceptDatetime;

    private Date toAcceptDatetime;

    private Date updateCompletionDatetime;

    public Date getFromAcceptDatetime() {
        return fromAcceptDatetime;
    }

    public void setFromAcceptDatetime(Date fromAcceptDatetime) {
        this.fromAcceptDatetime = fromAcceptDatetime;
    }

    public Date getToAcceptDatetime() {
        return toAcceptDatetime;
    }

    public void setToAcceptDatetime(Date toAcceptDatetime) {
        this.toAcceptDatetime = toAcceptDatetime;
    }

    public Date getUpdateCompletionDatetime() {
        return updateCompletionDatetime;
    }

    public void setUpdateCompletionDatetime(Date updateCompletionDatetime) {
        this.updateCompletionDatetime = updateCompletionDatetime;
    }

}
