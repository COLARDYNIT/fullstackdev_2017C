package com.colardynit.fullstackdev.service.impl;

import com.colardynit.fullstackdev.domain.Car;
import com.colardynit.fullstackdev.repository.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by stijnhaesendonck on 09/06/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplMockTest {
    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private CarServiceImpl carService;

    @Test
    public void findAllByExampleOf() throws Exception {
        ArrayList<Car> cars= new ArrayList<Car>();
        cars.add(new Car());
        cars.add(new Car());
        doReturn(cars).when(carRepository).findAll(any(Example.class));
        List<Car> allByExampleOf = carService.findAllByExampleOf(new Car());

        assertTrue(allByExampleOf.size() == 2);

        verify(carRepository).findAll(any(Example.class));
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    public void findAllByExampleOfNull() throws Exception {
        ArrayList<Car> cars= new ArrayList<Car>();
        cars.add(new Car());
        doReturn(cars).when(carRepository).findAll(any(Example.class));
        List<Car> allByExampleOf = carService.findAllByExampleOf(null);

        assertTrue(allByExampleOf.size() == 1);

        verify(carRepository).findAll(any(Example.class));
        verifyNoMoreInteractions(carRepository);


    }



    @Test
    public void findAllByExampleNoResult() throws Exception {
        ArrayList<Car> cars= new ArrayList<Car>();
        doReturn(cars).when(carRepository).findAll(any(Example.class));
        List<Car> allByExampleOf = carService.findAllByExampleOf(new Car());

        assertTrue(allByExampleOf.isEmpty());

        verify(carRepository).findAll(any(Example.class));
        verifyNoMoreInteractions(carRepository);
    }


}
