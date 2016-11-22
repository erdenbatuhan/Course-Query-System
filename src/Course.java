/*
 * Project   : CourseQuerySystem
 * Class     : Course.java
 * Developer : Batuhan Erden
 */

import java.util.*;
import org.json.simple.*;

public class Course {

	private String subjectName;
	private String courseNo;
	private String sectionNo;
	private ArrayList<Instructor> instructors = new ArrayList<Instructor>();
	private ArrayList<Schedule> schedules = new ArrayList<Schedule>();
	private ArrayList<Student> students = new ArrayList<Student>();

	public Course(String subjectName, String courseNo, String sectionNo, JSONArray instructorsData, JSONArray scheduleData) {
		this.subjectName = subjectName;
		this.courseNo = courseNo;
		this.sectionNo = sectionNo;

		if (instructorsData != null && scheduleData != null) {
			extractInstructorsFromData(instructorsData);
			extractScheduleFromData(scheduleData);
		}
	}

	private void extractInstructorsFromData(JSONArray instructorsData) {
		for (int i = 0; i < instructorsData.size(); i++) {
			JSONObject instructor = (JSONObject) instructorsData.get(i);
			String name = (String) instructor.get("Name");
			String surname = (String) instructor.get("Surname");
			boolean isPrimary = (boolean) instructor.get("IsPrimary");

			instructors.add(new Instructor(name, surname, isPrimary));
		}
	}

	private void extractScheduleFromData(JSONArray scheduleData) {
		for (int i = 0; i < scheduleData.size(); i++) {
			JSONObject schedule = (JSONObject) scheduleData.get(i);
			String startDate = (String) schedule.get("StartDate");
			String finishDate = (String) schedule.get("FinishDate");
			JSONArray meetingTime = (JSONArray) schedule.get("MeetingTime");

			schedules.add(new Schedule(startDate, finishDate, meetingTime));
		}
	}

	public void takeUserInputToCreateInstructor() {
		System.out.println("        Instructors [ (enter -1 for at least one input to stop)");
		
		while (true) {
			System.out.println("           {");
			System.out.print("             Enter Instructor's Name (e.g. -> Robert): ");
			String name = CourseQuerySystemView.SCANNER.nextLine();

			System.out.print("             Enter Instructor's Surname (e.g. -> Verburg): ");
			String surname = CourseQuerySystemView.SCANNER.nextLine();

			System.out.print("             Is Instructor Primary or Not (e.g. -> false): ");
			String isPrimary = CourseQuerySystemView.SCANNER.nextLine();
			
			if (name.equals("-1") || surname.equals("-1") || isPrimary.equals("-1"))
				break;
			
			createInstructor(name, surname, isPrimary);
			System.out.println("           }");
		}

		System.out.println("           }");
		System.out.println("        ]");
	}

	private void createInstructor(String name, String surname, String isPrimary) {
		if (isPrimary.equals("true"))
			instructors.add(new Instructor(name, surname, true));
		else if (isPrimary.equals("false"))
			instructors.add(new Instructor(name, surname, false));
		else
			System.out.println("-------------> IsPrimary can only be false or true.");
	}

	public void takeUserInputToCreateSchedule() {
		System.out.println("        Schedule [ (enter -1 for at least one input to stop)");
		
		while (true) {
			System.out.println("           {");
			System.out.print("             Enter Start Date (e.g. -> 31/12/2015): ");
			String startDate = CourseQuerySystemView.SCANNER.nextLine();

			System.out.print("             Enter Finish Date (e.g. -> 31/12/2016): ");
			String finishDate = CourseQuerySystemView.SCANNER.nextLine();
			
			if (startDate.equals("-1") || finishDate.equals("-1"))
				break;
			
			createSchedule(startDate, finishDate);		
			System.out.println("           }");
		}

		System.out.println("           }");
		System.out.println("        ]");
	}

	private void createSchedule(String startDate, String finishDate) {
		Schedule newSchedule = new Schedule(startDate, finishDate, null);
		
		newSchedule.takeUserInputToCreateMeetingTime();
		schedules.add(newSchedule);
	}

	public void printData() {
		System.out.println("Course: {");
		System.out.println("     SubjectName: " + subjectName + ",");
		System.out.println("     CourseNo: " + courseNo + ",");
		System.out.println("     SectionNo: " + sectionNo + ",");

		printDataElements();

		System.out.println("}");
	}

	private void printDataElements() {
		System.out.println("     Instructors: [");

		for (int i = 0; i < instructors.size(); i++) {
			instructors.get(i).printData();
		}

		System.out.println("     ],");
		System.out.println("     Students: [");

		for (int i = 0; i < students.size(); i++) {
			students.get(i).printData();
		}

		System.out.println("     ],");
		System.out.println("     Schedule: [");

		for (int i = 0; i < schedules.size(); i++) {
			schedules.get(i).printData();
		}

		System.out.println("     ]");
	}

	@Override
	public String toString() {
		return subjectName + courseNo;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getSectionNo() {
		return sectionNo;
	}

	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}

	public ArrayList<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(ArrayList<Instructor> instructors) {
		this.instructors = instructors;
	}

	public ArrayList<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(ArrayList<Schedule> schedules) {
		this.schedules = schedules;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
}