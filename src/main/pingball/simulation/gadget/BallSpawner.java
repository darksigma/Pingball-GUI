package pingball.simulation.gadget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import physics.Vect;
import pingball.simulation.Ball;
import pingball.simulation.Board;
import pingball.simulation.Constants;
import pingball.simulation.GridLocation;
import pingball.simulation.collidable.Collidable;
import pingball.simulation.collidable.FixedCircle;
import pingball.util.Pair;

/**
 * This is our new feature. Ball spawner is a new gadget that spawns a new ball whenever a ball
 * collides with it. The ball that collides is reflected of the spawner. The newly spawned ball
 * gets a velocity of 50 in a random direction. Size of a ball spawner is 1x1 grid. The location 
 * of this object is relative to the board. 
 * 
 * Rep-Invariant:
 *      The gridlocation must be in the way that the ballspawner does not exceed the board when combining with the width/height
 */

public class BallSpawner extends Gadget {

    private final List<String> representation;
    private int spawnCount = 0;
        
   
    /**
     * Creates a ball spawner.
     * 
     * @param board The board on which the ball spawner is.
     * @param name The name of this spawner.
     * @param location The grid location of the top left corner of the ball spawner.
     */
    public BallSpawner(Board board, String name, GridLocation location) {
        super(board, name, location, Constants.BALLSPAWNER_REFLECTION_COEFF);
        collidables.add(new FixedCircle(location.toVect().plus(new Vect(0.5,0.5)), 0.5, reflectionCoeff));
        representation = Collections.unmodifiableList(Arrays.asList("@"));
        assert(checkRep());
    }
    
    /**
     * Check the rep-invariant of this object
     * @return whether the rep is ok
     */
    public boolean checkRep() {
        return location.x() >= 0 && location.x() < board.getWidth() && 
               location.y() >= 0 && location.y() < board.getHeight();         
    }

    /**
     * Performs the action of this ball spawner.
     * 
     * Ball spawners have no action, so this does nothing.
     */
    
    @Override public void action() {
       //Do Nothing
        assert(checkRep());
    }
    
    @Override public Pair<Double, Collidable> timeUntilCollision(Ball ball) {
        if( !isBallInside(ball)) {
            assert(checkRep());
            return super.timeUntilCollision(ball);
        } else {
            assert(checkRep());
            return Pair.of(Double.POSITIVE_INFINITY, null);
        }
    }
    
    /**
     * Adds a new ball to the board when a ball collides with a ballspawner gadget.
     * The colliding ball is reflected from its original direction, but the new ball
     * is added to move in a random direction from the center of the ballspawner.
     */
    @Override 
    public void collide(Ball ball, Collidable collidable) {
        super.collide(ball, collidable);
        Vect transferLoc = new Vect(this.getLocation().x()+0.5, this.getLocation().y()+0.5);
        Double theta = Math.random()*2*Math.PI;
        board.addBall(new Ball(transferLoc, new Vect(Constants.SPAWNER_SHOOT_VELOCITY*Math.cos(theta),Constants.SPAWNER_SHOOT_VELOCITY*Math.sin(theta) ), this.getName()+"SpawnedBall"+this.spawnCount, board.getGravity(), board.getFriction1(), board.getFriction2()));
        spawnCount += 1;
        this.trigger();
    }
    
    /**
     * Returns whether ball is inside the ball spawner
     * @param ball The ball to check
     * @return true if ball is inside, false otherwise (false only if whole ball has left the ball spawner)
     */
    private boolean isBallInside(Ball ball) {
        Vect ballCenter = ball.getCircle().getCircle().getCenter();
        Double ballX = ballCenter.x();
        Double ballY = ballCenter.y();
        Double radius = ball.getCircle().getCircle().getRadius();
        Double centerX = this.location.x()+0.5;
        Double centerY = this.location.y()+0.5;
        assert(checkRep());
        return ((ballX-centerX)*(ballX-centerX)+(ballY-centerY)*(ballY-centerY)) < ((0.5+radius)*(0.5+radius));
    }    
    
    /**
     * Returns a list of strings for the string representation of the ball spawner.
     * 
     * Grid representation of the ball spawner is "@".
     * 
     * @return The grid representation
     */
    @Override public List<String> gridRepresentation() {
        assert(checkRep());
        return representation;
    }


    /**
     * Returns the ballspawner's data in a list.
     * The data is a list containing the grid location of its top left corner,
     * the radius, and the trigger state.
     */
    @Override
    public Pair<GameObjectType, List<Object>> getObjectData() {
        List<Object> objData = new ArrayList<Object>(Arrays.asList(this.topLeft(),this.getRadius(),this.triggerState));
        return Pair.of(GameObjectType.BALLSPAWNER, objData);
    }

    private double getRadius() {
        return 0.5;
    }

    private Pair<Double,Double> topLeft() {
       return Pair.of((double) this.location.getFirst(),(double) this.location.getSecond());
    }
    
}

