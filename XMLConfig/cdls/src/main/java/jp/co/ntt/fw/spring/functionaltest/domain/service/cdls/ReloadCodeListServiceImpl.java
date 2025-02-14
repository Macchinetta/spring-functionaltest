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
package jp.co.ntt.fw.spring.functionaltest.domain.service.cdls;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.codelist.ReloadableCodeList;
import org.terasoluna.gfw.common.time.ClockFactory;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jp.co.ntt.fw.spring.functionaltest.domain.model.CodeList;
import jp.co.ntt.fw.spring.functionaltest.domain.model.PriceCodeList;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.cdls.UpdateCodelistRepository;

@Service
public class ReloadCodeListServiceImpl implements ReloadCodeListService {

    @Inject
    UpdateCodelistRepository updateCodeListRepository;

    @Inject
    ClockFactory clockFactory;

    @Inject
    @Named(value = "CL_REFRESH_CODELIST")
    ReloadableCodeList codeListItem;

    @Inject
    @Named(value = "CL_RELOADABLE_I18N_DB_PRICE")
    ReloadableCodeList codeListItem2;

    @Override
    public void refresh() {
        codeListItem.refresh();
    }

    @Override
    public void refresh2() {
        codeListItem2.refresh();
    }

    @Override
    @Transactional
    public void updateAuthorityTableValue(CodeList updateCodeList) {
        updateCodeListRepository.updateAuthorityTableValueById(updateCodeList);
    }

    @Override
    @Transactional
    public void updatePriceTableValue(CodeList updateCodeList, Locale locale) {
        PriceCodeList priceCodeList = new PriceCodeList();
        priceCodeList.setLocale(locale.getLanguage());
        priceCodeList.setCode(updateCodeList.getId());
        priceCodeList.setLabel(updateCodeList.getValue());
        updateCodeListRepository.updatePriceTableValueById(priceCodeList);
    }

    @Override
    public String getSystemYear() {
        Clock clock = clockFactory.fixed();
        return String.valueOf(LocalDate.now(clock).getYear());
    }

    @Override
    public ReloadableCodeList getCodeListItem() {
        return codeListItem;
    }
}
