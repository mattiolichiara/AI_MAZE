package AI_MAZE.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import java.util.Stack;
import AI_MAZE.Logic.BonusMalus;
import AI_MAZE.Logic.Cell;
import AI_MAZE.Logic.Piece;

public class MainPanel extends JPanel { 
    
    private boolean UP;
    private boolean DOWN;
    private boolean RIGHT;
    private boolean LEFT;
    
    private static BonusMalus bonus;
    private static BonusMalus malus;
    private static BonusMalus bonus2;
    private static BonusMalus malus2;
    private static int bonus_count;
    private static int malus_count;
    
    private Stack backT;
    protected static boolean gameActive;
    protected static boolean pause;
    private static int level;
    private boolean refreshed;
    protected static boolean creating;
    //private boolean fooled;
    
    private ActiveStatistics activeStatistics;
    private StatisticsPanel statisticsPanel;
    
    private static Piece exit;
    private static Piece player;
    
    private Cell current;
    private Cell next;
    
    private Cell cell;
    
    private ArrayList<Cell> neighbors;
    private static ArrayList<Cell> cells;
    
    private static Color color;
    
    public MainPanel () {
        this.setSize(MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT);
        this.setLocation(27, 15);
        
        gameActive = false;
        creating = true;
        pause = false;
        this.refreshed = false;
        //this.fooled = false;
        level = 1;
        
        activeStatistics = new ActiveStatistics();
        statisticsPanel = new StatisticsPanel();
        
        cells = new ArrayList<>();
        
        switch(level) {
            case 1:
               Cell.setWidth(40); 
            break;
            case 2:
               Cell.setWidth(30); 
            break;
            case 3:
               Cell.setWidth(20); 
            break;
            case 4:
               Cell.setWidth(10); 
            break;
        }
        
        Cell.setRows();
        Cell.setCols();
        
        for(int j = 0; j < Cell.getRows(); j++) { 
            for(int i=0; i < Cell.getCols(); i++) {                   
                cell = new Cell(i,j);
                cells.add(cell);
            }
        }
        
        color = getColor();        
         
        
        MainPanel.player = new Piece(0,0, cells.get(0)/*, color*/);
        exit = new Piece(Cell.getRows()-1, Cell.getRows()-1, cells.get(cells.size()-1)/*, color*/); 
                
        exit.setVisible(false);
        MainPanel.player.setVisible(false); 
        
        malus_count=0;
        bonus_count=0;
        
        this.backT = new Stack();
        
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.grabFocus();
        this.addKeyListener(new KeyListener());
    }
    
    @Override
    public void paintComponent (Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT);        
        
        for(int i = 0; i < cells.size()-1; i++) {            
            cells.get(i).draw(g);            
        }
        
        if(exit!= null && exit.isVisible()) {
            exit.draw(g);
        }
        if(MainPanel.player != null && MainPanel.player.isVisible()) {
            MainPanel.player.draw(g);
        }
        if(bonus != null && bonus.isVisible()) {
            bonus.draw(g);
        }
        if(malus != null && malus.isVisible()) {
            malus.draw(g);
        }
        if(bonus2 != null && bonus2.isVisible()) {
            bonus2.draw(g);
        }
        if(malus2 != null && malus2.isVisible()) {
            malus2.draw(g);
        }
            
    } 
    
    public void newLevel(int _level) {
        updateColor();
        setLevel(_level);
        setGrid();
        updateGrid();
        ActiveStatistics.TimeInit();
    }
    
    public int setGrid() {
        if(level == 1)
            return 40;
        if(level == 2)
            return 30;
        if(level == 3)
            return 20;
        if(level == 4) 
            return 10;
        else 
            return -1;
    }

    public void updateGrid() {
        
        Cell.setWidth(setGrid());
        Cell.setRows();
        Cell.setCols();
        
        cells.clear();
        for(int j = 0; j < Cell.getRows(); j++) { 
            for(int i=0; i < Cell.getCols(); i++) {                   
                cell = new Cell(i,j);
                cells.add(cell);
            }
        }        
        
        repaint();   
    } 
    
    public void updateColor() {
        color = getColor();
    }
    
    public static int getLevel() {
        return level;
    }
    
    public static boolean getActiveStatus() {
        return gameActive;
    }
    
    public static boolean getPauseStatus() {
        return pause;
    }
    
    public static boolean getCreationStatus() {
        return creating;
    }
    
    public static void setLevel(int _level) {
        level = _level;
    }
    
    public static void setPause(boolean ispause) {
        pause = ispause;
    }
    
    public static void setGameActivity(boolean active) {
        gameActive = active;
    }
    
    public static void setCreation(boolean active) {
        creating = active;
    }
    
    public static void hidePieces() {
        MainPanel.player.setVisible(false);
        exit.setVisible(false);
        if(bonus!=null) {
            bonus.setVisible(false);            
        }
        if(bonus2!=null) {
            bonus2.setVisible(false);
        }
        if(malus!=null) {
            malus.setVisible(false);            
        }
        if(malus2!=null) {
            malus2.setVisible(false);
        }
    }
    
    public void resetGame() {
        gameActive = false; 
        //pause = false;
        if(!backT.empty()) {
            backT.clear();
        }
        if(neighbors != null && !neighbors.isEmpty()) {
            neighbors.clear();
        }        
        for(int i = 0; i < cells.size()-1; i++) {
            cells.get(i).setVisited(false);
            cells.get(i).setCurrent(false);
           for(int j = 0; j < cells.get(i).getTRBL().size(); j++)   {
              cells.get(i).getTRBL().set(j, true); 
           }              
        }  
        RIGHT = false;
        LEFT = false;
        UP = false;
        DOWN = false;
        
        ActiveStatistics.TimeInit();
        MainPanel.player.setVisible(false);
        exit.setVisible(false);
        bonus=null;
        bonus2=null;
        malus=null;
        malus2=null;
        malus_count=0;
        bonus_count=0;
    }
    
    public static int getBonusCount() {
        return bonus_count;
    }
    
    public static int getMalusCount() {
        return malus_count;
    }
    
    public void startGame() {
        gameActive = true;
        pause = false;
        
        Random rand = new Random();
        int num = rand.nextInt(cells.size()-1);
        this.current = cells.get(num);
        this.current.setVisited(true); //depth first search
        this.current.setCurrent(true);  
        
        MainPanel.player = new Piece(0,0, cells.get(0));
        exit = new Piece(Cell.getRows()-1, Cell.getRows()-1, cells.get(cells.size()-1)); 
        
        MainPanel.hidePieces();
        startVisiting();
    }
    
    public void startVisiting() {
        Thread visitThread = new Thread(new MazeThread());
        visitThread.start();
    }
    
    public void startMoving() {        
        if(!refreshed) {
            Thread movementThread = new Thread(new MovementThread());
            movementThread.start();
            activeStatistics.StartTime();
            Thread bonusMalusThread = new Thread(new BonusMalusThread());
            bonusMalusThread.start();
        }         
    }
    
    public static Color getColor() {
        Random randColor = new Random();
        int c = randColor.nextInt(6);
        switch(c) {
            case 0:
              color = Color.BLUE;
            break;
            case 1: 
                color = Color.CYAN;
            break;
            case 2: 
                 color = Color.GREEN;
            break;
            case 3:
                 color = Color.MAGENTA;
            break;
            case 4:
                 color = Color.ORANGE;
            break;
            case 5:
                 color = Color.RED;
            break;
        }
        return color;
    }
    
    public static Color giveColor() {
        return color;
    }
    
    public int index (int _i, int _j, int _cols, int _rows) {
        if(_i < 0 || _j < 0 || _j > _cols-1 || _i > _rows-1) {
          return -1;
        }
        
       int index = _i+_j*_cols;
       return index;
    }
    
    public Cell checkNeighbors() {
        
        neighbors = new ArrayList<>();
        
        if(current==null) {
            System.out.println("true");
        }        
                
        int up = index(current.getI(), current.getJ()-1, current.getCols(), current.getRows());                   
        int down = index(current.getI(), current.getJ()+1, current.getCols(),current.getRows()); 
        int left = index(current.getI()-1, current.getJ(), current.getCols(), current.getRows());   
        int right = index(current.getI()+1, current.getJ(), current.getCols(), current.getRows());  
        
        if(up >= 0 && !(cells.get(up).isVisited())) {
            //System.out.println("UP");
            neighbors.add(cells.get(up));
        }      
                
        if(down >= 0 && !(cells.get(down).isVisited())) {
            //System.out.println("DOWN");
            neighbors.add(cells.get(down));
        }
        
        if(left >= 0 && !(cells.get(left).isVisited())) {
            //System.out.println("LEFT");
            neighbors.add(cells.get(left));
        }
        
        if(right >= 0 && !(cells.get(right).isVisited())) {
            //System.out.println("RIGHT");
            neighbors.add(cells.get(right));
        }        
        
        if(neighbors.size() > 0) {
            Random rand = new Random();
            int pos;
                if(neighbors.size()<0) {
                    pos = rand.nextInt(neighbors.size()+1);
                } else {
                    pos = rand.nextInt(neighbors.size());
                    //
                    //System.out.println("Bound: "+neighbors.size());
                }                
                //System.out.println("Pos: " + pos);
                return neighbors.get(pos);  
                //return neighbors.get(rand.nextInt(neighbors.size()));
        } else {
            //System.out.println("false");
            return null;            
        }       
    }
    
    public void removeWalls(Cell _current, Cell _next) {
        
        int x = _current.getI() - _next.getI();
        if(x==1) {
            _current.getTRBL().set(3, false);
            _next.getTRBL().set(1, false);
        } else {
            if(x==-1) {
                _current.getTRBL().set(1, false);
                _next.getTRBL().set(3, false);
            }
        }
        
        int y = _current.getJ() - _next.getJ();
        if(y==1) {
            _current.getTRBL().set(0, false);
            _next.getTRBL().set(2, false);
        } else {
            if(y==-1) {
                _current.getTRBL().set(2, false);
                _next.getTRBL().set(0, false);
            }
        }
        
        //System.out.println("1: "+ (boolean)cells.get(cells.size()-2).getTRBL().get(0));
        //System.out.println("3: "+ (boolean)cells.get(cells.size()-(Cell.getCols()-1)).getTRBL().get(2));
        if((boolean)cells.get(cells.size()-2).getTRBL().get(1) && (boolean)cells.get(cells.size()-(Cell.getCols()-1)).getTRBL().get(2)) { 
            //System.out.println("true");
                Random rand = new Random();
            if(rand.nextBoolean()) {
                cells.get(cells.size()-2).getTRBL().set(1, false);
            } else {
                cells.get(cells.size()-(Cell.getCols()-1)).getTRBL().set(3, false);
            }  
        }
        
    } 
    
    public int randPosI() {
        Random randPos = new Random();
        int i=randPos.nextInt(Cell.getCols()-1);
        return i;        
    }
    
    public int randPosJ() {
        Random randPos = new Random();
        int j=randPos.nextInt(Cell.getRows()-1);
        return j;
    }
    
    public void randBonus(int _rand) {
        switch(_rand) {
            case 0:
                bonus = new BonusMalus(randPosI(), randPosJ(), true);
            break;
            case 1:
                bonus2 = new BonusMalus(randPosI(), randPosJ(), true);
            break;
        }
    }
    
    public void randMalus(int _rand) {
        switch(_rand) {
            case 0:
                malus = new BonusMalus(randPosI(), randPosJ(), false);
            break;
            case 1:
                malus2 = new BonusMalus(randPosI(), randPosJ(), false);
            break;
        }
    }
    
    public class KeyListener extends KeyAdapter {
        @Override
        public void keyPressed (KeyEvent k) {
            int key = k.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                    UP = true;
                break;
                case KeyEvent.VK_DOWN:
                    DOWN = true;
                break;
                case KeyEvent.VK_LEFT:
                    LEFT = true;
                break;
                case KeyEvent.VK_RIGHT:
                    RIGHT = true;
                break;                               
                case KeyEvent.VK_ENTER:  
                    if(!gameActive) {
                        startGame();
                    } 
                break;
                case KeyEvent.VK_SPACE:       
                    if(!pause && gameActive) {
                      MainFrame.pausedStatus();
                      pause=true;                        
                    } else {
                        pause=false;
                    }                     
                break;
                case KeyEvent.VK_ESCAPE:
                    MainPanel.hidePieces();
                    resetGame();                    
                    refreshed=false;
                    MainFrame.changeGameStatus(MainFrame.LEVELS);
                break; 
                case KeyEvent.VK_BACK_SPACE:
                    MainPanel.hidePieces();
                    resetGame();
                    MainFrame.changeGameStatus(MainFrame.MENU);
                break;
                case KeyEvent.VK_R:
                    if(gameActive && !creating) {
                        updateColor();
                        refreshed=true;
                        MainPanel.hidePieces();
                        resetGame();
                        startGame();
                    }                    
                break;
                case KeyEvent.VK_M:
                        MainPanel.hidePieces();
                        resetGame();
                        refreshed=false;
                        MainFrame.changeGameStatus(MainFrame.MODE);
                break;
            }
        }
        @Override
        public void keyReleased (KeyEvent k) {
            int key = k.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                    UP = false;
                break;
                case KeyEvent.VK_DOWN:
                    DOWN = false;
                break;
                case KeyEvent.VK_LEFT:
                    LEFT = false;
                break;
                case KeyEvent.VK_RIGHT:
                    RIGHT = false;
                break;
            }
        }
    }
    
    public class MovementThread implements Runnable {
        @Override
        public void run() {
            while(gameActive) {                  
                try {
                    while(pause) {
                        Thread.sleep(1000);
                        if(!MainFrame.getMazeSolvedFrame().isVisible()) {
                        pause=false;
                        }                                                
                    }
                        Thread.sleep(220); 
                    
                    if(LEFT) {
                   int left = MainPanel.player.moveLEFT();
                   if(left >= 0) {
                      player.setCell(cells.get(left));
                      repaint();
                   }     
                }
                
                if(RIGHT) {
                   int right = MainPanel.player.moveRIGHT();
                   if(right >= 0) {
                      player.setCell(cells.get(right));
                      repaint();
                   }
                }
                
                if(UP) {
                   int up = MainPanel.player.moveUP();
                   if(up >= 0) {
                      player.setCell(cells.get(up));
                      repaint(); 
                   }     
                   repaint();
                }
                
                if(DOWN) {
                   int down = MainPanel.player.moveDOWN();
                   if(down >= 0) {
                      MainPanel.player.setCell(cells.get(down));
                      repaint();                      
                   }             
                } 
                
                if(player.collides(exit)) {
                          refreshed=false;
                          StatisticsPanel.getTime(ActiveStatistics.getSec(), ActiveStatistics.getMin(), level);
                          //MainFrame.changeGameStatus(MainFrame.LEVELS);
                          MainPanel.hidePieces();
                          resetGame();
                }
                
                if(malus!=null && MainPanel.player.bonusMalusCollision(malus)) {
                      BonusMalus.substractTimerMalus();
                      BonusMalus.sumChronometerMalus();
                      malus.setVisible(false);
                      malus=null;
                      malus_count++;
                      repaint();
                }
                
                if(bonus!=null && MainPanel.player.bonusMalusCollision(bonus)) {
                      BonusMalus.substractChronometerBonus();
                      BonusMalus.sumTimerBonus();
                      bonus.setVisible(false);
                      bonus = null;
                      bonus_count++;
                      repaint();
                }
                
                if(malus2!=null && MainPanel.player.bonusMalusCollision(malus2)) {
                      BonusMalus.substractTimerMalus();
                      BonusMalus.sumChronometerMalus();
                      malus2.setVisible(false);
                      malus2 = null;
                      malus_count++;
                      repaint();
                }
                
                if(bonus2!=null && MainPanel.player.bonusMalusCollision(bonus2)) {
                      BonusMalus.substractChronometerBonus();
                      BonusMalus.sumTimerBonus();
                      bonus2.setVisible(false);
                      bonus2 = null;
                      bonus_count++;
                      repaint();
                }
                
                repaint();
                } catch(InterruptedException ex) {
                    
                }
            }
        }
    }
    
    public class MazeThread implements Runnable {
        @Override
        public void run() {        
            creating=true;
            while(gameActive && creating) {                  
                try {
                    Thread.sleep(1);
                      next = checkNeighbors();
                    if(next!=null) {
                      next.setVisited(true);
                      removeWalls(current, next);
                      current.setCurrent(false);
                      current = next;
                      current.setCurrent(true);
                      backT.push(current);
                      repaint();
                  } else {
                        if(!backT.empty()) {
                           current = (Cell)backT.pop(); 
                           current.setCurrent(false);//draw
                           repaint();
                        } else {
                            //System.out.println("empty");
                            exit.setVisible(true);
                            MainPanel.player.setVisible(true);
                            repaint();                             
                            startMoving();
                            creating = false;
                        }                         
                                                    
                    }
                } catch (InterruptedException ex) {
                    
                }
            }
        }
    }
    
    public class BonusMalusThread implements Runnable {
        @Override
        public void run() {
            Random rand = new Random();
            while(gameActive) {                
                try {
                    while(pause || creating) {
                        Thread.sleep(1000);
                    }
                    Thread.sleep(rand.nextInt(6000)); 
                    randBonus(rand.nextInt(2));
                    randMalus(rand.nextInt(2));
                    //bonus = new BonusMalus(3,3,true);
                    //malus = new BonusMalus(3,3,false); //bonus testing
                    repaint();                    
                    Thread.sleep(rand.nextInt(10000));
                } catch (InterruptedException ex) {
                    
                }
            }
        }
    }
}










/*public int randPosI() {
        Random randPos = new Random();
        int i=randPos.nextInt(Cell.getCols()-4);
        return i;        
    }
    
    public int randPosJ() {
        Random randPos = new Random();
        int j=randPos.nextInt(Cell.getRows()-4);
        return j;
    }
    
    public int randCell(int _i, int _j) {
        int cell = _i+_j*Cell.getRows();
        return cell;
    }
    
    public void randPosExit(int _i, int _j) {
        int cell = randCell(_i, _j);
        exit = new Piece(_i, _j, cells.get(cell));
        exit.setRandPos(_i, _j);
        System.out.println("I: " + exit.getI()+" J:"+exit.getJ());
    }*/
