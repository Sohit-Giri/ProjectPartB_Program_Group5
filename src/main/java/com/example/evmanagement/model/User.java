package com.example.evmanagement.model;

import java.io.Serializable;

/**
 * Model for storing user profile and vehicle details.
 */
public class User implements Serializable {
    private String name;
    private String email;
    private String evType;
    private double batteryCapacity;
    private double chargingSpeed;
    private String preferredRoute;

    public User(String name, String email, String evType, double batteryCapacity, double chargingSpeed, String preferredRoute) {
        this.name = name;
        this.email = email;
        this.evType = evType;
        this.batteryCapacity = batteryCapacity;
        this.chargingSpeed = chargingSpeed;
        this.preferredRoute = preferredRoute;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEvType() {
        return evType;
    }

    public void setEvType(String evType) {
        this.evType = evType;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public double getChargingSpeed() {
        return chargingSpeed;
    }

    public void setChargingSpeed(double chargingSpeed) {
        this.chargingSpeed = chargingSpeed;
    }

    public String getPreferredRoute() {
        return preferredRoute;
    }

    public void setPreferredRoute(String preferredRoute) {
        this.preferredRoute = preferredRoute;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", evType='" + evType + '\'' +
                ", batteryCapacity=" + batteryCapacity +
                ", chargingSpeed=" + chargingSpeed +
                ", preferredRoute='" + preferredRoute + '\'' +
                '}';
    }
}