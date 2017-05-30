/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Car;

public interface CarService {

    Car createCar(CarUnidirectionalDto car);

    Car createCar(CarBidirectionalDto car);

    CarUnidirectionalDto getCarWithUnidirectionalDto(String carId);

    CarBidirectionalDto getCarWithBidirectionalDto(String carId);
}
