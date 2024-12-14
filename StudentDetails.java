import java.util.Scanner;

public class StudentDetails {
        public static String InsertNames (String[] StuName){
        Scanner scanner = new Scanner(System.in);
        int i;
        for (i = 0; i < StuName.length; i++) {
            System.out.print("Enter name for student " + (i + 1) + ": ");
            StuName[i] = scanner.nextLine();
        }
        return null;
    }
    public static String InsertDetail (String[] Hafazan){
        Scanner scanner = new Scanner(System.in);
        int i;
        for (i = 0; i < Hafazan.length; i++) {
            System.out.print("Enter Hafazan detail for student " + (i + 1) + ": ");
            Hafazan[i] = scanner.nextLine();
        }
        return null;
    }
    public void Edit(String[] StuNames, String[] Hafazan){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nList of Students:");
        for (int i = 0; i < StuNames.length; i++) {
        System.out.println((i + 1) + ". " + StuNames[i] + " - Hafazan: " + Hafazan[i]);
        }

        System.out.print("\nEnter the number of the student you want to edit (1-" + StuNames.length + "): ");
        int studentIndex = (scanner.nextInt()-1) ; 


        if (studentIndex >= 0 && studentIndex < StuNames.length) {
        System.out.print("Enter the new Hafazan detail for " + StuNames[studentIndex] + ": ");
        Hafazan[studentIndex] = scanner.nextLine();
        System.out.println("Hafazan detail updated successfully!");
        }
        else {
        System.out.println("Invalid student number!");
        }       
    }
    
    public void Delete(String[] StuNames, String[] Hafazan){
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("\nList of Students:");
    for (int i = 0; i < StuNames.length; i++) {
        if (StuNames[i] != null) { 
            System.out.println((i + 1) + ". " + StuNames[i] + " - Hafazan: " + Hafazan[i]);
        }
    }

    System.out.print("\nEnter the number of the student you want to delete (1-" + StuNames.length + "): ");
    int studentIndex = (scanner.nextInt() - 1); 

    if (studentIndex >= 0 && studentIndex < StuNames.length && StuNames[studentIndex] != null) {

        System.out.println("Deleting student " + StuNames[studentIndex] + "...");
        StuNames[studentIndex] = null;
        Hafazan[studentIndex] = null;
        System.out.println("Student and corresponding Hafazan detail deleted successfully!");
    } 
    else {
        System.out.println("Invalid student number or student already deleted!");
    }
    }
}

