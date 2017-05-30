/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.bnmp;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Car;

public interface CarRepository {

    Long getCarSequence();

    void insert(Car car);

    Car findOneByCarId(String carId);
}
