package com.example.evmanagement.controller;

import com.example.evmanagement.model.EnergyLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker; // Import DatePicker
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate; // Import LocalDate
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * DailyEnergyTrackerController logs and visualizes daily energy usage.
 * It displays data in a TableView and provides chart-based insights.
 */
public class DailyEnergyTrackerController {

    @FXML
    private DatePicker datePicker; // Changed from TextField to DatePicker
    @FXML
    private TextField txtDistance;
    @FXML
    private TextField txtEnergyConsumed;
    @FXML
    private TableView<EnergyLog> tableEnergyLogs;
    @FXML
    private TableColumn<EnergyLog, String> dateColumn;
    @FXML
    private TableColumn<EnergyLog, Double> distanceColumn;
    @FXML
    private TableColumn<EnergyLog, Double> energyColumn;
    @FXML
    private LineChart<String, Number> chartEnergyTrend;

    private ObservableList<EnergyLog> energyLogs = FXCollections.observableArrayList();
    private static final String LOG_FILE = "energy_logs.dat";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Format for date

    @FXML
    public void initialize() {
        chartEnergyTrend.setTitle("Daily Energy Consumption");
        loadEnergyLogs();
        setupTableView();
    }

    private void setupTableView() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
        energyColumn.setCellValueFactory(new PropertyValueFactory<>("energyConsumed"));
        tableEnergyLogs.setItems(energyLogs);
    }

    @FXML
    private void handleLogEnergyUsage(ActionEvent event) {
        LocalDate date = datePicker.getValue(); // Get LocalDate from DatePicker

        if (date == null) {
            showAlert("Input Error", "Please select a date.");
            return;
        }

        double distance = 0.0, energy = 0.0;
        try {
            distance = Double.parseDouble(txtDistance.getText());
            energy = Double.parseDouble(txtEnergyConsumed.getText());
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid distance and energy values.");
            return;
        }

        String formattedDate = date.format(DATE_FORMATTER); // Format the date

        EnergyLog newLog = new EnergyLog(formattedDate, distance, energy);
        energyLogs.add(newLog);
        saveEnergyLogs();

        showAlert("Energy Logged", "Daily energy usage has been recorded.");
        updateEnergyChart(formattedDate, energy);
    }

    private void updateEnergyChart(String date, double energy) {
        XYChart.Series<String, Number> series;
        if (chartEnergyTrend.getData().isEmpty()) {
            series = new XYChart.Series<>();
            series.setName("Energy Consumption");
            chartEnergyTrend.getData().add(series);
        } else {
            series = chartEnergyTrend.getData().get(0);
        }
        series.getData().add(new XYChart.Data<>(date, energy));
    }

    private void loadEnergyLogs() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(LOG_FILE))) {
            List<EnergyLog> loadedLogs = (List<EnergyLog>) ois.readObject();
            energyLogs.addAll(loadedLogs);
        } catch (FileNotFoundException e) {
            // File not found, no logs to load
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load energy logs.");
        }
    }

    private void saveEnergyLogs() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LOG_FILE))) {
            oos.writeObject(new ArrayList<>(energyLogs));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save energy logs.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}