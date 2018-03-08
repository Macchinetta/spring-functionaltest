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
