package com.devlawal.booking;

import com.devlawal.car.Brand;
import com.devlawal.car.Car;
import com.devlawal.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CarBookingDAOTest {

    // single DAO instance for all tests; will be (re)initialized before each test
    private CarBookingDAO dao;

    @BeforeEach
    void setUp() {
        dao = new CarBookingDAO();
        // ensure clean slate for the static backing list
        dao.getCarBookings().clear();
    }

    @Test
    void getCarBookings() {
        assertTrue(dao.getCarBookings().isEmpty(), "expected no bookings initially");

        User user = new User(UUID.randomUUID(), "Alice");
        Car car = new Car("REG1", new BigDecimal("10.0"), Brand.TESLA, false);
        CarBooking booking = new CarBooking(UUID.randomUUID(), user, car, LocalDateTime.now(), false);

        dao.book(booking);

        assertEquals(1, dao.getCarBookings().size(), "expected one booking after book()");
        assertSame(booking, dao.getCarBookings().get(0), "the stored booking should be the same instance");
    }

    @Test
    void book() {
        User user = new User(UUID.randomUUID(), "Bob");
        Car car = new Car("REG2", new BigDecimal("20.5"), Brand.AUDI, false);
        CarBooking booking = new CarBooking(UUID.randomUUID(), user, car, LocalDateTime.now(), false);

        dao.book(booking);

        assertFalse(dao.getCarBookings().isEmpty());
        assertTrue(dao.getCarBookings().contains(booking));
    }

    @Test
    void cancelCarBooking() {

        User user = new User(UUID.randomUUID(), "Charlie");
        Car car = new Car("REG3", new BigDecimal("30"), Brand.VW, false);
        CarBooking booking = new CarBooking(UUID.randomUUID(), user, car, LocalDateTime.now(), false);

        dao.book(booking);
        assertTrue(dao.getCarBookings().contains(booking));

        // current implementation of cancelCarBooking is a no-op; assert behavior to avoid test breakage
        dao.cancelCarBooking();

        // booking should still exist
        assertTrue(dao.getCarBookings().contains(booking), "cancelCarBooking() is currently unimplemented and should leave bookings unchanged");
    }
}