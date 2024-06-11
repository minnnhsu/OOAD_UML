package shape;

import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

public abstract class Obj {
    protected Point topLeft;
    protected Point bottomRight;

    public Obj() {
    }

    public Obj(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public abstract void draw(Graphics g);

    public abstract void drawSelected(Graphics g);

    public abstract List<Obj> inside(Point pos);

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void move(Point pos) {
        topLeft.x += pos.x;
        topLeft.y += pos.y;
        bottomRight.x += pos.x;
        bottomRight.y += pos.y;
    }

    public Point choosePort(Point pos) {
        return null;
    }

    public void setObjName(String objName) {

    }

    public List<Obj> getObjects() {
        return null;
    }

}
