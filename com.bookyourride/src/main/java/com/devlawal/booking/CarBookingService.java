package com.devlawal.booking;

import com.devlawal.car.Car;
import com.devlawal.car.CarService;
import com.devlawal.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarBookingService {

    private final CarBookingDAO carBookingDAOS;
    private final CarService carService ;

    public CarBookingService(CarBookingDAO carBookingDAOS, CarService carService) {
        this.carBookingDAOS = carBookingDAOS;
        this.carService = carService;
    }

    public UUID bookCars(User user, String regNumber){
        List<Car> availableCars = getAvailableCars();
        if (availableCars.isEmpty()){
            throw new IllegalStateException("No car Available for renting");
        }
        for (Car availableCar : availableCars){
            if(availableCar.getRegNumber().equals(regNumber)){
                Car car = carService.getCar(regNumber);
                UUID bookingId = UUID.randomUUID();
                // include the car in the booking so downstream code can access it
                carBookingDAOS.book(new CarBooking(bookingId, user, car, LocalDateTime.now(), false));
                return bookingId;
            }
        }
        throw new IllegalStateException("Already booked car with regNumber"+ regNumber);
    }

    public List<Car> getUserBookedCars(UUID useId){
        List<CarBooking> carBooking = carBookingDAOS.getCarBookings();

        int numberOfBookingsForUser = 0;
        for(CarBooking cb : carBooking){
            // ensure cb is not null before accessing its user
            if (cb != null && cb.getUser() != null && cb.getUser().getId().equals(useId)){
                ++numberOfBookingsForUser;
            }
        }
        if (numberOfBookingsForUser == 0){
            return  List.of(new Car[0]);
        }
        List<Car> userCar = new ArrayList<>();
        int counter = 0;

        for(CarBooking cb : carBooking){
            if(cb != null && cb.getUser() != null && cb.getUser().getId().equals(useId)){
                // be defensive: cb.getCar() may be null if booking was created incorrectly
                if (cb.getCar() != null) userCar.add(cb.getCar());
            }
        }
        return userCar;
    }

    public List<Car> getAvailableCars(){
        return getCars(carService.getCars());
    }

    public List<Car> getAvailableElectricCars(){
        return getCars(carService.getAllElectricCars());
    }


    private List<Car> getCars(List<Car> cars){
        if (cars.isEmpty()){
            return List.of(new Car[0]);
        }

        List<CarBooking> carBookings = carBookingDAOS.getCarBookings();

        if (carBookings.isEmpty()){
            return cars;
        }

        int availableCarCount = 0;

        for (Car car : cars){
            boolean booked = false;
            for (CarBooking carBooking : carBookings){
                if(carBooking == null || carBooking.getCar() == null || !carBooking.getCar().equals(car)){
                    continue;
                }
                booked = true;
            }
            if(!booked){
                ++availableCarCount;
            }
        }
        List<Car> availableCars = new ArrayList<>();
        int index = 0;

        for (Car car: cars){
            boolean booked = false;
            for(CarBooking carBooking : carBookings){
                if(carBooking == null || carBooking.getCar() == null || !carBooking.getCar().equals(car)){
                    continue;
                }
                booked = true;
            }
            if (!booked){
                availableCars.add(car);
            }
        }
        return availableCars;
    }


    public List<CarBooking> getBooking(){
        List<CarBooking> carBookings = carBookingDAOS.getCarBookings();

        int numberOfBooking = 0;

        for (CarBooking cbook : carBookings){
            if (cbook != null){
                ++numberOfBooking;
            }
        }
        if (numberOfBooking == 0){
            return List.of(new CarBooking[0]);
        }

        List<CarBooking> bookings = new ArrayList<>();
        int index = 0;
        for (CarBooking carBooking : carBookings){
            if(carBooking != null){
                bookings.add(carBooking);
            }
        }
        return bookings;
    }
}
