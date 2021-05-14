package AI_MAZE.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import AI_MAZE.Logic.Cell;

public class ActiveStatistics extends JPanel {
    
    private final static int START_CHRONOMETER=0;
    
    protected static boolean timer;
    protected static boolean chronometer;
    
    private static int timer_sec;
    private static int chronometer_sec;
    private static int timer_min;
    private static int chronometer_min;
    
    private Image bg;
    private Image level;
    private Image bonus;
    private Image malus;
    private Image timer_img;
    private Image chronometer_img;
    private Image best_time;
    
    public ActiveStatistics () {
        this.setSize(StatisticsFrame.STATISTICS_WIDTH, StatisticsFrame.STATISTICS_HEIGHT);
        this.setLocation(27, 15);
        
        chronometer = true;
        timer = false;
        TimeInit();
        
        bg = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/1.png"));
        level = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/level.png"));
        bonus = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/bonus.png"));
        malus = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/malus.png"));
        timer_img = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/timer.png"));
        chronometer_img = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/chronometer.png"));
        best_time = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/AI_MAZE/GUI/Images/best_time.png"));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT); 
        
        g.drawImage(bg, 0, 0, MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT, this);
        g.drawImage(level, 45, 10, 210, 40, this);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        if(MainPanel.getLevel()==1) {
            g.drawString("EASY", 110, 95);
        }
        if(MainPanel.getLevel()==2) {
            g.drawString("MEDIUM", 90, 95);
        }
        if(MainPanel.getLevel()==3) {
            g.drawString("HARD", 110, 95);
        }
        if(MainPanel.getLevel()==4) {
            g.drawString("EXTREME", 80, 95);
        }
        
           g.setColor(Color.white);
           g.setFont(new Font("Arial", Font.BOLD, 30));
        if(timer) {
           g.drawImage(timer_img, 45, 120, 210, 40, this);
           g.drawString(timer_min+" : "+timer_sec, 115, 200);
           repaint(timer_sec); 
           repaint(timer_min);
        } else {
           g.drawImage(chronometer_img, 25, 120, 250, 50, this);
           g.drawString(chronometer_min+" : "+chronometer_sec, 120, 210);
           repaint(chronometer_sec);
           repaint(chronometer_min);
        }
        
        if(!Menu.getAI_value()) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 30));        
            g.drawImage(bonus, 45, 225, 210, 40, this);
            g.fillRect(120, 290, 20, 20);
            g.drawString(""+MainPanel.getBonusCount(), 160, 310);
            g.drawImage(malus, 45, 335, 210, 40, this);
            g.setColor(Cell.getColor());      
            g.fillRect(120, 405, 20, 20);        
            g.setColor(Color.white);
            g.drawString(""+MainPanel.getMalusCount(), 160, 425);
        } else {
            //Generation
            //Population
        }
        
        if(chronometer) {
            switch(MainPanel.getLevel()) {
                case 1:                    
                    if(StatisticsPanel.get_c(MainPanel.getLevel()).getSec()!=1000) {
                        g.drawString(StatisticsPanel.get_c(MainPanel.getLevel()).getMin()+" : "
                        +StatisticsPanel.get_c(MainPanel.getLevel()).getSec(), 120,540);
                    }
                    if(StatisticsPanel.get_c(1).getSec()!=1000) {
                          g.drawImage(best_time, 40, 445, 230, 50,this);
                    }
                break;
                case 2:
                    if(StatisticsPanel.get_c(MainPanel.getLevel()).getSec()!=1000) {
                        g.drawString(StatisticsPanel.get_c(MainPanel.getLevel()).getMin()+" : "
                        +StatisticsPanel.get_c(MainPanel.getLevel()).getSec(), 120,540);
                    }
                    if(StatisticsPanel.get_c(2).getSec()!=1000) {
                          g.drawImage(best_time, 40, 445, 230, 50,this);
                    }
                break;
                case 3:
                    if(StatisticsPanel.get_c(MainPanel.getLevel()).getSec()!=1000) {
                        g.drawString(StatisticsPanel.get_c(MainPanel.getLevel()).getMin()+" : "
                        +StatisticsPanel.get_c(MainPanel.getLevel()).getSec(), 120,540);
                    }
                    if(StatisticsPanel.get_c(3).getSec()!=1000) {
                          g.drawImage(best_time, 40, 445, 230, 50,this);
                    }
                break;
                case 4:
                    if(StatisticsPanel.get_c(MainPanel.getLevel()).getSec()!=1000) {
                        g.drawString(StatisticsPanel.get_c(MainPanel.getLevel()).getMin()+" : "
                        +StatisticsPanel.get_c(MainPanel.getLevel()).getSec(), 120,540);
                    }
                    if(StatisticsPanel.get_c(4).getSec()!=1000) {
                          g.drawImage(best_time, 40, 445, 230, 50,this);
                    }
                break;
            }
        } else {
            switch(MainPanel.getLevel()) {
                case 1:
                    if(StatisticsPanel.get_t(MainPanel.getLevel()).getSec()!=1000) {
                        g.drawString(StatisticsPanel.get_t(MainPanel.getLevel()).getMin()+" : "
                        +StatisticsPanel.get_t(MainPanel.getLevel()).getSec(), 120,540);
                    }
                    if(StatisticsPanel.get_t(1).getSec()!=1000) {
                          g.drawImage(best_time, 40, 445, 230, 50,this);
                    }
                break;
                case 2:
                    if(StatisticsPanel.get_t(MainPanel.getLevel()).getSec()!=1000) {
                        g.drawString(StatisticsPanel.get_t(MainPanel.getLevel()).getMin()+" : "
                        +StatisticsPanel.get_t(MainPanel.getLevel()).getSec(), 120,540);
                    }
                    if(StatisticsPanel.get_t(2).getSec()!=1000) {
                          g.drawImage(best_time, 40, 445, 230, 50,this);
                    }
                break;
                case 3:
                    if(StatisticsPanel.get_t(MainPanel.getLevel()).getSec()!=1000) {
                        g.drawString(StatisticsPanel.get_t(MainPanel.getLevel()).getMin()+" : "
                        +StatisticsPanel.get_t(MainPanel.getLevel()).getSec(), 120,540);
                    }
                    if(StatisticsPanel.get_t(3).getSec()!=1000) {
                          g.drawImage(best_time, 40, 445, 230, 50,this);
                    }
                break;
                case 4:
                    if(StatisticsPanel.get_t(MainPanel.getLevel()).getSec()!=1000) {
                        g.drawString(StatisticsPanel.get_t(MainPanel.getLevel()).getMin()+" : "
                        +StatisticsPanel.get_t(MainPanel.getLevel()).getSec(), 120,540);
                    }
                    if(StatisticsPanel.get_t(4).getSec()!=1000) {
                          g.drawImage(best_time, 40, 445, 230, 50,this);
                    }
                break;
            }
        }
           
    }
    
    public void StartTime() {
        Thread timerThread = new Thread(new TimerThread());
        timerThread.start();
    }
    
    public static void setMode(boolean _timer, boolean _chronometer) {
        timer=_timer;
        chronometer=_chronometer;        
    }
    
    public static void TimeInit() {
        chronometer_sec=START_CHRONOMETER;
        chronometer_min=START_CHRONOMETER;
        if(MainPanel.getLevel()==1) {
            timer_sec=0;
            timer_min=2;
        }
        if(MainPanel.getLevel()==2) {
            timer_sec=30;
            timer_min=2;
        }
        if(MainPanel.getLevel()==3) {
            timer_sec=30;
            timer_min=3;
        }
        if(MainPanel.getLevel()==4) {
            timer_sec=0;
            timer_min=5;
        }
    }
    
    public static void setChronometerMin(int _min) {
        chronometer_min=_min;
    }
    
    public static void setChronometerSec(int _sec) {
        chronometer_sec=_sec;
    }
    
    public static void setTimerMin(int _min) {
        timer_min=_min;
    }
    
    public static void setTimerSec(int _sec) {
        timer_sec=_sec;
    }
    
    public static int getSec() {
        //if(chronometer)
        return chronometer_sec;
        /*else
        return timer_sec;*/
    }
    
    public static int getMin() {
        //if(chronometer)
        return chronometer_min;
        /*else
        return timer_min;*/
    }
    
    public static int getTimerSec() {
        return timer_sec;
    }
    
    public static int getTimerMin() {
        return timer_min;
    }
    
    public static boolean getChronometer() {
        return chronometer;
    }  
    
    public static boolean getTimer() {
        return timer;
    }  
    
    public void Chronometer() {
        chronometer_sec++;
            if(chronometer_sec >= 60) {
                chronometer_min++;
                chronometer_sec=START_CHRONOMETER;
            }                    
                /*System.out.println("chronometer");
                System.out.println("Min: " + chronometer_min + "- Sec: " + chronometer_sec);*/
                repaint(chronometer_sec);
                repaint(chronometer_min);
    }
    
    public void Timer() {
        if(timer_sec == 0 && timer_min!=0) {
            timer_min--;
            timer_sec=60;
        }
        if(timer_sec == 0 && timer_min==0) {
            MainPanel.gameActive=false;
            MainFrame.changeSolvedStatus(3);
            //System.out.println("Lost");
            MainPanel.hidePieces();
            //MainFrame.changeGameStatus(MainFrame.LEVELS);
        }
            timer_sec--;
            /*System.out.println("timer");
            System.out.println("Min: " + timer_min + "- Sec: " + timer_sec); */            
    }
    
    public class TimerThread implements Runnable {
        @Override
        public void run() {
            while(MainPanel.gameActive) {
                if(timer) {
                        Timer();
                        Chronometer();
                    } else {
                        Chronometer();
                    }
                try {                    
                    Thread.sleep(1000);
                    while(MainPanel.pause || MainPanel.creating) {
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException ex) {
                    
                }
            }
        }
    }
}
