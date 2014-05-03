package boardPhysics;

import physics.*;

public class Ball implements BoardObject{
	
	/**
	 * A vector to the center of the ball describing its position
	 */
	private Vect pos; 
	
	/**
	 * A double representing the length of a ball's radius
	 */
	private final double radius;
	
	/**
	 * A vector representing the velocity of the ball
	 */
	private Vect vel;
	
	/**
	 * A double representing the mass of the ball, to be used as needed
	 */
	private final double mass;
	
	/**
	 * A boolean that is true if a ball is held by an absorber and true
	 * otherwise
	 */
	private boolean inAbsorber;
	
	/**
     * The unique identifier of this ball
     */
    protected final String id;
	
	public Ball(String id, double x, double y, double xVel, double yVel, double radius, double mass){
		this.id = id;
		this.pos = new Vect(x, y);
		this.vel = new Vect(xVel, yVel);
		this.radius = radius;
		this.mass = mass;
		this.inAbsorber = false;
	}
	
	/**
	 * Determine the x-coordinate of the center of the ball
	 * 
	 * @return
	 * 		returns a double representing the x-coordinate of the ball
	 */
	public double getX(){
		return pos.x();
	}
	
	/**
	 * Determine the y-coordinate of the center of the ball
	 * 
	 * @return
	 * 		returns a double representing the y-coordinate of the ball
	 */
	public double getY(){
		return pos.y();
	}
	
	/**
	 * Determine the mass of the ball
	 * 
	 * @return
	 * 		returns a double representing the mass of the ball
	 */
	public double getMass(){
		return mass;
	}
	
	/**
	 * Determine the position of the ball
	 * 
	 * @return
	 * 			returns a vector representing the position of the ball
	 */
	public Vect getPos(){
		return pos;
	}
	
	/**
	 * Determine the velocity of the ball
	 * 
	 * @return
	 * 			returns a vector representing the velocity of the ball
	 */
	public Vect getVel(){
		return vel;
	}
	
	/**
	 * Determine whether the ball is being held by an absorber
	 * 
	 * @return
	 * 			returns a true if the ball is in an absorber and false otherwise
	 */
	public boolean getInAbsorber(){
		return inAbsorber;
	}
	
	/**
	 * Changes the velocity of the ball
	 * 
	 * @param vector
	 * 			the vector representing the new velocity of the ball
	 */
	public void setVel(Vect vel){
		this.vel = vel;
	}
	
	/**
	 * Changes the position of the ball
	 * 
	 * @param pos
	 * 			the pos representing the new position of the ball
	 */
	public void setPos(Vect pos){
		this.pos = pos;
	}
	
	/**
	 * Changes the status of whether ball is being held by an absorber
	 * 
	 * @param inAbsorber
	 * 			boolean of whether the ball is now in an absorber
	 */
	public void setInAbsorber(boolean inAbsorber){
		this.inAbsorber = inAbsorber;
	}

	
	/**
	 * Create a circle representing the ball at its given position
	 * 
	 * @return
	 * 			returns Circle object representing the ball
	 */
	public Circle toCircle(){
		return new Circle(pos, radius);
	}
	
	/**
	 * Serves to retrieve the identifier associated with this ball
	 * 
	 * @return
	 * 			the string ID associated with this ball
	 */
	public Object getID() {
		return id;
	}
	
	@Override
	public void progress(double timeStep){
		pos = pos.plus(vel.times(timeStep));
	}
	
	@Override
	public double[] impactCalc(Ball ball){
		double secondsUntilImpact = Geometry.timeUntilBallBallCollision(this.toCircle(), this.vel, ball.toCircle(), ball.getVel());
		Vect newVel = Geometry.reflectBalls(this.pos, this.mass, this.vel, ball.getPos(), ball.getMass(), ball.getVel()).v2;
		
		return new double[]{secondsUntilImpact, newVel.x(), newVel.y()};
	}

}

