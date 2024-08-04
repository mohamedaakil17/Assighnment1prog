import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StudentMarks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "";
        boolean validFile = false;

        while (!validFile) {
            System.out.println("Enter the file name:");
            fileName = scanner.nextLine();

            try {
                Scanner fileScanner = new Scanner(new File(fileName));
                validFile = true;  // If no exception, file is valid
                fileScanner.close();  // Close immediately after checking validity
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please enter a valid file name.");
            }
        }

        String unitName = "";
        ArrayList<String> studentNames = new ArrayList<>();
        ArrayList<Integer> studentIDs = new ArrayList<>();
        ArrayList<float[]> studentMarks = new ArrayList<>();
        ArrayList<Float> totalMarksList = new ArrayList<>();

        // Read data from the file
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
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

        // Menu system
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display unit name and all student marks");
            System.out.println("2. Display highest and lowest marks");
            System.out.println("3. Display mean and standard deviation");
            System.out.println("4. Display top 5 students with highest total marks");
            System.out.println("5. Display top 5 students with lowest total marks");
            System.out.println("6. Display students with total marks less than threshold");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Display unit name and all student marks
                    System.out.println("Unit Name: " + unitName);
                    for (int i = 0; i < noStudents; i++) {
                        System.out.println("Student Name: " + studentNames.get(i) + ", Student ID: " + studentIDs.get(i) + 
                                           ", Marks: " + studentMarks.get(i)[0] + ", " + studentMarks.get(i)[1] + ", " + studentMarks.get(i)[2] + 
                                           ", Total Mark: " + totalMarksList.get(i));
                    }
                    break;
                case 2:
                    // Display highest and lowest marks
                    System.out.println("Highest mark: " + highMark);
                    System.out.println("Lowest mark: " + lowMark);
                    break;
                case 3:
                    // Display mean and standard deviation
                    System.out.println("Mean: " + mean);
                    System.out.println("Standard Deviation: " + stndDev);
                    break;
                case 4:
                    // Display top 5 students with highest total marks
                    System.out.println("Top 5 Students with Highest Total Marks:");
                    for (int i = noStudents - 1; i >= Math.max(0, noStudents - 5); i--) {
                        System.out.println("Student Name: " + studentNames.get(i) + ", Student ID: " + studentIDs.get(i) + 
                                           ", Total Mark: " + totalMarksList.get(i));
                    }
                    break;
                case 5:
                    // Display top 5 students with lowest total marks
                    System.out.println("Top 5 Students with Lowest Total Marks:");
                    for (int i = 0; i < Math.min(5, noStudents); i++) {
                        System.out.println("Student Name: " + studentNames.get(i) + ", Student ID: " + studentIDs.get(i) + 
                                           ", Total Mark: " + totalMarksList.get(i));
                    }
                    break;
                case 6:
                    // Display students with total marks less than threshold
                    System.out.println("Enter the threshold:");
                    float threshold = -1;
                    while (true) {
                        try {
                            threshold = scanner.nextFloat();
                            if (threshold < 0) {
                                System.out.println("Threshold must be non-negative. Please try again.");
                            } else {
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            scanner.next(); // Consume the invalid input
                        }
                    }

                    System.out.println("Students with total marks less than " + threshold + ":");
                    for (int i = 0; i < noStudents; i++) {
                        if (totalMarksList.get(i) < threshold) {
                            System.out.println("Student Name: " + studentNames.get(i) + ", Student ID: " + studentIDs.get(i) + 
                                               ", Total Mark: " + totalMarksList.get(i));
                        }
                    }
                    break;
                case 7:
                    // Exit the program
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
