/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    public List<String> waitingReceivedMessageAndForList(String jmsTodoId) throws InterruptedException, IOException {

        String filepath = temporaryDirectory + jmsTodoId;

        TimeUnit.MILLISECONDS.sleep(receiveCheckInterval);
        int roopSize = (receiveWaitTime - receiveCheckInterval)
                / receiveCheckInterval;

        List<String> list = null;

        for (int i = 0; i < roopSize; i++) {
            if (jmsSharedService.existsFile(filepath) == true) {
                list = jmsSharedService.readFileToList(filepath);
                if (list != null) {
                    jmsSharedService.deleteFile(filepath);
                    break;
                }
            }
            TimeUnit.MILLISECONDS.sleep(receiveCheckInterval);
        }

        return list != null ? list : null;
    }

    public Map<String, String> waitingReceivedMessageAndForMap(String jmsTodoId) throws InterruptedException, IOException {

        String filepath = temporaryDirectory + jmsTodoId;

        TimeUnit.MILLISECONDS.sleep(receiveCheckInterval);
        int roopSize = (receiveWaitTime - receiveCheckInterval)
                / receiveCheckInterval;

        Map<String, String> map = null;

        for (int i = 0; i < roopSize; i++) {
            if (jmsSharedService.existsFile(filepath) == true) {
                map = jmsSharedService.readFileToMap(filepath);
                if (map != null) {
                    jmsSharedService.deleteFile(filepath);
                    break;
                }
            }
            TimeUnit.MILLISECONDS.sleep(receiveCheckInterval);
        }

        return map != null ? map : null;
    }

    public JmsTodo waitingReceivedMessageAndForJmsTodo(String jmsTodoId) throws InterruptedException, IOException {

        String filepath = temporaryDirectory + jmsTodoId;

        TimeUnit.MILLISECONDS.sleep(receiveCheckInterval);
        int roopSize = (receiveWaitTime - receiveCheckInterval)
                / receiveCheckInterval;

        Object obj = null;

        for (int i = 0; i < roopSize; i++) {
            if (jmsSharedService.existsFile(filepath) == true) {
                obj = jmsSharedService.readFileToObject(filepath);
                if (obj != null) {
                    jmsSharedService.deleteFile(filepath);
                    break;
                }
            }
            TimeUnit.MILLISECONDS.sleep(receiveCheckInterval);
        }

        return obj != null ? (JmsTodo) obj : null;

    }

}
