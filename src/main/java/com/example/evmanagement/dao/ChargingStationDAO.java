package com.example.evmanagement.dao;

import com.example.evmanagement.model.ChargingStation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChargingStationDAO {
    private static final String CSV_FILE_PATH = "data/stations.csv";

    public List<ChargingStation> loadStations() throws IOException {
        List<ChargingStation> stations = new ArrayList<>();
        File file = new File(CSV_FILE_PATH);
        if (!file.exists()) {
            return stations;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Assuming the first line is a header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 7) {
                    String name = tokens[0].trim();
                    double latitude = Double.parseDouble(tokens[1].trim());
                    double longitude = Double.parseDouble(tokens[2].trim());
                    int totalChargers = Integer.parseInt(tokens[3].trim());
                    int availableChargers = Integer.parseInt(tokens[4].trim());
                    double price = Double.parseDouble(tokens[5].trim());
                    int waitingTime = Integer.parseInt(tokens[6].trim());
                    ChargingStation station = new ChargingStation(name, latitude, longitude, totalChargers, availableChargers, price, waitingTime);
                    stations.add(station);
                }
            }
        }
        return stations;
    }
}
