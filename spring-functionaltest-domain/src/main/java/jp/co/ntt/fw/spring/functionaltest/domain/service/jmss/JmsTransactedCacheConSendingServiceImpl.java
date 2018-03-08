/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

@Transactional("sendJmsTransactionManager")
@Service
public class JmsTransactedCacheConSendingServiceImpl implements
                                                     JmsTransactedCacheConSendingService {

    private static final Logger logger = LoggerFactory.getLogger(
            JmsTransactedCacheConSendingServiceImpl.class);

    @Inject
    JmsSharedService jmsSharedService;

    @Inject
    JmsMessagingTemplate jndiConCacheJmsMessagingTemplate;

    @Override
    public void sendMessage_TxSndOK(String id) {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0601001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        // Todoオブジェクト作成
        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        // メッセージ送信
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0601001",
                jmsTodo);

    }

    @Override
    public void sendMessage_TxSndNG(String id) {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0601002");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        // Todoオブジェクト作成
        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        // メッセージ送信
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0601002",
                jmsTodo);

        // 例外発生
        throw new BusinessException(ResultMessages.error().add(
                "e.sf.jmss.8003"));

    }
}
