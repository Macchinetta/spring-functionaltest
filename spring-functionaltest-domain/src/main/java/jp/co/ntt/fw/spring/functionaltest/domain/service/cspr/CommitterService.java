/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.cspr;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Committer;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.cspr.CommitterCriteria;

public interface CommitterService {

    List<Committer> getCommitters(CommitterCriteria criteria);

    Committer update(Committer committer);

}
