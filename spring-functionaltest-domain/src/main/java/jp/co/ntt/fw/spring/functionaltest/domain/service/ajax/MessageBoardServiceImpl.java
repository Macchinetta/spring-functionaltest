/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.ajax;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        messageBoard.setMessageBoardId(messageBoardRepository
                .getMessageBoardSequence().intValue());

        messageBoardRepository.insert(messageBoard);

        return messageBoard;
    }

    @Override
    public void deleteAll() {
        messageBoardRepository.deleteAll();
    }
}
