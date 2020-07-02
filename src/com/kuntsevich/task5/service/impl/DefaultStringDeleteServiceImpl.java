package com.kuntsevich.task5.service.impl;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.service.StringDeleteService;
import com.kuntsevich.task5.validator.StringParamsValidator;

public class DefaultStringDeleteServiceImpl implements StringDeleteService {

    @Override
    public String deleteAllExceptLetters(String text) throws InvalidDataException {
        if (text == null) {
            throw new InvalidDataException("String is null");
        }
        String symbols = "abcdefghijklmnopqrstuvwxyzабвгдеёжзиёклмнопрстуфхцчшщъыьэюя";
        String[] words = breakText(text);
        for (int i = 0; i < words.length; i++) {
            String wordDup = words[i].toLowerCase();
            StringBuilder word = new StringBuilder(words[i]);
            for (int j = 0; j < word.length(); j++) {
                boolean flag = false;
                for (int k = 0; k < symbols.length(); k++) {
                    char symbol = symbols.charAt(k);
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
        String symbols = "QWRTYPSDFGHJKLZXCVBNMБВГДЖЗЙКЛМНПРСТФХЦЧШЩ";
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == length) {
                String word = words[i].toUpperCase();
                boolean flag = false;
                for (int j = 0; j < symbols.length(); j++) {
                    char symbol = symbols.charAt(j);
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
        return String.join(" ", words);
    }

    private String[] breakText(String text) {
        text = text.strip();
        int wordCount = calcWordCount(text);
        String[] words = new String[wordCount];
        int i = 0;
        int wordIndex = 0;
        boolean flag = false;
        StringBuilder sb = null;
        while (i < text.length()) {
            if (text.charAt(i) == ' ') {
                flag = false;
                if (sb != null) {
                    words[wordIndex] = sb.toString();
                    sb = null;
                    wordIndex++;
                }
            } else {
                if (!flag) {
                    sb = new StringBuilder();
                    flag = true;
                }
                sb.append(text.charAt(i));
            }
            i++;
        }
        if (sb != null) {
            words[wordIndex] = sb.toString();
        }
        return words;
    }

    private int calcWordCount(String text) {
        int i = 0;
        int wordCount = 0;
        boolean flag = false;
        while (i < text.length()) {
            if (text.charAt(i) == ' ') {
                flag = false;
            } else {
                if (!flag) {
                    wordCount++;
                    flag = true;
                }
            }
            i++;
        }
        return wordCount;
    }
}
