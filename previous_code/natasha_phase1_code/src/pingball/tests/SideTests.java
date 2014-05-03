package pingball.tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import pingball.datastructures.Side;

/*
 * Testing Strategy:
 * --------------------
 * Test all four enum values' toString. Test toSide with strings that
 * will yield all four enum values. Then test with a random String,
 * and a string with the incorrect capitalization to make sure the
 * method correctly throws its exception.
 *
 */
public class SideTests {
    private static Side l;
    private static Side r;
    private static Side t;
    private static Side b;
    private static String lString;
    private static String rString;
    private static String tString;
    private static String bString;
    private static String random;
    private static String wrongCapitalization;

    @BeforeClass
    public static void setUpBeforeClass() {
        l = Side.LEFT;
        r = Side.RIGHT;
        t = Side.TOP;
        b = Side.BOTTOM;
        lString = "left";
        rString = "right";
        tString = "top";
        bString = "bottom";
        random = "such tests. Wow";
        wrongCapitalization = "BOTTOM";
    }

    //toString tests
    @Test
    public void testToStringLeft() {
        assertEquals("left", l.toString());
    }

    @Test
    public void testToStringRight() {
        assertEquals("right", r.toString());
    }

    @Test
    public void testToStringTop() {
        assertEquals("top", t.toString());
    }

    @Test
    public void testToStringBottom() {
        assertEquals("bottom", b.toString());
    }

    //toSide tests
    @Test
    public void testToSideRightString() {
        assertEquals(Side.RIGHT, Side.toSide(rString));
    }

    @Test
    public void testToSideLeftString() {
        assertEquals(Side.LEFT, Side.toSide(lString));
    }

    @Test
    public void testToSideTopString() {
        assertEquals(Side.TOP, Side.toSide(tString));
    }

    @Test
    public void testToSideBottomString() {
        assertEquals(Side.BOTTOM, Side.toSide(bString));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToSideRandomString() throws IllegalArgumentException {
        Side.toSide(random);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToSideWrongCapitalization() throws IllegalArgumentException {
        Side.toSide(wrongCapitalization);
    }




}
