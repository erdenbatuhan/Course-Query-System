package com.erdenbatuhan.main;

/*
 * Project   : CourseQuerySystem
 * Class     : CourseQuerySystem.java
 * Developer : Batuhan Erden
 */

import com.erdenbatuhan.mvc.CourseQuerySystemController;
import java.io.*;
import org.json.simple.*;

public class CourseQuerySystem {

	private static final String JSON_FILE = "data/CoursesOffered.json";
	private JSONArray jsonData = null;

	public static void main(String[] args) {
		new CourseQuerySystem();
	}

	public CourseQuerySystem() {
		getDataFromFile();
		new CourseQuerySystemController(jsonData);
	}

	private void getDataFromFile() {
		FileReader fileReader = null;

		try {
			fileReader = new FileReader(JSON_FILE);
			jsonData = (JSONArray) JSONValue.parse(fileReader);
		} catch (FileNotFoundException e) {
			System.err.println("'" + JSON_FILE + "' does not exist.. Program is being terminated..");
			System.exit(0);
		}
		
		finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.err.println("FileReader could not be closed.. Program is being terminated..");
				System.exit(0);
			}
		}
	}

	public static String getJsonFile() {
		return JSON_FILE;
	}

	public JSONArray getJsonData() {
		return jsonData;
	}

	public void setJsonData(JSONArray jsonData) {
		this.jsonData = jsonData;
	}
}