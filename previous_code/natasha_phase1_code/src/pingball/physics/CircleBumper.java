package pingball.physics;

import java.awt.geom.Point2D;

import physics.Circle;
import physics.Geometry;
import physics.Vect;

/**
 * {@code CircleBumper} is a type of {@code Gadget} that can exist in Pingball.
 * A {@code CircleBumper} is by default a filled circle of radius L/2 that a
 * ball can bounce off of. This allows the {@code Gadget} to fit completely in
 * an L x L square. Because a {@code CircleBumper} is a bumper type {@code
 * Gadget}, there is no action directly associated with it.
 * @author Erik Nguyen
 *
 */
public class CircleBumper extends Gadget {

    /* Bumper rep invariant follows gadget rep invariant. note
     * radius = width / 2 = height / 2
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
     * Stores the radius in L of this bumper
     */
    private final double radius;

    /**
     * Stores the center point of this bumper
     */
    private final Point2D.Double center;

    /**
     * Creates a circle shaped bumper based on the following parameters.
     * @param board the board that this bumper is in
     * @param name the name of this gadget
     * @param x the x coordinate of the upper left corner of this bumper,
     *      0 <= x < 20
     * @param y the y coordinate of the upper left corner of this bumper,
     *      0 <= y < 20
     */
    public CircleBumper(Board board, String name, int x, int y) {
        super(board, name, x, y);
        this.width = 1;
        this.height = 1;
        this.radius = width / 2.;
        this.center = new Point2D.Double(x + radius, y + radius);
        checkRep();
    }

    /**
     * Bumper does not have an associated action
     */
    @Override public void startAction() {
        // CircleBumpers don't have any associated action
    }

    /**
     * Bumper does not have an associated action
     */
    @Override public void action(double dt) {
        // CircleBumpers don't have any associated action
    }

    @Override public String stateDescription() {
        StringBuilder state = new StringBuilder();
        //The type of gadget this is along with its name and location
        state.append(String.format("CircleBumper(name=%s, x=%d, y=%d, refCoeff=%.2f)",
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

    /**
     * Returns the radius of this circular bumper as a double
     * @return the radius of this circular bumper
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns the actual coordinates of the center of this circular bumper
     * @return the center coordinates of this bumper
     */
    public Vect getCenter() {
        return new Vect(center.x, center.y);
    }

    @Override void handleCollision(Ball ball) {
        handleCollision(new Circle(center, radius), ball);
    }

    @Override double calculateCollisionTimeWithBall(Ball ball) {
        Circle circle = new Circle(center, radius);
        return Geometry.timeUntilCircleCollision(circle, ball.asCircle(),
                ball.getVelocityVector());
    }

    @Override public void putInDisplay(char[][] board, int hOffset, int vOffset) {
        board[vOffset + y][hOffset + x] = 'O';
    }

    @Override public String toString() {
        return "O";
    }
}
