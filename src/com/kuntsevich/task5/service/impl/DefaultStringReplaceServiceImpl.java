package com.kuntsevich.task5.service.impl;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.service.StringReplaceService;
import com.kuntsevich.task5.util.DefaultStringUtil;
import com.kuntsevich.task5.validator.StringParamsValidator;

public class DefaultStringReplaceServiceImpl implements StringReplaceService {

    private DefaultStringUtil defaultStringUtil = new DefaultStringUtil();

    @Override
    public String replaceSymbol(String text, char symbol, int index) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || !validator.validatePosition(index)) {
            throw new InvalidDataException("String is null or invalid position value");
        }
        String[] words = defaultStringUtil.breakText(text);
        for (int i = 0; i < words.length; i++) {
            if (index < words[i].length()) {
                String word = words[i];
                StringBuilder sb = new StringBuilder(word);
                sb.setCharAt(index, symbol);
                words[i] = sb.toString();
            }
        }
        return defaultStringUtil.joinWords(words);
    }

    @Override
    public String replaceSubstring(String text, String substr, String newSubstr) throws InvalidDataException {
        if (text == null || substr == null || newSubstr == null) {
            throw new InvalidDataException("String is null");
        }
        String[] words = defaultStringUtil.breakText(text);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder sb = new StringBuilder(word);
            int result = sb.indexOf(substr, -1);
            while (result >= 0) {
                sb.delete(result, result + substr.length());
                sb.insert(result, newSubstr);
                result = sb.indexOf(substr, result);
            }
            words[i] = sb.toString();
        }
        return defaultStringUtil.joinWords(words);
    }

    @Override
    public String replaceWordsWithLength(String text, int length, String newSubstr) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || newSubstr == null || !validator.validateLength(length)) {
            throw new InvalidDataException("String is null or invalid length value");
        }
        String[] words = defaultStringUtil.breakText(text);
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == length) {
                words[i] = newSubstr;
            }
        }
        return defaultStringUtil.joinWords(words);
    }
}
