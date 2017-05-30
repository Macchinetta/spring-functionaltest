/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.ajax;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.MessageBoard;

public interface MessageBoardRepository {

    List<MessageBoard> findAll();

    long countAll();

    Long getMessageBoardSequence();

    void insert(MessageBoard messageBoard);

    void deleteAll();
}
