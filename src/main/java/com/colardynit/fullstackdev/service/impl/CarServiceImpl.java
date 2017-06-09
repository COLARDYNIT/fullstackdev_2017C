package com.colardynit.fullstackdev.service.impl;

import com.colardynit.fullstackdev.domain.Car;
import com.colardynit.fullstackdev.repository.CarRepository;
import com.colardynit.fullstackdev.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;


/**
 * Service Implementation for managing Car.
 */
@Service
@Transactional
public class CarServiceImpl implements CarService{

    private final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Save a car.
     *
     * @param car the entity to save
     * @return the persisted entity
     */
    @Override
    public Car save(Car car) {
        log.debug("Request to save Car : {}", car);
        return carRepository.save(car);
    }

    /**
     *  Get all the cars.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Car> findAll(Pageable pageable) {
        log.debug("Request to get all Cars");
        return carRepository.findAll(pageable);
    }

    /**
     *  Get one car by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Car findOne(Long id) {
        log.debug("Request to get Car : {}", id);
        return carRepository.findOneWithEagerRelationships(id);
    }

    /**
     *  Delete the  car by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Car : {}", id);
        carRepository.delete(id);
    }

    @Override
    public List<Car> findAllByExampleOf(Car car) {
        if (car == null) {
            car = new Car();
            car.setAvailable(true); //only show the available cars
        }
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues()
            .withMatcher("name", contains().ignoreCase())
            .withMatcher("brand", exact())
            .withMatcher("available", exact())
            .withMatcher("categories", contains());
        Example<Car> example = Example.of(car, matcher);

        List<Car> cars = carRepository.findAll(example);
        return cars;
    }
}
