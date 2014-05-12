package pingball.simulation.gadget;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import physics.Vect;
import pingball.simulation.Board;
import pingball.simulation.GridLocation;
import pingball.simulation.Constants;
import pingball.simulation.collidable.FixedCircle;
import pingball.util.Pair;

/**
 * Represents a circlebumper of size 1x1 grid. The location of this object is relative to the board. 
 * It will bounce any object that collides with it.
 * 
 * Rep-Invariant:
 *      The gridlocation must be in the way that the circlebumper does not exceed the board when combing with the width/height
 */

public class CircleBumper extends Gadget {

    private final List<String> representation;
    
    /**
     * Creates a circular bumper.
     * 
     * @param board The board on which the circular bumper is.
     * @param name The name of this bumper.
     * @param location The grid location of the top left corner of the circular bumper.
     */
    public CircleBumper(Board board, String name, GridLocation location) {
        super(board, name, location, Constants.BUMPER_REFLECTION_COEFF);
        collidables.add(new FixedCircle(location.toVect().plus(new Vect(0.5,0.5)), 0.5, reflectionCoeff));
        representation = Collections.unmodifiableList(Arrays.asList("O"));
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
     * Performs the action of this circular bumper.
     * 
     * Circle bumpers have no action, so this does nothing.
     */
    
    @Override public void action() {
       //Do Nothing
        assert(checkRep());
    }


    /**
     * Returns a list of strings for the string representation of the circular bumper.
     * 
     * Grid representation of the circular bumper is "O".
     * 
     * @return The grid representation
     */
    @Override public List<String> gridRepresentation() {
        assert(checkRep());
        return representation;
    }

    @Override
    public Pair<GameObjectType, List<Object>> getObjectData() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
