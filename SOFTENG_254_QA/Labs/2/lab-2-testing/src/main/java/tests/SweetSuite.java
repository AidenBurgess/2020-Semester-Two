package main.java.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	CuplaTests.class,
	BasicTests.class})
public class SweetSuite { // This is the class we've created
	public void exampleMethod() {
		System.out.println("OregairuXt");
	}
}

