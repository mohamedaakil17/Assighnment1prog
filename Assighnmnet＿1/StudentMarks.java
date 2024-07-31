/**
 * Write a description of class StudentMarks here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;

public class StudentMarks {
    public static void main(String[] args) {
        int noStudents = 30;
        float[] marks = new float[noStudents];
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Assignment");
        // Enter Assignment name
        String assigName = scanner.nextLine();

        // Read marks from the file
        try {
            Scanner fileScanner = new Scanner(new File("/Users/muhammedaakil/Assighnmnet＿1/prog5001_students_grade_2022.txt"));
            int i = 0;
            while (fileScanner.hasNextFloat() && i < noStudents) {
                marks[i] = fileScanner.nextFloat();
                i++;
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        } catch (InputMismatchException e) {
            System.out.println("Invalid data format in file.");
            return;
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

        // Print highest and lowest marks
        System.out.println("Highest mark: " + highMark);
        System.out.println("Lowest mark: " + lowMark);
        // Print mean and standard deviation
        System.out.println("Mean: " + mean);
        System.out.println("Standard Deviation: " + stndDev);
    }
}
