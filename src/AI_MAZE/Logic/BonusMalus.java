package AI_MAZE.Logic;

import java.awt.Color;
import java.awt.Graphics;
import AI_MAZE.GUI.ActiveStatistics;

public class BonusMalus extends Cell {
    
    private int i;
    private int j;
    private boolean visible;
    private Color color;
    private static int effect;
    
    public BonusMalus(int _i, int _j, boolean type) {
        super(_i,_j); 
        this.i = _i;
        this.j = _j;
        this.visible=true;
        if(type) {
           this.color=Color.white;
        } else {
            color=getColor();
        }   
        
        effect=10;
        
        setTRBL(false);
    }
    
    @Override
    public void draw (Graphics g) {
        int x = this.i*Cell.getWidth();
        int y = this.j*Cell.getWidth();    
        
        g.setColor(this.color);
        g.fillRect(x+Cell.getWidth()/4, y+Cell.getWidth()/4, Cell.getWidth()-Cell.getWidth()/2, Cell.getWidth()-Cell.getWidth()/2);
    }
    
    public void setVisible(boolean isIt) {
        this.visible = isIt;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public int getEffect() {
        return effect;
    }
    
    public static void substractChronometerBonus() {
        if(ActiveStatistics.getChronometer()) {
            int _sec = ActiveStatistics.getSec() - effect;
            int _min = ActiveStatistics.getMin();
            int ris = 60 + _sec;
            if(_min != 0) {
                if(_sec > 0) {
                    ActiveStatistics.setChronometerSec(_sec);
                } else {
                    if(_sec<0) {
                        ActiveStatistics.setChronometerSec(ris);
                        ActiveStatistics.setChronometerMin(_min-1);
                    }
                }
            } else {
                if(_min==0) {
                    if(_sec > 0) {
                       ActiveStatistics.setChronometerSec(_sec);
                    } else {
                        if(_sec<0) {
                            ActiveStatistics.setChronometerSec(0);
                        }
                    }
                }
            } 
                      
        }
    } 
    
    public static void substractTimerMalus() {
        if(ActiveStatistics.getTimer()) {
            int _sec = ActiveStatistics.getTimerSec() - effect;
            int _min = ActiveStatistics.getTimerMin();
            int ris = 60 + _sec;
            if(_min != 0) {
                if(_sec > 0) {
                    ActiveStatistics.setTimerSec(_sec);
                } else {
                    if(_sec<0) {
                        ActiveStatistics.setTimerSec(ris);
                        ActiveStatistics.setTimerMin(_min-1);
                    }
                }
            } else {
                if(_min==0) {
                    if(_sec > 0) {
                       ActiveStatistics.setTimerSec(_sec);
                    } else {
                        if(_sec<0) {
                            ActiveStatistics.setTimerSec(0);
                        }
                    }
                }
            }           
        }
    }
    
    public static void sumChronometerMalus() {
        if(ActiveStatistics.getChronometer()) {
            int _sec = ActiveStatistics.getSec() + effect;
            int _min = ActiveStatistics.getMin();
            int ris = _sec-59;
            if(_sec>59) { 
                    ActiveStatistics.setChronometerMin(_min+1);
                    ActiveStatistics.setChronometerSec(ris);
            } else {
                ActiveStatistics.setChronometerSec(_sec);
            }
        }
    }
    
    public static void sumTimerBonus() {
        if(ActiveStatistics.getTimer()) {
            int _sec = ActiveStatistics.getTimerSec() + effect;
            int _min = ActiveStatistics.getTimerMin();
            int ris = _sec-59;
            if(_sec>59) { 
                    ActiveStatistics.setTimerMin(_min+1);                    
                    ActiveStatistics.setTimerSec(ris);
            } else {
                ActiveStatistics.setChronometerSec(_sec);
            } 
        }
    }
    
    
    
}           
