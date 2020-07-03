package test.kuntsevich.task5.util;

import com.kuntsevich.task5.util.CharArrayUtil;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class CharArrayUtilTest {

    private CharArrayUtil charArrayUtil = new CharArrayUtil();

    @Test
    public void testBreakText() {
        char[] text = {' ', 'a', 'b', 'c', 'd', 'e', ' ', ' ', 'D', 'E', ' ', ' ', ' '};
        char[][] wordsActual = charArrayUtil.breakText(text);
        char[][] wordsExpected = {{'a', 'b', 'c', 'd', 'e'}, {'D', 'E'}};
        boolean actual = Arrays.deepEquals(wordsActual, wordsExpected);
        assertTrue(actual);
    }

    @Test
    public void testStripCharArray() {
        char[] text = {' ', 'a', 'b', 'c', 'd', 'e', ' ', ' ', 'D', 'E', ' ', ' ', ' '};
        char[] actual = charArrayUtil.stripCharArray(text);
        char[] expected = {'a', 'b', 'c', 'd', 'e', ' ', ' ', 'D', 'E'};
        assertEquals(actual, expected);
    }

    @Test
    public void testCalcWordCountGreaterThanZero() {
        char[] text = {' ', 'a', 'b', 'c', 'd', 'e', ' ', ' ', 'D', 'E', ' ', ' ', ' '};
        int actual = charArrayUtil.calcWordCount(text);
        int expected = 2;
        assertEquals(actual, expected);
    }

    @Test
    public void testCalcWordCountZero() {
        char[] text = {' ', ' ', ' ', ' '};
        int actual = charArrayUtil.calcWordCount(text);
        int expected = 0;
        assertEquals(actual, expected);
    }

    @Test
    public void testJoinCharArrays() {
        char[][] words = {{'a', 'b', 'c', 'd', 'e'}, {'D', 'E'}};
        String actual = charArrayUtil.joinCharArrays(' ', words);
        String expected = "abcde DE";
        assertEquals(actual, expected);
    }

    @Test
    public void testIndexOfSubstring() {
        char[] word = {'a', 'b', 'c', 'd', 'e'};
        char[] substr = {'d', 'e'};
        int actual = charArrayUtil.indexOfSubstring(word, substr);
        int expected = 3;
        assertEquals(actual, expected);
    }
}