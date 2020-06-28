package com.kuntsevich.task5.reader;

import java.util.Scanner;

public class StringConsoleReader {

    public String read(int count) {
        StringBuilder sb = new StringBuilder();
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < count; i++) {
            String line = in.nextLine();
            sb.append(line);
            sb.append(' ');
        }
        return sb.toString();
    }
}
