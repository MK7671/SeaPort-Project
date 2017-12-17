/*
File Name: QueComparator.java
Date: 11-15-2017
Author: Minseok Kwak
Purpose: Implementing QueComparator that implements by Comparator<Ship> to sort Ques by weight,
         length, width, or draft, and Descending or Ascending order
*/

import java.util.Comparator;

public class QueComparator implements Comparator<Ship>{
    private String criterion;
    private String order;

    // Constructor that has two parameters so we can know which criteria the user wants to use
    // to sort the ship que
    public QueComparator(String criterion, String order) {
        this.criterion = criterion;
        this.order = order;
    } // End Constructor

    // compare methods to compare two objects by criterion that user choose
    @Override
    public int compare(Ship o1, Ship o2) {
        int resultValue = -999;
        switch (this.criterion) {
            case "Weight":
                if (o1.getWeight() > o2.getWeight()) {
                    resultValue =  1;
                } else if (o1.getWeight() < o2.getWeight()) {
                    resultValue =  -1;
                } else {
                    resultValue =  0;
                }
                break;

            case "Length":
                if (o1.getLength() > o2.getLength()) {
                    resultValue =  1;
                } else if (o1.getLength() < o2.getLength()) {
                    resultValue =  -1;
                } else {
                    resultValue = 0;
                }
                break;

            case "Width":
                if (o1.getWidth() > o2.getWidth()) {
                    resultValue =  1;
                } else if (o1.getWidth() < o2.getWidth()) {
                    resultValue = -1;
                } else {
                    resultValue =  0;
                }
                break;

            case "Draft":
                if (o1.getDraft() > o2.getDraft()) {
                    resultValue =  1;
                } else if (o1.getDraft() < o2.getDraft()) {
                    resultValue =  -1;
                } else {
                    resultValue =  0;
                }
                break;
        }
        // If the user wants to ascending order, then return the resultValue times -1
        return order.equals("Descending Order") ? -1 * resultValue : resultValue;
    }
}
