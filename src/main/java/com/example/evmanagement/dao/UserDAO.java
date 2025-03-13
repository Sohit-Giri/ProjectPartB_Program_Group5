package com.example.evmanagement.dao;

import com.example.evmanagement.model.User;
import com.example.evmanagement.util.FileUtil;

import java.io.IOException;

public class UserDAO {
    private static final String FILE_PATH = "data/user.dat"; // Changed to relative path

    public void saveUser(User user) throws IOException {
        FileUtil.writeObjectToFile(user, FILE_PATH);
    }

    public User loadUser() throws IOException, ClassNotFoundException {
        return (User) FileUtil.readObjectFromFile(FILE_PATH);
    }
}