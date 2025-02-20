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
package jp.co.ntt.fw.spring.functionaltest.domain.service.msmn;

import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessages;

@Service
public class MsmnUserServiceImpl implements MsmnUserService {

    @Override
    public void create() {
        ResultMessages messages = ResultMessages.error().add("e.ex.an.9001");
        throw new BusinessException(messages);
    }

    @Override
    public void update() {
        ResultMessages messages = ResultMessages.error().add("e.ex.an.9001");
        throw new ResourceNotFoundException(messages);
    }

}
