/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.cspr;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Committer;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.cspr.CommitterCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.cspr.CommitterRepository;

@Transactional
@Service
public class CommitterServiceImpl implements CommitterService {

    @Inject
    CommitterRepository committerRepository;

    @Override
    public List<Committer> getCommitters(CommitterCriteria criteria) {

        List<Committer> committers = committerRepository
                .findCommiterByCriteria(criteria);

        return committers;
    }

    @Override
    public Committer update(Committer committer) {

        committerRepository.update(committer);

        return committer;
    }

}
