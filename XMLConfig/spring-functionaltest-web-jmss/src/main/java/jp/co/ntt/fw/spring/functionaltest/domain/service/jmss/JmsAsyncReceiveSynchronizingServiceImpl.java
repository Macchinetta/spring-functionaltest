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

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.exception.ExceptionLogger;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

@Service
public class JmsAsyncReceiveSynchronizingServiceImpl implements
                                                     JmsAsyncReceiveSynchronizingService {
    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    @Inject
    ExceptionLogger exceptionLogger;

    private final ConcurrentMap<String, CountDownLatch> latchMap = new ConcurrentHashMap<>();

    /**
     * latchを作成してmapに格納しカウントダウンする<br/>
     * 画面操作が先行していて既にlatchがある場合はカウントダウンだけする
     */
    @Override
    @EventListener
    public void executeAfterReceivedId(ReceivedEvent<String> event) {
        createLatchIfAbsent(event.getPayload()).countDown();
    }

    @Override
    @EventListener
    public void executeAfterReceivedTodo(ReceivedEvent<JmsTodo> event) {
        createLatchIfAbsent(event.getPayload().getJmsTodoId()).countDown();
    }

    /**
     * 受信待ちをする<br/>
     * latchがmapに無い場合はlatchを作成しカウントダウンされるのを待つ
     */
    @Override
    public void await(String jmsTodoId) {
        try {
            createLatchIfAbsent(jmsTodoId).await(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            exceptionLogger.error(e);
            Thread.currentThread().interrupt();
        } finally {
            latchMap.remove(jmsTodoId);
        }
    }

    @Override
    public void deleteTempFilesAndClearMap() throws IOException {
        latchMap.clear();
        Path uploadTemporaryDirectoryPath = Paths.get(temporaryDirectory);
        if (Files.exists(uploadTemporaryDirectoryPath)) {
            Files.walkFileTree(uploadTemporaryDirectoryPath,
                    new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path path,
                                BasicFileAttributes attributes) throws IOException {
                            Files.delete(path);
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path path,
                                IOException exception) throws IOException {
                            return FileVisitResult.CONTINUE;
                        }
                    });
        }
    }

    private CountDownLatch createLatchIfAbsent(String jmsTodoId) {
        CountDownLatch latch = latchMap.get(jmsTodoId);
        if (latch == null) {
            CountDownLatch newLatch = new CountDownLatch(1);
            CountDownLatch existingLatch = latchMap.putIfAbsent(jmsTodoId,
                    newLatch);
            latch = (existingLatch == null) ? newLatch : existingLatch;
        }
        return latch;
    }
}
