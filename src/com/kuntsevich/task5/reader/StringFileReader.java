package com.kuntsevich.task5.reader;

import com.kuntsevich.task5.exception.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringFileReader {

    public String read(File file) throws InvalidDataException {
        StringBuilder sb = new StringBuilder();
        if (!file.exists() || !file.isFile()) {
            throw new InvalidDataException("Can't read from file");
        }
        try (Scanner in = new Scanner(file)) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                sb.append(line);
                sb.append(' ');
            }
        } catch (FileNotFoundException e) {
            throw new InvalidDataException("Can't read from file");
        }
        return sb.toString();
    }
}
