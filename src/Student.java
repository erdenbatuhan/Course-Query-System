/*
 * Project   : CourseQuerySystem
 * Class     : Student.java
 * Developer : Batuhan Erden
 */

import java.util.*;

public class Student {

	private static final String[] DAYS = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
	private String name, id;
	private ArrayList<Course> coursesTaken = new ArrayList<Course>();
	private String[][] weeklyCoursePlan = new String[5][24];

	public Student(String name, String id) {
		this.name = name;
		this.id = id;

		initializeWeeklyCoursePlan();
	}

	private void initializeWeeklyCoursePlan() {
		for (int i = 0; i < weeklyCoursePlan.length; i++) {
			for (int j = 0; j < weeklyCoursePlan[i].length; j++) {
				weeklyCoursePlan[i][j] = "";
			}
		}
	}

	public void addCourse(Course course) {
		for (Course courseTaken : coursesTaken)
			if (courseTaken.toString().equals(course.toString())) {
				System.out.println("----> You cannot take the same course twice.");
				return;
			}

		coursesTaken.add(course);
		course.getStudents().add(this);

		findFreeTimePeriodToAddCourse(course);
	}

	private void findFreeTimePeriodToAddCourse(Course course) {
		String courseInfo = course.getSubjectName() + course.getCourseNo() + "." + course.getSectionNo();

		for (Schedule schedule : course.getSchedules()) {
			for (MeetingTime meetingTime : schedule.getMeetingTimes()) {
				String day = meetingTime.getDay().toLowerCase();
				int integerValueOfDay = IntegerValueOfDay.valueOf(day).getIntegerValueOfDay();
				int integerValueOfStartHour = MeetingTime.getIntegerValueOfHour(meetingTime.getStartHour());
				int integerValueOfFinishHour = MeetingTime.getIntegerValueOfHour(meetingTime.getFinishHour());

				if (isScheduleAvailable(integerValueOfDay, integerValueOfStartHour, integerValueOfFinishHour)) {
					addCourseToSchedule(courseInfo, meetingTime, integerValueOfDay, integerValueOfStartHour, integerValueOfFinishHour);

					return;
				}
			}
		}

		System.out.println("----> " + courseInfo + " could not be added because you have another course at that time or "
									+ courseInfo + " does not have any meeting time.");
	}

	private boolean isScheduleAvailable(int integerValueOfDay, int integerValueOfStartHour, int integerValueOfFinishHour) {
		int startingPoint = integerValueOfStartHour / 100;
		int endingPoint = integerValueOfFinishHour / 100;

		/* Check if starting point of the plan contains ending point of another course */
		if (weeklyCoursePlan[integerValueOfDay][startingPoint] != "")
			if (weeklyCoursePlan[integerValueOfDay][startingPoint].contains("["))
				return false;

		for (int i = startingPoint + 1; i <= endingPoint; i++)
			if (weeklyCoursePlan[integerValueOfDay][i] != "" && weeklyCoursePlan[integerValueOfDay][i].contains("-"))
				return false;

		return true;
	}

	private void addCourseToSchedule(String courseInfo, MeetingTime meetingTime, int integerValueOfDay, int integerValueOfStartHour, int integerValueOfFinishHour) {
		if (weeklyCoursePlan[integerValueOfDay][integerValueOfStartHour / 100].endsWith("]")) {
			weeklyCoursePlan[integerValueOfDay][integerValueOfStartHour / 100] += " " + courseInfo + "[" + meetingTime.getStartHour();
		} else {
			String previousValue = weeklyCoursePlan[integerValueOfDay][integerValueOfStartHour / 100];
			weeklyCoursePlan[integerValueOfDay][integerValueOfStartHour / 100] = " " + courseInfo + "[" + meetingTime.getStartHour() + previousValue;
		}

		if (weeklyCoursePlan[integerValueOfDay][integerValueOfFinishHour / 100].endsWith("]")) {
			weeklyCoursePlan[integerValueOfDay][integerValueOfFinishHour / 100] += " - " + meetingTime.getFinishHour() + "]";
		} else {
			String previousValue = weeklyCoursePlan[integerValueOfDay][integerValueOfFinishHour / 100];
			weeklyCoursePlan[integerValueOfDay][integerValueOfFinishHour / 100] = " - " + meetingTime.getFinishHour() + "]" + previousValue;
		}
	}

	public void printWeeklyCoursePlan() {
		for (int i = 0; i < weeklyCoursePlan.length; i++) {
			System.out.print(DAYS[i] + ": ");

			for (int j = 0; j < weeklyCoursePlan[i].length; j++)
				if (weeklyCoursePlan[i][j] != "")
					System.out.print(weeklyCoursePlan[i][j]);

			System.out.println();
		}
	}

	public void printData() {
		System.out.println("          {");
		System.out.println("               Student ID: " + id + ",");
		System.out.println("          }");
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public ArrayList<Course> getCoursesTaken() {
		return coursesTaken;
	}

	public String[][] getWeeklyCoursePlan() {
		return weeklyCoursePlan;
	}
}

enum IntegerValueOfDay {

	monday(0), tuesday(1), wednesday(2), thursday(3), friday(4);
	private int integerValue;

	private IntegerValueOfDay(int integerValue) {
		this.integerValue = integerValue;
	}

	public int getIntegerValueOfDay() {
		return integerValue;
	}
}