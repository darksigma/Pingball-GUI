package warmup;

/**
 * Empty class is a subclass of BoardObject that creates instances of empty spaces. Cells of the
 * pingball board that do not contain other BoardObject objects contain an Empty object.
 *
 * @author enguyen, nconsul, spefley
 *
 */

public class Empty extends BoardObject {

    /**
     * Returns the String representation of an empty cell in the board, represented by " ".
     * @return " "
     */
    public String toString() {
        return " ";
    }
}
