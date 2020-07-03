package test.kuntsevich.task5.util;

import com.kuntsevich.task5.util.RegExUtil;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RegExUtilTest {

    private RegExUtil regExUtil = new RegExUtil();

    @Test
    public void testBreakText() {
        String text = " abcde  ABCDE   абвгде  аб   ";
        String[] actual = regExUtil.breakText(text);
        String[] expected = {"abcde", "ABCDE", "абвгде", "аб"};
        assertEquals(actual, expected);
    }
}