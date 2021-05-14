package AI_MAZE.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import AI_MAZE.Logic.Time;

public class StatisticsPanel extends JPanel {
    
    private Image bg;
    private Image logo;
    private Image t_mode;
    private Image c_mode;
    private Image easy;
    private Image medium;
    private Image hard;
    private Image extreme;   
 
    private static Time easy_c;
    private static Time medium_c;
    private static Time hard_c;
    private static Time extreme_c;
    private static Time easy_t;
    private static Time medium_t;
    private static Time hard_t;
    private static Time extreme_t;
    
    public StatisticsPanel () {
        this.setSize(StatisticsFrame.STATISTICS_WIDTH, StatisticsFrame.STATISTICS_HEIGHT);
        this.setLocation(27, 15);
        
        bg = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/1.png"));
        logo = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/statistics.png"));    
        t_mode = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/t_mode.png"));    
        c_mode = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/c_mode.png"));         
        easy = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/easy.png"));    
        hard = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/hard.png"));    
        medium = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/medium.png"));  
        extreme = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/extreme.png"));  
        
        easy_c=new Time(1000,1000);
        medium_c=new Time(1000,1000);
        hard_c=new Time(1000,1000);
        extreme_c=new Time(1000,1000);
        //
        easy_t=new Time(1000,1000);
        medium_t=new Time(1000,1000);
        hard_t=new Time(1000,1000);
        extreme_t=new Time(1000,1000);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, StatisticsFrame.STATISTICS_WIDTH, StatisticsFrame.STATISTICS_HEIGHT); 
        
        g.drawImage(bg, 0, 0, MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT, this);
        g.drawImage(logo, 25, 10, StatisticsFrame.STATISTICS_WIDTH-45, 70, this);
        
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 20));
        if(ActiveStatistics.chronometer) {
            g.drawImage(c_mode, 25, 120, StatisticsFrame.STATISTICS_WIDTH-45, 50, this);
            if(easy_c.getSec()==1000 && medium_c.getSec()==1000 && hard_c.getSec()==1000 && extreme_c.getSec()==1000) {
            g.drawString("Play to get statistics!", 50, 290);
            } else {
                if(easy_c.getSec()!=1000) {
                    g.drawImage(easy, 105, 200, 90, 30, this);
                    g.drawString(easy_c.getMin()+" : "+easy_c.getSec(), 115, 260);
                }
                if(medium_c.getSec()!=1000) {
                    g.drawImage(medium, 100, 300, 100, 30, this);
                    g.drawString(medium_c.getMin()+" : "+medium_c.getSec(), 115, 360);
                }
                if(hard_c.getSec()!=1000) {
                    g.drawImage(hard, 100, 400, 100, 30, this);
                    g.drawString(hard_c.getMin()+" : "+hard_c.getSec(), 115, 460);
                }
                if(extreme_c.getSec()!=1000) {
                    g.drawImage(extreme, 100, 500, 100, 30, this);
                    g.drawString(extreme_c.getMin()+" : "+extreme_c.getSec(), 115, 560);
                }                
            }
        } else {
            g.drawImage(t_mode, 35, 120, 230, 50, this);
            if(easy_t.getSec()==1000 && medium_t.getSec()==1000 && hard_t.getSec()==1000 && extreme_t.getSec()==1000) {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Play to get statistics!", 50, 290);
            } else {
                if(easy_t.getSec()!=1000) {
                    g.drawImage(easy, 105, 200, 90, 30, this);
                    g.drawString(easy_t.getMin()+" : "+easy_t.getSec(), 115, 260);
                }
                if(medium_t.getSec()!=1000) {
                    g.drawImage(medium, 100, 300, 100, 30, this);
                    g.drawString(medium_t.getMin()+" : "+medium_t.getSec(), 115, 360);
                }
                if(hard_t.getSec()!=1000) {
                    g.drawImage(hard, 100, 400, 100, 30, this);
                    g.drawString(hard_t.getMin()+" : "+hard_t.getSec(), 115, 460);
                }
                if(extreme_t.getSec()!=1000) {
                    g.drawImage(extreme, 100, 500, 100, 30, this);
                    g.drawString(extreme_t.getMin()+" : "+extreme_t.getSec(), 115, 560);
                }
            }
        }
        repaint();       
        
    } 
    
    public static void min(int _sec, int _min, Time _time) {
        if(_time.getSec()!=1000) {
                if(_min<_time.getMin()) {
                    _time.setSec(_sec);
                    _time.setMin(_min);
                    MainFrame.changeSolvedStatus(1);
                    //System.out.println("Best Time");
            } else {
                if(_min==_time.getMin()) {
                    if(_sec<_time.getSec()) {
                        _time.setSec(_sec);
                        _time.setMin(_min);
                        MainFrame.changeSolvedStatus(1);
                        //System.out.println("Best Time");
                    } else {
                      MainFrame.changeSolvedStatus(2);
                      //System.out.println("Maze Solved");
                    }                 
                } 
            }
        } else {
            MainFrame.changeSolvedStatus(1);
            //System.out.println("Best Time");
            _time.setSec(_sec);
            _time.setMin(_min);
        }        
    }
    
    public static void getTime(int _sec, int _min, int _level) {
        if(ActiveStatistics.chronometer) {
            switch(_level) {
                case 1:
                   min(_sec,_min,easy_c);
                break;
                case 2:
                    min(_sec,_min,medium_c);
                break;
                case 3:
                    min(_sec,_min,hard_c);
                break;
                case 4:
                    min(_sec,_min,extreme_c);
                break;
            }
        } else {
            switch(_level) {
                case 1:
                   min(_sec,_min,easy_t);
                break;
                case 2:
                    min(_sec,_min,medium_t);
                break;
                case 3:
                    min(_sec,_min,hard_t);
                break;
                case 4:
                    min(_sec,_min,extreme_t);
                break;
            }
        }
    }
    
    public static Time get_c(int _level) {
        switch(_level) {
            case 1:
                return easy_c;
            case 2:
                return medium_c;
            case 3:
                return hard_c;
            case 4:
                return extreme_c;
        }
        return null;
    }
    
    public static Time get_t(int _level) {
        switch(_level) {
            case 1:
                return easy_t;
            case 2:
                return medium_t;
            case 3:
                return hard_t;
            case 4:
                return extreme_t;
        }
        return null;
    }
          
}
