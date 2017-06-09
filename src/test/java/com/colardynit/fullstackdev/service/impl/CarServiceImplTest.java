package com.colardynit.fullstackdev.service.impl;

import com.colardynit.fullstackdev.Fullstackdev2017CApp;
import com.colardynit.fullstackdev.domain.Brand;
import com.colardynit.fullstackdev.domain.Car;
import com.colardynit.fullstackdev.domain.Category;
import com.colardynit.fullstackdev.domain.enumeration.Type;
import com.colardynit.fullstackdev.repository.BrandRepository;
import com.colardynit.fullstackdev.repository.CarRepository;
import com.colardynit.fullstackdev.repository.CategoryRepository;
import com.colardynit.fullstackdev.service.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

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
    @Resource
    private CarService carService;

    Category categorySport;
    Category categoryFamily;
    Category categorySuv;
    Category categoryCity;

    Set<Category> breakCategory;
    Set<Category> sportCategory;

    Brand brandPorsche;
    Brand brandmercedes;
    Brand brandBmw;

    Car cayenne;
    Car m3;
    Car cla_shooting_break;

    @Before
    public void setUp() throws Exception {
        categoryRepository.deleteAll();
        brandRepository.deleteAll();
        carRepository.deleteAll();

        Category sport = new Category();
        sport.setType(Type.SPORT);
        Category family = new Category();
        family.setType(Type.FAMILY);
        Category suv = new Category();
        suv.setType(Type.SUV);
        Category city = new Category();
        city.setType(Type.CITY);

        categorySport = categoryRepository.save(sport);
        categoryFamily = categoryRepository.save(family);
        categorySuv = categoryRepository.save(suv);
        categoryCity = categoryRepository.save(city);

        Brand porsche = new Brand();
        porsche.setName("Porsche");
        Brand mercedes = new Brand();
        mercedes.setName("Mercedes");
        Brand bmw = new Brand();
        bmw.setName("BMW");

        brandPorsche = brandRepository.save(porsche);
        brandmercedes = brandRepository.save(mercedes);
        brandBmw = brandRepository.save(bmw);

        cayenne = new Car();
        m3 = new Car();
        cla_shooting_break = new Car();

        breakCategory = new HashSet<>();
        breakCategory.add(categoryCity);
        breakCategory.add(categoryFamily);

        sportCategory = new HashSet<>();
        sportCategory.add(categorySport);


        cayenne.setBrand(brandPorsche);
        cayenne.setModel("Cayenne");
        cayenne.setAvailable(false);

        m3.setBrand(brandBmw);
        m3.setModel("M3");
        m3.setCategories(sportCategory);
        m3.setAvailable(true);

        cla_shooting_break.setBrand(brandmercedes);
        cla_shooting_break.setModel("Cla");
        cla_shooting_break.setCategories(breakCategory);
        cla_shooting_break.setAvailable(true);

        carRepository.save(cayenne);
        carRepository.save(m3);
        carRepository.save(cla_shooting_break);


    }

    @Test
    public void findAllByExampleOf() throws Exception {
        List<Car> allByExampleOfCayenne = carService.findAllByExampleOf(cayenne);
        List<Car> allByExampleOfCla = carService.findAllByExampleOf(cla_shooting_break);
        Car car = new Car();
        List<Car> allByExampleOfAvailable = carService.findAllByExampleOf(car);

        assertTrue(allByExampleOfCayenne.isEmpty());
        assertTrue(allByExampleOfCla.contains(cla_shooting_break));
        assertTrue(allByExampleOfAvailable.size() == 2);


    }

    @Test
    public void findAllByBrand () throws Exception{
        Car newCar1 = new Car();
        newCar1.setBrand(brandBmw);
        Car newCar2 = new Car();
        newCar2.setBrand(brandmercedes);
        Car newCar3 = new Car();
        newCar3.setBrand(brandPorsche);

        List<Car> findAllByBmw = carService.findAllByExampleOf(newCar1);
        List<Car> findAllByMercedes = carService.findAllByExampleOf(newCar2);
        List<Car> findAllByPorsche = carService.findAllByExampleOf(newCar3);

        assertTrue(findAllByBmw.size() == 1);
        assertTrue(findAllByMercedes.size() == 1);
        assertTrue(findAllByPorsche.isEmpty());

    }

}
