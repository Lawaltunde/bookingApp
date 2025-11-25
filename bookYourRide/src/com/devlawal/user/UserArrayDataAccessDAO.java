package com.devlawal.user;

import java.util.UUID;

public class UserArrayDataAccessDAO implements UserDAO {
    private static final User[] users;

    static {
        users = new User[]{
                new User(UUID.fromString("70718b9d-4dc1-4817-8d80-f4eb4c7456b7"), "Hammed"),
                new User(UUID.fromString("fe4efe8a-3ea2-4f12-bb39-1b706c578887"), "Farima")
        };
    }

    @Override
    public User[] getUsers() {
        return users;
    }
}
