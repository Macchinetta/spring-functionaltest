/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.cspr;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Committer;

public interface CommitterRepository {

    Committer findOneByName(String name);

    long countByCriteria(CommitterCriteria committerCriteria);

    List<Committer> findCommiterByCriteria(CommitterCriteria committerCriteria);

    void update(Committer committer);

}
