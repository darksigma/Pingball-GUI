package warmup;

import physics.Vect;

/**
 * Board is a class that creates instances of Pingball boards which contain BoardObject objects (bumpers,
 * balls,flippers, absorbers, and empty spaces).
 *
 * @author enguyen, nconsul, spefley
 *
 */

public class Board {
    private final BoardObject[][] board;

    private final int x;
    private final int y;

    private double bX;
    private double bY;

    private final Empty e;

    /**
     * Constructor that creates a new Board with given height and width. Each cell in the board
     * is empty and contains an Empty object. The ball starts off the board at the top left corner.
     * @param x represents the width of the board
     * @param y represents the height of the board
     */
    public Board(int x, int y) {
        this.x = x;
        this.y = y;
        e = new Empty();
        board = new BoardObject[x][y];
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                board[i][j] = e;
            }
        }
        bX = -1;
        bY = -1;
    }

    /**
     * Constructor that creates a new Board with a given height and width, with a Ball at a given location
     * on the Board. All spaces in the board without the ball are empty, with an Empty object.
     * @param x represents the width of the board
     * @param y represents the height of the board
     * @param b represents the Ball that is in the board
     * @param bX represents the x-coordinate of the ball in the board
     * @param bY represents the y-coordinate of the ball in the board
     */
    public Board(int x, int y, Ball b, double bX, double bY) {
        this.x = x;
        this.y = y;
        this.bX = bX;
        this.bY = bY;
        e = new Empty();
        board = new BoardObject[x][y];
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                if(i == bX && j == bY) {
                    board[i][j] = b;
                } else {
                    board[i][j] = e;
                }
            }
        }
    }

    /**
     * Method that determines whether a cell in the board is a part of the wall/edge. The edge is
     * defined as being outside the board's dimensions, bordering the board.
     * @param x represents the x-coordinate of the cell in the board
     * @param y represents the y-coordinate of the cell in the board
     * @return true if the cell is a part of the edge, false if the cell is a part of the board.
     */
    public boolean isEdge(int x, int y) {
        return (x == -1 || x == this.x || y == -1 || y == this.y);
    }

    int bXrounded;
    int bYrounded;
    double newbX;
    double newbY;
    boolean reflected = false;

    /**
     * Updates the location of the ball in the board by taking the velocity and time into account. Each
     * of the coordinates are updated by the formula: newCoordinate = prevCoordinate + velocity * time.
     * If the new coordinate values are not integers, they are rounded down. If the ball bounces off a
     * wall, the boolean reflected is updated to true.
     * @param dt represents the amount of time that passes before the ball updates its position.
     */
    public void update(double dt) {
        reflected = false;
        Ball ball = (Ball) board[(int)Math.round(bX)][(int)Math.round(bY)];

        Vect v = ball.velocity();
        newbX = bX + v.x() * dt;
        newbY = bY + v.y() * dt;
        bXrounded = (int)Math.round(newbX);
        bYrounded = (int)Math.round(newbY);
        if(bYrounded > y - 1 || bYrounded < 0) {
            ball.reflectY();
            reflected = true;
        }
        if(bXrounded > x - 1 || bXrounded < 0) {
            ball.reflectX();
            reflected = true;
        }
        if(!reflected) {
            board[(int)Math.round(bX)][(int)Math.round(bY)] = e;
            bX = newbX;
            bY = newbY;
            board[bXrounded][bYrounded] = ball;
        } else {
            board[(int)Math.round(bX)][(int)Math.round(bY)] = ball;
        }
    }

    /**
     * Returns the String representation of the Board. All edges are represented by "." and all cells in
     * the board are represented by the String representation of the respective BoardObject in each cell.
     * If a cell contains an Empty Object, it is represented by an empty String.
     */
    public String toString() {
        String str = "";
        for(int j = -1; j < y + 1; j++) {
            for(int i = -1; i < x + 1; i++) {
                if(isEdge(i, j)) {
                    str += ".";
                } else if(i > -1 && i < x && j > -1 && j < y) {
                    str += board[i][j].toString();
                }
            }
            if(j != y) {
                str += "\n";
            }
        }

        return str;
    }
}
