package AI_MAZE.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import AI_MAZE.Logic.Button;

public class GameModality extends JPanel {
    private Button timer;    
    private Button chronometer;
    private Button back;
    private Image bg;
    private Image logo;
    
    private MainPanel mainPanel;
    
    public GameModality () {
        bg = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/1.png"));
        logo = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/selectmode.png"));      
        
        this.setLocation(27,15);
        this.setSize(MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT);
        this.setLayout(null);
        
        timer = new Button(210, 230, 180, 45, Color.black);
        timer.setLabel(270, 260, "TIMER", Color.white, 20);
        
        chronometer = new Button(210, 300, 180, 45, Color.black);
        chronometer.setLabel(220, 330, "CHRONOMETER", Color.white, 20);
        
        back = new Button(225, 470, 150, 45, Color.black); 
        back.setLabel(270, 500, "BACK", Color.white, 20);
        
        mainPanel = new MainPanel();
        
        this.addMouseListener(new Mouse_Listener());
    }
    
    @Override
    public void paintComponent (Graphics g) {
       g.setColor(Color.black);
       g.fillRect(0, 0, MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT);       
       
       g.drawImage(bg, 0, 0, MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT, this);
       g.drawImage(logo, 20, 50, MainFrame.PANEL_WIDTH-45, 90, this);  
       
       timer.draw(g);
       chronometer.draw(g);
       back.draw(g);
    }
    
    public class Mouse_Listener extends MouseAdapter {
        @Override
        public void mouseClicked (MouseEvent e) {
            if(timer.contains(e.getPoint())) {
                ActiveStatistics.setMode(true, false);
                Menu.getAI().setVisible(false);
                MainFrame.changeGameStatus(MainFrame.LEVELS);                
            }
            if(chronometer.contains(e.getPoint())) {
                ActiveStatistics.setMode(false, true);
                Menu.getAI().setVisible(true);
                MainFrame.changeGameStatus(MainFrame.LEVELS);
            }
            
            if(back.contains(e.getPoint())) {
                MainFrame.changeGameStatus(MainFrame.MENU);
            }
            
        }
    }
}
