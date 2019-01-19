/**
 * @author Zachary Zampa
 * Create the template for creating a class and the grade received in the class
 */
public class ClassAndGrade {
    private double classGPA;
    private String className;
    private int classCredits;
    
    
    //The class and GPA constructor
    public ClassAndGrade(String className, double classGPA, int classCredits) {
	this.setClassName(className);
	this.setClassGPA(classGPA);
	this.setClassCredits(classCredits);
    } // end ClassAndGrade constructor
    
    
    public String getClassName() {
	return className;
    }
    
    public double getClassGPA() {
	return classGPA;
    }
    
    public int getClassCredits() {
	return classCredits;
    }
    
    
    public void setClassName(String className) {
	this.className = className;
    }
    
    public void setClassGPA(double classGPA) {
	this.classGPA = classGPA;
    }
    
    public void setClassCredits(int classCredits) {
        this.classCredits = classCredits;
    }


    // allow for printing of class and corresponding info
    public String toString() {
        return this.getClassName() + "; " +
                this.getClassGPA() + "; " +
                this.getClassCredits();

    }

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


}
