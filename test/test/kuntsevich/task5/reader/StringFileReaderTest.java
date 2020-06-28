package test.kuntsevich.task5.reader;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.reader.StringFileReader;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

public class StringFileReaderTest {

    private StringFileReader stringFileReader = new StringFileReader();

    @Test
    public void testReadPositive() {
        File file = new File("resources/file/text.txt");
        try {
            String actual = stringFileReader.read(file);
            String expected = "Lorem ipsum dolor sit amet ";
            assertEquals(actual, expected);
        } catch (InvalidDataException e) {
            fail();
        }
    }

    @Test(expectedExceptions = InvalidDataException.class)
    public void testReadException() throws InvalidDataException {
        File file = new File("resources/file/invalidName.123");
        stringFileReader.read(file);
    }
}