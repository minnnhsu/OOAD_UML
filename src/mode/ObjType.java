package mode;

import java.awt.Point;
import shape.*;

public enum ObjType {
    CLASS {
        @Override
        public Obj create(Point p) {
            return new ClassObj(p);
        }
    },
    USECASE {
        @Override
        public Obj create(Point p) {
            return new UsecaseObj(p);
        }
    };

    public abstract Obj create(Point p);
}
