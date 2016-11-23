package com.erdenbatuhan.mvc;

/*
 * Project   : CourseQuerySystem
 * Class     : CourseQuerySystemView.java
 * Developer : Batuhan Erden
 */

import com.erdenbatuhan.object.*;
import java.text.*;
import java.util.*;

public class CourseQuerySystemView {

	public static final String NEW_LINE = System.lineSeparator();
	public static final Scanner SCANNER = new Scanner(System.in);
	private CourseQuerySystemController controller;
	private int userInputAsInt = -1;

	public CourseQuerySystemView(final CourseQuerySystemController controller) {
		this.controller = controller;

		printUserMenu();
	}
	
	private void printUserMenu() {
		System.out.println("-------------------------------------------------------------- " + NEW_LINE +
						   " Course Query System										   " + NEW_LINE +
				   		   "-------------------------------------------------------------- " + NEW_LINE +
						   "Operations:							   					       " + NEW_LINE +
						   "0.  Terminate the program					                   " + NEW_LINE +
						   "1.  Add a course  											   " + NEW_LINE +
						   "2.  Add a student  											   " + NEW_LINE +
						   "3.  Print student's weekly plan by entering his/her student ID " + NEW_LINE +
						   "4.  List instructors					   					   " + NEW_LINE +
						   "5.  List rooms					 	   				  	       " + NEW_LINE +
						   "6.  List subject names										   " + NEW_LINE +
						   "7.  List course numbers										   " + NEW_LINE +
						   "8.  Query courses by specific room	   				    	   " + NEW_LINE +
						   "9.  Query courses by specific day	  	   					   " + NEW_LINE +
						   "10. Query courses by specific instructor 					   " + NEW_LINE +
						   "11. Query courses by specific course number	   			       " + NEW_LINE +
						   "12. Query courses by specific subject name	   			       " + NEW_LINE +
						   "13. Query courses that start in the morning (08:40 - 11:40)    " + NEW_LINE +
						   "-------------------------------------------------------------- " );
	}

	public void takeUserInputAsInt() {
		while (true) {
			System.out.print("-> Please enter your operation number: ");
			
			try {
				userInputAsInt = Integer.parseInt(SCANNER.nextLine());
				controller.processInput(userInputAsInt);
			} catch (NumberFormatException e) {
				System.out.println("--> Error: The input must be a number");
				userInputAsInt = -1;
			}
			
			printUserMenu();
		}
	}

	public String getUserInputAsString(String chosenData) {
		System.out.print("--> Please enter " + chosenData + ": ");
		String userInputAsString = SCANNER.nextLine();
		
		return userInputAsString;
	}
	
	public void takeUserInputToAddStudent() {
		System.out.print("--> Please enter student's full name: ");
		String fullName = SCANNER.nextLine();
		
		System.out.print("--> Please enter student's ID: ");
		String studentId = SCANNER.nextLine().toUpperCase();
		
		System.out.println("--> Please enter courses taken by the student (e.g. -> CS102): ");
		Student student = new Student(fullName, studentId);
		
		while (true) {
			System.out.print("---> Enter a course (-1 to stop): ");
			String courseEntered = SCANNER.nextLine().toUpperCase();
			
			if (courseEntered.equals("-1"))
				break;
			
			controller.addCourseToStudent(student, courseEntered);
		}
	}

	public void printUniqueList(String printMessage, List<String> list) {
		System.out.println("-----------------");
		System.out.println(printMessage);
		System.out.println("-----------------");
		
		Set<String> uniqueList = new TreeSet<String>(list);
		list = new ArrayList<String>(uniqueList);
		
		/* The "Collator" below is used for UTF-8 sorting */
		Collections.sort(list, Collator.getInstance(new Locale("lt_LT")));

		for (String listItem : list)
			if (listItem.length() != 0)
				System.out.println("~ " + listItem);
	}

	public void printCourses(List<Course> courses) {
		if (courses.size() == 0)
			System.out.println("---> Error: There is no data found");
		else
			for (Course course : courses)
				course.printData();
	}

	public CourseQuerySystemController getController() {
		return controller;
	}

	public void setController(CourseQuerySystemController controller) {
		this.controller = controller;
	}

	public int getUserInputAsInt() {
		return userInputAsInt;
	}

	public void setUserInputAsInt(int userInputAsInt) {
		this.userInputAsInt = userInputAsInt;
	}
}