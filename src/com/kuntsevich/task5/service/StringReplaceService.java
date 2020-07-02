package com.kuntsevich.task5.service;

import com.kuntsevich.task5.exception.InvalidDataException;

public interface StringReplaceService {

    String replaceSymbol(String text, char symbol, int index) throws InvalidDataException;

    String replaceSubstring(String text, String substr, String newSubstr) throws InvalidDataException;

    String replaceWordsWithLength(String text, int length, String newSubstr) throws InvalidDataException;
}
