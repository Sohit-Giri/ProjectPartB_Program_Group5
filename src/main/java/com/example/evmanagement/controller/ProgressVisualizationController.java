package com.example.evmanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

/**
 * ProgressVisualizationController shows visual progress tracking such as monthly cost/fuel savings,
 * carbon reduction and achievements.
 */
public class ProgressVisualizationController {

    @FXML
    private LineChart<String, Number> lineChartProgress;
    @FXML
    private Label lblAchievement;

    private Map<String, Integer> monthlySavings; // Store monthly savings data

    @FXML
    public void initialize() {
        lineChartProgress.setTitle("Monthly Progress");
        monthlySavings = new HashMap<>();
        loadMonthlyProgressData(); // Load data on initialization
    }

    private void loadMonthlyProgressData() {
        // Simulate loading data from a file or in-memory storage
        monthlySavings.put("Jan", 50);
        monthlySavings.put("Feb", 70);
        monthlySavings.put("Mar", 90);
        monthlySavings.put("Apr", 110);
        monthlySavings.put("May", 100);
        monthlySavings.put("Jun", 120);
    }

    @FXML
    private void handleVisualizeProgress(ActionEvent event) {
        updateChart();
        updateAchievementLabel();
        showAlert("Progress Visualized", "Monthly progress updated successfully.");
    }

    private void updateChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Savings");

        for (Map.Entry<String, Integer> entry : monthlySavings.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        lineChartProgress.getData().clear();
        lineChartProgress.getData().add(series);
    }

    private void updateAchievementLabel() {
        // Example: Check for a specific pattern to unlock an achievement
        boolean consistentSaver = true;
        int previousSavings = -1;

        for (int savings : monthlySavings.values()) {
            if (previousSavings != -1 && savings < previousSavings) {
                consistentSaver = false;
                break;
            }
            previousSavings = savings;
        }

        if (consistentSaver) {
            lblAchievement.setText("Achievement Unlocked: Consistent Saver");
        } else {
            lblAchievement.setText("No new achievements.");
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