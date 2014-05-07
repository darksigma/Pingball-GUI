package pingball.parsing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

import physics.Vect;
import pingball.parser.BoardGrammarCentral;
import pingball.simulation.Board;
import pingball.util.StringUtils;


public class BoardGrammarTests {
    /*Test Suite:
     *
     * -Board: parse through file with boardname and optional gravity and friction values
     * -Ball: parse through file with single ball, multiple balls, balls with non-unique names (which
     * isn't allowed)
     * -Bumpers/flippers/absorber: parse through file with combinations of some and not the others,
     * multiple of each, non-unique names (which isn't allowed)
     * -Comments and whitespace: ignore comments and whitespace throughout, have multiple comments at a time,
     * have single comments.
     * -Integers and doubles: values that are FLOAT or double can be given as integers in the file, but
     * should be double  when creating the board.
     *
     */

    File f1 = new File("src/test-resources/sampleBoard1.pb");
    File f2 = new File("src/test-resources/sampleBoard2-1.pb");
    File f3 = new File("src/test-resources/sampleBoard2-2.pb");
    File f4 = new File("src/test-resources/sampleBoard3.pb");
    File f5 = new File("src/test-resources/sampleBoard4.pb");
    File f6 = new File("src/test-resources/sampleBoard5.pb");
    File f7 = new File("src/test-resources/sampleBoard6.pb");
    File f8 = new File("src/test-resources/sampleBoard7.pb");
    File f9 = new File("src/test-resources/sampleBoard8.pb");

    BlockingQueue<String> sendQueue = new LinkedBlockingQueue<String>();
    
    //Tests that boards with only a name given take the default values for friction1, friction2, and gravity
    //File used: sampleBoard5.pb
    @Test
    public void testBoardNameOnlyProvided() throws IOException {
    	Board b = new Board(sendQueue, f6);

        assertEquals("ExampleC", b.getName());

        assertTrue(b.getFriction1() == 0.025);
        assertTrue(b.getFriction2() == 0.025);
        assertTrue(b.getGravity() == 10.0);
    }

    //Tests that if only gravity or friction1 or friction2 is given in the file, the other 2 values will default.
    //File used: sampleBoard3.pb for only gravity given
    //File used: sampleBoard6.pb for only friction2 given
    @Test
    public void testBoardNameWithOnlyGravityOrFriction1or2Provided() throws IOException {

        Board b = new Board(sendQueue, f7);

        assertTrue(b.getGravity() == 25.0);
        assertTrue(b.getFriction2() == 0.015);
        assertTrue(b.getFriction1() == 0.025);

        Board b2 = new Board(sendQueue, f4);

        assertTrue(b2.getFriction1() == 0.025);
        assertTrue(b2.getFriction2() == 0.025);
        assertTrue(b2.getGravity() == 10.0);

    }

    //Tests that the board name, gravity, friction1, and friction2 match what is specified in the file
    //File used: sampleBoard1.pb

    @Test
    public void testBoardNameWithGravityAndFrictionsProvided() throws IOException {
        Board b = new Board(sendQueue, f1);

        assertEquals("sampleBoard1", b.getName());
        assertEquals("sampleBoard1", b.getName());

        assertTrue(b.getFriction1() == 0.020);
        assertTrue(b.getFriction2() == 0.020);
        assertTrue(b.getGravity() == 20.0);

    }

    //Tests correct placement of all gadgets and single ball on board
    //File used: sampleBoard1.pb
    @Test
    public void testSingleBall() throws IOException {
        Board b = new Board(sendQueue, f1);
        String actual = "......................\n" +
                        ".*                   .\n" +
                        ".                    .\n" +
                        ".########|  |########.\n" +
                        ".    O   |  |   O    .\n" +
                        ".     O        O     .\n" +
                        ".      O      O      .\n" +
                        ".       O    O       .\n" +
                        ".        |  |        .\n" +
                        ".        |  |        .\n" +
                        ".        \\  /        .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".====================.\n" +
                        "......................";

        assertEquals(actual, StringUtils.join("\n", b.gridRepresentation()));
    }

    //Tests correct placement of gadgets on board when there are multiple balls in the board file.
    //File used: sampleBoard3.pb
    @Test
    public void testMultipleBalls() throws IOException {
        Board b = new Board(sendQueue, f4);

        String actual = "......................\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".    O              \\.\n" +
                        ". *                  .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".          |  |      .\n" +
                        ".          |  |      .\n" +
                        ".                    .\n" +
                        ".########            .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".          *         .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".          ==========.\n" +
                        ".          ==========.\n" +
                        ".                    .\n" +
                        "......................" ;

        assertEquals(actual, StringUtils.join("\n", b.gridRepresentation()));

    }
    
    //tests correct placement of every type of gadget (one each) on board
    //some gadgets are so close they are correctly hidden (ball, flipper left, flipper right all overlap)
    //File used: sampleBoard6.pb
    @Test
    public void singleGadgetAllTypes() throws IOException {
        Board b = new Board(sendQueue, f7);

        String actual = "......................\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".*  |      O         .\n" +
                        ".  ||                .\n" +
                        ".  |                 .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".   #                .\n" +
                        ".    \\               .\n" +
                        ".    /               .\n" +
                        ".                    .\n" +
                        ".   =                .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        "......................" ;

        assertEquals(actual, StringUtils.join("\n", b.gridRepresentation()));
    }

    //tests with parsing through multiple comments at a time and multiple lines of whitespace
    //File used: sampleBoard2-1.pb and sampleBoard2-2.pb
    @Test
    public void testCommentsAndWhitespaceParsing() throws IOException {
        Board b1 = new Board(sendQueue, f2);
        Board b2 = new Board(sendQueue, f3);

        String actual1 = "......................\n" +
                         ".*                   .\n" +
                         ".                    .\n" +
                         ".################|   .\n" +
                         ".          O     |   .\n" +
                         ".           O        .\n" +
                         ".            O       .\n" +
                         ".             O      .\n" +
                         ".              O     .\n" +
                         ".               O    .\n" +
                         ".                |   .\n" +
                         ".                |   .\n" +
                         ".                 \\  .\n" +
                         ".                  \\ .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".====================.\n" +
                         "......................" ;

        String actual2 = "......................\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".   |################.\n" +
                         ".   |     O          .\n" +
                         ".        O           .\n" +
                         ".       O            .\n" +
                         ".      O             .\n" +
                         ".     O              .\n" +
                         ".    O               .\n" +
                         ".   |                .\n" +
                         ".   |                .\n" +
                         ".  /                 .\n" +
                         ". /                  .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".====================.\n" +
                         "......................" ;

        assertEquals(actual1, StringUtils.join("\n", b1.gridRepresentation()));
        assertEquals(actual2, StringUtils.join("\n", b2.gridRepresentation()));
    }

    
    //tests parsing with extra whitespace spaces between "=" and tokens to be parsed
    //tests parsing with negative FLOAT values stored in velocity tokens
    //File used: sampleBoard4.pb and sampleBoard6.pb
    @Test
    public void testExtraSpacingWithEqualSign() throws IOException {
        Board b1 = new Board(sendQueue, f5);
        Board b2 = new Board(sendQueue, f7);

        String actual1 = "......................\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".    O              \\.\n" +
                         ". *                  .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".          |  |      .\n" +
                         ".          |  |      .\n" +
                         ".                    .\n" +
                         ".####                .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".          ==========.\n" +
                         ".          ==========.\n" +
                         ".                    .\n" +
                         "......................" ;

        String actual2 = "......................\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".*  |      O         .\n" +
                         ".  ||                .\n" +
                         ".  |                 .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".   #                .\n" +
                         ".    \\               .\n" +
                         ".    /               .\n" +
                         ".                    .\n" +
                         ".   =                .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         ".                    .\n" +
                         "......................" ;

        assertEquals(actual1, StringUtils.join("\n", b1.gridRepresentation()));
        assertEquals(actual2, StringUtils.join("\n", b2.gridRepresentation()));
    }

    //tests to make sure only gadgets and balls with non-unique names are added to the board
    //first ball or gadget of the name should be added to the board.
    //File used: sampleBoard7.pb
    @Test
    public void testNonUniqueNamedGadgetsAndBallsOnly() throws IOException {
        Board b = new Board(sendQueue, f8);
        String actual = "......................\n" +
                        ".                    .\n" +
                        ".  ====              .\n" +
                        ".  ====              .\n" + //third ball in file isn't added because it shares
                        ".*|        O         .\n" + //the same name as the first ball
                        ". ||               * .\n" +
                        ".  |                 .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".   # ======         .\n" + //a triangle bumper isn't added because it shares the
                        ".    /               .\n" + //same name as a ball
                        ".    \\               .\n" +
                        ".                    .\n" +
                        ".   =                .\n" + //an absorber isn't added because it shares the same
                        ".                    .\n" + //name as the first absorber listed
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        "......................" ;


        assertEquals(actual, StringUtils.join("\n", b.gridRepresentation()));

    }

    //tests that integer inputs for FLOAT values still become double, and integers that should be integers
    //remain as integers.
    //File used: sampleBoard8.pb
    @Test
    public void testDoubleAndIntegerValuesCreatedAccurately() throws IOException {
        Board b = new Board(sendQueue, f9);
        String actual = "......................\n" +
                        ".                    .\n" +
                        ".  ====              .\n" +
                        ".  ====              .\n" +
                        ".*|        O         .\n" +
                        ". ||               * .\n" + //this ball correctly converts integer-input values
                        ".  |                 .\n" + //from the file to doubles
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".   # ======         .\n" +
                        ".    /               .\n" +
                        ".    \\               .\n" +
                        ".                    .\n" +
                        ".   =                .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        ".                    .\n" +
                        "......................" ;
        assertEquals(actual, StringUtils.join("\n", b.gridRepresentation()));

    }

}
