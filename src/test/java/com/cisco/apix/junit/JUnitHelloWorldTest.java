package com.cisco.apix.junit;
 
import static org.junit.Assert.assertTrue;
 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cisco.apix.junit.JUnitHelloWorld;
 
public class JUnitHelloWorldTest {
 
    @Before
    public void before() {
        System.out.println("Before Test Case");
    }
 
    @Test
    public void isGreaterTest() {
        System.out.println("Hello World!");
        JUnitHelloWorld helloWorld = new JUnitHelloWorld();
        assertTrue("Num 1 is greater than Num 2", helloWorld.isGreater(7, 6));
        
    }
 
    @After
    public void after() {
        System.out.println("After Test Case");
    }
 
}

