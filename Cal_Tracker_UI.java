import java.util.InputMismatchException;
import java.util.Scanner;

public class Cal_Tracker_UI {
    private Scanner scanner;

    public Cal_Tracker_UI() {
        this.scanner = new Scanner(System.in);
    }

    public int getCalorieInput() {
        while (true) {
            try {
                System.out.print("How many calories have you consumed: ");
                int cal = scanner.nextInt();
                scanner.nextLine(); // consume newline
                return cal;
            } catch (InputMismatchException e) {
                clear();
                System.out.println("----------------------------------------");
                System.out.println("\nInvalid input. Please enter a valid integer for calories.\n");
                scanner.nextLine(); // clear the buffer
                this.clear();
            }
        }
    }

    public void displayWelcomeMessage() {
        System.out.println("----------------------------------------\n");
        System.out.println("Welcome to the Calorie Tracker!");
        System.out.println("\n----------------------------------------\n");
    }

    public void displayTotalCalories(int totalCalories) {
        System.out.println("----------------------------------------\n");
        System.out.println("You have consumed a total of " + totalCalories + " today.");
    }

    public void displayClosingMessage() {
        System.out.println("\n----------------------------------------\n");
        System.out.print("Thank you for using the Calorie Tracker! \n\nPress \"Enter\" to return to the main menu.");
        scanner.nextLine();
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
