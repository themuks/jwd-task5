package com.kuntsevich.task5.service.impl;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.service.StringReplaceService;
import com.kuntsevich.task5.util.DefaultStringUtil;
import com.kuntsevich.task5.util.RegExUtil;
import com.kuntsevich.task5.validator.StringParamsValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExStringReplaceServiceImpl implements StringReplaceService {

    private RegExUtil regExUtil = new RegExUtil();
    private DefaultStringUtil defaultStringUtil = new DefaultStringUtil();

    private static final String SINGLE_CHARACTER_REGEX = "^(\\p{L}{%d})(\\p{L})(\\p{L}*)";
    private static final String GROUP_REPLACE = "$1%s$3";
    private static final String STRING_WITH_LENGTH_REGEX = ".{%d}";

    @Override
    public String replaceSymbol(String text, char symbol, int index) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || !validator.validatePosition(index)) {
            throw new InvalidDataException("String is null or invalid position value");
        }
        String[] words = regExUtil.breakText(text);
        for (int i = 0; i < words.length; i++) {
            if (index < words[i].length()) {
                String regex = String.format(SINGLE_CHARACTER_REGEX, index);
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(words[i]);
                String groupReplace = String.format(GROUP_REPLACE, symbol);
                words[i] = matcher.replaceAll(groupReplace);
            }
        }
        return defaultStringUtil.joinWords(words);
    }

    @Override
    public String replaceSubstring(String text, String substr, String newSubstr) throws InvalidDataException {
        if (text == null || substr == null || newSubstr == null) {
            throw new InvalidDataException("String is null");
        }
        String[] words = regExUtil.breakText(text);
        String regex = Pattern.quote(substr);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            words[i] = word.replaceAll(regex, newSubstr);
        }
        return defaultStringUtil.joinWords(words);
    }

    @Override
    public String replaceWordsWithLength(String text, int length, String newSubstr) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || newSubstr == null || !validator.validateLength(length)) {
            throw new InvalidDataException("String is null or invalid length value");
        }
        String[] words = regExUtil.breakText(text);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String regex = String.format(STRING_WITH_LENGTH_REGEX, length);
            if (word.matches(regex)) {
                words[i] = newSubstr;
            }
        }
        return defaultStringUtil.joinWords(words);
    }
}
