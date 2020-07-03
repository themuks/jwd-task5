package com.kuntsevich.task5.util;

public class CharArrayUtil {

    public char[][] breakText(char[] text) {
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

    public char[] stripCharArray(char[] text) {
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

    public int calcWordCount(char[] text) {
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

    public String joinCharArrays(char delimiter, char[][] words) {
        int charCount = 0;
        for (char[] word : words) {
            charCount += word.length;
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

    public int indexOfSubstring(char[] text, char[] substr) {
        int i = 0;
        int j = 0;
        boolean flag = true;
        int result = -1;
        while (i < text.length && j < substr.length) {
            if (text[i] == substr[j]) {
                if (flag) {
                    result = i;
                    flag = false;
                }
                i++;
                j++;
            } else {
                i++;
                j = 0;
                flag = true;
                result = -1;
            }
        }
        if (j == substr.length) {
            return result;
        }
        return -1;
    }
}
