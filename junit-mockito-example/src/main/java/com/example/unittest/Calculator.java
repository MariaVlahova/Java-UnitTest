package com.example.unittest;

public class Calculator {

    public enum Operation
    {
        Plus,Minus,Obelus,Times,Other
    }
    public double sumUp(int a,int b,Operation op) {
        if (op.equals(Operation.Minus)) {
            return a - b;
        } else if (op.equals(Operation.Plus)) {
            return a + b;
        } else if (op.equals(Operation.Obelus)) {
            return a / b;
        } else if (op.equals(Operation.Times)) {
            return a * b;
        }else{
            throw new RuntimeException(" not valid operation");
        }
    }
}
