package text;  // DO NOT CHANGE THIS OR YOU WILL GET ZERO

import static org.junit.Assert.assertEquals;
import org.junit.rules.Timeout;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
/**
 * SOFTENG 254 Assignment 1 submission template
 *
 * Author: (Josh Lim, jlim322)
 **/

public class TestFlushLeft {// DO NOT CHANGE THE CLASS NAME OR YOU WILL GET ZERO
	private List<String> outputList;
	private List<String> expectedList;
	@Before
	public void setup() {
		if (outputList!=null) {
			outputList.clear();
		}
		if (expectedList!=null) {
			expectedList.clear();
		}
	}

	@Rule
	public Timeout timeoutRule=Timeout.seconds(2);

	
	/**
	 * ====================================================================================================
	 * Testing normal input (include spaces/white spaces)
	 * ====================================================================================================
	 */
	
	/**
	 * Explanation:
	 * Normal test to ensure hyphen is added to a string longer than the line width and also
	 * the string is split correctly
	 */
	@Test
	public void testWithoutSpaceMinWidth() {
		outputList=Formatter.flushLeftText("abc", 2);
		expectedList=Arrays.asList("a-", "bc");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Normal test to ensure no hyphen is added to a string when it is not supposed to
	 * also the string is split correctly
	 */
	@Test
	public void testSpacedMinWidth() {
		outputList=Formatter.flushLeftText("ab c d", 2);
		expectedList=Arrays.asList("ab", "c", "d");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Normal test to ensure no hyphen is added to a string when it is not supposed to
	 * also the string is split correctly when multiple space are added.
	 */
	@Test
	public void testMultipleSpacedMinWidth() {
		outputList=Formatter.flushLeftText("ab\nc \t \t d \n \t e", 2);
		expectedList=Arrays.asList("ab", "c", "d", "e");
		assertEquals(expectedList, outputList);
	}

	/**
	 * Explanation:
	 * Test if string is split correctly when width is longer and able to hold multiple words
	 */
	@Test
	public void testLongWidthWithNoHyphenAdded() {
		outputList=Formatter.flushLeftText("abcd ef", 6);
		expectedList=Arrays.asList("abcd", "ef");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Test if string is split correctly with spaces when width is longer and able to hold multiple words
	 */
	@Test
	public void testLongWidthWithSpacesButNoHyphenAdded() {
		outputList=Formatter.flushLeftText("abcd \n \t \n \t e", 6);
		expectedList=Arrays.asList("abcd", "e");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Test if string is split correctly when width is longer and able to hold multiple words.
	 * This input is different from before as one of the output holds 2 words.
	 */
	@Test
	public void testLongWidthWithHyphensAdded() {
		outputList=Formatter.flushLeftText("abcdefg hi jk", 6);
		expectedList=Arrays.asList("abcde-", "fg hi", "jk");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Test if string is split correctly when width is longer and must hold multiple words with lots
	 * of spaces.
	 */
	@Test
	public void testLongWidthWithMultipleWhiteSpaces() {
		outputList=Formatter.flushLeftText("a \t b \t c \t d", 6);
		expectedList=Arrays.asList("a b c", "d");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Test if string is split correctly when width is longer and must hold multiple words with normal
	 * spacing
	 */
	@Test
	public void testLongWidthWithSpaces() {
		outputList=Formatter.flushLeftText("a b c d", 6);
		expectedList=Arrays.asList("a b c", "d");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Not considering line width, breaking string with only white spaces (new lines)
	 */
	@Test
	public void testMultipleWhitepacesWithNewLine() {
		outputList=Formatter.flushLeftText("ab \n\n\t cd \t e \t\t f  g", 50);
		expectedList=Arrays.asList("ab", "cd e f g");
		assertEquals(expectedList, outputList);
	}

	/**
	 * Explanation:
	 * Make sure no empty string or white space is added at the end and at the front
	 */
	@Test
	public void testMostlyWhitespacesAtStartAndEnd() {
		outputList=Formatter.flushLeftText("   \t\t\n\n ab c  \t\t\n\n", 4);
		expectedList=Arrays.asList("ab c");
		assertEquals(expectedList, outputList);
	}
	
	
	/**
	 * ====================================================================================================
	 * Testing input with hyphens
	 * ====================================================================================================
	 */

	/**
	 * Explanation:
	 * Test if hyphens are ignored when no characters are present around it.
	 * The 'a' is there to make sure something is being output
	 */
	@Test
	public void testHyphensTreatedAsWord() {
		outputList=Formatter.flushLeftText("- -- a-", 6);
		expectedList=Arrays.asList("- --", "a-");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Test if extra hyphen is added when it is not needed
	 */
	@Test
	public void testExtraHyphensAddedWhenNotNeeded() {
		outputList=Formatter.flushLeftText("a-b", 2);
		expectedList=Arrays.asList("a-", "b");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Test if hyphens are ignored when it is part of a word
	 */
	@Test
	public void testHyphensAsPartOfAWord() {
		outputList=Formatter.flushLeftText("a- ----b c--- d e ", 6);
		expectedList=Arrays.asList("a- ---", "-b c--", "- d e");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Test if hyphens are ignored with normal input without splitting
	 */
	@Test
	public void testNormalInputWithHyphensNoSplit() {
		outputList=Formatter.flushLeftText("a--bc", 5);
		expectedList=Arrays.asList("a--bc");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Test if hyphens are ignored with normal input and if extra hyphen is added
	 */
	@Test
	public void testNormalInputWithHyphensSplit() {
		outputList=Formatter.flushLeftText("a--bc", 4);
		expectedList=Arrays.asList("a--", "bc");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Test if hyphens are ignored and treated as white space with normal input
	 */
	@Test
	public void testNormalInputWithHyphensAndSpacedWords() {
		outputList=Formatter.flushLeftText("a-- b", 4);
		expectedList=Arrays.asList("a--", "b");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Test if all hyphens are being output when it is a single character. Line width in this case
	 * fits all the hyphens given
	 */
	@Test
	public void testSingleLineHyphensOnly() {
		outputList=Formatter.flushLeftText(" - -", 3);
		expectedList=Arrays.asList("- -");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Test if all hyphens are being output and split correctly when it is treated as 2 words.
	 * Line width in this case force the hyphens to break into 2 parts.
	 */
	@Test
	public void testSpacedHyphensOnly() {
		outputList=Formatter.flushLeftText("- --", 3);
		expectedList=Arrays.asList("- -", "-");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Test if all hyphens are being output and split correctly when it is treated as a word
	 */
	@Test
	public void testBreakingHyphensOnly() {
		outputList=Formatter.flushLeftText("-----", 3);
		expectedList=Arrays.asList("---", "--");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Check if every hyphen is being output with line width of 1 without space
	 */
	@Test
	public void testAllHyphensAndLineWidth1WithNoSpace() {
		outputList=Formatter.flushLeftText("-----", 1);
		expectedList=Arrays.asList("-", "-", "-", "-", "-");
		assertEquals(expectedList, outputList);
	}
	
	/**
	 * Explanation:
	 * Check if every hyphen is being output with line width of 1 with space
	 */
	@Test
	public void testAllHyphensAndLineWidth1WithSpace() {
		outputList=Formatter.flushLeftText("--- -- - -", 1);
		expectedList=Arrays.asList("-", "-", "-", "-", "-", "-", "-");
		assertEquals(expectedList, outputList);
	}
	
	
	/**
	 * ====================================================================================================
	 * Testing exceptions
	 * ====================================================================================================
	 */
	
	/**
	 * Explanation:
	 * Some implementation may start looping the string before checking the length of the string.
	 * This will obviously throw an index out of bound exception.
	 */
	@Test
	public void testEmptyStringWithWidth() {
		try {
			outputList=Formatter.flushLeftText("", 2);
			expectedList=Arrays.asList("");
			assertEquals(expectedList, outputList);
		} catch(StringIndexOutOfBoundsException e) {
			fail("Not supposed to have string index out of bound");
		}
	}

	/**
	 * Explanation:
	 * Some implementation may throw illegal argument exception as soon as the input line width is 0.
	 * However, this will work as the length of the string is also 0. Any exception thrown will fail the
	 * test.
	 */
	@Test
	public void testEmptyStringWith0Width() {
		try {
			outputList=Formatter.flushLeftText("", 0);
			expectedList=Arrays.asList("");
			assertEquals(expectedList, outputList);
		} catch(IllegalArgumentException e) {
			fail("Not an illegal argument");
		} catch(StringIndexOutOfBoundsException e) {
			fail("Not supposed to have string index out of bound");
		}
	}
	
	/**
	 * Explanation:
	 * A few new line and tab have been thrown in to test only 1 empty string is being output.
	 * Line width is 0 to test if the implementation would throw exception
	 */
	@Test
	public void testOnlyWhitespaces() {
		try {
			outputList=Formatter.flushLeftText("        \n\n  \n   \t         \t", 0);
			expectedList=Arrays.asList("");
			assertEquals(expectedList, outputList);
		} catch(IllegalArgumentException e) {
			fail("Not supposed to throw exception");
		}
	}

	/**
	 * Explanation:
	 * Exception should be thrown as negative line width
	 */
	@Test
	public void testExceptionConstraintsSatisfactionNegativeWidth() {
		try {
			Formatter.flushLeftText("abcde", -3);
			fail("Fail to throw exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Constraints cannot be satisfied");
		}
	}

	/**
	 * Explanation:
	 * Exception should be thrown because line width is 1 and string(no space) length is longer
	 */
	@Test
	public void testExceptionConstraintsSatisfactionWidth1() {
		try {
			Formatter.flushLeftText("abc", 1);
			fail("Fail to throw exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Constraints cannot be satisfied");
		}
	}

	/**
	 * Explanation:
	 * Test if exception is thrown because of the line width, and/or because of the "-b"
	 */
	@Test
	public void testPassWithWidth1() {
		try {
			Formatter.flushLeftText("a -b c", 1);
		} catch(IllegalArgumentException e) {
			fail("Not an illegal argument");
		}
	}

	/**
	 * Explanation:
	 * Null pointer exception should be thrown even though the line width is negative
	 */
	@Test
	public void testExceptionNullInput() {
		try {
			Formatter.flushLeftText(null, -1);
			fail("Fail to throw exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid text (null)");
		}
	}
}
