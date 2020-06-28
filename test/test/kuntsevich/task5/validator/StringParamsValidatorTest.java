package test.kuntsevich.task5.validator;

import com.kuntsevich.task5.validator.StringParamsValidator;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringParamsValidatorTest {

    private StringParamsValidator validator = new StringParamsValidator();

    @Test
    public void testValidateLengthPositive() {
        assertTrue(validator.validateLength(100));
    }

    @Test
    public void testValidateLengthNegative() {
        assertFalse(validator.validateLength(0));
    }

    @Test
    public void testValidatePositionPositive() {
        assertTrue(validator.validatePosition(3));
    }

    @Test
    public void testValidatePositionNegative() {
        assertFalse(validator.validatePosition(0));
    }
}