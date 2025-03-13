package com.example.evmanagement.controller;

import com.example.evmanagement.model.CostComparisonRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * CostComparisonController compares energy costs between EVs and gasoline vehicles.
 * It handles user inputs for current electricity/fuel rates and displays tabular results.
 */
public class CostComparisonController {

    @FXML
    private TextField txtElectricityRate;
    @FXML
    private TextField txtFuelRate;
    @FXML
    private TableView<CostComparisonRecord> tableCostComparison; // Use CostComparisonRecord
    @FXML
    private TableColumn<CostComparisonRecord, Double> evCostColumn;
    @FXML
    private TableColumn<CostComparisonRecord, Double> fuelCostColumn;
    @FXML
    private TableColumn<CostComparisonRecord, Double> costDifferenceColumn;
    @FXML
    private TableColumn<CostComparisonRecord, Double> percentageDifferenceColumn;

    private ObservableList<CostComparisonRecord> comparisonData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize table columns
        evCostColumn.setCellValueFactory(new PropertyValueFactory<>("evCost"));
        fuelCostColumn.setCellValueFactory(new PropertyValueFactory<>("fuelCost"));
        costDifferenceColumn.setCellValueFactory(new PropertyValueFactory<>("costDifference"));
        percentageDifferenceColumn.setCellValueFactory(new PropertyValueFactory<>("percentageDifference"));

        tableCostComparison.setItems(comparisonData); // Set data source
    }

    @FXML
    private void handleCompareCosts(ActionEvent event) {
        double elecRate = 0.0, fuelRate = 0.0;
        try {
            elecRate = Double.parseDouble(txtElectricityRate.getText());
            fuelRate = Double.parseDouble(txtFuelRate.getText());
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid electricity and fuel rates.");
            return;
        }

        // Implement cost comparison calculation
        // Example: Simple comparison based on a fixed distance
        double distance = 100; // Example distance in km
        double evConsumption = 15; // Example EV consumption in kWh per 100km
        double fuelConsumption = 8; // Example fuel consumption in liters per 100km
        double evCost = (distance / 100) * evConsumption * elecRate;
        double fuelCost = (distance / 100) * fuelConsumption * fuelRate;

        CostComparisonRecord record = new CostComparisonRecord(evCost, fuelCost);
        comparisonData.clear(); // Clear previous data
        comparisonData.add(record); // Add the new record

        showAlert("Cost Comparison", "Cost comparison results updated in the table.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}