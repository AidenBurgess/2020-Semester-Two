package softeng254.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import softeng254.coverage.Student;

public class StudentTests {

	@Test
	public void constructWith3Args() {
		xd(true, false);
		xd(true, true);
	}
	
	public void xd(boolean one, boolean two) {
		if (one && two) {
			System.out.println("XXD");
		}
	}
	
	@Test
	public void nullFirstName() {
		new Student(null, "Vern", "Burgess");
	}
	

}
