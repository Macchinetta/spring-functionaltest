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

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

import org.springframework.jms.core.JmsTemplate;

public interface JmsSharedService {

    static String UUID_KEY = "UUID_KEY";

    static String HEADER_KEY1 = "HEADER_KEY1";

    static String RECEIVE_QUEUE = "RECEIVE_QUEUE";

    static String ERROR_KEY1 = "ERROR_KEY1";

    static String DELIVERY_MODE = "DELIVERY_MODE";

    static String PRIORITY = "PRIORITY";

    static String TTL = "TTL";

    void purgeMessageFrom(List<String> destinationNameList,
            boolean pubsubDomain);

    void deleteDirectory(File file);

    void deleteFile(String path) throws IOException;

    void createTemporaryDirectoryIfNotExists(Path path) throws IOException;

    void createTemporaryDirectoryIfNotExists(String path) throws IOException;

    void deleteTemporaryDirectory(Path path) throws IOException;

    boolean existsFile(String filePath);

    List<String> readFileToList(String path) throws IOException;

    void writeListToAddFile(String dir, String fileName,
            List<String> inputData) throws IOException, InterruptedException;

    void writeListToFile(String dir, String fileName,
            List<String> inputData) throws IOException;

    Map<String, String> readFileToMap(String path) throws IOException;

    void writeMapToFile(String dir, String fileName,
            Map<String, String> inputData) throws IOException;

    void writeObjectToFile(String dir, String fileName,
            Object obj) throws IOException;

    Object readFileToObject(String path) throws IOException;

    List<javax.jms.Message> getMessages(JmsTemplate jmsTemplate,
            String distinationName) throws JMSException;

    List<javax.jms.Message> getMessagesSelected(JmsTemplate jmsTemplate,
            String distinationName, String messageSelector) throws JMSException;

    void insert(JmsTodo jmsTodo);

    JmsTodo find(String jmsTodoId);

    String getTemporaryDirectory();

}
