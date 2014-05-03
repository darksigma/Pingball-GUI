package boardPhysics;

import physics.*;

public interface BoardObject {
	
	
	/**
	 * Progresses an object by a timestep assuming the current position and velocities
	 * of the object and assuming that *NO* collisions occur between that object and any
	 * other objects
	 * 
	 * @param timeStep
	 * 			a double representing the time step by which we will progress the state
	 * 			of the object
	 */
	public void progress(double timeStep);
	
	
	/**
	 * Determine the time it will take until this object collides with a given ball
	 * and recalculate a ball's velocity assuming it collided with this board object
	 * 
	 * @param ball
	 * 			a ball that collided with this board object
	 * @return
	 * 			a array of doubles with components:
	 * 				0. Time until impact with the ball
	 * 				1. x-component of the ball's new velocity post-impact
	 * 				2. y-component of the ball's new velocity post-impact
	 */
	public double[] impactCalc(Ball ball);
}
