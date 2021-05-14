package AI_MAZE.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import AI_MAZE.Logic.Button;

public class Tutorial extends JPanel {
    private Button play;
    private Button back;
    private Image bg;
    private Image logo;
    private Image down;
    private Image up;
    private Image r;
    private Image l;
    private Image esc;
    private Image delete;
    private Image space;
    private Image enter;
    private Image vk_r;
    private Image vk_m;
    
    public Tutorial () {
        this.setSize(MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT);
        this.setLayout(null);
        this.setLocation(27,15);
        
        this.bg = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/1.png"));
        this.logo = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/tutorial.png"));
        this.up = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/upKey.png"));
        this.down = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/downKey.png"));
        this.r = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/rightKey.png"));
        this.l = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/leftKey.png"));
        this.esc = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/escKey.png"));
        this.space = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/spaceKey.png")); 
        this.delete = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/back_space.png"));
        this.enter = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/enter.png"));
        this.vk_r = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/r.png"));
        this.vk_m = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/m.png"));
        
        play = new Button(130, 530, 150, 45, Color.black);
        play.setLabel(180, 560, "PLAY", Color.white, 20);
        
        back = new Button(320, 530, 150, 45, Color.black);
        back.setLabel(370, 560, "BACK", Color.white, 20);
        
        this.addMouseListener(new Mouse_Listener());
    }
    
    @Override
    public void paintComponent (Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT);
        
        g.drawImage(bg, 0, 0, MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT, this);
        g.drawImage(logo, 40, 0, MainFrame.PANEL_WIDTH-90, 70, this);
        
        //        
        g.drawImage(enter, 30, 80, 80, 80, this);    
        g.drawImage(space, 25, 183, 100, 40, this);
        g.drawImage(delete, 15, 250, 120, 60, this);
        g.drawImage(esc, 40, 335, 70, 60, this);
        g.drawImage(vk_r, 45, 420, 55, 50, this);
        g.drawImage(vk_m, 340, 335, 55, 55, this);
        //
        g.drawImage(up, 453, 80, 70, 65, this);
        g.drawImage(down, 448, 170, 80, 80, this);
        g.drawImage(l, 380, 130, 75, 65, this);
        g.drawImage(r, 520, 130, 80, 60, this);
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("START GAME", 150, 120);
        g.drawString("PAUSE/RESTART", 150, 210);
        g.drawString("BACK TO MENU", 150, 285);
        g.drawString("CHANGE LEVEL", 150, 370);
        g.drawString("REFRESH GAME", 150, 450);
        g.drawString("MOVE", 460, 275);    
        g.drawString("CHANGE MODE", 410, 370); 
        
        play.draw(g);
        back.draw(g);
    }
    
    public class Mouse_Listener extends MouseAdapter {
        @Override
        public void mouseClicked (MouseEvent e) {
            if(play.contains(e.getPoint())) {
               MainFrame.changeGameStatus(MainFrame.LEVELS);
            }
            if(back.contains(e.getPoint())) {
               MainFrame.changeGameStatus(MainFrame.MENU); 
            }
        }
    }
}
