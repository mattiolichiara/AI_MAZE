package AI_MAZE.Logic;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Piece extends Cell{
    
    private int i;
    private int j;
    private Color color;
    private boolean visible;
    private Cell cell;
    
    public Piece (int _i, int _j, Cell _cell/*, Color _color*/) {
        super(_i, _j);
        this.i = _i;
        this.j = _j;
        this.cell = _cell;
        //this.color = _color;
    }
    
    @Override
    public void draw (Graphics g) {
        int x = this.i*Cell.getWidth();
        int y = this.j*Cell.getWidth();    
        
        g.setColor(this.color);
        g.drawRect(x+Cell.getWidth()/4, y+Cell.getWidth()/4, Cell.getWidth()-Cell.getWidth()/2, Cell.getWidth()-Cell.getWidth()/2);
    }
    
    public int index (int _i, int _j, int _cols, int _rows) {
        if(_i < 0 || _j < 0 || _j > _cols-1 || _i > _rows-1) {
          return -1;
        }
        
       int index = _i+_j*_cols;
       return index;
    }
    
    public boolean collides(Piece _piece) {
        if(this.i==_piece.getI() && this.j == _piece.getJ()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean bonusMalusCollision(BonusMalus bonusMalus) {
        if(this.i==bonusMalus.getI() && this.j == bonusMalus.getJ()) {
            return true;
        } else {
            return false;
        }
    }
    
    /*public void setRandPos(int _i, int _j) {
        this.i = _i;
        this.j = _j;
    }*/
    
    public int moveUP() {
        
        int up = index(i, j-1, Cell.getCols(), Cell.getRows()); 
        
        if(up >= 0) {
            boolean _up = (boolean)cell.getTRBL().get(0);
            //System.out.println("Up: " + _up);
            if(!_up) {
                return up;
            }else {
                return -1;
            }
        } else {
            return -1;
        }
    }
    
    public int moveDOWN() {
                           
        int down = index(i, j+1, Cell.getCols(), Cell.getRows());  
        
        if(down >= 0) {
            boolean _down = (boolean)cell.getTRBL().get(2);
            if(!_down) {
                return down;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
    
    public int moveLEFT() {
         
        int left = index(i-1, j, Cell.getCols(), Cell.getRows());  
        
        if(left >= 0) {
            boolean _left = (boolean)cell.getTRBL().get(3);
            //System.out.println("Left: " + _left);
            if(!_left) {
                return left;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
    
    public int moveRIGHT() {
          
        int right = index(i+1, j, Cell.getCols(), Cell.getRows());  
        
        if(right >= 0) {
            boolean _right = (boolean)cell.getTRBL().get(1);
            //System.out.println("Right: " + _right);
            if(!_right) {
                return right;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
    
    public void setColor(Color _color) {
        this.color = _color;
    }
    
    public void setCell(Cell _cell) {
        this.cell = _cell;
        this.i = _cell.getI();
        this.j = _cell.getJ();
    }
    
    public int getCell() {
        int position = index(this.i, this.j, Cell.getCols(), Cell.getRows());
        return position;
    }
    
    public void setVisible(boolean isIt) {
        this.visible = isIt;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
}
