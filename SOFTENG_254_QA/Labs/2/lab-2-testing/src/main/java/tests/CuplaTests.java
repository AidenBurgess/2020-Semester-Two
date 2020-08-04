package main.java.tests;

import static org.junit.Assert.*;


import org.junit.Test;

public class CuplaTests {

	@Test
	public void first() {
		System.out.println("Za warudo");
	}
	
	@Test
	public void secondo() {
		System.out.println("Hunter x Hunter");
	}

	  @Test(timeout=100)
	  public void infinite() {
	    while (true) {
//	      System.out.println("here I am!");
	    }
	  }
	  
}
