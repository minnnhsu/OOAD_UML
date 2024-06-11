package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import shape.*;
import UI.*;

public class SelectMode extends Mode {
    private Canvas canvas;
    private boolean singleObj = false;
    private Point startPoint;

    public SelectMode(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        canvas.setSelectedObjects(findObj(startPoint, null));
        if (canvas.getSelectedObjects().size() > 0) {
            singleObj = true;
        } else
            singleObj = false;
        repaintCanvas(canvas);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (singleObj) {
            Obj temp = canvas.getSelectedObjects().get(0);
            temp.move(new Point(e.getPoint().x - startPoint.x, e.getPoint().y - startPoint.y));
            startPoint = e.getPoint();
            repaintCanvas(canvas);
        } else {
            canvas.drawSelectWindow(startPoint, e.getPoint());
            canvas.setSelectedObjects(findObj(startPoint, e.getPoint()));
            repaintCanvas(canvas);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvas.clearSelection();
    }

    public List<Obj> findObj(Point startPoint, Point endPoint) {
        List<Obj> temp = new ArrayList<>();
        List<Obj> checkObjs = canvas.getObjests();
        if (endPoint == null) {
            for (int i = checkObjs.size() - 1; i >= 0; i--) {
                temp = checkObjs.get(i).inside(startPoint);
                if (temp.size() > 0) {
                    break;
                }
            }
            return temp;
        } else {
            int checkBound[] = { Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y),
                    Math.max(startPoint.x, endPoint.x), Math.max(startPoint.y, endPoint.y) };
            for (int i = checkObjs.size() - 1; i >= 0; i--) {
                if (checkObjs.get(i).getTopLeft().x >= checkBound[0] && checkObjs.get(i).getTopLeft().y >= checkBound[1]
                        &&
                        checkObjs.get(i).getBottomRight().x <= checkBound[2]
                        && checkObjs.get(i).getBottomRight().y <= checkBound[3])
                    temp.add(checkObjs.get(i));
            }
            return temp;
        }
    }
}
