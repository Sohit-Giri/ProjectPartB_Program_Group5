// File: EnvironmentalAnalytics.java
package com.example.evmanagement.model;

import java.io.Serializable;

/**
 * Model for environmental analytics data.
 * Calculates CO₂ savings based on the travelled distance.
 */
public class EnvironmentalAnalytics implements Serializable {
    private double distanceTravelled;
    private double co2Saved; // in kg

    public EnvironmentalAnalytics(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
        this.co2Saved = distanceTravelled * 0.12;
    }

    // Getters and setters
    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
        // Recalculate CO₂ savings
        this.co2Saved = distanceTravelled * 0.12;
    }

    public double getCo2Saved() {
        return co2Saved;
    }

    @Override
    public String toString() {
        return "EnvironmentalAnalytics{" +
                "distanceTravelled=" + distanceTravelled +
                ", co2Saved=" + co2Saved +
                '}';
    }
}
