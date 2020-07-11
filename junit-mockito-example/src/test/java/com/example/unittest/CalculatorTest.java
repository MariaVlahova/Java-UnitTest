package com.example.unittest;

import org.junit.*;

public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp(){
        calculator=new Calculator();
    }

    @Test
    public void test_sum(){
        Assert.assertNotNull(calculator);
        double result = calculator.sumUp(5,7, Calculator.Operation.Plus);
        Assert.assertEquals("Sum is correct",12,12);
        Assert.assertNotEquals("Sum is correct",12,0);
    }
    //@Ignore
    @Test(expected = RuntimeException.class)
    public void test_sum_exception(){
        Assert.assertNotNull(calculator);
        double result = calculator.sumUp(5,7, Calculator.Operation.Other);
    }

    @After
    public void tearDown(){
        calculator=null;
    }
}
