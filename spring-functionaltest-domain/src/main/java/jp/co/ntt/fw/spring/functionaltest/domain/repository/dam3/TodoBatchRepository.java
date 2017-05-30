/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TodoBatchRepository {

    int updateFinishedByTodIds(@Param("finished") boolean finished,
            @Param("todoIds") List<String> todoIds);

}
