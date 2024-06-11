package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class ComposLine extends Line {
    public ComposLine(Point startPoint, Point endPoint, Obj startObj) {
        super(startPoint, endPoint, startObj);
    }

    public void draw(Graphics g) {
        super.draw(g); // Draw the line first

        // Draw the diamond-shaped arrowhead
        g.setColor(Color.BLACK);
        int arrowSize = 15; // Size of the arrowhead
        double angle = Math.atan2(endPoint.y - startPoint.y, endPoint.x - startPoint.x);
        int x3 = (int) (endPoint.x - arrowSize * Math.cos(angle - Math.PI / 4));
        int y3 = (int) (endPoint.y - arrowSize * Math.sin(angle - Math.PI / 4));
        int x4 = (int) (endPoint.x - arrowSize * Math.cos(angle + Math.PI / 4));
        int y4 = (int) (endPoint.y - arrowSize * Math.sin(angle + Math.PI / 4));
        int x5 = (int) (x4 - arrowSize * Math.cos(angle - Math.PI / 4));
        int y5 = (int) (y4 - arrowSize * Math.sin(angle - Math.PI / 4));

        int[] xPoints = { endPoint.x, x3, x5, x4 }; // X coordinates for diamond
        int[] yPoints = { endPoint.y, y3, y5, y4 }; // Y coordinates for diamond
        g.setColor(Color.WHITE);
        g.fillPolygon(xPoints, yPoints, 4); // Fill the polygon to create a diamond

        // Draw the border of the diamond in white
        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, 4);
    }
}
