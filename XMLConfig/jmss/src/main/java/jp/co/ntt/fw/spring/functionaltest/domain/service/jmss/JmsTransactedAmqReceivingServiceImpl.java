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

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

@Transactional("sendJmsTransactionManager")
@Service
public class JmsTransactedAmqReceivingServiceImpl implements JmsTransactedAmqReceivingService {
    private static final Logger logger =
            LoggerFactory.getLogger(JmsTransactedAmqReceivingServiceImpl.class);

    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    @Value("${app.jms.receiveWaitTime}")
    int receiveWaitTime;

    @Value("${app.jms.addReceiveWaitTime}")
    int addReceiveWaitTime;

    @Inject
    JmsSharedService jmsSharedService;

    @Inject
    JmsMessagingTemplate jndiConCacheJmsMessagingTemplate;

    @Override
    public void receiveMessage_TxRcvOK(String id) throws IOException, InterruptedException {

        logger.debug("Received Message![TestQueue0602001] {}", id);

        // メッセージ受信までの間にreceiveWaitTime秒の待ち時間を発生させる
        try {
            TimeUnit.MILLISECONDS.sleep((long) (receiveWaitTime + addReceiveWaitTime));
        } catch (InterruptedException e) {
            logger.warn("InterruptedException Occured", e);
            throw e;
        }

        // メッセージ受信
        JmsTodo jmsTodo = jndiConCacheJmsMessagingTemplate.receiveAndConvert("TestQueue0602001",
                JmsTodo.class);

        // 一時ファイルへ出力
        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo.getJmsTodoId(), jmsTodo);
    }

    @Override
    public void receiveMessage_TxRcvNG(String id) throws InterruptedException {

        logger.debug("Received Message![TestQueue0602002] {}", id);

        // メッセージ受信までの間にreceiveWaitTime秒の待ち時間を発生させる
        try {
            TimeUnit.MILLISECONDS.sleep((long) (receiveWaitTime + addReceiveWaitTime));
        } catch (InterruptedException e) {
            logger.warn("InterruptedException Occured", e);
            throw e;
        }

        // メッセージ受信
        jndiConCacheJmsMessagingTemplate.receiveAndConvert("TestQueue0602002", JmsTodo.class);

        // 例外発生
        throw new BusinessException(ResultMessages.error().add("e.sf.js.8001"));
    }
}
