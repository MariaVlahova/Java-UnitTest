package com.example.unittest;

public class Util {
    private static int i=0;

    public static String dataTransformation1(String input1,String input2){
        if(i+2==2){
            throw new RuntimeException("Error");
        }
        return input1+input2;
    }
}
