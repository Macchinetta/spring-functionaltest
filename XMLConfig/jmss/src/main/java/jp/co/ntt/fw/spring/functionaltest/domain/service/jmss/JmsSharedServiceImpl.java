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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.SystemException;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.QueueBrowser;
import jakarta.jms.Session;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.jmss.JmsTodoRepository;

@Service
public class JmsSharedServiceImpl implements JmsSharedService {

    private static final Logger logger = LoggerFactory.getLogger(JmsSharedServiceImpl.class);

    @Value("${app.jms.receiveWaitTime}")
    int receiveWaitTime;

    @Inject
    JmsTemplate queuePurgeJmsTemplate;

    @Inject
    JmsTemplate topicPurgeJmsTemplate;

    @Inject
    JmsTodoRepository jmsTodoRepository;

    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    public void purgeMessageFrom(List<String> destinationNameList, boolean pubsubDomain) {
        JmsTemplate jmsTemplateToUse = null;
        if (pubsubDomain) {
            jmsTemplateToUse = topicPurgeJmsTemplate;
        } else {
            jmsTemplateToUse = queuePurgeJmsTemplate;
        }

        for (String destinationName : destinationNameList) {
            logger.debug("Purge Destination Name: {}", destinationName);
            int i = 0;
            while (jmsTemplateToUse.receive(destinationName) != null) {
                i++;
            }
            logger.debug("Purged count: {}", i);
        }
    }

    public void deleteDirectory(File file) {
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            for (File currentFile : fileList) {
                deleteDirectory(currentFile);
            }
        }
    }

    public void deleteFile(String path) throws IOException {
        // deleteメソッドによる削除の成功失敗によってその後のアクションをとることは無いため、SonarQube指摘は未対応としています。
        new File(path).delete();
    }

    public void createTemporaryDirectoryIfNotExists(Path path) throws IOException {
        if (Files.exists(path) == false) {
            Files.createDirectories(path);
        }
    }

    public void createTemporaryDirectoryIfNotExists(String path) throws IOException {
        File file = new File(path);
        file.mkdirs();
    }

    public void deleteTemporaryDirectory(Path path) throws IOException {
        Files.delete(path);
    }

    public boolean existsFile(String filePath) {
        return new File(filePath).exists();
    }

    public List<String> readFileToList(String path) throws IOException {

        List<String> outputData = new ArrayList<String>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                outputData.add(line);
            }
        }
        return outputData;
    }

    public void writeListToAddFile(String dir, String fileName, List<String> inputData)
            throws IOException, InterruptedException {

        createTemporaryDirectoryIfNotExists(dir);

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(dir + fileName),
                StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            for (String s : inputData) {
                bw.write(s + System.lineSeparator());
            }
        }
    }

    public void writeListToFile(String dir, String fileName, List<String> inputData)
            throws IOException {

        createTemporaryDirectoryIfNotExists(dir);

        try (BufferedWriter bw =
                Files.newBufferedWriter(Paths.get(dir + fileName), StandardCharsets.UTF_8)) {
            for (String s : inputData) {
                bw.write(s + System.lineSeparator());
            }
        }
    }

    public Map<String, String> readFileToMap(String path) throws IOException {

        Map<String, String> outputData = new HashMap<String, String>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(",");
                outputData.put(s[0], s[1]);
            }
        }
        return outputData;
    }

    public void writeMapToFile(String dir, String fileName, Map<String, String> inputData)
            throws IOException {

        createTemporaryDirectoryIfNotExists(dir);

        try (BufferedWriter bw =
                Files.newBufferedWriter(Paths.get(dir + fileName), StandardCharsets.UTF_8)) {
            for (Iterator<Entry<String, String>> iterator =
                    inputData.entrySet().iterator(); iterator.hasNext();) {
                Entry<String, String> entry = iterator.next();

                StringBuilder sb = new StringBuilder();
                sb.append(entry.getKey());
                sb.append(",");
                sb.append(entry.getValue());
                bw.write(sb.toString() + System.lineSeparator());
            }
        }
    }

    public void writeValidObjectToFile(String dir, String fileName, Object obj) throws IOException {
        writeObjectToFile(dir, fileName, obj);
    }

    public void writeObjectToFile(String dir, String fileName, Object obj) throws IOException {

        createTemporaryDirectoryIfNotExists(dir);

        try (ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream(dir + fileName))) {
            out.writeObject(obj);
        }
    }

    public Object readFileToObject(String path) throws IOException {

        Object data = null;
        try (FileInputStream fis = new FileInputStream(new File(path));
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            data = (Object) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new SystemException("e.sf.js.8000", e);
        }
        return data;
    }

    public List<jakarta.jms.Message> getMessages(JmsTemplate jmsTemplate, String distinationName)
            throws JMSException {

        // JMSからメッセージ件数取得
        return getMessagesSelected(jmsTemplate, distinationName, null);

    }

    public List<jakarta.jms.Message> getMessagesSelected(JmsTemplate jmsTemplate,
            String distinationName, String messageSelector) throws JMSException {

        // JMSからメッセージ件数取得
        return jmsTemplate.browseSelected(distinationName, messageSelector,
                new BrowserCallback<List<jakarta.jms.Message>>() {

                    @Override
                    public List<jakarta.jms.Message> doInJms(Session session, QueueBrowser browser)
                            throws JMSException {
                        List<jakarta.jms.Message> list = new ArrayList<jakarta.jms.Message>();
                        Enumeration<?> messages = browser.getEnumeration();
                        while (messages.hasMoreElements()) {
                            list.add((jakarta.jms.Message) messages.nextElement());
                        }
                        return list;
                    }
                });

    }

    @Override
    @Transactional(value = "transactionManager")
    public void insert(JmsTodo jmsTodo) {
        jmsTodoRepository.insert(jmsTodo);
    }

    public String getTemporaryDirectory() {
        return temporaryDirectory;
    }

    @Override
    public JmsTodo find(String jmsTodoId) {
        return jmsTodoRepository.findById(jmsTodoId);
    }

}
