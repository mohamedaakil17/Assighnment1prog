/**
 * Write a description of class StudentMarks here.
 * 
 * @author
 * @version
 */

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StudentMarks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the file name:");
        String fileName = scanner.nextLine();

        String unitName = "";
        ArrayList<String> studentNames = new ArrayList<>();
        ArrayList<Integer> studentIDs = new ArrayList<>();
        ArrayList<float[]> studentMarks = new ArrayList<>();
        ArrayList<Float> totalMarksList = new ArrayList<>();

        // Read data from the file
        try {
            Scanner fileScanner = new Scanner(new File("/Users/muhammedaakil/Assighnmnet＿1/prog5001_students_grade_2022.txt"));
            boolean unitNameRead = false;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();

                // Ignore comment lines
                if (line.startsWith("#")) {
                    continue;
                }

                // Read unit name
                if (!unitNameRead && line.startsWith("Unit")) {
                    unitName = line.split(",")[1].trim();
                    unitNameRead = true;
                    continue;
                }

                // Skip header line
                if (line.startsWith("Last Name")) {
                    continue;
                }

                // Read student data
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String studentName = parts[1].trim() + " " + parts[0].trim();
                    int studentID = Integer.parseInt(parts[2].trim());
                    float assignment1 = parts[3].isEmpty() ? 0 : Float.parseFloat(parts[3].trim());
                    float assignment2 = parts[4].isEmpty() ? 0 : Float.parseFloat(parts[4].trim());
                    float assignment3 = parts[5].isEmpty() ? 0 : Float.parseFloat(parts[5].trim());

                    studentNames.add(studentName);
                    studentIDs.add(studentID);
                    float[] marks = new float[] { assignment1, assignment2, assignment3 };
                    studentMarks.add(marks);
                    float totalMarks = assignment1 + assignment2 + assignment3;
                    totalMarksList.add(totalMarks);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Invalid data format in file.");
            return;
        }

        // Calculate statistics
        int noStudents = studentMarks.size();
        if (noStudents == 0) {
            System.out.println("No student marks found.");
            return;
        }

        float highMark = -1;
        float lowMark = 101;
        float totalMarks = 0;

        for (float total : totalMarksList) {
            if (total > highMark) {
                highMark = total;
            }
            if (total < lowMark) {
                lowMark = total;
            }
            totalMarks += total;
        }

        float mean = totalMarks / noStudents;

        float sumOfSquares = 0;
        for (float total : totalMarksList) {
            float deviation = total - mean;
            sumOfSquares += deviation * deviation;
        }
        float variance = sumOfSquares / noStudents;
        float stndDev = (float) Math.sqrt(variance);

        // Print unit name and student data
        System.out.println("Unit Name: " + unitName);
        for (int i = 0; i < noStudents; i++) {
            System.out.println("Student Name: " + studentNames.get(i) + ", Student ID: " + studentIDs.get(i) + 
                               ", Marks: " + studentMarks.get(i)[0] + ", " + studentMarks.get(i)[1] + ", " + studentMarks.get(i)[2] + 
                               ", Total Mark: " + totalMarksList.get(i));
        }

        // Print highest and lowest marks
        System.out.println("Highest mark: " + highMark);
        System.out.println("Lowest mark: " + lowMark);
        // Print mean and standard deviation
        System.out.println("Mean: " + mean);
        System.out.println("Standard Deviation: " + stndDev);
    }
}
