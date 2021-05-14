package AI_MAZE.Logic;

import java.util.ArrayList;

public class Movements {
    
    private ArrayList<Integer> movements;
    private ArrayList<Integer> visited_cells;
    private float score;
    private boolean stuck;
    
    public Movements () {
        movements = new ArrayList<>();
        visited_cells = new ArrayList<>();
        //this.stuck=false;
    }
    
    public ArrayList getMovements() {
        return movements;
    }
    
    public ArrayList getVisitedCells() {
        return visited_cells;
    }
    
    public void setScore(float _score) {
        score = _score;
    }
    
    public float getScore() {
        return score;
    }
    
    public void setStuck(boolean stuck) {
        this.stuck = stuck;
    }
    
    public boolean getStuck() {
        return this.stuck;
    }
}
