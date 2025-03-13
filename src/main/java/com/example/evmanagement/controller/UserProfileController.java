package com.example.evmanagement.controller;

import com.example.evmanagement.dao.UserDAO;
import com.example.evmanagement.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * UserProfileController handles user signup, profile management,
 * vehicle details (battery capacity, EV type, etc.), and trip recommendations.
 */
public class UserProfileController {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtEVType;
    @FXML
    private TextField txtBatteryCapacity;
    @FXML
    private TextField txtChargingSpeed;
    @FXML
    private TextField txtPreferredRoute;

    private User currentUser;
    private UserDAO userDAO; // Added UserDAO instance

    @FXML
    public void initialize() {
        userDAO = new UserDAO(); // Initialize UserDAO
        loadUserProfile();
    }

    @FXML
    private void handleSignup(ActionEvent event) {
        // Create a new user object using entered data
        currentUser = new User(
                txtName.getText(),
                txtEmail.getText(),
                txtEVType.getText(),
                Double.parseDouble(txtBatteryCapacity.getText()),
                Double.parseDouble(txtChargingSpeed.getText()),
                txtPreferredRoute.getText()
        );
        saveUserProfile();
        showAlert("Signup Successful", "User profile created and saved successfully.");
    }

    @FXML
    private void handleUpdateProfile(ActionEvent event) {
        // Update user data with the new info
        if (currentUser != null) {
            currentUser.setName(txtName.getText());
            currentUser.setEmail(txtEmail.getText());
            currentUser.setEvType(txtEVType.getText());
            currentUser.setBatteryCapacity(Double.parseDouble(txtBatteryCapacity.getText()));
            currentUser.setChargingSpeed(Double.parseDouble(txtChargingSpeed.getText()));
            currentUser.setPreferredRoute(txtPreferredRoute.getText());
            saveUserProfile();
            showAlert("Update Successful", "User profile updated successfully.");
        } else {
            showAlert("Error", "No user profile found. Please sign up first.");
        }
    }

    private void loadUserProfile() {
        try {
            User loadedUser = userDAO.loadUser(); // Load the User object
            if (loadedUser != null) {
                currentUser = loadedUser; // Set the current user to the loaded user
                txtName.setText(currentUser.getName());
                txtEmail.setText(currentUser.getEmail());
                txtEVType.setText(currentUser.getEvType());
                txtBatteryCapacity.setText(String.valueOf(currentUser.getBatteryCapacity()));
                txtChargingSpeed.setText(String.valueOf(currentUser.getChargingSpeed()));
                txtPreferredRoute.setText(currentUser.getPreferredRoute());
            } else {
                // If no user is found, create a dummy user or leave the fields blank
                currentUser = new User("John Doe", "john@example.com", "Sedan", 60.0, 1.2, "Highway");
                txtName.setText(currentUser.getName());
                txtEmail.setText(currentUser.getEmail());
                txtEVType.setText(currentUser.getEvType());
                txtBatteryCapacity.setText(String.valueOf(currentUser.getBatteryCapacity()));
                txtChargingSpeed.setText(String.valueOf(currentUser.getChargingSpeed()));
                txtPreferredRoute.setText(currentUser.getPreferredRoute());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading user profile: " + e.getMessage());
            e.printStackTrace();
            showAlert("Error", "Failed to load user profile.");
        }
    }
    private void saveUserProfile() {
        try {
            userDAO.saveUser(currentUser);
            System.out.println("Saving user: " + currentUser);
        } catch (IOException e) {
            System.err.println("Error saving user profile: " + e.getMessage());
            e.printStackTrace();
            showAlert("Error", "Failed to save user profile.");
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