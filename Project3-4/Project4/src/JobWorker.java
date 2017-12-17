import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class JobWorker extends SwingWorker<Void, Integer>{
    private JobProgressBar progressBar;
    private JobPanel jobPanel;
    private final ResourcePoolPanel resourcePoolPanel;
    private SeaPort seaPort;

    JPanel parent;
    Dock dock;

    public JobWorker(JobPanel jobPanel, JPanel parent, Dock dock, SeaPort seaPort, ResourcePoolPanel resourcePoolPanel) {
        this.jobPanel = jobPanel;
        this.progressBar = jobPanel.getProgressBar();
        this.parent = parent;
        this.dock = dock;
        this.seaPort = seaPort;
        this.resourcePoolPanel = resourcePoolPanel;
    }














    @Override
    protected Void doInBackground() throws Exception {
        List<Ship> ships = dock.getShip();
        for(int i = 0; i < ships.size(); i++){
            jobPanel.updateShip(ships.get(i).getName());
            List<Job> jobs = ships.get(i).getJobs();
            int jobCount = 1;
//            for(int j = 0; j < jobs.size(); j++){
            for(Job j:jobs){
                if(!j.isValid()){
                    continue;
                }
                jobPanel.updateJob(jobCount,jobs.size());

                synchronized (seaPort.resourcePool){

//                    if(isResourceAvailable(j)){
//                        j.setReady(true);
//                        pullResource(j);
//                    }
                    j.setReady(true);
                    for (Skill skill : j.getRequirements()) {
                        Integer jobct = seaPort.resourcePool.get(skill);
                        if (jobct == null || i < 1) {
                            j.setReady(false);
                        }
                    }

                    while(!j.isReady()){
                        seaPort.resourcePool.wait();
                    }
                    j.setReady(false);
                    pullResource(j);
                }


                //WORK
                int duration = (int)j.getDuration();
                long time = System.currentTimeMillis();
                long startTime = time;
                long stopTime = (time + 1000 * duration);
                double totalTime = stopTime - time;


                while(time < stopTime&&!progressBar.getKillFlag()){
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException ignored){}
                    if(progressBar.getGoFlag()){
                        time+=100;
                        progressBar.setValue((int)(((time - startTime) / totalTime) * 100));
                    }

                }

                synchronized (seaPort.resourcePool){
                    restoreResource(j);
                    j.setReady(false);
                    seaPort.resourcePool.notifyAll();
                }
                jobCount++;
            }
        }
        return null;
    }

    @Override
    protected void done() {
        try {
            get();
            parent.remove(jobPanel);
            parent.revalidate();
            parent.repaint();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

      boolean isResourceAvailable(Job job){

         synchronized (seaPort.resourcePool) {
             System.out.println("THREAD-- RESOURCE CHECK Job: "+job.getName());
             for (Skill skill : job.getRequirements()) {
                 Integer i = seaPort.resourcePool.get(skill);
                 if (i == null || i < 1) {
                     return false;
                 }
             }
             return true;
         }
    }

    // You must always call isResourceAvailable before calling pullResource
    void pullResource(Job job){
          synchronized (seaPort.resourcePool) {
              for (Skill skill : job.getRequirements()) {
                  resourcePoolPanel.decrementResource(skill);
                  seaPort.resourcePool.put(skill,seaPort.resourcePool.get(skill)-1);
                  skill.notifyAll();
              }
          }
          for(Map.Entry<Skill,Integer> entry:seaPort.resourcePool.entrySet()){
              System.out.println("S: "+entry.getKey()+" I: "+entry.getValue());
          }
    }

     void restoreResource(Job job){
        synchronized (seaPort.resourcePool) {
            for (Skill skill : job.getRequirements()) {
                resourcePoolPanel.incrementResource(skill);
                skill.notifyAll();
            }
        }
    }


}
