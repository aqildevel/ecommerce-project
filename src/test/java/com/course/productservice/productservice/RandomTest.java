package com.course.productservice.productservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomTest {
    @Test
    void testIsOnePlusOneIsTwo(){
        int i = 1+1; //arrange + act

        //Assert => check against the expected value, and always hard code expected value
//        assert  i == 2;
        assertEquals(2, 2);
    }

    @Test
    public void testAddNumber(){
        //units test work on 3A framework
        //arrange
        int a = 2;
        int b = 3;
        //act
        int res = Calculator.addNumbers(2,3);
        //assert
//        assert res == 5;
        /*expected value => value that we want to get from the function
        actual value => value that we are getting from the function*/

        assertEquals(5, res, "actual value that we are getting from function is not equal to expected value" );
    }
    @Test
    public void testSubstractNumber(){
        int a = 5;
        int b = 10;
        int res = Calculator.substractNumbers(a, b);
//        assert res == -5;
        assertEquals(-5, res);
    }

    /*
    In one test case we can have multiple assert statements, and the TC will pass
iff all the assert statements will pass, even if one assert statement fails, TC fails.


In some of the test cases we might need to check if the function is throwing
an Exception or not.

Scenario :
If we want to test the time taken by a particular function to get executed. we should go for a library
assert library
 */
}
