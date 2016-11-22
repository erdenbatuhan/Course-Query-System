/*
 * Project   : CourseQuerySystem
 * Class     : Instructor.java
 * Developer : Batuhan Erden
 */

public class Instructor {

	private String name;
	private String surname;
	private boolean isPrimary;

	public Instructor(String name, String surname, boolean isPrimary) {
		this.name = name;
		this.surname = surname;
		this.isPrimary = isPrimary;
	}

	public void printData() {
		System.out.println("          {");
		System.out.println("               Name: " + name + ",");
		System.out.println("               Surname: " + surname + ",");
		System.out.println("               IsPrimary: " + isPrimary + ",");
		System.out.println("          }");
	}

	public String getFullName() {
		return name + " " + surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
}