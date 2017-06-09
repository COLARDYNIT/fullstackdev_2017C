package com.colardynit.fullstackdev.service.impl;

import com.colardynit.fullstackdev.repository.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

    }

    @Test
    public void findAllByExampleOfNull() throws Exception {

    }
    @Test
    public void findAllByExampleNoResult() throws Exception {

    }


}
