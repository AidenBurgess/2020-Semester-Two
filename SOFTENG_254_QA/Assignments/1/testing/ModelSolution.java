package text;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

/**
 * This is a "model" solution in that it shows tests that will fail for each of the
 * 15 bad implementations. It is not a model solution in that this test suite is
 * sufficient to detect all possible faults (it does not even have 100% statement coverage).
 */
public class ModelSolution {
	/**
	 * Leading white space should not appear in the output.
	 */
	@Test
	public void testA() throws Exception {
		String[] expected = { "abc def" };
		runTest(10, "  abc def", expected, false);
	}	
	
	/**
	 * When a word is hyphenated, the hyphen should be in the last position on the line
	 */
	@Test
	public void testB() throws Exception {
		String[] expected = { "abcdefghi-", "jklm" };
		runTest(10, "abcdefghijklm", expected, false);
	}
	
	/**
	 * Multiple adjacent spaces between words should be reduced to a single space 
	 */
	@Test
	public void testC() throws Exception {
		String[] expected = { "ab cd" };
		runTest(10, "ab  cd", expected, false);
	}
	
	/**
	 * The text can be split at a hyphen in the last position on the line, and if
	 * so no further hyphens should be added.
	 */
	@Test
	public void testD() throws Exception {
		String[] expected = { "abcdefghi-", "jklm" };
		runTest(10, "abcdefghi-jklm", expected, false);
	}


	/**
	 * When there is a single new line, the text must be split at that point.
	 */
	@Test
	public void testE() throws Exception {
		String[] expected = { "a b c", "d"};
		runTest(10, "a b c\nd", expected, false);
	}

	/**
	 * The last character in the output can be at the last position of a line when 
	 * there are multiple words.
	 */
	@Test
	public void testF() throws Exception {
		String[] expected = { "a b c d ef"};
		runTest(10, "a b c d ef", expected, false); // exactly 10
	}

	
	/**
	 * If the word does not contain hyphens and is 2 characters or longer then
	 * it will not fit on a line of width 1, that is, the constraints cannot
	 * be satisfied.
	 */
	@Test(timeout=1000)
	public void testG() throws Exception {
		String[] expected = { "Constraints cannot be satisfied" };
		runTest(1, "abcde", expected, true);
	}


	/**
	 * An input that is the empty string produces a single (empty) line as output.
	 */
	@Test
	public void testH() throws Exception {
		String[] expected = { "" };
		runTest(10, "", expected, false);
	}

	/**
	 * A line can be split at any hyphen, the hyphen must appear in the output,
	 * so an input that is all hyphens can fit with a linewidth of 1.
	 */
	@Test
	public void testI() throws Exception {
        String[] expected = { "-", "-", "-", "-", "-", "-", "-"};
        runTest(1, "-------", expected, false);
    }

	/**
	 * An input that is the empty string will fit in a line of width zero.
	 */
	@Test
	public void testJ() throws Exception {
		String[] expected = { "" };
		runTest(0, "", expected, false);
	}

	
	/**
	 * Whitespace following a newline is removed
	 */
	@Test
	public void testK() throws Exception {
		String[] expected = { "a b c", "d"};
		runTest(10, "a b c\n d", expected, false);
	}

	/**
	 * Words that are the same length as the linewidth should fit on a line,
	 * that is, not split.
	 */
	@Test
	public void testL() throws Exception {
		String[] expected = { "aaaa", "bbbb", "cccc", "dddd", "eeee", "ff" };
		runTest(4, "aaaa bbbb cccc dddd eeee ff", expected, false);
	}
	
	/**
	 * Printable characters following a hyphen can be moved to the next line, and
	 * if there is only one that will fit on a line of width 1. 
	 */
	@Test
	public void testM() throws Exception {
		String[] expected = { "-", "a" };
		runTest(1, "-a", expected, false);
	}
	

	/**
	 * When a tab separates two words it is replaced by a space.
	 */
	@Test
	public void testN() throws Exception {
		String[] expected = { "a b c d", "efg h" }; 
		runTest(10, "a b\tc d efg h", expected, false);		
	}
	

	/**
	 * A null text is not a valid input. 
	 */
	@Test
	public void testO() throws Exception {
		runTest(10, null, new String[] {"Invalid text (null)"}, true);
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
			// Only legal IllegalArgumentException is if there is exactly one expected value and that's
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
			fail("actual has too many elements. expected " + expected.length + " but got " + actual.size() + " " + actual);
		}
	}

	
}

