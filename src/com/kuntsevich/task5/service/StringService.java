package com.kuntsevich.task5.service;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.validator.StringParamsValidator;

import java.util.Arrays;

public class StringService {

    public String replaceSymbol(String text, char symbol, int position) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || !validator.validatePosition(position)) {
            throw new InvalidDataException("String is null or invalid position value");
        }
        String[] words = breakText(text);
        for (int i = 0; i < words.length; i++) {
            if (position < words[i].length()) {
                char[] chars = words[i].toCharArray();
                chars[position] = symbol;
                words[i] = String.valueOf(chars);
            }
        }
        return String.join(" ", words);
    }

    public String replaceSymbolAfterSymbol(String text, String prevSubstring, String newSubstring) throws InvalidDataException {
        if (text == null || prevSubstring == null || newSubstring == null) {
            throw new InvalidDataException("String is null");
        }
        // TODO: 29.06.2020
        return null;
    }

    public String replaceWords(String text, int length, String newSubstring) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || newSubstring == null || !validator.validateLength(length)) {
            throw new InvalidDataException("String is null or invalid length value");
        }
        String[] words = breakText(text);
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == length) {
                words[i] = newSubstring;
            }
        }
        return String.join(" ", words);
    }

    public String deleteExceptLetters(String text) throws InvalidDataException {
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

    public String deleteWordsOnConsonant(String text, int length) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || !validator.validateLength(length)) {
            throw new InvalidDataException("String is null or invalid length value");
        }
        // FIXME: 29.06.2020 Not working
        String[] words = breakText(text);
        String emptyString = "";
        String regex = "^([QWRTYPSDFGHJKLZXCVBNM]|[БВГДЖЗЙКЛМНПРСТФХЦЧШЩ])";
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == length) {
                if (words[i].toUpperCase().matches(regex)) {
                    words[i] = emptyString;
                }
            }
        }
        return String.join(" ", words);
    }

    public String[] breakText(String text) {
        return text.strip().split("\\s+");
    }
}
