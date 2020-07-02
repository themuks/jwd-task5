package com.kuntsevich.task5.service.impl;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.service.StringDeleteService;
import com.kuntsevich.task5.validator.StringParamsValidator;

public class RegExStringDeleteServiceImpl implements StringDeleteService {

    @Override
    public String deleteAllExceptLetters(String text) throws InvalidDataException {
        if (text == null) {
            throw new InvalidDataException("String is null");
        }
        String[] words = breakText(text);
        String regex = "[^\\p{Alpha}|[а-яА-Я]]";
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll(regex, " ");
        }
        return String.join(" ", words);
    }

    @Override
    public String deleteWordsWithLengthOnConsonant(String text, int length) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || !validator.validateLength(length)) {
            throw new InvalidDataException("String is null or invalid length value");
        }
        String[] words = breakText(text);
        String emptyString = "";
        String regex = "^([QWRTYPSDFGHJKLZXCVBNM]|[БВГДЖЗЙКЛМНПРСТФХЦЧШЩ]).*";
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == length) {
                String word = words[i].toUpperCase();
                if (word.matches(regex)) {
                    words[i] = emptyString;
                }
            }
        }
        return String.join(" ", words);
    }

    private String[] breakText(String text) {
        return text.strip().split("\\s+");
    }
}
