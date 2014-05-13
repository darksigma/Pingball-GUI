package pingball.simulation.gadget;

import java.util.List;

import pingball.simulation.Ball;
import pingball.simulation.Board;
import pingball.simulation.GridLocation;
import pingball.simulation.collidable.Collidable;
import pingball.util.Pair;


/**
 * 
 * Represents a portal.
 *
 */
public class Portal extends Gadget {
    
    //private final List<String> representation;
    
    private boolean active;
	private String otherBoard;
	private String otherPortal;

    public Portal(Board board, String name, GridLocation location, double reflectionCoeff, String otherBoard, String otherPortal, boolean thisBoard ) {
        super(board, name, location, reflectionCoeff);
        this.otherBoard = otherBoard;
        this.otherPortal = otherPortal;
        if(thisBoard){
        	this.active = true;
        } else {
        	this.active = false;
        }
        // TODO Auto-generated constructor stub
    }

    private boolean checkRep() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void action() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<String> gridRepresentation() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * If the portal is not connected, then the collide method allows the ball 
     */
    @Override 
    public void collide(Ball ball, Collidable collidable) {
        
    }
    /**
     * Connect the portal to a particular portal on a particular board. 
     * In special case, this will be the same board.
     * 
     * Balls hitting the portal will be teleported.
     * 
     * @param boardName Name of the board to which this wall is connected.
     * @param portalName Name of the portal to which this wall is connected.
     */
    public void activate() {
        this.active = true;
        assert(checkRep());
    }


    /**
     * Disconnect the portal from any boards. 
     * 
     * Balls hitting the portal will now pass over without any effect.
     */
    public void deactivate() {
        this.active = false;
        assert(checkRep());
    }
    
    /**
     * Obtains the board of the other portal this portals is connected to
     * 
     * @return
     * 		a String representing the board this portal is connected to
     */
    public String getOtherBoard(){
    	return this.otherBoard;
    }

    @Override
    public Pair<GameObjectType, List<Object>> getObjectData() {
        // TODO Auto-generated method stub
        return null;
    }
}
