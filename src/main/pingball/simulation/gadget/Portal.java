package pingball.simulation.gadget;

import java.util.List;

import pingball.simulation.Board;
import pingball.simulation.GridLocation;


/**
 * 
 * Represents a portal.
 *
 */
public class Portal extends Gadget {
    
    //private final List<String> representation;
    
    private String connectedBoardName;
    private String connectedPortalName;
    private boolean connected;

    public Portal(Board board, String name, GridLocation location,
            double reflectionCoeff) {
        super(board, name, location, reflectionCoeff);
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
     * Connect the portal to a particular portal on a particular board. 
     * In special case, this will be the same board.
     * 
     * Balls hitting the portal will be teleported.
     * 
     * @param boardName Name of the board to which this wall is connected.
     * @param portalName Name of the portal to which this wall is connected.
     */
    public void connect(String boardName,String portalName) {
        this.connectedBoardName = boardName;
        this.connectedPortalName = portalName;
        this.connected = true;
        assert(checkRep());
    }


    /**
     * Disconnect the portal from any boards. 
     * 
     * Balls hitting the portal will now pass over without any effect.
     */
    public void disconnect() {
        this.connectedBoardName = null;
        this.connectedPortalName = null;
        this.connected = false;
        assert(checkRep());
    }
}
