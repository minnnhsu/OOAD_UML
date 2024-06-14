package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import shape.*;
import UI.*;

public class LineMode extends Mode {
    private LineType lineType;
    private Canvas canvas;
    private Point startPoint;
    private Point endPoint;
    private Obj startObj;
    private Obj endObj;
    private boolean linestart = false;

    public LineMode(LineType lineType, Canvas canvas) {
        this.lineType = lineType;
        this.canvas = canvas;
    }

    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        startObj = findObj(startPoint);
        if (startObj != null && startObj.choosePort(startPoint) != null) {
            linestart = true;
            canvas.setSelectedObjects(new ArrayList<Obj>(Arrays.asList(startObj)));
        }
        repaintCanvas(canvas);
    }

    public void mouseReleased(MouseEvent e) {
        endPoint = e.getPoint();
        endObj = findObj(endPoint);
        if (linestart) {
            if (endObj != null && !startObj.equals(endObj) && endObj.choosePort(endPoint) != null) {
                Line line = lineType.create(startPoint, endPoint, startObj);
                line.setEndOject(endObj);
                canvas.addLine(line);
                canvas.setSelectedObjects(new ArrayList<Obj>(Arrays.asList(endObj)));
            }
            linestart = false;
        }
        canvas.setCurrentLine(null);
        repaintCanvas(canvas);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (linestart) {
            endPoint = e.getPoint();
            Line line = lineType.create(startPoint, endPoint, startObj);
            canvas.setCurrentLine(line);
            repaintCanvas(canvas);
        }

    }

    public Obj findObj(Point pos) {
        List<Obj> temp = new ArrayList<>();
        List<Obj> checkObjs = canvas.getObjests();
        for (int i = checkObjs.size() - 1; i >= 0; i--) {
            temp = checkObjs.get(i).inside(pos);
            if (temp.size() > 0) {
                break;
            }
        }
        if (temp.size() == 0) {
            return null;
        }
        return temp.get(temp.size() - 1);
    }

}
