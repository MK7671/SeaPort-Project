/*
File Name: World.java
Date: 11-05-2017
Author: Minseok Kwak
Purpose: Implementing World class that is extended by Thing
         Assign and Get right Port, Dock, Ship, or Person by index
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class World extends Thing {
    private ArrayList<SeaPort> ports;
    private PortTime time;

    public World(Scanner sc) {
        super(sc);
        ports = new ArrayList<>();
    }


    /*
        Getter and Setter methods
    */

    public ArrayList<SeaPort> getPorts() {
        return ports;
    }

    public void setPorts(ArrayList<SeaPort> ports) {
        this.ports = ports;
    }

    public PortTime getTime() {
        return time;
    }

    public void setTime(PortTime time) {
        this.time = time;
    }

    // End Getter and Setter Methods



    // Get Sea Port by index
    public SeaPort getSeaPortByIndex (int x) {
        for (SeaPort msp: ports)
            if (msp.getIndex() == x)
                return msp;

        return null;
    } //End getSeaPortByIndex

    // Get Dock by Index
    public Dock getDockByIndex(int x) {
        for(SeaPort msp : ports)
            for(Dock d : msp.getDocks())
                if(d.getIndex() == x)
                    return d;

        return null;
    } //End getDockByIndex

    // Get Ship by Index
//    public Ship getShipByIndex (int x) {
//        for (SeaPort msp: ports)
//            for (Ship ms: msp.getShips())
//                if (ms.getIndex() == x)
//                    return ms;
//        return null;
//    } //End getShipByIndex

    //Get Ship by Index from the Hashmap
    public Ship getShipByIndex(int x, HashMap<Integer, Ship> shm) {
        if(shm.containsKey(x)) {
            return shm.get(x);
        }
        return null;
    }// END getShipByIndex

    // Assign Port
    public void assignPort(SeaPort port) {
        if(port != null) {
            ports.add(port);
        }
    } // End assignPort

    // Assign Dock
    public void assignDock(Dock dok) {
        for(SeaPort msp : ports) {
            if(msp.getIndex() == dok.getParent()) {
                msp.getDocks().add(dok);
            }
        }
    } // End assignDock

    // Asign Ship
    public void assignShip (Ship ms) {
        Dock md = getDockByIndex(ms.getParent());

        if (md == null) {
            getSeaPortByIndex(ms.getParent()).getShips().add(ms);
            getSeaPortByIndex(ms.getParent()).getQue().add(ms);
            return;
        }
        md.setShip(ms);
        getSeaPortByIndex (md.getParent()).getShips().add(ms);
    } // end method assignShip


    public void assignPerson(Person p) {
        for(SeaPort msp : ports) {
            if(msp.getIndex() == p.getParent()) {
                msp.getPersons().add(p);
                ConcurrentHashMap<Skill,Integer> rp = msp.getResourcePool();
                int count = rp.getOrDefault(p.skill, 0);
                rp.put(p.skill, count + 1);
            }
        }
    } // End assignPerson

    // THIS IS REALLY BAD MAYBE USE HASHMAP FOR SAVING PORTS, DOCKS, AND SHIPS!
    public void assignJob(Job j){
        //for aSPae
        for(SeaPort sp:ports){
            for(Ship ship:sp.getShips()){
                System.out.println();
                if(ship.getIndex() == j.getParent()){
                    ship.addJob(j);
                }
            }
        }

        //for aSPab
//        Dock dock = getDockByIndex(j.getParent());
//        dock.getShip().addJob(j);

    }

    public void validateSeaPortJobs(){
        ports.forEach(SeaPort::validateJobs);
    }
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(">>>>> The world:\n");

        for(SeaPort port : ports) {
            sb.append(port + "\n");
        }

        return sb.toString();
    }
}//End World Class
