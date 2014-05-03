package pingball.tests;

import static org.junit.Assert.*;

import java.util.concurrent.LinkedBlockingQueue;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.Vect;
import pingball.datastructures.Pair;
import pingball.datastructures.Side;
import pingball.physics.Absorber;
import pingball.physics.Ball;
import pingball.physics.Board;
import pingball.physics.CircleBumper;
import pingball.physics.Flipper;
import pingball.physics.Orientation;
import pingball.physics.SquareBumper;
import pingball.physics.TriangularBumper;

/**
 * Testing strategy for the physics related classes {Board, all Gadgets,
 * and Ball}.
 *
 * Testing accuracy of the physics engine for pingball is close to impossible
 * to achieve using complete coverage due the the infinite possibilities of
 * arrangements of objects on the board as well as board settings like friction
 * and gravity, number of balls, initial locations of said balls, etc. As such
 * the testing suite will focus on ensuring that each independent gadget
 * behaves as expected (if it should rotate, triggering it to rotate will
 * change it's state, if it shouldn't do anything, triggering it shouldn't
 * change it's state), and is constructed as expected. This includes tests
 * on the displays generated from the Board itself. Then a higher level
 * set of tests will make sure that simple physical systems (i.e. ball-ball
 * collisions, ball-gadget collisions, board updates etc.) work both as
 * expected and produce results that are within a small tolerance range to
 * results calculated by hand (e.g. the math shouldn't have lots of error in
 * computations).
 *
 * Construction Test Sets:
 *      All of the below test sets must have observer functions that produce
 *      expected results
 *      1) Absorber - make sure that construction is correct and size can be
 *          variable
 *      2) Flipper - make sure that initial states are correct along with
 *          orientation (both left and right flippers)
 *      3) CircleBumper - make sure that this can be created as expected
 *      4) SquareBumper - make sure that this can be created as expected
 *      5) TriangularBumper - make sure that this can be created as expected,
 *          with different orientations
 *      6) Ball - make sure that construction of a ball is fine and that it
 *          has the correct settings
 *      7) Board - make sure that construction of a board, with different sets
 *          of internal gadgets works as expected and that the output state is
 *          as expected. @see BoardGrammarTests.java
 *
 * State Descriptions:
 *      1) make sure that all gadget state descriptions are accurate regardless
 *          of connection/disconnections, and location
 *      2) make sure that determining points interior to a shape is accurate
 *      3) make sure that the list of line segments that defines the shapes
 *          of the gadgets is accurate
 *
 * Gadget actions:
 *      1) make sure that all bumpers do not change state when they are
 *          triggered
 *      2) make sure that Flipper can rotate properly as expected (left flipper
 *          goes counter clockwise, right goes clockwise)
 *      3) make sure that an Absorber triggers properly and fires a ball only
 *          if it has one and no ball is moving through it
 *      4) make sure that gadgets can be connected and disconnected properly
 *      5) make sure that connected gadgets get fired together
 *          - special case: make sure that a self connected absorber fires the
 *          captured ball when it is hit and has no other balls in it
 *
 * Physics Collisions tests:
 *      1) Moving Line - a ball needs to be able to bounce off of a rotating
 *          line segment (e.g. a moving flipper)
 *      2) Moving circle - two balls need to be able to collide and move away
 *          from the collision with the expected direction and velocities.
 *          This also includes tests with hitting an end of a flipper that is
 *          rounded.
 * Stationary tests:
 *      - make sure that a ball that is sitting on an object with 0 velocity
 *      doesn't move.
 *
 * Visual tests in terminal that cannot be easily JUnit-tested:
 * 		We observed the motion of the ball(s) in several different board game files.
 * We did this by looking at printed frame-by-frame boards in Eclipse, as well as through
 * observing the real-time motion of the ball(s) through different files.
 * 		When observing the trajectory of the ball in an empty board, we see that it
 * has a parabolic trajectory as it moves, which reflects the effects of gravity, and with
 * decreasing energy which shows the friction based effects of mu, and mu2,
 * accurately representing how a ball would move through the air in reality.
 * 		In files with multiple gadgets, we observe that the ball's angle of incidence
 * and reflection seem about equal when the ball bounces off walls and bumpers. When the balls
 * collide with flippers, we observe the balls reflecting off in varied directions due to the
 * flipper's shape and high rotational velocity. When the balls collide with an absorber, they
 * are sucked in and later ejected (when triggered to) at a high velocity, straight up. This
 * accurately reflects the physics of the balls' collisions with flippers and absorbers. When
 * balls collide with other balls, they bounce off each other in varied directions at varied
 * velocities depending on the angle and speed of approach. This accurately reflects
 * collisions between objects with different velocities and angles of approach in real life.
 * 		Overall, through observing frame-by-frame outputs and real-time display of the board,
 * the balls' motion trajectories, changes in speed, and changes in direction imply that our
 * physics is accurate. This is further supported through the JUnit tests below.
 *
 * @author Erik, Natasha
 *
 */
public class PhysicsTests {

    //dummy queue for board construction
    static LinkedBlockingQueue<Pair<Ball, Pair<String, Side>>> queue;

    // dummy board so that gadgets can be made
    static Board board;

    @BeforeClass public static void setup() {
        queue = new LinkedBlockingQueue<>();
        board = new Board("testBoard", 25, .025, .025, queue);
    }

    ////////////////////////Construction tests/////////////////////////////////

    /**
     * Test to see if creation of an {@code Absorber} is okay, and that all
     * parameters are as expected for the {@code Absorber}.
     */
    @Test public void testAbsorberConstruction() {
        int x = 2, y = 3, width = 5, height = 10;
        double defaultReflectionCoeff = 0.0;
        double toleratedError = 0;
        Absorber ab = new Absorber(board, "absorber", x, y, width, height);

        assertEquals("absorber", ab.getName());
        assertEquals(x, ab.getX());
        assertEquals(y, ab.getY());
        assertEquals(width, ab.getWidth());
        assertEquals(height, ab.getHeight());
        assertEquals(defaultReflectionCoeff, ab.getReflectionCoeff(), toleratedError);
        assertEquals("=", ab.toString());
    }

    /**
     * Test to see if creation of a left {@code Flipper} is okay, and that all
     * parameters are as expected for the {@code Flipper}. This checks for all
     */
    @Test public void testLeftFlipperConstruction() {
        int x = 0, y = 0;
        boolean isLeftFlipper = true;
        int defaultFlipperDim = 2; // bounding box of a flipper is a 2 x 2 box

        for(Orientation orientation : Orientation.values()) {
            Flipper leftFlipper = new Flipper(board, "lFlipper", x, y, orientation, isLeftFlipper);

            //A left flipper is 90 degrees from the minimum angle that it can
            //assume from the horizontal. Angle is defined on 0 to 360
            double flipperAngle = (orientation.angle() + 90) % 360;

            double defaultReflectionCoeff = .95;
            double toleratedError = 1e-14;

            assertEquals("lFlipper", leftFlipper.getName());
            assertEquals(x, leftFlipper.getX());
            assertEquals(y, leftFlipper.getY());
            assertEquals(defaultFlipperDim, leftFlipper.getWidth());
            assertEquals(defaultFlipperDim, leftFlipper.getHeight());
            assertEquals(flipperAngle, leftFlipper.getAngle(), toleratedError);
            assertEquals(defaultReflectionCoeff, leftFlipper.getReflectionCoeff(),
                         toleratedError);
            assertTrue(leftFlipper.isLeftFlipper());
            assertEquals(orientation, leftFlipper.getOrientation());
        }
    }

    @Test public void testRightFlipperConstruction() {
        int x = 0, y = 0;
        boolean isLeftFlipper = false;
        int defaultFlipperDim = 2; // bounding box of a flipper is a 2 x 2 box

        for(Orientation orientation : Orientation.values()) {
            Flipper rightFlipper = new Flipper(board, "rFlipper", x, y, orientation, isLeftFlipper);

            //A right flipper is at the minimum angle that it can
            //assume from the horizontal. Angle is defined on 0 to 360
            //but is still 90 degrees from the base orientation given
            double flipperAngle = (orientation.angle() + 90) % 360;
            double defaultReflectionCoeff = .95;
            double toleratedError = 1e-14;

            assertEquals("rFlipper", rightFlipper.getName());
            assertEquals(x, rightFlipper.getX());
            assertEquals(y, rightFlipper.getY());
            assertEquals(defaultFlipperDim, rightFlipper.getWidth());
            assertEquals(defaultFlipperDim, rightFlipper.getHeight());
            assertEquals(flipperAngle, rightFlipper.getAngle(), toleratedError);
            assertEquals(defaultReflectionCoeff, rightFlipper.getReflectionCoeff(),
                         toleratedError);
            assertFalse(rightFlipper.isLeftFlipper());
            assertEquals(orientation, rightFlipper.getOrientation());
        }
    }

    @Test public void testCircularBumperConstruction() {
        double expectedRadius = 0.5;
        double reflectionCoeff = 1.0;
        double toleratedError = 1e-14;
        Vect expectedCenter = new Vect(4.5, 5.5);
        int x = 4, y = 5, width = 1, height = 1;
        CircleBumper bumper = new CircleBumper(board, "circ", x, y);

        assertEquals("circ", bumper.getName());
        assertEquals(x, bumper.getX());
        assertEquals(y, bumper.getY());
        assertEquals(width, bumper.getWidth());
        assertEquals(height, bumper.getHeight());
        assertEquals(expectedRadius, bumper.getRadius(), toleratedError);
        assertEquals(reflectionCoeff, bumper.getReflectionCoeff(), toleratedError);
        assertEquals(expectedCenter, bumper.getCenter());
        assertEquals("O", bumper.toString());
    }

    @Test public void testSquareBumperConstruction() {
        double reflectionCoeff = 1.0;
        double toleratedError = 1e-14;
        int x = 4, y = 5, width = 1, height = 1;
        SquareBumper bumper = new SquareBumper(board, "sq", x, y);

        assertEquals("sq", bumper.getName());
        assertEquals(x, bumper.getX());
        assertEquals(y, bumper.getY());
        assertEquals(width, bumper.getWidth());
        assertEquals(height, bumper.getHeight());
        assertEquals(reflectionCoeff, bumper.getReflectionCoeff(), toleratedError);
        assertEquals("#", bumper.toString());
    }

    @Test public void testTriangularBumperConstruction() {
        double reflectionCoeff = 1.0;
        double toleratedError = 1e-14;
        int x = 4, y = 5, width = 1, height = 1;

        for(Orientation orientation : Orientation.values()) {
            TriangularBumper bumper = new TriangularBumper(board, "tri", x, y, orientation);

            assertEquals("tri", bumper.getName());
            assertEquals(x, bumper.getX());
            assertEquals(y, bumper.getY());
            assertEquals(width, bumper.getWidth());
            assertEquals(height, bumper.getHeight());
            assertEquals(reflectionCoeff, bumper.getReflectionCoeff(), toleratedError);
            assertEquals(orientation, bumper.getOrientation());
            //The orientation of a bumper affects it's output shape
            if(orientation == Orientation._0 || orientation == Orientation._180) {
                assertEquals("/", bumper.toString());
            } else {
                assertEquals("\\", bumper.toString());
            }
        }
    }

    /**
     * The first constructor accepts very few arguments and uses default values
     * for many parameters
     */
    @Test public void testBallConstruction1() {
        int x = 5, y = 2;
        double g = 22;
        double expectedRadius = 0.25;
        double toleratedError = 1e-14;
        Ball ball = new Ball("ball",
                             0, 0, x, y, g, .025, .025);

        assertEquals("ball", ball.getName());
        assertEquals(new Vect(x, y), ball.getPositionVector());
        //This ball has no velocity
        assertEquals(new Vect(0, 0), ball.getVelocityVector());
        assertEquals(expectedRadius, ball.getRadius(), toleratedError);
        assertEquals(g, ball.getGravity(), toleratedError);
        assertEquals("*", ball.toString());
    }

    /**
     * The second constructor accepts more arguments and uses defaults values
     * for the radius
     */
    @Test public void testBallConstruction2() {
        double x = 2.3, y = 1.1, g = 24, expectedRadius = 0.25;
        double vx = 1.1, vy = -.22;
        double toleratedError = 1e-14;

        Ball ball = new Ball("ball2", vx, vy, x, y, g, .025, .025);

        assertEquals("ball2", ball.getName());
        assertEquals(new Vect(x, y), ball.getPositionVector());
        assertEquals(new Vect(vx, vy), ball.getVelocityVector());
        assertEquals(expectedRadius, ball.getRadius(), toleratedError);
        assertEquals(g, ball.getGravity(), toleratedError);
        assertEquals("*", ball.toString());
    }

    /**
     * The last constructor enables creation of the most variable balls and
     * accepts values for all parameters.
     */
    @Test public void testBallConstruction3() {
        double x = 0.0, y = 20.0, g = 1.2, diameter = 1.2, radius = diameter/2;
        double vx = -3.1, vy = 200;
        double toleratedError = 1e-14;

        Ball ball = new Ball("ball3", vx, vy, x, y, diameter, g, .025, .025);

        assertEquals("ball3", ball.getName());
        assertEquals(new Vect(x, y), ball.getPositionVector());
        assertEquals(new Vect(vx, vy), ball.getVelocityVector());
        assertEquals(radius, ball.getRadius(), toleratedError);
        assertEquals(g, ball.getGravity(), toleratedError);
        assertEquals("*", ball.toString());

    }

    @Test
    public void testSquareBumperPostTrigger() {
        double reflectionCoeff = 1.0;
        double toleratedError = 1e-14;
        int x = 4, y = 5, width = 1, height = 1;
        SquareBumper bumper = new SquareBumper(board, "sq", x, y);

        Ball ball = new Ball("ball", 0, 0, 1.0, 1.0, 25.0, .025, .025);

        bumper.trigger(ball);

        assertEquals(x, bumper.getX());
        assertEquals(y, bumper.getY());
        assertEquals(width, bumper.getWidth());
        assertEquals(height, bumper.getHeight());
        assertEquals(reflectionCoeff, bumper.getReflectionCoeff(), toleratedError);
        assertEquals("#", bumper.toString());

    }

    @Test
    public void testCircularBumperPostTrigger() {
        double expectedRadius = 0.5;
        double reflectionCoeff = 1.0;
        double toleratedError = 1e-14;
        Vect expectedCenter = new Vect(4.5, 5.5);
        int x = 4, y = 5, width = 1, height = 1;
        CircleBumper bumper = new CircleBumper(board, "circ", x, y);

        Ball ball = new Ball("ball", 0, 0, 1.0, 1.0, 25.0, .025, .025);

        bumper.trigger(ball);

        assertEquals(x, bumper.getX());
        assertEquals(y, bumper.getY());
        assertEquals(width, bumper.getWidth());
        assertEquals(height, bumper.getHeight());
        assertEquals(expectedRadius, bumper.getRadius(), toleratedError);
        assertEquals(reflectionCoeff, bumper.getReflectionCoeff(), toleratedError);
        assertEquals(expectedCenter, bumper.getCenter());
        assertEquals("O", bumper.toString());
    }

    @Test
    public void testTriangularBumperPostTrigger() {
        double reflectionCoeff = 1.0;
        double toleratedError = 1e-14;
        int x = 4, y = 5, width = 1, height = 1;

        Ball ball = new Ball("ball", 0, 0, 1.0, 1.0, 25.0, .025, .025);

        for(Orientation orientation : Orientation.values()) {
            TriangularBumper bumper = new TriangularBumper(board, "tri", x, y, orientation);

            bumper.trigger(ball);

            assertEquals(x, bumper.getX());
            assertEquals(y, bumper.getY());
            assertEquals(width, bumper.getWidth());
            assertEquals(height, bumper.getHeight());
            assertEquals(reflectionCoeff, bumper.getReflectionCoeff(), toleratedError);
            assertEquals(orientation, bumper.getOrientation());
            //The orientation of a bumper affects it's output shape
            if(orientation == Orientation._0 || orientation == Orientation._180) {
                assertEquals("/", bumper.toString());
            } else {
                assertEquals("\\", bumper.toString());
            }
        }
    }

    /*@Test
    public void testLeftFlipperPostTrigger(){
    	int x = 0, y = 0;
        boolean isLeftFlipper = true;
        int defaultFlipperDim = 2; // bounding box of a flipper is a 2 x 2 box

        Ball ball = new Ball("ball", 0, 0, 1.0, 1.0, 25.0, .025, .025);

        for(Orientation orientation : Orientation.values()){
        	Flipper leftFlipper = new Flipper(board, "lFlipper", x, y, orientation, isLeftFlipper);

            //A left flipper is 90 degrees from the minimum angle that it can
            //assume from the horizontal. Angle is defined on 0 to 360
            double flipperAngle = (orientation.angle() + 90) % 360;


            leftFlipper.trigger(ball);
            System.out.println(leftFlipper.getAngle());
            System.out.println(flipperAngle);
           // assertTrue(flipperAngle == leftFlipper.getAngle() - 90);
            }


        }*/

    @Test
    public void testAbsorberWithPostTriggerAction() {
        int x = 2, y = 3, width = 5, height = 10;

        Absorber ab = new Absorber(board, "absorber", x, y, width, height);

        Ball b1 = new Ball("ball1", 0, 0, 1.0, 1.0, 25.0, .025, .025);
        Ball b2 = new Ball("ball2", 0, 0, 1.0, 1.0, 25.0, .025, .025);

        ab.trigger(b1);

        //absorbs ball1, should set position to bottom right corner and velocity to 0
        Vect b1pos = new Vect(x + width - b1.getRadius(), y + height - b1.getRadius());
        Vect b1vel = new Vect(0, 0);
        assertEquals(b1pos, b1.getCenterAsVect());
        assertEquals(b1vel, b1.getVelocityVector());

        ab.trigger(b2);

        //ejects ball1, shoudl set velocity to the default ejection velocity
        Vect b1velUpdated = Board.DEFAULT_ABSORBER_EJECTION_VELOCITY;
        assertEquals(b1velUpdated, b1.getVelocityVector());

        Vect b2pos = new Vect(x + width - b2.getRadius(), y + height - b2.getRadius());
        Vect b2vel = new Vect(0,0);
        assertEquals(b2pos, b2.getCenterAsVect());
        assertEquals(b2vel, b2.getVelocityVector());

    }

    @Test
    public void testAbsorberWithoutPostTriggerAction() {
        int x = 5, y = 0, width = 5, height = 10;
        Absorber ab = new Absorber(board, "absorber", x, y, width, height);

        Ball b1 = new Ball("ball1", 0, 0, 1.0, 1.0, 25.0, .025, .025);
        Ball b2 = new Ball("ball2", 1, 1, 1.0, 1.0, 25.0, .025, .025);

        ab.trigger(b1);

        //absorbs ball1, should set position to bottom left corner and velocity to 0
        Vect b1pos = new Vect(x + width - b1.getRadius(), y + height - b1.getRadius());
        Vect b1vel = new Vect(0, 0);
        assertEquals(b1pos, b1.getCenterAsVect());
        assertEquals(b1vel, b1.getVelocityVector());

        ab.trigger(b2);

        //doesn't eject ball1, position remains same
        //velocity of ball1 changes to default ejection velocity from attempted ejection
        Vect b1velUpdated = Board.DEFAULT_ABSORBER_EJECTION_VELOCITY;
        assertEquals(b1pos, b1.getCenterAsVect());
        assertEquals(b1velUpdated, b1.getVelocityVector());

    }

    @Test
    public void testConnectAndDisconnectGadgets() {

        int sqx = 4, sqy = 5;
        SquareBumper sqbumper = new SquareBumper(board, "sq", sqx, sqy);

        int cirx = 4, ciry = 5;
        CircleBumper cirbumper = new CircleBumper(board, "circ", cirx, ciry);

        int trix = 4, triy = 5;
        TriangularBumper tribumper = new TriangularBumper(board, "tri", trix, triy, Orientation.valueOf("_0"));

        int abx = 2, aby = 3, abwidth = 5, abheight = 10;
        Absorber ab = new Absorber(board, "absorber", abx, aby, abwidth, abheight);

        int x = 0, y = 0;
        boolean isLeftFlipper = true;
        Flipper leftFlipper = new Flipper(board, "lFlipper", x, y, Orientation.valueOf("_180"), isLeftFlipper);
        Flipper rightFlipper = new Flipper(board, "rFlipper", x, y, Orientation.valueOf("_180"), !isLeftFlipper);

        //connecting gadgets to various gadgets
        sqbumper.connect(cirbumper);
        sqbumper.connect(tribumper);
        sqbumper.connect(ab);
        sqbumper.connect(leftFlipper);
        sqbumper.connect(rightFlipper);

        tribumper.connect(sqbumper);
        tribumper.connect(leftFlipper);

        rightFlipper.connect(cirbumper);
        rightFlipper.connect(ab);
        rightFlipper.connect(tribumper);

        //FIX THE TRIGGERS ERROR -- WE DO NOT HAVE ACCESS TO TRIGGERS!!!!
        /*assertEquals(5, sqbumper.triggers.size());
        assertEquals(2, tribumper.triggers.size());*/

        //disconnecting gadgets from each other
        sqbumper.disconnect(ab);
        sqbumper.disconnect(leftFlipper);

        tribumper.disconnect(sqbumper);

        rightFlipper.disconnect(ab);

        /*assertEquals(3, sqbumper.triggers.size());
        assertEquals(1, tribumper.triggers.size());
        assertEquals(2, rightFlipper.triggers.size());

        assertTrue(tribumper.triggers.contains(leftFlipper));
        assertTrue(rightFlipper.triggers.contains(tribumper));
        assertTrue(rightFlipper.triggers.contains(cirbumper));*/
    }

    @Test
    public void testTriggerActionCausesTargetAction() {
        int sqx = 4, sqy = 5;
        SquareBumper sqbumper = new SquareBumper(board, "sq", sqx, sqy);

        int cirx = 4, ciry = 5;
        CircleBumper cirbumper = new CircleBumper(board, "circ", cirx, ciry);

        int abx = 2, aby = 3, abwidth = 5, abheight = 10;
        Absorber ab = new Absorber(board, "absorber", abx, aby, abwidth, abheight);

        int x = 0, y = 0;
        boolean isLeftFlipper = true;
        Flipper leftFlipper = new Flipper(board, "lFlipper", x, y, Orientation.valueOf("_180"), isLeftFlipper);

        Ball b1 = new Ball("ball1", 0, 0, 1.0, 1.0, 25.0, .025, .025);
        Ball b2 = new Ball("ball2", 1, 1, 1.0, 1.0, 25.0, .025, .025);

        //creates connections
        sqbumper.connect(ab);
        cirbumper.connect(sqbumper);
        leftFlipper.connect(ab);

        //sqbumper triggers ab to release b1
        ab.trigger(b1);
        sqbumper.trigger(b2);
        Vect b1velUpdated = Board.DEFAULT_ABSORBER_EJECTION_VELOCITY;
        assertEquals(b1velUpdated, b1.getVelocityVector());

        //cirbumper shouldn't trigger ab -- triggers aren't transitive
        ab.trigger(b1);
        cirbumper.trigger(b2);
        Vect b1vel = new Vect(0, 0);
        assertEquals(b1vel, b1.getVelocityVector());
    }

    @Test
    public void testAbsorberActionTriggersAbsorberSelfAction() {
        int x = 5, y = 0, width = 5, height = 10;
        Absorber ab2 = new Absorber(board, "absorber2", x, y, width, height);

        int abx = 2, aby = 3, abwidth = 5, abheight = 10;
        Absorber ab = new Absorber(board, "absorber", abx, aby, abwidth, abheight);

        Ball b1 = new Ball("ball1", 0, 0, 1.0, 1.0, 25.0, .025, .025);
        Ball b2 = new Ball("ball2", 1, 1, 1.0, 1.0, 25.0, .025, .025);

        //ab and ab2 create self-connection
        ab.connect(ab);
        ab2.connect(ab2);

        //absorbs and fires the ball immediately since no ball is already inside absorber
        ab.trigger(b1);
        Vect b1velUpdated = Board.DEFAULT_ABSORBER_EJECTION_VELOCITY;
        assertEquals(b1velUpdated, b1.getVelocityVector());

        ab2.trigger(b2);
        //doesn't eject ball2, position remains same
        //velocity of ball2 changes to default ejection velocity from attempted ejection
        Vect b2velUpdated = Board.DEFAULT_ABSORBER_EJECTION_VELOCITY;
        Vect b2pos = new Vect(x + width - b1.getRadius(), y + height - b1.getRadius());
        assertEquals(b2pos, b2.getCenterAsVect());
        assertEquals(b2velUpdated, b2.getVelocityVector());

        //cannot absorb and fire ball1 because ball2 is inside.
        //Attempts to fire ball2 but cannot due to board constraints
        ab2.trigger(b1);
        assertEquals(b2pos, b2.getCenterAsVect());
        assertEquals(b2velUpdated, b2.getVelocityVector());

    }

    @Test
    public void tetBallToBallCollision() {
        Board board2 = new Board("board2", 0.0, 0.0, 0.0, queue);

        //creates 2 balls that will collide head-on, moving towards each other.
        //board2.addBall("ball1", -1, 0, 6.0, 2.0);
        //board2.addBall("ball2", 1, 0, 2.0, 2.0);
        Ball b1 = new Ball("ball1", -1, 0, 6.0, 2.0, 0.0, 0.0, 0.0);
        Ball b2 = new Ball("ball2", 1, 0, 2.0, 2.0, 0.0, 0.0, 0.0);

        Vect b1initV = new Vect(-1.0, 0.0);
        Vect b2initV = new Vect(1.0, 0.0);
        assertEquals(b1initV, b1.getVelocityVector());
        assertEquals(b2initV, b2.getVelocityVector());

        board.update(3);

        //should flip the velocities as the balls bounce off each other perfectly elastically
        Vect b1finalV = new Vect(1.0, 0.0);
        Vect b2finalV = new Vect(-1.0, 0.0);
        assertEquals(b2finalV, b1.getVelocityVector());
        assertEquals(b1finalV, b2.getVelocityVector());
    }

    @Test
    public void testBallToFlipperCollision() {
        Board board2 = new Board("board2", 0.0, 0.0, 0.0, queue);

        //creates 1 ball and 1 leftflipper that will collide during a trigger.
        //board2.addBall("ball1", -1, 0, 6.0, 2.0);
        //board2.addBall("ball2", 1, 0, 2.0, 2.0);
        Ball b1 = new Ball("ball1", 0, 1, 6.0, 2.0, 0.0, 0.0, 0.0);
        int x = 6, y = 5;
        boolean isLeftFlipper = true;
        Flipper leftFlipper = new Flipper(board, "lFlipper", x, y, Orientation.valueOf("_180"), isLeftFlipper);



        Vect b1initV = new Vect(0.0, 1.0);
        assertEquals(b1initV, b1.getVelocityVector());

        leftFlipper.trigger(b1);

        //left flipper orientation should return to what it was
        //ball velocity should reflect change
        Vect b1finalV = new Vect(0.0, 1.0);
        Orientation leftFlOrientation = Orientation.valueOf("_180");
        assertEquals(leftFlOrientation, leftFlipper.getOrientation());
        assertEquals(b1finalV, b1.getVelocityVector());
    }
}
