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
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import java.io.Serializable;
import java.util.Date;

public class DeliveryOrderStatusForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String delOrderStatus;

    private String deliveryType;

    private String senderName;

    private String senderAddress;

    private String receiverName;

    private String receiverAddress;

    private Date acceptDateTime;

    private Date completionDateTime;

    private String deliveryDriver;

    private String deliveryStatus;

    private Integer deliverNumber;

    private String matchOption;

    private String escapeSrchVal;

    public String getEscapeSrchVal() {
        return escapeSrchVal;
    }

    public void setEscapeSrchVal(String escapeSrchVal) {
        this.escapeSrchVal = escapeSrchVal;
    }

    public String getMatchOption() {
        return matchOption;
    }

    public void setMatchOption(String matchOption) {
        this.matchOption = matchOption;
    }

    public Integer getDeliverNumber() {
        return deliverNumber;
    }

    public void setDeliverNumber(Integer deliverNumber) {
        this.deliverNumber = deliverNumber;
    }

    public String getDelOrderStatus() {
        return delOrderStatus;
    }

    public void setDelOrderStatus(String delOrderStatus) {
        this.delOrderStatus = delOrderStatus;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Date getAcceptDateTime() {
        return acceptDateTime;
    }

    public void setAcceptDateTime(Date acceptDateTime) {
        this.acceptDateTime = acceptDateTime;
    }

    public Date getCompletionDateTime() {
        return completionDateTime;
    }

    public void setCompletionDateTime(Date completionDateTime) {
        this.completionDateTime = completionDateTime;
    }

    public String getDeliveryDriver() {
        return deliveryDriver;
    }

    public void setDeliveryDriver(String deliveryDriver) {
        this.deliveryDriver = deliveryDriver;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
