# BookYourRide

A simple Java console (CLI) application for booking cars.

This project is a small demo CLI that lets users view available cars, view electric cars, create bookings, and inspect bookings and users. It is intended as a learning / sample project and does not use a database — data is seeded in memory.

---

## Features

- Book a car for a user
- View all bookings
- View available cars
- View available electric cars
- View all users
- View all cars booked by a specific user


## Quick overview

The interactive menu (in `com.devlawal.Main`) exposes these options:

1 - Book Car
2 - View All User Booked Cars
3 - View All Bookings
4 - View Available Cars
5 - View Available Electric Cars
6 - View all users
7 - Exit

When booking, the app asks for a car registration number and a user id (UUID). The application uses simple in-memory DAOs that are pre-seeded with example users and cars.


## Prerequisites

- Java JDK 11 or later installed and `java` / `javac` available on PATH
- Optional: IntelliJ IDEA (project contains IntelliJ module files)


## Build & run (command line)

From the repository root (where `bookYourRide` is located) you can compile and run the project using the JDK tools.

1. Compile all Java files into an output folder:

```bash
# from project root
mkdir -p out
javac -d out $(find bookYourRide/src -name "*.java")
```

2. Run the CLI application:

```bash
java -cp out com.devlawal.Main
```

Notes:
- The project does not use Maven/Gradle by default; the instructions above are minimal and work for small demos.
- You can also open the `bookYourRide` module in IntelliJ and run the `Main` class directly.


## Seeded data (examples you can use in the CLI)

Cars (from `com.devlawal.car.CarDAO`):
- reg number: `1234`, brand: `TESLA`, price/day: `95.00`, electric: `true`
- reg number: `5678`, brand: `MERCEDES`, price/day: `76.5`, electric: `false`
- reg number: `9876`, brand: `AUDI`, price/day: `65.94`, electric: `false`

Users (from `com.devlawal.user.UserDAO`):
- id: `70718b9d-4dc1-4817-8d80-f4eb4c7456b7`, username: `Hammed`
- id: `fe4efe8a-3ea2-4f12-bb39-1b706c578887`, username: `Farima`

Example booking flow (interactive):
1. Start the app
2. Choose menu item `1` (Book Car)
3. When asked for reg number enter `1234`
4. When asked for user id enter `70718b9d-4dc1-4817-8d80-f4eb4c7456b7`

If successful you should see a confirmation and a booking reference (UUID).


## Project structure

Top-level (relevant files/folders):

- `bookYourRide/src/com/devlawal/Main.java` - entrypoint and interactive menu
- `bookYourRide/src/com/devlawal/car` - Car model, Brand enum, DAO and service
- `bookYourRide/src/com/devlawal/user` - User model, DAO and service
- `bookYourRide/src/com/devlawal/booking` - Booking model, DAO and service


## Planned features

These are small improvements and features that can be added to evolve the demo:

- Add unit tests for the services (booking, car, user)
- Replace in-memory DAOs with a simple embedded DB or serialized store for persistence
- Improve CLI UX: input validation, retry loops, and prettier output
- Add cancel booking and booking date/range functionality
- Add a build tool configuration (Gradle/Maven) to simplify builds and tests

