/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;
import java.util.LinkedList;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class NestedCollectionValidationForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Valid
    private LinkedList<AddressForm> addresses;

    public LinkedList<AddressForm> getAddresses() {
        return addresses;
    }

    public void setAddresses(LinkedList<AddressForm> addresses) {
        this.addresses = addresses;
    }
}
