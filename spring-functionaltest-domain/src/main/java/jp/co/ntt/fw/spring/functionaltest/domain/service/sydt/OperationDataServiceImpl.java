/*
 * Copyright(c) 2025 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.domain.service.sydt;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.sydt.SydtRepository;

@Transactional
@Service
public class OperationDataServiceImpl implements OperationDataService {

    @Inject
    private SydtRepository sydtRepository;

    @Override
    public int select() {
        Integer result = this.sydtRepository.selectDiff();
        return result.intValue();
    }

    @Override
    public void update(int diffValue) {
        this.sydtRepository.updateOperationDate(diffValue);
    }

}
