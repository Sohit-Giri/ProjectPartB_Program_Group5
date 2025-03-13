package com.example.evmanagement.model;

import java.io.Serializable;

/**
 * Model for a charging station.
 */
public class ChargingStation implements Serializable {
    private String name;
    private double latitude;
    private double longitude;
    private int totalChargers;
    private int availableChargers;
    private double price;
    private int waitingTime; // in minutes

    /**
     * Constructor for creating a ChargingStation object with basic information.
     *
     * @param name      The name of the charging station.
     * @param latitude  The latitude of the charging station.
     * @param longitude The longitude of the charging station.
     */
    public ChargingStation(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Constructor for creating a ChargingStation object with full information.
     *
     * @param name              The name of the charging station.
     * @param latitude          The latitude of the charging station.
     * @param longitude         The longitude of the charging station.
     * @param totalChargers     The total number of chargers at the station.
     * @param availableChargers The number of currently available chargers.
     * @param price             The price per kWh at the station.
     * @param waitingTime       The estimated waiting time in minutes.
     */
    public ChargingStation(String name, double latitude, double longitude, int totalChargers, int availableChargers, double price, int waitingTime) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.totalChargers = totalChargers;
        this.availableChargers = availableChargers;
        this.price = price;
        this.waitingTime = waitingTime;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getTotalChargers() {
        return totalChargers;
    }

    public void setTotalChargers(int totalChargers) {
        this.totalChargers = totalChargers;
    }

    public int getAvailableChargers() {
        return availableChargers;
    }

    public void setAvailableChargers(int availableChargers) {
        this.availableChargers = availableChargers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Override
    public String toString() {
        return "ChargingStation{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", totalChargers=" + totalChargers +
                ", availableChargers=" + totalChargers +
                ", price=" + price +
                ", waitingTime=" + waitingTime +
                '}';
    }
}