package AI_MAZE.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import javax.swing.JPanel;
import AI_MAZE.Logic.Cell;
import AI_MAZE.Logic.Movements;
import AI_MAZE.Logic.Piece;

public class AI_MainPanel extends JPanel {

    private int UP = 0;
    private int RIGHT = 1;
    private int DOWN = 2;
    private int LEFT = 3;
    private static int direction;
    int min = 1000000000;

    private static ArrayList<Integer> avaiable_directions;
    boolean solved = false;

    private Stack backT;
    private boolean refreshed;

    private ActiveStatistics activeStatistics;
    private StatisticsPanel statisticsPanel;
    private Movements movements;

    private static Piece exit;
    private static Piece player;

    private Cell current;
    private Cell next;

    private Cell cell;

    private ArrayList<Cell> neighbors;
    private static ArrayList<Cell> cells;

    private static Color color;

    public AI_MainPanel() {
        this.setSize(MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT);
        this.setLocation(27, 15);

        this.refreshed = false;

        activeStatistics = new ActiveStatistics();
        statisticsPanel = new StatisticsPanel();

        MainPanel.setGameActivity(false);
        MainPanel.setPause(false);

        cells = new ArrayList<>();

        //population = new ArrayList<>(); 
        switch (MainPanel.getLevel()) {
            case 1:
                Cell.setWidth(40);
                //AI_MainPanel.actual_moves=ONE_MOVEMENTS_SIZE;
                break;
            case 2:
                Cell.setWidth(30);
                //AI_MainPanel.actual_moves=TWO_MOVEMENTS_SIZE;
                break;
            case 3:
                Cell.setWidth(20);
                //AI_MainPanel.actual_moves=THREE_MOVEMENTS_SIZE;
                break;
            case 4:
                Cell.setWidth(10);
                //AI_MainPanel.actual_moves=FOUR_MOVEMENTS_SIZE;
                break;
        }

        Cell.setRows();
        Cell.setCols();

        for (int j = 0; j < Cell.getRows(); j++) {
            for (int i = 0; i < Cell.getCols(); i++) {
                cell = new Cell(i, j);
                cells.add(cell);
            }
        }

        color = MainPanel.getColor();

        AI_MainPanel.player = new Piece(0, 0, cells.get(0)/*, color*/);
        exit = new Piece(Cell.getRows() - 1, Cell.getRows() - 1, cells.get(cells.size() - 1)/*, color*/);

        exit.setVisible(false);

        this.backT = new Stack();

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.grabFocus();
        this.addKeyListener(new AI_MainPanel.AI_KeyListener());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT);

        for (int i = 0; i < cells.size() - 1; i++) {
            cells.get(i).draw(g);
        }

        if (exit != null && exit.isVisible()) {
            exit.draw(g);
        }
        if (AI_MainPanel.player != null && player.isVisible()) {
            AI_MainPanel.player.draw(g);
        }

    }

    public static Color giveColor() {
        return color;
    }

    public void newLevel(int _level) {
        updateColor();
        MainPanel.setLevel(_level);
        setGrid();
        updateGrid();
        ActiveStatistics.TimeInit();
    }

    public int setGrid() {
        if (MainPanel.getLevel() == 1) {
            //AI_MainPanel.actual_moves=ONE_MOVEMENTS_SIZE;
            return 40;
        }
        if (MainPanel.getLevel() == 2) {
            //AI_MainPanel.actual_moves=TWO_MOVEMENTS_SIZE;
            return 30;
        }

        if (MainPanel.getLevel() == 3) {
            //AI_MainPanel.actual_moves=THREE_MOVEMENTS_SIZE;
            return 20;
        }

        if (MainPanel.getLevel() == 4) {
            //AI_MainPanel.actual_moves=FOUR_MOVEMENTS_SIZE;
            return 10;
        } else {
            return -1;
        }
    }

    public void updateGrid() {

        Cell.setWidth(setGrid());
        Cell.setRows();
        Cell.setCols();

        cells.clear();
        for (int j = 0; j < Cell.getRows(); j++) {
            for (int i = 0; i < Cell.getCols(); i++) {
                cell = new Cell(i, j);
                cells.add(cell);
            }
        }

        repaint();
    }

    public void updateColor() {
        color = MainPanel.getColor();
    }

    public static void hidePieces() {
        AI_MainPanel.player.setVisible(false);
        exit.setVisible(false);
    }

    public void resetGame() {
        MainPanel.setGameActivity(false);
        //pause = false;
        if (!backT.empty()) {
            backT.clear();
        }
        if (neighbors != null && !neighbors.isEmpty()) {
            neighbors.clear();
        }
        for (int i = 0; i < cells.size() - 1; i++) {
            cells.get(i).setVisited(false);
            cells.get(i).setCurrent(false);
            cells.get(i).setIA_Visit(false);
            cells.get(i).setPenalty(false);
            for (int j = 0; j < cells.get(i).getTRBL().size(); j++) {
                cells.get(i).getTRBL().set(j, true);
            }
        }

        ActiveStatistics.TimeInit();
        AI_MainPanel.player.setVisible(false);
        exit.setVisible(false);
    }

    public void resolvingReset() {
        for (int i = 0; i < cells.size() - 1; i++) {
            cells.get(i).setVisited(false);
            cells.get(i).setCurrent(false);
            cells.get(i).setIA_Visit(false);
        }
    }

    public void startGame() {
        MainPanel.setGameActivity(true);
        MainPanel.setPause(false);

        Random rand = new Random();
        int num = rand.nextInt(cells.size() - 1);
        this.current = cells.get(num);
        this.current.setVisited(true); //depth first search
        this.current.setCurrent(true);

        AI_MainPanel.player = new Piece(0, 0, cells.get(0));
        exit = new Piece(Cell.getRows() - 1, Cell.getRows() - 1, cells.get(cells.size() - 1));

        AI_MainPanel.hidePieces();
        startVisiting();
    }

    public void startVisiting() {
        Thread visitThread = new Thread(new AI_MainPanel.MazeThread());
        visitThread.start();
    }

    public void startMoving() {
        for (int i = 0; i < cells.size() - 1; i++) {
            cells.get(i).setVisited(false);
            cells.get(i).setCurrent(false);
        }

        if (!refreshed) {
            Thread movementThread = new Thread(new AI_MainPanel.MovementThread());
            movementThread.start();

            activeStatistics.StartTime();
        }
    }

    public int index(int _i, int _j, int _cols, int _rows) {
        if (_i < 0 || _j < 0 || _j > _cols - 1 || _i > _rows - 1) {
            return -1;
        }

        int index = _i + _j * _cols;
        return index;
    }

    public Cell checkNeighbors() {

        neighbors = new ArrayList<>();

        if (current == null) {
            System.out.println("true");
        }

        int up = index(current.getI(), current.getJ() - 1, current.getCols(), current.getRows());
        int down = index(current.getI(), current.getJ() + 1, current.getCols(), current.getRows());
        int left = index(current.getI() - 1, current.getJ(), current.getCols(), current.getRows());
        int right = index(current.getI() + 1, current.getJ(), current.getCols(), current.getRows());

        if (up >= 0 && !(cells.get(up).isVisited())) {
            //System.out.println("UP");
            neighbors.add(cells.get(up));
        }

        if (down >= 0 && !(cells.get(down).isVisited())) {
            //System.out.println("DOWN");
            neighbors.add(cells.get(down));
        }

        if (left >= 0 && !(cells.get(left).isVisited())) {
            //System.out.println("LEFT");
            neighbors.add(cells.get(left));
        }

        if (right >= 0 && !(cells.get(right).isVisited())) {
            //System.out.println("RIGHT");
            neighbors.add(cells.get(right));
        }

        if (neighbors.size() > 0) {
            Random rand = new Random();
            int pos;
            if (neighbors.size() < 0) {
                pos = rand.nextInt(neighbors.size() + 1);
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
        if (x == 1) {
            _current.getTRBL().set(3, false);
            _next.getTRBL().set(1, false);
        } else {
            if (x == -1) {
                _current.getTRBL().set(1, false);
                _next.getTRBL().set(3, false);
            }
        }

        int y = _current.getJ() - _next.getJ();
        if (y == 1) {
            _current.getTRBL().set(0, false);
            _next.getTRBL().set(2, false);
        } else {
            if (y == -1) {
                _current.getTRBL().set(2, false);
                _next.getTRBL().set(0, false);
            }
        }

        //System.out.println("1: "+ (boolean)cells.get(cells.size()-2).getTRBL().get(0));
        //System.out.println("3: "+ (boolean)cells.get(cells.size()-(Cell.getCols()-1)).getTRBL().get(2));
        if ((boolean) cells.get(cells.size() - 2).getTRBL().get(1) && (boolean) cells.get(cells.size() - (Cell.getCols() - 1)).getTRBL().get(2)) {
            //System.out.println("true");
            Random rand = new Random();
            if (rand.nextBoolean()) {
                cells.get(cells.size() - 2).getTRBL().set(1, false);
            } else {
                cells.get(cells.size() - (Cell.getCols() - 1)).getTRBL().set(3, false);
            }
        }

    }

    public class AI_KeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent k) {
            int key = k.getKeyCode();
            switch (key) {
                case KeyEvent.VK_ENTER:
                    if (!MainPanel.getActiveStatus()) {
                        startGame();
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if (!MainPanel.getPauseStatus() && MainPanel.getActiveStatus()) {
                        MainFrame.pausedStatus();
                        MainPanel.setPause(true);
                    } else {
                        MainPanel.setPause(false);
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    AI_MainPanel.hidePieces();
                    resetGame();
                    refreshed = false;
                    MainFrame.changeGameStatus(MainFrame.LEVELS);
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    AI_MainPanel.hidePieces();
                    resetGame();
                    MainFrame.changeGameStatus(MainFrame.MENU);
                    break;
                /*case KeyEvent.VK_R:
                    if(MainPanel.getActiveStatus() && !MainPanel.getCreationStatus()) {
                        updateColor();
                        refreshed=true;
                        AI_MainPanel.hidePieces();
                        resetGame();
                        startGame();
                    }                 
                break;*/
                case KeyEvent.VK_M:
                    AI_MainPanel.hidePieces();
                    resetGame();
                    refreshed = false;
                    MainFrame.changeGameStatus(MainFrame.MODE);
                    break;
            }
        }
    }

    public class MazeThread implements Runnable {

        @Override
        public void run() {
            MainPanel.setCreation(true);
            while (MainPanel.getActiveStatus() && MainPanel.getCreationStatus()) {
                try {
                    Thread.sleep(1);
                    next = checkNeighbors();
                    if (next != null) {
                        next.setVisited(true);
                        removeWalls(current, next);
                        current.setCurrent(false);
                        current = next;
                        current.setCurrent(true);
                        backT.push(current);
                        repaint();
                    } else {
                        if (!backT.empty()) {
                            current = (Cell) backT.pop();
                            current.setCurrent(false);//draw
                            repaint();
                        } else {
                            //System.out.println("empty");
                            exit.setVisible(true);
                            AI_MainPanel.player.setVisible(true);
                            repaint();
                            resolvingReset();
                            startMoving();
                            MainPanel.setCreation(false);
                        }

                    }
                } catch (InterruptedException ex) {

                }
            }
        }
    }

    //fix player into the array and for 
    public class MovementThread implements Runnable {

        @Override
        public void run() {
            int speed = levelSpeed();
            movements = new Movements();
            player.setCell(cells.get(0));
            cells.get(cells.size() - 1).setVisited(false);
            boolean stuck = false;
            while (MainPanel.getActiveStatus() && !solved) {

                try {

                    while (MainPanel.getPauseStatus()) {
                        Thread.sleep(1000);
                        if (!MainFrame.getMazeSolvedFrame().isVisible()) {
                            MainPanel.setPause(false);
                        }
                    }
                    avaiable_directions = new ArrayList<>();
                    stuck = false;

                    int left = AI_MainPanel.player.moveLEFT();
                    int right = AI_MainPanel.player.moveRIGHT();
                    int up = AI_MainPanel.player.moveUP();
                    int down = AI_MainPanel.player.moveDOWN();

                    stuck = checkStuck(up, right, down, left, avaiable_directions);
                    if (stuck) {
                        System.out.println("\033[36mBackTracking");

                        int lastIndex = movements.getMovements().size() - 1;
                        movements.getMovements().remove(lastIndex);
                        movements.getVisitedCells().remove(lastIndex);

                        if (lastIndex <= 2) {
                            System.out.println("\033[33mPlayer Confused...Restarts");
                            player_confused(up, right, down, left, avaiable_directions);

                        } else {
                            int position = (int) movements.getVisitedCells().get(movements.getVisitedCells().size() - 1);
                            player.setCell(cells.get(position));
                        }

                        Thread.sleep(speed);
                    } else {
                        if (movements.getMovements().size() < 5) {
                            System.out.println("Random Movement");
                            randomMovements(up, right, down, left);
                        } else {
                            System.out.println("BFS Movement");
                            bfsMovements(up, right, down, left);
                        }

                        Thread.sleep(speed);
                    }

                    repaint();

                } catch (InterruptedException ex) {

                }
            }
        }
    }

    public void setMoves(boolean solved, int direction, int up, int right, int down, int left, Movements movements, Piece player) {

        if (direction == LEFT) {
            System.out.println("\033[35mleft, Cell: " + left);
            if (/*left >= 0 &&*/!cells.get(left).isVisited()) {
                player.setCell(cells.get(left));
                player.setI(cells.get(left).getI());
                player.setJ(cells.get(left).getJ());
                movements.getVisitedCells().add(left);
                cells.get(left).setVisited(true);
                cells.get(left).setIA_Visit(true);
                repaint();
            }
        }

        if (direction == RIGHT) {
            System.out.println("\033[35mright, Cell: " + right);
            if (/*right >= 0 &&*/!cells.get(right).isVisited()) {
                player.setCell(cells.get(right));
                player.setI(cells.get(right).getI());
                player.setJ(cells.get(right).getJ());
                movements.getVisitedCells().add(right);
                cells.get(right).setVisited(true);
                cells.get(right).setIA_Visit(true);
                repaint();
            }
        }

        if (direction == UP) {
            System.out.println("\033[35mup, Cell: " + up);
            if (/*up >= 0 &&*/!cells.get(up).isVisited()) {
                player.setCell(cells.get(up));
                player.setI(cells.get(up).getI());
                player.setJ(cells.get(up).getJ());
                movements.getVisitedCells().add(up);
                cells.get(up).setVisited(true);
                cells.get(up).setIA_Visit(true);
                repaint();
            }
            repaint();
        }

        if (direction == DOWN) {
            System.out.println("\033[35mdown, Cell: " + down);
            if (/*down >= 0 &&*/!cells.get(down).isVisited()) {
                player.setCell(cells.get(down));
                player.setI(cells.get(down).getI());
                player.setJ(cells.get(down).getJ());
                movements.getVisitedCells().add(down);
                cells.get(down).setVisited(true);
                cells.get(down).setIA_Visit(true);
                repaint();
            }
        }

        if (player.collides(exit)) {
            refreshed = false;
            solved = true;
            System.out.println("\033[31mSolved");
            MainFrame.changeGameStatus(MainFrame.LEVELS);
            StatisticsPanel.getTime(ActiveStatistics.getSec(), ActiveStatistics.getMin(), MainPanel.getLevel());            
            AI_MainPanel.hidePieces();
            resetGame();
        }
    }

    public boolean checkStuck(int up, int right, int down, int left, ArrayList<Integer> avaiable_directions) {
        if (up > 0 && !cells.get(up).isVisited() /*&& !cells.get(up).getPenalty()*/) {
            //System.out.println("up avaiable");
            avaiable_directions.add(UP);
        }
        if (right > 0 && !cells.get(right).isVisited() /*&& !cells.get(right).getPenalty()*/) {
            //System.out.println("right avaiable");
            avaiable_directions.add(RIGHT);
        }
        if (down > 0 && !cells.get(down).isVisited() /*&& !cells.get(down).getPenalty()*/) {
            //System.out.println("down avaiable");
            avaiable_directions.add(DOWN);
        }
        if (left > 0 && !cells.get(left).isVisited() /*&& !cells.get(left).getPenalty()*/) {
            //System.out.println("left avaiable");
            avaiable_directions.add(LEFT);
        }
        if (avaiable_directions.isEmpty()) {
            System.out.println("\033[32mStuck");
            //cells.get(AI_MainPanel.player.getCell()).setPenalty(true);
            return true;
        } else {
            return false;
        }
    }

    public int bfs(Cell position, Piece exit, int i) {
        int valid_index = 0;
        int score = calculateScore(position, exit);

        //System.out.println("Min prima: "+ min+ " Score: "+ score);
        if (min > score) {
            min = score;
            valid_index = i;
            //System.out.println("Min dopo: "+ min);
        }
        //System.out.println("Valid index: "+ valid_index);
        return valid_index;
    }

    public int calculateScore(Cell position, Piece exit) {
        /*int d1 = Math.abs(exit.getI() - position.getI());
        int d2 = Math.abs(exit.getJ() - position.getJ());

        int distance = d1 + d2;*/
        int position_cell = index(position.getI(), position.getJ(), Cell.getCols(), Cell.getRows());
        int distance = exit.getCell()-position_cell;
        //System.out.println("Cella: "+ position_cell+" Distanza: "+distance);
        return distance;
    }

    public void player_confused(int up, int right, int down, int left, ArrayList<Integer> avaiable_directions) {
        avaiable_directions = new ArrayList<>();

        movements.getMovements().clear();
        movements.getVisitedCells().clear();
        for (int i = 0; i < cells.size(); i++) {
            cells.get(i).setVisited(false);
        }

        player.setCell(cells.get(0));
        player.setI(0);
        player.setJ(0);
        movements.getVisitedCells().add(cells.get(0));
        movements.getMovements().add(-1);
    }

    public void randomMovements(int up, int right, int down, int left) {
        Random random = new Random();
        int rand_index = random.nextInt(avaiable_directions.size());
        
        movements.getMovements().add(avaiable_directions.get(rand_index));
        direction = (int) movements.getMovements().get(movements.getMovements().size() - 1);
        
        setMoves(solved, direction, up, right, down, left, movements, AI_MainPanel.player);
    }

    public void bfsMovements(int up, int right, int down, int left) {
        int valid_index = 0;
        for (int i = 0; i < avaiable_directions.size(); i++) {
            switch (avaiable_directions.get(i)) {
                case 0:
                    valid_index = bfs(cells.get(up), exit, i);
                    break;
                case 1:
                    valid_index = bfs(cells.get(right), exit, i);
                    break;
                case 2:
                    valid_index = bfs(cells.get(down), exit, i);
                    break;
                case 3:
                    valid_index = bfs(cells.get(left), exit, i);
                    break;

            }
        }
        min = 1000000000;
        movements.getMovements().add(avaiable_directions.get(valid_index));
        direction = (int) movements.getMovements().get(movements.getMovements().size() - 1);

        setMoves(solved, direction, up, right, down, left, movements, AI_MainPanel.player);
    }
    
    public int levelSpeed() {
        int speed=0;
        switch(MainPanel.getLevel()) {
            case 1:
                speed=100;
                break;
            case 2:
                speed=75;
                break;
            case 3:
                speed=50;
                break;
            case 4:
                speed=25;
                break;
        }
        return speed;
    }
}
