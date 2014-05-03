package boardPhysics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class SquareBumper implements Gadget, BoardObject {
	
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
     * List of line segments representing the square bumper
     */
    protected final List<LineSegment> squareSides;
    
    /**
     * List of circles representing the square bumper vertices
     */
    protected final List<Circle> squareVertices;
    
    public SquareBumper(String id, int x, int y){
		this.xCoord = x;
		this.yCoord = y;
		this.width = 1;
		this.height = 1;
		this.id = id;
		this.reflecCoeff = 1;
		this.squareSides = Arrays.asList(new LineSegment(x, y, x+1, y),
										new LineSegment(x+1, y, x+1, y+1),
										new LineSegment(x+1, y+1, x, y+1),
										new LineSegment(x, y+1, x, y));
		this.squareVertices = Arrays.asList(new Circle(x, y, 0.01),
											new Circle(x+1, y, 0.01),
											new Circle(x, y+1, 0.01),
											new Circle(x+1, y+1, 0.01));
		this.triggers = new ArrayList<Gadget>();
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
		return 1;
	}

	@Override
	public int getHeight() {
		return 1;
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
		double minTimeToEdgeCollision = Double.POSITIVE_INFINITY;
		LineSegment closestEdge = squareSides.get(0);
		
		for (LineSegment edge : squareSides){
			double timeToCollision = Geometry.timeUntilWallCollision(edge, ball.toCircle(), ball.getVel());
			if (timeToCollision < minTimeToEdgeCollision){
				minTimeToEdgeCollision = timeToCollision;
				closestEdge = edge;
			}
		}
		
		double minTimeToVertexCollision = Double.POSITIVE_INFINITY;
		Circle closestVertex = squareVertices.get(0);
		
		for (Circle vertex : squareVertices){
			double timeToCollision = Geometry.timeUntilCircleCollision(vertex, ball.toCircle(), ball.getVel());
			if (timeToCollision < minTimeToVertexCollision){
				minTimeToVertexCollision = timeToCollision;
				closestVertex = vertex;
			}
		}
		
		Vect newVel;
		double minTimeToCollision;
		
		if (minTimeToEdgeCollision < minTimeToVertexCollision){
			newVel = Geometry.reflectWall(closestEdge, ball.getVel(), reflecCoeff);
			minTimeToCollision = minTimeToEdgeCollision;
		} else{
			newVel = Geometry.reflectCircle(closestVertex.getCenter(), ball.getPos(), ball.getVel(), reflecCoeff);
			minTimeToCollision = minTimeToVertexCollision;
		}
		
		return new double[] {minTimeToCollision, newVel.x(), newVel.y()};
	}

	@Override
	public void progress(double timeStep) {
		return;
	}

}
