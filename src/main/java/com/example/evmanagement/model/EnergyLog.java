package com.example.evmanagement.model;

import java.io.Serializable;

public class EnergyLog implements Serializable {

    private String date;
    private double distance;
    private double energyConsumed;

    public EnergyLog(String date, double distance, double energyConsumed) {
        this.date = date;
        this.distance = distance;
        this.energyConsumed = energyConsumed;
    }

    // Getters and setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getEnergyConsumed() {
        return energyConsumed;
    }

    public void setEnergyConsumed(double energyConsumed) {
        this.energyConsumed = energyConsumed;
    }

    @Override
    public String toString() {
        return "EnergyLog{" +
                "date='" + date + '\'' +
                ", distance=" + distance +
                ", energyConsumed=" + energyConsumed +
                '}';
    }
}