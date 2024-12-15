import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UstazUI ustaz = new UstazUI();
        StudentDetails student = new StudentDetails();
        Scanner scan = new Scanner(System.in);
        boolean login_sucessfull = false;
    
        while (true) {
            try {
                // Display the menu
                System.out.println("\n1. Login\n2. Register\n3. Logout\n4. Exit");
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(scan.nextLine());
                
                // Perform the selected action
                switch (choice) {
                    case 1 -> login_sucessfull = ustaz.login();
                    case 2 -> ustaz.register();
                    case 3 -> ustaz.logout();
                    case 4 -> {
                        System.out.println("Exiting...");
                        scan.close(); // Close the scanner
                        System.exit(0); // Exit the program
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } 
            catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                scan.nextLine(); // Flush error input
            }

            while (login_sucessfull) {
                try {
                    // Display the menu
                    System.out.println("\n1. Add Student\n2. Edit Student\n3. Delete Student\n4. List Students\n5. Logout");
                    System.out.print("Choose an option: ");
                    int choice = Integer.parseInt(scan.nextLine());
                    
                    // Perform the selected action
                    switch (choice) {
                        case 1 -> student.addStudent();
                        case 2 -> student.editStudent();
                        case 3 -> student.deleteStudent();
                        case 4 -> student.listStudent();
                        case 5 -> {
                            ustaz.logout();
                            login_sucessfull = false; // Exit the inner loop
                        }
                        default -> System.out.println("Invalid choice. Try again.");
                    }
                } 
                catch (Exception e) {
                    System.out.println("Invalid input. Try again.");
                    scan.nextLine(); // Flush error input
                }
            }
        }
    }
}
