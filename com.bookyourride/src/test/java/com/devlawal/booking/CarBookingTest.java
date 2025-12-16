package com.devlawal.booking;

import com.devlawal.car.CarDAO;
import com.devlawal.car.CarService;
import com.devlawal.user.User;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CarBookingTest {

    @Test
    public void canItBookCar() {
        CarBookingDAO carBookingDAO = new CarBookingDAO();
        CarService carService = new CarService();
        CarBookingService carBookingService = new CarBookingService(carBookingDAO, carService);
        assertNotNull(carBookingService.bookCars(new User(UUID.fromString("7e4b9220-a47a-45a7-a33b-7182ee0dc30e"), "Leila"), "1234"));
    }
}
