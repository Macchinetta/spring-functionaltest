/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsJndiConSendingServiceImpl implements JmsJndiConSendingService {

    @Inject
    JmsMessagingTemplate jndiJmsMessagingTemplate;

    @Inject
    JmsSharedService jmsSharedService;

    @Override
    public void sendMessageByJndiCon(final String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<String>();
        destinationNameList.add("TestQueue0102001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jndiJmsMessagingTemplate.convertAndSend("TestQueue0102001", jmsTodo);

    }

}
