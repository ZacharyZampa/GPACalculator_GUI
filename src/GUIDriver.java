import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileNotFoundException;

public class GUIDriver extends Component {
    private JTextField enterName;
    private JLabel creditLabel;
    private JLabel nameLabel;
    private JTextField enterGrade;
    private JLabel gradeLabel;
    private JButton submit;
    private JButton delete;
    private JButton clear;
    private JButton importCoursesButton;
    private JButton exportCoursesButton;
    private JPanel mainPanel;
    private JTextField totalGPA;
    private JFormattedTextField enterCredits;
    private JTextArea textArea;

    // GPA Calculator Variables
    private String className;
    private double classGPA;
    private int classCredits;
    private GPAList courseList = new GPAList();
    private JFileChooser fc;

    protected void runnable() {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new GUIDriver().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    protected void updateScreen() {
        String gpaStr = String.format("Total GPA = %.2f", courseList.getGPA());
        totalGPA.setText(gpaStr);
        textArea.setText("");
        textArea.append(courseList.listCourses());
    }

    protected GUIDriver() {
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ClassAndGrade createdClass = new ClassAndGrade(className, classGPA, classCredits);
                courseList.addCourse(createdClass);
                System.out.println(createdClass.toString());
                textArea.append(createdClass.toString() + "\n");
                String gpaStr = String.format("Total GPA = %.2f", courseList.getGPA());
                totalGPA.setText(gpaStr);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                courseList.removeLast();
                updateScreen();
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                courseList.clearList();
                totalGPA.setText("0.0");
                textArea.setText("");
            }
        });
        exportCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(GUIDriver.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String fileName = fc.getSelectedFile().toString();
                    try {
                        courseList.saveToFile(fileName);
                        updateScreen();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        importCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(GUIDriver.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String fileName = fc.getSelectedFile().toString();
                    try {
                        courseList.pullFromFile(fileName);
                        updateScreen();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        enterCredits.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    classCredits = Integer.parseInt(enterCredits.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"Enter only valid whole numbers");
                }
            }
        });
        enterName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                className = enterName.getText();
            }
        });
        enterGrade.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String inputGPA = enterGrade.getText().toUpperCase();
                classGPA = GPA_Calculator.letterToGPA(inputGPA);
            }
        });
    }

}
