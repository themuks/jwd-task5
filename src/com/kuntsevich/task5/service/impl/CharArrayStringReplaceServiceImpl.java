package com.kuntsevich.task5.service.impl;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.service.StringReplaceService;
import com.kuntsevich.task5.util.CharArrayUtil;
import com.kuntsevich.task5.validator.StringParamsValidator;

public class CharArrayStringReplaceServiceImpl implements StringReplaceService {

    private CharArrayUtil charArrayUtil = new CharArrayUtil();

    @Override
    public String replaceSymbol(String text, char symbol, int index) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || !validator.validatePosition(index)) {
            throw new InvalidDataException("String is null or invalid position value");
        }
        char[][] words = charArrayUtil.breakText(text.toCharArray());
        for (char[] word : words) {
            if (index < word.length) {
                word[index] = symbol;
            }
        }
        return charArrayUtil.joinCharArrays(' ', words);
    }

    @Override
    public String replaceSubstring(String text, String substr, String newSubstr) throws InvalidDataException {
        if (text == null || substr == null || newSubstr == null) {
            throw new InvalidDataException("String is null");
        }
        char[][] words = charArrayUtil.breakText(text.toCharArray());
        char[] substrCharArray = substr.toCharArray();
        char[] newSubstrCharArray = newSubstr.toCharArray();
        for (int i = 0; i < words.length; i++) {
            char[] word = words[i];
            int result = charArrayUtil.indexOfSubstring(word, substrCharArray);
            char[] newWord = word;
            while (result >= 0) {
                newWord = new char[word.length - substrCharArray.length + newSubstrCharArray.length];
                System.arraycopy(word, 0, newWord, 0, result);
                System.arraycopy(newSubstrCharArray, 0, newWord, result, newSubstrCharArray.length);
                for (int j = 0; j < word.length - substrCharArray.length - result; j++) {
                    newWord[result + newSubstrCharArray.length + j] = word[result + substrCharArray.length + j];
                }
                result = charArrayUtil.indexOfSubstring(newWord, substrCharArray);
            }
            words[i] = newWord;
        }
        return charArrayUtil.joinCharArrays(' ', words);
    }

    @Override
    public String replaceWordsWithLength(String text, int length, String newSubstr) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || newSubstr == null || !validator.validateLength(length)) {
            throw new InvalidDataException("String is null or invalid length value");
        }
        char[][] words = charArrayUtil.breakText(text.toCharArray());
        for (int i = 0; i < words.length; i++) {
            if (words[i].length == length) {
                words[i] = newSubstr.toCharArray();
            }
        }
        return charArrayUtil.joinCharArrays(' ', words);
    }
}
