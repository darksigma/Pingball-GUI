package pingball.physics;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import pingball.datastructures.Side;

/**
 * OuterWalls is a special type of Gadget that is used to monitor reflections
 * of balls off of the outer boundaries of the playing field. This class also
 * handles monitoring of other boards connected to the board this gadget is in.
 * @author Erik
 *
 */
class OuterWalls extends Gadget {

    /* OuterWalls is a special gadget that follows all of Gadget's rep invariant
     * except for the naming convention, where it may contain characters other
     * than word characters. This allows creation of internal walls that are
     * not known to the users but will not pollute the naming space of user
     * known gadgets
     *
     * Gadget Rep Invariant: The bounding box of a gadget must allow it to
     * exist completely within the boundary of the board it is in. The name must
     * also consist of a letter or underscore followed optionally by more
     * word characters as defined by Pattern.java. Lastly, the gadget must be
     * two dimensional. This can be enumerated as follows:
     *  1) 0 <= x < 20, 0 < width, x + width <= 20
     *  2) 0 <= y < 20, 0 < height, y + height <= 20
     */

    /**
     * Stores the names of all connected boards along with the side they are on
     */
    private final String[] connectedBoards = new String[Side.values().length];

    private final double ulp;

    /**
     * Container for the line segments that make up the inner boundary of the
     * board
     */
    private final LineSegment[] innerLines;

    //The actual lines of the inner boundary
    private final LineSegment top;
    private final LineSegment bottom;
    private final LineSegment left;
    private final LineSegment right;

    /**
     * Creates a new set of outer walls for the specified board. By default,
     * the location of the walls are at (0, 0), and this represents the inner
     * boundary of the walls (as these are the ones that will be bounced off of.
     * @param gameBoard the board that this set of walls part of
     * @param name the name of this gadget
     */
    public OuterWalls(Board gameBoard, String name) {
        super(gameBoard, name, 0, 0);
        this.width = 20;
        this.height = 20;
        this.ulp = Math.ulp(width + 0.0);

        //set everything up for empty strings
        for(int i = 0; i < connectedBoards.length; i++) {
            connectedBoards[i] = "";
        }

        this.top = new LineSegment(x, y, x + width, y);
        this.bottom = new LineSegment(x, y + height, x + width, y + height);
        this.left = new LineSegment(x, y, x, y + height);
        this.right = new LineSegment(x + width, y, x + width, y + height);

        this.innerLines = new LineSegment[Side.values().length];
        innerLines[Side.TOP.ordinal()] = top;
        innerLines[Side.BOTTOM.ordinal()] = bottom;
        innerLines[Side.LEFT.ordinal()] = left;
        innerLines[Side.RIGHT.ordinal()] = right;
    }

    @Override
    public void startAction() {
        //walls aren't associated with any actions
    }

    @Override
    public void action(double dt) {
        //walls aren't associated with any actions
    }

    @Override
    void handleCollision(Ball ball) {
        Vect center = ball.getCenterAsVect();
        //project the ball on the inner sides of the gadget
        final Vect tProj = Geometry.perpendicularPoint(top, center);
        final Vect bProj = Geometry.perpendicularPoint(bottom, center);
        final Vect lProj = Geometry.perpendicularPoint(left, center);
        final Vect rProj = Geometry.perpendicularPoint(right, center);

        //calculate the distances between the projections and the actual centerr
        final double tDist = tProj == null ? Double.POSITIVE_INFINITY : Geometry.distanceSquared(tProj, center);
        final double bDist = bProj == null ? Double.POSITIVE_INFINITY : Geometry.distanceSquared(bProj, center);
        final double lDist = lProj == null ? Double.POSITIVE_INFINITY : Geometry.distanceSquared(lProj, center);
        final double rDist = rProj == null ? Double.POSITIVE_INFINITY : Geometry.distanceSquared(rProj, center);

        //figure out which side is closest
        Side closest = Side.TOP;
        double minDist = tDist;
        if(bDist < minDist) {
            closest = Side.BOTTOM;
            minDist = bDist;
        }
        if(lDist < minDist) {
            closest = Side.LEFT;
            minDist = lDist;
        }
        if(rDist < minDist) {
            closest = Side.RIGHT;
            minDist = rDist;
        }

        //store the data about the closest side locally
        String boardName = connectedBoards[closest.ordinal()];
        LineSegment side = innerLines[closest.ordinal()];


        if(boardName.equals("")) { //regular wall
            handleCollision(side, ball);
            return;
        }

        //tell the ball that it's gonna get passed outside of the board
        //and not to worry about position values too much
        ball.setBeingPassedFlagTrue();

        //we've hit a pass through wall, make sure the ball is past the boundary
        //by an ulp
        if(closest == Side.TOP && center.y() > -ulp) {
            ball.setCenter(new Vect(center.x(), -ulp));
        } else if(closest == Side.BOTTOM && center.y() < height + ulp) {
            ball.setCenter(new Vect(center.x(), height + ulp));
        } else if(closest == Side.LEFT && center.x() > -ulp) {
            ball.setCenter(new Vect(-ulp, center.y()));
        } else if(closest == Side.RIGHT && center.x() < width + ulp) {
            ball.setCenter(new Vect(width + ulp, center.x()));
        }

        gameBoard.sendBallToServer(ball, closest);
        checkRep();
    }

    @Override
    double calculateCollisionTimeWithBall(Ball ball) {
        double time = Double.POSITIVE_INFINITY;
        /*       for(Side side : Side.values()){
                   String connectedBoard = connectedBoards[side.ordinal()];
                   LineSegment innerSide = innerLines[side.ordinal()];
                   if(connectedBoard.equals("")){//plain wall
                       double collisionTime = Geometry.timeUntilWallCollision(innerSide,
                               ball.asCircle(), ball.getVelocityVector());
                       time = Math.min(time, collisionTime);
                   } else {//connecting wall
                       //this gives the time when the center first reaches the wall
                       Circle pointBall = new Circle(ball.getCenter(), 0);
                       double collisionTime = Geometry.timeUntilWallCollision(innerSide,
                               pointBall, ball.getVelocityVector());
                       time = Math.min(time, collisionTime);
                   }
               }
        */
        for(Side side : Side.values()) {
            String connectedBoard = connectedBoards[side.ordinal()];
            Vect velocity = ball.getVelocityVector();
            Vect center = ball.getCenterAsVect();
            double radius = ball.getRadius();
            if(side == Side.TOP && velocity.y() < 0) { // moving towards top wall
                // "" == plain wall
                //Where the center of the ball needs to go such that the ball
                //is JUST before colliding with the wall or passing through to
                //another board. Ensures that floating point error doesn't shove
                //the ball past it's target
                double centerTarget = (connectedBoard.equals("") ? radius : 0 );
                double t = (centerTarget - center.y()) / velocity.y();

                time = Math.min(time, t);
            } else if (side == Side.BOTTOM && velocity.y() > 0) { // moving towards the bottom
                //Center of the ball either is just before crossing the bottom
                //wall or before the ball pokes through the wall
                double centerTarget = 20 - (connectedBoard.equals("") ? radius : 0);
                double t = (centerTarget - center.y()) / velocity.y();

                time = Math.min(time, t);
            } else if (side == Side.LEFT && velocity.x() < 0) { //moving left
                double centerTarget = (connectedBoard.equals("") ? radius : 0);
                double t = (centerTarget - center.x()) / velocity.x();

                time = Math.min(time, t);
            } else if (side == Side.RIGHT && velocity.x() > 0) { //moving right
                double centerTarget = 20 - (connectedBoard.equals("") ? radius : 0);
                double t = (centerTarget - center.x()) / velocity.x();
                time = Math.min(time, t);
            }
        }
        return time;
    }

    @Override
    public void putInDisplay(char[][] board, int hOffset, int vOffset) {
        //setup the basic walls first
        for(int i = 0; i < board.length; i++) {
            board[i][0] = board[i][board[0].length - 1] = '.';
        }
        for(int j = 0; j < board[0].length; j++) {
            board[0][j] = board[board.length - 1][j] = '.';
        }

        for(Side side : Side.values()) {
            //there's a board connected
            if(!connectedBoards[side.ordinal()].equals("")) {
                String name = connectedBoards[side.ordinal()];
                //max number of characters written
                int maxLength = 20;
                int startLoc = 0;
                if(side == Side.TOP || side == Side.BOTTOM) {
                    maxLength = Math.min(name.length(), width);
                    //center the string
                    startLoc = (width - maxLength) / 2;
                } else {
                    maxLength = Math.min(name.length(), height);
                    //center the string
                    startLoc = (height - maxLength) / 2;
                }

                //place the name label in
                if(side == Side.TOP || side == Side.BOTTOM) {
                    int row = side == Side.TOP ? 0 : board.length - 1;
                    for(int j = 0; j < maxLength; j++) {
                        board[row][j + startLoc + hOffset] = name.charAt(j);
                    }
                } else if(side == Side.LEFT || side == Side.RIGHT) {
                    int col = side == Side.LEFT ? 0 : board[0].length - 1;
                    for(int i = 0; i < maxLength; i++) {
                        board[i + startLoc + vOffset][col] = name.charAt(i);
                    }
                }
            }
        }
    }

    @Override
    public String stateDescription() {
        return "OuterWalls(x=0, y=0)";
    }

    /**
     * Stores the name of the connected board with the side it's connected to
     * @param boardName the name of the connected board
     * @param side the side the connected board is on
     * @return the name of the board previously connected on the specified side,
     *      empty if no board was there
     */
    public String connectBoard(String boardName, Side side) {
        String prevBoard = connectedBoards[side.ordinal()];
        connectedBoards[side.ordinal()] = boardName;
        return prevBoard;
    }

    /**
     * Disconnects the board specified by boardName from this board, if the
     * specified board isn't connected to this board, nothing happens
     * @param boardName the name of the board to be disconnected
     * @return the name of the disconnected board, or an empty string if no board
     *      was disconnected
     */
    public String disconnectBoard(String boardName) {
        for(int i = 0; i < connectedBoards.length; i++) {
            if(connectedBoards[i].equals(boardName)) {
                connectedBoards[i] = "";
                return boardName;
            }
        }
        return "";
    }

    /**
     * Tweeks the check rep specifically for this special gadget for board
     */
    @Override protected boolean checkRep() {
        assert x > -1 && y > -1 && x + width <= 20 && y + height <= 20;
        assert width > 0;
        assert height > 0;
        return true;
    }
}
