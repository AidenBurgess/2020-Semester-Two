package se254.lab.junitreview;

import junit.framework.TestCase;

/**
 * Provides is a set of test cases to test the getMean() method in Stats class.
 * This is the JUnit 3 version, for comparison with the JUnit 4 version
 * 
 * @author Kelvin Jin, Summer 2002 
 * @modified Ewan Tempero, March 2004
 * @modified Ewan Tempero, July 2009 - removing last traces of previous junit versions
 * @modified Ewan Tempero, July 2010 - changing the package
 * @modified Ewan Tempero, June 2016 - changing the package again as part of update the rest of the lab to JUnit 4
 * 
 */

public class TestStatsMeanJUnit3 extends TestCase {
    /**
     * Test that the mean is correctly computed for the really
     * easy case of a list containing only 0.
     */
    public void testZero() {
        Stats stats = new Stats(new int[]{0});
        assertEquals("Mean of list {0}", 0, stats.getMean());
    }

    /**
     * Test that the mean is correctly computed for the easy case
     * of a list containing a single value (that isn't 0).
     */
    public void testOneElement() {
        Stats stats = new Stats(new int[]{2});
        assertEquals("Mean of list {2}", 2, stats.getMean());
    }

    /**
     * Test that the mean is correctly computed for the simple
     * case of a list containing two values whose sum is
     * an even number (and so there is no rounding involved
     * in computing the mean.)
     */
    public void testTwoElements() {
        Stats stats = new Stats(new int[]{4, 2});
        assertEquals(3, stats.getMean());
    }
    
    /**
     * Test that the mean is correctly computed for the 
     * straight-forward case of 3 numbers whose sum is
     * a multiple of 3 (and so there is no rounding involved
     * in computing the mean).
     */
    public void testThreeElements() {
        Stats stats = new Stats(new int[]{1, 0, 2});
        assertEquals(1, stats.getMean());
    }

    /**
     * Test that the mean is correctly computed when 
     * the division produces a fractional part that is
     * greater than 1/2 (meaning the correct result is
     * to round up).
     */
    public void testRoundingAboveHalf() {
        Stats stats = new Stats(new int[]{1, 0, 1});
        assertEquals(1, stats.getMean());
    }

    /**
     * Test that the mean is correctly computed when 
     * the division produces a fractional part that is
     * less than 1/2 (meaning the correct result is
     * to round down).
     */
    public void testRoundingBelowHalf() {
        Stats stats = new Stats(new int[]{1, 0, 0});
        assertEquals(0, stats.getMean());
    }

    /**
     * Test that the mean is correctly computed when 
     * the division produces a fractional part that is
     * exactly 1/2 (meaning the correct result is
     * to round up).
     */
    public void testRoundingAtHalf() {
        Stats stats = new Stats(new int[]{1, 0, 0, 1});
        assertEquals(1, stats.getMean());
    }

    /**
     * Test that the mean is correctly computed for the kind of
     * list we would normally expect to see.
     */
    public void testGeneralCase() {
        Stats stats = new Stats(new int[]{12, 10, 103, 45, 6});
        assertEquals(35, stats.getMean());
    }

    /**
     * Test that the computation of the mean does the right thing
     * for an empty list (namely throw an exception).
     */
    public void testEmptyInput() {
        Stats stats = new Stats(new int[]{});
        try {
            assertEquals(0, stats.getMean());
            fail("Should have failed: empty list");
        } catch (Exception e) {
            // Nothing here - just ignore the fact that the exception
            // occurred (since that's what's supposed to happen in
            // this case).
        }
    }

    /**
     * Test that the computation of the mean does the right
     * thing on a list containing a single, but invalid (ie negative)
     * number.
     */
    public void testMinusOne() {
        Stats stats = new Stats(new int[]{-1});
        try {
            assertEquals(-1, stats.getMean());
            fail("Should have failed: negative numbers in list");
        } catch (Exception e) {
            // Nothing here - just ignore the fact that the exception
            // occurred (since that's what's supposed to happen in
            // this case).
        }
    }
    
    /**
     * Test that the computation of the mean does the right
     * thing on a list containing more than one value, but one
     * of them being invalid (ie negative) number.
     */
    public void testInvalidInput() {
        Stats stats = new Stats(new int[]{1, 2, -1});
        try {
            assertEquals(1, stats.getMean());
            fail("Should have failed: negative numbers in list");
        } catch (Exception e) {
            // Nothing here - just ignore the fact that the exception
            // occurred (since that's what's supposed to happen in
            // this case).
        }
    }

}
