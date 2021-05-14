package AI_MAZE.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import AI_MAZE.Logic.Button;

public class Levels extends JPanel {
    private Button easy;    
    private Button medium;
    private Button hard;
    private Button extreme; 
    private Button back; 
    private Image bg;
    private Image logo;
    private Image ai_logo;
    
    private MainPanel mainPanel;
    private AI_MainPanel ai_MainPanel;
    
    public Levels () {
        bg = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/1.png"));
        logo = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/selectdifficulty.png"));  
        ai_logo = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/ai_complexity.png"));
        
        this.setLocation(27,15);
        this.setSize(MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT);
        this.setLayout(null);
        
        easy = new Button(225, 190, 150, 45, Color.black);
        easy.setLabel(275, 220, "EASY", Color.white, 20);
        
        medium = new Button(225, 260, 150, 45, Color.black);
        medium.setLabel(260, 290, "MEDIUM", Color.white, 20);
        
        hard = new Button(225, 330, 150, 45, Color.black);
        hard.setLabel(270, 360, "HARD", Color.white, 20);
        
        extreme = new Button(225, 400, 150, 45, Color.black); 
        extreme.setLabel(250, 430, "EXTREME", Color.white, 20);
        
        back = new Button(225, 470, 150, 45, Color.black); 
        back.setLabel(270, 500, "BACK", Color.white, 20);
        
        mainPanel = new MainPanel();
        ai_MainPanel = new AI_MainPanel();
        
        this.addMouseListener(new Mouse_Listener());
    }
    
    @Override
    public void paintComponent (Graphics g) {
       g.setColor(Color.black);
       g.fillRect(0, 0, MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT);       
       
       g.drawImage(bg, 0, 0, MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT, this);
       if(!Menu.getAI_value()) {
           g.drawImage(logo, 20, 50, MainFrame.PANEL_WIDTH-45, 90, this);  
       } else {
           g.drawImage(ai_logo, 20, 50, MainFrame.PANEL_WIDTH-45, 90, this);
       }       
       
       easy.draw(g);
       medium.draw(g);
       hard.draw(g);
       extreme.draw(g);
       back.draw(g);
    }
    
    public class Mouse_Listener extends MouseAdapter {
        @Override
        public void mouseClicked (MouseEvent e) {
            if(!Menu.getAI_value()) {
                if(easy.contains(e.getPoint())) {
                mainPanel.newLevel(1);
                MainPanel.hidePieces();
                MainFrame.changeGameStatus(MainFrame.MAIN_PANEL);                
                }
                if(medium.contains(e.getPoint())) {
                    mainPanel.newLevel(2);
                    MainPanel.hidePieces();
                    MainFrame.changeGameStatus(MainFrame.MAIN_PANEL);
                }
                if(hard.contains(e.getPoint())) {
                    mainPanel.newLevel(3);
                    MainPanel.hidePieces();
                    MainFrame.changeGameStatus(MainFrame.MAIN_PANEL);
                }

                if(extreme.contains(e.getPoint())) {
                    mainPanel.newLevel(4);
                    MainPanel.hidePieces();
                    MainFrame.changeGameStatus(MainFrame.MAIN_PANEL);
                }
            } else {
                if(easy.contains(e.getPoint())) {
                    MainPanel.setLevel(1);
                    ai_MainPanel.newLevel(MainPanel.getLevel());
                    AI_MainPanel.hidePieces();
                    MainFrame.changeGameStatus(MainFrame.AI_MAIN_PANEL);                
                }
                if(medium.contains(e.getPoint())) {
                    MainPanel.setLevel(2);
                    ai_MainPanel.newLevel(MainPanel.getLevel());
                    AI_MainPanel.hidePieces();
                    MainFrame.changeGameStatus(MainFrame.AI_MAIN_PANEL);
                }
                if(hard.contains(e.getPoint())) {
                    MainPanel.setLevel(3);
                    ai_MainPanel.newLevel(MainPanel.getLevel());
                    AI_MainPanel.hidePieces();
                    MainFrame.changeGameStatus(MainFrame.AI_MAIN_PANEL);
                }

                if(extreme.contains(e.getPoint())) {   
                    MainPanel.setLevel(4);
                    ai_MainPanel.newLevel(MainPanel.getLevel());
                    AI_MainPanel.hidePieces();
                    MainFrame.changeGameStatus(MainFrame.AI_MAIN_PANEL);
                }
            }            
            
            if(back.contains(e.getPoint())) {
                MainFrame.changeGameStatus(MainFrame.MENU);
            }
            
        }
    }
}
