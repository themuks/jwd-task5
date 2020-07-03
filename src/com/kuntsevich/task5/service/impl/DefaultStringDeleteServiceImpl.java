package com.kuntsevich.task5.service.impl;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.service.StringDeleteService;
import com.kuntsevich.task5.util.DefaultStringUtil;
import com.kuntsevich.task5.validator.StringParamsValidator;

public class DefaultStringDeleteServiceImpl implements StringDeleteService {

    private DefaultStringUtil defaultStringUtil = new DefaultStringUtil();

    private static final String LOWER_LETTERS = "abcdefghijklmnopqrstuvwxyzабвгдеёжзиёклмнопрстуфхцчшщъыьэюя";
    private static final String UPPER_CONSONANTS = "QWRTYPSDFGHJKLZXCVBNMБВГДЖЗЙКЛМНПРСТФХЦЧШЩ";

    @Override
    public String deleteAllExceptLetters(String text) throws InvalidDataException {
        if (text == null) {
            throw new InvalidDataException("String is null");
        }
        String[] words = defaultStringUtil.breakText(text);
        for (int i = 0; i < words.length; i++) {
            String wordDup = words[i].toLowerCase();
            StringBuilder word = new StringBuilder(words[i]);
            for (int j = 0; j < word.length(); j++) {
                boolean flag = false;
                for (int k = 0; k < LOWER_LETTERS.length(); k++) {
                    char symbol = LOWER_LETTERS.charAt(k);
                    if (wordDup.charAt(j) == symbol) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    word.setCharAt(j, ' ');
                }
            }
            words[i] = word.toString();
        }
        return defaultStringUtil.joinWords(words);
    }

    @Override
    public String deleteWordsWithLengthOnConsonant(String text, int length) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || !validator.validateLength(length)) {
            throw new InvalidDataException("String is null or invalid length value");
        }
        String[] words = defaultStringUtil.breakText(text);
        String emptyString = "";
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == length) {
                String word = words[i].toUpperCase();
                boolean flag = false;
                for (int j = 0; j < UPPER_CONSONANTS.length(); j++) {
                    char symbol = UPPER_CONSONANTS.charAt(j);
                    if (word.charAt(0) == symbol) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    words[i] = emptyString;
                }
            }
        }
        return defaultStringUtil.joinWords(words);
    }
}
