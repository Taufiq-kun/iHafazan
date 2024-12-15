import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class StudentDetails {
    // Scanner object to get user input
    Scanner scanner = new Scanner(System.in);

    // Create a database to store student details
    static ArrayList<String> studentNames = new ArrayList<>();
    static ArrayList<String> hafazanDetails = new ArrayList<>();

    // Add a file to store student details
    static File studentDataFile = new File("student_data.txt");

    // Count number of students
    static int count = 0;

    public StudentDetails() {
        // Load existing data from the file (if it exists)
        loadUserData();
    }


    public void addStudent (){
        // Get the names of the students
        System.out.println("Enter the names of the student:");
        String name = scanner.nextLine();
        studentNames.add(name); // Add the name to the database

        // Get the Hafazan details of the students
        System.out.println("Enter the Hafazan detail for student:");
        String hafazan = scanner.nextLine();
        hafazanDetails.add(hafazan); // Add the Hafazan detail to the database

        // Increment the count
        count++;

        // Save data to the file
        saveUserData();

        System.out.println("Student added successfully!");
    
    }
    
    
    public void editStudent() {
        // List all the students
        listStudent();
    
        boolean validInput = false; // Flag to track valid input
    
        while (!validInput) {
            try {
                // Prompt user to choose which student to edit
                System.out.print("\nEnter the number of the student you want to edit (1-" + studentNames.size() + "): ");
                int studentIndex = (scanner.nextInt() - 1); // Subtract 1 to get the correct index
                scanner.nextLine(); // Flush the scanner buffer
    
                // Check if the student index is valid
                if (studentIndex >= 0 && studentIndex < studentNames.size()) {
                    System.out.print("Enter the new Hafazan detail for " + studentNames.get(studentIndex) + ": ");
                    String newHafazan = scanner.nextLine();
                    hafazanDetails.set(studentIndex, newHafazan); // Update the Hafazan detail
                    System.out.println("Hafazan detail updated successfully!");
                    validInput = true; // Input is valid, exit the loop
                } else {
                    System.out.println("Invalid student number! Please try again.");
                }
            } catch (Exception e) { // Catch any exceptions
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
    
    
    public void deleteStudent() {
        // List all the students
        listStudent();
    
        boolean validInput = false; // Flag to track valid input
    
        while (!validInput) {
            try {
                // Prompt user to choose which student to delete
                System.out.print("\nEnter the number of the student you want to delete (1-" + studentNames.size() + "): ");
                int studentIndex = (scanner.nextInt() - 1); // Subtract 1 to get the correct index
                scanner.nextLine(); // Flush the scanner buffer
    
                // Check if the student index is valid
                if (studentIndex >= 0 && studentIndex < studentNames.size()) {
                    System.out.println("Deleting student " + studentNames.get(studentIndex) + "...");
                    studentNames.remove(studentIndex); // Remove the student name
                    hafazanDetails.remove(studentIndex); // Remove the Hafazan detail
                    System.out.println("Student and corresponding Hafazan detail deleted successfully!");
                    validInput = true; // Input is valid, exit the loop
                } else {
                    System.out.println("Invalid student number!");
                }
            } catch (Exception e) { // Catch any exceptions
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }


    public void listStudent(){
        // List all the students
        System.out.println("\nList of Students:");
        for (int i = 0; i < studentNames.size(); i++) {
            System.out.println((i + 1) + ". " + studentNames.get(i) + " - Hafazan: " + hafazanDetails.get(i));
        }
    }
    

    private void saveUserData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentDataFile, true))) {
            for (int i = 0; i < studentNames.size(); i++) {
                String data = studentNames.get(i) + "," + hafazanDetails.get(i);
                
                // Check if the data already exists in the file
                if (!isDataAlreadySaved(data)) {
                    writer.write(data);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }


    private boolean isDataAlreadySaved(String data) {
        try (BufferedReader reader = new BufferedReader(new FileReader(studentDataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(data)) {
                    return true; // Data already exists
                }
            }
        } catch (IOException e) {
            System.out.println("Error checking existing data: " + e.getMessage());
        }
        return false; // Data doesn't exist
    }


    private void loadUserData() {
        if (!studentDataFile.exists()) {
            return; // If file doesn't exist, there's no data to load
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(studentDataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    studentNames.add(parts[0]);
                    hafazanDetails.add(parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading user data: " + e.getMessage());
        }
    }
}


