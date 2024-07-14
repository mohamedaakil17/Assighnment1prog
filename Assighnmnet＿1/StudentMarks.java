
/**
 * Write a description of class StudentMarks here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner;

public class StudentMarks
{
    public static void main(String[] args) 
    {
        int noStudents = 30;
        float[] marks = new float[noStudents];
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Assighnment");
        //Enter Assighnment name
        String assigName = scanner.nextLine();
        
        System.out.println("Marks");
        
        for(int i=0; i<noStudents; i++)
        {
            float input = scanner.nextFloat();
            //Check for marks is between 0 and 30
            if (input >= 0 && input <= 30)
            {
                marks[i] = input;
            } else {
                System.out.println("Please enter marks again, marks should be between 0 and 30");
                i--; // to re-enter the mark for the same student
            }
            scanner.nextLine(); // Consume the newline character
        }
        
        float highMark = marks[0];
        float lowMark = marks[0];

        for (int i = 1; i < noStudents; i++) {
            if (marks[i] > highMark) {
                highMark = marks[i];
            }
            if (marks[i] < lowMark) {
                lowMark = marks[i];
            }
        }
        
        
        System.out.println("Assighnment: "+assigName);
        for(int j=0; j<noStudents; j++)
        {
            System.out.println(marks[j]);
        }
        
        System.out.println("Highest mark: " + highMark);
        System.out.println("Lowest mark: " + lowMark);
    }
}
