// File: Trip.java
package com.example.evmanagement.model;

import java.io.Serializable;

/**
 * Model for a trip, including starting point, destination, battery usage, and charging recommendation.
 */
public class Trip implements Serializable {
    private String startPoint;
    private String destination;
    private double estimatedBatteryUsage;
    private String recommendedChargingStop;

    public Trip(String startPoint, String destination, double estimatedBatteryUsage, String recommendedChargingStop) {
        this.startPoint = startPoint;
        this.destination = destination;
        this.estimatedBatteryUsage = estimatedBatteryUsage;
        this.recommendedChargingStop = recommendedChargingStop;
    }

    // Getters and setters
    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getEstimatedBatteryUsage() {
        return estimatedBatteryUsage;
    }

    public void setEstimatedBatteryUsage(double estimatedBatteryUsage) {
        this.estimatedBatteryUsage = estimatedBatteryUsage;
    }

    public String getRecommendedChargingStop() {
        return recommendedChargingStop;
    }

    public void setRecommendedChargingStop(String recommendedChargingStop) {
        this.recommendedChargingStop = recommendedChargingStop;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "startPoint='" + startPoint + '\'' +
                ", destination='" + destination + '\'' +
                ", estimatedBatteryUsage=" + estimatedBatteryUsage +
                ", recommendedChargingStop='" + recommendedChargingStop + '\'' +
                '}';
    }
}
