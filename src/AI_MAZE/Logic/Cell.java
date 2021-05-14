package AI_MAZE.Logic;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
import AI_MAZE.GUI.MainFrame;
import AI_MAZE.GUI.MainPanel;
import AI_MAZE.GUI.Menu;
import AI_MAZE.GUI.AI_MainPanel;

public class Cell {
        
        private boolean isCurrent;
    
        private boolean visited;
        
        private boolean ia_visit=false;
    
        private int i;
        private int j;
        
        private static int rows;
        private static int cols;
        
        private static int width;
        
        private static Color color;
        
        private ArrayList<Boolean> trbl;
        
        private float score;
        private boolean penalty;
    
    public Cell (int _i, int _j) {
        
        this.i = _i;
        this.j = _j;
        
        trbl = new ArrayList<>();
        
        trbl.add(true);
        trbl.add(true);
        trbl.add(true);
        trbl.add(true);
        
        this.penalty=false;
        
        this.visited=false;
        if(Menu.getAI_value()) {
            color=MainPanel.giveColor();
        } else {
            color = AI_MainPanel.giveColor();
        }
        
    }
    
    public void draw (Graphics g) {
        int x = this.i*width;
        int y = this.j*width;        
                
        if(isCurrent){
            if(MainPanel.getLevel()==4) {
                g.setColor(MainPanel.getColor());
            } else {
                g.setColor(color);
            }            
            g.fillRect(x, y, width, width);
        } else {
           if(visited && !ia_visit) {
            g.setColor(Color.black);
            g.fillRect(x, y, width, width);
        }  /*else {
               if(visited && ia_visit) {
                   g.setColor(Color.gray);
                   g.fillRect(x, y, width, width);
               }
           } */ 
        }
        
        if(trbl.get(0)) {
            if(MainPanel.getLevel()==4) {
                g.setColor(MainPanel.getColor());
            } else {
                g.setColor(color);
            }  
            g.drawLine(x, y, x+width, y);//Horizontal UP
        }     
        
        if(trbl.get(1)) {
            if(MainPanel.getLevel()==4) {
                g.setColor(MainPanel.getColor());
            } else {
                g.setColor(color);
            }  
            g.drawLine(x+width,y,x+width,y+width);//Vertical RIGHT
        }   
        
        if(trbl.get(2)) {
            if(MainPanel.getLevel()==4) {
                g.setColor(MainPanel.getColor());
            } else {
                g.setColor(color);
            }  
            g.drawLine(x,y+width,x+width,y+width);//Horizonatal DOWN
        }
                
        if(trbl.get(3)) {
            if(MainPanel.getLevel()==4) {
                g.setColor(MainPanel.getColor());
            } else {
                g.setColor(color);
            }  
            g.drawLine(x,y,x,y+width);//Vertical LEFT
        }        
                                 
    }
    
    public static Color getColor() {
        return color;
    }
    
    public void setI(int _i) {
        this.i=_i;
    }
    
    public void setJ(int _j) {
        this.j=_j;
    }
        
    public ArrayList getTRBL() {
        return this.trbl;
    }
    
    public static int getCols() {
        return cols;
    }
    
    public static int getRows() {
        return rows;
    }
    
    public static int getWidth() {
        return width;
    }
    
    public boolean isCurrent() {
        return this.isCurrent;
    }
    
    public boolean isVisited() {
        return this.visited;
    }
    
    public int getI() {
        return this.i;
    }
    
    public int getJ() {
        return this.j;
    }
    
    public void setCurrent(boolean _set) {
        this.isCurrent = _set;
    }
    
    public static void setWidth(int _width) {
        width = _width;
    }
    
    public static void setCols() {
        cols = MainFrame.PANEL_HEIGHT/width; 
    }
    
    public static void setRows() {
        rows = MainFrame.PANEL_WIDTH/width;
    } 
    
    public void setVisited (boolean isit) {
        this.visited = isit;
    }
    
    public void setTRBL(boolean bulean) {
        for(int i = 0; i < getTRBL().size()-1; i++) {
            getTRBL().set(i, bulean);
        }
    }
    
    public void setIA_Visit(boolean bool) {
        this.ia_visit=bool;
        
    }
    
    public float getScore() {
        return this.score; 
    }
    
    public void setScore(float score) {
        this.score = score;
    }
    
    public boolean getPenalty() {
        return this.penalty;
    }
    
    public void setPenalty(boolean penalty) {
        this.penalty = penalty;
    }
}
