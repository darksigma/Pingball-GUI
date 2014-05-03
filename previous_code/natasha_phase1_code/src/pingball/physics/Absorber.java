package pingball.physics;

import java.util.ArrayDeque;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * An {@code Absorber} is a type of Gadget that has a variable size. An {@code
 * Absorber} can be of any size kL * mL where 1 <= m,k <= 20. The dimensions
 * of an {@code Absorber} can never force the absorber to exist outside of the
 * playing field of a pingball board. Also {@code Absorber}s have a unique
 * trigger and action sequence. When an {@code Absorber} is hit by a ball, the
 * ball is immediately captured and put in the {@code Absorber}'s bottom right
 * corner and stored. Being hit by a ball is also the {@code Absorber}'s
 * trigger, which causes it to fire the oldest stored ball straight upwards
 * at 50 L/s. If an {@code Absorber} no longer is holding a ball or if the
 * previously ejected ball has not yet left the {@code Absorber}, no action is
 * taken. An {@code Absorber} can self-trigger itself (potentially allowing a
 * ball to be absorbed and fired in a fluid sequence of events).
 * @author Erik
 *
 */
public class Absorber extends Gadget {

    /* Absorber rep invariant follows gadget rep invariant
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
     * The velocity at which to eject a ball from the absorber
     */
    private final Vect ballEjectionVelocity;

    /**
     * A queue of balls that this {@code Absorber} is currently holding inside
     * of it.
     */
    private final ArrayDeque<Ball> balls;

    /**
     * The ball that is being fired out of the Absorber if any. Set to null if
     * no such ball exists.
     */
    private Ball firedBall;

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
     * Create a new {@code Absorber} with the specified parameters.
     * @param board the board that the absorber is in
     * @param name the name of this {@code Absorber} gadget
     * @param x the x coordinate of the upper left corner of this absorber,
     *      0 <= x < 20
     * @param y the y coordinate of the upper left corner of this absorber,
     *      0 <= y < 20
     * @param width the desired width of this absorber, 1 <= width <= 20
     * @param height the desired height of this absorber, 1 <= height <= 20
     */
    public Absorber(Board board, String name, int x, int y, int width, int height) {
        super(board, name, x, y, 0); // reflection coefficient is basically 0
        this.width = width;
        this.height = height;
        this.balls = new ArrayDeque<>();
        this.firedBall = null;
        this.ballEjectionVelocity = Board.DEFAULT_ABSORBER_EJECTION_VELOCITY;

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

    @Override public void startAction() {
        // if there are balls to be fired and we aren't shooting out a ball right now
        if(!balls.isEmpty() && firedBall == null) {
            firedBall = balls.poll();
            firedBall.setVelocity(ballEjectionVelocity);
            gameBoard.addToActiveList(this);
        }

        checkRep();
    }

    /**
     * Fires a ball out of this absorber and updates the absorber-ball system by
     * dt.
     * @param dt the amount of time for the absorber to perform it's action
     */
    @Override public void action(double dt) {
        firedBall.update(dt);//move the ball up

        Vect pos = firedBall.getPositionVector();
        //if the fired ball is COMPLETELY outside of the absorber
        if(pos.y() + firedBall.getRadius() * 2 < y) {
            //deactivate this
            firedBall.becomeReleased();
            firedBall = null;
            gameBoard.addToDeactivatedList(this);
        }

        checkRep();
    }

    @Override public String stateDescription() {
        StringBuilder state = new StringBuilder();
        //The type of gadget this is along with its name and location
        state.append(String.format(
                         "Absorber(name=%s, x=%d, y=%d, width=%d, height=%d, refCoeff=%.2f)",
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

    @Override public String toString() {
        return "=";
    }

    @Override public void trigger(Ball ball) {
        //try to start this one's action (fire the saved ball
        this.startAction();
        double diameter = ball.getRadius() * 2;
        //store this captured ball
        ball.becomeCaptured();
        //put the ball in the lower left corner of the absorber
        ball.setPosition(new Vect(this.x + this.width - diameter,
                                  this.y + this.height - diameter));
        balls.offer(ball);

        //fire all triggers
        for(Gadget gadget : triggers) {
            gadget.startAction();
        }

        checkRep();
    }

    @Override
    void handleCollision(Ball ball) {
        trigger(ball);

        checkRep();
    }

    @Override
    double calculateCollisionTimeWithBall(Ball ball) {
        double time = Double.POSITIVE_INFINITY;
        //corner collisions
        for(Circle corner : corners) {
            double collisionTime = Geometry.timeUntilCircleCollision(corner,
                                   ball.asCircle(), ball.getVelocityVector());
            if(collisionTime < time) {
                time = collisionTime;
            }
        }

        //side collisions
        for(LineSegment segment : sides) {
            double collisionTime = Geometry.timeUntilWallCollision(segment,
                                   ball.asCircle(), ball.getVelocityVector());
            if(collisionTime < time) {
                time = collisionTime;
            }
        }
        return time;
    }

    @Override
    public void putInDisplay(char[][] board, int hOffset, int vOffset) {
        for(int i = y + vOffset; i < y + vOffset + height; i++) {
            for(int j = x + hOffset; j < x + hOffset + width; j++) {
                board[i][j] = '=';
            }
        }
    }
}
