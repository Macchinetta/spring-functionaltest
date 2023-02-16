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
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.activemq.artemis.jms.client.ActiveMQSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import jakarta.inject.Inject;
import jakarta.jms.BytesMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

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
                public Message createMessage(
                        Session session) throws JMSException {

                    ActiveMQSession activeMQSession = (ActiveMQSession) session;

                    BytesMessage message = activeMQSession.createBytesMessage();
                    message.setObjectProperty("JMS_AMQ_InputStream",
                            inputStream);

                    return message;
                }
            });
        }
    }

}
