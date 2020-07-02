package com.kuntsevich.task5.service.impl;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.service.StringDeleteService;
import com.kuntsevich.task5.validator.StringParamsValidator;

public class CharArrayStringDeleteServiceImpl implements StringDeleteService {

    @Override
    public String deleteAllExceptLetters(String text) throws InvalidDataException {
        if (text == null) {
            throw new InvalidDataException("String is null");
        }
        char[] symbols = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'ё',
                'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц',
                'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
        char[][] words = breakText(text.toCharArray());
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length; j++) {
                boolean flag = false;
                for (int k = 0; k < symbols.length; k++) {
                    char symbol = symbols[k];
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
        return joinCharArrays(' ', words);
    }

    @Override
    public String deleteWordsWithLengthOnConsonant(String text, int length) throws InvalidDataException {
        StringParamsValidator validator = new StringParamsValidator();
        if (text == null || !validator.validateLength(length)) {
            throw new InvalidDataException("String is null or invalid length value");
        }
        char[][] words = breakText(text.toCharArray());
        char[] emptyCharArray = new char[0];
        char[] symbols = {'Q', 'W', 'R', 'T', 'Y', 'P', 'S', 'D', 'F', 'G', 'H',
                'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', 'Б', 'В', 'Г',
                'Д', 'Ж', 'З', 'Й', 'К', 'Л', 'М', 'Н', 'П', 'Р', 'С', 'Т', 'Ф',
                'Х', 'Ц', 'Ч', 'Ш', 'Щ'};
        for (int i = 0; i < words.length; i++) {
            if (words[i].length == length) {
                boolean flag = false;
                for (int j = 0; j < symbols.length; j++) {
                    char symbol = symbols[j];
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
        return joinCharArrays(' ', words);
    }

    private char[][] breakText(char[] text) {
        text = stripCharArray(text);
        int wordCount = calcWordCount(text);
        char[][] words = new char[wordCount][];
        int i = 0;
        int wordIndex = 0;
        boolean flag = false;
        char[] word = null;
        int charIndex = 0;
        while (i < text.length) {
            if (text[i] == ' ') {
                flag = false;
                if (word != null) {
                    char[] newWord = new char[charIndex];
                    if (charIndex >= 0) {
                        System.arraycopy(word, 0, newWord, 0, charIndex);
                    }
                    words[wordIndex] = newWord;
                    word = null;
                    wordIndex++;
                }
            } else {
                if (!flag) {
                    word = new char[255];
                    charIndex = 0;
                    flag = true;
                }
                word[charIndex] = text[i];
                charIndex++;
            }
            i++;
        }
        if (word != null) {
            char[] newWord = new char[charIndex];
            if (charIndex >= 0) {
                System.arraycopy(word, 0, newWord, 0, charIndex);
            }
            words[wordIndex] = newWord;
        }
        return words;
    }

    private char[] stripCharArray(char[] text) {
        int lastCharIndex = -1;
        int firstCharIndex = -1;
        int i = 0;
        while (firstCharIndex == -1 && i < text.length) {
            if (text[i] != ' ') {
                firstCharIndex = i;
            }
            i++;
        }
        i = text.length - 1;
        while (lastCharIndex == -1 && i >= 0) {
            if (text[i] != ' ') {
                lastCharIndex = i;
            }
            i--;
        }
        int length = lastCharIndex - firstCharIndex + 1;
        char[] result = new char[length];
        for (i = firstCharIndex; i <= lastCharIndex; i++) {
            result[i - firstCharIndex] = text[i];
        }
        return result;
    }

    private int calcWordCount(char[] text) {
        int i = 0;
        int wordCount = 0;
        boolean flag = false;
        while (i < text.length) {
            if (text[i] == ' ') {
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

    private String joinCharArrays(char delimiter, char[][] words) {
        int charCount = 0;
        for (int i = 0; i < words.length; i++) {
            charCount += words[i].length;
        }
        char[] result = new char[charCount + words.length - 1];
        int charIndex = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i] != null) {
                for (int j = 0; j < words[i].length; j++) {
                    result[charIndex] = words[i][j];
                    charIndex++;
                }
                if (i != words.length - 1) {
                    result[charIndex] = delimiter;
                    charIndex++;
                }
            }
        }
        return String.valueOf(result);
    }
}
