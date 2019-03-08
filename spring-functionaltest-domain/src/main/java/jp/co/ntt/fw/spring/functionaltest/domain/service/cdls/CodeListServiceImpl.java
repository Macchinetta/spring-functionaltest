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
package jp.co.ntt.fw.spring.functionaltest.domain.service.cdls;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.codelist.CodeList;
import org.terasoluna.gfw.common.codelist.i18n.SimpleI18nCodeList;

@Service
public class CodeListServiceImpl implements CodeListService {

    @Inject
    @Named("CL_ORDERSTATUS")
    private CodeList orderStatusCodeList;

    @Inject
    @Named("CL_MONTH_ASC")
    private CodeList monthAscCodeList;

    @Inject
    @Named("CL_AUTHORITIES")
    private CodeList authoritiesCodeList;

    @Inject
    @Named("CL_ENUM_ORDERSTATUS")
    private CodeList enumOrderStatusCodeList;

    @Inject
    @Named("CL_I18N_PRICE")
    private CodeList i18nPriceCodeList;

    @Inject
    @Named("CL_I18N_DB_PRICE")
    private CodeList i18nDBPriceCodeList;

    @Override
    public String getOrderStatusCodeListValue(String key) {
        return this.nullToEmptyString(orderStatusCodeList.asMap().get(key));
    }

    @Override
    public String getMonthAscCodeListValue(String key) {
        return this.nullToEmptyString(monthAscCodeList.asMap().get(key));
    }

    @Override
    public String getAuthoritiesCodeListValue(String key) {
        return this.nullToEmptyString(authoritiesCodeList.asMap().get(key));
    }

    @Override
    public String getEnumOrderStatusCodeListValue(String key) {
        return this.nullToEmptyString(enumOrderStatusCodeList.asMap().get(key));
    }

    @Override
    public String getI18nPriceCodeListValue(String key, Locale locale) {
        return this.nullToEmptyString(((SimpleI18nCodeList) i18nPriceCodeList)
                .asMap(locale).get(key));
    }

    @Override
    public String getI18nDBPriceCodeListValue(String key, Locale locale) {
        return this.nullToEmptyString(((SimpleI18nCodeList) i18nDBPriceCodeList)
                .asMap(locale).get(key));
    }

    private String nullToEmptyString(String str) {
        if (str == null) {
            str = "";
        }
        return str;
    }

}
