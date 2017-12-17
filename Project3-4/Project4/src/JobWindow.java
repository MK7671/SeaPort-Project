import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JobWindow extends JFrame {
    World world;
    Font label;
    JScrollPane mainScrollPanel;
    int docNumber;
    List<JobWorker> worker2s;
    HashMap<SeaPort,JScrollPane> seaPortScrollPanels;
    ConcurrentHashMap<SeaPort, ResourcePoolPanel> seaPortResourcePoolPanelMap;
    public JobWindow(World world) {
        this.world = world;
        setBackground(Color.BLACK);
        label = new Font("TimesRoman", Font.BOLD, 13);
        seaPortScrollPanels = new HashMap<>();
        seaPortResourcePoolPanelMap = new ConcurrentHashMap<>();
        worker2s = new ArrayList<>();
        for(SeaPort seaPort:world.getPorts())docNumber+=seaPort.getDocks().size();


        setTitle ("Jobs");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        pack();
        setVisible(true);
        executeWarpDrive();

    }







    private void executeWarpDrive(){

        mainScrollPanel = new JScrollPane();
        JPanel mainPanel= new JPanel();
        mainPanel.setLayout(new GridLayout(0,world.getPorts().size()));
        mainPanel.setBackground(Color.BLACK);
        mainScrollPanel.setViewportView(mainPanel);
        mainScrollPanel.setBackground(Color.BLACK);
        mainScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        mainScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        mainScrollPanel.setPreferredSize(new Dimension(800,500));
        mainPanel.setLayout(new GridLayout(1,0));
        for(SeaPort seaPort: world.getPorts()){
            JPanel panel =  new JPanel();
            panel.setLayout(new GridLayout(0,1));

            panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
            seaPortResourcePoolPanelMap.put(seaPort, new ResourcePoolPanel(seaPort.resourcePool));
            panel.add(seaPortResourcePoolPanelMap.get(seaPort));

            boolean hack_n_rig = true; // this is really bad code...
            for(Dock dock: seaPort.getDocks()){
//                if(hack_n_rig && dock.ships.size() > 0 && dock.ships.get(0).getJobs().size() > 0){
//                    dock.ships.get(0).jobs.get(0).setReady(true);
//                    hack_n_rig=false;
//                }
                dockLoop(seaPort,dock,panel,seaPortResourcePoolPanelMap.get(seaPort));
            }
            mainPanel.add(generateSeaPortPanel(seaPort.getName(),panel));
        }

        startWorker(worker2s);
        add(mainScrollPanel);
        this.pack();

    }



    private void dockLoop(SeaPort seaPort, Dock dock,JPanel sideJobsPanel, ResourcePoolPanel resourcePoolPanel){
        ArrayList<JPanel>panels = new ArrayList<>();
        JobPanel jobPanel = new JobPanel(dock.getName(),0,0);
        panels.add(jobPanel);
        sideJobsPanel.add(jobPanel);
        JobWorker jobWorker = new JobWorker(jobPanel,sideJobsPanel,dock,seaPort, resourcePoolPanel);
        worker2s.add(jobWorker);


        sideJobsPanel.revalidate();
        sideJobsPanel.repaint();

        this.pack();

    }

    public void startWorker(List<JobWorker> workers){
        ExecutorService service = Executors.newFixedThreadPool(docNumber);

        for (JobWorker j: workers) {
            service.execute(j);
        }


    }

    public JScrollPane generateSeaPortPanel(String name,JPanel innerPanel){
        JScrollPane panel = new JScrollPane(innerPanel);
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),
                name, TitledBorder.LEFT, TitledBorder.TOP, label, Color.GREEN));
        panel.setPreferredSize(new Dimension(400,0));
        panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        return panel;
    }

}
