package com.example.evmanagement.controller;

import com.example.evmanagement.dao.EmergencyContactDAO;
import com.example.evmanagement.model.EmergencyContact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField; // Import TextField

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * EmergencyAssistanceController provides an SOS feature.
 * It manages emergency contacts, triggers an alert, and (if available) shares user location.
 */
public class EmergencyAssistanceController {

    @FXML
    private TextArea txtEmergencyContacts;

    @FXML
    private TextField txtName; // Add TextField for name

    @FXML
    private TextField txtPhoneNumber; // Add TextField for phone number

    private EmergencyContactDAO emergencyContactDAO;

    @FXML
    public void initialize() {
        emergencyContactDAO = new EmergencyContactDAO();
        loadEmergencyContacts();
    }

    private void loadEmergencyContacts() {
        try {
            List<EmergencyContact> contacts = emergencyContactDAO.loadEmergencyContacts();
            if (contacts != null) {
                StringBuilder sb = new StringBuilder();
                for (EmergencyContact contact : contacts) {
                    sb.append(contact.toString()).append("\n");
                }
                txtEmergencyContacts.setText(sb.toString());
            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle the exception (e.g., log it, show a user-friendly error message)
            txtEmergencyContacts.setText("Could not load emergency contacts.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTriggerSOS(ActionEvent event) {
        // Simulate triggering an emergency alert to listed contacts.
        showAlert("SOS Activated", "Emergency alert sent to your saved contacts!");
        // In a real application, you'd iterate through the contacts and send alerts.
    }

    @FXML
    private void handleShareLocation(ActionEvent event) {
        // Simulate location-sharing
        String location = simulateLocation(); // Get simulated location

        if (location != null && !location.isEmpty()) {
            showAlert("Location Shared", "Your location (" + location + ") has been sent to your emergency contacts.");
        } else {
            showAlert("Location Sharing Failed", "Location sharing is not available.");
        }
    }

    @FXML
    private void handleSaveContacts(ActionEvent event) {
        String contactsText = txtEmergencyContacts.getText();
        String[] contactLines = contactsText.split("\n"); // Corrected typo and type
        List<EmergencyContact> contacts = new ArrayList<>();

        String[] parts; // Declare parts here

        try {
            for (String line : contactLines) { // Corrected for loop syntax
                parts = line.split("\\s*:\\s*"); // Corrected split regex
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String phoneNumber = parts[1].trim();
                    contacts.add(new EmergencyContact(name, phoneNumber));
                }
            } // Added missing closing brace

            emergencyContactDAO.saveEmergencyContacts(contacts); // Moved inside try block
            showAlert("Contacts Saved", "Emergency contacts saved successfully!");
        } catch (IOException e) {
            showAlert("Error Saving Contacts", "Could not save emergency contacts.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddContact(ActionEvent event) { // Add handleAddContact method
        String name = txtName.getText().trim();
        String phoneNumber = txtPhoneNumber.getText().trim();

        if (!name.isEmpty() && !phoneNumber.isEmpty()) {
            String formattedContact = name + ": " + phoneNumber;
            String currentContacts = txtEmergencyContacts.getText();
            txtEmergencyContacts.setText(currentContacts + formattedContact + "\n");
            txtName.clear();
            txtPhoneNumber.clear();
        } else {
            showAlert("Invalid Input", "Please enter both name and contact number.");
        }
    }

    private String simulateLocation() {
        // Simulate getting the location
        // In a real application, you would use platform-specific APIs or location services.
        // For this example, we'll provide a hardcoded location.
        return "Madhyapur Thimi, Bagmati Province, Nepal";
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}