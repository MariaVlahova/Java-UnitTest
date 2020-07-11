package com.example.unittest;

public class DataCollectingService {
    private String getInfoFromUSB(){
        return "test";
    }
    public String collectData(){
        return getInfoFromUSB();
    }
}
