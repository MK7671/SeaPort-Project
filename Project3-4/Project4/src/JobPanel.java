import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JobPanel extends JPanel {
//    JPanel parent;
    JLabel dockLabel;
    JLabel jobLabel;
    JLabel shipLabel;
    JPanel topInfoPanel;
    JobProgressBar progressBar;
    JPanel middleProgressPane;
    JButton doneButton;
    JButton cancelButton;
    JPanel bottomButtonPanel;
    JPanel mainPanel;

    public JobPanel(String dock, int currentJobNumber, int maxJobNumber) {

//        parent = cv;

        dockLabel = new JLabel(dock);

        dockLabel.setForeground(Color.GREEN);

        shipLabel = new JLabel("SHIP: ");
        shipLabel.setForeground(Color.GREEN);

        // Job name
        jobLabel = new JLabel("Job: "+currentJobNumber+"/"+maxJobNumber);
        jobLabel.setForeground(Color.GREEN);

        // Top Pane

        topInfoPanel = new JPanel();
        topInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topInfoPanel.setBackground(Color.BLACK);
        topInfoPanel.add(dockLabel);
        topInfoPanel.add(shipLabel);
        topInfoPanel.add(jobLabel);


        //Progress Bar
        progressBar = new JobProgressBar();

        // mid pane
        middleProgressPane = new JPanel();
        middleProgressPane.setBackground(Color.BLACK);
        middleProgressPane.add(progressBar);

        //Done and Cancel Buttons
        doneButton = new JButton("Pause");
        doneButton.setForeground(Color.GREEN);
        doneButton.setBackground(Color.BLACK);
        doneButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(1,1,1,1)));
        doneButton.addActionListener(e -> {
            boolean goFlag = progressBar.toggleGoFlag();
            if(goFlag) doneButton.setText("Pause");
            else doneButton.setText("Resume");
        });



        cancelButton = new JButton("Cancel");
        cancelButton.setForeground(Color.GREEN);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 1),
                BorderFactory.createEmptyBorder(1,1,1,1)));
        cancelButton.addActionListener(e -> progressBar.setKillCommand());

        // bottom panel
        bottomButtonPanel = new JPanel();
        bottomButtonPanel.setBackground(Color.BLACK);
        bottomButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomButtonPanel.add(doneButton);
        bottomButtonPanel.add(cancelButton);

//        setPreferredSize(new Dimension(50,50));
        setBackground(Color.BLACK);
        setLayout(new GridLayout(0,1));
        add(topInfoPanel);
        add(middleProgressPane);
        add(bottomButtonPanel);

        setBorder(BorderFactory.createLineBorder(Color.WHITE));

//        panels.add(mainPanel);
//        parent.add(mainPanel);


    }

    public void updateJob(int currentJob, int maxJob){
        jobLabel.setText(generateJobString(currentJob,maxJob));
        jobLabel.revalidate();
        jobLabel.repaint();
    }
    public void updateShip(String shipName){
        shipLabel.setText("Ship: "+shipName);
        shipLabel.revalidate();
        shipLabel.repaint();
    }

    private String generateJobString(int a, int b){
        return "Job: "+a+"/"+b;
    }

    public JobProgressBar getProgressBar() {
        return progressBar;
    }
}
