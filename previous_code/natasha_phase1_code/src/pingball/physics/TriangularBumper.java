package pingball.physics;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * {@code TriangularBumper} is a type of {@code Gadget} that can exist in
 * Pingball. A {@code TriangularBumper} is L x L sized, filled right triangle
 * that a ball can bounce off of. The right triangle has hypotenuse length
 * L*sqrt(2) and can be oriented in one of four ways by rotation of the {@code
 * Gadget} clockwise from the horizontal. The default orientation (rotation 0)
 * has a corner in the upper left, upper right, and lower left corners of an
 * L x L square. Because a {@code TriangularBumper} is a bumper type {@code
 * Gadget}, there is no action directly associated with it.
 * @author Erik Nguyen
 *
 */
public class TriangularBumper extends Gadget {

    /* Bumper rep invariant follows Gadget Rep invariant
     *
     * Gadget Rep Invariant: The bounding box of a gadget must allow it to
     * exist completely within the boundary of the board it is in. The name must
     * also consist of a letter or underscore followed optionally by more
     * word characters as defined by Pattern.java. Lastly, the gadget must be
     * two dimensional. This can be enumerated as follows:
     *  1) 0 <= x < 20, 0 < width, x + width <= 20
     *  2) 0 <= y < 20, 0 < height, y + height <= 20
     *  3) name matches [A-Za-z_][A-Za-z_0-9]*
     */

    /**
     * The defined orientation that this gadget is initialized in
     */
    private final Orientation orientation;

    /**
     * Stores the corners of this triangular bumper in clockwise order when
     * looking at a computer graphics coordinate system. counter clockwise
     * if considering normal Cartesian coordinate system.
     */
    private final Circle[] corners;

    /**
     * Stores the sides in clockwise order. The first and second items are
     * line segments that are parallel to the axes
     */
    private final LineSegment[] sides;


    /**
     * Create a new triangle shaped bumper based on the following parameters
     * with coefficient of reflection equal to 1.0.
     * @param board the board that this bumper is in
     * @param name the name of the bumper
     * @param x the x coordinate of the upper left corner of this bumper,
     *      0 <= x < 20
     * @param y the y coordinate of the upper left corner of this bumper,
     *      0 <= y < 20
     * @param orientation the orientation clockwise in degrees from the
     *  horizontal that this bumper is initialized in.
     */
    public TriangularBumper(Board board, String name, int x, int y,
                            Orientation orientation) {
        super(board, name, x, y);
        this.width = 1;
        this.height = 1;
        this.orientation = orientation;
        this.corners = new Circle[3];

        //possible ordinate values for corners
        int x1 = x;
        int x2 = x + width;
        int y1 = y;
        int y2 = y + height;

        switch(orientation) {
        case _0:
            corners[0] = new Circle(x1, y2, 0); //South-West
            corners[1] = new Circle(x1, y1, 0); //North-West
            corners[2] = new Circle(x2, y1, 0); //North-East
            break;
        case _90:
            corners[0] = new Circle(x1, y1, 0); //North-West
            corners[1] = new Circle(x2, y1, 0); //North-East
            corners[2] = new Circle(x2, y2, 0); //South-East
            break;
        case _180:
            corners[0] = new Circle(x2, y1, 0); //North-East
            corners[1] = new Circle(x2, y2, 0); //South-East
            corners[2] = new Circle(x1, y2, 0); //South-West
            break;
        case _270:
            corners[0] = new Circle(x2, y2, 0); //South-East
            corners[1] = new Circle(x1, y2, 0); //South-West
            corners[2] = new Circle(x1, y1, 0); //North-West
            break;
        }

        Vect c1 = corners[0].getCenter();
        Vect c2 = corners[1].getCenter();
        Vect c3 = corners[2].getCenter();

        LineSegment straightLine1 = new LineSegment(c1.x(), c1.y(), c2.x(), c2.y());
        LineSegment straightLine2 = new LineSegment(c2.x(), c2.y(), c3.x(), c3.y());
        LineSegment straightLine3 = new LineSegment(c3.x(), c3.y(), c1.x(), c1.y());

        this.sides = new LineSegment[] {straightLine1, straightLine2, straightLine3};

        checkRep();
    }

    /**
     * Bumper does not have an associated action
     */
    @Override public void startAction() {
        // TriangularBumpers don't have any defined actions
    }

    /**
     * Bumper does not have an associated action
     */
    @Override public void action(double dt) {
        // TriangularBumpers don't have any defined actions
    }

    @Override public String stateDescription() {
        StringBuilder state = new StringBuilder();
        //The type of gadget this is along with its name and location
        state.append(String.format(
                         "TriangularBumper(name=%s, x=%d, y=%d, refCoeff=%.2f, orientation=%s)",
                         name, x, y, reflectionCoefficient, orientation.toString()));
        //Include whatever other gadgets this is connected to as well
        state.append("\n\tTrigger Connected to:");
        if(triggers.isEmpty()) {
            state.append(" nothing");
        } else {
            for(Gadget gadget : triggers) {
                state.append(' ').append(gadget.getName());
            }
        }
        return state.toString();
    }

    @Override public void trigger(Ball ball) {
        super.trigger(ball);
    }

    /**
     * Returns the orientation that this bumper is in
     * @return the orientation of this bumper
     */
    public Orientation getOrientation() {
        return orientation;
    }

    @Override public String toString() {
        //0 pops up for 0 or 180 degree orientations
        return orientation.angle() % 180 == 0 ? "/" : "\\" ;
    }

    @Override
    void handleCollision(Ball ball) {
        Vect pos = ball.getCenterAsVect();
        double bX = pos.x(), bY = pos.y();

        int x1 = x, x2 = x + width;
        int y1 = y, y2 = y + height;

        //find the projections of the point on the side
        Vect projectionOnSlant = Geometry.perpendicularPoint(sides[2], ball.getCenterAsVect()); // slant side

        if(orientation == Orientation._0) {
            if(bX < x1 && bY < y1) {                          //hit north-west corner
                handleCollision(corners[1], ball);
            } else if(bX > x2 && projectionOnSlant == null) { //hit north-east corner
                handleCollision(corners[2], ball);
            } else if(bY > y2 && projectionOnSlant == null) { //hit south-west corner
                handleCollision(corners[0], ball);
            } else if(bX < x1) {                              //first parallel line
                handleCollision(sides[0], ball);
            } else if(bY < y1) {                              //second parallel line
                handleCollision(sides[1], ball);
            } else {                                          //hit the slant
                handleCollision(sides[2], ball);
            }
        } else if(orientation == Orientation._90) {
            if(bX > x2 && bY < y1) {                          //hit north-east corner
                handleCollision(corners[1], ball);
            } else if(bY > y2 && projectionOnSlant == null) { //hit south-east corner
                handleCollision(corners[2], ball);
            } else if(bX < x1 && projectionOnSlant == null) { //hit north-west corner
                handleCollision(corners[0], ball);
            } else if(bY < y1) {                              //first parallel line
                handleCollision(sides[0], ball);
            } else if(bX > x2) {                              //second parallel line
                handleCollision(sides[1], ball);
            } else {                                          //hit the slant
                handleCollision(sides[2], ball);
            }
        } else if(orientation == Orientation._180) {
            if(bX > x2 && bY > y2) {                          //hit south-east corner
                handleCollision(corners[1], ball);
            } else if(bX < x1 && projectionOnSlant == null) { //hit south-west corner
                handleCollision(corners[2], ball);
            } else if(bY < y1 && projectionOnSlant == null) { //hit north-east corner
                handleCollision(corners[0], ball);
            } else if(bX > x2) {                              //first parallel line
                handleCollision(sides[0], ball);
            } else if(bY > y2) {                              //second parallel line
                handleCollision(sides[1], ball);
            } else {                                          //hit the slant
                handleCollision(sides[2], ball);
            }
        } else if(orientation == Orientation._270) {
            if(bX < x1 && bY > y2) {                          //hit south-west corner
                handleCollision(corners[1], ball);
            } else if(bY > y1 && projectionOnSlant == null) { //hit north-west corner
                handleCollision(corners[2], ball);
            } else if(bX > x2 && projectionOnSlant == null) { //hit north-east corner
                handleCollision(corners[0], ball);
            } else if(bY > y2) {                              //first parallel line
                handleCollision(sides[0], ball);
            } else if(bX < x1) {                              //second parallel line
                handleCollision(sides[1], ball);
            } else {                                          //hit the slant
                handleCollision(sides[2], ball);
            }
        }

        checkRep();
    }

    @Override
    double calculateCollisionTimeWithBall(Ball ball) {
        double time = Double.POSITIVE_INFINITY;
        //corner collisions
        for(Circle corner : corners) {
            double collisionTime = Geometry.timeUntilCircleCollision(corner,
                                   ball.asCircle(), ball.getVelocityVector());
            time = Math.min(time, collisionTime);
        }
        //side collisions
        for(LineSegment segment : sides) {
            double collisionTime = Geometry.timeUntilWallCollision(segment,
                                   ball.asCircle(), ball.getVelocityVector());
            time = Math.min(time, collisionTime);
        }
        return time;
    }

    @Override
    public void putInDisplay(char[][] board, int hOffset, int vOffset) {
        board[vOffset + y][hOffset + x] = orientation.angle() % 180 == 0 ? '/' : '\\';
    }

}
