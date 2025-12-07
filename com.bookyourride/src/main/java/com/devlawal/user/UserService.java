package com.devlawal.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {
    private final UserDAO userDAOArray =  new UserArrayDataAccessDAO();
    private final UserDAO userDAOFile = new UserFileDataAccessService();


    public List<User> getAllUsers(){
        List<User> fileUsers = userDAOFile.getUsers();
        List<User> arrayUsers = userDAOArray.getUsers();
        List<User> compact = new ArrayList<>();
        if (fileUsers != null) {
            for (User u : fileUsers) if (u != null) compact.add(u);
        }
        if (arrayUsers != null) {
            for (User u : arrayUsers) if (u != null) compact.add(u);
        }
        return compact;
    }

    public User getUser(UUID id){
        for (User user : getAllUsers()) {
            if(user.getId().equals(id)){
                return user;
            }
        }
        throw new IllegalArgumentException("User not found for id: " + id);
    }
}
