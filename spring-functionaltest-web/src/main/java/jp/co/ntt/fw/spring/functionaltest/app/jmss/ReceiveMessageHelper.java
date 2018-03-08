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
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsSharedService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReceiveMessageHelper {

    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    @Value("${app.jms.receiveWaitTime}")
    int receiveWaitTime;

    @Value("${app.jms.receiveCheckInterval}")
    int receiveCheckInterval;

    @Inject
    JmsSharedService jmsSharedService;

    public List<String> receivedMessageAndForList(
            String jmsTodoId) throws InterruptedException, IOException {

        String filepath = temporaryDirectory + jmsTodoId;

        List<String> list = null;

        if (jmsSharedService.existsFile(filepath) == true) {
            list = jmsSharedService.readFileToList(filepath);
            if (list != null) {
                jmsSharedService.deleteFile(filepath);
            }
        }

        return list != null ? list : null;
    }

    public Map<String, String> receivedMessageAndForMap(
            String jmsTodoId) throws InterruptedException, IOException {

        String filepath = temporaryDirectory + jmsTodoId;

        Map<String, String> map = null;

        if (jmsSharedService.existsFile(filepath) == true) {
            map = jmsSharedService.readFileToMap(filepath);
            if (map != null) {
                jmsSharedService.deleteFile(filepath);
            }
        }

        return map != null ? map : null;
    }

    public JmsTodo receiveMessagesForJmsTodo(
            String jmsTodoId) throws InterruptedException, IOException {

        String filepath = temporaryDirectory + jmsTodoId;

        Object obj = null;

        if (jmsSharedService.existsFile(filepath) == true) {
            obj = jmsSharedService.readFileToObject(filepath);
            if (obj != null) {
                jmsSharedService.deleteFile(filepath);
            }
        }
        return obj != null ? (JmsTodo) obj : null;
    }

}
