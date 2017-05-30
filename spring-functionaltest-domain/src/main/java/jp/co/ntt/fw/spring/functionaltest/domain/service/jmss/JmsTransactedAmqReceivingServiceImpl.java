/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.jms.JMSException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.jmss.JmsTodoRepository;

import org.apache.activemq.BlobMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

@Transactional("sendJmsTransactionManager")
@Service
public class JmsTransactedAmqReceivingServiceImpl implements
                                                 JmsTransactedAmqReceivingService {
    private static final Logger logger = LoggerFactory
            .getLogger(JmsTransactedAmqReceivingServiceImpl.class);

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
    public void receiveMessage_TxRcvOK(String id) throws IOException {

        logger.debug("Received Message![TestQueue0602001] {}", id);

        // メッセージ受信までの間にreceiveWaitTime秒の待ち時間を発生させる
        try {
            TimeUnit.MILLISECONDS.sleep(receiveWaitTime + addReceiveWaitTime);
        } catch (InterruptedException e) {
            logger.warn("InterruptedException Occured", e);
        }

        // メッセージ受信
        JmsTodo jmsTodo = jndiConCacheJmsMessagingTemplate.receiveAndConvert(
                "TestQueue0602001", JmsTodo.class);

        // 一時ファイルへ出力
        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);
    }

    @Override
    public void receiveMessage_TxRcvNG(String id) {

        logger.debug("Received Message![TestQueue0602002] {}", id);

        // メッセージ受信までの間にreceiveWaitTime秒の待ち時間を発生させる
        try {
            TimeUnit.MILLISECONDS.sleep(receiveWaitTime + addReceiveWaitTime);
        } catch (InterruptedException e) {
            logger.warn("InterruptedException Occured", e);
        }

        // メッセージ受信
        jndiConCacheJmsMessagingTemplate.receiveAndConvert("TestQueue0602002",
                JmsTodo.class);

        // 例外発生
        throw new BusinessException(ResultMessages.error()
                .add("e.sf.jmss.8001"));
    }
}
