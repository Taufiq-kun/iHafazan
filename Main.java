import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UstazUI app = new UstazUI();
        Scanner scan = new Scanner(System.in);
    
        while (true) {

            try {
                // Display the menu
                System.out.println("\n1. Login\n2. Register\n3. Logout\n4. Exit");
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(scan.nextLine());
                
                // Perform the selected action
                switch (choice) {
                    case 1 -> app.login();
                    case 2 -> app.register();
                    case 3 -> app.logout();
                    case 4 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
            }

            // Close the scanner
            scan.close(); // Temporary fix
            
        }



    }
}