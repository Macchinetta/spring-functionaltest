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
package jp.co.ntt.fw.spring.functionaltest.domain.service.ajax;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.MessageBoard;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.ajax.MessageBoardRepository;

@Transactional
@Service
public class MessageBoardServiceImpl implements MessageBoardService {

    @Inject
    MessageBoardRepository messageBoardRepository;

    @Override
    public List<MessageBoard> getMessageBoards() {

        List<MessageBoard> messageBoards = messageBoardRepository.findAll();

        return messageBoards;
    }

    @Override
    public MessageBoard register(MessageBoard messageBoard) {

        messageBoard.setMessageBoardId(messageBoardRepository.getMessageBoardSequence().intValue());

        messageBoardRepository.insert(messageBoard);

        return messageBoard;
    }

    @Override
    public void deleteAll() {
        messageBoardRepository.deleteAll();
    }
}
