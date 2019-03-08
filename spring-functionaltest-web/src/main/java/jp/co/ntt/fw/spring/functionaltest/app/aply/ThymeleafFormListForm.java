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
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ThymeleafFormListForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ThymeleafForm> thymeleafFormList;

    ThymeleafFormListForm() {
        thymeleafFormList = new ArrayList<ThymeleafForm>();
        for (int i = 0; i < 3; i++) {
            thymeleafFormList.add(new ThymeleafForm());
        }
    }

    public List<ThymeleafForm> getThymeleafFormList() {
        return thymeleafFormList;
    }

    public void setThymeleafFormList(List<ThymeleafForm> thymeleafFormList) {
        this.thymeleafFormList = thymeleafFormList;
    }
}
