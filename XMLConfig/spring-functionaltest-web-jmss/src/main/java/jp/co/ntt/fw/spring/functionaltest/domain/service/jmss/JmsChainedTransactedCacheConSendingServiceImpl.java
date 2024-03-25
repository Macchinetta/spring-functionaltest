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
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

@Transactional("sendChainedTransactionManager")
@Service
public class JmsChainedTransactedCacheConSendingServiceImpl implements
                                                            JmsChainedTransactedCacheConSendingService {

    @Inject
    JmsMessagingTemplate jndiConCacheJmsMessagingTemplate;

    @Inject
    JmsSharedService jmsSharedService;

    @Override
    public void sendMessage_sendTxBestEffort1PhaseOK(String id) {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0604001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        // Todoオブジェクト作成
        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jmsTodo.setDescription(id);
        jmsTodo.setDatetime(LocalDateTime.now());

        // メッセージ送信
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0604001",
                jmsTodo);

        jmsSharedService.insert(jmsTodo);

    }

    @Override
    public void sendMessage_sendTxBestEffort1PhaseNG(String id) {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0604002");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        // Todoオブジェクト作成
        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jmsTodo.setDescription(id);
        jmsTodo.setDatetime(LocalDateTime.now());

        // メッセージ送信
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0604002",
                jmsTodo);

        jmsSharedService.insert(jmsTodo);

        // 例外発生
        throw new BusinessException(ResultMessages.error().add(
                "e.sf.jmss.8003"));

    }
}
