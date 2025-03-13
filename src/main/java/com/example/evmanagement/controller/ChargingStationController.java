package com.example.evmanagement.controller;

import com.example.evmanagement.dao.ChargingStationDAO;
import com.example.evmanagement.model.ChargingStation;
import com.example.evmanagement.util.HaversineUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ChargingStationController handles locating charging stations.
 * It uses the Haversine formula to find the nearest stations and displays details.
 */
public class ChargingStationController {

    @FXML
    private ComboBox<String> locationComboBox;
    @FXML
    private TextField txtSearchRadius;

    private ChargingStationDAO stationDAO;
    private List<ChargingStation> allStations;
    private Map<String, double[]> locationCoordinates; // Store as double[]

    @FXML
    public void initialize() {
        stationDAO = new ChargingStationDAO();
        try {
            allStations = stationDAO.loadStations();
        } catch (IOException e) {
            showAlert("Error", "Could not load charging stations.");
            allStations = new ArrayList<>();
        }
        initializeLocationCoordinates();
        populateLocationComboBox();
    }

    private void initializeLocationCoordinates() {
        locationCoordinates = new HashMap<>();
        locationCoordinates.put("Kathmandu", new double[]{27.7172, 85.3240});
        locationCoordinates.put("Pokhara", new double[]{28.21, 83.99});
        locationCoordinates.put("Chitwan", new double[]{27.59, 84.34});
        locationCoordinates.put("Lalitpur", new double[]{27.67, 85.32});
        locationCoordinates.put("Bhaktapur", new double[]{27.67, 85.42});
        locationCoordinates.put("Madhyapur Thimi", new double[]{27.67, 85.41}); // Add Madhyapur Thimi
    }

    private void populateLocationComboBox() {
        locationComboBox.getItems().addAll(locationCoordinates.keySet());
    }

    @FXML
    private void handleSearchStations(ActionEvent event) {
        String selectedLocation = locationComboBox.getValue();
        double radius = 0.0;

        try {
            radius = Double.parseDouble(txtSearchRadius.getText());
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter a valid radius.");
            return;
        }

        if (selectedLocation == null) {
            showAlert("Input Error", "Please select a location.");
            return;
        }

        double[] coords = locationCoordinates.get(selectedLocation); // Get coordinates
        double latitude = coords[0];
        double longitude = coords[1];

        List<ChargingStation> nearbyStations = findNearbyStations(latitude, longitude, radius);
        displayNearbyStations(nearbyStations);
        showMap(latitude, longitude, nearbyStations);
    }

    private List<ChargingStation> findNearbyStations(double latitude, double longitude, double radius) {
        List<ChargingStation> nearby = new ArrayList<>();
        for (ChargingStation station : allStations) {
            double distance = HaversineUtil.calculateDistance(
                    latitude, longitude,
                    station.getLatitude(), station.getLongitude()
            );
            if (distance <= radius) {
                nearby.add(station);
            }
        }
        return nearby;
    }

    private void displayNearbyStations(List<ChargingStation> stations) {
        if (stations.isEmpty()) {
            showAlert("No Stations Found", "No charging stations found within the specified radius.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        String selectedLocation = locationComboBox.getValue();
        double[] coords = locationCoordinates.get(selectedLocation);
        double latitude = coords[0];
        double longitude = coords[1];

        for (ChargingStation station : stations) {
            double distance = HaversineUtil.calculateDistance(
                    latitude,
                    longitude,
                    station.getLatitude(),
                    station.getLongitude()
            );
            sb.append(station.getName())
                    .append(" (")
                    .append(String.format("%.2f", distance))
                    .append(" km)\n");
        }
        showAlert("Nearest Charging Stations", "Found:\n" + sb.toString());
    }

    @FXML
    private void handleShowMap(ActionEvent event) {
        String selectedLocation = locationComboBox.getValue();

        if (selectedLocation == null) {
            showAlert("Input Error", "Please select a location.");
            return;
        }

        double[] coords = locationCoordinates.get(selectedLocation);
        double latitude = coords[0];
        double longitude = coords[1];
        showMap(latitude, longitude, allStations);
    }

    private void showMap(double currentLatitude, double currentLongitude, List<ChargingStation> stations) {
        Stage mapStage = new Stage();
        mapStage.setTitle("Charging Station Map");
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        String html = "<html><head><style>body { margin: 0; padding: 0; }</style></head><body>"
                + "<iframe width='100%' height='100%' frameborder='0' style='border:0' "
                + "src='https://maps.google.com/maps?q="
                + currentLatitude + "," + currentLongitude + "&z=14&output=embed' "
                + "allowfullscreen></iframe></body></html>";

        webEngine.loadContent(html);

        VBox layout = new VBox(webView);
        Scene scene = new Scene(layout, 800, 600);
        mapStage.setScene(scene);
        mapStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}