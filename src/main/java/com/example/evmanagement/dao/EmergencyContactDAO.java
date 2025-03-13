package com.example.evmanagement.dao;

import com.example.evmanagement.model.EmergencyContact;
import com.example.evmanagement.util.FileUtil;

import java.io.IOException;
import java.util.List;

public class EmergencyContactDAO {
    private static final String FILE_PATH = "data/emergencyContacts.dat";

    public void saveEmergencyContacts(List<EmergencyContact> contacts) throws IOException {
        FileUtil.writeObjectToFile(contacts, FILE_PATH);
    }

    @SuppressWarnings("unchecked")
    public List<EmergencyContact> loadEmergencyContacts() throws IOException, ClassNotFoundException {
        return (List<EmergencyContact>) FileUtil.readObjectFromFile(FILE_PATH);
    }
}