package pingball.simulation.gadget;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import pingball.simulation.Board;
import pingball.simulation.GridLocation;
import pingball.simulation.Constants;
import pingball.simulation.collidable.FixedCircle;
import pingball.simulation.collidable.Line;

/**
 * Represents a trianglebumper of size 1x1. The location of this object is relative to the board. 
 * It will bounce any object that collides with it.
 * 
 * Rep-Invariant:
 *      The gridlocation must be in the way that the trianglebumper does not exceed the board when combing with the width/height
 */
public class TriangleBumper extends Gadget {
    
    private final List<String> representation;
    
    private final Orientation orientation;
    
    /**
     * Creates a triangular bumper
     * 
     * @param board The Board on which the triangular bumper is.
     * @param name The name of this bumper.
     * @param location The location of the top left corner of the bounding square
     *                  of the triangular bumper.
     * @param orientation The orientation of the triangular bumper.
     */
    public TriangleBumper(Board board, String name, GridLocation location, Orientation orientation) {
        super(board, name, location, Constants.BUMPER_REFLECTION_COEFF);
        this.orientation = orientation;
        
        final int[][] displaceBy;
        switch(this.orientation){
            case ANGLE_0: displaceBy = new int[][] {{0,0},{0,1},{1,0}}; break;
            case ANGLE_90: displaceBy = new int[][] {{0,0},{1,0},{1,1}}; break;
            case ANGLE_180: displaceBy = new int[][] {{0,1},{1,0},{1,1}}; break;
            default: displaceBy = new int[][] {{0,0},{0,1},{1,1}}; break;
        }
        
        for(int[] displacement: displaceBy){
            collidables.add(new FixedCircle(location.add(displacement).toVect(), 0, this.reflectionCoeff));
        }
        
        collidables.add(new Line(location.toVect(), location.add(displaceBy[1]).toVect(), reflectionCoeff));
        collidables.add(new Line(location.toVect(), location.add(displaceBy[2]).toVect(), reflectionCoeff));
        collidables.add(new Line(location.add(displaceBy[1]).toVect(), location.add(displaceBy[2]).toVect(), reflectionCoeff));
        
        if (this.orientation.equals(Orientation.ANGLE_0) || this.orientation.equals(Orientation.ANGLE_180)){
            representation = Collections.unmodifiableList(Arrays.asList("/"));
        }
        else representation = Collections.unmodifiableList(Arrays.asList("\\"));
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
     * Performs the action of this triangular bumper.
     * 
     * Triangle bumpers have no action, so this does nothing.
     */
    @Override public void action() {
        assert(checkRep());
        //Do nothing
    }
    
    /**
     * Returns a list of strings for the string representation of the triangular bumper.
     * 
     * Grid representation of the triangular bumper is "/" if orientation is 0 or 180,
     * "\\" if orientation is 90 or 270.
     * 
     * @return The grid representation
     */
    @Override public List<String> gridRepresentation() {
        assert(checkRep());
        return representation;
    }
    
}
