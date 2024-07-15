
/**
 * Write a description of class StudentMarks here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class StudentMarks {
    public static void main(String[] args) {
        int noStudents = 30;
        float[] marks = new float[noStudents];
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Assignment");
        // Enter Assignment name
        String assigName = scanner.nextLine();
        
        System.out.println("Marks");
        //Enter marks
        for (int i = 0; i < noStudents; i++) {
            boolean validInput = false;
            while (!validInput) {
                //To get error message if the input is not a number
                try {
                    System.out.print("Enter mark for student " + (i + 1) + ": ");
                    float input = scanner.nextFloat();
                    
                    // Check if marks are between 0 and 30
                    if (input >= 0 && input <= 30) {
                        marks[i] = input;
                        validInput = true;
                    } else {
                        System.out.println("Please enter marks again, marks should be between 0 and 30");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Only numbers allowed!!.");
                    scanner.next(); // Consume the invalid input
                }
            }
            scanner.nextLine(); // Consume the newline character
        }
        
        // Calculate highest and lowest marks
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
        
        // Print assignment name and marks
        System.out.println("Assignment: " + assigName);
        for (int j = 0; j < noStudents; j++) {
            System.out.println("Student " + (j + 1) + " Mark: " + marks[j]);
        }
        
        // Calculate mean
        float sum = 0;
        for (int i = 0; i < noStudents; i++) {
            sum += marks[i];
        }
        float mean = sum / noStudents;

        // Calculate standard deviation
        float sumOfSquares = 0;
        for (int i = 0; i < noStudents; i++) {
            float deviation = marks[i] - mean;
            sumOfSquares += deviation * deviation;
        }
        float variance = sumOfSquares / noStudents;
        float stndDev = (float) Math.sqrt(variance);
        //Print highest and lowest marks
        System.out.println("Highest mark: " + highMark);
        System.out.println("Lowest mark: " + lowMark);
        // Print mean and standard deviation
        System.out.println("Mean: " + mean);
        System.out.println("Standard Deviation: " + stndDev);
    }
}




