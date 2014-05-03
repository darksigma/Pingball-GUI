package boardPhysics;

import java.util.ArrayList;
import java.util.List;

import physics.*;


public class OuterWall implements Gadget, BoardObject {
	
	/**
	 * The line segment representing the wall
	 */
	private LineSegment wall;
	
	/**
     * What board this wall is connected to
     */
	private Board connectedBoard;
	
	/**
     * Which wall of that board it is connected to:
     * 		"wall 0" = top
     * 		"wall 1" = right
     * 		"wall 2" = bottom
     * 		"wall 3" = left
     * 
     * NOTE: if connectedBoard == self, and this.id.equals(connectedWall),
     * then the wall is reflective and not invisible
     */
	private String connectedWall;
	
	/**
     * List of gadgets whose triggers are connected to this gadget
     */
    protected List<Gadget> triggers;
    
    /**
     * X coordinate of the upper left corner of this gadget
     */
    protected final int xCoord;
    
    /**
     * Y coordinate of the upper left corner of this gadget
     */
    protected final int yCoord;
    
    /**
     * The unique identifier of this wall
     */
    protected final String id;
    
    /**
     * The reflection coefficient for this gadget
     */
    protected final double reflecCoeff;
	
	public OuterWall(String id, LineSegment wall, Board connectedBoard, String connectedWall){
		this.wall = wall;
		this.connectedBoard = connectedBoard;
		this.connectedWall = connectedWall;
		this.id = id; 
		this.triggers = new ArrayList<Gadget>();
		this.reflecCoeff = 1.0;
		
		if(wall.p1().x() < wall.p2().x()){
			this.xCoord = (int) wall.p1().x();
			this.yCoord = (int) wall.p1().y();
		} else if(wall.p2().x() < wall.p1().x()){
			this.xCoord = (int) wall.p2().x();
			this.yCoord = (int) wall.p2().y();
		} else if(wall.p1().y() < wall.p2().y()){
			this.xCoord = (int) wall.p1().x();
			this.yCoord = (int) wall.p1().y();
		} else{
			this.xCoord = (int) wall.p2().x();
			this.yCoord = (int) wall.p2().y();
		}
	}
	
	/**
	 * Sets the connectivity property of the wall
	 * 
	 * @param board
	 * 			the board to which this wall is to be connected
	 * @param wall
	 * 			a string representing which of the walls it is meant to
	 * 			be connected to
	 */
	public void setConnectivity(Board connectedBoard, String connectedWall){
		this.connectedBoard = connectedBoard;
		this.connectedWall = connectedWall;
	}
	
	/**
	 * Obtains which board it is connected to
	 * 
	 * @return
	 * 			The board to which this edge is connected to
	 */
	public Board getConnectedBoard(){
		return connectedBoard;
	}
	
	/**
	 * Obtains the wall of the board this wall is connected to
	 * 
	 * @return
	 * 			string representing the wall of the board this wall is connected to
	 */
	public String getConnectedWall(){
		return connectedWall;
	}

	@Override
	public boolean connect(Gadget g) {
		return triggers.add(g);
	}

	@Override
	public boolean disconnect(Gadget g) {
		return triggers.remove(g);
	}

	@Override
	public int getX() {
		return xCoord;
	}

	@Override
	public int getY() {
		return yCoord;
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public int getWidth() {
		return (int) Math.abs(wall.p1().x() - wall.p2().x());
	}

	@Override
	public int getHeight() {
		return (int) Math.abs(wall.p1().y() - wall.p2().y());
	}

	@Override
	public double getReflec() {
		return reflecCoeff;
	}

	@Override
	public void action() {
		return;
	}

	@Override
	public void trigger() {
		action();
		for (Gadget t : triggers){
			t.trigger();
		}
	}

	@Override
	public double[] impactCalc(Ball ball) {
		double timeToCollision = Geometry.timeUntilWallCollision(wall, ball.toCircle(), ball.getVel());
		Vect newVel = Geometry.reflectWall(wall, ball.getVel(), reflecCoeff);
		return new double[]{timeToCollision, newVel.x(), newVel.y()};
	}

	@Override
	public void progress(double timeStep) {
		return;
	}

}
