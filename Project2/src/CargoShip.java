/*
File Name: CargoShip.java
Date: 11-05-17
Author: Minseok Kwak
Purpose: CargoShip class is inherited by Ship class
*/

import java.util.Scanner;

public class CargoShip extends Ship {
    private double cargoValue;
    private double cargoVolume;
    private double cargoWeight;

    public CargoShip(Scanner sc) {
        super(sc);
        cargoValue = (sc.hasNextDouble()) ? sc.nextDouble() : 0.0d;
        cargoVolume = (sc.hasNextDouble()) ? sc.nextDouble() : 0.0d;
        cargoWeight = (sc.hasNextDouble()) ? sc.nextDouble() : 0.0d;
    }

    public double getCargoValue() {
        return cargoValue;
    }

    public void setCargoValue(double cargoValue) {
        if (cargoValue >= 0.0)
            this.cargoValue = cargoValue;
    }

    public double getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(double cargoVolume) {
        if (cargoVolume >= 0.0)
            this.cargoVolume = cargoVolume;
    }

    public double getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(double cargoWeight) {
        if(cargoWeight >= 0.0)
            this.cargoWeight = cargoWeight;
    }

    @Override
    public String toString() {
        return "Cargo Ship: " + super.toString();
    }
}//End CargoShip Class
