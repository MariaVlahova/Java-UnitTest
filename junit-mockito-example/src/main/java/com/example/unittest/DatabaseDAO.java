package com.example.unittest;

public class DatabaseDAO
{
    public boolean save(String fileName) {
        if(fileName.contains("test")){
            return false;
        }
        System.out.println("Saved in database");
        return true;
    }
    public boolean save2(String fileName) {
        if(fileName.contains("test")){
            throw new RuntimeException("test");
        }
        System.out.println("Saved in database");
        return true;
    }
}