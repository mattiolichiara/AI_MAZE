package AI_MAZE.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import AI_MAZE.Logic.Button;

public class Menu extends JPanel {
    private static Button ai;
    private Button play;    
    private Button mode;
    private Button tutorial;
    private Button exit;    
    private Image bg;
    private Image logo;
    private static boolean aiActive;
    
    public Menu () {
        bg = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/1.png"));
        logo = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/maze.png"));      
        
        this.setLocation(27,15);
        this.setSize(MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT);
        this.setLayout(null);
        
        ai = new Button(225, 200, 150, 45, Color.black);
        ai.setLabel(240, 230, "A.I. SOLVING", Color.white, 20);
        
        play = new Button(225, 270, 150, 45, Color.black);
        play.setLabel(275, 300, "PLAY", Color.white, 20);
        
        mode = new Button(225, 340, 150, 45, Color.black);
        mode.setLabel(250, 370, "MODALITY", Color.white, 20);
        
        tutorial = new Button(225, 410, 150, 45, Color.black);
        tutorial.setLabel(250, 440, "TUTORIAL", Color.white, 20);
        
        exit = new Button(225, 480, 150, 45, Color.black); 
        exit.setLabel(275, 510, "EXIT", Color.white, 20);
        
        this.addMouseListener(new Mouse_Listener());
    }
    
    @Override
    public void paintComponent (Graphics g) {
       g.setColor(Color.black);
       g.fillRect(0, 0, MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT);       
       
       g.drawImage(bg, 0, 0, MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT, this);
       g.drawImage(logo, 20, 70, MainFrame.PANEL_WIDTH-45, 90, this);  
       
       if(ai!=null && ai.isVisible()) {
           ai.draw(g);
       }       
       play.draw(g);
       mode.draw(g);
       tutorial.draw(g);
       exit.draw(g);
    }
    
    public class Mouse_Listener extends MouseAdapter {
        @Override
        public void mouseClicked (MouseEvent e) {
            if(ai.contains(e.getPoint())) {
                aiActive=true;
                MainFrame.changeGameStatus(MainFrame.LEVELS);
            }
            if(play.contains(e.getPoint())) {
                aiActive=false;
                MainFrame.changeGameStatus(MainFrame.LEVELS);                
            }
            if(mode.contains(e.getPoint())) {
                MainFrame.changeGameStatus(MainFrame.MODE);
            }
            if(tutorial.contains(e.getPoint())) {
                MainFrame.changeGameStatus(MainFrame.TUTORIAL);
            }
            
            if(exit.contains(e.getPoint())) {
                System.exit(0);
            }
            
        }
    }
    
    public static Button getAI() {
        return ai;
    }
    
    public static boolean getAI_value() {
        return aiActive;
    }
}
