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
import java.util.List;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.BlobMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class JmsAmqSendingServiceImpl implements JmsAmqSendingService {

    @Inject
    JmsMessagingTemplate dynamicJmsMessagingTemplate;

    @Inject
    JmsTemplate dynamicJmsTemplate;

    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    @Inject
    JmsSharedService jmsSharedService;

    @Override
    public void sendMessageForAmq(final String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<String>();
        destinationNameList.add("TestQueue0101001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        dynamicJmsMessagingTemplate.convertAndSend("TestQueue0101001", jmsTodo);

    }

    @Override
    public void sendBlobMessage(final String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<String>();
        destinationNameList.add("TestQueue0901001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        List<String> arrayStr = new ArrayList<>();
        arrayStr.add(id);
        jmsSharedService.writeListToFile(temporaryDirectory, "input_" + id,
                arrayStr);

        Path path = Paths.get(temporaryDirectory + "input_" + id);
        try (final InputStream inputStream = Files.newInputStream(path)) {

            dynamicJmsTemplate.send("TestQueue0901001", new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {

                    ActiveMQSession activeMQSession = (ActiveMQSession) session;

                    BlobMessage blobMessage = activeMQSession
                            .createBlobMessage(inputStream);
                    return blobMessage;
                }
            });
        }
    }

}
