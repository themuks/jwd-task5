package com.kuntsevich.task5.service.impl;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.service.StringReplaceService;
import com.kuntsevich.task5.validator.StringParamsValidator;

import java.util.regex.Pattern;

public class RegExStringReplaceServiceImpl implements StringReplaceService {

    @Override
    public String replaceSymbol(String text, char symbol, int index) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || !validator.validatePosition(index)) {
            throw new InvalidDataException("String is null or invalid position value");
        }
        String[] words = breakText(text);
        // Don't need to search symbol with regex
        for (int i = 0; i < words.length; i++) {
            if (index < words[i].length()) {
                String word = words[i];
                StringBuilder sb = new StringBuilder(word);
                sb.setCharAt(index, symbol);
                words[i] = sb.toString();
            }
        }
        return String.join(" ", words);
    }

    @Override
    public String replaceSubstring(String text, String substr, String newSubstr) throws InvalidDataException {
        if (text == null || substr == null || newSubstr == null) {
            throw new InvalidDataException("String is null");
        }
        String[] words = breakText(text);
        String regex = Pattern.quote(substr);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            words[i] = word.replaceAll(regex, newSubstr);
        }
        return String.join(" ", words);
    }

    @Override
    public String replaceWordsWithLength(String text, int length, String newSubstr) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || newSubstr == null || !validator.validateLength(length)) {
            throw new InvalidDataException("String is null or invalid length value");
        }
        String[] words = breakText(text);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.matches(".{" + length + "}")) {
                words[i] = newSubstr;
            }
        }
        return String.join(" ", words);
    }

    private String[] breakText(String text) {
        return text.strip().split("\\s+");
    }
}
