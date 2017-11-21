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
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class SeaPortProgram extends JFrame {
    World world;
    public static final long serialVersionUID = 11;
    JTextArea outPutTextArea = new JTextArea (30, 30);
    JComboBox <String> typeComboBox;
    JTextField searchField;
    JTextField fileField;
    private JFileChooser jfc;
    private File file;

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


        JButton readFileButton = new JButton("Read File");
        readFileButton.setFont(label);
        readFileButton.setForeground(Color.GREEN);
        readFileButton.setBackground(Color.BLACK);
        readFileButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(1,1,1,1)));

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(label);
        clearButton.setForeground(Color.GREEN);
        clearButton.setBackground(Color.BLACK);
        clearButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(2,2,2,2)));

        JButton searchButton = new JButton("Search");
        searchButton.setFont(label);
        searchButton.setForeground(Color.GREEN);
        searchButton.setBackground(Color.BLACK);
        searchButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(2,2,2,2)));

        JButton browseButton = new JButton("Browse");
        browseButton.setFont(label);
        browseButton.setForeground(Color.GREEN);
        browseButton.setBackground(Color.BLACK);
        browseButton.setBorder(BorderFactory.createCompoundBorder(
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


        typeComboBox = new JComboBox <String> ();
        typeComboBox.addItem("Name");
        typeComboBox.addItem("Index");
        typeComboBox.addItem("Skill");
        typeComboBox.setEditable(true);

        ((JTextField) typeComboBox.getEditor().getEditorComponent()).setForeground(Color.GREEN);
        ((JTextField) typeComboBox.getEditor().getEditorComponent()).setFont(label);
        ((JTextField) typeComboBox.getEditor().getEditorComponent()).setBackground(Color.BLACK);


        JPanel filePanelTop = new JPanel();
        filePanelTop.add(fileField);
        filePanelTop.add(browseButton);
        filePanelTop.setBackground(Color.black);

        JPanel filePanelBottom = new JPanel();
        filePanelBottom.add(readFileButton);
        filePanelBottom.add(clearButton);
        filePanelBottom.setBackground(Color.black);

        JPanel filePanel = new JPanel();
        filePanel.setLayout (new GridLayout (0, 1)); // ND
        filePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),
                "Import File", TitledBorder.LEFT, TitledBorder.TOP, label, Color.GREEN));
        filePanel.add(filePanelTop);
        filePanel.add(filePanelBottom);
        filePanel.setMaximumSize(filePanel.getPreferredSize());
        filePanel.setBackground(Color.black);

        JPanel searchPanelTop = new JPanel();
        searchPanelTop.add(searchField);
        searchPanelTop.add(typeComboBox);
        searchPanelTop.setBackground(Color.black);

        JPanel searchPanelBottom = new JPanel();
        searchPanelBottom.add(searchButton);
        searchPanelBottom.setBackground(Color.black);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout (new GridLayout (0, 1)); // ND
        searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),
                "Search Objects", TitledBorder.LEFT, TitledBorder.TOP, label, Color.GREEN));
        searchPanel.setBackground(Color.black);
        searchPanel.add(searchPanelTop);
        searchPanel.add(searchPanelBottom);
        searchPanel.setMaximumSize(searchPanel.getPreferredSize());

        JPanel leftMenu = new JPanel();
        leftMenu.setBackground(Color.black);
        JPanel TopMenuG = new JPanel ();
        TopMenuG.setLayout (new GridLayout (1, 0));
        // Adding more options in there
        TopMenuG.add(filePanel);
        TopMenuG.add(searchPanel);
        leftMenu.add (TopMenuG);

        add(scrollPane, BorderLayout.CENTER);
        add(leftMenu, BorderLayout.NORTH);

        pack();
        setVisible(true);

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
        });

        readFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world = new World(null);
                outPutTextArea.setText("");
                outPutTextArea.append("Starting New Search\n\n");
                if(file != null) {
                    read(file);
                } else {
                    JOptionPane.showMessageDialog(null, "Browse file first!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchResult();
            }
        });


    }

    public static void main(String[] args){
        SeaPortProgram run = new SeaPortProgram();
    }

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

            while((line = bufferedReader.readLine()) != null) {
                if(!line.startsWith("//")) {
                    process(line);
                }
            }
            outPutTextArea.append(world.toString());
            outPutTextArea.append("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }// End read Method

    // Evaluate each line and then assign each entity in right data structure
    private void process(String line) {
        System.out.println ("Processing >" + line + "<");
        Scanner sc = new Scanner(line);
        if (!sc.hasNext())
            return;
        switch (sc.next()) {
            case "port":
                world.assignPort(new SeaPort(sc));
                System.out.println( "SeaPort Assigned");
                break;

            case "dock":
                world.assignDock(new Dock(sc));
                System.out.println("Dock Assigned");
                break;

            case "ship":
                world.assignShip(new Ship(sc));
                System.out.println("Ship Assigned");
                break;

            case "pship":
                world.assignShip(new PassengerShip(sc));
                System.out.println("Passenger Ship Assigned");
                break;

            case "cship":
                world.assignShip(new CargoShip(sc));
                System.out.println("Cargp Ship Assigned");
                break;

            case "person":
                world.assignPerson(new Person(sc));
                System.out.println("Person Assigned");
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