/*
File Name: Job.java
Date: 11-05-2017
Author: Minseok Kwak
Purpose: Implementing Job class that is extended by Thing class
*/

//Note: It is optional for project 1 and 2 (Just implement constructor and getter and setter methods)

import java.util.ArrayList;
import java.util.Scanner;

public class Job extends Thing {
    double duration;
    ArrayList<String> requirements;

    public Job(Scanner sc) {
        super(sc);
        duration = sc.hasNextDouble() ? sc.nextDouble() : 0.0d;
        requirements = new ArrayList<>();
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

    public ArrayList<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(ArrayList<String> requirements) {
        this.requirements = requirements;
    }
}//End Job Class
