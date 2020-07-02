package test.kuntsevich.task5.service.impl;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.service.impl.DefaultStringDeleteServiceImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class DefaultStringDeleteServiceImplTest {

    private DefaultStringDeleteServiceImpl stringService = new DefaultStringDeleteServiceImpl();

    @Test
    public void testDeleteAllExceptLettersPositive() {
        String text = " ABC%, DC1a 2b3$jlkl ";
        try {
            String actual = stringService.deleteAllExceptLetters(text);
            String expected = "ABC   DC a  b  jlkl";
            assertEquals(actual, expected);
        } catch (InvalidDataException e) {
            fail();
        }
    }

    @Test(expectedExceptions = InvalidDataException.class)
    public void testDeleteAllExceptLettersException() throws InvalidDataException {
        String text = null;
        stringService.deleteAllExceptLetters(text);
    }

    @Test
    public void testDeleteWordsWithLengthOnConsonantPositive() {
        String text = "Лорем ипсум долор Сит амет";
        int length = 5;
        try {
            String actual = stringService.deleteWordsWithLengthOnConsonant(text, length);
            String expected = " ипсум  Сит амет";
            assertEquals(actual, expected);
        } catch (InvalidDataException e) {
            fail();
        }
    }

    @Test(expectedExceptions = InvalidDataException.class)
    public void testDeleteWordsWithLengthOnConsonantException() throws InvalidDataException {
        String text = null;
        int length = 0;
        stringService.deleteWordsWithLengthOnConsonant(text, length);
    }
}