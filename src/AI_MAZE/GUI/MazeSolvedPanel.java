package AI_MAZE.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class MazeSolvedPanel extends JPanel {
    
    private int type;
    private Image bg;
    private Image mazeSolved;
    private Image gameOver;
    private Image bestTime;
    private Image paused;
    
    public MazeSolvedPanel (int _type) {
        this.setSize(MazeSolvedFrame.PANEL_WIDTH, MazeSolvedFrame.PANEL_HEIGHT);
        this.setLocation(13, 15);
        this.type = _type;
        
        bg = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/1.png"));
        mazeSolved = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/mazeSolved.png"));
        gameOver = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/gameOver.png"));
        bestTime = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/best_time.png"));
        paused = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/paused.png"));
    }
    
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, MazeSolvedFrame.PANEL_WIDTH, MazeSolvedFrame.PANEL_HEIGHT);
        
        g.drawImage(bg, -10, -10, MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT, this);
        
        if(type==1) {
            g.drawImage(bestTime, 0, 15, MazeSolvedFrame.PANEL_WIDTH, MazeSolvedFrame.PANEL_HEIGHT-30, this);
        }
        if(type==2) {
            g.drawImage(mazeSolved, 0, 15, MazeSolvedFrame.PANEL_WIDTH, MazeSolvedFrame.PANEL_HEIGHT-30, this);
        }
        if(type==3) {
            g.drawImage(gameOver, 0, 15, MazeSolvedFrame.PANEL_WIDTH, MazeSolvedFrame.PANEL_HEIGHT-30, this);
        }
        if(type==4) {
            g.drawImage(paused, 0, 15, MazeSolvedFrame.PANEL_WIDTH, MazeSolvedFrame.PANEL_HEIGHT-30, this);
        }
    }
}
