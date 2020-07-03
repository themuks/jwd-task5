package com.kuntsevich.task5.util;

public class DefaultStringUtil {

    public String[] breakText(String text) {
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

    public int calcWordCount(String text) {
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

    public String joinWords(String[] words) {
        return String.join(" ", words);
    }
}
