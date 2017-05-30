/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dmly;

import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryType;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly.DeliveryTypeRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DeliveryTypeServiceImpl implements DeliveryTypeService {

    @Inject
    DeliveryTypeRepository deliveryTypeRepository;

    public List<DeliveryType> findAll() {
        return deliveryTypeRepository.findAll();
    }

}
