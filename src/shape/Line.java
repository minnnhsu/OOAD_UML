package shape;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

public class Line {
    protected Point startPoint;
    protected Point endPoint;

    public Line(Point startPoint, Point endPoint, Obj startObj) {
        this.startPoint = startObj.choosePort(startPoint);
        this.endPoint = endPoint;
    }

    public void setEndOject(Obj endObj) {
        this.endPoint = endObj.choosePort(endPoint);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
}
