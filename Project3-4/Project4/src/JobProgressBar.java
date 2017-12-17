import javax.swing.*;
import java.awt.*;

public class JobProgressBar extends JProgressBar {
    private Boolean killFlag;
    private Boolean goFlag;

    public JobProgressBar() {
        this.killFlag = false;
        this.goFlag = true;

        setBackground(Color.BLACK);
        setForeground(Color.GREEN);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.green, 1),
                BorderFactory.createEmptyBorder(1,1,1,1)));
    }

    public boolean toggleGoFlag() {
        synchronized (goFlag) {
            goFlag = !goFlag;
            if (!goFlag) {
                setForeground(Color.YELLOW);
                setString("Paused");
            }
            else {
                setString("Working");
                setForeground(Color.GREEN);
            }
        }
        return goFlag;
    }

    public void setWaiting(){
        setForeground(Color.ORANGE);
        setString("Waiting");
    }

    public void setKillCommand() {
        killFlag = true;
        setForeground(Color.RED);
    }

    public boolean getKillFlag() {
        return killFlag;
    }

    public boolean getGoFlag() {
        return goFlag;
    }


}
