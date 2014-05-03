package pingball.datastructures;

/**
 * Enum to describe the orientation of a user to another
 * user. The Enums are immutable and strictly defined to the set below.
 *
 * @author enguyen, nconsul, spefley
 *
 */
public enum Side {
    LEFT, RIGHT, TOP, BOTTOM;

    /**
     * Converts a String into an enum if it is a String
     * used in this enum's toString method.
     *
     * @param s the String
     * @return an enum of type Side, or throw an IllegalArgumentException
     */
    public static Side toSide(String s) {
        switch(s) {
        case "left":
            return LEFT;
        case "right":
            return RIGHT;
        case "top":
            return TOP;
        case "bottom":
            return BOTTOM;
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * The enum as a String.
     *
     * @return a lowercase String representation of the enum
     */
    @Override
    public String toString() {
        switch(this) {
        case LEFT:
            return "left";
        case RIGHT:
            return "right";
        case TOP:
            return "top";
        case BOTTOM:
            return "bottom";
        default:
            throw new IllegalArgumentException();
        }
    }
}
