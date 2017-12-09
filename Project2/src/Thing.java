/*
File Name: Thing.java
Date: 11-05-2017
Author: Minseok Kwak
Purpose: Implementing Thing class that is parent class of World, SetPort, Dock, Ship
          and this class is implemented by Comparable interface
*/


import java.util.Scanner;

public class Thing implements Comparable<Thing> {
    private int index;
    private String name;
    private int parent;

    //Constructor with Scanner Parameter
    public Thing(Scanner sc) {
        if(sc != null) {
            name = (sc.hasNext()) ? sc.next() : "ERROR";
            index = (sc.hasNextInt()) ? sc.nextInt() : 0;
            parent = (sc.hasNextInt()) ? sc.nextInt() : 0;
        }

    }

    /*
        Getting and Setter
    */
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        if(index >= 0)
            this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        if(parent >= 0)
            this.parent = parent;
    }

    @Override
    public int compareTo(Thing o) {
        return (o.getParent() == this.getParent()) &&
                (o.getIndex() == this.getIndex()) &&
                (o.getName().equals(this.getName())) ? 1 : 0;
    }

    @Override
    public String toString() {
        return String.format("%s %d", name, index);
    }
}//End thing Class
