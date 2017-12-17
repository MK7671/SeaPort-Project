/*
File Name: NameComparator.java
Date: 11-15-2017
Author: Minseok Kwak
Purpose: Implementing NameComparator that implements by Comparator<Thing> to sort rest of items by name
*/
import java.util.Comparator;

public class NameComparator implements Comparator<Thing>{

    @Override
    public int compare(Thing o1, Thing o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
