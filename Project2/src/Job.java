/*
File Name: Job.java
Date: 11-05-2017
Author: Minseok Kwak
Purpose: Implementing Job class that is extended by Thing class
*/

//Note: It is optional for project 1 and 2 (Just implement constructor and getter and setter methods)

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Job extends Thing {
    double duration;
    ArrayList<String> requirements;

    enum Status {RUNNING, SUSPENDED, WAITING, DONE};
    private JButton suspendButton;
    private JButton cancelButton;
    private Status status;






    public Job(Scanner sc) {
        super(sc);
        duration = sc.hasNextDouble() ? sc.nextDouble() : 0.0d;
        requirements = new ArrayList<>();
        while(sc.hasNext()) {
            String txt = sc.next();
            requirements.add(txt);
        }
    }

    private void showStatus(Status st){
        status = st;
        switch(status){
            case RUNNING:
                suspendButton.setBackground(Color.GREEN);
                suspendButton.setText("Running");
                break;
            case SUSPENDED:
                suspendButton.setBackground(Color.YELLOW);
                suspendButton.setText("Suspended");
                break;
            case WAITING:
                suspendButton.setBackground(Color.ORANGE);
                suspendButton.setText("Waiting");
                break;
            case DONE:
                suspendButton.setBackground(Color.RED);
                suspendButton.setText("Done");
                break;
        }
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
