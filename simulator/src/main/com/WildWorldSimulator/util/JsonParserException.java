package com.WildWorldSimulator.util;

public class JsonParserException extends Exception {
    public JsonParserException(String message, Exception e) {
        System.out.println(message);
        e.printStackTrace();
    }
}
