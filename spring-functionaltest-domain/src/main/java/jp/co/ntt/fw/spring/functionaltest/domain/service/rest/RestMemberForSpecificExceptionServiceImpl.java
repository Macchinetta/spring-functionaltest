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
package jp.co.ntt.fw.spring.functionaltest.domain.service.rest;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

import jp.co.ntt.fw.spring.functionaltest.domain.message.DomainMessageCodes;
import jp.co.ntt.fw.spring.functionaltest.domain.model.RestMember;

@Service
public class RestMemberForSpecificExceptionServiceImpl
        implements RestMemberForSpecificExceptionService {

    /**
     * callBusinessException
     * <ul>
     * <li>サービスレイヤからBusinessException例外がスローされた場合に、</li>
     * <li>Spring MVCから提供されているControllerAdviceアノテーションを付与で例外をハンドリングの実装を確認するため、</li>
     * <li>サービスレイヤから特定な例外のみをスローされていること</li>
     * </ul>
     */
    @Override
    public void callBusinessException(String memberId) {
        // BusinessException例外をスロー
        throw new BusinessException(
                ResultMessages.error().add(DomainMessageCodes.E_SF_RS_8001, memberId), null);
    }

    /**
     * callOptimisticfailureException
     * <ul>
     * <li>サービスレイヤからObjectOptimisticLockingFailureException例外がスローされた場合に、</li>
     * <li>Spring MVCから提供されているControllerAdviceアノテーションを付与で例外をハンドリングの実装を確認するため、</li>
     * <li>サービスレイヤから特定な例外のみをスローされていること</li>
     * </ul>
     */
    @Override
    public void callOptimisticFailureException(String memberId) {
        // ObjectOptimisticLockingFailureException例外をスロー
        throw new ObjectOptimisticLockingFailureException(RestMember.class, memberId);
    }

    /**
     * callException
     * <ul>
     * <li>サービスレイヤから通常の例外がスローされた場合に、</li>
     * <li>Spring MVCから提供されているControllerAdviceアノテーションを付与で例外をハンドリングの実装を確認するため、</li>
     * <li>サービスレイヤから特定な例外のみをスローされていること</li>
     * </ul>
     */
    @Override
    public void callException(String memberId) {
        // RuntimeException例外をスロー
        throw new RuntimeException("Exception from Service");
    }

}
