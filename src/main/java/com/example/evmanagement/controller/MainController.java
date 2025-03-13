package com.example.evmanagement.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * MainController is responsible for navigating among all the application modules.
 * It loads each module’s view into a corresponding tab.
 */
public class MainController {

    @FXML
    private TabPane mainTabPane;

    @FXML
    private Tab tabUserProfile;
    @FXML
    private Tab tabTripPlanning;
    @FXML
    private Tab tabChargingStation;
    @FXML
    private Tab tabEnvironmentalAnalytics;
    @FXML
    private Tab tabCostComparison;
    @FXML
    private Tab tabProgressVisualization;
    @FXML
    private Tab tabEmergencyAssistance;
    @FXML
    private Tab tabDailyEnergyTracker;

    @FXML
    public void initialize() {
        loadModuleView(tabUserProfile, "/com/example/evmanagement/fxml/UserProfileView.fxml");
        loadModuleView(tabTripPlanning, "/com/example/evmanagement/fxml/TripPlanningView.fxml");
        loadModuleView(tabChargingStation, "/com/example/evmanagement/fxml/ChargingStationView.fxml");
        loadModuleView(tabEnvironmentalAnalytics, "/com/example/evmanagement/fxml/EnvironmentalAnalyticsView.fxml");
        loadModuleView(tabCostComparison, "/com/example/evmanagement/fxml/CostComparisonView.fxml");
        loadModuleView(tabProgressVisualization, "/com/example/evmanagement/fxml/ProgressVisualizationView.fxml");
        loadModuleView(tabEmergencyAssistance, "/com/example/evmanagement/fxml/EmergencyAssistanceView.fxml");
        loadModuleView(tabDailyEnergyTracker, "/com/example/evmanagement/fxml/DailyEnergyTrackerView.fxml");
    }

    private void loadModuleView(Tab tab, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            AnchorPane pane = new AnchorPane(view);
            // Anchor the loaded view to fill the tab content
            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
            tab.setContent(pane);
        } catch (IOException e) {
            System.err.println("Error loading view: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
