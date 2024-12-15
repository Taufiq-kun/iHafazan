import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class UstazUI {

    // Create a Scanner object
    static Scanner scan = new Scanner(System.in);

    // Create a database for Ustaz objects
    static ArrayList<String> database_username = new ArrayList<>();
    static ArrayList<String> database_password = new ArrayList<>();

    // File to store credentials 
    static File userDataFile = new File("user_data.txt");

    // Count number of Ustaz objects
    static int count = 0;

    public UstazUI() {
        // Load existing data from the file (if it exists)
        loadUserData();
    }


    public boolean login() {
        // Validate credentials
        System.out.print("Please Enter Your Username: ");
        String username = scan.nextLine();
        System.out.print("Please Enter Your Password: ");
        String password = scan.nextLine();

        // Check if the username and password are correct
        for (int i = 0; i < database_username.size(); i++) {
            if (database_username.get(i).equals(username) && database_password.get(i).equals(password)) {
                System.out.println("Login Successful");
                return true; // Return Login Successful
            }
        }

        System.out.println("Username or Password is incorrect");
        return false; // Return Login Failed
    }


    public void register() {
        // Register a new Ustaz
        System.out.print("Please Enter Your Username: ");
        String username = scan.nextLine();
        System.out.print("Please Enter Your Password: ");
        String password = scan.nextLine();

        // Add the new Ustaz to the database
        database_username.add(username);
        database_password.add(password);

        // Increment the count
        count++;

        // Save data to the file
        saveUserData();

        System.out.println("Registration Successful!");
    }


    public void logout() {
        System.out.println("Logout");
    }


    private void saveUserData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userDataFile, true))) {
            for (int i = 0; i < database_username.size(); i++) {
                String data = database_username.get(i) + "," + database_password.get(i);
                
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
        try (BufferedReader reader = new BufferedReader(new FileReader(userDataFile))) {
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
        if (!userDataFile.exists()) {
            return; // If file doesn't exist, there's no data to load
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(userDataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    database_username.add(parts[0]);
                    database_password.add(parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading user data: " + e.getMessage());
        }
    }
}
