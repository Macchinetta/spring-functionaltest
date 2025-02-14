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
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl;

import jakarta.validation.constraints.NotNull;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

public interface HttpsRestClientService {

    UserResource connectHttps(@NotNull SERVERS servers, @NotNull DESTINATION destination,
            boolean isCausesSoTimeout);

    enum SERVERS {
        SERVER1, SERVER2, SERVER3, SERVER4, NOTEXISTS
    }

    enum DESTINATION {
        RSCL1, RSCL2, RSCL3, RSCL4
    }
}
