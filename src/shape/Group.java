package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Group extends Obj {
    private List<Obj> objects = new ArrayList<>();

    public Group(List<Obj> objects) {
        this.objects = objects;
        setBound();
    }

    private void setBound() {
        Point newTopLeft = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Point newBottomRight = new Point(0, 0);
        for (Obj obj : objects) {
            if (obj.topLeft.x <= newTopLeft.x)
                newTopLeft.x = obj.topLeft.x;
            if (obj.topLeft.y <= newTopLeft.y)
                newTopLeft.y = obj.topLeft.y;
            if (obj.bottomRight.x >= newBottomRight.x)
                newBottomRight.x = obj.bottomRight.x;
            if (obj.bottomRight.y >= newBottomRight.y)
                newBottomRight.y = obj.bottomRight.y;
        }
        super.topLeft = newTopLeft;
        super.bottomRight = newBottomRight;
    }

    @Override
    public void draw(Graphics g) {
        for (Obj obj : objects) {
            obj.draw(g);
        }
    }

    @Override
    public void drawSelected(Graphics g) {
        int width = bottomRight.x - topLeft.x;
        int height = bottomRight.y - topLeft.y;
        g.setColor(new Color(100, 255, 100, 100));
        g.drawRect(topLeft.x, topLeft.y, width, height);
        g.setColor(new Color(100, 255, 100, 50));
        g.fillRect(topLeft.x, topLeft.y, width, height);
    }

    @Override
    public List<Obj> inside(Point pos) {
        List<Obj> temp = new ArrayList<>();
        if (pos.x >= topLeft.x && pos.x <= bottomRight.x &&
                pos.y >= topLeft.y && pos.y <= bottomRight.y) {
            temp.add(this);
        }
        List<Obj> check = new ArrayList<>();
        for (int i = objects.size() - 1; i >= 0; i--) {
            check = objects.get(i).inside(pos);
            if (check.size() > 0) {
                temp.addAll(check);
                break;
            }
        }
        return temp;
    }

    @Override
    public void move(Point pos) {
        super.move(pos);
        for (Obj obj : objects) {
            obj.move(pos);
        }
    }

    public List<Obj> getObjects() {
        return objects;
    }
}
