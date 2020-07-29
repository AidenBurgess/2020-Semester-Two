package se254.lab.junitreview;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Provides is a set of test cases to test the getStdDevn() method in Stats
 * class.
 * 
 * @author Kelvin Jin, Summer 2002 
 * @modified Ewan Tempero, March 2004
 * @modified Ewan Tempero, July 2009 - removing last traces of previous junit versions
 * @modified Ewan Tempero, July 2010 - removing some tests, changing the package
 * @modified Ewan Tempero, June 2016 - changing package again, updating to JUnit 4.
 */

public class TestStatsStdDevn {
	/**
     * Test that the mean is correctly computed for the really
     * easy case of a list containing only 0.
     */
	@Test
    public void testZero() {
        Stats stats = new Stats(new int[]{0});
        assertEquals(0, stats.getStdDevn());
    }

    /**
     * Test that the mean is correctly computed for the easy case
     * of a list containing a single value (that isn't 0).
     */
	@Test
    public void testOneElement() {
        Stats stats = new Stats(new int[]{2});
        assertEquals(0, stats.getStdDevn());
    }

	@Test
    public void testTwoElements() {
        Stats stats = new Stats(new int[]{1, 3});
        assertEquals(1, stats.getStdDevn());
    }
    
    /**
     * Now try a list with just 2 numbers but they differ by more 
     * than 1.
     */
	@Test
    public void testLargeDifference() {
        Stats stats = new Stats(new int[]{1, 101});
        assertEquals(50,stats.getStdDevn());
    }

    /**
     * Test that the computation of the mean does the right thing
     * for an empty list (namely throw an exception, since otherwise
     * we would have to divide by zero.
     */
	@Test
    public void testEmptyInput() {
        Stats stats = new Stats(new int[]{});
        try {
            assertEquals(0, stats.getStdDevn());
            fail("should have failed");
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
	@Test
    public void testMinusOne() {
        Stats stats = new Stats(new int[]{-1});
        try {
            assertEquals(-1, stats.getStdDevn());
            fail("should have failed");
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
	@Test
    public void testInvalidInput() {
        Stats stats = new Stats(new int[]{1, 2, -1});
        try {
            assertEquals(1, stats.getStdDevn());
            fail("Should have failed");
        } catch (Exception e) {
            // Nothing here - just ignore the fact that the exception
            // occurred (since that's what's supposed to happen in
            // this case).
        }
    }

}
