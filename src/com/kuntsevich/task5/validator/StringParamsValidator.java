package com.kuntsevich.task5.validator;

public class StringParamsValidator {

    public boolean validateLength(int length) {
        return length > 0;
    }

    public boolean validatePosition(int position) {
        return position > 0;
    }
}
