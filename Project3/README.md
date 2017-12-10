# Project 3

Implement threads and a GUI interface using advanced Java Swing classes.
The project will be graded according the criteria for the final project - see below.

#### Elaboration:

1. Required data the data structure specified in Project 1:
    - World has SeaPort's
    - SeaPort has Dock's, Ship's, and Person's
    - Dock has a Ship
    - Ship has Job's
    - PassengerShip
    - CargoShip
    - Person has a skill
    - Job requires skills- NEW CLASS for this project!
    - PortTime
2. Extend Project 2 to use the Swing class JTree effectively to display the contents of the data file. 
    - (Optional) Implement a JTable to also show the contents of the data file. There are lots of options here for extending your program.
3. Threads:
    - Implement a thread for each job representing a task that ship requires.
    - Use the synchronize directive to avoid race conditions and insure that a dock is performing the jobs for only one ship at a time.
      - the jobs of a ship in the queue should not be progressing
      - when all the jobs for a ship are done, the ship should leave the dock, allowing a ship from the que to dock
      - once the ship is docked, the ships jobs should all progress
      - in Project 4, the jobs will also require persons with appropriate skills.
    - The thread for each job should be started as the job is read in from the data file immediately after the entire data file has been read.
    - Use delays to simulate the progress of each job.
    - Use a JProgressBar for each job to display the progress of that job.
    - Use JButton's on the Job panel to allow the job to be suspended or cancelled.
4. As before, the GUI elements should be distinct (as appropriate) from the other classes in the program.
5. See the code at the end of this posting for some suggestions.

![1](https://user-images.githubusercontent.com/20687661/33797608-83c91f42-dd4e-11e7-8d1d-0574dce81c2d.png)
![2](https://user-images.githubusercontent.com/20687661/33797611-8dbb252c-dd4e-11e7-881a-39fcdcb06208.png)
