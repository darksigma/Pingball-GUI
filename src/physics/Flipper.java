package physics;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * <p>A {@code Flipper} is a movable {@code Gadget} that can hit a ball. A {@code
 * Flipper} is designed to fit in a 2L x 2L bounding box regardless of how it
 * is rotating back and forth. The shape of a {@code Flipper} is generally
 * rectangular and can be bounded by a 0.5L x 2L rectangle. More specifically
 * a flipper can be thought of as a long rectangle with ends rounded as semi-
 * circles. This equates to two semicirlcs of radius 0.25L on the ends of a
 * .5L x 1.5L box.</p>
 *
 * <p>The flipper comes in two varieties, a left flipper and a right flipper. The
 * primary difference between these two is the initial direction in which it
 * rotates. Left flippers begin rotation in the counter-clockwise direction,
 * while right flippers being rotation in the clockwise direction. When
 * triggered, a flipper will rotate in the direction specified by 90 degrees
 * at a rate of 1080 degrees per second. When it is triggered again, the flipper
 * will rotate in the opposite direction by 90 degrees again at 1080 degrees
 * per second. Because flippers rotate back and forth, the primary distinction
 * between a right and left flipper is ONLY it's original orientation and as
 * such a left flipper can be converted into a right flipper through one
 * orientation rotation and a trigger event. </p>
 *
 * <p>A flipper by default has a coefficient of reflection of 0.95, however it is
 * important to keep in mind that, similar to the real world, due to the
 * angular momentum of the flipper, a ball that is hit by a rotating flipper
 * can easily fly away with a higher kinetic energy than it started with. </p>
 *
 * <p>As a side note, the pivot point in a left flipper at orientation 0 is in the
 * upper left corner of the bounding box, while the pivot point in a right
 * flipper at orientation 0 is in the upper right corner of the bounding box. </p>
 * @author Erik
 *
 */
public class Flipper extends Gadget {

    /* Flipper rep invariant follows gadget rep invariant and extends upon it
     * such that it's angle must be only defined on the interval [0, 360)
     *
     * Gadget Rep Invariant: The bounding box of a gadget must allow it to
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
     * Speed at which a flipper rotates in units Degrees / sec
     */
    public static final int ROTATION_SPEED = 1080;

    /**
     * The original orientation of this Flipper
     */
    private final Orientation orientation;

    /**
     * Is this flipper a left or right flipper
     */
    private final boolean isLeft;

    /**
     * In the 2L x 2L bounding box that contains the flipper, is the flipper's
     * pivot on the bottom of the box. Remember, the bounding box has it's
     * upper left corner at (x, y)
     */
    private final boolean pivotOnBottom;

    /**
     * In the 2L x 2L bounding box that contains the flipper, is the flipper's
     * pivot on the right side of the box.Remember, the bounding box has it's
     * upper left corner at (x, y)
     */
    private final boolean pivotOnRight;

    /**
     * the horizontal location of the pivot in units of L
     */
    private final double pivotX;

    /**
     * The vertical location of the pivot in units of L
     */
    private final double pivotY;

    /**
     * Stores the location of the pivot in coordinate form
     */
    private final Vect pivotLoc;

    /**
     * The thickness of the pivot, which is also 2 times the radius of the
     * semicircular ends of the pivot in units of L
     */
    private final double pivotThickness;

    /**
     * Stores the distance between the centers of the semicircular halves of
     * the pivot in units of L
     */
    private final double lengthBetweenEnds;

    /**
     * Tells the flipper if it needs to rotate clockwise or counter-clockwise
     * next. True corresponds to clockwise, false to counter-clockwise
     */
    private boolean rotateClockwise;

    /**
     * Stores if the flipper is rotating;
     */
    private boolean isRotating;

    /**
     * The angle of the flipper from the origin in a range of [0, 360) degrees
     */
    private double flipperAngle;

    /**
     * The minimum angle that is defined for this flipper in range of [0, 360)
     * keep in mind that 0 = 360 in degrees
     */
    private final double minAngle;

    /**
     * The maximum angle that is defined for this flipper in range of [0, 360)
     * keep in mind that 0 = 360 in degrees
     */
    private final double maxAngle;

    /**
     * Creates a {@code Flipper} with coefficient of reflection set to 0.95 and with
     * the desired parameters. A {@code Flipper} by default has a 2L x 2L bounding
     * box.
     * @param board the board that this flipper is in
     * @param name the name of this {@code Flipper} gadget
     * @param name the name of the bumper
     * @param x the x coordinate of the upper left corner of this bumper,
     *      0 <= x < 20
     * @param y the y coordinate of the upper left corner of this bumper,
     *      0 <= y < 20
     * @param orientation the orientation clockwise in degrees from the
     *  horizontal that this {@code Flipper} is initialized in.
     * @param isLeftFlipper determines if this flipper is a left or right flipper
     */
    public Flipper(Board board, String name, int x, int y,
                   Orientation orientation, boolean isLeftFlipper) {
        super(board, name, x, y, 0.95);

        this.width = 2;
        this.height = 2;
        this.orientation = orientation;
        this.isLeft = isLeftFlipper;

        this.lengthBetweenEnds = 1.5; //units of L
        this.pivotThickness = 0.5; //units of L

        //right flipper moves clockwise first, left moves counter clockwise
        this.rotateClockwise = !isLeft;
        this.isRotating = false;

        Orientation pivotOrientation = orientation;
        // the difference between a left and right flipper is a rotation
        if(!isLeftFlipper) {
            pivotOrientation = pivotOrientation.rotateClockwise();
        }

        //Define the pivot orientation such that 0 = NW corner, 90 = NE corner, etc.

        final double radius = pivotThickness / 2;

        switch(pivotOrientation) {
        case _0:
            pivotOnBottom = false;
            pivotOnRight = false;
            minAngle = Orientation._0.angle();
            maxAngle = Orientation._90.angle();
            //(.25, .25) interior from NW corner
            pivotX = x + radius;
            pivotY = y + radius;
            break;
        case _90:
            pivotOnBottom = false;
            pivotOnRight = true;
            minAngle = Orientation._90.angle();
            maxAngle = Orientation._180.angle();
            //(.25, .25) interior from NE corner
            pivotX = x + width - radius;
            pivotY = y + radius;
            break;
        case _180:
            pivotOnBottom = true;
            pivotOnRight = true;
            minAngle = Orientation._180.angle();
            maxAngle = Orientation._270.angle();
            //(.25, .25) interior from SE corner
            pivotX = x + width - radius;
            pivotY = y + height - radius;
            break;
        default:    //270
            pivotOnBottom = true;
            pivotOnRight = false;
            minAngle = Orientation._270.angle();
            maxAngle = Orientation._0.angle();
            //(.25, .25) interior from SW corner
            pivotX = x + radius;
            pivotY = y + height - radius;
            break;
        }

        //Set up the initial angle of this flipper, left flipper starts by going
        //counter clockwise, so the initial angle should be the max angle
        flipperAngle = isLeftFlipper ? maxAngle : minAngle;

        this.pivotLoc = new Vect(pivotX, pivotY);

        checkRep();
    }

    /**
     * Sets up and begins the action sequence associated with this
     * {@code Flipper} and adds it to the board active list. If the flipper is
     * already rotating, it makes it start rotating in the other direction
     */
    @Override public void startAction() {
        //if not moving, add to active list
        if(!isRotating) {
            isRotating = true;
            gameBoard.addToActiveList(this);
        } else {//if already moving, just switch direction of motion
            rotateClockwise = !rotateClockwise;
        }
        checkRep();
    }

    /**
     * Rotates this flipper for a small amount of time, dt
     * @param the amount of time to rotate this flipper
     */
    @Override public void action(double dt) {
        final double degreesToRotate = dt * Flipper.ROTATION_SPEED;
        // rotating so fast that it hits the other side in one update
        if(degreesToRotate >= 90) {
            flipperAngle = rotateClockwise ? maxAngle : minAngle;
            //set up the gadget for the next movement and deactivate it
            rotateClockwise = !rotateClockwise;
            isRotating = false;
            gameBoard.addToDeactivatedList(this);
        } else {//rotating at < 90
            if(rotateClockwise) {
                flipperAngle = (flipperAngle + degreesToRotate) % 360;
                if(flipperAngle >= maxAngle) { //overshoot
                    flipperAngle = maxAngle;
                    //set up the gadget for the next movement and deactivate it
                    rotateClockwise = !rotateClockwise;
                    isRotating = false;
                    gameBoard.addToDeactivatedList(this);
                }
            } else {//rotating counterclockwise
                flipperAngle = (flipperAngle - degreesToRotate + 360) % 360;
                if(flipperAngle <= minAngle) { //overshoot
                    flipperAngle = minAngle;
                    //set up the gadget for the next movement and deactivate it
                    rotateClockwise = !rotateClockwise;
                    isRotating = false;
                    gameBoard.addToDeactivatedList(this);
                }
            }
        }

        checkRep();

    }

    /**
     * Returns a {@code String} that describes the state of the {@code Flipper}
     * including the {@code Flipper}'s name, type, and location. Other critical
     * properties is present such as orientation, connected {@code Gadget}s,
     * current angle from the horizontal, and whether or not it is moving.
     * @return A string representation of the state of this Gadget
     */
    @Override public String stateDescription() {
        StringBuilder state = new StringBuilder();
        //The type of gadget this is along with its name and location
        state.append(String.format(
                         "%sFlipper(name=%s, x=%d, y=%d, angle=%f, refCoeff=%.2f)",
                         isLeft? "Left" : "Right", name, x, y, flipperAngle, reflectionCoefficient));
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

    /**
     * Returns the current angle of this flipper as measured from the horizontal
     * going clockwise
     * @return the current angle of this flipper as a double
     */
    public double getAngle() {
        return flipperAngle;
    }

    /**
     * Returns the orientation that this flipper is in
     * @return the orientation that this flipper is in
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Returns if this flipper is a left flipper
     * @return true if this flipper is a left flipper, else false
     */
    public boolean isLeftFlipper() {
        return isLeft;
    }

    @Override public void trigger(Ball ball) {
        super.trigger(ball);
    }

    @Override void handleCollision(Ball ball) {
        final double radius = pivotThickness / 2;
        final double theta = Math.toRadians(flipperAngle);
        final double angularVelocity = Math.toRadians(1080) * (rotateClockwise ? 1 : -1);

        //Create a linear transformer to rotate the default points to where the flipper actually is
        AffineTransform transformer = AffineTransform.getRotateInstance(theta, pivotX, pivotY);

        //center of the other semicircular part of the flipper
        final double otherX = pivotX + lengthBetweenEnds;
        final double otherY = pivotY;

        //one of the end points of the lower line segment of the flipper at
        //orientation 0
        final double x11 = pivotX;
        final double y11 = pivotY + radius;

        //other end point of the lower line segment of the flipper at orientation 0
        final double x12 = x11 + lengthBetweenEnds;
        final double y12 = y11;

        //one of the end points of the upper line segment of the flipper at orientation 0
        final double x21 = pivotX ;
        final double y21 = pivotY - radius;

        //other end of the upper line segment of the flipper at orientation 0
        final double x22 = x21 + lengthBetweenEnds;
        final double y22 = y21;

        //not pivot semicirle
        Point2D.Double otherPoint = new Point2D.Double(otherX, otherY);
        //lower line segment
        Point2D.Double p11 = new Point2D.Double(x11, y11);
        Point2D.Double p12 = new Point2D.Double(x12, y12);
        //upper line segment
        Point2D.Double p21 = new Point2D.Double(x21, y21);
        Point2D.Double p22 = new Point2D.Double(x22, y22);

        //rotate and store all of the points in their point instances
        transformer.transform(otherPoint, otherPoint);
        transformer.transform(p11, p11);
        transformer.transform(p12, p12);
        transformer.transform(p21, p21);
        transformer.transform(p22, p22);

        //create the semicircle around the pivot and the semicircle at the other end
        final Circle pivot = new Circle(pivotX, pivotY, radius);
        final Circle other = new Circle(otherPoint.x, otherPoint.y, radius);
        //The ball as a circle and ball data
        final Circle ballCirc = ball.asCircle();
        final Vect ballVel = ball.getVelocityVector();
        final Vect ballPos = ball.getCenterAsVect();

        //the original upper and lower sides of the pivot, and the segment
        //between circle centers respectively
        final LineSegment lowerSeg = new LineSegment(p11.x, p11.y, p12.x, p12.y);
        final LineSegment upperSeg = new LineSegment(p21.x, p21.y, p22.x, p22.y);

        //try to project the ball onto the line segments
        final Vect upperProjection = Geometry.perpendicularPoint(upperSeg, ballPos);
        final Vect lowerProjection = Geometry.perpendicularPoint(lowerSeg, ballPos);

        //could also use lowerProjection, if one isn't null the other shouldn't be null
        if(upperProjection != null) { // ball hit one of the line segments
            //Take segment 1 if closer
            LineSegment segmentToUse = Geometry.distanceSquared(upperProjection, ballPos) <
                                       Geometry.distanceSquared(lowerProjection, ballPos) ? upperSeg : lowerSeg;
            Vect newVelocity = Geometry.reflectRotatingWall(segmentToUse,
                               pivotLoc, angularVelocity, ballCirc, ballVel, reflectionCoefficient);
            //store it
            ball.setVelocity(newVelocity);
        } else { // hit one of the semi circles
            //find the distances to the centers of the semi circles
            double distToPivot = Geometry.distanceSquared(pivotLoc, ballPos);
            double distToOther = Geometry.distanceSquared(other.getCenter(), ballPos);
            if(distToPivot < distToOther) { //hit the stationary pivot
                handleCollision(pivot, ball);
            } else {
                Vect newVelocity = Geometry.reflectRotatingCircle(other,
                                   pivotLoc, angularVelocity, ballCirc, ballVel,
                                   reflectionCoefficient);
                ball.setVelocity(newVelocity);
            }
        }

        //trigger this gadget
        trigger(ball);

        checkRep();
    }

    public boolean isMoving() {
        return isRotating;
    }

    @Override double calculateCollisionTimeWithBall(Ball ball) {
        double time = Double.POSITIVE_INFINITY;
        final double radius = pivotThickness / 2;
        final double theta = Math.toRadians(flipperAngle);

        //Create a linear transformer to rotate the default points to where the flipper actually is
        AffineTransform transformer = AffineTransform.getRotateInstance(theta, pivotX, pivotY);

        //center of the other semicircular part of the flipper
        final double otherX = pivotX + lengthBetweenEnds;
        final double otherY = pivotY;

        //one of the end points of the lower line segment of the flipper at
        //orientation 0
        final double x11 = pivotX;
        final double y11 = pivotY + radius;

        //other end point of the lower line segment of the flipper at orientation 0
        final double x12 = x11 + lengthBetweenEnds;
        final double y12 = y11;

        //one of the end points of the upper line segment of the flipper at orientation 0
        final double x21 = pivotX ;
        final double y21 = pivotY - radius;

        //other end of the upper line segment of the flipper at orientation 0
        final double x22 = x21 + lengthBetweenEnds;
        final double y22 = y21;

        //not pivot semicirle
        Point2D.Double otherPoint = new Point2D.Double(otherX, otherY);
        //lower line segment
        Point2D.Double p11 = new Point2D.Double(x11, y11);
        Point2D.Double p12 = new Point2D.Double(x12, y12);
        //upper line segment
        Point2D.Double p21 = new Point2D.Double(x21, y21);
        Point2D.Double p22 = new Point2D.Double(x22, y22);

        //rotate and store all of the points in their point instances to reflect
        //the true state of the flipper
        transformer.transform(otherPoint, otherPoint);
        transformer.transform(p11, p11);
        transformer.transform(p12, p12);
        transformer.transform(p21, p21);
        transformer.transform(p22, p22);

        //create the semicircle around the pivot and the semicircle at the other end
        final Circle pivot = new Circle(pivotX, pivotY, radius);
        final Circle other = new Circle(otherPoint.x, otherPoint.y, radius);

        //the original upper and lower sides of the pivot
        final LineSegment lowerSeg = new LineSegment(p11.x, p11.y, p12.x, p12.y);
        final LineSegment upperSeg = new LineSegment(p21.x, p21.y, p22.x, p22.y);

        //The ball as a circle and the velocity of the ball
        final Circle ballCirc = ball.asCircle();
        final Vect ballVel = ball.getVelocityVector();
        final double angularVelocity = Math.toRadians(1080) * (rotateClockwise ? 1 : -1);

        //The pivot doesn't actually move
        double collisionTime = Geometry.timeUntilCircleCollision(pivot,
                               ballCirc, ballVel);
        time = Math.min(time, collisionTime);

        if(isRotating) { //flipper is moving around very quickly
            /*TODO: technically, not completely accurate because flipper may
            /*stop moving before displayed time
             */

            //the other circular part
            collisionTime = Geometry.timeUntilRotatingCircleCollision(other,
                            pivotLoc, angularVelocity, ballCirc, ballVel);
            time = Math.min(time, collisionTime);
            //first line segment
            collisionTime = Geometry.timeUntilRotatingWallCollision(lowerSeg,
                            pivotLoc, angularVelocity, ballCirc, ballVel);
            time = Math.min(time, collisionTime);
            //second line segment
            collisionTime = Geometry.timeUntilRotatingWallCollision(upperSeg,
                            pivotLoc, angularVelocity, ballCirc, ballVel);
            time = Math.min(time, collisionTime);
        } else {//flipper looks like a wall almost
            //collision with other circular part
            collisionTime = Geometry.timeUntilCircleCollision(other,
                            ballCirc, ballVel);
            time = Math.min(time, collisionTime);

            //collision with line segments
            collisionTime = Geometry.timeUntilWallCollision(lowerSeg,
                            ballCirc, ballVel);
            time = Math.min(time, collisionTime);

            collisionTime = Geometry.timeUntilWallCollision(upperSeg,
                            ballCirc, ballVel);
            time = Math.min(time, collisionTime);
        }
        return time;
    }

    @Override public void putInDisplay(char[][] board, int hOffset, int vOffset) {
        double assumedAngle = flipperAngle;
        if(isRotating) {
            // if the flipper is moving about, just auto assume it's done moving
            assumedAngle = rotateClockwise ? maxAngle : minAngle;
        }

        //assumedAngle ~= one of these values.
        double diffFromMin = Math.abs(minAngle - assumedAngle);
        double diffFromMax = Math.abs(maxAngle - assumedAngle);

        if(!pivotOnRight && !pivotOnBottom) { // pivot in NW corner
            if(diffFromMin < diffFromMax) {
                board[y + vOffset + 0][x + hOffset + 0] = '-';
                board[y + vOffset + 0][x + hOffset + 1] = '-';
                board[y + vOffset + 1][x + hOffset + 0] = ' ';
                board[y + vOffset + 1][x + hOffset + 1] = ' ';
            } else {
                board[y + vOffset + 0][x + hOffset + 0] = '|';
                board[y + vOffset + 0][x + hOffset + 1] = ' ';
                board[y + vOffset + 1][x + hOffset + 0] = '|';
                board[y + vOffset + 1][x + hOffset + 1] = ' ';
            }
        } else if (pivotOnRight && !pivotOnBottom) { // pivot in NE corner
            if(diffFromMin > diffFromMax) {
                board[y + vOffset + 0][x + hOffset + 0] = '-';
                board[y + vOffset + 0][x + hOffset + 1] = '-';
                board[y + vOffset + 1][x + hOffset + 0] = ' ';
                board[y + vOffset + 1][x + hOffset + 1] = ' ';
            } else {
                board[y + vOffset + 0][x + hOffset + 0] = ' ';
                board[y + vOffset + 0][x + hOffset + 1] = '|';
                board[y + vOffset + 1][x + hOffset + 0] = ' ';
                board[y + vOffset + 1][x + hOffset + 1] = '|';
            }
        } else if (pivotOnRight && pivotOnBottom) { // pivot in SE corner
            if(diffFromMin < diffFromMax) {
                board[y + vOffset + 0][x + hOffset + 0] = ' ';
                board[y + vOffset + 0][x + hOffset + 1] = ' ';
                board[y + vOffset + 1][x + hOffset + 0] = '-';
                board[y + vOffset + 1][x + hOffset + 1] = '-';
            } else {
                board[y + vOffset + 0][x + hOffset + 0] = ' ';
                board[y + vOffset + 0][x + hOffset + 1] = '|';
                board[y + vOffset + 1][x + hOffset + 0] = ' ';
                board[y + vOffset + 1][x + hOffset + 1] = '|';
            }
        } else { //pivot in SW corner
            if(diffFromMin > diffFromMax) {
                board[y + vOffset + 0][x + hOffset + 0] = ' ';
                board[y + vOffset + 0][x + hOffset + 1] = ' ';
                board[y + vOffset + 1][x + hOffset + 0] = '-';
                board[y + vOffset + 1][x + hOffset + 1] = '-';
            } else {
                board[y + vOffset + 0][x + hOffset + 0] = '|';
                board[y + vOffset + 0][x + hOffset + 1] = ' ';
                board[y + vOffset + 1][x + hOffset + 0] = '|';
                board[y + vOffset + 1][x + hOffset + 1] = ' ';
            }
        }
    }

    /**
     * All gadgets must follow the rep invariant of {@code Gadget}. See gadget
     * for more details. Then the rep invariant is extended such that the
     * flipper angle must be on the interval [0, 360)
     */
    @Override protected boolean checkRep() {
        assert 0 <= flipperAngle && flipperAngle < 360;
        return super.checkRep();
    }
}
