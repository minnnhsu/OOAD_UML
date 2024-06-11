package mode;

import java.awt.event.MouseEvent;
import shape.*;
import UI.*;

public class ObjMode extends Mode {
    private ObjType objType;
    private Canvas canvas;

    public ObjMode(ObjType objType, Canvas canvas) {
        this.objType = objType;
        this.canvas = canvas;
    }

    public void mousePressed(MouseEvent e) {
        Obj Obj = objType.create(e.getPoint());
        canvas.addObj(Obj);
        repaintCanvas(canvas);
    }

}
