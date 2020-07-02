package com.kuntsevich.task5.service;

import com.kuntsevich.task5.exception.InvalidDataException;

public interface StringDeleteService {

    String deleteAllExceptLetters(String text) throws InvalidDataException;

    String deleteWordsWithLengthOnConsonant(String text, int length) throws InvalidDataException;
}
