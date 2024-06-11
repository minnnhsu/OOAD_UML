package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class AssocLine extends Line {
    public AssocLine(Point startPoint, Point endPoint, Obj startObj) {
        super(startPoint, endPoint, startObj);
    }

    public void draw(Graphics g) {
        super.draw(g); // Draw the line first

        // Draw the arrowhead
        g.setColor(Color.BLACK);
        int arrowSize = 10; // Size of the arrowhead
        double angle = Math.atan2(endPoint.y - startPoint.y, endPoint.x - startPoint.x);
        int x3 = (int) (endPoint.x - arrowSize * Math.cos(angle - Math.PI / 6));
        int y3 = (int) (endPoint.y - arrowSize * Math.sin(angle - Math.PI / 6));
        int x4 = (int) (endPoint.x - arrowSize * Math.cos(angle + Math.PI / 6));
        int y4 = (int) (endPoint.y - arrowSize * Math.sin(angle + Math.PI / 6));

        g.drawLine(endPoint.x, endPoint.y, x3, y3);
        g.drawLine(endPoint.x, endPoint.y, x4, y4);

    }
}
