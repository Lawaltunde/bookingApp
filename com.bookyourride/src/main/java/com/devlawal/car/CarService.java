package com.devlawal.car;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private final CarDAO carDAO = new CarDAO();

    public List<Car> getCars(){
        return carDAO.getAllCars();
    }

    public Car getCar(String regNumber){
        for (Car car : carDAO.getAllCars()) {
            if (regNumber.equals(car.getRegNumber())){
                return car;
            }
            }
        throw new IllegalArgumentException("regNumber can't be null");
        }

    public List<Car> getAllElectricCars() {
        int countElectricCars = 0;
        List<Car> cars = getCars();

        if (cars.isEmpty()) {
            return List.of(new Car[0]);
        }

        for (Car car : cars) {
            if (car.isElectricCar()) {
                countElectricCars++;
            }
        }

        if (countElectricCars == 0) {
            return List.of(new Car[0]);
        }

        int index = 0;

        List<Car> allElectricCars = new ArrayList<>();

        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).isElectricCar()) {
                allElectricCars.add(cars.get(i));
                index++;
            }
        }
        return allElectricCars;
    }
}

