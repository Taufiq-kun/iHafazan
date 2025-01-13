import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProgressTracking {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the file name to scan and update (e.g., student_data.txt): ");
        String fileName = scanner.nextLine();

        File file = new File(fileName);

        if (!file.exists() || !file.isFile()) {
            System.out.println("Error: The file " + fileName + " does not exist.");
            return;
        }

        displayFileContent(file);

        System.out.print("Do you want to update a student's data? (yes/no): ");
        String updateChoice = scanner.nextLine();

        if (updateChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter the student's name to update: ");
            String targetName = scanner.nextLine();

            // Gather updates for each field
            System.out.print("Enter new Surah Number (or press Enter to skip): ");
            String newSurahNumInput = scanner.nextLine();
            Integer newSurahNum = newSurahNumInput.isEmpty() ? null : Integer.parseInt(newSurahNumInput);

            System.out.print("Enter new Start (or press Enter to skip): ");
            String newStartInput = scanner.nextLine();
            Integer newStart = newStartInput.isEmpty() ? null : Integer.parseInt(newStartInput);

            System.out.print("Enter new End (or press Enter to skip): ");
            String newEndInput = scanner.nextLine();
            Integer newEnd = newEndInput.isEmpty() ? null : Integer.parseInt(newEndInput);

            System.out.print("Enter new Date (or press Enter to skip): ");
            String newDate = scanner.nextLine();

            System.out.print("Enter new Feedback (or press Enter to skip): ");
            String newFeedback = scanner.nextLine();

            updateStudentData(file, targetName, newSurahNum, newStart, newEnd, newDate, newFeedback);
        } else {
            System.out.println("No update performed. Program ended.");
        }
    }

    private static void displayFileContent(File file) throws FileNotFoundException {
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splitted = line.split(",");

                if (splitted.length >= 6) {
                    String name = splitted[0];
                    int surah_num = Integer.parseInt(splitted[1]);
                    int start = Integer.parseInt(splitted[2]);
                    int end = Integer.parseInt(splitted[3]);
                    String tarikh = splitted[4];
                    String feedback = splitted[5];

                    System.out.println("Student Name: " + name);
                    System.out.println("  Surah Number: " + surah_num);
                    System.out.println("  Start: " + start);
                    System.out.println("  End: " + end);
                    System.out.println("  Date: " + tarikh);
                    System.out.println("  Feedback: " + feedback);
                    System.out.println("----------------------------------------");
                } else {
                    System.out.println(line);
                }
            }
        }
    }

    private static void updateStudentData(File file, String targetName, Integer newSurahNum, Integer newStart, Integer newEnd, String newDate, String newFeedback) throws IOException {
        List<String> fileContent = new ArrayList<>();
        boolean isUpdated = false;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splitted = line.split(",");

                if (splitted.length >= 6 && splitted[0].equalsIgnoreCase(targetName)) {
                    // Update fields if new values are provided
                    if (newSurahNum != null) splitted[1] = newSurahNum.toString();
                    if (newStart != null) splitted[2] = newStart.toString();
                    if (newEnd != null) splitted[3] = newEnd.toString();
                    if (!newDate.isEmpty()) splitted[4] = newDate;
                    if (!newFeedback.isEmpty()) splitted[5] = newFeedback;

                    line = String.join(",", splitted);
                    isUpdated = true;
                    System.out.println("Data updated for " + targetName);
                }
                fileContent.add(line);
            }
        }

        // Write updated content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String updatedLine : fileContent) {
                writer.write(updatedLine);
                writer.newLine();
            }
        }

        if (!isUpdated) {
            System.out.println("Student with name '" + targetName + "' not found.");
        }
    }
}
