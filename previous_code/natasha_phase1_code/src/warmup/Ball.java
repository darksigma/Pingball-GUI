package warmup;
import physics.Vect;

/**
 * Ball is a subclass of BoardObject that creates ball instances. Each ball has a velocity
 * represented by a Vect.
 *
 * @author enguyen, nconsul, spefley
 *
 */
public class Ball extends BoardObject {

    private Vect velocity;

    /**
     * Constructor that creates a non-moving ball with an initial velocity set to (0.0, 0.0).
     */
    public Ball() {
        velocity = new Vect(0.0, 0.0);
    }

    /**
     * Constructor that creates a ball with a given initial velocity in the x and y directions.
     * @param velX represents the initial velocity in the x-direction (double)
     * @param velY represents the initial velocity in the y-direction (double)
     */
    public Ball(double velX, double velY) {
        velocity = new Vect(velX, velY);
    }

    /**
     * Returns the String representation of the ball, represented as "*"
     * @return "*"
     */
    public String toString() {
        return "*";
    }

    /**
     * Gets the current velocity of the ball.
     * @return velocity of the ball, represented as a Vect
     */
    public Vect velocity() {
        return velocity;
    }

    /**
     * Modifies the velocity of the ball if the ball hits either the left side or right side
     * of the board. Velocity x-component reverses and y-component remains unchanged.
     */
    public void reflectX() {
        double newX = -1*velocity.x();
        velocity = new Vect(newX, velocity.y());
    }

    /**
     * Modifies the velocity of the ball if the ball hits either the top or bottom of the board.
     * Velocity y-component reverses and x-component remains unchanged.
     */
    public void reflectY() {
        double newY = -1*velocity.y();
        velocity = new Vect(velocity.x(), newY);
    }

}
