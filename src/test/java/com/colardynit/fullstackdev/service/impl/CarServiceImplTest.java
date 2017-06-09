package com.colardynit.fullstackdev.service.impl;

import com.colardynit.fullstackdev.Fullstackdev2017CApp;
import com.colardynit.fullstackdev.domain.Brand;
import com.colardynit.fullstackdev.domain.Car;
import com.colardynit.fullstackdev.domain.Category;
import com.colardynit.fullstackdev.repository.BrandRepository;
import com.colardynit.fullstackdev.repository.CarRepository;
import com.colardynit.fullstackdev.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by stijnhaesendonck on 09/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Fullstackdev2017CApp.class)
@Transactional
public class CarServiceImplTest {
    @Resource
    private CarRepository carRepository;
    @Resource
    private BrandRepository brandRepository;
    @Resource
    private CategoryRepository categoryRepository;

    Category sport;
    Category family;
    Category suv;
    Category city;

    Brand porsche;
    Brand mercedes;
    Brand bmw;

    Car cayenne;
    Car M3;
    Car cla_shooting_break;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findAllByExampleOf() throws Exception {

    }

}
