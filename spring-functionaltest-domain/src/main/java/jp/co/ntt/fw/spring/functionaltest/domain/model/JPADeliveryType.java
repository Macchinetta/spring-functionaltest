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
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author btundes
 */

@Entity
@Table(name = "m_delivery_type")
public class JPADeliveryType {
    /**
     * delivery_type_id INTEGER ,delivery_type_name VARCHAR(1024) ,CONSTRAINT pk_delivery_type PRIMARY KEY (delivery_type_id) );
     */

    @Id
    @Column(name = "delivery_type_id", nullable = false)
    private Integer deliveryTypeId;

    @Column(name = "delivery_type_name", nullable = true, length = 1024)
    private String deliveryTypeName;

    public Integer getDeliveryTypeId() {
        return deliveryTypeId;
    }

    public void setDeliveryTypeId(Integer deliveryTypeId) {
        this.deliveryTypeId = deliveryTypeId;
    }

    public String getDeliveryTypeName() {
        return deliveryTypeName;
    }

    public void setDeliveryTypeName(String deliveryTypeName) {
        this.deliveryTypeName = deliveryTypeName;
    }

}
