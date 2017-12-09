/*
File Name: Dock.java
Date: 11-05-2017
Author: Minseok Kwak
Purpose: Implementing SeaPortProgram class that is extended by JFrame class
Update:
        1. I changed the layout again regarding upcoming project 3 and 4 that requires to use JTree
        and for better looking for user to use the program.
        2. Instead of clicking read button and searching for file every time, I add Browse button
           and show path of the file so when user searches specific object and did not find it,
           The user just simply clicks Read File to read the data quickly.
        3. Add Display methods to display all items in each HashMap that includes Ship, Person, and Dock
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class SeaPortProgram extends JFrame {
    World world;
    public static final long serialVersionUID = 11;
    JTextArea outPutTextArea = new JTextArea (30, 30);
    JComboBox <String> typeComboBox, sortIndexComboBox, sortDecendOrAscend;
    JTextField searchField;
    JTextField fileField;
    private JFileChooser jfc;
    private File file;

    // Project 3: Extend Project 2 to use the Swing class JTree effectively to display the contents of the data file.
    JTree tree;
    JScrollPane treePane;
    JButton expandRow;
    JButton collapseRow;
    JPanel treeButtonPanel, treePanel;

    JPanel progressPanel;
    JScrollPane progressScroll;
    JobTable jobTable;
    JTable jTable;
    String[] header = {"Ship Name", "Job Name"};
    //////////////////////////////////

    public SeaPortProgram(){

        setTitle ("Sea Port Project II");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        Font label = new Font("TimesRoman", Font.BOLD, 13);
        JScrollPane scrollPane = new JScrollPane(outPutTextArea);
        // Determines when the vertical scrollbar appears in the scrollpane.
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // Determines when the horizontal scrollbar appears in the scrollpane.
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outPutTextArea.setBackground(Color.black);
        outPutTextArea.setForeground(Color.green);

        /*********************************************************************************
         *   All Buttons                                                                 *
         *********************************************************************************/

        JButton readFileButton = new JButton(" Read File ");
        readFileButton.setFont(label);
        readFileButton.setForeground(Color.GREEN);
        readFileButton.setBackground(Color.BLACK);
        readFileButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(1,1,1,1)));

        JButton clearButton = new JButton(" Clear ");
        clearButton.setFont(label);
        clearButton.setForeground(Color.GREEN);
        clearButton.setBackground(Color.BLACK);
        clearButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(2,2,2,2)));

        JButton searchButton = new JButton(" Search ");
        searchButton.setFont(label);
        searchButton.setForeground(Color.GREEN);
        searchButton.setBackground(Color.BLACK);
        searchButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(2,2,2,2)));

        JButton browseButton = new JButton(" Browse ");
        browseButton.setFont(label);
        browseButton.setForeground(Color.GREEN);
        browseButton.setBackground(Color.BLACK);
        browseButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(2,2,2,2)));

        JButton sortButton = new JButton(" Sort ");
        sortButton.setFont(label);
        sortButton.setForeground(Color.GREEN);
        sortButton.setBackground(Color.BLACK);
        sortButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(2,2,2,2)));


        searchField = new JTextField(11);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(2,2,2,2)));
        searchField.setBackground(Color.BLACK);
        searchField.setForeground(Color.green);
        fileField = new JTextField(11);
        fileField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(2,2,2,2)));
        fileField.setBackground(Color.BLACK);
        fileField.setForeground(Color.green);

        // ComboBox index for Search starts
        typeComboBox = new JComboBox <String> ();
        typeComboBox.addItem("Name");
        typeComboBox.addItem("Index");
        typeComboBox.addItem("Skill");
        typeComboBox.setEditable(true);

        ((JTextField) typeComboBox.getEditor().getEditorComponent()).setForeground(Color.GREEN);
        ((JTextField) typeComboBox.getEditor().getEditorComponent()).setFont(label);
        ((JTextField) typeComboBox.getEditor().getEditorComponent()).setBackground(Color.BLACK);
        // ComboBox index for Search ends


        // ComboBox for Sort starts
        sortIndexComboBox = new JComboBox<String>();
        sortIndexComboBox.addItem("Weight");
        sortIndexComboBox.addItem("Length");
        sortIndexComboBox.addItem("Width");
        sortIndexComboBox.addItem("Draft");
        sortIndexComboBox.setEditable(true);

        ((JTextField) sortIndexComboBox.getEditor().getEditorComponent()).setForeground(Color.GREEN);
        ((JTextField) sortIndexComboBox.getEditor().getEditorComponent()).setFont(label);
        ((JTextField) sortIndexComboBox.getEditor().getEditorComponent()).setBackground(Color.BLACK);

        //ComboBox for sort (Descending or ascending order)
        sortDecendOrAscend = new JComboBox<String>();
        sortDecendOrAscend.addItem("Descending Order");
        sortDecendOrAscend.addItem("Ascending Order");
        sortDecendOrAscend.setEditable(true);

        ((JTextField) sortDecendOrAscend.getEditor().getEditorComponent()).setForeground(Color.GREEN);
        ((JTextField) sortDecendOrAscend.getEditor().getEditorComponent()).setFont(label);
        ((JTextField) sortDecendOrAscend.getEditor().getEditorComponent()).setBackground(Color.BLACK);
        // ComboBox for Sort ends

        /*********************************************************************************
         *   All Panels                                                                  *
         *********************************************************************************/

        // File Panel Start
        JPanel filePanelTop = new JPanel();
        filePanelTop.add(fileField);
        filePanelTop.add(browseButton);
        filePanelTop.setBackground(Color.black);

        JPanel filePanelBottom = new JPanel();
        filePanelBottom.add(readFileButton);
        filePanelBottom.add(clearButton);
        filePanelBottom.setBackground(Color.black);

        JPanel filePanel = new JPanel();
        filePanel.setLayout (new GridLayout (0, 1));
        filePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),
                "Import File", TitledBorder.LEFT, TitledBorder.TOP, label, Color.GREEN));
        filePanel.add(filePanelTop);
        filePanel.add(filePanelBottom);
        filePanel.setMaximumSize(filePanel.getPreferredSize());
        filePanel.setBackground(Color.black);
        // Final Panel End

        // Search Label Start
        JLabel sortInstLabel = new JLabel("<html>* Sort Que by the Field You Choose<br>" +
                                                        "* Sort Rest of Items by Name</html>");
        sortInstLabel.setForeground(Color.green);
        // Search Label End

        // Search Panel Start
        JPanel searchPanelTop = new JPanel();
        searchPanelTop.add(searchField);
        searchPanelTop.add(typeComboBox);
        searchPanelTop.setBackground(Color.black);

        JPanel searchPanelBottom = new JPanel();
        searchPanelBottom.add(searchButton);
        searchPanelBottom.setBackground(Color.black);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout (new GridLayout (0, 1));
        searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),
                "Search Objects", TitledBorder.LEFT, TitledBorder.TOP, label, Color.GREEN));
        searchPanel.setBackground(Color.black);
        searchPanel.add(searchPanelTop);
        searchPanel.add(searchPanelBottom);
        searchPanel.setMaximumSize(searchPanel.getPreferredSize());
        //Search Panel End

        //Sort Panel Start
        JPanel sortPanelTop = new JPanel();
        sortPanelTop.setBackground(Color.black);
        sortPanelTop.add(sortInstLabel);

        JPanel sortPanelBottom = new JPanel();
        sortPanelBottom.add(sortIndexComboBox);
        sortPanelBottom.add(sortDecendOrAscend);
        sortPanelBottom.add(sortButton);
        sortPanelBottom.setBackground(Color.black);

        JPanel sortPanel = new JPanel();
        sortPanel.setLayout (new GridLayout (0, 1));
        sortPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),
                "Sort Objects", TitledBorder.LEFT, TitledBorder.TOP, label, Color.GREEN));
        sortPanel.setBackground(Color.black);
        sortPanel.add(sortPanelTop);
        sortPanel.add(sortPanelBottom);
        sortPanel.setMaximumSize(sortPanel.getPreferredSize());
        // Sort Panel End


        JPanel topMenu = new JPanel();
        topMenu.setBackground(Color.black);
        JPanel topMenuG = new JPanel ();
        topMenuG.setLayout (new GridLayout (1, 0));
        // Adding more options in there
        topMenuG.add(filePanel);
        topMenuG.add(searchPanel);
        topMenuG.add(sortPanel);
        topMenu.add (topMenuG);

        add(scrollPane, BorderLayout.CENTER);
        add(topMenu, BorderLayout.NORTH);

        ////////////////////////////////////////////////////////JTree
        treePanel = new JPanel(new BorderLayout());
        treePanel.setBorder(new LineBorder(Color.white, 1));
        treePanel.setBackground(Color.black);
        treePanel.setMaximumSize(treePanel.getPreferredSize());
        add(treePanel, BorderLayout.WEST);

        ////////////////////////////////////////////////////////
        pack();
        setVisible(true);

        /*********************************************************************************
         *   actionListners for all buttons                                              *
         *********************************************************************************/

        // addActionListener for browseButton
        browseButton.addActionListener(new ActionListener() {
                    //Handle open button action.
                    public void actionPerformed(ActionEvent e) {
                        searchFile();
                    }
                });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outPutTextArea.setText("");
            }
        }); // End addActionListener for browseButton

        // addActionListener for readFileButton
        readFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world = new World(null);
                outPutTextArea.setText("");
                outPutTextArea.append("Starting New Search\n\n");
                if(file != null) {
                    read(file);
                    ////////////////////////////////////////////////////////// Project 3
                    drawTree();

                    ////////////////////////////////////////////////////////
                } else {
                    JOptionPane.showMessageDialog(null, "Browse file first!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        }); // End addActionListener for readFileButton

        // addActionListener for searchButton
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchResult();
            }
        }); // End addActionListener for searchButton

        // addActionListener for SortButton
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // call sortItems method with criterion that user set
                outPutTextArea.setText("");
                sortItems(sortIndexComboBox.getItemAt(sortIndexComboBox.getSelectedIndex()),
                        sortDecendOrAscend.getItemAt(sortDecendOrAscend.getSelectedIndex()));
            }
        }); // End addActionListener for SortButton


    }
    //////////////////////////////////////////////////////// Draw JTree for project 3
    private void drawTree() {
        DefaultMutableTreeNode root = createNodes("World");

        tree = new JTree(root);
        tree.setBackground(Color.black);
        tree.setForeground(Color.green);
        treePane = new JScrollPane(tree);
        treePane.setBackground(Color.black);
        // Add two buttons
        // there are collapseRow() expandRow() methods in Jtree class
        expandRow = new JButton("Expand All Rows");
        expandRow.setForeground(Color.GREEN);
        expandRow.setBackground(Color.BLACK);
        expandRow.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(2,2,2,2)));

        collapseRow = new JButton("Collapse All rows");
        collapseRow.setForeground(Color.GREEN);
        collapseRow.setBackground(Color.BLACK);
        collapseRow.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(2,2,2,2)));

        treeButtonPanel = new JPanel();
        treeButtonPanel.setBorder(new LineBorder(Color.white));
        treeButtonPanel.setBackground(Color.black);
        treeButtonPanel.add(expandRow);
        treeButtonPanel.add(collapseRow);
        treePanel.add(treeButtonPanel, BorderLayout.SOUTH);
        treePanel.add(treePane, BorderLayout.CENTER);


        // addActionListener for expandRow and collapseRow buttons
        expandRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < tree.getRowCount(); i++) {
                    tree.expandRow(i);
                }
            }
        });

        collapseRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < tree.getRowCount(); i++) {
                    tree.collapseRow(i);
                }
            }
        });



    }
    ////////////////////////////////////////////////////////

    // Add Node to show data structure in organizing way
    private DefaultMutableTreeNode createNodes(String world) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(world);
        DefaultMutableTreeNode seaPort;
        for(SeaPort s : this.world.getPorts()) {
            seaPort = new DefaultMutableTreeNode("SeaPort: " + s.getName());
            root.add(seaPort);
            seaPort.add(addBranchDock(s.getDocks(), "Docks"));
            seaPort.add(addBranch(s.getQue(), "Que"));
            seaPort.add(addBranch(s.getShips(), "Ships"));
            seaPort.add(addBranch(s.getPersons(), "Persons"));

        }

        return root;
    }
    ////////////////////////////////////////////////////////

    private MutableTreeNode addBranchDock(ArrayList<Dock> docks, String name) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(name);
        for(Dock d : docks) {
            String txt;
            if(d.getShip() != null) {
                txt = "Ship: " + d.getShip().getName();
            } else {
                txt = "(Empty)";
            }
            root.add(new DefaultMutableTreeNode(txt));
        }

        return root;
    }
    ////////////////////////////////////////////////////////

    private <T extends Thing> DefaultMutableTreeNode addBranch(ArrayList<T> lst, String name) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(name);
        for(T ele: lst) {
            String s = ele.getName();
            root.add(new DefaultMutableTreeNode(s));
        }
        return root;
    }
    ////////////////////////////////////////////////////////

    public static void main(String[] args){
        SeaPortProgram run = new SeaPortProgram();
    }
    ////////////////////////////////////////////////////////

    /*********************************************************************************
     *   All specific functions for Buttons                                          *
     *********************************************************************************/

    // SortItems: get criterion and order from sort combobox and  sort the data in ArrayLists
    //            in each seaport
    private void sortItems(String criterion, String order) {
        for(SeaPort sp: world.getPorts()) {
            sp.getDocks().sort(new NameComparator());
            sp.getPersons().sort(new NameComparator());
            sp.getShips().sort(new NameComparator());
            sp.getQue().sort(new QueComparator(criterion, order));

            // Check if it is sorted or not
            System.out.println("Sorted By " + criterion + " (" + order + ")\n");
            for(Ship s : sp.getQue()) {
                System.out.println(s.toString() + " Weight: " +s.getWeight()
                        + " Length: " + s.getLength()
                        + " Width: " + s.getWidth()
                        + " Draft: " + s.getDraft());
            }
            System.out.println();
        }
        outPutTextArea.setText(world.toString());
    } // End sortItmes method

    // searchFile: Using JFileChooser to select right file to read
    private void searchFile() {
        try {
            jfc = new JFileChooser("."); // Start selection at dot!
            jfc.setDialogTitle("Proejct 1: Select file");
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            // Pops up an "Open File" file chooser dialog
            int ret = jfc.showOpenDialog(null);
            if(ret != JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(null, "No File Selected!","Warning" ,JOptionPane.WARNING_MESSAGE);
            } else {
                file = jfc.getSelectedFile();
                //This is where a real application would open the file.
                fileField.setText(file.toString());
            }
        } catch(Exception f) {
            JOptionPane.showMessageDialog(null, f.getMessage());
        }
    }// END searchFile method


    // Method to Read File
    private void read(File file) {
        try {
            FileReader f = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(f);
            String line = "";
            // Proejct2: Add three HashMap so I can pass those HashMaps as explicit parameters
            //           to process method to save specific data in there
            HashMap<Integer, Ship> shipHashMap = new HashMap<>();
            HashMap<Integer, Person> personHashMap = new HashMap<>();
            HashMap<Integer, Dock> dockHashMap = new HashMap<>();


            while((line = bufferedReader.readLine()) != null) {
                if(!line.startsWith("//")) {
                    // pass those local HashMap variables as explicit parameterst to process method
                    process(line, shipHashMap, personHashMap, dockHashMap);
                }
            }
            System.out.println();
            displayShipInfo(shipHashMap);
            displayPersonInfo(personHashMap);
            displayDockInfo(dockHashMap);
            outPutTextArea.append(world.toString());
            outPutTextArea.append("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }// End read Method

    // Add three display methods to check if the data is stored well in each HashMap
    private void displayDockInfo(HashMap<Integer, Dock> dockHashMap) {
        System.out.println("Information about Dock that saved in hashMap, \"dockHashMap\"");
        for(int i: dockHashMap.keySet()) {
            System.out.println(dockHashMap.get(i).toString());
        }
        System.out.println("\n");
    }

    private void displayPersonInfo(HashMap<Integer, Person> personHashMap) {
        System.out.println("Information about Person that saved in hashMap, \"personHashMap\"");
        for(int i: personHashMap.keySet()) {
            System.out.println(personHashMap.get(i).toString());
        }
        System.out.println("\n");
    }

    private void displayShipInfo(HashMap<Integer, Ship> shipHashMap) {
        System.out.println("Information about Ship that saved in hashMap, \"shipHashMap\"");
        for(int i: shipHashMap.keySet()) {
            System.out.println(shipHashMap.get(i).toString());
        }
        System.out.println("\n");
    } // End display methods

    // Evaluate each line and then assign each entity in right data structure
    private void process(String line, HashMap<Integer, Ship> shm, HashMap<Integer, Person> phm,
                         HashMap<Integer, Dock> dhm) {
        System.out.println ("Processing >" + line + "<");
        Scanner sc = new Scanner(line);
        if (!sc.hasNext())
            return;
        // Update for Proejct2: Distribute Ship, Person, and Dock to right HashMap
        switch (sc.next()) {
            case "port":
                world.assignPort(new SeaPort(sc));
                //System.out.println( "SeaPort Assigned");
                break;

            case "dock":
                Dock dk = new Dock(sc);
                dhm.put(dk.getIndex(), dk);
                world.assignDock(dk);
                //System.out.println("Dock Assigned");
                //world.assignDock(new Dock(sc));
                break;

            case "ship":
                Ship sh = new Ship(sc);
                shm.put(sh.getIndex(), sh);
                world.assignShip(sh);
                //world.assignShip(new Ship(sc));
                //System.out.println("Ship Assigned");
                break;

            case "pship":
                Ship psh = new PassengerShip(sc);
                shm.put(psh.getIndex(), psh);
                world.assignShip(psh);
                //world.assignShip(new PassengerShip(sc));
                //System.out.println("Passenger Ship Assigned");
                break;

            case "cship":
                Ship csh = new CargoShip(sc);
                shm.put(csh.getIndex(), csh);
                world.assignShip(csh);
                //world.assignShip(new CargoShip(sc));
                //System.out.println("Cargp Ship Assigned");
                break;

            case "person":
                Person p = new Person(sc);
                phm.put(p.getIndex(), p);
                world.assignPerson(p);
                //world.assignPerson(new Person(sc));
                //System.out.println("Person Assigned");
                break;

            case "job":
                Job job = new Job(sc);
                world.assignJob(job, shm.get(job.getParent()));
                break;

            default:
                break;
        }
    } // End process method

    // searchResult Method to show the result of search by user
    private void searchResult() {
        StringBuffer result = new StringBuffer();
        if(outPutTextArea.getText() == null || outPutTextArea.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Read file first before doing specific search");

        } else {
            ArrayList<SeaPort> sp = world.getPorts();
            switch (typeComboBox.getSelectedIndex()) {
                // Name
                case 0:
                    String name = searchField.getText();
                    outPutTextArea.setText("");
                    for (SeaPort port : sp) {
                        ArrayList<Dock> dok = port.getDocks();
                        ArrayList<Ship> ships = port.getShips();
                        ArrayList<Ship> que = port.getQue();
                        ArrayList<Person> person = port.getPersons();
                        searchByName(dok, result, name);
                        searchByName(ships, result, name);
                        searchByName(que, result, name);
                        searchByName(person, result, name);
                    }
                    if(result.toString().equals("") || result.toString() == null) {
                        JOptionPane.showMessageDialog(null, "The name, \"" + name + "\" is not found");
                    } else {
                        outPutTextArea.setText(result.toString());
                    }
                    break;
                // Index
                case 1:
                    try {
                        int index = Integer.parseInt(searchField.getText());
                        outPutTextArea.setText("");
                        for (SeaPort port : sp) {
                            ArrayList<Dock> dok = port.getDocks();
                            ArrayList<Ship> ships = port.getShips();
                            ArrayList<Ship> que = port.getQue();
                            ArrayList<Person> person = port.getPersons();
                            searchByIndex(dok, result, index);
                            searchByIndex(ships, result, index);
                            searchByIndex(que, result, index);
                            searchByIndex(person, result, index);
                        }
                        if(result.toString().equals("") || result.toString() == null) {
                            JOptionPane.showMessageDialog(null, "The index, \"" + index + "\" is not found");
                        } else {
                            outPutTextArea.setText(result.toString());
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "ERROR: " +
                                e.getMessage() + "cannot be converted to integer value" +
                                "\nPlease type numeric value (only integer).");
                    }
                    break;

                // Skill
                case 2:
                    String skill = searchField.getText();
                    outPutTextArea.setText("");
                    for (SeaPort port : sp) {
                        ArrayList<Person> people = port.getPersons();
                        for(Person p : people) {
                            if(p.getSkill().equalsIgnoreCase(skill)) {
                                result.append(p.toString() + "\n");
                            }
                        }
                    }
                    if(result.toString().equals("") || result.toString() == null) {
                        JOptionPane.showMessageDialog(null, "The skill, \"" + skill + "\" is not found");
                    } else {
                        outPutTextArea.setText(result.toString());
                    }
                    break;

                default:
                    break;
            }
        }

    }

    // searchByIndex find out index in dock, ship, or person, and then get string of that
    // By using generics, this can iterate the ArrayList without defining specific data type
    private <T> void searchByIndex(ArrayList<T> arr, StringBuffer result, int index) {
        boolean flag = false;
        for(T ele: arr) {
            if(flag) {
                break;
            }
            if(ele instanceof Dock && ((Dock) ele).getIndex() == index) {
                result.append(ele.toString() + "\n");
                flag = true;
            } else if(ele instanceof Ship && ((Ship) ele).getIndex() == index) {
                result.append(ele.toString() + "\n");
                flag = true;
            } else if(ele instanceof Person && ((Person) ele).getIndex() == index) {
                result.append(ele.toString() + "\n");
                flag = true;
            }
        }
    }

    // searchByName find out name in dock, ship, or person
    // By using generics, this can iterate the ArrayList without defining specific data type
    private <T> void searchByName(ArrayList<T> arr, StringBuffer result, String name) {
        for(T ele: arr) {
            if(ele instanceof Dock && ((Dock) ele).getName().equalsIgnoreCase(name)) {
                result.append(ele.toString() + "\n");
            } else if(ele instanceof Ship && ((Ship) ele).getName().equalsIgnoreCase(name)) {
                result.append(ele.toString() + "\n");
            } else if(ele instanceof Person && ((Person) ele).getName().equalsIgnoreCase(name)) {
                result.append(ele.toString() + "\n");
            }
        }
    }
} // end class Treasure