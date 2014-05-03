package boardPhysics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import physics.*;

public class Flipper implements Gadget, BoardObject {
	
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
     * The width of this gadget in units of L
     */
    protected int width;
    
    /**
     * The height of this gadget in units of L
     */
    protected int height;
    
    /**
     * The unique identifier of this wall
     */
    protected final String id;
    
    /**
     * The reflection coefficient for this gadget
     */
    protected final double reflecCoeff;
    
    /**
     * The line segment representing the flipper
     */
    protected LineSegment flipper;
    
    /**
     * The end points of the flipper
     */
    protected List<Circle> flipperVertices;
    
    /**
     * False if the flipper is a right flipper, True if it is a left flipper.
     */
    protected final boolean isLeft;
    
    /**
     * Orientation of the flipper --> 0 if it is vertical, 90 if it is horizontal,
     * and correspondingly in between
     */
    protected double orientation;
    
    /**
     * Boolean that denotes whether the flipper is in the process of rotating
     */
    protected boolean isRotating;
    
    /**
     * Boolean that denotes whether the flipper is in the process of rotating up
     * if it is rotating
     */
    protected boolean isRotatingUp;
    
    /**
     * Angular velocity at which rotation occurs in degrees
     */
    protected double rotationRate;
    
    /**
     * Angular velocity at which rotation occurs in radians
     */
    protected double rotationRateRad;
    
    /**
     * Base error that is tolerable in angle
     */
    protected static double EPSILON = 0.01;
    
    public Flipper(String id, int x, int y, boolean isLeft, int startingOrientation){
		this.xCoord = x;
		this.yCoord = y;
		this.width = 2;
		this.height = 2;
		this.id = id;
		this.reflecCoeff = 0.95;
		this.isLeft = isLeft;
		this.orientation = startingOrientation;
		this.isRotating = false;
		this.isRotatingUp = false;
		this.triggers = new ArrayList<Gadget>();
		if(this.isLeft && this.orientation == 0){
			this.flipper = new LineSegment(new Vect(x, y), new Vect(x, y+2));
			this.flipperVertices = Arrays.asList(new Circle(x, y, 0.01), new Circle(x, y+2, 0.01));
		}else if(!this.isLeft && this.orientation == 0){
			this.flipper = new LineSegment(new Vect(x+2, y), new Vect(x+2, y+2));
			this.flipperVertices = Arrays.asList(new Circle(x+2, y, 0.01), new Circle(x+2, y+2, 0.01));
		} else if(this.isLeft && this.orientation == 1){
			this.flipper = new LineSegment(new Vect(x, y), new Vect(x+2, y));
			this.flipperVertices = Arrays.asList(new Circle(x, y, 0.01), new Circle(x+2, y, 0.01));
		} else{
			this.flipper = new LineSegment(new Vect(x+2, y), new Vect(x, y));
			this.flipperVertices = Arrays.asList(new Circle(x+2, y, 0.01), new Circle(x, y, 0.01));
		}
		this.rotationRate = 1080;
		this.rotationRateRad = 1080*Math.PI/180;
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
		return 2;
	}

	@Override
	public int getHeight() {
		return 2;
	}

	@Override
	public double getReflec() {
		return reflecCoeff;
	}

	@Override
	public void action() {
		if(isRotating){
			isRotatingUp = !isRotatingUp;
		}else{
			isRotating = true;
			if(orientation < EPSILON){
				isRotatingUp = true;
			}else{
				isRotatingUp = false;
			}
		}
	}

	@Override
	public void trigger() {
		action();
		for (Gadget t : triggers){
			t.trigger();
		}
	}

	@Override
	public void progress(double timeStep) {
		if(!isRotating){
			return;
		}
		
		double timeToCompleteRotating;
		if(isRotatingUp){
			timeToCompleteRotating = (90.0-orientation)/rotationRate;
		} else{
			timeToCompleteRotating = orientation/rotationRate;
		}
		
		if (timeToCompleteRotating < timeStep){
			if(isRotatingUp & isLeft){
				flipper = new LineSegment(xCoord, yCoord, xCoord+2, yCoord);
				flipperVertices = Arrays.asList(new Circle(xCoord, yCoord, 0.01), new Circle(xCoord + 2, yCoord, 0.01));
				orientation = 90;
				isRotating = false;
				return;
			} else if(isRotatingUp){
				flipper = new LineSegment(xCoord + 2, yCoord, xCoord, yCoord);
				flipperVertices = Arrays.asList(new Circle(xCoord, yCoord, 0.01), new Circle(xCoord + 2, yCoord, 0.01));
				orientation = 90;
				isRotating = false;
				return;
			}else if(isLeft){
				flipper = new LineSegment(xCoord, yCoord, xCoord, yCoord+2);
				flipperVertices = Arrays.asList(new Circle(xCoord, yCoord, 0.01), new Circle(xCoord, yCoord + 2, 0.01));
				isRotating = false;
				orientation = 0;
				return;
			}else{
				flipper = new LineSegment(xCoord + 2, yCoord, xCoord + 2, yCoord + 2);
				flipperVertices = Arrays.asList(new Circle(xCoord + 2, yCoord, 0.01), new Circle(xCoord + 2, yCoord + 2, 0.01));
				isRotating = false;
				orientation = 0;
				return;
			}
		}
		
		double rotateAngle;
		if(isRotatingUp){
			orientation = orientation + rotationRate*timeStep;
			if(isLeft){
				rotateAngle = -rotationRateRad*timeStep;
			}else{
				rotateAngle = rotationRateRad*timeStep;
			}
		}else{
			orientation = orientation - rotationRate*timeStep;
			if(isLeft){
				rotateAngle = rotationRateRad*timeStep;
			}else{
				rotateAngle = -rotationRateRad*timeStep;
			}
		}
		
		flipper = Geometry.rotateAround(flipper, flipper.p1(), new Angle(rotateAngle));
		flipperVertices = Arrays.asList(new Circle(flipper.p1(), 0.01), new Circle(flipper.p2(), 0.01));
		return;
	}

	@Override
	public double[] impactCalc(Ball ball) {
		if(!isRotating){
			
			double minTimeToEdgeCollision = Geometry.timeUntilWallCollision(flipper, ball.toCircle(), ball.getVel());
			
			double minTimeToVertexCollision = Double.POSITIVE_INFINITY;
			Circle closestVertex = flipperVertices.get(0);
			
			for (Circle vertex : flipperVertices){
				double timeToCollision = Geometry.timeUntilCircleCollision(vertex, ball.toCircle(), ball.getVel());
				if (timeToCollision < minTimeToVertexCollision){
					minTimeToVertexCollision = timeToCollision;
					closestVertex = vertex;
				}
			}
			
			Vect newVel;
			double minTimeToCollision;
			
			if (minTimeToEdgeCollision < minTimeToVertexCollision){
				newVel = Geometry.reflectWall(flipper, ball.getVel(), reflecCoeff);
				minTimeToCollision = minTimeToEdgeCollision;
			} else{
				newVel = Geometry.reflectCircle(closestVertex.getCenter(), ball.getPos(), ball.getVel(), reflecCoeff);
				minTimeToCollision = minTimeToVertexCollision;
			}
			
			return new double[] {minTimeToCollision, newVel.x(), newVel.y()};
			
		} else{
			double timeToCompleteRotating;
			double rotate;
			if(isRotatingUp){
				timeToCompleteRotating = (90.0-orientation)/rotationRate;
				if(isLeft){
					rotate = -rotationRateRad;
				}else{
					rotate = rotationRateRad;
				}
			} else{
				timeToCompleteRotating = orientation/rotationRate;
				if(isLeft){
					rotate = rotationRateRad;
				}else{
					rotate = -rotationRateRad;
				}
			}
			
			double minTimeToEdgeCollision = Geometry.timeUntilRotatingWallCollision(flipper, flipper.p1(), rotate, ball.toCircle(), ball.getVel());
			
			double minTimeToVertexCollision = Double.POSITIVE_INFINITY;
			Circle closestVertex = flipperVertices.get(0);
			
			for (Circle vertex : flipperVertices){
				double timeToCollision = Geometry.timeUntilRotatingCircleCollision(vertex, flipper.p1(), rotate, ball.toCircle(), ball.getVel());
				if (timeToCollision < minTimeToVertexCollision){
					minTimeToVertexCollision = timeToCollision;
					closestVertex = vertex;
				}
			}
			
			if(minTimeToEdgeCollision < timeToCompleteRotating){
				Vect newVel = Geometry.reflectRotatingWall(flipper, flipper.p1(), rotate, ball.toCircle(), ball.getVel(), reflecCoeff);
				double minTimeToCollision = minTimeToEdgeCollision;
				return new double[] {minTimeToCollision, newVel.x(), newVel.y()};
				
			} else if(minTimeToVertexCollision < timeToCompleteRotating){
				Vect newVel = Geometry.reflectRotatingCircle(closestVertex, flipper.p1(), rotate, ball.toCircle(), ball.getVel(), reflecCoeff);
				Double minTimeToCollision = minTimeToVertexCollision;
				return new double[] {minTimeToCollision, newVel.x(), newVel.y()};
			}
			
			LineSegment newFlipper;
			List<Circle> newFlipperVertices;
			if(isRotatingUp){
				newFlipper = new LineSegment(xCoord, yCoord, xCoord+2, yCoord);
				newFlipperVertices = Arrays.asList(new Circle(xCoord, yCoord, 0.01), new Circle(xCoord + 2, yCoord, 0.01));
			}else if(isLeft){
				newFlipper = new LineSegment(xCoord, yCoord, xCoord, yCoord+2);
				newFlipperVertices = Arrays.asList(new Circle(xCoord, yCoord, 0.01), new Circle(xCoord, yCoord + 2, 0.01));
			}else{
				newFlipper = new LineSegment(xCoord + 2, yCoord, xCoord + 2, yCoord + 2);
				newFlipperVertices = Arrays.asList(new Circle(xCoord + 2, yCoord, 0.01), new Circle(xCoord + 2, yCoord + 2, 0.01));
			}
			
			minTimeToEdgeCollision = Geometry.timeUntilWallCollision(newFlipper, ball.toCircle(), ball.getVel());
			
			minTimeToVertexCollision = Double.POSITIVE_INFINITY;
			closestVertex = newFlipperVertices.get(0);
			
			for (Circle vertex : flipperVertices){
				double timeToCollision = Geometry.timeUntilCircleCollision(vertex, ball.toCircle(), ball.getVel());
				if (timeToCollision < minTimeToVertexCollision){
					minTimeToVertexCollision = timeToCollision;
					closestVertex = vertex;
				}
			}
			
			Vect newVel;
			double minTimeToCollision;
			
			if (minTimeToEdgeCollision < minTimeToVertexCollision){
				newVel = Geometry.reflectWall(flipper, ball.getVel(), reflecCoeff);
				minTimeToCollision = minTimeToEdgeCollision;
			} else{
				newVel = Geometry.reflectCircle(closestVertex.getCenter(), ball.getPos(), ball.getVel(), reflecCoeff);
				minTimeToCollision = minTimeToVertexCollision;
			}
			
			return new double[] {minTimeToCollision, newVel.x(), newVel.y()};
			
		}
	}

}
