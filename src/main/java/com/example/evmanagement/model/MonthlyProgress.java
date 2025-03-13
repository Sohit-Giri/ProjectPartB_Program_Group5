// File: MonthlyProgress.java
package com.example.evmanagement.model;

import java.io.Serializable;

/**
 * Model for visualizing monthly progress in savings.
 */
public class MonthlyProgress implements Serializable {
    private String month;
    private double savings;

    public MonthlyProgress(String month, double savings) {
        this.month = month;
        this.savings = savings;
    }

    // Getters and setters
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    @Override
    public String toString() {
        return "MonthlyProgress{" +
                "month='" + month + '\'' +
                ", savings=" + savings +
                '}';
    }
}
