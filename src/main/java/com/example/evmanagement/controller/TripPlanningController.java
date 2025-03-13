package com.example.evmanagement.controller;

import com.example.evmanagement.dao.TripDAO;
import com.example.evmanagement.model.ChargingStation;
import com.example.evmanagement.model.Trip;
import com.example.evmanagement.util.DijkstraUtil;
import com.example.evmanagement.util.HaversineUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TripPlanningController handles the route planning feature.
 * It uses Dijkstra's Algorithm for optimized route calculation and recommends charging stops.
 */
public class TripPlanningController {

    @FXML
    private ComboBox<String> startPointComboBox;
    @FXML
    private ComboBox<String> destinationComboBox;
    @FXML
    private TextField txtBatteryLevel;

    private TripDAO tripDAO;
    private List<ChargingStation> chargingStations;
    private double vehicleEfficiency = 0.15;

    @FXML
    public void initialize() {
        tripDAO = new TripDAO();
        loadChargingStations();
        populateLocationComboBoxes();
    }

    @FXML
    private void handleCalculateRoute(ActionEvent event) {
        String start = startPointComboBox.getValue();
        String destination = destinationComboBox.getValue();

        if (start == null || destination == null) {
            showAlert("Error", "Please select start and destination points.");
            return;
        }

        String optimalRoute = DijkstraUtil.calculateOptimalRoute(start, destination);
        showAlert("Route Calculated", "Optimal route: " + optimalRoute);
    }

    @FXML
    private void handleRecommendChargingStop(ActionEvent event) {
        String start = startPointComboBox.getValue();
        String destination = destinationComboBox.getValue();
        double batteryLevel;

        try {
            batteryLevel = Double.parseDouble(txtBatteryLevel.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid battery level.");
            return;
        }

        if (start == null || destination == null) {
            showAlert("Error", "Please select start and destination points.");
            return;
        }

        double distance = HaversineUtil.calculateDistance(
                getLatitude(start), getLongitude(start),
                getLatitude(destination), getLongitude(destination)
        );

        String recommendation = recommendChargingStop(batteryLevel, distance);
        showAlert("Charging Recommendation", recommendation);
    }

    @FXML
    private void handleEstimateBatteryUsage(ActionEvent event) {
        String start = startPointComboBox.getValue();
        String destination = destinationComboBox.getValue();

        if (start == null || destination == null) {
            showAlert("Error", "Please select start and destination points.");
            return;
        }

        double distance = HaversineUtil.calculateDistance(
                getLatitude(start), getLongitude(start),
                getLatitude(destination), getLongitude(destination)
        );

        double estimatedUsage = distance * vehicleEfficiency;
        showAlert("Battery Usage Estimate", "Estimated battery usage: " + estimatedUsage + " kWh");
    }

    private double getLatitude(String location) {
        switch (location.toLowerCase()) {
            case "hellop":
                return 27.68;
            case "pkr":
                return 28.21;
            case "ktm":
                return 27.70;
            case "bkt":
                return 27.67;
            case "he":
                return 27.69;
            default:
                return 0.0;
        }
    }

    private double getLongitude(String location) {
        switch (location.toLowerCase()) {
            case "hellop":
                return 85.32;
            case "pkr":
                return 83.99;
            case "ktm":
                return 85.33;
            case "bkt":
                return 85.42;
            case "he":
                return 85.35;
            default:
                return 0.0;
        }
    }

    private String recommendChargingStop(double batteryLevel, double distance) {
        if (batteryLevel < 30 || distance * vehicleEfficiency > batteryLevel) {
            ChargingStation nearestStation = findNearestChargingStation(
                    getLatitude(startPointComboBox.getValue()),
                    getLongitude(startPointComboBox.getValue())
            );
            if (nearestStation != null) {
                return "Recommend charging at " + nearestStation.getName();
            } else {
                return "Low battery! No nearby charging stations found.";
            }
        } else {
            return "No charging stop needed.";
        }
    }

    private ChargingStation findNearestChargingStation(double lat, double lon) {
        if (chargingStations == null || chargingStations.isEmpty()) {
            return null;
        }

        ChargingStation nearestStation = chargingStations.get(0);
        double shortestDistance = HaversineUtil.calculateDistance(lat, lon, nearestStation.getLatitude(), nearestStation.getLongitude());

        for (ChargingStation station : chargingStations) {
            double distance = HaversineUtil.calculateDistance(lat, lon, station.getLatitude(), station.getLongitude());
            if (distance < shortestDistance) {
                shortestDistance = distance;
                nearestStation = station;
            }
        }

        return nearestStation;
    }

    private void loadChargingStations() {
        chargingStations = new ArrayList<>();
        chargingStations.add(new ChargingStation("Station A", 27.69, 85.33));
        chargingStations.add(new ChargingStation("Station B", 28.21, 83.99));
        chargingStations.add(new ChargingStation("Station C", 27.67, 85.42));
    }

    private void populateLocationComboBoxes() {
        List<String> locations = new ArrayList<>(DijkstraUtil.getLocations());
        startPointComboBox.getItems().addAll(locations);
        destinationComboBox.getItems().addAll(locations);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}