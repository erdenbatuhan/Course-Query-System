/*
 * Project   : CourseQuerySystem
 * Class     : CourseQuerySystemController.java
 * Developer : Batuhan Erden
 */

import java.util.*;
import org.json.simple.*;

public class CourseQuerySystemController {

	private CoursesOffered coursesOffered;
	private CourseQuerySystemView view;

	public CourseQuerySystemController(JSONArray jsonData) {
		coursesOffered = new CoursesOffered(jsonData);
		view = new CourseQuerySystemView(this);

		view.takeUserInputAsInt();
	}

	public void processInput(int userInputAsInt) {
		if (userInputAsInt == 0) {
			System.exit(0);
		} else if (userInputAsInt == 1) {
			coursesOffered.takeUserInputToCreateCourse();
		} else if (userInputAsInt == 2) {
			view.takeUserInputToAddStudent();
		} else if (userInputAsInt == 3) {
			String userInputAsString = view.getUserInputAsString("a student ID (e.g. -> S004345)");
			printWeeklyCoursePlanOfStudent(userInputAsString);
		} else if (userInputAsInt == 4) {
			view.printUniqueList(" Instructors", getAllInstructors());
		} else if (userInputAsInt == 5) {
			view.printUniqueList(" Rooms", getAllRooms());
		} else if (userInputAsInt == 6) {
			view.printUniqueList(" Subject Names", getAllSubjectNames());
		} else if (userInputAsInt == 7) {
			view.printUniqueList(" Course Numbers", getAllCourseNumbers());
		} else {
			processQuery(userInputAsInt);
		}
	}

	private void processQuery(int userInputAsInt) {
		String userInputAsString = "";

		if (userInputAsInt == 8) {
			userInputAsString = view.getUserInputAsString("a room code (e.g. -> CK:EF: 511)");
			view.printCourses(getCoursesBySpecificRoom(userInputAsString.toUpperCase()));
		} else if (userInputAsInt == 9) {
			userInputAsString = view.getUserInputAsString("a day (e.g. -> Wednesday)");
			view.printCourses(getCoursesBySpecificDay(userInputAsString));
		} else if (userInputAsInt == 10) {
			userInputAsString = view.getUserInputAsString("an instructor name and surname (e.g. -> FURKAN KIRAÇ)");
			view.printCourses(getCoursesBySpecificInstructor(userInputAsString.toUpperCase()));
		} else if (userInputAsInt == 11) {
			userInputAsString = view.getUserInputAsString("a course no (e.g. -> 202)");
			view.printCourses(getCoursesBySpecificCourseNo(userInputAsString));
		} else if (userInputAsInt == 12) {
			userInputAsString = view.getUserInputAsString("a subject name (e.g. -> CS)");
			view.printCourses(getCoursesBySpecificSubjectName(userInputAsString.toUpperCase()));
		} else if (userInputAsInt == 13) {
			view.printCourses(getCoursesThatStartInTheMorning());
		} else {
			System.out.println("--> Error: The input must be between [0-13]");
		}
	}

	public void addCourseToStudent(Student student, String courseEntered) {
		for (Course course : coursesOffered.getCourses())
			if (courseEntered.equals(course.toString())) {
				student.addCourse(course);
				return;
			}
		
		System.out.println(courseEntered + " does not exist.");		
	}
	
	public void printWeeklyCoursePlanOfStudent(String studentId) {
		for (Course course : coursesOffered.getCourses())
			for (Student student : course.getStudents())
				if (studentId.equals(student.getId())) {
					student.printWeeklyCoursePlan();
					return;
				}
		
		System.out.println("---> There is no student whose ID is " + studentId + ".");
	}
	
	public List<String> getAllInstructors() {
		List<String> instructors = new ArrayList<String>();

		for (Course course : coursesOffered.getCourses())
			for (Instructor instructor : course.getInstructors())
				instructors.add(instructor.getFullName());
		
		return instructors;
	}

	public List<String> getAllRooms() {
		List<String> rooms = new ArrayList<String>();

		for (Course course : coursesOffered.getCourses())
			for (Schedule schedule : course.getSchedules())
				for (MeetingTime meetingTime : schedule.getMeetingTimes())
					for (Room room : meetingTime.getRooms())
						rooms.add(room.getRoomCode());

		return rooms;
	}

	public List<String> getAllSubjectNames() {
		List<String> courses = new ArrayList<String>();

		for (Course course : coursesOffered.getCourses())
			courses.add(course.getSubjectName());

		return courses;
	}
	
	public List<String> getAllCourseNumbers() {
		List<String> courses = new ArrayList<String>();

		for (Course course : coursesOffered.getCourses())
			courses.add(course.getCourseNo());

		return courses;
	}
	
	public List<Course> getCoursesBySpecificRoom(String specificRoom) {
		List<Course> courses = new ArrayList<Course>();

		for (Course course : coursesOffered.getCourses())
			innerLoop:
			for (Schedule schedule : course.getSchedules())
				for (MeetingTime meetingTime : schedule.getMeetingTimes())
					for (Room room : meetingTime.getRooms())
						if (specificRoom.equals(room.getRoomCode())) {
							courses.add(course);
							break innerLoop;
						}

		return courses;
	}

	public List<Course> getCoursesBySpecificDay(String specificDay) {
		List<Course> courses = new ArrayList<Course>();

		for (Course course : coursesOffered.getCourses())
			innerLoop: 
			for (Schedule schedule : course.getSchedules())
				for (MeetingTime meetingTime : schedule.getMeetingTimes())
					if (specificDay.equals(meetingTime.getDay())) {
						courses.add(course);
						break innerLoop;
					}

		return courses;
	}

	public List<Course> getCoursesBySpecificInstructor(String specificInstructor) {
		List<Course> courses = new ArrayList<Course>();

		for (Course course : coursesOffered.getCourses())
			innerLoop: 
			for (Instructor instructor : course.getInstructors())
				if (specificInstructor.equals(instructor.getFullName())) {
					courses.add(course);
					break innerLoop;
				}

		return courses;
	}

	public List<Course> getCoursesBySpecificCourseNo(String specificCourseNo) {
		List<Course> courses = new ArrayList<Course>();

		for (Course course : coursesOffered.getCourses())
			if (specificCourseNo.equals(course.getCourseNo()))
				courses.add(course);

		return courses;
	}

	public List<Course> getCoursesBySpecificSubjectName(String specificSubjectName) {
		List<Course> courses = new ArrayList<Course>();

		for (Course course : coursesOffered.getCourses())
			if (specificSubjectName.equals(course.getSubjectName()))
				courses.add(course);

		return courses;
	}
	
	public List<Course> getCoursesThatStartInTheMorning() {
		List<Course> courses = new ArrayList<Course>();

		for (Course course : coursesOffered.getCourses())
			innerloop:
			for (Schedule schedule : course.getSchedules())
				for (MeetingTime meetingTime : schedule.getMeetingTimes()) {
					int integerValueOfStartHour = MeetingTime.getIntegerValueOfHour(meetingTime.getStartHour());
					if (integerValueOfStartHour >= 840 && integerValueOfStartHour <= 1140) {
						courses.add(course);
						break innerloop;
					}
				}

		return courses;
	}
}