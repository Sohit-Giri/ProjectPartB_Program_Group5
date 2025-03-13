// File: module-info.java
module evmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web; // Add this line

    // Export packages so they can be accessed by JavaFX and other modules if needed
    exports com.example.evmanagement;
    exports com.example.evmanagement.controller;
    exports com.example.evmanagement.dao;
    exports com.example.evmanagement.model;
    exports com.example.evmanagement.util;

    // Open packages to JavaFX's FXML loader
    opens com.example.evmanagement to javafx.fxml;
    opens com.example.evmanagement.controller to javafx.fxml;
}
