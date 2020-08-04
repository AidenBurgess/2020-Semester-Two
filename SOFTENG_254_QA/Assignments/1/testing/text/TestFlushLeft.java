package text; // DO NOT CHANGE THIS OR YOU WILL GET ZERO

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;

/**
 * SOFTENG 254 Assignment 1 submission template
 *
 * Author: (name, username)
 **/

public class TestFlushLeft {// DO NOT CHANGE THE CLASS NAME OR YOU WILL GET ZERO

    @Test
    public void testLeadingSpace() throws Exception {
        String[] expected = { "Test" };
        runTest(10, " Test", expected, false);
    }

    @Test
    public void testLeadingNewLine() throws Exception {
        String[] expected = { "Test" };
        runTest(10, "\nTest", expected, false);
    }

    @Test
    public void testLongWordOverflow() throws Exception {
        String[] expected = { "Looo-", "ng" };
        runTest(5, "Looong", expected, false);
    }

    @Test
    public void testLongWordOverflowWithHyphen() throws Exception {
        String[] expected = { "Loo-", "ong" };
        runTest(5, "Loo-ong", expected, false);
    }

    /*
     * Some helper methods to make the tests easier to understand
     */

    private void runTest(int width, String input, String[] expected, boolean exception) throws Exception {
        try {
            List<String> actual = Formatter.flushLeftText(input, width);
            if (exception) {
                fail("Did not throw exception");
            } else {
                assertListsEquals("", expected, actual);
            }
        } catch (IllegalArgumentException ex) {
            // Only legal IllegalArgumentException is if there is exactly one expected value
            // and that's
            // the message we're supposed to get
            if (expected.length != 1 || !expected[0].equals(ex.getMessage())) {
                throw ex;
            }
        }
    }

    private static <T> void assertListsEquals(String msg, T[] expected, List<T> actual) {
        for (int i = 0; i < expected.length; i++) {
            T e = expected[i];
            T a = null;
            if (i < actual.size()) {
                a = actual.get(i);
            } else {
                fail("actual has too few elements. expected " + expected.length + " but got " + actual.size());
            }
            assertEquals("position " + i, e, a);
        }
        if (expected.length < actual.size()) {
            fail("actual has too many elements. expected " + expected.length + " but got " + actual.size() + " "
                    + actual);
        }
    }
}
