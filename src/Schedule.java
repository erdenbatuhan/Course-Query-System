/*
 * Project   : CourseQuerySystem
 * Class     : Schedule.java
 * Developer : Batuhan Erden
 */

import java.text.*;
import java.util.*;
import org.json.simple.*;

public class Schedule {

	private static final DateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
	private Date startDate;
	private Date finishDate;
	private ArrayList<MeetingTime> meetingTimes = new ArrayList<MeetingTime>();

	public Schedule(String startDate, String finishDate, JSONArray meetingTimeData) {
		this.startDate = getDateFormatOf(startDate);
		this.finishDate = getDateFormatOf(finishDate);

		if (meetingTimeData != null)
			extractMeetingTimeFromData(meetingTimeData);
	}
	
	private Date getDateFormatOf(String date) {
		Date dateFormat = null;
		
		/* Decide whether the date entered in JSON Format or Normal Date Format */
		if (date.length() > 10) {
			dateFormat = new Date(Long.parseLong(date.replaceAll("\\D", "")));
		} else {
			try {
				dateFormat = (Date) DATE_FORMATTER.parse(date);
			} catch (ParseException e) {
				System.err.println("Please enter a valid date.");
			}
		}
		
		return dateFormat;
	}

	private void extractMeetingTimeFromData(JSONArray meetingTimeData) {
		for (int i = 0; i < meetingTimeData.size(); i++) {
			JSONObject meetingTime = (JSONObject) meetingTimeData.get(i);
			String day = (String) meetingTime.get("Day");
			String startHour = (String) meetingTime.get("StartHour");
			String finishHour = (String) meetingTime.get("FinishHour");
			JSONArray room = (JSONArray) meetingTime.get("Room");

			meetingTimes.add(new MeetingTime(day, startHour, finishHour, room));
		}
	}
	
	public void takeUserInputToCreateMeetingTime() {
		System.out.println("             Meeting Time [ (enter -1 for at least one input to stop)");
		
		while (true) {
			System.out.println("               {");
			System.out.print("                 Enter a Day (e.g. -> Tuesday): ");
			String day = CourseQuerySystemView.SCANNER.nextLine();
			
			System.out.print("                 Enter start hour (e.g. -> 11:40): ");
			String startHour = CourseQuerySystemView.SCANNER.nextLine();
			
			System.out.print("                 Enter finish hour (e.g. -> 13:30): ");
			String finishHour = CourseQuerySystemView.SCANNER.nextLine();
			
			if (day.equals("-1") || startHour.equals("-1") || finishHour.equals("-1"))
				break;
			
			createMeetingTime(day, startHour, finishHour);
			System.out.println("               }");
		}
		
		System.out.println("               }");
		System.out.println("             ]");
	}

	private void createMeetingTime(String day, String startHour, String finishHour) {
		MeetingTime newMeetingTime = new MeetingTime(day, startHour, finishHour, null);
		
		newMeetingTime.takeUserInputToCreateRoom();
		meetingTimes.add(newMeetingTime);
	}

	public void printData() {
		System.out.println("          {");
		System.out.println("               StartDate: " + startDate + ",");
		System.out.println("               FinishDate: " + finishDate + ",");
		System.out.println("               Meeting Time: [");

		for (int i = 0; i < meetingTimes.size(); i++) {
			meetingTimes.get(i).printData();
		}

		System.out.println("               ]");
		System.out.println("          }");
	}

	public static DateFormat getDateFormatter() {
		return DATE_FORMATTER;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public ArrayList<MeetingTime> getMeetingTimes() {
		return meetingTimes;
	}

	public void setMeetingTimes(ArrayList<MeetingTime> meetingTimes) {
		this.meetingTimes = meetingTimes;
	}
}