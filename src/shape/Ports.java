package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

public class Ports {
    private List<Point> position;
    private int size = 10;

    public Ports(List<Point> position, int size) {
        this.position = position;
        this.size = size;
    }

    public void draw(Graphics g) {
        int halfSize = size / 2;
        g.setColor(Color.BLACK);
        for (Point p : position) {
            int topLeftX = p.x - halfSize;
            int topLeftY = p.y - halfSize;

            g.fillRect(topLeftX, topLeftY, size, size);
        }

    }

    public void move(Point pos) {
        for (Point p : position) {
            p.x += pos.x;
            p.y += pos.y;
        }
    }

    public int getSize() {
        return size;
    }
}
