package com.kuntsevich.task5.util;

public class RegExUtil {

    public String[] breakText(String text) {
        return text.strip().split("\\s+");
    }
}
