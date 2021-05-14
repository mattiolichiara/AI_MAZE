package AI_MAZE.Logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Button extends HotArea {
    private int xLabel;
    private int yLabel;
    private String label;
    private int height;
    private Color colorL;
    private boolean visible;
    int large;
    
    public Button (int _x,int _y, int _width, int _height, Color color) {
        super(_x, _y ,_width, color); 
        this.height = _height;
        this.visible = true;
    }
    
    public void setLabel(int _xL, int _yL, String _label, Color _colorL, int _large) {
        this.xLabel = _xL;
        this.yLabel = _yL;
        this.label = _label;
        this.colorL = _colorL;
        this.large = _large;
    }
    
    public void draw (Graphics g) {
        /*g.setColor(getColor());
        g.fillRect(getPosition().x, getPosition().y, getWidth(), getHeight());*/
        
        if(getColor()!=Color.black) {
            g.setColor(Color.black);
        } else {
            g.setColor(Color.white);
        }
        g.drawRect(getPosition().x, getPosition().y, getWidth(), height);
        
        if(label!=null) {
            g.setColor(colorL);
            g.setFont(new Font("Arial", Font.BOLD, large));
            g.drawString(label, xLabel, yLabel);
        }
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
}
