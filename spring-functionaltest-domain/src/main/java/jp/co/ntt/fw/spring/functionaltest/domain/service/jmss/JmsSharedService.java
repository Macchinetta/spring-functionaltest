/*
 * Copyright(c) 2014-2017 NTT Corporation.
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

    void purgeMessageFrom(List<String> destinationNameList, boolean pubsubDomain);

    void deleteDirectory(File file);

    void deleteFile(String path) throws IOException;

    void createTemporaryDirectoryIfNotExists(Path path) throws IOException;

    void createTemporaryDirectoryIfNotExists(String path) throws IOException;

    void deleteTemporaryDirectory(Path path) throws IOException;

    boolean existsFile(String filePath);

    List<String> readFileToList(String path) throws IOException;

    void writeListToAddFile(String dir, String fileName, List<String> inputData) throws IOException, InterruptedException;

    void writeListToFile(String dir, String fileName, List<String> inputData) throws IOException;

    Map<String, String> readFileToMap(String path) throws IOException;

    void writeMapToFile(String dir, String fileName,
            Map<String, String> inputData) throws IOException;

    void writeObjectToFile(String dir, String fileName, Object obj) throws IOException;

    Object readFileToObject(String path) throws IOException;

    List<javax.jms.Message> getMessages(JmsTemplate jmsTemplate,
            String distinationName) throws JMSException;

    List<javax.jms.Message> getMessagesSelected(JmsTemplate jmsTemplate,
            String distinationName, String messageSelector) throws JMSException;

    void insert(JmsTodo jmsTodo);

    JmsTodo find(String jmsTodoId);

    String getTemporaryDirectory();

}
