/*
File Name: SeaPort.java
Date: 11-05-2017
Author: Minseok Kwak
Purpose: Implementing SeaPort class that is extended by Thing class.
         It has four ArrayList fields; docks, que, ships, nad person
*/

import java.util.ArrayList;
import java.util.Scanner;

public class SeaPort extends Thing {
    ArrayList<Dock> docks;
    ArrayList<Ship> que;
    ArrayList<Ship> ships;
    ArrayList<Person> persons;


    public SeaPort(Scanner sc) {
        super(sc);
        docks = new ArrayList<>();
        que = new ArrayList<>();
        ships = new ArrayList<>();
        persons = new ArrayList<>();
    }

    /*
        Getter and Setter methods
    */

    public ArrayList<Dock> getDocks() {
        return docks;
    }

    public void setDocks(ArrayList<Dock> docks) {
        this.docks = docks;
    }

    public ArrayList<Ship> getQue() {
        return que;
    }

    public void setQue(ArrayList<Ship> que) {
        this.que = que;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public String toString () {
        String st = "\n\nSeaPort: " + super.toString() + "\n";
        for (Dock md: docks) st += "\n" + md;
        st += "\n --- List of all ships in que:";
        for (Ship ms: que ) st += "\n   > " + ms;
        st += "\n\n --- List of all ships:";
        for (Ship ms: ships) st += "\n   > " + ms;
        st += "\n\n --- List of all persons:";
        for (Person mp: persons) st += "\n   > " + mp;

        return st;
    } // end method toString
}//End SeaPort Class
