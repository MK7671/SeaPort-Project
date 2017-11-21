/*
File Name: Dock.java
Date: 11-05-2017
Author: Minseok Kwak
Purpose: Implementing Dock class that is extended by Thing class
*/

import java.util.Scanner;

public class Dock extends Thing {
    Ship ship;

    // Constructor with Scanner parameter
    public Dock(Scanner sc) {
        super(sc);
    }

    /*
        Getter and Setter Methods
    */
    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("  Dock: " + super.toString() + "\n");
        sb.append("    Ship: " + ship.toString() + "\n");

        return sb.toString();
    }
}//End Dock Class
