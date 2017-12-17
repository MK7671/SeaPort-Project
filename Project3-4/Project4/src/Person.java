/*
File Name: Person.java
Date: 11-05-2017
Author: Minseok Kwak
Purpose: Implementing Person class that is extended by Thing class
*/

import java.util.Scanner;

public class Person extends Thing {
    Skill skill;

    public Person(Scanner sc) {
        super(sc);
        skill = (sc.hasNext()) ? new Skill(sc.next()) : new Skill("ERROR");
    }

    /*
        Getter and Setter Methods
     */

    public String getSkill() {
        return skill.getName();
    }

    public void setSkill(String skill) {
        this.skill = new Skill(skill);
    }

    public void setSkill(Skill skill){
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Person: " + super.toString() + " " + getSkill();
    }
}//End Person Class
