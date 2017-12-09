/*
File Name: Person.java
Date: 11-05-2017
Author: Minseok Kwak
Purpose: Implementing Person class that is extended by Thing class
*/

import java.util.Scanner;

public class Person extends Thing {
    String skill;

    public Person(Scanner sc) {
        super(sc);
        skill = (sc.hasNext()) ? sc.next() : "ERROR";
    }

    /*
        Getter and Setter Methods
     */

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Person: " + super.toString() + " " + getSkill();
    }
}//End Person Class
