import java.util.Scanner;

/**
 * @author Zachary Zampa
 * Requires ClassAndGrade.javam GPAList.java, GUIDriver.java files
 * Purpose: This calculates a GPA based on the credits and grade for that class
 */

public class GPA_Calculator {

	public static void main(String[] args) {
		// launch the GUI App
		GUIDriver driver = new GUIDriver();
		driver.runnable();
	} // end main method




	// Method converts letter grade to Miami University GPA scale
	public static double letterToGPA(String letter) {
		double classGPA = -1.0;
		switch (letter) {
		case "A": 
			classGPA = 4.0;
			break;
		case "A-": 
			classGPA = 3.70;
			break;
		case "B+":
			classGPA = 3.30;
			break;
		case "B":
			classGPA = 3.00;
			break;
		case "B-":
			classGPA = 2.70;
			break;
		case "C+":
			classGPA = 2.30;
			break;
		case "C":
			classGPA = 2.00;
			break;
		case "C-":
			classGPA = 1.70;
		case "D+":
			classGPA = 1.30;
			break;
		case "D":
			classGPA = 1.00;
			break;
		case "D-":
			classGPA = 0.70;
			break;
		case "F":
			classGPA = 0;
			break;
		default: 
			System.out.println("Error: Please enter valid letter grade. ");
			break;
		}

		return classGPA;

	} // end lettertoGPA method



	public static char menu() {
		System.out.println("\n\n\t===============================");
		System.out.println("\tGPA Calculator Application"); 
		System.out.println("\tDeveloped by: Zachary Zampa "); 
		System.out.println("\tDate: 2018/12/20");
		System.out.println("\t===============================");
		System.out.println("\tA. Add a Class to the Calculation List");
		System.out.println("\tB. List Classes in the Calculation List");
		System.out.println("\tC. Caculate the Overall GPA");
		System.out.println("\tD. Save the Class List to a File");
		System.out.println("\tE. Import Existing .txt File");
		System.out.println("\tF. Clear Current List");
		System.out.println("\tG. Remove Last Entry");
		System.out.println("\tX. Exit");
		System.out.println();
		System.out.print("Option:"); 

		return keyboard.nextLine().toUpperCase().charAt(0);

	} // end menu method


	// read course info from user input
	public static ClassAndGrade readCourseInfofromKeyboard() {
		String className = null;
		double classGPA = -1.0;
		int classCredits = -1;

		do {
			System.out.print("Enter the class name: ");
			className = keyboard.nextLine();
		} while(className == null);

		do {
			System.out.print("Enter the letter grade: ");
			classGPA = letterToGPA(keyboard.next().toUpperCase());
			keyboard.nextLine();
		} while(classGPA == -1.0);

		do {
			System.out.print("Enter how may credits the class was: ");
			if (keyboard.hasNextInt()) {
				classCredits = keyboard.nextInt();
				keyboard.nextLine();
			}
			else {
				System.out.println("Error: Please enter a valid credit number. ");
				keyboard.nextLine();
			}
		} while(classCredits == -1);

		ClassAndGrade createdClass = new ClassAndGrade(className, classGPA, classCredits);
		System.out.println(className + " added to course list");
		return createdClass; // return info of added class

	} // end readCourseInfofromKeyboard method


	private static String needFileExt(String str) {
		if (str.endsWith(".txt") || str.endsWith(".rtx")) {}
		else {
			str += ".txt";  // if no file extension specified; assumes .txt
		}

		return str;
	}


	public static Scanner keyboard = new Scanner(System.in); // keyboard input

}