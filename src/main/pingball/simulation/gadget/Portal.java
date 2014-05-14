package pingball.simulation.gadget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

import physics.Vect;
import pingball.simulation.Ball;
import pingball.simulation.Board;
import pingball.simulation.Constants;
import pingball.simulation.GridLocation;
import pingball.simulation.GameObject.GameObjectType;
import pingball.simulation.collidable.Collidable;
import pingball.simulation.collidable.FixedCircle;
import pingball.util.Pair;


/**
 * 
 * Represents a portal.
 *
 */
public class Portal extends Gadget {
    
    private final boolean thisBoard;
	private final String otherBoard;
	private final String otherPortal;

    private final List<String> representation;
	private BlockingQueue<String> sendQueue;
	
	private boolean active = false;
	private Vect transferLoc;
    
    /**
     * Creates a portal
     * @param board The board on which the portal is.
     * @param name The name of this portal.
     * @param location The grid locaiton of the top left corner of the portal.
     * @param otherBoard The name of the of the otherBoard which has the connected portal.
     * @param otherPortal The name of the portal to which this portal is connected
     * @param thisBoard Whether the connected portal is on this board.
     */
    public Portal(Board board, String name, GridLocation location, String otherBoard, String otherPortal, boolean thisBoard ) {
        super(board, name, location, Constants.PORTAL_REFLECTION_COEFF);
        collidables.add(new FixedCircle(location.toVect().plus(new Vect(0.5,0.5)), 0.5, reflectionCoeff));
        this.otherBoard = otherBoard;
        this.otherPortal = otherPortal;
        this.thisBoard = thisBoard;
        representation = Collections.unmodifiableList(Arrays.asList("0"));
        // TODO Auto-generated constructor stub
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
     * Portals have no action, so this does nothing.
     */
    @Override
    public void action() {
        //Do nothing
    }


    /**
     * Returns a list of strings for the string representation of the circular bumper.
     * 
     * Grid representation of the portal is "0".
     * 
     * @return The grid representation
     */
    @Override public List<String> gridRepresentation() {
        assert(checkRep());
        return representation;
    }
    
    /**
     * If the portal is not connected, then the collide method allows the ball 
     */
    @Override 
    public void collide(Ball ball, Collidable collidable) {
        if (active) {
            if(thisBoard){
                ball.setCenter(transferLoc);
            }
            else{
                Vect center = ball.getCircle().getCircle().getCenter();
                Vect velocity = ball.getVelocity();
                try {
                    board.removeBall(ball);
                    sendQueue.put(String.format("portalball %s %s %s %f %f %f %f",ball.getName(), otherBoard, otherPortal, center.x(), center.y(),
                            velocity.x(), velocity.y()));
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } 
        }
        this.trigger();
    }
    
    /**
     * Returns the minimum time after which the ball will collide with 
     * some collidable in this game object, and also returns the collidable 
     * with which it will collide. 
     * 
     * @param ball The ball that will collide
     * @return The pair of the minimum time and the collidable with which 
     *          the ball will collide after that time. 
     */
    @Override public Pair<Double, Collidable> timeUntilCollision(Ball ball) {
        if( !isBallInside(ball) && active ) {
            assert(checkRep());
            return super.timeUntilCollision(ball);
        } else {
            assert(checkRep());
            return Pair.of(Double.POSITIVE_INFINITY, null);
        }
    }
    
    /**
     * Returns whether ball is inside the portal
     * @param ball The ball to check
     * @return true if ball is inside, false otherwise (false only if whole ball has left the portal)
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
        List<Object> objData = new ArrayList<Object>(Arrays.asList(this.topLeft(),this.getRadius(),this.active));
        return Pair.of(GameObjectType.PORTAL, objData);
    }


    private Pair<Double,Double> topLeft() {
        return Pair.of((double) this.location.getFirst(),(double) this.location.getSecond());
     }
     

    private double getRadius() {
        return 0.5;
    }

    public void find(Set<Portal> portals) {
        if(thisBoard){
            for(Portal portal: portals){
                if(portal.getName().equals(otherPortal)){
                    this.active = true;
                    this.transferLoc = new Vect(portal.location.x()+0.5, portal.location.y()+0.5);
                    break;
                }
            }
        }
    }

    public void setQueue(BlockingQueue<String> sendQueue) {
     this.sendQueue = sendQueue;   
    }

    public void selfOnly() {
        if(!thisBoard){
            this.active = false;
        }
    }

	public String getOtherPortal() {
		return this.otherPortal;
	}
}
