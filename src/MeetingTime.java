import java.util.ArrayList;
import org.json.simple.*;

public class MeetingTime {

	private String day;
	private String startHour;
	private String finishHour;
	private ArrayList<Room> rooms = new ArrayList<Room>();

	public MeetingTime(String day, String startHour, String finishHour, JSONArray roomData) {
		this.day = day;
		this.startHour = startHour;
		this.finishHour = finishHour;

		if (roomData != null)
			extractRoomsFromData(roomData);
	}

	private void extractRoomsFromData(JSONArray roomData) {
		for (int i = 0; i < roomData.size(); i++) {
			JSONObject meetingTime = (JSONObject) roomData.get(i);
			String roomCode = (String) meetingTime.get("RoomCode");

			rooms.add(new Room(roomCode));
		}
	}

	public void takeUserInputToCreateRoom() {
		System.out.println("                 Room [ (enter -1 for at least one input to stop)");
		
		while (true) {
			System.out.println("                    {");
			System.out.print("                      Enter a roomCode (e.g. -> CK:FEAS: G16): ");
			String roomCode = CourseQuerySystemView.SCANNER.nextLine();
	
			if (roomCode.equals("-1"))
				break;
			
			createRoom(roomCode);	
			System.out.println("                    }");
		}
		
		System.out.println("                    }");
		System.out.println("                 ]");
	}

	private void createRoom(String roomCode) {
		Room newRoom = new Room(roomCode);	
		
		rooms.add(newRoom);
	}

	public void printData() {
		System.out.println("                    {");
		System.out.println("                         Day: " + day + ",");
		System.out.println("                         StartHour: " + startHour + ",");
		System.out.println("                         FinishHour: " + finishHour + ",");
		System.out.println("                         Room: [");

		for (int i = 0; i < rooms.size(); i++) {
			rooms.get(i).printData();
		}

		System.out.println("                         ]");
		System.out.println("                    }");
	}
	
	public static int getIntegerValueOfHour(String hour) {
		String integerValue = "";

		for (int i = 0; i < hour.length(); i++) {
			if (hour.charAt(i) == ' ')
				integerValue += "0";
			else if (i != 2)
				integerValue += hour.charAt(i);
		}

		return Integer.parseInt(integerValue);
	}

	public String getDay() {
		return day;
	}

	public String getStartHour() {
		return startHour;
	}

	public String getFinishHour() {
		return finishHour;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
}