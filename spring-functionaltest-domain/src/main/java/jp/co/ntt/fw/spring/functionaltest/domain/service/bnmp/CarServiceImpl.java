/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp;

import javax.inject.Inject;

import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Car;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.bnmp.CarRepository;

@Transactional
@Service
public class CarServiceImpl implements CarService {

    @Inject
    CarRepository carRepository;

    @Inject
    Mapper beanMapper;

    @Override
    public Car createCar(CarUnidirectionalDto dto) {
        dto.setCarId(String.format("C%09d", carRepository.getCarSequence()));
        Car car = beanMapper.map(dto, Car.class);
        carRepository.insert(car);
        return car;
    }

    @Override
    public Car createCar(CarBidirectionalDto dto) {
        dto.setCarId(String.format("C%09d", carRepository.getCarSequence()));
        Car car = beanMapper.map(dto, Car.class);
        carRepository.insert(car);
        return car;
    }

    @Override
    public CarUnidirectionalDto getCarWithUnidirectionalDto(String carId) {
        Car car = carRepository.findOneByCarId(carId);
        if (car == null) {
            throw new ResourceNotFoundException(String.format(
                    "Specified car is not found. carId is '%s'.", carId));
        }

        return beanMapper.map(car, CarUnidirectionalDto.class);
    }

    @Override
    public CarBidirectionalDto getCarWithBidirectionalDto(String carId) {
        Car car = carRepository.findOneByCarId(carId);
        if (car == null) {
            throw new ResourceNotFoundException(String.format(
                    "Specified car is not found. carId is '%s'.", carId));
        }

        return beanMapper.map(car, CarBidirectionalDto.class);
    }
}
