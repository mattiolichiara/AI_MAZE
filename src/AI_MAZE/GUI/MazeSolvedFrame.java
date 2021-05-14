package AI_MAZE.GUI;

import javax.swing.JFrame;

public class MazeSolvedFrame extends JFrame {
    
    public static final int PANEL_WIDTH = 350;
    public static final int PANEL_HEIGHT = 150;
    
    public static final int OPEN = 1;
    
    private static MazeSolvedPanel mazeSolvedPanel;
    
    public MazeSolvedFrame (int type) {
        this.setSize(PANEL_WIDTH+30,PANEL_HEIGHT+60);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        
        if(type==1 || type==2) {
            this.setTitle("MAZE SOLVED");
        } 
        if(type==3) {
            this.setTitle("GAME OVER");
        }
        if(type==4) {
            this.setTitle("GAME PAUSED");
        }
        
        this.setLocation(605, 260);
        
        mazeSolvedPanel = new MazeSolvedPanel(type);
        this.getContentPane().add(mazeSolvedPanel);
        mazeSolvedPanel.setVisible(true);
    }
}
