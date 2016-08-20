/*
 * Project   : CourseQuerySystem
 * Class     : StudentTest.java
 * Developer : Batuhan Erden
 */

import static org.junit.Assert.*;
import org.junit.*;

public class StudentTest {
	
	private Student student = new Student("Batuhan Erden", "S004345");
	private Course course1 = new Course("CS", "102", "A", null, null);
	private Course course2 = new Course("ACCT", "201", "A", null, null);
	private Course course3 = new Course("EE", "203", "A", null, null);
	
	@Test
	public void testInitializeWeeklyCoursePlan() {
		assertEquals("", student.getWeeklyCoursePlan()[4][8]);
	}
	
	@Test
	public void testAddCourse() {		
		student.addCourse(course1);
		student.addCourse(course2);
		
		assertEquals(2, student.getCoursesTaken().size());
	}
	
	@Test
	public void testGetName() {
		assertEquals("Batuhan Erden", student.getName());
	}
	
	@Test
	public void testGetId() {
		assertEquals("S004345", student.getId());
	}
	
	@Test
	public void testGetCoursesTaken() {
		student.addCourse(course3);
		
		assertEquals(course3, student.getCoursesTaken().get(0));
	}
}