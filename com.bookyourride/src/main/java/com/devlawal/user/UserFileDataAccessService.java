package com.devlawal.user;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class UserFileDataAccessService implements UserDAO{

    private final static List<User> users;

    static{
        users = new ArrayList<>();
    }
    public List<User> getUserFromFile(){
        // prefer project-relative CSV file
        File file = new File(getClass().getClassLoader().getResource("users.csv").getPath());
        if (!file.exists()) {
            // fallback to ./users.csv or ./users.txt if someone runs from different cwd
            File alt = new File("./users.csv");
            if (alt.exists()) file = alt;
        }
        if (file.exists())
        {
            try (Scanner scanner = new Scanner(file)) {
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine().trim();
                    if (line.isEmpty()) continue;
                    String[] userData = line.split(",", 2);
                    addFromFileToArray(userData);
                }
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        return users;
    }

    public void addFromFileToArray(String [] userData){
        if (userData == null){
            throw new IllegalArgumentException("userData is null");
        }
        if (userData.length < 2){
            throw new IllegalArgumentException("userData is invalid");
        }
        String idStr = userData[0].trim();
        String name = userData[1].trim();
        UUID id = UUID.fromString(idStr);

        users.add(new User(id, name));

    }

    @Override
    public List<User> getUsers() {
        // Lazy-load users from file once to avoid duplicating entries when called repeatedly
        for (User u : users) {
            if (u != null) {
                return users;
            }
        }
        return getUserFromFile();
    }
}
