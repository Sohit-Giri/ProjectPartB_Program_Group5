package com.example.evmanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * EnvironmentalAnalyticsController calculates and visualizes CO₂ savings.
 * It compares EV emissions against fuel-based vehicles.
 */
public class EnvironmentalAnalyticsController {

    @FXML
    private TextField txtDistanceTravelled;
    @FXML
    private BarChart<String, Number> barChartSavings;

    @FXML
    public void initialize() {
        // Initialization logic if needed, e.g., setup bar chart
        barChartSavings.setTitle("CO₂ Savings (kg)");
    }

    @FXML
    private void handleCalculateCO2Savings(ActionEvent event) {
        double distance = Double.parseDouble(txtDistanceTravelled.getText());
        double co2Saved = distance * 0.12; // Using formula: CO₂ Saved = Distance × 0.12 kg/km
        showAlert("CO₂ Savings Calculated", "You have saved " + co2Saved + " kg of CO₂.");
        updateBarChart(co2Saved);
    }

    private void updateBarChart(double co2Saved) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("CO₂ Savings");
        series.getData().add(new XYChart.Data<>("Savings", co2Saved));
        barChartSavings.getData().clear();
        barChartSavings.getData().add(series);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
