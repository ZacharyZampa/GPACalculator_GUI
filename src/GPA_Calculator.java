import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Zachary Zampa
 * 
 * Requires ClassAndGrade.java and GPAList.java files
 * Purpose: This calculates a GPA based on the credits and grade for that class
 */

public class GPA_Calculator extends JPanel{

    JFrame window = new JFrame("GPA Calculator");
    public GPAList courseList = new GPAList();
    private JLabel gpaLabel, creditLabel, courseLabel, calcGPALabel, fileLabel;
    private JTextField gpaField, creditField, courseField, fileField;
    private double calcGPA = 0.0, classGPA = 0.0;
    private String className = "course", fileName = "GPA_List.txt", inputGPA = "A";
    private int classCredits = 0;

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    private static final int FIELD_WIDTH = 11; 
    private static final int TABLE_ROWS = 10; // lines of text
    private static final int TABLE_COLUMNS = 40; // characters per row
    JTextArea textArea;
    JScrollPane scrollPane;
    


    public GPA_Calculator() {
        createWindow();

        // Text Input Fields

        // course input field
        createCourseInput();

        // GPA input field
        createGPAInput();

        // Credit input field
        createCreditInput();

        // File Name input field
        createFileInput();


        // Save Button
        // Save a course list
        createSave();


        // Load Button
        // Load in a course list
        createLoad();


        // Clear Button
        // Clear all classes from the list
        createClear();


        // Calculate Button
        // Calculate in a course list
        createCalculate();

        // Add Button
        // Add course to course list
        createAdd();



        // GPA Calculation Display
        createCalculationDisplay();


        // TODO fix course list table
        // Add Course Table
        textArea = new JTextArea("Course List", TABLE_ROWS, TABLE_COLUMNS);
        textArea.setEditable(false);
        window.add(textArea);
        textArea.setBounds(370, 50, 500, 400);
        scrollPane = new JScrollPane(textArea);
        
       



    } // end GPA_Calculator Method


    public void createWindow() {
        window.setBounds(100, 100, 1, 1);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(this);
        window.setAlwaysOnTop(false); // change to true to keep window on top
        window.setVisible(true);

        window.setLayout(null); // unlock the screen stretching
        window.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    } // end createWinow method
    
    
    public void createCourseInput() {
        courseLabel = new JLabel("Enter the course name: ");
        courseField = new JTextField(FIELD_WIDTH);
        courseField.setText("" + className);
        window.add(courseLabel);
        courseLabel.setBounds(10, 100, 170, 30);
        window.add(courseField);
        courseField.setBounds(170, 100, 150, 30);
        courseField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                className = courseField.getText();

            }
        }); 
    } // end createCourseInput method
    
    
    public void createGPAInput() {
        gpaLabel = new JLabel("Enter the course GPA: ");
        gpaField = new JTextField(FIELD_WIDTH);
        gpaField.setText("" + inputGPA);
        window.add(gpaLabel);
        gpaLabel.setBounds(10, 140, 170, 30);
        window.add(gpaField);
        gpaField.setBounds(170, 140, 150, 30);
        gpaField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                inputGPA = gpaField.getText().toUpperCase();
                classGPA = ClassAndGrade.letterToGPA(inputGPA);
            }
        }); 
    } // end createGPAInput method
    
    
    public void createCreditInput() {
        creditLabel = new JLabel("Enter course credit amount: ");
        creditField = new JTextField(FIELD_WIDTH);
        creditField.setText("" + classCredits);
        window.add(creditLabel);
        creditLabel.setBounds(10, 180, 170, 30);
        window.add(creditField);
        creditField.setBounds(170, 180, 150, 30);
        creditField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                classCredits = Integer.parseInt(creditField.getText());

            }
        }); 
    } // end createCreditInput method
    
    
    public void createFileInput() {
        fileLabel = new JLabel("Enter the desired file name: ");
        fileField = new JTextField(FIELD_WIDTH);
        fileField.setText("" + fileName);
        window.add(fileLabel);
        fileLabel.setBounds(10, window.getHeight()-150, 170, 30);
        window.add(fileField);
        fileField.setBounds(170, window.getHeight()-150, 150, 30);
        fileField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fileName = fileField.getText() + ".txt";

            }
        }); 
    } // end createFileInput method

    
    public void createSave() {
        JButton btnSave = new JButton("Save");
        window.add(btnSave); // add button to the JFrame
        btnSave.setBounds(10, window.getHeight()-75, 100, 30);
        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    courseList.saveToFile(fileName);
                } 
                catch (Exception ex)
                {
                    System.out.println("Error Saving File");
                    ex.printStackTrace();
                }



            }
        }); 
    } // end createSave method
    
    
    public void createLoad() {
        JButton btnLoad = new JButton("Load");
        window.add(btnLoad); // add button to the JFrame
        btnLoad.setBounds(110, window.getHeight()-75, 100, 30);
        btnLoad.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    courseList.pullFromFile(fileName);
                } 
                catch (Exception ex)
                {
                    System.out.println("Error Loading File");
                    ex.printStackTrace();
                }



            }
        }); 
    } // end createLoad method
    
    
    public void createClear() {
        JButton btnClear = new JButton("Clear Classes");
        window.add(btnClear); // add button to the JFrame
        btnClear.setBounds(580, window.getHeight()-75, 150, 30);
        btnClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                courseList.clearList();


            }
        }); 
    } // end createClear method
    
    
    public void createCalculate() {
        JButton btnCalculate = new JButton("Calculate GPA");
        window.add(btnCalculate); // add button to the JFrame
        btnCalculate.setBounds(300, window.getHeight()-75, 150, 30);
        btnCalculate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                calcGPA = courseList.getGPA();
                calcGPALabel.setText("Calculated GPA: " + calcGPA);

            }
        }); 
    } // end createCalculate method
    
    
    public void createAdd() {
        JButton btnAdd = new JButton("Add Course");
        window.add(btnAdd); // add button to the JFrame
        btnAdd.setBounds(169, 220 , 150, 30);
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ClassAndGrade createdClass = new ClassAndGrade(className, classGPA, classCredits);
                courseList.addcourse(createdClass);
                System.out.println(createdClass.toString());
//                textArea.append(createdClass.toString() + "\n");
                

            }
        }); 
    } // end createAdd method
    
    
    public void createCalculationDisplay() {
        calcGPALabel = new JLabel();
        calcGPALabel.setText("Calculated GPA: " + calcGPA);
        window.add(calcGPALabel);
        calcGPALabel.setBounds(300, window.getHeight()-100, 150, 30);
    } // end createCalculationDisplay method

    
    public static void main(String[] args) {
        new GPA_Calculator();

    } // end main method

}