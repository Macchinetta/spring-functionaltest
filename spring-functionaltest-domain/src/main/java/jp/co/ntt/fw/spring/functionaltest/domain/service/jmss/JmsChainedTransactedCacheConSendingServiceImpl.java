/*
 * Copyright(c) 2014-2017 NTT Corporation.
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

@Transactional("sendChainedTransactionManager")
@Service
public class JmsChainedTransactedCacheConSendingServiceImpl implements
                                                           JmsChainedTransactedCacheConSendingService {

    private static final Logger logger = LoggerFactory
            .getLogger(JmsChainedTransactedCacheConSendingServiceImpl.class);

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
        jmsTodo.setDatetime(DateTime.now());

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
        jmsTodo.setDatetime(DateTime.now());

        // メッセージ送信
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0604002",
                jmsTodo);

        jmsSharedService.insert(jmsTodo);

        // 例外発生
        throw new BusinessException(ResultMessages.error()
                .add("e.sf.jmss.8003"));

    }
}
