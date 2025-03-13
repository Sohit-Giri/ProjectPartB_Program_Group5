// File: CostComparisonRecord.java
package com.example.evmanagement.model;

import java.io.Serializable;

/**
 * Model to store the results of a cost comparison between EV and fuel-based vehicles.
 */
public class CostComparisonRecord implements Serializable {
    private double evCost;
    private double fuelCost;
    private double costDifference;
    private double percentageDifference;

    public CostComparisonRecord(double evCost, double fuelCost) {
        this.evCost = evCost;
        this.fuelCost = fuelCost;
        this.costDifference = fuelCost - evCost;
        this.percentageDifference = (fuelCost != 0) ? ((fuelCost - evCost) / fuelCost) * 100 : 0;
    }

    // Getters and setters
    public double getEvCost() {
        return evCost;
    }

    public void setEvCost(double evCost) {
        this.evCost = evCost;
    }

    public double getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(double fuelCost) {
        this.fuelCost = fuelCost;
    }

    public double getCostDifference() {
        return costDifference;
    }

    public void setCostDifference(double costDifference) {
        this.costDifference = costDifference;
    }

    public double getPercentageDifference() {
        return percentageDifference;
    }

    public void setPercentageDifference(double percentageDifference) {
        this.percentageDifference = percentageDifference;
    }

    @Override
    public String toString() {
        return "CostComparisonRecord{" +
                "evCost=" + evCost +
                ", fuelCost=" + fuelCost +
                ", costDifference=" + costDifference +
                ", percentageDifference=" + percentageDifference +
                '}';
    }
}
