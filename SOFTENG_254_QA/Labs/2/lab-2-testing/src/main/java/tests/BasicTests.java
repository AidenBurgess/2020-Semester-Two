package main.java.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.After;
import org.junit.Test;

public class BasicTests {

	
	@Before
	public void b4() {
		System.out.println("This method is called before");
	}

	@Before
	public void b42() {
		System.out.println("B42 New York City Bus");
	}
	
	@Test
//	@Ignore
    public void yo() {
      System.out.println("Yo! We're testing here!");
    }
    
    @Test
    public void onePlusOneIsTwo() {
    	assertEquals(1+1, 2);
    }
    
    @Test
    public void oneTimesOneIsOne() {
    	assertEquals(1*1, 1);
    }
    
	@After
	public void afta() {
		System.out.println("This method is called after");
	}

}
