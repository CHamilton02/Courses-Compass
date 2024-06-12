package nineToFourGPA;

public class Course {
	private String courseName;
	private int creditAmount;
	private String grade;
	
	public Course(String courseName, int creditAmount, String grade) {
		this.courseName = courseName;
		this.creditAmount = creditAmount;
		this.grade = grade;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public int getCreditAmount() {
		return creditAmount;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public double get4GPAValue() {
		switch (grade) {
			case "A+":
				return 4.0;
			case "A":
				return 3.8;
			case "B+":
				return 3.3;
			case "B":
				return 3.0;
			case "C+":
				return 2.3;
			case "C":
				return 2.0;
			case "D+":
				return 1.3;
			case "D":
				return 1.0;
			default:
				return 0;
				
		}
	}
	
	public double get9GPAValue() {
		switch (grade) {
			case "A+":
				return 9.0;
			case "A":
				return 8.0;
			case "B+":
				return 7.0;
			case "B":
				return 6.0;
			case "C+":
				return 5.0;
			case "C":
				return 4.0;
			case "D+":
				return 3.0;
			case "D":
				return 2.0;
			default:
				return 0;
				
		}
	}
	
	@Override
	public String toString() {
		return courseName + " (" + creditAmount + " credits): " + grade;
	}
}
