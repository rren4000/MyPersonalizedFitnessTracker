import java.util.InputMismatchException;
import java.util.Scanner;

public class Cal_Manager_UI {
    private final Scanner scanner;

    public Cal_Manager_UI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayWelcomeMessage() {
        clear();
        System.out.println("----------------------------------------\n");
        System.out.println("Welcome to Calorie Manager!");
        System.out.println("\n----------------------------------------\n");
        System.out.println("What would you like to do today?");
        System.out.println("\n\t(1) Track Calories\n\t(2) Calculate BMR\n\t(3) Go Back");
    }

    public int getUserChoice() {
        int choice = 0;
        while (true) {
            try {
                System.out.print("\nPlease enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("\nInvalid input. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input
                this.clear();
            }
        }
        return choice;
    }

    public void displayInvalidChoiceMessage() {
        System.out.println("Invalid choice. Please try again.");
    }

    public void displayExitMessage() {
        System.out.println("Going back to the main menu...");
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}