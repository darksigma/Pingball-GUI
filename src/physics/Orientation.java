package physics;

/**
 * Defines a set of orientations that objects can be in as specified for
 * pingball. All orientation values are angular rotations clockwise of an entire
 * gadget. Class is Immutable.
 * @author Erik
 *
 */
public enum Orientation {
    _0(0), _90(90), _180(180), _270(270);

    /**
     * The value of this orientation
     */
    private final int angleValue;

    /**
     * Create a new Orientation with the specified angle
     * @param angle the angle in degrees that a gadget is rotated clockwise by
     */
    private Orientation(int angle) {
        angleValue = angle;
    }

    /**
     * The numerical value of this Orientation in degrees.
     * @return the value of this Orientation
     */
    public int angle() {
        return angleValue;
    }

    /**
     * Calculates and returns the next orientation angle from this after a
     * 90 degree rotation clockwise
     * @return the next angle
     */
    public Orientation rotateClockwise() {
        switch(this) {
        case _0:
            return _90;
        case _90:
            return _180;
        case _180:
            return _270;
        default:
            return _0; //_270 degrees
        }
    }

    /**
     * Calculates and returns the next orientation angle from this after a
     * 90 degree rotation counter-clockwise
     * @return the next angle
     */
    public Orientation rotateCounterClockwise() {
        switch(this) {
        case _0:
            return _270;
        case _90:
            return _0;
        case _180:
            return _90;
        default:
            return _180; //_270 degrees
        }
    }
}
