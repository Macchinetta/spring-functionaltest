/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAItem;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPAItemRepository;

@Service
public class JPAItemServiceImpl implements JPAItemService {

    @Inject
    JPAItemRepository jpaItemRepository;

    @Override
    public JPAItem getItemDetail(String itemCode) {
        return jpaItemRepository.findOne(itemCode);
    }

    @Override
    public List<JPAItem> getAll() {
        return jpaItemRepository.findAll();
    }

}
