package mode;

import java.awt.event.MouseEvent;
import shape.*;
import UI.*;

public class ObjMode extends Mode {
    private String objType = null;
    private ShapeFactory factory = new ShapeFactory();
    private Canvas canvas;

    public ObjMode(String objType, Canvas canvas) {
        this.objType = objType;
        this.canvas = canvas;
    }

    public void mousePressed(MouseEvent e) {
        Obj Obj = factory.createObj(objType, e.getPoint());
        canvas.addObj(Obj);
        canvas.repaint();
    }

}
