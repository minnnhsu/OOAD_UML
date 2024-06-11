package mode;

import java.awt.Point;
import shape.*;

public enum LineType {
    ASSOCIATION {
        @Override
        public Line create(Point startP, Point endP, Obj startO) {
            return new AssocLine(startP, endP, startO);
        }
    },
    GENERALIZATION {
        @Override
        public Line create(Point startP, Point endP, Obj startO) {
            return new GeneralLine(startP, endP, startO);
        }
    },
    COMPOSITION {
        @Override
        public Line create(Point startP, Point endP, Obj startO) {
            return new ComposLine(startP, endP, startO);
        }
    };

    public abstract Line create(Point startP, Point endP, Obj startO);
}
