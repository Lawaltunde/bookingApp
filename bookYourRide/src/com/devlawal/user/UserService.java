package com.devlawal.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UserService {
    private final UserDAO userDAOArray =  new UserArrayDataAccessDAO();
    private final UserDAO userDAOFile = new UserFileDataAccessService();


    private final List<User> compact = new ArrayList<>();



    public List<User> getAllUsers(){
        List<User> fileUsers = userDAOFile.getUsers();
        List<User> arrayUsers = userDAOArray.getUsers();
        compact.addAll(fileUsers);
        compact.addAll(arrayUsers);
//        int fileLen = fileUsers == null ? 0 : fileUsers.length;
//        int arrayLen = arrayUsers == null ? 0 : arrayUsers.length;

//        User[] allUsers = new User[fileLen + arrayLen];
//        if (fileLen > 0) {
//            System.arraycopy(fileUsers, 0, allUsers, 0, fileLen);
//        }
//        if (arrayLen > 0) {
//            System.arraycopy(arrayUsers, 0, allUsers, fileLen, arrayLen);
//        }
        // compact out null slots before returning
//        int nonNullCount = 0;
//        for (User u : allUsers) {
//            if (u != null) nonNullCount++;
//        }
//        if (nonNullCount == 0) return new User[0];
//        compact = new User[nonNullCount];
//        int idx = 0;
//        for (User u : allUsers) {
//            if (u != null) compact[idx++] = u;
//        }
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
