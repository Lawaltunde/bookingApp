package com.devlawal.user;

import java.util.Objects;
import java.util.UUID;

public class UserService {
    private final UserDAO userDAOArray = new UserArrayDataAccessDAO();
    private final UserDAO userDAOFile = new UserFileDataAccessService();


    public User[] getAllUsers(){
        User[] fileUsers = userDAOFile.getUsers();
        User[] arrayUsers = userDAOArray.getUsers();
        int fileLen = fileUsers == null ? 0 : fileUsers.length;
        int arrayLen = arrayUsers == null ? 0 : arrayUsers.length;

        User[] allUsers = new User[fileLen + arrayLen];
        if (fileLen > 0) {
            System.arraycopy(fileUsers, 0, allUsers, 0, fileLen);
        }
        if (arrayLen > 0) {
            System.arraycopy(arrayUsers, 0, allUsers, fileLen, arrayLen);
        }
        // compact out null slots before returning
        int nonNullCount = 0;
        for (User u : allUsers) {
            if (u != null) nonNullCount++;
        }
        if (nonNullCount == 0) return new User[0];
        User[] compact = new User[nonNullCount];
        int idx = 0;
        for (User u : allUsers) {
            if (u != null) compact[idx++] = u;
        }
        return compact;
    }
    public User[] getAllUsersFromArray(){
        User[] arr = userDAOArray.getUsers();
        if (arr == null || arr.length == 0){
            return new User[0];
        }
        // compact
        int count = 0;
        for (User u : arr) if (u != null) count++;
        User[] res = new User[count];
        int i = 0;
        for (User u : arr) if (u != null) res[i++] = u;
        return res;
    }

    public User[] getAllUserFromFile(){
        User[] arr = userDAOFile.getUsers();
        if (arr == null || arr.length == 0){
            return new User[0];
        }
        int count = 0;
        for (User u : arr) if (u != null) count++;
        User[] res = new User[count];
        int i = 0;
        for (User u : arr) if (u != null) res[i++] = u;
        return res;
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
