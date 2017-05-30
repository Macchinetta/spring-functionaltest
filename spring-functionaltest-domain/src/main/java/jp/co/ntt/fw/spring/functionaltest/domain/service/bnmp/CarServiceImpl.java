/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp;

import javax.inject.Inject;

import org.dozer.Mapper;
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
