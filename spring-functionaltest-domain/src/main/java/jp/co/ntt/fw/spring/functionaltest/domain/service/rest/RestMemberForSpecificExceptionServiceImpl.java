/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.rest;

import jp.co.ntt.fw.spring.functionaltest.domain.message.DomainMessageCodes;
import jp.co.ntt.fw.spring.functionaltest.domain.model.RestMember;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

@Service
public class RestMemberForSpecificExceptionServiceImpl implements
                                                      RestMemberForSpecificExceptionService {

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
        throw new BusinessException(ResultMessages.error().add(
                DomainMessageCodes.E_SF_MM_8001, memberId), null);
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
