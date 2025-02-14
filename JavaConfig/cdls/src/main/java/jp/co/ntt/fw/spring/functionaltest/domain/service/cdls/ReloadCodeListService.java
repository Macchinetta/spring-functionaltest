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

import java.util.Locale;
import org.terasoluna.gfw.common.codelist.ReloadableCodeList;
import jp.co.ntt.fw.spring.functionaltest.domain.model.CodeList;

public interface ReloadCodeListService {

    void refresh();

    void refresh2();

    void updateAuthorityTableValue(CodeList updateCodeList);

    void updatePriceTableValue(CodeList updateCodeList, Locale locale);

    String getSystemYear();

    ReloadableCodeList getCodeListItem();
}
