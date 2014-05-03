package physics;

import java.awt.geom.Point2D;

import physics.Circle;
import physics.Vect;

/**
 * A {@code Ball} is a computerized model of a physical, impenetrable ball that
 * will bounce around in a pingball game (a computerized version of pinball).
 * The ball moves around freely, experiencing both gravity and friction on
 * the {@code Board} that it lies in and bounces around hitting different
 * {@code Gadget}s.
 *
 * @author enguyen, nconsul, spefley
 *
 */

public class Ball {

    /*
     * Rep invariant: The name of the ball must be a letter or underscore
     * followed by one or more optional word characters as described by
     * Pattern.java. Furthermore, the center of a ball must be within the
     * field of play (the 20L x 20L grid) and can only have a value outside
     * of this if it is being passed to another board.
     *
     * 1) xCenter and yCenter are on interval [0, 20] unless being passed
     *      outside of the board
     * 2) name matches [A-Za-z][A-Za-z_0-9]*
     */

    /**
     * Defines the default radius of a ball in units of L
     */
    public static final double DEFAULT_DIAMETER = 0.5;

    /**
     * The name of this ball
     */
    private final String name;

    /**
     * The horizontal velocity of this ball
     */
    private double vX;

    /**
     * The vertical velocity of this ball
     */
    private double vY;

    /**
     * The horizontal position of this ball
     */
    private double x;

    /**
     * The vertical position of this ball
     */
    private double y;

    /**
     * The center of this ball
     */
    private Point2D.Double center;

    /**
     * The radius of this ball in units of L
     */
    private final double radius;

    /**
     * The gravity that this ball experiences in units of L/sec^2
     */
    private final double gravity;

    /**
     * The constant of friction in 1/sec that this ball will experience
     */
    private final double mu;

    /**
     * The constant of friction in 1/L that this ball will experience
     */
    private final double mu2;

    /**
     * Stores whether or not this ball was captured by an absorber
     */
    private boolean isCaptured;

    /**
     * Stores if this ball is being passed outside
     */
    private boolean beingPassed = false;

////////////////////////////////CONSTRUCTORS//////////////////////////////////

    /**
     * Creates a ball with a given initial velocity in the x and y directions
     * at the desired starting point (x, y) with a default diameter of 0.5L.
     * @param velX represents the initial velocity in the x-direction (double)
     * @param velY represents the initial velocity in the y-direction (double)
     * @param x represents the initial position in the x-direction (double)
     * @param y represents the initial position in the y-direction (double)
     * @param g represents the gravitational acceleration that this ball will
     *     experience in units of L/sec^2 (double)
     * @param mu represents the constant for friction that this ball will
     *     experience in units of 1/sec
     * @param mu2 represents the constant for friction that this ball will
     *     experience in units of 1/L
     */
    public Ball(String name, double velX, double velY, double x, double y, double g, double mu, double mu2) {
        this(name, velX, velY, x, y, DEFAULT_DIAMETER, g, mu, mu2);
    }

    /**
     * Creates a ball with a given initial velocity in the x and y directions
     * at the desired starting point (x, y) with the desired radius in units
     * of L
     * @param velX represents the initial velocity in the x-direction (double)
     * @param velY represents the initial velocity in the y-direction (double)
     * @param x represents the initial position in the x-direction (double)
     * @param y represents the initial position in the y-direction (double)
     * @param d represents the diameter of this ball in units of L (double)
     * @param g represents the gravitational acceleration that this ball will
     *     experience in units of L/sec^2 (double)
     * @param mu represents the constant for friction that this ball will
     *     experience in units of 1/sec
     * @param mu2 represents the constant for friction that this ball will
     *     experience in units of 1/L
     */
    public Ball(String name, double velX, double velY, double x, double y, double d, double g, double mu, double mu2) {
        this.name = name;
        setVelocity(new Vect(velX, velY));
        this.x = x;
        this.y = y;
        this.radius = d / 2;
        this.gravity = g;
        this.mu = mu;
        this.mu2 = mu2;
        this.center = new Point2D.Double(x + radius, y + radius);
        checkRep();
    }

//////////////////////////////////MUTATORS////////////////////////////////////

    /**
     * Move this ball forward by a time dt;
     * @param dt the amount of time that this ball is moving forward, 0 < dt
     */
    public void update(double dt) {
        //move the ball
        x = x + dt * vX;
        y = y + dt * vY;

        //scale velocities due to friction
        double newVY = vY + gravity * dt;
        double newVX = vX * (1 - mu * dt - mu2 * Math.abs(vX) * dt);
        newVY = newVY * (1 - mu * dt - mu2 * Math.abs(newVY) * dt);

        setVelocity(new Vect(newVX, newVY));

        this.center = new Point2D.Double(x + radius, y + radius);
        checkRep();
    }

    /**
     * Tells the ball it has just been captured and set it's velocity to 0
     */
    public void becomeCaptured() {
        isCaptured = true;
        vX = 0;
        vY = 0;
        checkRep();
    }

    /**
     * Tells the ball that it is about to be passed to the server
     */
    public void setBeingPassedFlagTrue() {
        beingPassed = true;
        checkRep();
    }

    /**
     * Tells the ball that it has been released from capture
     */
    public void becomeReleased() {
        isCaptured = false;
        checkRep();
    }

    /**
     * Sets the center of the ball to the new position
     * @param newPosition
     */
    void setCenter(Vect newPosition) {
        x = newPosition.x() - radius;
        y = newPosition.y() - radius;
        center = new Point2D.Double(newPosition.x(), newPosition.y());
        checkRep();
    }

    /**
     * Sets the upper left corner of the bounding box of this ball to
     * the new specified position
     * @param newPosition the new position of the ball
     */
    void setPosition(Vect newPosition) {
        x = newPosition.x();
        y = newPosition.y();
        center = new Point2D.Double(x + radius, y + radius);
        checkRep();
    }

    /**
     * Sets the velocity of this ball to the new specified velocity
     * @param newVelocity the new velocity for this ball
     */
    void setVelocity(Vect newVelocity) {
        //vX = Math.min(200, newVelocity.x());
        //vY = Math.min(200, newVelocity.y());
        vX = newVelocity.x();
        vY = newVelocity.y();
        checkRep();
    }

//////////////////////////////////OBSERVERS///////////////////////////////////

    /**
     * Puts the ball into the provided board in text format. The given board
     * may have buffers on the outer edges such that the true origin of the
     * board maybe offset towards the inside
     * @param board the board to have text put in
     * @param hOffset the horizontal offset of the board's origin
     * @param vOffset the vertical offset of the board's origin
     */
    public void putInDisplay(char[][] board, int hOffset, int vOffset) {

        int xApprox = (int) Math.floor(center.x + hOffset);
        int yApprox = (int) Math.floor(center.y + vOffset);

        //put the ball in the board if there isn't something like another ball
        //or a gadget already there
        if(board[yApprox][xApprox] == ' ') {
            board[yApprox][xApprox] = '*';
        }
    }

    /**
     * Returns the name of this ball.
     * @return the name of this ball
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current velocity of the ball.
     * @return velocity of the ball, represented as a Vect
     */
    public Vect getVelocityVector() {
        return new Vect(vX, vY);
    }

    /**
     * Returns the current position of the center of the ball.
     * @return
     */
    public Vect getPositionVector() {
        return new Vect(x, y);
    }

    public Circle asCircle() {
        return new Circle(center, radius);
    }

    /**
     * Returns the radius of this ball in units of L
     * @return the radius of this ball
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns the center of this ball as a Point2D.Double coordinate
     * @return the coordinate of the center of this ball
     */
    public Point2D.Double getCenter() {
        return new Point2D.Double(center.x, center.y);
    }

    /**
     * Returns the center of this ball as a vector <x, y>
     * @return the vector form of the center of this ball
     */
    public Vect getCenterAsVect() {
        return new Vect(center.x, center.y);
    }

    /**
     * Returns the gravity that this ball is experiencing in units of L/sec^2
     * @return the gravity that this ball is experiencing
     */
    public double getGravity() {
        return gravity;
    }

    /**
     * Returns the constant of friction that this ball is experiencing in units
     * of 1/sec
     * @return the constant of friction for this ball in units 1/sec
     */
    public double getMu() {
        return mu;
    }

    /**
     * Returns the constant of friction that this ball is experiencing in units
     * of 1/L
     * @return the constant of friction for this ball in units 1/L
     */
    public double getMu2() {
        return mu2;
    }

    /**
     * Returns if this ball is captured by an absorber
     * @return true if this ball is currently being held by an absorber
     */
    public boolean isCaptured() {
        return isCaptured;
    }

    /**
     * Returns the String representation of the ball, represented as "*"
     * @return "*"
     */
    public String toString() {
        return "*";
    }

    /**
     * Checks if the ball satisfies the rep invariant listed above.
     * @return true if the ball satisfies the rep invariant
     */
    private boolean checkRep() {
        if(!this.beingPassed) {
            assert this.center.x >= 0 && this.center.x <= 20;
            assert this.center.y >= 0 && this.center.y <= 20;
        }
        assert name.matches("[A-Za-z][A-Za-z_0-9]*");
        return true;
    }
}
