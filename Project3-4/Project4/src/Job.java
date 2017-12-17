/*
File Name: Job.java
Date: 11-05-2017
Author: Minseok Kwak
Purpose: Implementing Job class that is extended by Thing class
*/

//Note: It is optional for project 1 and 2 (Just implement constructor and getter and setter methods)

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Job extends Thing{
    double duration;
    ArrayList<Skill> requirements;
    private boolean isValid;
    private boolean isReady;

    public Job(Scanner sc) {
        super(sc);
        duration = sc.hasNextDouble() ? sc.nextDouble() : 0.0d;
        requirements = new ArrayList<>();
        isValid = true;
        isReady = false;
    }

    /*
        Getter and Setter Methods
     */

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public ArrayList<Skill> getRequirements() {
        return requirements;
    }

    public void setRequirements(ArrayList<Skill> requirements) {
        this.requirements = requirements;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}//End Job Class
