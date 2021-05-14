package AI_MAZE.Logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class HotArea {
    private Rectangle area;
    private int width;
    private Color color;     
    
    public HotArea (int _x, int _y, int _width, Color _color) {
        this.color = _color;
        this.area = new Rectangle(new Point(_x,_y), new Dimension(_width,_width));
    }
    
    public Point getPosition() {
        Point point = new Point(area.x, area.y);
        return point;
    }
    
    public int getWidth() {
        return this.area.width;
    }
    
    public Color getColor() {
        return color;
    }
    
    public boolean contains (Point pPoint) {
        return this.area.contains(pPoint);
    }
    
}
