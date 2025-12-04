package com.devlawal.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserArrayDataAccessDAO implements UserDAO {
    private static final List<User> users;

    static {
        users = new ArrayList<>(
                List.of(
                new User(UUID.fromString("70718b9d-4dc1-4817-8d80-f4eb4c7456b7"), "Hammed"),
                new User(UUID.fromString("fe4efe8a-3ea2-4f12-bb39-1b706c578887"), "Farima")
                )
        );
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
