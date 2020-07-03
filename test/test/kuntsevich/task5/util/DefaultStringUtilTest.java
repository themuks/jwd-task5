package test.kuntsevich.task5.util;

import com.kuntsevich.task5.util.DefaultStringUtil;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DefaultStringUtilTest {

    private DefaultStringUtil defaultStringUtil = new DefaultStringUtil();

    @Test
    public void testBreakText() {
        String text = " abcde  ABCDE   абвгде  аб   ";
        String[] actual = defaultStringUtil.breakText(text);
        String[] expected = {"abcde", "ABCDE", "абвгде", "аб"};
        assertEquals(actual, expected);
    }

    @Test
    public void testCalcWordCountGreaterThanZero() {
        String text = " abcde  ABCDE   абвгде  аб   ";
        int actual = defaultStringUtil.calcWordCount(text);
        int expected = 4;
        assertEquals(actual, expected);
    }

    @Test
    public void testCalcWordCountZero() {
        String text = "    ";
        int actual = defaultStringUtil.calcWordCount(text);
        int expected = 0;
        assertEquals(actual, expected);
    }

    @Test
    public void testJoinWords() {
        String[] words = {"abcde", "ABCDE", "абвгде", "аб"};
        String actual = defaultStringUtil.joinWords(words);
        String expected = "abcde ABCDE абвгде аб";
        assertEquals(actual, expected);
    }
}