package shape;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicObj extends Obj {
    protected String objName = "object name";
    protected List<Point> portPosition = new ArrayList<>();
    protected List<Port> ports = new ArrayList<>();

    // top, bottom, left, right: {changeX, changeY}
    protected int pointChange[][] = { { 1, 0 }, { 1, 2 }, { 0, 1 }, { 2, 1 } };
    protected int pointToPort[][] = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    protected int width;
    protected int height;
    protected int halfPortSize = 5;

    public boolean group_selected = false;

    public BasicObj(Point topLeft, Point bottomRight, int width, int height) {
        super(topLeft, bottomRight);
        this.width = width;
        this.height = height;
        setPort();
    }

    public abstract void draw(Graphics g);

    public void setPort() {
        // find points: top, bottom, left, right
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        for (int i = 0; i < 4; i++) {
            int tempX = topLeft.x + pointChange[i][0] * halfWidth;
            int tempY = topLeft.y + pointChange[i][1] * halfHeight;
            portPosition.add(new Point(tempX, tempY));
        }

        // points to ports
        for (int i = 0; i < 4; i++) {
            int tempX = portPosition.get(i).x + pointToPort[i][0] * halfPortSize;
            int tempY = portPosition.get(i).y + pointToPort[i][1] * halfPortSize;
            ports.add(new Port(new Point(tempX, tempY)));
        }
    }

    public String getObjName() {
        return objName;
    }

    @Override
    public void setObjName(String objName) {
        this.objName = objName;
    }

    @Override
    public void drawSelected(Graphics g) {
        for (Port p : ports)
            p.draw(g);
    }

    @Override
    public List<Obj> inside(Point pos) {
        List<Obj> temp = new ArrayList<>();
        if (pos.x >= topLeft.x && pos.x <= bottomRight.x &&
                pos.y >= topLeft.y && pos.y <= bottomRight.y) {
            temp.add(this);
        }
        return temp;
    }

    @Override
    public Point choosePort(Point p) {
        // top, bottom, left, right: {isOnRightSide by two diagonals}
        boolean checkPort[][] = { { true, false }, { false, true }, { false, false }, { true, true } };

        Point topRight = new Point(super.bottomRight.x, super.topLeft.y);
        Point bottomLeft = new Point(super.topLeft.x, super.bottomRight.y);

        boolean check[] = { isOnRightSide(super.topLeft, super.bottomRight, p),
                isOnRightSide(topRight, bottomLeft, p) };
        for (int i = 0; i < 4; i++) {
            if (checkPort[i][0] == check[0] && checkPort[i][1] == check[1])
                return portPosition.get(i);
        }
        return null;
    }

    public boolean isOnRightSide(Point A, Point B, Point P) {
        int APx = P.x - A.x;
        int APy = P.y - A.y;

        int ABx = B.x - A.x;
        int ABy = B.y - A.y;

        int crossProduct = APx * ABy - APy * ABx;

        return crossProduct > 0;
    }

    @Override
    public void move(Point pos) {
        super.move(pos);
        for (int i = 0; i < 4; i++) {
            portPosition.get(i).x += pos.x;
            portPosition.get(i).y += pos.y;
        }
        for (Port p : ports)
            p.move(pos);
    }
}
