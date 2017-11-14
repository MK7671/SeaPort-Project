Elaboration:

1. Required data structure - the data structure specified in Project 1:
      World has SeaPort's
      SeaPort has Dock's, Ship's, and Person's
      Dock has a Ship
      Ship has Job's
      PassengerShip
      CargoShip
      Person has a skill
      Job requires skills - optional until Project 3
      PortTime
2. Use the HashMap class to support efficient linking of the classes used in Project 1.
    1. The instances of the hash map class should be local to the readFile (Scanner) method.
    2. These instances should be passed as explicit parameters to other methods used when reading the data file.
        1. For example, the body of the methods like the following should be replaced to effectively use a <Integer, Ship> hash map, the             surrounding code needs to support this structure:
            Ship getShipByIndex (int x, java.util.HashMap <Integer, Ship> hms) {
              return hms.get(x);
            } // end getDockByIndex
            
        2. Since the body of this method has become trivial, perhaps the call to this method can be simply replaced by the get method of the HashMap.
        3. Your code should be sure to handle a null return from this call gracefully.
    3. The instances should be released (go out of scope, hence available for garbage collection) when the readFile method returns.
    4. Comments: The idea here, besides getting some experience with an interesting JDK Collections class, is to change the operation of searching for an item with a particular index from an O(N) operation, ie searching through the entire data structure to see if the code can find the parent index parameter, to an O(1) operation, a hash map lookup. Of course, this isn't so very interesting in such a small    program, but consider what might happen with hundreds of ports, thousands of ships, and perhaps millions of persons and jobs.
    5. Comments: Also, after the readFile operation, the indices are no longer interesting, and could be completely eliminated from the program. In this program, removing the index references could be accomplished by removing those variables from the parent class, Thing.
3. Implement comparators to support sorting:
    - ships in port que ArrayList's by weight, length, width, draft within their port que
    - all items withing their ArrayList's by name
    - OPTIONALLY: sorting by any other field that can be compared
    - The sorting should be within the parent ArrayList
4. Extend the GUI from Project 1 to allow the user to:
    - sort by the comparators defined in part 2.
5. Again, the GUI elements should be distinct from the other classes in the program.
