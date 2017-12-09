/*
File Name: Ship.java
Date: 11-05-2017
Author: Minseok Kwak
Purpose: Implementing Ship class that is extended by Thing
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Ship extends Thing {
    private PortTime arrivalTime;
    private PortTime dockTime;
    private double draft;
    private double length;
    private double weight;
    private double width;

    // Need to use Jobs for Project 3 and 4
    ArrayList<Job> jobs;

    //Constructor with Scanner Parameter
    public Ship(Scanner sc) {
        super(sc);
        weight = (sc.hasNextDouble()) ? sc.nextDouble() : 0.0d;
        length = (sc.hasNextDouble()) ? sc.nextDouble() : 0.0d;
        width = (sc.hasNextDouble()) ? sc.nextDouble() : 0.0d;
        draft = (sc.hasNextDouble()) ? sc.nextDouble() : 0.0d;
        jobs = new ArrayList<>();

    }

    /*
        Getter and Setter methods
    */

    public PortTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(PortTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public PortTime getDockTime() {
        return dockTime;
    }

    public void setDockTime(PortTime dockTime) {
        this.dockTime = dockTime;
    }

    public double getDraft() {
        return draft;
    }

    public void setDraft(double draft) {
        if(draft >= 0.0)
            this.draft = draft;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        if(length >= 0.0)
            this.length = length;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if(weight >= 0.0)
            this.weight = weight;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if(width >= 0.0)
            this.width = width;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}//End Ship Class
