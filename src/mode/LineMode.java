package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import shape.*;
import UI.*;

public class LineMode extends Mode {
    private String lineType = null;
    private ShapeFactory factory = new ShapeFactory();
    private Canvas canvas;
    private Point startPoint;
    private Point endPoint;
    private BasicObj startObj;
    private BasicObj endObj;
    private boolean linestart = false;

    public LineMode(String lineType, Canvas canvas) {
        this.lineType = lineType;
        this.canvas = canvas;
    }

    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        startObj = findObj(startPoint);
        if (startObj != null) {
            linestart = true;
        }
        canvas.setSelectedObjects(new ArrayList<Obj>(Arrays.asList(startObj)));
        canvas.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        endPoint = e.getPoint();
        endObj = findObj(endPoint);
        if (linestart) {
            if (endObj != null && !startObj.equals(endObj)) {
                Line line = factory.createLine(lineType, startPoint, endPoint, startObj);
                line.setEndOject(endObj);
                canvas.addLine(line);
            }
            canvas.setSelectedObjects(new ArrayList<Obj>(Arrays.asList(endObj)));
            linestart = false;
        }
        canvas.setCurrentLine(null);
        canvas.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (linestart) {
            endPoint = e.getPoint();
            Line line = factory.createLine(lineType, startPoint, endPoint, startObj);
            canvas.setCurrentLine(line);
            canvas.repaint();
        }

    }

    public BasicObj findObj(Point pos) {
        List<Obj> temp = new ArrayList<>();
        List<Obj> checkObjs = canvas.getObjests();
        for (int i = checkObjs.size() - 1; i >= 0; i--) {
            temp = checkObjs.get(i).inside(pos);
            if (temp.size() > 0 && temp.get(temp.size() - 1) instanceof BasicObj) {
                break;
            }
        }
        if (temp.size() == 0 || !(temp.get(temp.size() - 1) instanceof BasicObj)) {
            return null;
        }
        return (BasicObj) temp.get(temp.size() - 1);
    }
}
