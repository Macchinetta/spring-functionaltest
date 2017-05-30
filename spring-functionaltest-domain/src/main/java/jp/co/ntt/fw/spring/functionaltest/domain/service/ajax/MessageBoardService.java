/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.ajax;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.MessageBoard;

public interface MessageBoardService {

    List<MessageBoard> getMessageBoards();

    MessageBoard register(MessageBoard messageBoard);

    void deleteAll();

}
