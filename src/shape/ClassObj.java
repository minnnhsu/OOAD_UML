package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class ClassObj extends BasicObj {
    static int width = 100;
    static int height = 120;

    public ClassObj(Point topLeft) {
        super(topLeft, new Point(topLeft.x + width, topLeft.y + height), width, height);
        this.objName = "class name";
    }

    @Override
    public void draw(Graphics g) {
        int x1 = super.topLeft.x;
        int y1 = super.topLeft.y;

        g.setColor(Color.BLACK);
        g.drawRect(x1, y1, width, height);

        int portion = height / 3;
        g.drawLine(x1, y1 + portion, x1 + width, y1 + portion);
        g.drawLine(x1, y1 + 2 * portion, x1 + width, y1 + 2 * portion);

        // Draw the object name
        int stringWidth = g.getFontMetrics().stringWidth(objName);
        int startX = x1 + (width - stringWidth) / 2;
        g.drawString(objName, startX, y1 + portion - 15);

    }

}
