package com.example.ambulanceapp;

/**
 * To indicate the type of data we are taking from the database
 */
public class Model {
    /**
     * The naming convention must match with the fire base fields
     */
    String name,distance;

    /**
     * default constructor
     */
    public Model() {
    }

    /**
     * constructor
     * @param name
     * @param distance
     */
    public Model(String name, String distance) {
        this.name = name;
        this.distance = distance;
    }

    /**
     * getter method
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * setter method
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
