package com.erdenbatuhan.object;

/*
 * Project   : CourseQuerySystem
 * Class     : Room.java
 * Developer : Batuhan Erden
 */

public class Room {

	private String roomCode;

	public Room(String roomCode) {
		this.roomCode = roomCode;
	}

	public void printData() {
		System.out.println("                              {");
		System.out.println("                                   RoomCode: " + roomCode);
		System.out.println("                              }");
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
}