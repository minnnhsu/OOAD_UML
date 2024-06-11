package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Port {
    private Point position;
    private int size = 10;

    public Port(Point position) {
        this.position = position;
    }

    public void draw(Graphics g) {
        int halfSize = size / 2;
        g.setColor(Color.BLACK);
        g.fillRect(position.x - halfSize, position.y - halfSize, size, size);
    }

    public void move(Point pos) {
        position.x += pos.x;
        position.y += pos.y;
    }

    public int getSize() {
        return size;
    }
}
