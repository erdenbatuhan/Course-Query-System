/*
 * Project   : CourseQuerySystem
 * Class     : CoursesOffered.java
 * Developer : Batuhan Erden
 */

import java.util.*;
import org.json.simple.*;

public class CoursesOffered {

	private ArrayList<Course> courses = new ArrayList<Course>();

	public CoursesOffered(JSONArray jsonData) {
		extractCoursesFromData(jsonData);
	}

	private void extractCoursesFromData(JSONArray jsonData) {
		for (int i = 0; i < jsonData.size(); i++) {
			JSONObject jsonElement = (JSONObject) jsonData.get(i);
			JSONObject course = (JSONObject) jsonElement.get("Course");

			extractCourseFromData(course);
		}
	}

	private void extractCourseFromData(JSONObject course) {
		String subjectName = (String) course.get("SubjectName");
		String courseNo = (String) course.get("CourseNo");
		String sectionNo = (String) course.get("SectionNo");
		JSONArray instructors = (JSONArray) course.get("Instructors");
		JSONArray schedule = (JSONArray) course.get("Schedule");

		courses.add(new Course(subjectName, courseNo, sectionNo, instructors, schedule));
	}
	
	public void takeUserInputToCreateCourse() {
		System.out.println("   Course [");
		System.out.println("      {");
		
		System.out.print("        Enter Subject Name (e.g. -> CS): ");
		String subjectName = CourseQuerySystemView.SCANNER.nextLine();
		
		System.out.print("        Enter Course No. (e.g. -> 102): ");
		String courseNo = CourseQuerySystemView.SCANNER.nextLine();
		
		System.out.print("        Enter Section No. (e.g. -> B): ");
		String sectionNo = CourseQuerySystemView.SCANNER.nextLine();
		
		Course newCourse = new Course(subjectName, courseNo, sectionNo, null, null);
		
		newCourse.takeUserInputToCreateInstructor();
		newCourse.takeUserInputToCreateSchedule();
		
		courses.add(newCourse);
		System.out.println("      }");
		System.out.println("   ]");
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
}