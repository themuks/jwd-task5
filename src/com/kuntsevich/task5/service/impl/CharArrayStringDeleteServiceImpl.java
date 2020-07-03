package com.kuntsevich.task5.service.impl;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.service.StringDeleteService;
import com.kuntsevich.task5.util.CharArrayUtil;
import com.kuntsevich.task5.validator.StringParamsValidator;

public class CharArrayStringDeleteServiceImpl implements StringDeleteService {

    private CharArrayUtil charArrayUtil = new CharArrayUtil();

    private static final char[] LOWER_LETTERS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'а', 'б',
            'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'ё', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с',
            'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
    private static final char[] UPPER_CONSONANTS = {'Q', 'W', 'R', 'T', 'Y', 'P', 'S', 'D', 'F', 'G', 'H',
            'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', 'Б', 'В', 'Г', 'Д', 'Ж', 'З', 'Й', 'К', 'Л',
            'М', 'Н', 'П', 'Р', 'С', 'Т', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ'};

    @Override
    public String deleteAllExceptLetters(String text) throws InvalidDataException {
        if (text == null) {
            throw new InvalidDataException("String is null");
        }
        char[][] words = charArrayUtil.breakText(text.toCharArray());
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length; j++) {
                boolean flag = false;
                for (int k = 0; k < LOWER_LETTERS.length; k++) {
                    char symbol = LOWER_LETTERS[k];
                    if (Character.toLowerCase(words[i][j]) == symbol) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    words[i][j] = ' ';
                }
            }
        }
        return charArrayUtil.joinCharArrays(' ', words);
    }

    @Override
    public String deleteWordsWithLengthOnConsonant(String text, int length) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || !validator.validateLength(length)) {
            throw new InvalidDataException("String is null or invalid length value");
        }
        char[][] words = charArrayUtil.breakText(text.toCharArray());
        char[] emptyCharArray = new char[0];
        for (int i = 0; i < words.length; i++) {
            if (words[i].length == length) {
                boolean flag = false;
                for (int j = 0; j < UPPER_CONSONANTS.length; j++) {
                    char symbol = UPPER_CONSONANTS[j];
                    if (Character.toUpperCase(words[i][0]) == symbol) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    words[i] = emptyCharArray;
                }
            }
        }
        return charArrayUtil.joinCharArrays(' ', words);
    }
}
