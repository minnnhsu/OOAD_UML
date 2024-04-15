package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class UsecaseObj extends BasicObj {
    static int width = 120;
    static int height = 60;

    public UsecaseObj(Point topLeft) {
        super(topLeft, new Point(topLeft.x + width, topLeft.y + height), width, height);
        this.objName = "use case";
    }

    @Override
    public void draw(Graphics g) {
        int x1 = super.topLeft.x;
        int y1 = super.topLeft.y;

        // Draw the use case object oval
        g.setColor(Color.BLACK);
        g.drawOval(x1, y1, width, height);

        // Draw the object name
        int stringWidth = g.getFontMetrics().stringWidth(objName);
        int startX = x1 + (width - stringWidth) / 2;
        g.drawString(objName, startX, y1 + 32);

    }

    @Override
    public List<Obj> inside(Point pos) {
        List<Obj> temp = new ArrayList<>();
        Point center = new Point(super.topLeft.x + width / 2, super.topLeft.y + height / 2);
        double a = width / 2.0;
        double b = height / 2.0;

        double part1 = Math.pow(pos.x - center.x, 2) / Math.pow(a, 2);
        double part2 = Math.pow(pos.y - center.y, 2) / Math.pow(b, 2);

        if ((part1 + part2) <= 1) {
            temp.add(this);
        }
        return temp;
    }

}
