package mode;

import java.awt.Point;
import shape.*;

public class ShapeFactory {
	public Obj createObj(String objType, Point p) {
		if (objType.equals("class")) {
			return new ClassObj(p);
		} else if (objType.equals("usecase")) {
			return new UsecaseObj(p);
		}
		/*
		 * else if(objType.equals("group")){
		 * return new Group(p);
		 * }
		 */
		return null;
	}

	public Line createLine(String lineType, Point startP, Point endP, BasicObj startO) {
		if (lineType.equals("association")) {
			return new AssocLine(startP, endP, startO);
		}
		if (lineType.equals("generalization")) {
			return new GeneralLine(startP, endP, startO);
		}
		if (lineType.equals("composition")) {
			return new ComposLine(startP, endP, startO);
		}
		return null;
	}
}
