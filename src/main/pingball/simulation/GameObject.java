package pingball.simulation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pingball.util.Pair;
import pingball.simulation.collidable.Collidable;

/**
 * An object that appears on the board. 
 * 
 * The game objects can be a ball, a bounding wall or a gadget.
 */
public abstract class GameObject {
    
    /**
     * An enum to represent the type of the game object. 
     *
     */
    public enum GameObjectType {
        BALL,
        WALL,
        SQUAREBUMPER,
        CIRCLEBUMPER,
        TRIANGLEBUMPER,
        FLIPPER,
        ABSORBER,
        PORTAL,
        BALLSPAWNER
    }

    protected final Set<Collidable> collidables = new HashSet<>();
    
    /**
     * Returns the grid location of the top left corner of the game object.
     * 
     * @return The grid location.
     */
    public abstract GridLocation getLocation();
    
    /**
     * Returns a list of strings for the string representation of the game object.
     * 
     * Each string in the list represents a row of the bounding square of the object,
     * Number of strings is equal to the height of the bounding square of the object 
     * The rows are aligned with respect to the top left of the object.
     * 
     * @return The grid representation
     */
    public abstract List<String> gridRepresentation();
    
    /**
     * Evolve the game object for the specified period of time.
     * @param time The time period to evolve the object for.
     */
    public void evolve(double time) {
        // by default, do nothing
    }

    /**
     * Collide the ball with a particular collidable in the game object. 
     * 
     * If this game object is a ball, then mutate itself also.
     * 
     * @param ball The ball that collides.
     * @param collidable The collidable that the ball collides with.
     */
    public void collide(Ball ball, Collidable collidable) {
        collidable.collide(ball);
    }
    
    /**
     * Returns the minimum time after which the ball will collide with 
     * some collidable in this game object, and also returns the collidable 
     * with which it will collide. 
     * 
     * Assumes ball moves with constant velocity for the specified time period. So, 
     * returned value is accurate only for a time period < Simulation timestep.
     * 
     * @param ball The ball that will collide
     * @return The pair of the minimum time and the collidable with which 
     *          the ball will collide after that time. 
     */
    public Pair<Double, Collidable> timeUntilCollision(Ball ball) {
        double minTime = Double.POSITIVE_INFINITY;
        Collidable collidesWith = null;
        for(Collidable collidable: collidables){
            double collideTime = collidable.timeUntilCollision(ball);
            if (collideTime < minTime) {
                minTime = collideTime;
                collidesWith = collidable;
            }
        }
        return Pair.of(minTime,collidesWith);
    }
    
    /**
     * Returns all the data about the current state of the game object.
     * @return The object data
     */
    public abstract Pair<GameObjectType,List<Object>> getObjectData();
    
    /**
     * Resets the trigger state of the game object to not triggered.
     */
    public void deTrigger() {
    }

}
