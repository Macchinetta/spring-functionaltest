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
