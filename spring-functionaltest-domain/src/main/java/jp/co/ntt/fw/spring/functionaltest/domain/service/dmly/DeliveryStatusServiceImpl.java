/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dmly;

import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryStatus;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly.DeliveryStatusRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DeliveryStatusServiceImpl implements DeliveryStatusService {

    @Inject
    DeliveryStatusRepository deliveryStatusRepository;

    public List<DeliveryStatus> findAll() {
        return deliveryStatusRepository.findAll();
    }

}
