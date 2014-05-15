package pingball.simulation.gadget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import physics.Vect;
import pingball.simulation.Constants;
import pingball.simulation.GridLocation;
import pingball.simulation.Board;
import pingball.simulation.collidable.Collidable;
import pingball.simulation.collidable.FixedCircle;
import pingball.simulation.collidable.Line;
import pingball.util.Pair;

/**
 * Represents a flipper. The location of this object is relative to the board. It is basically
 * a line segment with two dots. The flipper can flip that its action was called. The current
 * angle variable represents the current orientation of the flipper in counterclockwise direction
 * respect to its most counterclockwise angle.
 * 
 * Rep-Invariant:
 *      The gridlocation must be in the way that the absorber does not exceed the board when combing with the width/height
 *      The current angle must be between 0 and 90
 */
public class Flipper extends Gadget {
    
    private final Orientation orientation;
    
    /**
     * Type of flipper - can be left or right
     *
     */
    public enum FlipperType {
        LEFT,
        RIGHT
    }
    
    /**
     * Direction of rotation of flipper - can be clockwise or counterclockwise
     *
     */
    public enum FlipDirection {
        CLOCKWISE,
        COUNTERCLOCKWISE
    }
    
    /**
     * Orientation of flipper - can be vertical or horizontal.
     *
     */
    public enum FlipperRepresentation {
        LEFT_VERTICAL,
        RIGHT_VERTICAL,
        TOP_HORIZONTAL,
        BOTTOM_HORIZONTAL
    }
    
    private final FlipperType type; 
    private final Vect rotationCenter;
    private final Vect otherCenter;
    private double currentAngle;
    private FlipperRepresentation clockwiseMostRepresentation;
    private FlipDirection currentDirection;
    
    /**
     * Creates a flipper.
     * 
     * @param board The board on which the flipper is.
     * @param name The name of this flipper.
     * @param location The grid location of the top left corner of the flipper. 
     * @param type The type of the flipper. Can be left or right.
     * @param orientation The orientation of the flipper. Can be 0,90,180,270
     */
    public Flipper(Board board, String name, GridLocation location, FlipperType type, Orientation orientation) {
        super(board, name, location, Constants.FLIPPER_REFLECTION_COEFF);
        this.orientation = orientation;
        this.type = type;    
        if(this.type.equals(FlipperType.LEFT)){
            currentDirection = FlipDirection.CLOCKWISE;
            switch(this.orientation){
            case ANGLE_0:{
                rotationCenter = location.toVect();
                otherCenter = location.toVect().plus(new Vect(0,Constants.FLIPPER_LENGTH));
                clockwiseMostRepresentation = FlipperRepresentation.LEFT_VERTICAL; 
                break;
            }
            case ANGLE_90:{
                rotationCenter = location.toVect().plus(new Vect(Constants.FLIPPER_LENGTH,0));
                otherCenter = location.toVect();
                clockwiseMostRepresentation = FlipperRepresentation.TOP_HORIZONTAL;
                break;
            }
            case ANGLE_180:{
                rotationCenter = location.toVect().plus(new Vect(Constants.FLIPPER_LENGTH,Constants.FLIPPER_LENGTH));
                otherCenter = location.toVect().plus(new Vect(Constants.FLIPPER_LENGTH,0));
                clockwiseMostRepresentation = FlipperRepresentation.RIGHT_VERTICAL;
                break;
            }
            default:{
                rotationCenter = location.toVect().plus(new Vect(0,Constants.FLIPPER_LENGTH));
                otherCenter = location.toVect().plus(new Vect(Constants.FLIPPER_LENGTH,Constants.FLIPPER_LENGTH));
                clockwiseMostRepresentation = FlipperRepresentation.BOTTOM_HORIZONTAL;
                break;
            }
            }
            currentAngle = 0.0;
        }
        else {
            currentDirection = FlipDirection.COUNTERCLOCKWISE;
            switch(this.orientation){
            case ANGLE_0:{
                rotationCenter = location.toVect().plus(new Vect(Constants.FLIPPER_LENGTH,0));
                otherCenter = location.toVect().plus(new Vect(Constants.FLIPPER_LENGTH,Constants.FLIPPER_LENGTH));
                clockwiseMostRepresentation = FlipperRepresentation.TOP_HORIZONTAL;
                break;
            }
            case ANGLE_90:{
                rotationCenter = location.toVect().plus(new Vect(Constants.FLIPPER_LENGTH,Constants.FLIPPER_LENGTH));
                otherCenter = location.toVect().plus(new Vect(0,Constants.FLIPPER_LENGTH));
                clockwiseMostRepresentation = FlipperRepresentation.RIGHT_VERTICAL;
                break;
            }
            case ANGLE_180:{
                rotationCenter = location.toVect().plus(new Vect(0,Constants.FLIPPER_LENGTH));
                otherCenter = location.toVect();
                clockwiseMostRepresentation = FlipperRepresentation.BOTTOM_HORIZONTAL;
                break;
            }
            default:{
                rotationCenter = location.toVect();
                otherCenter = location.toVect().plus(new Vect(Constants.FLIPPER_LENGTH,0));
                clockwiseMostRepresentation = FlipperRepresentation.LEFT_VERTICAL;
                break;
            }
            }
            currentAngle = Constants.FLIPPER_MAX_ROTATE;
        }
        
        collidables.add(new Line(rotationCenter, otherCenter, reflectionCoeff, rotationCenter));
        collidables.add(new FixedCircle(rotationCenter, 0, reflectionCoeff, rotationCenter));
        collidables.add(new FixedCircle(otherCenter, 0, reflectionCoeff, rotationCenter));
        assert(checkRep()); 
    }

    /**
     * Check the rep-invariant of this object
     * @return whether the rep is ok
     */
    public boolean checkRep() {
        return location.x() >= 0 && location.x() < board.getWidth() && 
               location.y() >= 0 && location.y() < board.getHeight() &&
               currentAngle >= 0 && currentAngle <= 90;
    }
    
    /**
     * Performs the action of this flipper.
     * 
     * The flipper rotates at a constant angular velocity 
     * of 1080 degrees per second in the direction opposite to
     * its current rotation direction, if its rotating. If its not
     * rotating, it rotates towards the position 90 degrees away from 
     * its current position.
     */
    @Override public void action() {
        if( currentDirection == FlipDirection.CLOCKWISE ) {
            currentDirection = FlipDirection.COUNTERCLOCKWISE;
        } else {
            currentDirection = FlipDirection.CLOCKWISE;
        }
        assert(checkRep());
    }
    
    /**
     * Evolve the flipper, flipping it in its current direction for the given time
     * @param time the time that the flipper will flip
     */
    @Override public void evolve(double time) {
        double angularVelocity;
        double nextAngle;
        
        if( currentDirection == FlipDirection.CLOCKWISE ) {
            nextAngle = currentAngle - Constants.FLIPPER_ANGULAR_VELOCITY * time;
            if( nextAngle > 0.0 ) {
                angularVelocity = Constants.FLIPPER_ANGULAR_VELOCITY;
            } else {
                angularVelocity = 0.0;
                nextAngle = 0.0;
            }
        } else {
            nextAngle = currentAngle + Constants.FLIPPER_ANGULAR_VELOCITY * time;
            if( nextAngle < Constants.FLIPPER_MAX_ROTATE ) {
                angularVelocity = - Constants.FLIPPER_ANGULAR_VELOCITY;
            } else {
                angularVelocity = 0.0;
                nextAngle = Constants.FLIPPER_MAX_ROTATE;
            }
        }
        
        for(Collidable collidable: collidables) {
            collidable.setRotating(angularVelocity);
            collidable.rotate(currentAngle - nextAngle);
        }
        currentAngle = nextAngle;
        assert(checkRep());
    }
        
    /**
     * Returns current direction of rotation of flipper
     * @return current direction of rotation
     */
    public FlipDirection getDirection(){
        assert(checkRep());
        return this.currentDirection;
    }

    /**
     * Returns a list of strings for the string representation of the flipper.
     * 
     * Grid representation of the flipper is "|" if its closer to the vertical,
     * it is "-" if its closer to the horizontal.
     * 
     * @return The grid representation.
     */
    @Override public List<String> gridRepresentation() {
        assert(checkRep());
        if (this.currentAngle < Constants.FLIPPER_MAX_ROTATE/2.0) {
            switch(this.clockwiseMostRepresentation) {
            case BOTTOM_HORIZONTAL: return Arrays.asList("  ","--");
            case TOP_HORIZONTAL: return Arrays.asList("--","  ");
            case RIGHT_VERTICAL: return Arrays.asList(" |"," |");
            default: return Arrays.asList("| ","| ");
            }
        }
        else {
            switch(this.clockwiseMostRepresentation) {
            case BOTTOM_HORIZONTAL: return Arrays.asList("| ","| ");
            case TOP_HORIZONTAL: return Arrays.asList(" |"," |");
            case RIGHT_VERTICAL: return Arrays.asList("  ","--");
            default: return Arrays.asList("--","  ");
            }
        }
    }
    
    /**
     * Returns the current angle of the flipper from the clockwisemost position of the flipper,
     * measured in counter-clockwise direction in radians.
     * 
     * Always between 0 and Math.pi/2
     * 
     * @return the angle
     */
    public Double getCurrentAngle(){
        assert(checkRep());
        return this.currentAngle;
    }
    
    /**
     * Returns the type of the flipper
     * 
     * @return the type of the flipper
     */
    public FlipperType getType() {
        return this.type;
    }
    
    private Line getLine() {
        for(Collidable collidable:collidables){
            if (collidable instanceof Line) return (Line) collidable;
        }
        return null;
    }
    
    /**
     * Returns the flipper's data in a list.
     * Includes the location of its two end points, and the trigger state.
     */
    @Override
    public Pair<GameObjectType, List<Object>> getObjectData() {
        Line line = this.getLine();
        Pair<Vect, Vect> ends = line.getEnds();
        Pair<Double, Double> end1 = Pair.of(ends.getFirst().x(),ends.getFirst().y());
        Pair<Double, Double> end2 = Pair.of(ends.getSecond().x(),ends.getSecond().y());
        List<Object> objData = new ArrayList<Object>(Arrays.asList(end1,end2,this.triggerState));
        return Pair.of(GameObjectType.FLIPPER, objData);
    }
}
