package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class GeneralLine extends Line {
    public GeneralLine(Point startPoint, Point endPoint, BasicObj startObj) {
        super(startPoint, endPoint, startObj);
    }

    public void draw(Graphics g) {
        super.draw(g); // Draw the line first

        // Draw the arrowhead as a solid triangle
        g.setColor(Color.BLACK);
        int arrowSize = 20; // Size of the arrowhead
        double angle = Math.atan2(endPoint.y - startPoint.y, endPoint.x - startPoint.x);
        int x3 = (int) (endPoint.x - arrowSize * Math.cos(angle - Math.PI / 6));
        int y3 = (int) (endPoint.y - arrowSize * Math.sin(angle - Math.PI / 6));
        int x4 = (int) (endPoint.x - arrowSize * Math.cos(angle + Math.PI / 6));
        int y4 = (int) (endPoint.y - arrowSize * Math.sin(angle + Math.PI / 6));

        int[] xPoints = { endPoint.x, x3, x4 };
        int[] yPoints = { endPoint.y, y3, y4 };
        g.setColor(Color.WHITE); // Set the fill color to white
        g.fillPolygon(xPoints, yPoints, 3); // Fill the polygon to create a solid triangle

        // Draw the border of the triangle in black
        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, 3);

    }
}
