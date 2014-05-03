package pingball.physics;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * {@code SquareBumper} is a type of {@code Gadget} that can exist in Pingball.
 * A {@code SquareBumper} is exactly an L x L sized, filled square that a ball
 * can bounce off of. Because a {@code SquareBumper} is a bumper type {@code
 * Gadget}, there is no action directly associated with it.
 * @author Erik Nguyen
 *
 */
public class SquareBumper extends Gadget {

    /* Bumper follows rep invariant of Gadget
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
     * Stores the corners of the square bumper as 0 width radius points
     */
    private final Circle[] corners;

    //the actual corners
    private final Circle northEastCorner;

    private final Circle northWestCorner;

    private final Circle southWestCorner;

    private final Circle southEastCorner;

    /**
     * Stores the sides of the board
     */
    private final LineSegment[] sides;

    //the actual sides
    private final LineSegment top;

    private final LineSegment bottom;

    private final LineSegment right;

    private final LineSegment left;

    /**
     * Creates a square shaped bumper with the given parameters and with a
     * reflection coefficient of 1.0
     * @param board the board that this bumper is in
     * @param name the name of the bumper
     * @param x the x coordinate of the upper left corner of this bumper,
     *      0 <= x < 20
     * @param y the y coordinate of the upper left corner of this bumper,
     *      0 <= y < 20
     */
    public SquareBumper(Board board, String name, int x, int y) {
        super(board, name, x, y);
        this.width = 1;
        this.height = 1;

        //corner ordinal values
        final int x1 = x;
        final int x2 = x + width;
        final int y1 = y;
        final int y2 = y + height;

        northWestCorner = new Circle(x1, y1, 0);
        northEastCorner = new Circle(x2, y1, 0);
        southEastCorner = new Circle(x2, y2, 0);
        southWestCorner = new Circle(x1, y2, 0);
        this.corners = new Circle[] {northWestCorner, northEastCorner,
                                     southWestCorner, southEastCorner
                                    };

        top =    new LineSegment(x1, y1, x2, y1);
        bottom = new LineSegment(x1, y2, x2, y2);
        left =   new LineSegment(x1, y1, x1, y2);
        right =  new LineSegment(x2, y1, x2, y2);
        this.sides = new LineSegment[] {top, right, bottom, left};

        checkRep();
    }

    /**
     * Bumper does not have an associated action
     */
    @Override public void startAction() {
        //No action is associated with square bumpers so this does nothing
    }

    /**
     * Bumper does not have an associated action
     */
    @Override public void action(double dt) {
        //No action is associated with square bumpers so this does nothing
    }

    @Override public String stateDescription() {
        StringBuilder state = new StringBuilder();
        //The type of gadget this is along with its name and location
        state.append(String.format("SquareBumper(name=%s, x=%d, y=%d, refCoeff=%.2f)",
                                   name, x, y, reflectionCoefficient));
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

    @Override public String toString() {
        return "#";
    }

    @Override
    void handleCollision(Ball ball) {
        Vect pos = ball.getCenterAsVect();
        double bX = pos.x(), bY = pos.y();

        int x1 = x, x2 = x + width;
        int y1 = y, y2 = y + height;

        if(bX < x1 && bY < y1) { //north-east corner
            handleCollision(northEastCorner, ball);
        } else if(bX > x2 && bY < y1) { //north-west corner
            handleCollision(northWestCorner, ball);
        } else if(bX < x1 && bY > y2) { //south-west corner
            handleCollision(southWestCorner, ball);
        } else if(bX > x2 && bY > y2) { //south-east corner
            handleCollision(southEastCorner, ball);
        } else if(bX < x1) { // left side
            handleCollision(left, ball);
        } else if(bX > x2) { // right side
            handleCollision(right, ball);
        } else if(bY < y1) { // top
            handleCollision(top, ball);
        } else if(bY > y2) { // bottom
            handleCollision(bottom, ball);
        }

        checkRep();
    }

    @Override double calculateCollisionTimeWithBall(Ball ball) {
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
        board[vOffset + y][hOffset + x] = '#';
    }
}
