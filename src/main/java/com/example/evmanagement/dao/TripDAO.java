// File: com/example/evmanagement/dao/TripDAO.java
package com.example.evmanagement.dao;

import com.example.evmanagement.model.Trip;
import com.example.evmanagement.util.FileUtil;

import java.io.IOException;
import java.util.List;

public class TripDAO {
    private static final String FILE_PATH = "data/trips.dat";

    public void saveTrips(List<Trip> trips) throws IOException {
        FileUtil.writeObjectToFile(trips, FILE_PATH);
    }

    @SuppressWarnings("unchecked")
    public List<Trip> loadTrips() throws IOException, ClassNotFoundException {
        return (List<Trip>) FileUtil.readObjectFromFile(FILE_PATH);
    }
}
