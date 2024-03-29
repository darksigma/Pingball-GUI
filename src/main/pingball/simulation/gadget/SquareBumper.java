package pingball.simulation.gadget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pingball.simulation.Board;
import pingball.simulation.GridLocation;
import pingball.simulation.Constants;
import pingball.simulation.collidable.FixedCircle;
import pingball.simulation.collidable.Line;
import pingball.util.Pair;

/**
 * Represents a squarebumper of size 1x1. The location of this object is relative to the board. 
 * It will bounce any object that collides with it.
 * 
 * Rep-Invariant:
 *      The gridlocation must be in the way that the squarebumper does not exceed the board when combing with the width/height
 */
public class SquareBumper extends Gadget {
    
    private final List<String> representation;
    
    /**
     * Creates a square bumper.
     * 
     * @param board The board on which the square bumper is.
     * @param name The name of this bumper.
     * @param location The grid location of the top left corner of the square bumper.
     */
    public SquareBumper(Board board, String name, GridLocation location) {
        super(board, name, location, Constants.BUMPER_REFLECTION_COEFF);
        
        int[][] displaceBy = new int[][] {{0,0},{0,1},{1,0},{1,1}};
  
        for(int[] displacement: displaceBy){
            collidables.add(new FixedCircle(location.add(displacement).toVect(), 0, this.reflectionCoeff));
        }
        
        collidables.add(new Line(location.toVect(), location.add(displaceBy[1]).toVect(), reflectionCoeff));
        collidables.add(new Line(location.toVect(), location.add(displaceBy[2]).toVect(), reflectionCoeff));
        collidables.add(new Line(location.add(displaceBy[1]).toVect(), location.add(displaceBy[3]).toVect(), reflectionCoeff));
        collidables.add(new Line(location.add(displaceBy[2]).toVect(), location.add(displaceBy[3]).toVect(), reflectionCoeff));

        representation = Collections.unmodifiableList(Arrays.asList("#"));
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
     * Performs the action of this square bumper.
     * 
     * Square bumpers have no action, so this does nothing.
     */
    @Override public void action() {
        assert(checkRep());
        //Do nothing
    }

    /**
     * Returns a list of strings for the string representation of the square bumper.
     * 
     * Grid representation of the square bumper is "#".
     * 
     * @return the grid representation
     */
    @Override public List<String> gridRepresentation() {
        assert(checkRep());
        return representation;
    }

    public Pair<Double, Double> topLeft() {
        return Pair.of((double) this.location.getFirst(),(double) this.location.getSecond());
    }
    
    public int size(){
        return 1;
    }

    /**
     * Returns the square bumper's data in a list.
     * Includes the grid location, the size, and the trigger state.
     */
    @Override
    public Pair<GameObjectType, List<Object>> getObjectData() {
        List<Object> objData = new ArrayList<Object>(Arrays.asList(this.topLeft(),1.0,this.triggerState));
        return Pair.of(GameObjectType.SQUAREBUMPER, objData);
    }

}
