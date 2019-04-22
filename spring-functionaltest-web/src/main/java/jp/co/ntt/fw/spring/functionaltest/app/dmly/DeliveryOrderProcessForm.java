/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dmly;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

public class DeliveryOrderProcessForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer deliveryNo;

    private String deliveryTypeName;

    private String senderName;

    private String senderAddress;

    private String recieverName;

    private String recieverAddress;

    private LocalDateTime acceptDatetime;

    private LocalDateTime completionDatetime;

    private String deliveryDriver;

    private String deliveryStatus;

    private Integer displayDeliveryNo;

    public Integer getDisplayDeliveryNo() {
        return displayDeliveryNo;
    }

    public void setDisplayDeliveryNo(Integer displayDeliveryNo) {
        this.displayDeliveryNo = displayDeliveryNo;
    }

    public Integer getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(Integer deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getDeliveryTypeName() {
        return deliveryTypeName;
    }

    public void setDeliveryTypeName(String deliveryTypeName) {
        this.deliveryTypeName = deliveryTypeName;
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

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public String getRecieverAddress() {
        return recieverAddress;
    }

    public void setRecieverAddress(String recieverAddress) {
        this.recieverAddress = recieverAddress;
    }

    public LocalDateTime getAcceptDatetime() {
        return acceptDatetime;
    }

    public void setAcceptDatetime(LocalDateTime acceptDatetime) {
        this.acceptDatetime = acceptDatetime;
    }

    public LocalDateTime getCompletionDatetime() {
        return completionDatetime;
    }

    public void setCompletionDatetime(LocalDateTime completionDatetime) {
        this.completionDatetime = completionDatetime;
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
