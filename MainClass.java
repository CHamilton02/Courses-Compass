package nineToFourGPA;

import java.util.*;

public class MainClass {

	public static void main(String[] args) {
		DoublyLinkedList<Course> courses = new DoublyLinkedList<Course>();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to your YorkU Courses Information Menu. We will begin by entering your course data.");
		
		String courseName = "";
		int creditCount = 0;
		String courseGrade = "";
		boolean validGrade = false;
		boolean exists;
		String userChoice = "lol";
		boolean validInput = false;
		
		System.out.print("Please enter your course name: ");
		
		courseName = scanner.nextLine();
		
		while (!courseName.isEmpty()) {
			System.out.print("Now enter the number of credits " + courseName + " was worth: ");
			
			while (!validInput) {
				try {
					creditCount = scanner.nextInt();
					if (creditCount > 9 || creditCount < 3) {
						throw new Exception();
					}
					validInput = true;
				}
				catch (InputMismatchException ex1) {
					System.out.print("Input mismatch, please enter an integer value: ");
					scanner.nextLine();
				}
				catch (Exception ex2) {
					System.out.print("Invalid credit count, please re-enter: ");
					scanner.nextLine();
				}
			}
			
			validInput = false;
			scanner.nextLine();
			
			System.out.print("Finally, enter the LETTER grade you received (A+, A, B+, B, C+, C, D+, D, F): ");
			
			while (!validGrade) {
				try {
					courseGrade = scanner.nextLine();
					if (validGrade(courseGrade)) {
						throw new Exception();
					}
					validGrade = true;
				}
				catch (InputMismatchException ex1) {
					System.out.print("Input mismatch, please enter an integer value: ");
				}
				catch (Exception ex2) {
					System.out.print("Invalid letter, please re-enter: ");
				}
			}
			
			validGrade = false;
			
			Course tempCourse = new Course(courseName, creditCount, courseGrade);
			
			courses.insertTail(tempCourse);
			
			System.out.print("\nPlease enter the name for the next course you would like to add (Press enter without entering anything to continue to course menu): "); 
			
			courseName = scanner.nextLine();
		}
		
		while (!userChoice.isEmpty()) {
			System.out.println("\nYorkU Course Information Menu\nType 1: 4.0 GPA Calculator\nType 2: 9.0 GPA Calculator\n"
					+ "Type 3: Find course details\nType 4: Remove course\nType 5: Add course\n"
					+ "Type 6: Display all course details\nType anything else to end program.");
			userChoice = scanner.nextLine();
			
			switch (userChoice) {
				case "1":
					System.out.printf("Your calculated 4.0 GPA is %.2f\n", fourGPA(courses));
					break;
				case "2":
					System.out.printf("Your calculated 9.0 GPA is %.2f\n", nineGPA(courses));
					break;
				case "3":
					System.out.print("Please enter the desired course name: ");
					courseName = scanner.nextLine();
					exists = exists(courses, courseName);
					while(!exists) {
						System.out.print("Course does not exist. Please enter a valid name: ");
						courseName = scanner.nextLine();
						exists = exists(courses, courseName);
					}
					System.out.println(findCourse(courses, courseName));
					exists = false;
					break;
				case "4":
					System.out.print("Please enter the name of the desired course to delete: ");
					courseName = scanner.nextLine();
					exists = exists(courses, courseName);
					while(!exists) {
						System.out.print("Course does not exist. Please enter a valid name: ");
						courseName = scanner.nextLine();
						exists = exists(courses, courseName);
					}
					deleteCourse(courses, courseName);
					System.out.println("Course successfully deleted.\n");
					break;
				case "5":
					System.out.print("Please enter your course name: ");
					courseName = scanner.nextLine();
					System.out.print("Now enter the number of credits " + courseName + " was worth: ");
					creditCount = scanner.nextInt();
					while (creditCount > 9 || creditCount < 3) {
						System.out.print("Invalud credit count, please re-enter: ");
						creditCount = scanner.nextInt();
					}
					scanner.nextLine();
					System.out.print("Finally, enter the LETTER grade you received (A+, A, B+, B, C+, C, D+, D, F): ");
					courseGrade = scanner.nextLine();
					validGrade = validGrade(courseGrade);
					while (validGrade) {
						System.out.print("Invalid letter, please re-enter: ");
						courseGrade = scanner.nextLine();
					}
					Course tempCourse = new Course(courseName, creditCount, courseGrade);
					courses.insertTail(tempCourse);
					System.out.println("Course successfully added.\n");
					break;
				case "6":
					System.out.println("Here are the details of all your courses:");
					displayCourses(courses);
					break;
				default:
					System.out.println("Thank you for using YorkU Course Module, goodbye.");
					userChoice = "";
					break;
			}
		}
	}
	
	private static boolean validGrade(String courseGrade) {
		return !courseGrade.equals("A+") && !courseGrade.equals("A") && !courseGrade.equals("B+") && !courseGrade.equals("B")
				&& !courseGrade.equals("C+") && !courseGrade.equals("C") && !courseGrade.equals("D+") && !courseGrade.equals("D")
				&&  !courseGrade.equals("F");
	}
	
	private static double fourGPA(DoublyLinkedList<Course> courses) {
		double tempSum = 0;
		double totalCredits = 0;
		DoublyLinkedList.Node<Course> course = courses.nodeFirst();
		
		while (course.getNext() != null) {
			tempSum += course.getElement().getCreditAmount() * course.getElement().get4GPAValue();
			totalCredits += course.getElement().getCreditAmount();
			course = course.getNext();
		}
		
		return tempSum / totalCredits;
	}
	
	private static double nineGPA(DoublyLinkedList<Course> courses) {
		double tempSum = 0;
		double totalCredits = 0;
		DoublyLinkedList.Node<Course> course = courses.nodeFirst();
		
		while (course.getNext() != null) {
			tempSum += course.getElement().getCreditAmount() * course.getElement().get9GPAValue();
			totalCredits += course.getElement().getCreditAmount();
			course = course.getNext();
		}
		
		return tempSum / totalCredits;
	}
	
	private static boolean exists(DoublyLinkedList<Course> courses, String courseName) {
		boolean exists = false;
		DoublyLinkedList.Node<Course> course = courses.nodeFirst();
		
		while (course.getNext() != null) {
			if (course.getElement().getCourseName().equals(courseName))
				exists = true;
			course = course.getNext();
		}
		
		return exists;
	}
	
	private static String findCourse(DoublyLinkedList<Course> courses, String courseName) {
		String details = "";
		DoublyLinkedList.Node<Course> course = courses.nodeFirst();
		
		while (course.getNext() != null) {
			if (course.getElement().getCourseName().equals(courseName))
				return course.getElement().toString();
			course = course.getNext();
		}
		
		return details;
	}
	
	private static void deleteCourse(DoublyLinkedList<Course> courses, String courseName) {
		DoublyLinkedList.Node<Course> course = courses.nodeFirst();
		
		while (course.getNext() != null) {
			if (course.getElement().getCourseName().equals(courseName))
				courses.delete(course);
			course = course.getNext();
		}
	}
	
	private static void displayCourses(DoublyLinkedList<Course> courses) {
		DoublyLinkedList.Node<Course> course = courses.nodeFirst();
		
		while (course.getNext() != null) {
			System.out.println(course.getElement());
			course = course.getNext();
		}
	}
}
