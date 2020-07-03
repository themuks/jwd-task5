package com.kuntsevich.task5.service.impl;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.service.StringDeleteService;
import com.kuntsevich.task5.util.DefaultStringUtil;
import com.kuntsevich.task5.util.RegExUtil;
import com.kuntsevich.task5.validator.StringParamsValidator;

public class RegExStringDeleteServiceImpl implements StringDeleteService {

    private RegExUtil regExUtil = new RegExUtil();
    private DefaultStringUtil defaultStringUtil = new DefaultStringUtil();

    private static final String LETTERS_REGEX = "[^\\p{Alpha}|[а-яА-Я]]";
    private static final String CONSONANTS_REGEX = "^([QWRTYPSDFGHJKLZXCVBNM]|[БВГДЖЗЙКЛМНПРСТФХЦЧШЩ]).*";
    private static final String EMPTY_STRING = "";

    @Override
    public String deleteAllExceptLetters(String text) throws InvalidDataException {
        if (text == null) {
            throw new InvalidDataException("String is null");
        }
        String[] words = regExUtil.breakText(text);
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll(LETTERS_REGEX, " ");
        }
        return defaultStringUtil.joinWords(words);
    }

    @Override
    public String deleteWordsWithLengthOnConsonant(String text, int length) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || !validator.validateLength(length)) {
            throw new InvalidDataException("String is null or invalid length value");
        }
        String[] words = regExUtil.breakText(text);
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == length) {
                String word = words[i].toUpperCase();
                if (word.matches(CONSONANTS_REGEX)) {
                    words[i] = EMPTY_STRING;
                }
            }
        }
        return defaultStringUtil.joinWords(words);
    }
}
