package com.devlawal.car;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarDAO {

    public static final List<Car> CARS = Arrays.asList(
            new Car("1234", new BigDecimal("95.00"), Brand.TESLA, true),
            new Car("5678", new BigDecimal("76.5"), Brand.MERCEDES, false),
            new Car("9876", new BigDecimal("65.94"), Brand.AUDI, false));

    public List<Car> getAllCars(){
        return CARS;
    }
}
