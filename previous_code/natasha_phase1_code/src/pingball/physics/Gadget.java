package pingball.physics;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * A {@code Gadget} represents anything that can be placed on a Pingball
 * board. Every {@code Gadget} has a coefficient of reflection, a trigger,
 * and an action. A 'trigger' represent events that happen to a
 * {@code Gadget} instance, while an 'action' represents an event that the
 * {@code Gadget} will perform as a response to it's 'trigger'. The
 * coefficient of reflection is a scalar multiplier that is applied to the
 * magnitude of a ball's velocity after it bounces off of a {@code Gadget}.
 * @author Erik, Shelby, Natasha
 *
 */
public abstract class Gadget {

    /* Gadget Rep Invariant: The bounding box of a gadget must allow it to
     * exist completely within the boundary of the board it is in. The name must
     * also consist of a letter or underscore followed optionally by more
     * word characters as defined by Pattern.java. Lastly, the gadget must be
     * two dimensional. This can be enumerated as follows:
     *  1) 0 <= x < 20, 0 < width, x + width <= 20
     *  2) 0 <= y < 20, 0 < height, y + height <= 20
     *  3) name matches [A-Za-z_][A-Za-z_0-9]*
     */

    /**
     * The board that the gadget is sitting in
     */
    protected final Board gameBoard;

    /**
     * List of other board objects that need to be triggered when this object
     * is triggered.
     */
    protected List<Gadget> triggers;

    /**
     * Horizontal coordinate of the upper left corner of this gadget
     */
    protected final int x;

    /**
     * Vertical coordinate of the upper left corner of this gadget
     */
    protected final int y;

    /**
     * The name of this gadget
     */
    protected final String name;

    /**
     * The horizontal dimension of this gadget in units of L
     */
    protected int width;

    /**
     * The vertical dimension of this gadget in units of L
     */
    protected int height;

    /**
     * The reflection coefficient for this gadget
     */
    protected final double reflectionCoefficient;

    /**
     * Creates a gadget with the given parameters and with a reflection
     * coefficient of 1.0
     * @param gameBoard the board that this gadget is sitting in
     * @param name the name of the gadget
     * @param x the x coordinate of the upper left corner of this gadget,
     *      0 <= x < 20
     * @param y the y coordinate of the upper left corner of this gadget
     *      0 <= y < 20;
     */
    protected Gadget(Board gameBoard, String name, int x, int y) {
        this(gameBoard, name, x, y, 1.0);
    }

    /**
     * Create a gadget with the following parameters.
     * @param gameBoard the board that this gadget is sitting in
     * @param name the name of this gadget
     * @param x the x coordinate of the upper left corner of this gadget
     * @param y the y coordinate of the upper left corner of this gadget
     * @param refCoef the reflection coefficient for this gadget
     */
    protected Gadget(Board gameBoard, String name, int x, int y, double refCoef) {
        this.gameBoard = gameBoard;
        this.name = name;
        this.x = x;
        this.y = y;
        this.reflectionCoefficient = refCoef;
        this.triggers = new ArrayList<>();
        checkRep();
    }


    /**
     * Connects the trigger of this object to the parameter object such that
     * triggering this object triggers the parameter object as well.
     * @param obj the Gadget that needs to be connected to this
     * @return true if this object successfully connected it's trigger to obj's
     */
    public final boolean connect(Gadget obj) {
        boolean out = triggers.add(obj);
        checkRep();
        return out;
    }

    /**
     * Disconnects the parameter object from this object such that when this
     * object is triggered, {@code obj} is no longer triggered. If {@code obj}
     * is not connected to
     * @param obj the Gadget that needs to be disconnected from this
     * @return true if the connection between this objects trigger and
     *  {@code obj} has been broken, false if there was never said connection
     *  to begin with.
     */
    public final boolean disconnect(Gadget obj) {
        boolean out = triggers.remove(obj);
        checkRep();
        return out;
    }

    /**
     * Sets up and begins the action sequence associated with this
     * {@code Gadget} - i.e. rotating a flipper. If no action is associated
     * with this, then it has no effect. Gadgets that have an associated action
     * sequence are also added to the board's active list.
     */
    public abstract void startAction();

    /**
     * Performs the action associated with this {@code Gadget} for the specified
     * amount of time - i.e. rotating a flipper for .02 sec. If no action is
     * associated with this, then it has no effect.
     * @param dt the time to perform this action
     */
    public abstract void action(double dt);

    /**
     * Reflects the ball off of the surface of the gadget it hits and notifies
     * this gadget that it has been hit
     * @param ball the ball that hit this gadget
     */
    abstract void handleCollision(Ball ball);

    /**
     * Calculates the amount of time until the ball hits this gadget.
     * @param ball the ball that may hit this gadget
     * @return the minimum positive time until the ball hits this gadget
     */
    abstract double calculateCollisionTimeWithBall(Ball ball);

    /**
     * Returns the horizontal coordinate of the upper left corner of
     * this {@code Gadget}
     * @return the horizontal coordinate of this {@code Gadget}
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the vertical coordinate of the upper left corner of this
     * {@code Gadget}
     * @return the vertical coordinate of this {@code Gadget}
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the name of this {@code Gadget}
     * @return the name of this {@code Gadget}
     */
    public String getName() {
        return name;
    }

    /**
     * Puts the gadget into the provided board in text format. The given board
     * may have buffers on the outer edges such that the true origin of the
     * board maybe offset towards the inside
     * @param board the board to have text put in
     * @param hOffset the horizontal offset of the board's origin
     * @param vOffset the vertical offset of the board's origin
     */
    public abstract void putInDisplay(char[][] board, int hOffset, int vOffset);

    /**
     * Returns a {@code String} that describes the state of the {@code Gadget}
     * including the {@code Gadget}'s name, type, and location. Other critical
     * properties is present such as orientation, connected {@code Gadget}s,
     * etc.
     * @return A string representation of the state of this Gadget
     */
    public abstract String stateDescription();

    /**
     * Handles bouncing a ball off of one of the circular components of the gadget
     * @param circle the circular component to be bounced off of
     * @param ball the ball the needs to be reflected
     */
    protected void handleCollision(Circle circle, Ball ball) {
        //get the reflection velocity
        Vect newVelocity = Geometry.reflectCircle(circle.getCenter(),
                           ball.getCenterAsVect(), ball.getVelocityVector(),
                           reflectionCoefficient);
        //store it
        ball.setVelocity(newVelocity);
        //trigger this gadget
        trigger(ball);
        checkRep();
    }

    /**
     * Handles bouncing a ball off of one of the sides
     * @param side the side to bounce the ball off of
     * @param ball the ball that needs to be reflected
     */
    protected void handleCollision(LineSegment side, Ball ball) {
        Vect newVelocity = Geometry.reflectWall(side, ball.getVelocityVector(),
                                                reflectionCoefficient);
        //store it
        ball.setVelocity(newVelocity);
        //trigger this gadget
        trigger(ball);
        checkRep();
    }

    /**
     * Returns the coefficient of reflection of this {@code Gadget}
     *
     * <p>Values of 1.0 represents perfectly elastic collisions. Values less
     * than 1.0 represents a damping coefficient for the ball which decreases
     * the ball's speed and kinetic energy. Values greater than 1.0 represent
     * increases in speed and kinetic energy.</p>
     *
     * @return the coefficient of reflection for this bumper
     */
    public double getReflectionCoeff() {
        return reflectionCoefficient;
    }

    /**
     * Returns the width of this {@code Gadget} in units of L
     * @return the width of this
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of this {@code Gadget} in units of L
     * @return the height of this
     */
    public int getHeight() {
        return height;
    }

    /**
     * Trigger this {@code Gadget} and all other {@code Gadget} objects
     * connected to this instance. This effectively begins whatever action
     * sequences are associated with triggering a {@code Gadget}. All {@code
     * Gadget} objects can be triggered by a ball.
     * @param ball the ball that hit and triggered this {@code Gadget}
     */
    public void trigger(Ball ball) {
        this.startAction();
        for(Gadget gadget : triggers) {
            gadget.startAction();
        }
        checkRep();
    }

    /* Gadget Rep Invariant: The bounding box of a gadget must allow it to
     * exist completely within the boundary of the board it is in. The name must
     * also consist of a letter or underscore followed optionally by more
     * word characters as defined by Pattern.java. Lastly, the gadget must be
     * two dimensional. This can be enumerated as follows:
     *  1) 0 <= x + width <= 20
     *  2) 0 <= y + height <= 20
     *  3) name matches [A-Za-z_][A-Za-z_0-9]*
     *  4) 0 < width, height
     */

    /**
     * All gadgets must follow this rep invariant. All gadgets must have their
     * bounding box contained in the 20L by 20L board.
     * @return true if the gadget follows this rep invariant.
     */
    protected boolean checkRep() {
        assert x > -1 && y > -1 && x + width <= 20 && y + height <= 20;
        assert width > 0;
        assert height > 0;
        assert name.matches("[A-Za-z_][A-Za-z_0-9]*");
        return true;
    }
}
