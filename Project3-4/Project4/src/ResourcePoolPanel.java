import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResourcePoolPanel extends JPanel {

    private ConcurrentHashMap<Skill,Integer> resoucePool;
    private ConcurrentHashMap<Skill,JLabel> resourceJLabels;

    public ResourcePoolPanel(ConcurrentHashMap<Skill, Integer> resourcePool) {
        System.out.println("DEBUG-- RESOUCEPOOL SIZE: "+ resourcePool.keySet().size());

        this.setLayout(new GridLayout(0,1));
        this.setBackground(Color.BLACK);
        this.resoucePool = resourcePool;
        this.resourceJLabels = new ConcurrentHashMap<>(resourcePool.size());

        for(ConcurrentHashMap.Entry<Skill,Integer> entry: resoucePool.entrySet()){
            resourceJLabels.put(entry.getKey(),generateJLabel(entry.getKey(),entry.getValue()));
        }

        Iterator<Map.Entry<Skill,JLabel>> iterator = resourceJLabels.entrySet().iterator();

        while(iterator.hasNext()){
            JPanel stripPanel = new JPanel();
//            stripPanel.setLayout();
            stripPanel.setBackground(Color.BLACK);


            JLabel firstLabel = iterator.next().getValue();
            JPanel jPanel = generateJPanel();
            jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            jPanel.add(firstLabel);
            stripPanel.add(jPanel);
            if(iterator.hasNext()){
                JLabel secondLabel = iterator.next().getValue();
                JPanel jPanel2 = generateJPanel();
                jPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
                jPanel2.add(secondLabel);
                stripPanel.add(jPanel2);
            }
            this.add(stripPanel);
        }

        for(Map.Entry<Skill,JLabel> entry: resourceJLabels.entrySet()){
            System.out.println("\t\t"+entry.getKey().toString());
        }
        this.repaint();
        this.validate();
    }

    public void incrementResource(Skill skill){
        resoucePool.put(skill, resoucePool.get(skill)+1);
        resourceJLabels.get(skill).setText(generateStringForJLabel(skill, resoucePool.get(skill)));
        repaint();
        revalidate();
    }

    public void decrementResource(Skill skill){
        resoucePool.put(skill, resoucePool.get(skill)-1);
        resourceJLabels.get(skill).setText(generateStringForJLabel(skill, resoucePool.get(skill)));
        repaint();
        revalidate();
    }
    private JLabel generateJLabel(Skill skill, Integer number){
        JLabel jLabel = new JLabel();
        jLabel.setForeground(Color.GREEN);
        jLabel.setText(generateStringForJLabel(skill,number));
        return jLabel;
    }

    private String generateStringForJLabel(Skill skill, Integer number){
        return skill.name+": "+number;
    }

    private JPanel generateJPanel(){
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.BLACK);

        return jPanel;
    }
}
