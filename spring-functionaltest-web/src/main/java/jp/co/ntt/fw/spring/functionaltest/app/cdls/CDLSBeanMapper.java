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
package jp.co.ntt.fw.spring.functionaltest.app.cdls;

import org.mapstruct.Mapper;

import jp.co.ntt.fw.spring.functionaltest.domain.model.CodeList;

@Mapper
public interface CDLSBeanMapper {

    // CDLS0105Controller
    CodeList map(ClI18nPriceForm clI18nPriceForm);

    CodeList map(ClI18nDBPriceForm clI18nDBPriceForm);

    // CDLS01Controller
    CodeList map(ClOrderStatusForm clOrderStatusForm);

    CodeList map(ClMonthAscForm clMonthAscForm);

    CodeList map(ClAuthoritiesForm clAuthoritiesForm);

    CodeList map(ClEnumOrderStatusForm clEnumOrderStatusForm);

    // CDLS02Controller
    CodeList map(UpdateCodeListForm updateCodeListForm);

}
